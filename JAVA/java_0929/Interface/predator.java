package java_0929.Interface;
        
public interface predator {
    abstract public String getFood(); // body 구현하지 않음. 

    // 인터페이스의 메소드는 이름과 정의만 있다. 내용은 구현하지 않는다. 
    // --> abstract method
    // 이 인터페이스를 실행하는 모든 클래스들은 인터페이스의 동작을 반드시 구현해야 한다. 
    // 즉 인터페이스를 실행한다는 것은, 인터페이스 내의 메서드들을 기본 뼈대로써 클래스를 만든다는 소리이다. 
}