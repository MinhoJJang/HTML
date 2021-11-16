# Set

Set키워드는 중복된 요소를 저장하지 않는다. 
add, addAll 등 중복된 요소 제거용으로 많이 사용된다.
순서를 유지하지 않는다. 

```java
import java.util.HashSet;
import java.util.Set;

public class SetExam {
    public static void main(String[] args) {
        Object[] objArr = {"1", "2", "2", "3", "3", "4", "4", "4"}; // 중복된 값을 넣었음
        Set set = new HashSet();

        for(int i = 0; i<objArr.length; i++){
            set.add(objArr[i]);
        }
        System.out.println(set);
    }
}

실행결과
[1, 2, 3, 4]
```
## Set를 이용한 프로그램 응용 
이러한 set기능은 대표적으로 로또번호를 만드는데 응용할 수 있다. 

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

결과: 
2
[2]
2
[2] --> 중복된 값은 넣지 않음 
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