# JPA

JPA란 Java Persistence API의 약자로, Java 객체와 관계형 DB를 맵핑해주는 ORM 프레임워크입니다.

## JPA 프로젝트 생성 

1. Maven Project

## 작업!

1. Board.java 생성 
```java
package com.mhj.app.board.JPAProject;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TemporalType;

/**
 * Entity implementation class for Entity: Board
 *
 */
@Entity
@Table(name="BOARD")
public class Board implements Serializable {

   @Id
   @GeneratedValue
   private int bid;
   private String title;
   private String writer;
   private String content;
   @Temporal(TemporalType.DATE)
   private Date bdate=new Date();
   private int cnt;
   public int getBid() {
      return bid;
   }
   public void setBid(int bid) {
      this.bid = bid;
   }
   public String getTitle() {
      return title;
   }
   public void setTitle(String title) {
      this.title = title;
   }
   public String getWriter() {
      return writer;
   }
   public void setWriter(String writer) {
      this.writer = writer;
   }
   public String getContent() {
      return content;
   }
   public void setContent(String content) {
      this.content = content;
   }
   public Date getBdate() {
      return bdate;
   }
   public void setBdate(Date bdate) {
      this.bdate = bdate;
   }
   public int getCnt() {
      return cnt;
   }
   public void setCnt(int cnt) {
      this.cnt = cnt;
   }
   
   @Override
   public String toString() {
      return "Board [bid=" + bid + ", title=" + title + ", writer=" + writer + ", content=" + content + ", bdate="
            + bdate + ", cnt=" + cnt + "]";
   }
}
```
2. pom.xml 수정

```xml
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
  <modelVersion>4.0.0</modelVersion>

  <groupId>com.mhj.app.board</groupId>
  <artifactId>JPAProject</artifactId>
  <version>0.0.1-SNAPSHOT</version>
  <packaging>jar</packaging>

  <name>JPAProject</name>
  <url>http://maven.apache.org</url>

  <properties>
    <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
  </properties>

  <dependencies>
  		<dependency>
           <groupId>org.hibernate</groupId>
         <artifactId>hibernate-entitymanager</artifactId>
         <version>5.1.0.Final</version>
        </dependency> 
        
 
        	<dependency>
           <groupId>mysql</groupId>
           <artifactId>mysql-connector-java</artifactId>
           <version>8.0.22</version>
        </dependency>
      
    <dependency>
      <groupId>junit</groupId>
      <artifactId>junit</artifactId>
      <version>3.8.1</version>
      <scope>test</scope>
    </dependency>
  </dependencies>
</project>
```

3. persistance.xml 수정

```xml
<!-- <?xml version="1.0" encoding="UTF-8"?> -->
<persistence version="2.1" xmlns="http://xmlns.jcp.org/xml/ns/persistence"
   xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
   xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/persistence/persistence_2_1.xsd">
   <persistence-unit name="JPAProject">
      <class>com.mhj.app.board.JPAProject.Board</class>
   </persistence-unit>
   <properties>
     
      <property name="javax.persistence.jdbc.driver" value="com.mysql.cj.jdbc.Driver" />
      <property name="javax.persistence.jdbc.url" value="jdbc:mysql://localhost:3306/mhj" />
      <property name="javax.persistence.jdbc.user" value="mhj" />
      <property name="javax.persistence.jdbc.password" value="0000" />
      <property name="hibernate.dialect" value="org.hibernate.dialect.MySQL8Dialect" />
      
      <!-- 옵션 속성 -->
      <property name="hibernare.show_sql" value="true" />
      <property name="hibernate.format_sql" value="true" />
      <property name="hibernate.use_sql_comments" value="true" />
      <property name="hibernate.id.new_generater_mappings" value="true" />
      <property name="hibernate.hbm2ddl.auto" value="update" /> <!-- create update create-drop -->
   </properties>
</persistence>
``` 

4. Client 생성
```java
package com.mhj.app.board.JPAProject;



import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;


public class Client {
	public static void main(String[] args) {
	      // EntityManager 생성하기
	      EntityManagerFactory emf=Persistence.createEntityManagerFactory("JPAProject");
	      EntityManager em=emf.createEntityManager();

	      // Transaction 생성하기
	      EntityTransaction etx=em.getTransaction();

	      etx.begin();
	      try {
	         Board board=new Board();
	         board.setContent("JPA 실습중입니다! :D");
	         board.setTitle("JPA 제목");
	         board.setWriter("JPA 작성자");

	         em.persist(board); // insert
	         
	         etx.commit();
	         
	         String jpql="select b from Board b order by b.bid desc"; // SQL의 표준언어로 작성하는것이 특징!
	         List<Board> datas=em.createQuery(jpql).getResultList();
	         for(Board v:datas) {
	            System.out.println(v);
	         }
	      }catch(Exception e) {
	         e.printStackTrace();
	         etx.rollback();
	      }finally {
	         em.close();
	         emf.close();
	      }
	   }
}
``` 

## 오류발생 

이유모를 에러발생
`Unable to locate persistence units`

java버전 1.8로 낮춰서 하라는 의견이 많은데, 좀 더 자세히 알아보아야겠다. 
```
Exception in thread "main" javax.persistence.PersistenceException: Unable to locate persistence units
	at org.hibernate.jpa.HibernatePersistenceProvider.getEntityManagerFactoryBuilderOrNull(HibernatePersistenceProvider.java:84)
	at org.hibernate.jpa.HibernatePersistenceProvider.getEntityManagerFactoryBuilderOrNull(HibernatePersistenceProvider.java:71)
	at org.hibernate.jpa.HibernatePersistenceProvider.createEntityManagerFactory(HibernatePersistenceProvider.java:52)
	at javax.persistence.Persistence.createEntityManagerFactory(Persistence.java:55)
	at javax.persistence.Persistence.createEntityManagerFactory(Persistence.java:39)
	at com.mhj.app.board.JPAProject.Client.main(Client.java:16)
Caused by: javax.persistence.PersistenceException: Invalid persistence.xml.
Error parsing XML [line : -1, column : -1] : cvc-complex-type.2.4.a: '{"http://xmlns.jcp.org/xml/ns/persistence":properties}' 요소로 시작하는 부적합한 콘텐츠가 발견되었습니다. '{"http://xmlns.jcp.org/xml/ns/persistence":persistence-unit}' 중 하나가 필요합니다.

	at org.hibernate.jpa.boot.internal.PersistenceXmlParser.validate(PersistenceXmlParser.java:357)
	at org.hibernate.jpa.boot.internal.PersistenceXmlParser.loadUrl(PersistenceXmlParser.java:290)
	at org.hibernate.jpa.boot.internal.PersistenceXmlParser.parsePersistenceXml(PersistenceXmlParser.java:94)
	at org.hibernate.jpa.boot.internal.PersistenceXmlParser.doResolve(PersistenceXmlParser.java:84)
	at org.hibernate.jpa.boot.internal.PersistenceXmlParser.locatePersistenceUnits(PersistenceXmlParser.java:66)
	at org.hibernate.jpa.HibernatePersistenceProvider.getEntityManagerFactoryBuilderOrNull(HibernatePersistenceProvider.java:80)
	... 5 more
```