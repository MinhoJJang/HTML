# AOP 

AOP 란?
- Aspect Oriented Programming (관점 지향 프로그래밍)

핵심관심: core concern => 비즈니스 메서드 CRUD
(트랜잭션, 로그, 예외처리) 횡단관심 crosscutting concern

☆유지보수
핵심관심 전후에 수행되는 횡단관심들의 유지보수 향상을 위해 AOP를 사용한다. 

## Before Using AOP

1. CRUD메서드 전에 로그를 적는 LogAdvice 클래스 생성
```java
package com.test.app.common;

public class LogAdvice {
    public void printLog() {
        System.out.println("비즈니스 로직 전에 출력하는 로그");
    }
}
```

2. BoardServiceImpl로 가서 CRUD메서드 수행 전에 로그 호출
```java
public void insertBoard(BoardVO vo) {		
		LogAdvice la = new LogAdvice();
		la.printLog();		
		boardDAO.insertBoard(vo);
	}

public void updateBoard(BoardVO vo) {
        LogAdvice la = new LogAdvice();
		la.printLog();
		boardDAO.updateBoard(vo);
	}

    ...
```
이런식으로 만들면 싱글톤 패턴이 깨지는데, new가 계속 생기기 때문이다. 
따라서 아래와 같이 변경한다. 

```java
private LogAdvice la;
	
public  BoardServiceImpl() {
    la = new LogAdvice();
}

@Override
public void insertBoard(BoardVO vo) {		
    // LogAdvice la = new LogAdvice();
    la.printLog();
    
    boardDAO.insertBoard(vo);
}
```

3. LogAdvice2.java 생성 후 코드변경

이렇게 만들면, 코드하나하나를 바꿔주어야 하기 때문에 너무 귀찮아진다 . 그래서 AOP가 필요한 것 

## AOP 사용 순서

1. AOP관련 pom.xml 설정

아래 코드를 pom.xml에 추가해준다. 
```xml
<!-- AspectJ -->
    <dependency>
        <groupId>org.aspectj</groupId>
        <artifactId>aspectjrt</artifactId>
        <version>${org.aspectj-version}</version>
    </dependency>	
    
    <dependency>
        <groupId>org.aspectj</groupId>
        <artifactId>aspectjweaver</artifactId>
        <version>1.8.8</version>
    </dependency>
```
![1](./images/1.png)

2. namespace추가 

![]()

3. .xml에 결합관련 설정 작성

    - la=new LogAdvice();을 위한 <bean>설정
    - 비즈니스메서드 호출시, la.printLog()호출될수있도록 설정

```xml
<bean id="la" class="com.test.app.common.LogAdvice" />
   <aop:config>
      <aop:pointcut expression="execution(* com.test.app..*Impl.*(..))" id="aPointcut"/>
      <aop:aspect ref="la">
         <aop:before pointcut-ref="aPointcut" method="printLog"/>
      </aop:aspect>
   </aop:config>
```

- pointcut == (비즈니스 메서드, CRUD)
    + 표현식
    + 이름

- aspect 
    + pointcut
    + 횡단관심 

