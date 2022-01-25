# mybatis 

## board-mapping.xml 변경해보기 

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="BoardDAO">

   <resultMap type="board" id="boardResult">
      <id property="bid" column="BID" />
      <result property="title" column="TITLE" />
      <result property="writer" column="WRITER" />
      <result property="content" column="CONTENT" />
      <result property="bdate" column="BDATE" />
      <result property="cnt" column="CNT" />
      <result property="filepath" column="FILEPATH" />
   </resultMap>
   
  <insert id="insertBoard" parameterType="board">
      insert into board(title,writer,content,filepath) values(#{title},#{writer},#{content},#{filepath})
   </insert>

    <select id="selectOne" parameterType="board" resultMap="boardResult">
      select * from board where bid=#{bid}
   </select>

   <select id="selectW" parameterType="board" resultMap="boardResult">
   	<![CDATA[
   		  SELECT * FROM BOARD WHERE WRITER LIKE #{searchContent} ORDER BY BID DESC
      ]]>
      <!-- select * from board where writer like #{searchContent} order by bid desc -->
   </select>
   
    <select id="selectT" parameterType="board" resultMap="boardResult">
   	<![CDATA[
   		  SELECT * FROM BOARD WHERE TITLE LIKE #{searchContent} ORDER BY BID DESC
      ]]>
      <!-- select * from board where writer like #{searchContent} order by bid desc -->
   </select>

   <update id="updateBoard" parameterType="board">
      update board set title=#{title},content=#{content} where bid=#{bid}
   </update>

   <delete id="deleteBoard" parameterType="board">
      delete from board where bid=#{bid}
   </delete>
   
</mapper>
```

## CDATA Section

- CDATA = Character DATA 
- 모든 XML 문서 내에서 활용될 수 있다. 
- XML에서 CDATA Section은 XML parser이 해당 내용을 해석할 수 없도록 한다. 
- 해당 부분의 SQL문들은 가독성을 위해 대문자로 작성한다. 

이런식으로 작성할 수 있다. 
```JSP
<select id="selectAll" parameterType="board" resultMap="boardResult">
   	<![CDATA[
   		  SELECT * FROM BOARD WHERE BID<=#{bid} ORDER BY BID DESC
      ]]>
      <!-- select * from board where writer like #{searchContent} order by bid desc -->
   </select>
```
이렇게 쓰는 이유는, 마이바티스에서 매퍼 파일은 XML으로 작성되어 있고, 파싱될 때 XML 표준으로 파싱된다. SELECT문에는 조건을 걸어 쿼리하기 위해 <, >, = 등의 기호를 많이 사용하는데, 이것이 파싱 중에 태그로 인식되거나 하는 등의 문제가 생길 수 있다. <![CDATA[  ]]> 안에 원하는 쿼리문을 선언 한다면, 파싱하지 않고 그대로 문자열로 인식 시킬 수 있어 이런 문제를 예방할 수 있다.동적 SQL에서는 사용하지 못하는데, 필요한 특수문자에 한해서만 적용시키면 동적 SQL에서도 사용 가능하다.

# Spring과 Mybatis 연결 

1. pom.xml 확인
```xml
<!-- Mybatis -->
<dependency>
    <groupId>org.mybatis</groupId>
    <artifactId>mybatis</artifactId>
    <version>3.3.1</version>
</dependency>
<dependency>
    <groupId>org.mybatis</groupId>
    <artifactId>mybatis-spring</artifactId>
    <version>1.2.4</version>
</dependency>
```

2. sql-map-config.xml 수정

applicationContent.xml 에서 이미 DataSource를 정의하고 있기 때문에, DataSource와 관련된 코드들을 없애주자. 
```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE configuration PUBLIC "-//mybatis.org//DTD Config 3.0//EN" "http://mybatis.org/dtd/mybatis-3-config.dtd">
<configuration>
   <!-- <properties resource="db.properties" /> -->
   
   <typeAliases>
      <typeAlias alias="board" type="com.test.app.board.BoardVO" />
   </typeAliases>
   
   <!-- DataSource관련설정 
   <environments default="development">
      <environment id="development">
         <transactionManager type="JDBC" />
         <dataSource type="POOLED">
            <property name="driver" value="${jdbc.driver}" />
            <property name="url" value="${jdbc.url}" />
            <property name="username" value="${jdbc.username}" />
            <property name="password" value="${jdbc.password}" />
         </dataSource>
      </environment>
   </environments> -->
   
   <!-- Sql Mapper 설정 -->
   <mappers>
      <mapper resource="mappings/board-mapping.xml" />
   </mappers>
</configuration>
```

3. applicationContent.xml 에서 mybatis 설정 

```xml
 <!-- DataSource 객체 설정 -->
<bean id="dataSource" class="org.apache.commons.dbcp.BasicDataSource" destroy-method="close">
    <property name="driverClassName" value="com.mysql.cj.jdbc.Driver" />
    <property name="url" value="jdbc:mysql://localhost:3306/mhj" />
    <property name="username" value="mhj" />
    <property name="password" value="0000" />
</bean>

<!-- mybatis 설정 -->
<bean id="sessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
    <property name="dataSource" ref="dataSource" />
    <property name="configLocation" value="classpath:sql-map-config.xml" />
</bean>
```

***

```java
package com.test.app.board;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class SqlSessionFactoryBean {
   private static SqlSessionFactory sessionFactory=null;
   // "객체와 무관하게"
   static {
      try {
         if(sessionFactory == null) {
            Reader reader = Resources.getResourceAsReader("sql-map-config.xml");
            sessionFactory=new SqlSessionFactoryBuilder().build(reader);
         }
      } catch (IOException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
   }
   public static SqlSession getSqlSessionInstance() {
      return sessionFactory.openSession();
   }
}
```

여기서 mybatis설정을 잘 보면, 우리가 전에 만든 SqlSessionFactoryBean 에서 사용했던 `Reader reader = Resources.getResourceAsReader("sql-map-config.xml");` 이 코드와, `<property name="configLocation" value="classpath:sql-map-config.xml" />` 이 부분이 같은 역할을 한다는 것을 눈치챌 수 있다. 

또한, `<property name="configLocation" value="classpath:sql-map-config.xml" />` 에서 classpath을 붙여주어야 src/main/resources 내부를 찾아가서 해당 파일을 찾아낸다. 

4. BoardDAO 수정 

> 기존 DAO

우리가 직접 만든 SqlSessionFactoryBean 을 사용한다. 
```java
package com.test.app.board.impl;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import com.test.app.board.BoardVO;
import com.test.app.board.SqlSessionFactoryBean;

public class BoardDAO4 {
   private SqlSession mybatis;
   public BoardDAO4() {
      mybatis=SqlSessionFactoryBean.getSqlSessionInstance();
   }
   public void insertBoard(BoardVO vo) {
      mybatis.insert("BoardDAO.insertBoard", vo);
      mybatis.commit();
   }
   public void updateBoard(BoardVO vo) {
      mybatis.update("BoardDAO.updateBoard", vo);
      mybatis.commit();
   }
   public void deleteBoard(BoardVO vo) {
      mybatis.delete("BoardDAO.deleteBoard", vo);
      mybatis.commit();
   }
   public List<BoardVO> selectAll(BoardVO vo){
      return mybatis.selectList("BoardDAO.selectAll", vo);
   }
   public BoardVO selectOne(BoardVO vo) {
      return (BoardVO)mybatis.selectOne("BoardDAO.selectOne", vo);
   }
```
이제 우리가 임시로 만든 SqlSessionFactoryBean 이 필요없고, spring에서 제공하는 것을 사용할 것이다. 이때, 상속받는 방식과 Annotation을 사용하는 방법이 있다. 

먼저 상속받는 방식을 살펴보자. 

> DAO (extends)
```java
package com.test.app.board.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;

import com.test.app.board.BoardVO;

// 1. 상속
// 2. @
public class BoardDAO4 extends SqlSessionDaoSupport {
   
   @Autowired
   public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
      super.setSqlSessionFactory(sqlSessionFactory);
   }
   
   public void insertBoard(BoardVO vo) {
      getSqlSession().insert("BoardDAO.insertBoard", vo);
   }
   public void updateBoard(BoardVO vo) {
      getSqlSession().update("BoardDAO.updateBoard", vo);
   }
   public void deleteBoard(BoardVO vo) {
      getSqlSession().delete("BoardDAO.deleteBoard", vo);
   }
   public List<BoardVO> selectAll(BoardVO vo){
      return getSqlSession().selectList("BoardDAO.selectAll", vo);
   }
   public BoardVO selectOne(BoardVO vo) {
      return (BoardVO)getSqlSession().selectOne("BoardDAO.selectOne", vo);
   } 
}
```
이 방식도 작동하긴 하지만, 코드의 강제성이 부족하다는 취약점이 존재한다. 그렇기 때문에 Annotation을 주로 사용한다. 

> DAO (Annotation)

일단 DAO를 바꾸기 전에 applicationContent.xml을 수정해주어야 한다. 
```xml
<!-- mybatis 설정 -->
<bean id="sessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
    <property name="dataSource" ref="dataSource" />
    <property name="configLocation" value="classpath:sql-map-config.xml" />
</bean>
<bean class="org.mybatis.spring.SqlSessionTemplate">
<!--  SqlSessionTemplate은 setter가 없다. 따라서 생성자 주입을 사용한다. -->
    <constructor-arg ref="sqlSession"/>
</bean>
```

그리고 DAO를 만들어준다. 

```java

```

여기서, BoardDAO~~라고 되어 있는데, 이 BoardDAO는 어디서 만들어졌길래 DAO 파일에서 인식할 수 있을까? 바로 board-mapping.xml이다. 

그렇다면 board-mapping.xml 을 볼 수 있게 해주는 것은 무엇일까? 바로 sql-map-config.xml의 sql mapper이다. 