package java_1005.set;

import java.util.HashSet;
import java.util.Set;

public class SetExam {
    public static void main(String[] args) {
        Object[] objArr = {"1", "2", "2", "3", "3", "4", "4", "4"}; // 중복된 값을 넣었음
        Set<Object> set = new HashSet<Object>();

        for(int i = 0; i<objArr.length; i++){
            set.add(objArr[i]);
        }
        System.out.println(set);
    }
    // 중복된 값을 알아서 제거해줌. 
}
