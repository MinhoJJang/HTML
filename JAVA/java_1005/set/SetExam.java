package java_1005.set;

import java.util.HashSet;
import java.util.Set;

public class SetExam {
    public static void main(String[] args) {
        Object[] objArr = {"1", "2", "2", "3", "3", "4", "4", "4"}; // �ߺ��� ���� �־���
        Set<Object> set = new HashSet<Object>();

        for(int i = 0; i<objArr.length; i++){
            set.add(objArr[i]);
        }
        System.out.println(set);
    }
    // �ߺ��� ���� �˾Ƽ� ��������. 
}
