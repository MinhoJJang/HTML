# Advice가 동작하는 다양한 시점

## after-returning

1. Advice 추가 

Advice추가 == 횡단관심 추가 
```java
package com.test.app.common;

public class AfterReturningAdvice {
   public void afterLog() {
      System.out.println("비즈니스 메서드 수행후에 출력됨");
   }
}
```
2. bean 추가 
```xml
<bean id="ara" class="com.test.app.common.AfterReturningAdvice"/>
```
3. aspect 설정 

이때, `aop:before` 의 경우 비즈니스 메서드 수행 전에 수행되고, `aop:after-returning` 의 경우 비즈니스 메서드를 수행한 후에 수행된다

```xml
<aop:config>
      <aop:pointcut expression="execution(* com.test.app..*Impl.*(..))" id="aPointcut"/>
      <aop:pointcut expression="execution(* com.test.app..*Impl.select*(..))" id="bPointcut"/>
      <aop:aspect ref="ara">
         <aop:after-returning pointcut-ref="bPointcut" method="afterLog"/>
      </aop:aspect>
   </aop:config>
```

4. Client 실행 
```
Impl 기본 생성자
비즈니스 메서드 수행후에 출력됨
BoardVO [bid=18, title=글 제목, writer=글쓰니, content=작성한 내용, bdate=2021-12-31, cnt=0]
BoardVO [bid=17, title=글 제목, writer=글쓰니, content=작성한 내용, bdate=2021-12-30, cnt=0]
```
## after-throwing

비즈니스 메서드 수행중에 예외나 문제가 발생 시에만 수행된다. 
```java
package com.test.app.common;

public class AfterThrowingAdvice {
	public void exceptionLog() {
		System.out.println("비즈니스 메서드 수행중에 문제, 예외 발생시 출력");
	}
}
```

## after

비즈니스 메서드 수행 후에 반드시 수행된다. 중간에 오류가 발생하든 하지 않든 상관없다. 
```java
package com.test.app.common;

public class AfterAdvice {
	public void finallyLog() {
		System.out.println("비즈니스 메서드 수행 후에 반드시 출력");
	}
}

```

## before 

비즈니스 메서드 수행 전에 수행된다. 

```java
package com.test.app.common;

public class LogAdvice {
		public void printLog() {
			System.out.println("비즈니스 로직 전에 출력하는 로그");
		}
}
```

## around

비즈니스 메서드를 탈취한 후, 비즈니스 메서드 전후로 공통로직을 수행할 수 있으며, 독특하게 작동한다.

```java
@Override
	public List<BoardVO> selectAll(BoardVO vo) {
		//la.printLog();
		System.out.println("어드바이스 동작시점 확인중...");
		return boardDAO.selectAll(vo);
	}
```

```java
package com.test.app.common;

import org.aspectj.lang.ProceedingJoinPoint;

public class AroundAdvice {
	public Object aroundLog(ProceedingJoinPoint pjp) throws Throwable {
		System.out.println("[비즈니스 메서드 수행 전]");
		Object obj = pjp.proceed();
		System.out.println("[비즈니스 메서드 수행 후]");
		return obj;
	}
}
```

```xml
<bean id="aa2" class="com.test.app.common.AroundAdvice"/>
   <aop:config>
      <aop:pointcut expression="execution(* com.test.app..*Impl.*(..))" id="aPointcut"/>
      <aop:pointcut expression="execution(* com.test.app..*Impl.select*(..))" id="bPointcut"/>
      <aop:aspect ref="aa2">
         <aop:around pointcut-ref="aPointcut" method="aroundLog"/>
      </aop:aspect>
   </aop:config>
```

- 결과 
```txt 
Impl 기본 생성자
[비즈니스 메서드 수행 전]
[비즈니스 메서드 수행 후]
[비즈니스 메서드 수행 전]
어드바이스 동작시점 확인중...
[비즈니스 메서드 수행 후]
BoardVO [bid=20, title=글 제목, writer=After, content=작성한 내용, bdate=2021-12-31, cnt=0]
```

이것을 응용하여 좀 재미있는걸 해보자. 

```java
package com.test.app.common;

import org.aspectj.lang.JoinPoint;

public class LogAdvice {
		public void printLog(JoinPoint jp) {
			// jp: 스프링 컨테이너가 new JP() 를 해준다. 즉 jp객체가 생성됨
			// 그냥 argument에 JoinPoint를 넣었을 뿐인데 컨테이너가 자동으로 객체를 만들어준다. 			
			// 현재 수행되는 비즈니스메서드 정보를 세팅한다. 
			
			System.out.println("비즈니스 로직 전에 출력하는 로그");
			String coreMethod = jp.getSignature().getName(); 
			// 지금 실행중인 비즈니스 메서드가 무엇인지 출력가능 
			
			System.out.println("처리중인 비즈니스 메서드 : "+coreMethod);
			
			Object[] args = jp.getArgs();
			System.out.println("args들 출력: ");
			for(Object v : args) {
				System.out.println(v+" ");
			}
			System.out.println();
		}
}
```

이것을 사용해서 출력하면 재미난 결과가 나온다.
```console
JP실습중...
처리중인 비즈니스 메서드 : insertBoard
args들 출력: 
BoardVO [bid=0, title=글 제목, writer=After, content=작성한 내용, bdate=null, cnt=0]
```

바로 인자로 넣은 값을 직접 볼 수 있다는 점이다. 