package java_1005.set;

import java.util.HashSet;
import java.util.Set;

public class lottoExam {
    public static void main(String[] args) {
        Set<Integer> set = new HashSet<Integer>();

        for (int i =0; set.size() < 6; i++) {
            int num = (int)(Math.random()*45) + 1;
            System.out.println(num);
            set.add(num);
            System.out.println(set);
        }
    }
}
