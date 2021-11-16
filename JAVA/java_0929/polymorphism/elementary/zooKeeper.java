package java_0929.polymorphism.elementary;

public class zooKeeper {

    public void feed(tiger t1)  {
        System.out.println("feed apple");
    }

    public void feed(lion l1)  {
        System.out.println("feed banana");
    }

    // 하지만 이곳에 동물이 들어올 때마다 새로 함수를 만들어주어야 한다.. 코드 수정이 매우매우 불편해짐 

    // --> interface의 필요성
    // Interface 폴더로 가보자! 

    public static void main(String[] args) {
        zooKeeper k1 = new zooKeeper();
        tiger t1 = new tiger();
        lion l1 = new lion();
    
        k1.feed(t1);
        k1.feed(l1);
    }
}
