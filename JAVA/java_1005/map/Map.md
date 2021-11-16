# Map 

- 이름 = 홍길동, 나이 = 19, 성별 = 남 - 처럼 키에 따라 해당하는 값에 대한 대응관계를 쉽게 표현할 수 있다. 

key      |  value

people   |  사람
baseball |  야구

**map는 순서가 없이 key를 통해 value를 얻는다. 맵의 가장 큰 특징은, 데이터를 key로만 value를 얻는다는 것이다. 즉, 데이터를 찾을 때 순차적으로 찾는 게 아니라 키값으로 바로 접근하므로 검색에 유리한 구조이다.**

### Methods
```java
1. put -> 데이터 삽입
map.put("people", "사람");
map.put("baseball", "야구");

2. get -> 데이터 추출
System.out.println(map.get("people"));
System.out.println(map.get("baseball"));

3. containKey -> 맵에 어떤 키가 있는지 조사한다. 
그 키가 존재하면 True, 존재하지 않으면 False
System.out.println(map.containKey("people")); // True
System.out.println(map.get("FalseExample")); // False

4. remove -> 맵에 항목을 삭제하는 메서드. 해당 key값에 해당하는 key값과 value도 모두 삭제됨
System.out.println(map.remove("people"));

5. size -> map의 개수를 알아낸다. 
System.out.println(map.size());
```

##### 기본구현
```java
public class mapExam {
    public static void main(String[] args) {
        
        HashMap<String, String>map = new HashMap<String, String>();

        map.put("people", "사람");
        map.put("baseball", "야구");

        System.out.println(map.size());   
        System.out.println(map.get("people"));   
        System.out.println(map.containsKey("people"));   
        System.out.println(map.remove("people"));   
        System.out.println(map.size());   

    }   
}

결과:
2
사람
true
사람
1
```