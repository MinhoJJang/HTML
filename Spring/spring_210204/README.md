# JPA

## JPA Annotation Tip

[JPA Annotation](https://gmlwjd9405.github.io/2019/08/11/entity-mapping.html)

|Keyword|explanation|
|-|-|
|@Entity|@Entity가 붙은 클래스는 JPA가 관리하는 클래스로, 해당 클래스를 엔티티라고 한다. JPA를 사용해서 테이블과 매핑할 클래스는 반드시 @Entity 를 붙여야 한다|
|@Table|@Table은 엔티티와 매핑할 테이블을 지정하는 것이다.|
|@Column|`@Column(nullable = false, length = 10)` 회원 이름은 필수, 10글자 초과 X - `@Column(unique = true)` DB에 영향을 주는 것 해당 필드는 unique 함을 의미|
|@Id|pk를 매핑할 경우 사용한다|
|@GeneratedValue|기본키를 방언에 따라 자동으로 지정해주도록 한다|
|@Temproal|날짜 타입을 매핑할 경우 사용한다.|
|@Transient|특정 필드를 칼럼에 매핑하지 않는다.|


## 기존 프로젝트에 JPA 삽입 

0. Project Facets 에서 JPA를 눌러주고 Apply 해주면 된다. 

1. persistence.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<persistence version="2.2" xmlns="http://xmlns.jcp.org/xml/ns/persistence" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/persistence http://xmlns.jcp.org/xml/ns/persistence/persistence_2_2.xsd">
	<persistence-unit name="day35(0204)">
	<class>com.test.app.board.BoardVO</class>
	<!-- class 때문에 BoardVO를 엔티티 객체라고 생각하게 되는 것임  -->
	<properties>
      <!-- 필수 속성 -->
      <property name="hibernate.dialect" value="org.hibernate.dialect.MySQL8Dialect" />
      
      <!-- 옵션 속성 -->
      <property name="hibernare.show_sql" value="true" />
      <property name="hibernate.format_sql" value="true" />
      <property name="hibernate.use_sql_comments" value="true" />
      <property name="hibernate.id.new_generater_mappings" value="true" />
      <property name="hibernate.hbm2ddl.auto" value="update" /> <!-- create update create-drop -->
   </properties>
	</persistence-unit>
</persistence>
```

2. BoardVO 수정 

BoardVO를 persistence.xml 에서 인식할 수 있도록 하기 위해 적절한 Annotation을 활용해 VO를 수정해준다. 

```java
package com.test.app.board;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;

@Entity
@Table(name="BOARD")
public class BoardVO {
	
	@Id
	@GeneratedValue
	private int bid;
	
	private String title;
	private String writer;
	private String content;
	
	@Temporal(TemporalType.DATE)
	private Date bdate=new Date();
	
	private int cnt;
	
	@Transient
	private String searchCondition;
	@Transient
	private String searchContent;
	@Transient
	private MultipartFile file;
	
	private String filepath;

    ...getters&setters
}
```

3. applicationContent.xml 수정 

JPA 연동 설정을 해주면 된다. 
```xml
   <!-- DataSource 객체 설정 -->
<bean id="dataSource" class="org.apache.commons.dbcp.BasicDataSource" destroy-method="close">
    <property name="driverClassName" value="com.mysql.cj.jdbc.Driver" />
    <property name="url" value="jdbc:mysql://localhost:3306/mhj" />
    <property name="username" value="mhj" />
    <property name="password" value="0000" />
</bean>

<!-- JPA 연동 설정 -->
   <bean id="jpaVendorAdapter" class="org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter" />
   <bean id="entityManagerFactory" class="org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean">
      <property name="dataSource" ref="dataSource" />
      <property name="jpaVendorAdapter" ref="jpaVendorAdapter" />
      <!-- <property name="packagesToScan" value="com.test.app.board" />
      <property name="jpaProperties">
         <props>
            <prop key="org.hibernate.dialect">org.hibernate.dialect.Oracle10gDialect</prop>
            <prop key="hibernare.show_sql">true</prop>
         </props>
      </property> -->

   </bean>
```
주석 부분의 경우 persistence.xml에 있던 필수 속성과 옵션 속성을 applicationContext.xml에서 정의하려고 하는 경우에 사용할 수 있다. 그러나 지금은 persistence.xml에 이미 정의해 놓았으므로 당장 필요없는 주석이다. 

4. DAO 추가 

JPA와 연결을 위한 DAO를 따로 만들어주자. 
