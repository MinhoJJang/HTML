package java_0929.polymorphism.elementary;

public class zooKeeper {

    public void feed(tiger t1)  {
        System.out.println("feed apple");
    }

    public void feed(lion l1)  {
        System.out.println("feed banana");
    }

    // ������ �̰��� ������ ���� ������ ���� �Լ��� ������־�� �Ѵ�.. �ڵ� ������ �ſ�ſ� �������� 

    // --> interface�� �ʿ伺
    // Interface ������ ������! 

    public static void main(String[] args) {
        zooKeeper k1 = new zooKeeper();
        tiger t1 = new tiger();
        lion l1 = new lion();
    
        k1.feed(t1);
        k1.feed(l1);
    }
}
