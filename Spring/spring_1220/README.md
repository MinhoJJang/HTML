# Annotation configure

우리는 현재 객체들을 xml 파일에서 다루고 있기 때문에 코드의 설정을 확인하려면 xml 파일을 전체적으로 확인해야한다. 이것이 불편하기 때문에, `@` 를 사용해서 설정을 코드위에 보여줌으로써 설정을 위한 파일인 xml을 분석하는 부담을 줄여준다. 

우리는 스프링 컨테이너 프레임워크를 사용하고 있는데, 컨테이너는 객체를 생성하고 관리하는 역할을 맡는다. 즉, 각종 설정들이 많다. 이것이 부담이 되기 때문에 `@`, Annotatiaon을 사용해서 코드위에 설정을 보여므로 코드를 보는 사람이 바로 설정을 파악할 수 있게 된다. 

## @Component

1. 먼저, xml에서 Annotation을 쓰고 있다는 것을 알려주기 위해 먼저 Context namespace를 추가해야 한다. 
2. <bean> 을 @Component 로 바꾸어야 하는데, 이를 설정 범위를 정해준다고 표현한다. 
```xml
<context:component-scan base-package="test"/>
```
이렇게 하면 스프링 컨테이너가 패키지 범위를 스캔하여 `@Component` 가 있는 파일들을 객체로 만들어준다. 

3. 객체를 만들 파일에 @Component를 작성해준다.
```java
@Component("iPhone")
```
이렇게 만들면 아래 코드와 같은 의미이다. 
```java
IPhone iPhone = new IPhone();
``` 
즉, 기본생성자를 사용하기 때문에 클래스에 기본 생성자를 정의해주면 좋다. 

## @Autowired & @Resource

메서드(생성자), 혹은 멤버변수 바로 위에 작성한다. 주로 멤버변수 위에 더 많이 사용되며 타입을 체크한다. 만약 타입이 겹치면 Autowired는 정상적으로 작동하지 않는다. 

```java
public class IPhone implements Phone{
	
	@Autowired
	private Watch watch;
    ...
}
```

```java
@Component
public class AppleWatch implements Watch{
    ...
}
```
이렇게 만들면 컨테이너가 만든 객체중에서 Watch에 해당하는 객체가 있으면 자동으로 그 객체를 주입해준다. 예를 들어 AppleWatch를 Component로 객체를 만들면 Client에서 정상적으로 실행된다. 그러나 만약 Watch 타입의 객체가 컨테이너에게 존재하지 않으면 `NoSuchBean...` 에러가 발생한다. 혹은, 만약 Watch 타입 객체가 유일하지 않을 경우 `NoUniqueBean...` 에러가 발생한다. 

이렇게 중복된 객체가 생기면 그들을 구분하기 위해 `@Resource` 를 사용한다. 

```java
@Component("applewatch")
public class AppleWatch implements Watch{
    ...
}
```

```java
@Component()
public class SmartWatch implements Watch{
    ...
}
```

```java
public class IPhone implements Phone{
	
	//@Autowired
	@Resource(name="applewatch")
	private Watch watch;
    ...
}
```

이렇게 하면 오류 없이 정상적으로 실행된다. 