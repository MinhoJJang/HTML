# Mybatis 설치 

Eclipse Marketplace에서 mybatis을 설치하였다. mybatis 1.2.4 

## Annotation으로 예외처리하기 

예외가 발생할 때 Annotation으로 처리할 수 있다. 
먼저, 예외를 강제로 발생시키도록 해보자. 

```java
@RequestMapping(value="/login.do",method=RequestMethod.POST)
	public String login(MemberVO vo,HttpSession session) {
		
		if(vo.getMid()==null || vo.getMid().equals("")) {
			throw new IllegalArgumentException("아이디 입력을 해주세요");
		}
		
		System.out.println("로그: login() @컨트롤러");
		MemberVO data=ms.selectOne(vo);
		if(data!=null) {
			///session.setAttribute("userName", data.getName());
			session.setAttribute("memData", data); // 마이페이지에서 이용할 데이터
			return "main.do";
		}
		return "index.jsp";
	}
```

여기서 IllegalArgumentException 을 통해, 아이디를 입력하지 않았을 때 로그인을 누르면 예외가 발생하도록 만들었다. 저대로 그냥 실행시키면 못생긴 로그들..(StackTrace)이 잔뜩 나온다. 그게 싫으니까, error.jsp 같은 파일을 만들어놓고 에러가 발생하면 그 페이지를 보여주도록 해보자. 

1. 먼저 예외를 처리하는 Handler가 필요하다. 

```java
package com.test.app.common;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice("com.test.app")
public class CommonExceptionHandler {
	
	@ExceptionHandler(NullPointerException.class)
	public ModelAndView excep1(Exception e) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("exception", e);
		mav.setViewName("error/error.jsp");
		return mav;
	}
	@ExceptionHandler(IllegalArgumentException.class)
	public ModelAndView excep2(Exception e) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("exception", e);
		mav.setViewName("error/errorId.jsp");
		return mav;
	}
}
```

2. DispatcherServlet가 annotation을 인식할 수 있도록 해준다. 

```xml
<mvc:annotation-driven/>
```

혹은, 예외처리 설정을 따로 해줘도 된다. 
```xml
<!-- 예외처리 설정 -->
   <bean id="exceptionResolver" class="org.springframework.web.servlet.handler.SimpleMappingExceptionResolver">
      <property name="exceptionMappings">
         <props>
            <prop key="java.lang.NullPointerException">error/error.jsp</prop>
            <prop key="java.lang.IllegalArgumentException">error/errorId.jsp</prop>
         </props>
      </property>
      <!-- <property name="defalut~~" -> 기타 에러에 해당하는 경우를 위해 설정 -->
   </bean>
``` 

# Mybatis 사용하기! 

장점: 코드가 간결해진다. 

    - SQL 쿼리문을 DAO로부터 분리할 수 있게 해준다. 

1. board-mapping.xml 생성

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="BoardDAO">

   <insert id="insertBoard">
      insert into board(title,writer,content,filepath) values(#{title},#{writer},#{content},#{filepath})
   </insert>

   <select id="selectOne" resultType="board">
      select * from board where bid=#{bid}
   </select>

   <select id="selectAll" resultType="board">
      select * from board where writer like #{searchContent} order by bid desc
   </select>

   <update id="updateBoard">
      update board set title=#{title},content=#{content} where bid=#{bid}
   </update>

   <delete id="deleteBoard">
      delete from board where bid=#{bid}
   </delete>
   
</mapper>
```

2. db.properties 생성 

```sql
jdbc.driver="com.mysql.cj.jdbc.Driver"
jdbc.url="jdbc:mysql://localhost:3306/mhj"
jdbc.user="mhj"
jdbc.password="0000"
```

3. pom.xml 설정

mybatis와 관련된 의존성을 추가해준다. 
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

4. sql-map-config.xml 생성

참고로, 이 파일을 새로 만들면 아래와 같은 코드가 나온다. 
여기서 `<jdbcConnection connectionURL="???" driverClass="???" password="???" userId="???" />` 을 볼 수 있는데, 마치 jdbc와 비슷한 양상임을 볼 수 있다. 이를 우리는 db.properties에서 한번에 관리하도록 하겠다. 
```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE generatorConfiguration PUBLIC "-//mybatis.org//DTD MyBatis Generator Configuration 1.0//EN" "http://mybatis.org/dtd/mybatis-generator-config_1_0.dtd">
<generatorConfiguration>
  <context id="context1">
    <jdbcConnection connectionURL="???" driverClass="???" password="???" userId="???" />
    <javaModelGenerator targetPackage="???" targetProject="???" />
    <javaClientGenerator targetPackage="???" targetProject="???" type="XMLMAPPER" />
    <table schema="???" tableName="???">
      <columnOverride column="???" property="???" />
    </table>
  </context>
</generatorConfiguration>
```

이것을 입맛대로 원하는 대로 바꿔서 작성하면 된다.

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE configuration PUBLIC "-//mybatis.org//DTD Config 3.0//EN" "http://mybatis.org/dtd/mybatis-3-config.dtd">
<configuration>
   <properties resource="db.properties" />
   
   <typeAliases>
      <typeAlias alias="board" type="com.test.app.board.BoardVO" />
   </typeAliases>
   
   <!-- DataSource관련설정 -->
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
   </environments>
   
   <!-- Sql Mapper 설정 -->
   <mappers>
      <mapper resource="mappings/board-mapping.xml" />
   </mappers>
</configuration>
```

5. SqlSessionFactory

SqlSessionFactory 라는 객체가 DataSource를 참고하여 MySql과 Mybatis을 연결해준다. 일단 SqlSessionFactory 내부 구조를 이해하기 위해 직접 클래스를 만들어보자. 

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
         e.printStackTrace();
      }
   }
   public static SqlSession getSqlSessionInstance() {
      return sessionFactory.openSession();
   }
}
``` 

6. DAO 생성 

~~

## + 오류발생? 

ClassNotFound.. oracle.jdbc.driver 머시깽이를 못찾는단다... 이유를 알아보자 