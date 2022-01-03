# Spring - AOP

1. 업캐스팅_동적바인딩

가장 최근에 구현된(가장 하위클래스) 메서드를 자동호출하는 현상이다. 이를 동적바인딩이라고 하며, 다형성을 실현한다. 

```java
package com.test.app.common;

class A{
   void func() {
      System.out.println("A에서 출력");
   }
}
class B extends A{
   void func() {
      System.out.println("B에서 출력");
   }
}
class C extends A{
   void func() {
      System.out.println("C에서 출력");
   }
}
public class Test {

   public static void main(String[] args) {
      
      // 추상화 상속 다형성 캡슐화 정보은닉      
      // 오버라이딩 -> 동적바인딩
      A[] data=new A[2];
      data[0]=new B(); // 업 캐스팅
      data[1]=new C();
      for(int i=0;i<2;i++) {
         data[i].func();
         // 가장 최근에 구현된(가장 하위클래스의) 메서드를 자동호출하는 현상
         // == 동적바인딩 -> 다형성을 실현했다!
      }     
   }
}
```

2. 다운캐스팅_오버라이딩

```java
package com.test.app.common;

class Point{
	int x;
	int y;
	
	Point(int x, int y){
		this.x = x;
		this.y = y;
	}

	@Override
	public boolean equals(Object obj) {
        // 인자를 Object 로 둠으로써 파라미터로 무엇이 오더라도 처리할 수 있게 하였다. 
		
		if(!(obj instanceof Point)) {
			return false;
		}
		
		Point p = (Point)obj;
		// int x = (int) 3.14;
		if(x == p.x && y == p.y) {
			return true;
		}	
		return false;
	}
}

public class Test2 {
	
	public static void main(String[] args) {
		Point p1 = new Point(10,20);
		Point p2 = new Point(10,20);
		
		if(p1.equals(new A())) {
			System.out.println("??");
			/*예외발생 할 수도 있으므로 
			if(!(obj instanceof Point)) {
				return false;
			}
			이 코드를 넣어준다. */
		}
		
		if(p1.equals(p2)) {
			System.out.println("Same");
		}
		else {
			System.out.println("Different");
		}
	}

}

```
## AfterReturningAdvice 변경

위에서 살펴본 다운캐스팅과 업캐스팅을 응용하여, 메서드의 파라미터값에 JoinPoint, Object 타입 변수가 들어가도록 세팅하였다. 

```java
package com.test.app.common;

import org.aspectj.lang.JoinPoint;

import com.test.app.board.BoardVO;
import com.test.app.member.MemberVO;

public class AfterReturningAdvice {
   public void afterLog(JoinPoint jp, Object obj) {
      System.out.println("비즈니스 메서드 수행후에 출력됨");
      String coreMethod = jp.getSignature().getName();
      System.out.println("현재 수행중인 "+coreMethod+"()이후에 출력됨");
      if(obj instanceof MemberVO) {
    	  MemberVO vo = (MemberVO)obj; // 다운캐스팅
    	  System.out.println("리턴된 객체는 "+ vo+"입니다");   	  
      }
      else if(obj instanceof BoardVO){
    	  BoardVO vo = (BoardVO)obj;
    	  System.out.println("리턴된 객체는 "+ vo+"입니다");   	  
      }
      else {
    	  System.out.println("instanceof체크 false");
      }
   }
}
```
여기도 마찬가지로 Object 타입으로 일단 파라미터를 받고, 그것이 유효한 타입인지는 메서드 안에서 검사해주어야 한다. 

이제 xml설정을 해주면 된다. 

```xml
// TODO
```


## AfterThrowing

```java
package com.test.app.common;

import org.aspectj.lang.JoinPoint;

public class AfterThrowingAdvice {
   public void exceptionLog(JoinPoint jp,Exception excep) { // 바인드 변수
      String coreMethod=jp.getSignature().getName();
      System.out.println("비즈니스 메서드 "+coreMethod+"() 수행중에 문제,예외 발생시 출력");
      System.out.println("예외메세지: "+excep.getMessage());
      
      if(excep instanceof IllegalArgumentException) {
         System.out.println("사용하지않는 매개변수입니다.");
      }
      else if(excep instanceof NullPointerException) {
         System.out.println("객체생성 실패했습니다.");
      }
      else {
         System.out.println("현재 예외는 파악되지않은 예외입니다! 분석이 필요합니다!");
      }
   }
}
```

이런식으로 만들면, 예외가 발생했을 때 어떤 예외, 어떤 오류인지 한눈에 파악이 가능해진다. 그래서 가장 유용하게 쓰이는 곳이기도 하다. 

## Around

around는 메서드가 실행되는데 걸린 시간을 재는 데 사용할 수도 있다. 

```java
package com.test.app.common;

import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.util.StopWatch;

public class AroundAdvice {
	public Object aroundLog(ProceedingJoinPoint pjp) throws Throwable {
	
		System.out.println("[비즈니스 메서드 수행 전]");
		StopWatch sw = new StopWatch();
		sw.start();		
		Object obj = pjp.proceed();
		sw.stop();
		System.out.println("[비즈니스 메서드 수행 후]");
		
		System.out.println(pjp.getSignature().getName()+"() 비즈니스 메서드 실행시간");
		System.out.println(sw.getTotalTimeMillis()+"입니다");
		
		return obj;
	}
}
```