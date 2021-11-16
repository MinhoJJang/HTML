# Set

SetŰ����� �ߺ��� ��Ҹ� �������� �ʴ´�. 
add, addAll �� �ߺ��� ��� ���ſ����� ���� ���ȴ�.
������ �������� �ʴ´�. 

```java
import java.util.HashSet;
import java.util.Set;

public class SetExam {
    public static void main(String[] args) {
        Object[] objArr = {"1", "2", "2", "3", "3", "4", "4", "4"}; // �ߺ��� ���� �־���
        Set set = new HashSet();

        for(int i = 0; i<objArr.length; i++){
            set.add(objArr[i]);
        }
        System.out.println(set);
    }
}

������
[1, 2, 3, 4]
```
## Set�� �̿��� ���α׷� ���� 
�̷��� set����� ��ǥ������ �ζǹ�ȣ�� ����µ� ������ �� �ִ�. 

```java
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

���: 
2
[2]
2
[2] --> �ߺ��� ���� ���� ���� 
7
[2, 7]
34
[2, 34, 7]
34
[2, 34, 7]
24
[2, 34, 7, 24]
26
[2, 34, 7, 24, 26]
40
[2, 34, 7, 24, 40, 26]
```