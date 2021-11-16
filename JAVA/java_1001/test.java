package java_1001;

import java.util.ArrayList;

public class test extends Thread {

// 1. 기본적인 start - run
    // public void run() {
    // System.out.println("thread run.");
    // }

    // public static void main(String[] args) {
    // test t1 = new test();
    // t1.start();
    // }

// 2. 쓰레드 이용해서 호출하기 
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

    // public static void main(String[] args) {
    //     for (int i = 1; i <= 10; i++) {
    //         test t2 = new test(i);
    //         t2.start();
    //     }
    //     System.out.println("main end");
    // }
// 이렇게만들면... 이상하게 끝남

    public static void main(String[] args) {
        ArrayList<Thread> threads = new ArrayList<Thread>();
        for(int i=0; i<10; i++){
            Thread t3 = new test(i);
            t3.start();
            threads.add(t3);
        }
        
        for(int i=0; i<threads.size(); i++){
            Thread t3 = threads.get(i);
            try {
                t3.join();
            } catch (Exception e) {

            }
        }

        System.out.println("main end");
    }


}
