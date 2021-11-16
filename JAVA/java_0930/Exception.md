# 예외 처리 

예외처리에는 오류처리와 에러처리가 있다.

프로그램을 실행 중 어떤 원인에 의해서 오작동을 하거나 비정상적으로 종료되는 경우가 발생한다. 이것을 오류, 그리고 예외라고 한다. 발생 시점에 따라 원인이 다르다. 

### 에러

- 컴파일 에러
프로그램이 시작조차 되지 않은 에러
- 런타임 에러
프로그램이 시작되고 사용하닥가 실행도중에 발생하는 에러 
- 논리적 에러 
실행은 되지만, 의도와는 다른 결과를 도출하는 에러 

# try - catch 
```java
try {
    // 예외가 발생할 가능성이 있는 구문
} catch {
    // 예외 발생 시 처리
    // catch 문은 계속 사용가능
} finally {
    // 예외 발생 여부와 관계없이 항상 실행 
}
``` 

### 대표적인 런타임에러 상황

```java
1) 0으로 나누는 경우

        int c = 4 / 0;
        System.out.println(c);

        --> Exception in thread "main" java.lang.ArithmeticException: / by zero

2) 배열범위 초과한곳을 참조할 경우

        int[] arr = {1,2,3};
        System.out.println(arr[3]);

        --> Exception in thread "main" java.lang.ArrayIndexOutOfBoundsException: Index 3 out of bounds for length 3
```
이러한 코드들을 try-catch문으로 잡아주자
```java
1) 0으로 나누는 경우 

    int c;
    try { //오류가 날 가능성이 있는 코드를 try문 안에 넣어준다. 
        c = 4 / 0;
    } catch (ArithmeticException e){ //에러 역시 객체임
        c = -1;  //예외발생시 실행하는 코드
    } finally {
        System.out.println(c);
    }

    // -1 
``` 

### finally에는 무슨 일이 있어도 반드시 실행하기 위한 함수를 넣는다. 예를 들어 다음과 같이 응용 가능하다.

```java
public static class shouldBeRun {
    public void should(){
        System.out.println("RUN!!");
    }
}

public static void main(String[] args) {
    int c; 
    shouldBeRun run = new shouldBeRun();
    try {  
        c = 4 / 0;
    } catch (ArithmeticException e){ 
        c = -1;  
    } finally {
        System.out.println(c);
        run.should();
    }
}

// -1
// RUN!!
```

# Throw
이제 직접 예외를 던지는 throw를 알아보자. 지금까지는 불가항력적으로 발생할 수도 있는 예외에 대해서 처리하는 방법을 배웠다면, 이제는 내가 원하는 상황에 예외를 던져서 능동적으로 코드를 처리할 수 있게 된다.  

```java
public class Exception_throw extends RuntimeException{

    public static void main(String[] args) {

        Exception_throw example = new Exception_throw();
            example.say("fool"); 
            example.say("genious");         
       }
    } // main end

    public void say(String nick) {
    try {
        if ("fool".equals(nick)){
            throw new Exception_throw();
            ## 예외를 강제로 발생시킴 
        }
        System.out.println("당신의 별명은 "+nick+"입니다");
    } catch (Exception_throw e) {
        ## 위에서 발생시킨 예외를 받고 처리함 
        System.out.println("에러가 발생했습니다. ");
    }
}

결과: 
에러가 발생했습니다. 
당신의 별명은 genious입니다
```

# Throws
throw와 throws는 같아 보이지만 다르다. throw는 적극적으로 예외를 발생시키는 것이고, throws는 예외를 던진다. 똑같아 보이지만 코드를 보면 차이점을 알 수 있다. 

1. throw 
위 예제에서 say()메서드는 위와 같이 예외를 강제로 발생시키고, 예외처리도 say()에서 처리했다. 하나의 메서드에 너무 많은 기능을 부여하였기 때문에, 나중에 코드를 관리해야 할 일이 생겼을 때 수정하기도 어렵고 가독성이 떨어지게 된다. 또한 메인의 say()를 오류가 나던 말던 실행하기 때문에, 프로그램에 오류가 생겼는데 그냥 실행해버리면 문제가 생길 가능성이 크다.  

2. throws 
그러나 throws를 이용하면, say()메서드가 하던 일을 main함수에서 하게 만들 수 있다. 
```java
Exception_throw example = new Exception_throw();

  try {
    example.say("fool"); 
    1) say메서드 호출.
    --
    3) 예외가 발생함.. 즉 아래 say()는 실행하지 않음 
    example.say("genious");
  } catch(Exception_throw e) {
    4) catch문으로 이동해서 sysout메서드 호출 
    System.out.println("에러가 발생했습니다. ");
  } 5) main end

public void say(String nick) throws Exception_throw {

    if("fool".equals(nick)) {
       throw new Exception_throw();
       2) say메서드에서 예외를 던짐 
    }
    System.out.println("당신의 별명은 "+nick+"입니다. ");
}

결과: 
에러가 발생했습니다. 
```
결과값이 달라진 것을 확인할 수 있다. 왜 그럴까? try문 특성상, 코드를 순서대로 진행하다가 **예외가 발생하는 순간, try문을 즉시 탈출하고 catch문을 읽기 시작한다.** 이렇게 코드를 구현하게 되면, try 코드에 문제가 생기는 순간을 즉시 알 수 있으므로 유지보수에 효과적이며 프로그램의 문제를 파악하기 좋아진다. 

# Transaction
프로그래밍 시 예외처리를 하는 위치는 대단히 중요하다. 프로그램의 수행 여부를 결정하기도 하고 또 트랜잭션 처리와도 밀접한 관계가 있다. 

트랜잭션은 하나의 작업단위를 의미하는데, 예를 들면 '상품발송' 트랜잭션 에는 아래의 작업들이 있다. 

```java
포장 -> 영수증 발행 -> 택배 발송
``` 

상품 발송 이라는 하나의 트랜잭션은 3가지 작업 중 하나라도 실패를 할 경우 발송이 이루어져서는 안된다. 그렇다면 이러한 트랜잭션을 실행시키는 코드를 어떻게 구현해야 할 지 고민해보자. 

1. 문제가 생길 수 있는 코드
```java
    상품발송() {
      포장();
      영수증발행();
      택배발송();
    }

    포장() {...} 
    영수증발행() {...}
    택배발송() {...}

    -> 포장에는 실패했을지라도, 영수증발행이나 택배발송이 되어버리는 상황이 발생할 수도 있음. 
```
2. 문제가 발생 시 즉시 예외를 던지는 코드 
```java
    상품발송() {
      try{
        포장();
        영수증발행();
        택배발송();
      }catch(예외){
        모두 취소();
      }
    }

    포장() throws 예외{} 
    영수증발행() throws 예외 {}
    택배발송() throws 예외 {}

    -> 포장에서 문제가 생기면 즉시 모두 취소하는 메서드가 실행됨. 마찬가지로 try문 내부에 있는 어떤 메서드이든 하나라도 실패하는 순간 모두취소되므로 문제가 생기지 않음.
```
보통 프로그래머 실력 평가할 때 예외처리를 어떻게 하는지로 그 사람의 실력을 가늠해 볼 수 있다. 예외처리는 부분적으로 알아서는 안되고 코드 전체를 관통하는 시야를 갖고 있어야만 정확히 할 수 있기 때문이다. 
