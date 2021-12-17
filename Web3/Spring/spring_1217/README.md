# Spring 

spring을 왜 사용할까? 아래와 같은 장점이 있기 때문이다. 
1. 구현시간이 빠름
2. 유지보수가 용이
3. 개발자 역량 상향평준화
4. 재사용성 용이 

Spring을 한마디로 표현하자면, 아래와 같이 말할 수 있을 것이다. 
> Spring = IoC와 AOP를 지원하는 경량의 컨테이너 프레임워크 

저 요소들 각각이 어떤 의미를 갖는지 알아보자. 
## IoC

IoC = **Inversion of Control**

제어의 역행

자바에서는 메서드를 수행하기 위해, 먼저 그 메서드가 존재하는 클래스의 객체를 만들어야 한다. 그 과정을 바로 컨테이너가 수행한다. 

예를 들어, 서블릿 컨테이너와 doGet()메서드 수행 메서드를 수행하려면, 객체가 있어야한다. => 서블릿 컨테이너==톰캣 
서블릿 컨테이너의 경우, 서블릿 파일만 객체를 생성해 주지만 스프링 컨테이너는 POJO를 포함해서 객체를 생성해 준다. 따라서, 스프링 컨테이너에게 객체 생성 및 관리를 맡긴다. 

1. 서블릿 컨테이너 구동시 web.xml을 확인
2. 브라우저가 hello.do 요청
3. 등록한 서블릿 클래스를 객체화하고 doGet()메서드 호출
4. doGet()메서드 수행결과를 브라우저로 응답

- 개발자가 객체 생성을 스스로 판단하고 직접작성 및 관리
- 컨테이너가 객체 생성 및 관리를 하기때문에, 코드 사이의 결합도가 낮아짐
- 유지보수가 좋은 코드 == 결합도 낮고 응집도 높은 코드


컨테이너가 객체 생성 및 관리를 한다는 것이 무슨 소리일까? 

### Factory 

결합도를 낮추는 기술에는 여러가지가 있는데, 첫번째로 Factory를 사용해보자. 그중 먼저 기본적으로 해야 할 것은 인터페이스이다. 

```java
public interface Phone {
	void volumeUp();
	void volumeDown();
	void msg();
}
```

그리고 이 인터페이스를 상속받는 클래스들을 만들어 주자. 

```java
public class GalaxyPhone implements Phone{	
	public GalaxyPhone() {
		System.out.println("갤럭시 생성자");
	}	
	public void volumeUp() {
		System.out.println("갤럭시 소리 ++");
	}	
	public void volumeDown() {
		System.out.println("갤럭시 소리 --");
	}	
	public void msg() {
		System.out.println("갤럭시!");
	}
}
```
```java
public class IPhone implements Phone{	
	public IPhone() {
		System.out.println("아이폰 생성자");
    }
	public void volumeUp() {
		System.out.println("아이폰 소리 ++");
	}	
	public void volumeDown() {
		System.out.println("아이폰 소리 --");
	}	
	public void msg() {
		System.out.println("아이폰!");
	}
}
```
이렇게 인터페이스와 클래스들을 만들고, Factory라는 클래스를 사용할 것이다. 
```java
public class BeanFactory {
	public Object getBean(String beanName) {
		if(beanName.equals("iPhone")) {
			return new IPhone();
		}
		else if (beanName.equals("galaxyPhone")) {
			return new GalaxyPhone();
		}
		return null;
	}
}
```
그리고 이를 실행시키는 main 클래스를 만들자. 

```java
public static void main(String[] args) {
		// Factory 사용 
		BeanFactory factory = new BeanFactory();
		
		Phone phone = (Phone)factory.getBean(args[0]);
		// Run -> Run Configurations - Client - Arguments - Program Arguments 에서 설정		
		phone.volumeDown();
		phone.msg();
```

### applicationContext.xml 

이제 컨테이너를 사용해서 파일 설정을 해보자. 

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">
	
	<bean class="test.IPhone" id="iPhone"/>	
	<bean class="test.GalaxyPhone" id="galaxyPhone"/>
<!-- 여기 이렇게 써놓으면 xml에서 객체생성을 스프링 컨테이너에게 맡길 수 있다. -->
</beans>
```

이런식으로 bean 으로 객체를 생성하고, 클라이언트에게 저 xml 파일을 읽을 수 있도록 만들어주자.

```java
public class Client {
	
	public static void main(String[] args) {
			
		// SpringContainer 사용 
		// 1. applicationContext.xml 에서 class 및 id 지정 
		AbstractApplicationContext factory = new GenericXmlApplicationContext("applicationContext.xml");
		Phone phone = (Phone)factory.getBean("iPhone");
		phone.volumeDown();
		phone.msg();
		
		factory.close(); // 굳이 안써도 되지만 명시적으로 써주자 
	}
}
```

이렇게 만들고 실행시키면 잘 실행되는 모습을 볼 수 있다. 

### 객체 생성 시 부가적 요소 

xml 파일에서 beans 로 bean 들을 관리하는데, 이때 bean 안에서 어떠한 요소를 추가하면 객체의 특성을 바꿀 수 있다. 

```xml
<bean class="test.IPhone" id="iPhone" init-method="initMethod" destroy-method="destroyMethod"/>
<!-- init-method -> 초기화 시 진행할 메서드 이름
	destroy-method -> 객체 삭제 시 진행할 메서드 이름 -->	
	
<bean class="test.GalaxyPhone" id="galaxyPhone" lazy-init="true" />
<!-- lazy init -> 객체를 사용할 때 불러오는 속성  -->
```