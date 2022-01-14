# Spring 트랜잭션 

Spring에서는 트랜잭션을 선언적으로 처리한다. 

이 말의 뜻은, 트랜잭션을 컨테이너가 처리하게 하고, 이는 우리가 직접 어떠한 클래스를 만들어서 처리하는 것이 아니기 때문에, 자동으로 처리된다. 

즉, 우리가 AOP를 처리하는 클래스를 만들지 않으므로, 클래스 위에 사용하는 @ 를 사용할 수 없다. 은닉된 클래스를 불러와서 사용하기 때문에, 트랜잭션이 사용하는 메서드명을 알지 못한다. 따라서 반드시 xml로 설정해주어야 한다. 

사용하는 DB연동기술에 따라 설정이 달라진다. 가장 많이 사용하는 JDBC + DataSourceTransactionManager 을 사용할 것이다. 추가적으로 Mybatis, JPA 같은 것들을 사용할 수도 있다. 

## Spring 트랜잭션 설정 

### 1. applicationContent.xml 설정 

이때, xml의 Namespaces에서 tx를 추가해주어야 한다. 

```xml
<!-- DataSource 객체 설정 -->
<bean id="dataSource" class="org.apache.commons.dbcp.BasicDataSource" destroy-method="close">
    <property name="driverClassName" value="com.mysql.cj.jdbc.Driver" />
    <property name="url" value="jdbc:mysql://localhost:3306/mhj" />
    <property name="username" value="mhj" />
    <property name="password" value="0000" />
</bean>

<!-- 트랜잭션관련설정 -->
<bean id="txManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
    <property name="dataSource" ref="dataSource" />
</bean>

<!-- Advice설정 -->
<tx:advice id="txAdvice" transaction-manager="txManager">
<tx:attributes>
    <tx:method name="select*" read-only="true"/>
    <tx:method name="*"/>
</tx:attributes>
</tx:advice>

  <!-- advisor를 설정하는 이유는 aspect설정을 하기위헤서는 메서드명이 필요한데 지금 우리는 트랜잭션을 컨테이너에게 맞겼기 때문에 메서드명을 알지 못한다. 따라서 aspect를 쓰지 못하기 때문에 advisor을 사용하는 것이다.  -->
 <aop:config>
      <aop:pointcut expression="execution(* com.test.app..*Impl.*(..))" id="cPointcut"/>
      <aop:advisor advice-ref="txAdvice" pointcut-ref="cPointcut"/>
   </aop:config>
   
```