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

결합도를 낮추는 기술에는 여러가지가 있는데, 먼저 **Factory**를 사용해보자. 그중 먼저 기본적으로 만들어야 할 것은 다형성 구현을 위한 인터페이스이다. 

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
이것은 우리가 Factory라는 클래스를 *직접* 만들어서 구현한 것인데, 이제 우리는 Spring에서 제공하는 Factory를 사용해서 구현할 것이다. 

***
### Container

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

#### Bean 속성 

xml 파일에서 beans 로 bean 들을 관리하는데, 이때 bean 안에서 어떠한 속성 키워드를 추가하면 객체의 속성을 바꿀 수 있다. 

```xml
<bean class="test.IPhone" id="iPhone" init-method="initMethod" destroy-method="destroyMethod"/>
<!-- init-method -> 초기화 시 진행할 메서드 이름
	destroy-method -> 객체 삭제 시 진행할 메서드 이름 -->	
	
<bean class="test.GalaxyPhone" id="galaxyPhone" lazy-init="true" scope="singleton"/>
<!-- lazy init -> 객체를 불러올 때 속성정의.-->
```

|keywords|init-method|destroy-method|lazy-init|scope|
|-|-|-|-|-|
|설명|해당 클래스의 객체를 처음 생성할 때 같이 실행하는 메서드이다. 해당 클래스에 `public void initMethod() {...}` 와 같이 메서드를 정의해 주어야 오류가 나지 않는다.|사용한 객체를 삭제할 때 실행되는 메서드이다. init과 마찬가지로 해당 클래스에 `public void destroyMethod() {...}` 와 같이 메서드를 정의해 주어야 한다.|xml파일 특성상, 객체가 사용될지 사용되지 않을지 모르기 때문에 모든 클래스를 정의해놓는다. 그렇게 될 경우, 사용되지 않는 클래스들 역시 xml파일에 존재하므로 코드 실행 시 무조건 객체가 생성된다. 이를 막기 위해, 객체가 필요할 때에만 객체 생성을 돕는 키워드이다. <bean>이 객체를 생성하는 방식은 즉시 로딩(pre-loading)방식으로, 기본값은 `false` 이며 만약 `true` 로 해놓으면 객체가 필요할 때에만 객체가 생성되므로 메모리를 절약할 수 있다.|하나의 객체를 여러번 재사용하는 경우, 메모리를 절약하기 위해 scope를 사용해 `singleton` 으로 해주면 컨테이너에게 싱글톤 패턴유지를 맡길 수 있다. 이렇게 만들면 하나의 객체만 생성하고도 마치 여러개의 객체가 있는 것처럼 사용할 수 있다. 만약 하나의 객체를 여러개 만들어야 하는 경우는 `prototype` 을 사용하면 객체를 사용할 때마다 새로운 객체를 만들게 된다. scope는 singleton이 기본값이다.|

## 의존성 관리 및 주입 

의존성 주입, DI는 Dependency Injection의 약자이다. DI를 하기 위해서는 두가지 방법을 사용하는데, 하나씩 알아보자. 

### 생성자 주입 

일단, 아래 코드를 보자.

```java
public class AppleWatch{
	public AppleWatch() {
		System.out.println("애플워치 객체생성");
	}
	public void volumeUp() {
		System.out.println("애플워치 소리 ++");
	}
	public void volumeDown() {
		System.out.println("애플워치 소리 --");
	}
}
```

```java
public class IPhone implements Phone{
	
	private AppleWatch watch;
	// 이렇게 생성한 객체가 단독으로 사용하는 것이 아니라 IPhone의 멤버변수로 존재하는데, 이를 의존성이라 칭한다. 
	
	public IPhone() {
		System.out.println("아이폰 생성자");
	}
	
	public IPhone(AppleWatch watch) {
		System.out.println("아이폰을 애플워치로 생성!");
		this.watch = watch;
	}
	
	public void volumeUp() {
//		System.out.println("아이폰 소리 ++");
		// 여기서 watch의 객체를 new 로 생성해버리면..? 객체가 낭비됨.
		watch.volumeUp();
	}
	
	public void volumeDown() {
		watch.volumeDown();
	}
```
만약 volumeUp()과 volumeDown() 자체에 new연산자로 watch 객체를 만들면 어떻게 될까? 정상적으로 동작은 하지만, 효율성 관점에서 따져보았을 때 어떤 메서드가 수행될지도 모르는데 객체 메모리를 낭비하는 코드가 될 것이다. 

이를 막기 위해 생성자로 객체를 받아서 멤버변수에 넣어주게 된다. 

이렇게 하면, IPhone을 생성할 때 watch가 들어오기 때문에 watch의 메서드를 수행할 수 있다. 

이때, 이 IPhone 객체를 생성하는 곳이 xml이므로 우리는 xml에서 저 watch가 파라미터 값으로 들어올 수 있도록 설정해주어야 한다. 

```xml
<bean class="test.IPhone" id="iPhone">
	<constructor-arg ref="appleWatch"/>
</bean>
<bean class="test.AppleWatch" id="appleWatch"/>
```
이렇게 하면, appleWatch 객체를 생성한 다음, IPhone객체를 생성할 때 appleWatch객체를 파라미터값으로 받아서 생성자를 수행할 수 있게 된다. 

***

그런데 만약 appleWatch가 고장났다고 해보자. 그럼 다른 Watch을 사용해야 되지 않을까? 여러개의 Watch를 만들기 위해 Watch 인터페이스를 만들어주자. 

```java
public interface Watch {
	void volumeUp();
	void volumeDown();
}
```

그리고 Watch를 상속받는 스마트워치 객체를 만들어보자. 

```java
public class SmartWatch implements Watch{
	public SmartWatch() {
		System.out.println("스마트워치 객체생성");
	}
	public void volumeUp() {
		System.out.println("스마트워치 소리 ++");
	}
	public void volumeDown() {
		System.out.println("스마트워치 소리 --");
	}	
}
```

그럼 이제 아까 만든 IPhone 에서 Watch를 자유자재로 바꿀 수 있도록 하기 위해 아래와 같이 코드를 변경한다. 

```java
public class IPhone implements Phone{
	private Watch watch;	
	public IPhone() {
		System.out.println("아이폰 생성자");
	}	
	public IPhone(Watch watch) {
		System.out.println("아이폰을 애플워치로 생성!");
		this.watch = watch;
	}	
	public void volumeUp() {
		watch.volumeUp();
	}	
	public void volumeDown() {
		watch.volumeDown();
	}
	public void msg() {
		System.out.println("아이폰!");
	}
```

`private Watch watch` 이 코드를 사용하기 위해, 외부에서 watch라는 변수를 주입받는 방법을 사용했다. 그런데 우리는 watch를 하나 더 만들었으니, 하나 더 추가해보자. 

```xml
<bean class="test.IPhone" id="iPhone">
		<property name="watch" ref="smartWatch" />
</bean> 
<bean class="test.AppleWatch" id="appleWatch"/>
<bean class="test.SmartWatch" id="smartWatch"/>
```

이렇게 xml 파일만 변경하면, 자바코드의 변경없이 주입되는 객체를 변경할 수 있다. 즉, 코드의 변화가 점점 줄어들고, 변화하는 장소가 한정적으로 변한다. 

### setter 주입 

setter 주입의 경우, 기본생성자로 일단 객체를 만든 다음에 setter로 주입한다. 그러므로 IPhone.java 파일을 아래와 같이 변경한다. 

```java
public class IPhone implements Phone{
	
	private Watch watch;
	private String uname;
	
	public Watch getWatch() {
		return watch;
	}

	public void setWatch(Watch watch) {
		this.watch = watch;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}
	
	public IPhone() {
		System.out.println("아이폰 생성자");
	}

	public void volumeUp() {
		watch.volumeUp();
	}
	
	public void volumeDown() {
		watch.volumeDown();
	}
```

이제 xml 에서 설정을 바꿔주자. 

```xml
 <bean class="test.IPhone" id="iPhone">
		<property name="watch" ref="appleWatch" />
		<property name="uname" value="티모" />
</bean> 
```

setter로 가져올 때는 property 속성을 사용해 가져온다. name 속성은 멤버변수 이름으로 설정해야 하고, ref 속성은 객체이름으로 설정해야 한다. 