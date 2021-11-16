# Thread 

쓰레드란..
    __동작하고 있는 프로그램을 프로세스라고 한다. 보통 한개의 프로세스는 한가지 일을 한다. 쓰레드를 이용하면 한 프로세스 내에서 두가지 또는 그 이상의 일을 동시에 할 수 있다.__

참고로 Thread의 start()메소드는 클래스안의 run()를 호출한다. 
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
이 코드를 돌리면 어떤 결과가 나올까? 
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
심지어 이 결과값조차도 일정하지 않고, 실행할 때마다 결과가 변경된다. 더욱 이상한것은 *메인함수는 쓰레드가 시작하기도 전에 종료된다*는 점이다. 

그렇다면 모든 쓰레드가 종료된 뒤에 main을 종료시키도록 코딩해보자. 생성되는 쓰레드를 담기 위해서 ArrayList를 사용해서 쓰레드 생성 시 리스트에 넣고, 쓰레드가 다 종료될때까지 기다리도록 하자.
```java
public static void main(String[] args) {
    ArrayList<Thread> threads = new ArrayList<Thread>();
    --> 제너릭스를 <Thread> 로 명시해서 list에 쓰레드를 넣거나 꺼낼 때 따로 타입을 명시하지 않아도 되도록 하였음
    for(int i=0; i<10; i++){
        test t3 = new test(i);
        t3.start();
        threads.add(t3);
    }
    
    for(int i=0; i<threads.size(); i++){
        Thread t3 = threads.get(i);
        try {
            t3.join(); --> join: 이 쓰레드가 끝날 때까지 기다리는 함수.
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
이렇게 main이 쓰레드가 전부 종료된 후에 종료된 것을 확인할 수 있다. 