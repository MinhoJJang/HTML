# 접근제어자

- 접근제어자 : 외부에서 접근하지 못하도록 제한하는 역할을 한다. 

- 접근제어자는 클래스. 멤버변수, 메서드, 생성자 앞에 붙는다.

private - 같은 클래스 내부
(default) - 같은 패키지 내
protected - 같은 패키지 내에서, 다른 패키지의 자손 클래스에 접근 가능
public 접근제한없음

```java
public > protected > default > private 
```

클래스는 public, default 
메서드, 멤버변수 - public protected default private
지역변수 - 없음 

**클래스나 멤버, 주로 멤버에 접근 제어자를 사용하는 이유는 클래스 내부의 선언된 데이터를 보호하기 위해서이다. 데이터가 유효한 값을 유지하게 하거나, 비밀번호와 같은 중요한 데이터를 외부에서 함부로 변경하지 못하게 외부로부터의 접근을 제한하는 것이 필요하다.**

# 캡슐화

**외부에서 접근할 필요가 없는 멤버들은 private로 지정하여 외부에 노출을 막음으로 복잡성을 줄일 수 있다. (캡슐화)**

```java
public class Time { // public 

    public int hour;
    public int minute;
    public int second;
}
```

이렇게 보두 public 접근제한자가 설정되어 있으면

```java
    Time t = new Time();
    t.hour = 25; -> 이렇게 바꿔버릴 수 있음... 
```

따라서 private 접근제어자를 이용해서 멤버변수를 제한하고, 그 멤버변수를 읽고 변경할 수 있는 getter, setter을 public으로 설정하여 간접적으로 바꿀 수 있도록 해야 한다. 
