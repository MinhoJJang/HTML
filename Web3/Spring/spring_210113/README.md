# JDBC Template

**필요성**
 -> 유사한 코드가 자주 중복됨
 -> Util클래스와 static메서드() 구현
   "응집도 높임"

이렇게 해도 코드가 너무 많다! 

BoardClient는 View 역할을 하므로, 곧바로 DAO를 호출해 DB로 접근할 수 없다. 따라서 중간 연결다리 역할을 하는 BoardService를 통해 연결할 수 있다. BoardService를 상속받는 BoardServiceImpl에서 DAO를 사용해 DB로 접근할 때 BoardDAO로 이동한다. 이때, DAO의 문제점이 있는데 그것이 바로 위에서 언급한 `유사한 코드 중복` 이다. 얼마나 중복되는지 아래 코드를 살펴보자. 

```java
package com.test.app.board.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.test.app.board.BoardVO;
import com.test.app.common.JDBCUtil;

@Repository("boardDAO")
public class BoardDAO {
   private Connection conn=null;
   private PreparedStatement pstmt=null;
   private ResultSet rs=null;
   
   private String board_insert="insert into newboard(title,writer,content,bdate) values(?,?,?,now())";
   private String board_update="update newboard set title=?,content=? where bid=?";
   private String board_delete="delete newboard where bid=?";
   private String board_selectOne="select * from newboard where bid=?";
   private String board_selectAll="select * from newboard order by bid desc";

   public void insertBoard(BoardVO vo) {
      conn=JDBCUtil.connect();
      try {
         pstmt=conn.prepareStatement(board_insert);
         pstmt.setString(1, vo.getTitle());
         pstmt.setString(2, vo.getWriter());
         pstmt.setString(3, vo.getContent());
         pstmt.executeUpdate();
      } catch (SQLException e) {
         e.printStackTrace();
      } finally {
         JDBCUtil.disconnect(pstmt, conn);
      }      
   }
   public void updateBoard(BoardVO vo) {
      conn=JDBCUtil.connect();
      try {
         pstmt=conn.prepareStatement(board_update);
         pstmt.setString(1, vo.getTitle());
         pstmt.setString(2, vo.getContent());
         pstmt.setInt(3, vo.getBid());
         pstmt.executeUpdate();
      } catch (SQLException e) {
         e.printStackTrace();
      } finally {
         JDBCUtil.disconnect(pstmt, conn);
      }
   }
   public void deleteBoard(BoardVO vo) {
      conn=JDBCUtil.connect();
      try {
         pstmt=conn.prepareStatement(board_delete);
         pstmt.setInt(1, vo.getBid());
         pstmt.executeUpdate();
      } catch (SQLException e) {
         e.printStackTrace();
      } finally {
         JDBCUtil.disconnect(pstmt, conn);
      }
   }
   public BoardVO selectOne(BoardVO vo) {
      BoardVO data=null;
      conn=JDBCUtil.connect();
      try {
         pstmt=conn.prepareStatement(board_selectOne);
         pstmt.setInt(1, vo.getBid());
         rs=pstmt.executeQuery();
         if(rs.next()) { // 하나만 선택하므로 if 사용 
            data=new BoardVO();
            data.setBdate(rs.getDate("bdate"));
            data.setBid(rs.getInt("bid"));
            data.setCnt(rs.getInt("cnt"));
            data.setContent(rs.getString("content"));
            data.setTitle(rs.getString("title"));
            data.setWriter(rs.getString("writer"));     
         }
      } catch (SQLException e) {
         e.printStackTrace();
      } finally {
         JDBCUtil.disconnect(rs, pstmt, conn);
      }   
      return data;
   }
   public List<BoardVO> selectAll(BoardVO vo) {
      List<BoardVO> datas=new ArrayList<BoardVO>();
      conn=JDBCUtil.connect();
      try {
         pstmt=conn.prepareStatement(board_selectAll);
         rs=pstmt.executeQuery();
         while(rs.next()) {
            BoardVO data=new BoardVO();
            data.setBdate(rs.getDate("bdate"));
            data.setBid(rs.getInt("bid"));
            data.setCnt(rs.getInt("cnt"));
            data.setContent(rs.getString("content"));
            data.setTitle(rs.getString("title"));
            data.setWriter(rs.getString("writer"));             
            datas.add(data);
         }
      } catch (SQLException e) {
         e.printStackTrace();
      } finally {
         JDBCUtil.disconnect(rs, pstmt, conn);
      }   
      return datas;
   }
}
```
`conn=JDBCUtil.connect();` 과 같은 중복적인 코드를 사용하지 않기 위해, **JDBC Template** 를 활용하는 것이다. 

**특징**
- MVC 패턴과 같은 설계패턴중 하나이다. 
- 반복되는 로직을 캡슐화해둔 클래스를 이용한다. 
- 순서가 고정된 로직에 특화 
    + jdbc:로드-연결-사용-해제
- sql문에 사용할 값만 코더가 신경쓰면됨
- Service->DAO--Template-->DB

점점 만들어야 할 파일들은 늘어나지만, 파일들간의 연결고리는 약해지기 때문에 유지보수성이 증가한다. 

## JDBC Template 작업 

### 1. pom.xml에서 DBCP dependency 설정 

DBCP = DataBase Connection Pool 
DBCP : 커넥션풀을 관리해주는 설정
   -> dependency 설정
 
```xml
<!--DBCP-->
<dependency>
    <groupId>commons-dbcp</groupId>
    <artifactId>commons-dbcp</artifactId>
    <version>1.4</version>
</dependency>
```

### 2. applicationContent.xml에서 DataSource 객체 설정 

```xml
<!-- DataSource 객체 설정 -->
<bean id="dataSource" class="org.apache.commons.dbcp.BasicDataSource" destroy-method="close">
    <property name="driverClassName" value="com.mysql.cj.jdbc.Driver" />
    <property name="url" value="jdbc:mysql://localhost:3306/mhj" />
    <property name="username" value="mhj" />
    <property name="password" value="0000" />
</bean>
```

### 3. BoardDAO2 생성

BoardDAO의 한계는 중복코드가 많다는 점이고, 이를 해결하기 위해 JDBCTemplate를 사용한다. 이 JdbcTemplate를 사용하는 데에는 두가지 방법이 존재하는데, 하나씩 알아보자. 

#### 3-1. JdbcDAOSupport 클래스 상속받아 구현 

- pom.xml에 아래 코드를 넣어주어야 상속가능하다. 
```xml
<dependency>
<groupId>org.springframework</groupId>
<artifactId>spring-jdbc</artifactId>
<version>${org.springframework-version}</version>
</dependency> 
```

- @Repository 사용
applicationContext.xml 에서 `<context:component-scan base-package="com.test.app"/>` 를 사용해 객체를 찾고 만들기 때문이다. 

- 완성코드 
```java
package com.test.app.board.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import com.test.app.board.BoardVO;

//BoardDAO의 한계: 중복코드多
//-> JDBCTemplate 사용
//      1) JdbcDAOSupport 클래스를 상속받아서 구현

@Repository("boardDAO")
public class BoardDAO2 extends JdbcDaoSupport{

   private String board_insert="insert into newboard(title,writer,content,bdate) values(?,?,?,now())";
   private String board_update="update newboard set title=?,content=? where bid=?";
   private String board_delete="delete newboard where bid=?";
   private String board_selectOne="select * from newboard where bid=?";
   private String board_selectAll="select * from newboard order by bid desc";

   @Autowired // 주로 멤버변수위에서 타입을 먼저보고 DI
   public void setSuperDataSource(DataSource dataSource) {
      super.setDataSource(dataSource);
   }
   // ds객체를 사용할수있는 코드 존재
   // ds객체주입

   public void insertBoard(BoardVO vo) {
      System.out.println("insert수행중");
      getJdbcTemplate().update(board_insert,vo.getTitle(),vo.getWriter(),vo.getContent());
   }
   public void updateBoard(BoardVO vo) {
      System.out.println("update수행중");
      getJdbcTemplate().update(board_update,vo.getTitle(),vo.getContent(),vo.getBid());
   }
   public void deleteBoard(BoardVO vo) {
      System.out.println("delete수행중");
      getJdbcTemplate().update(board_delete,vo.getBid());
   }
   public BoardVO selectOne(BoardVO vo) {
      System.out.println("selectOne수행중");
      Object[] obj= {vo.getBid()};
      return getJdbcTemplate().queryForObject(board_selectOne,obj,new BoardRowMapper());
   }
   
	 public List<BoardVO> selectAll(BoardVO vo) {
		 System.out.println("selectAll수행중");    
	     return getJdbcTemplate().query(board_selectAll, new BoardRowMapper());
	 } 
}

class BoardRowMapper implements RowMapper<BoardVO>{

   @Override
   public BoardVO mapRow(ResultSet rs, int rowNum) throws SQLException {
      BoardVO data=new BoardVO();
      data.setBdate(rs.getDate("bdate"));
      data.setBid(rs.getInt("bid"));
      data.setCnt(rs.getInt("cnt"));
      data.setContent(rs.getString("content"));
      data.setTitle(rs.getString("title"));
      data.setWriter(rs.getString("writer"));
      return data;
   }
}
```
코드가 확연히 줄어들었음을 확인할 수 있다. 

이때 query를 사용하는 데에는 여러가지가 있다.

```
- update()
   insert,update,delete
   오버로딩되어있음
    -> 제목,내용,작성자 VS PK
   sql문에 사용하는 값이 여러개인 경우 VS 1개 경우
오버로딩 vs 오버라이딩
함수명 중복정의   메서드 재정의
- queryForInt()
   select
   데이터가 아닌, 정수값을 반환받고싶을때 사용
- queryForObject()
   select
   데이터를 반환받고싶을때 사용
   RowMapper객체와 "반드시" 함께 사용함!!!!!
     - 테이블당 1개
     - 어떤 VO와 매핑될지에 대한 설정
- query()
   selectAll
```

#### 3-2. JdbcTemplate 클래스 <bean> 등록하여 DI 

- applicationContent.xml 파일에 bean 을 추가해준다. 
```xml

```

- 완성코드 
```java
package com.test.app.board.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.test.app.board.BoardVO;

//BoardDAO의 한계: 중복코드多
//-> JDBCTemplate 사용
//      1) JdbcDAOSupport 클래스를 상속받아서 구현
//		2) JdbcTemplate 클래스 <bean> 등록하여 DI 

// JdbcTemplate 클래스 <bean> 등록하여 DI 

@Repository("boardDAO")
public class BoardDAO3 {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
   private String board_insert="insert into newboard(title,writer,content,bdate) values(?,?,?,now())";
   private String board_update="update newboard set title=?,content=? where bid=?";
   private String board_delete="delete newboard where bid=?";
   private String board_selectOne="select * from newboard where bid=?";
   private String board_selectAll="select * from newboard order by bid desc";

   public void insertBoard(BoardVO vo) {
      System.out.println("insert수행중");
      jdbcTemplate.update(board_insert,vo.getTitle(),vo.getWriter(),vo.getContent());
   }
   public void updateBoard(BoardVO vo) {
      System.out.println("update수행중");
      jdbcTemplate.update(board_update,vo.getTitle(),vo.getContent(),vo.getBid());
   }
   public void deleteBoard(BoardVO vo) {
      System.out.println("delete수행중");
      jdbcTemplate.update(board_delete,vo.getBid());
   }
   public BoardVO selectOne(BoardVO vo) {
      System.out.println("selectOne수행중");
      Object[] obj= {vo.getBid()};
      return jdbcTemplate.queryForObject(board_selectOne,obj,new BoardRowMapper());
   }
   
	 public List<BoardVO> selectAll(BoardVO vo) {
		 System.out.println("selectAll수행중");    
	     return jdbcTemplate.query(board_selectAll, new BoardRowMapper());
	 } 
}




```