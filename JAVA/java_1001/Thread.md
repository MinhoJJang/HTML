# Thread 

�������..
    __�����ϰ� �ִ� ���α׷��� ���μ������ �Ѵ�. ���� �Ѱ��� ���μ����� �Ѱ��� ���� �Ѵ�. �����带 �̿��ϸ� �� ���μ��� ������ �ΰ��� �Ǵ� �� �̻��� ���� ���ÿ� �� �� �ִ�.__

����� Thread�� start()�޼ҵ�� Ŭ�������� run()�� ȣ���Ѵ�. 
```java
int seq;

    public test(int seq) {
        this.seq = seq;
    }

    public void run() {
        System.out.println(this.seq+"thread start.");
        try {
            Thread.sleep(1000);
        } catch (Exception e) {

        }
        System.out.println(this.seq + " thread end.");
    }

    public static void main(String[] args) {
        for (int i = 1; i <= 10; i++) {
            test t2 = new test(i);
            t2.start();
        }
        
        System.out.println("main end");
    }
```
�� �ڵ带 ������ � ����� ���ñ�? 
```java
1thread start.
10thread start.
9thread start.
8thread start.
7thread start.
6thread start.
main end
2thread start.
4thread start.
5thread start.
3thread start.
10 thread end.
9 thread end.
8 thread end.
1 thread end.
7 thread end.
6 thread end.
4 thread end.
2 thread end.
3 thread end.
5 thread end.
```
������ �� ����������� �������� �ʰ�, ������ ������ ����� ����ȴ�. ���� �̻��Ѱ��� *�����Լ��� �����尡 �����ϱ⵵ ���� ����ȴ�*�� ���̴�. 

�׷��ٸ� ��� �����尡 ����� �ڿ� main�� �����Ű���� �ڵ��غ���. �����Ǵ� �����带 ��� ���ؼ� ArrayList�� ����ؼ� ������ ���� �� ����Ʈ�� �ְ�, �����尡 �� ����ɶ����� ��ٸ����� ����.
```java
public static void main(String[] args) {
    ArrayList<Thread> threads = new ArrayList<Thread>();
    --> ���ʸ����� <Thread> �� ����ؼ� list�� �����带 �ְų� ���� �� ���� Ÿ���� ������� �ʾƵ� �ǵ��� �Ͽ���
    for(int i=0; i<10; i++){
        test t3 = new test(i);
        t3.start();
        threads.add(t3);
    }
    
    for(int i=0; i<threads.size(); i++){
        Thread t3 = threads.get(i);
        try {
            t3.join(); --> join: �� �����尡 ���� ������ ��ٸ��� �Լ�.
        } catch (Exception e) {

        }
    }

    System.out.println("main end");
}
--> result
0thread start.
4thread start.
7thread start.
2thread start.
1thread start.
9thread start.
8thread start.
5thread start.
6thread start.
3thread start.
0 thread end.
3 thread end.
4 thread end.
7 thread end.
2 thread end.
1 thread end.
9 thread end.
8 thread end.
5 thread end.
6 thread end.
main end
```
�̷��� main�� �����尡 ���� ����� �Ŀ� ����� ���� Ȯ���� �� �ִ�. 