# Override

부모 클래스로부터 상속받은 매서드의 내용을 변경하는 것이 오버라이딩.

매서드의 기능을 클래스에 따라 다르게 변경해야 할 경우 사용한다. 
```java
class Point {
    int x;
    int y;

    int getNum() {
        return x + y;
    }
}

class Point3D extends Point {
    
    @Override
    int getNum(){
        return x-y;
    }
    // 이렇게 메서드명을 잘못 작성하는 경우를 위해 Override를 사용한다.
}
```
오버라이딩 조건
1. 이름이 같아야 함
2. 매개변수가 같아야 함
3. 반환타입이 같아야 함
*. 접근제어자 등 제한된 조건 하에서만 다르게 변경 가능 

- 오버로딩 -> 기존에 없는 새로운 매서드를 정의 
- 오버라이딩 -> 기존의 매서드의 내용변경 




