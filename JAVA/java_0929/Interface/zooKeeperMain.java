package java_0929.Interface;

public class zooKeeperMain {

    // public void feed(tiger t1)  {
    //     System.out.println("feed apple");
    // }

    // public void feed(lion l1)  {
    //     System.out.println("feed banana");
    // }

    // 하지만 이곳에 동물이 들어올 때마다 메인함수에 새로 함수를 만들어주어야 한다.. 코드 수정이 매우매우 불편해짐 

    // --> interface의 필요성
    public void feed(predator p1) {
       System.out.println("feed "+ p1.getFood());
    }
    // tiger, lion 각각 predator 인터페이스의 객체이기도 하다.
    // 이와 같이 객체가 하나 이상의 자료형 타입을 갖게 되는 특성을 다형성이라고 한다. 

    public static void main(String[] args) {
        zooKeeperMain k1 = new zooKeeperMain();
        tiger t1 = new tiger();
        lion l1 = new lion();
        crocodile c1 = new crocodile();
    
        k1.feed(t1);
        k1.feed(l1);
        k1.feed(c1);
    }
}
