package java_0929.Interface;

public class zooKeeperMain {

    // public void feed(tiger t1)  {
    //     System.out.println("feed apple");
    // }

    // public void feed(lion l1)  {
    //     System.out.println("feed banana");
    // }

    // ������ �̰��� ������ ���� ������ �����Լ��� ���� �Լ��� ������־�� �Ѵ�.. �ڵ� ������ �ſ�ſ� �������� 

    // --> interface�� �ʿ伺
    public void feed(predator p1) {
       System.out.println("feed "+ p1.getFood());
    }
    // tiger, lion ���� predator �������̽��� ��ü�̱⵵ �ϴ�.
    // �̿� ���� ��ü�� �ϳ� �̻��� �ڷ��� Ÿ���� ���� �Ǵ� Ư���� �������̶�� �Ѵ�. 

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
