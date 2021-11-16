# Map 

- �̸� = ȫ�浿, ���� = 19, ���� = �� - ó�� Ű�� ���� �ش��ϴ� ���� ���� �������踦 ���� ǥ���� �� �ִ�. 

key      |  value

people   |  ���
baseball |  �߱�

**map�� ������ ���� key�� ���� value�� ��´�. ���� ���� ū Ư¡��, �����͸� key�θ� value�� ��´ٴ� ���̴�. ��, �����͸� ã�� �� ���������� ã�� �� �ƴ϶� Ű������ �ٷ� �����ϹǷ� �˻��� ������ �����̴�.**

### Methods
```java
1. put -> ������ ����
map.put("people", "���");
map.put("baseball", "�߱�");

2. get -> ������ ����
System.out.println(map.get("people"));
System.out.println(map.get("baseball"));

3. containKey -> �ʿ� � Ű�� �ִ��� �����Ѵ�. 
�� Ű�� �����ϸ� True, �������� ������ False
System.out.println(map.containKey("people")); // True
System.out.println(map.get("FalseExample")); // False

4. remove -> �ʿ� �׸��� �����ϴ� �޼���. �ش� key���� �ش��ϴ� key���� value�� ��� ������
System.out.println(map.remove("people"));

5. size -> map�� ������ �˾Ƴ���. 
System.out.println(map.size());
```

##### �⺻����
```java
public class mapExam {
    public static void main(String[] args) {
        
        HashMap<String, String>map = new HashMap<String, String>();

        map.put("people", "���");
        map.put("baseball", "�߱�");

        System.out.println(map.size());   
        System.out.println(map.get("people"));   
        System.out.println(map.containsKey("people"));   
        System.out.println(map.remove("people"));   
        System.out.println(map.size());   

    }   
}

���:
2
���
true
���
1
```