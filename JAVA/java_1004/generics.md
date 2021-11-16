# Generics�� �ʿ伺

���ʸ����� ������� �ʰ� �ܼ��� ArrayList�� ������...

```java
import java.util.ArrayList;

public class genericsExam {
    public static void main(String[] args) {
        ArrayList aList = new ArrayList();
        aList.add("web");
        aList.add("dev");
        aList.add(101);

        String web = aList.get(0); <�����߻�>
        String dev = (String)aList.get(1);
        int num = (int)aList.get(2);

        System.out.println(web);
        System.out.println(dev);
        System.out.println(num);
    }
}
```
      �����͸� ���� ��, ������Ÿ���� �ݵ�� ������־�� �ϴ� ���ŷο��� ������, ������ ���� �������� �������� Ÿ���� �����Ͱ� �ڼ��� �ֱ� ������ �����ͺм��� �Ҹ��ϴ�    

���� �Ʒ��� ���� Generics�� ����� �ش�.
```java
    ArrayList<String> aList = new ArrayList<String>();
    aList.add("web");
    aList.add("developer");   
    aList.add("123");
    
    String web = aList.get(0);
    String developer = aList.get(1);   
    String num = aList.get(2);   
    System.out.println(web);
    System.out.println(developer);
    System.out.println(num);
```
�̷��� ���ʸ����� ����� ������ ���� ������ �ִ�. 

1. ���ʸ����� ��ü�� ���� �� �ִ� �ڷ����� �����ϴ� ����� ���� �ִ�. 
2. ���ʸ����� �̿��ϸ� ��Ȯ�ϰ� Ÿ�� üũ�� ����������. 
3. ���ʸ����� ����ϸ� �� �ڷ� �ڷḦ ����� �� ���� ����ȯ�� ���� �ʿ䰡 ����. 
4. ���ʿ��� �ڵ� & �߸��� �ڷ��� ����� ���� �� �ִ�.

# Generics in Class
�̷��� ���ʸ����� Ư¡�� ������ Ŭ�������� ���ʸ����� ����� ����. 

```java
1) [without generics]
         
    class Box {
    Object item;
    
    void setItem(Object item) {
        this.item = item;
    }
    
    Object getItem() {
        return item;
    }
    
2) [with generics]

    class Box<T> {
    T item;
    
    void setItem(T item){
        this.item = item;
    }

    T getItem() {
        return item;
    }
```
<T> Ÿ�Ժ��� 'T'�� ù���ڿ��� ���°��̸� �ٸ� ������ ����ص� �ȴ�. T ��ü�� � Ÿ���̶�� �ǹ̰� �ƴ϶�, ���� ������ ��ü�� ������ ��, T ��ġ�� String, Integer ����� ���� Ÿ���� �־�� �Ѵ�. 

### Apply 01
������ �� ���ʸ����� ������ Ŭ������ ����� �����ϰ� ��ü�� �����غ���

```java
Box<String> b = new Box<String>(); // ���� ��ü �����ÿ��� ���� Ÿ���� �����Ѵ�. 
b.setItem(new Object());  // �����߻�!!!  String  �ܴ̿� ���� �Ұ�..
b.setItem("abc");         // ���� ���� 
String item = b.getItem(); // ����ȯ�� �ʿ����. 

Box<String> b = new Box<String>(); 
// ��ü �����ÿ� String�� �����߱� ������, Box Ŭ������ �Ʒ��� ���� �ǹ̰� �ȴ�. 

class Box {
    String item;

void setItem(String item){
    this.item = item;
}

String getItem() {
    return item;
}

���׸����� ���ԵǱ� ������ �ڵ�� ȣȯ�� ���ؼ� ���׸��� Ŭ�� �ӿ��� ������ �������
��ü�� �����ϴ� ���� ���ȴ�. ������ ���� �߻��ȴ�. 

���׸����� ���
���׸��� ������ �򰥸��� ����. 

class Box<T> {}

Box<t> ���׸��� Ŭ����. 't�� �ڽ�' �Ǵ� 't�ڽ�' �� �д´�.
t Ÿ�Ժ��� Ÿ�� �Ű�����  (t�� �ٸ� ���ĺ��� �� ���� ������ �Ϲ������� t�� ����ϴ�.);

Box<Apple> applebox = new Box<Apple>(); // Apple ��ü�� ��� ����
Box<Grape> grapebox = new Box<Grape>(); // Grape ��ü�� ��� ����
```

### Apply 02
���ʸ��� Ŭ������ ����� �� ������..
```java

class Fruit{
	public String toString() {
		return "Fruit";
	}
}

class Apple extends Fruit {
	public String toString() {
		return "Apple";
	}
}

class Grape extends Fruit {
	public String toString() {
		return "Grape";
	}
}

class Toy {
	public String toString() {
		return "Toy";
	}
}

class Box<T> {
	ArrayList<T> list = new ArrayList<T>();
	void add(T item) {
		list.add(item);
	}
	T get(int i) {
		return list.get(i);
	}
	int size() {
		return list.size();
	}
	public String toString() {
		return list.toString();
	}
}

public class genericsExam {

	public static void main(String[] args) {

1)		Box<Fruit> fruitBox = new Box<Fruit>();
		Box<Apple> appleBox = new Box<Apple>();
		Box<Toy> toyBox = new Box<Toy>();
//		Box<Grape> grapeBox = new Box<Apple>(); 
        --> Error occured. type mismatch
		
2)		fruitBox.add(new Fruit());
		fruitBox.add(new Apple());
        --> Fruit�� Apple�� �θ�Ŭ�����̹Ƿ�, Fruit�� ��� �ʵ�� �޼��带 �����ϰ� �ִ� �ڽ�Ŭ������ Apple ��ü�� Fruit ��� ����� �� �ִ�. 
        --> �׷��� �� �ݴ�� �Ұ����ϴ�. 
		
3)		appleBox.add(new Apple());
//		appleBox.add(new Toy());
        --> Error occured. Apple�� ���� �� �ִ�. 
	
		toyBox.add(new Toy());
//		toyBox.add(new Apple());
        --> Error occured. Toy�� ���� �� �ִ�.  
```

# Type Limit...

Ÿ�� ���ڷ� ����� Ÿ���� ����ϸ� �� ������ Ÿ�Ը� ���� �Ҽ� �ֵ��� ���� �� �� ������, �׷��� ������ T�� String, Integer, Long ��� ��� ������ Ÿ���� ������ �� �ִٴ� ���� ������ ����. 
```java
FruitBox<Toy> fruitBox = new FruitBox<Toy>;
fruitBox.add(new Toy());
````
���� <T>�� �� �� �ִ� Ÿ�� ��ü�� �����غ���. 
--> ���׸��� Ÿ�Կ��� extends �� ����ϸ� Ư�� Ÿ���� ��ü�鸸 ������ �� �ְ� ���� �� �� �ִ�. 

```java
class FruitBox<T extends Fruit> { //Fruit�� �ڽĸ� Ÿ������ �����Ѵ�. 
}

FruitBox<Apple> appleBox = new FruitBox<Apple>();
FruitBox<Grape> grapeBox = new FruitBox<Grape>();
// FruitBox<Toy> toyBox = new FruitBox<Toy>;  
Error occured. Toy is not an extension of Fruit
```

�̷� ������ ���� ���ø� ������.
```java
class Fruit2 implements Eatable2{
   public String toString() {
      return "Fruit2";
   }
}

class Apple2 extends Fruit2 {
   public String toString() {
      return "Apple2";
   }
}

class Grape2 extends Fruit2 {
   public String toString() {
      return "Grape2";
   }
}

class Toy2{
   public String toString() {
      return "Toy2";
   }
}

interface Eatable2 {}

class FruitBox2<T extends Fruit2 & Eatable2> extends Box2<T> {
   --> Fruit2�� Eatable2�� ��ӹ��� Ŭ������ FruitBox2<T>�� 'T' �ڸ��� �� �� �ִ�. 
}

class Box2<T> {
   ArrayList<T> list = new ArrayList<T>();
   void add(T item) {
      list.add(item);
   }
   T get(int i) {
      return list.get(i);
   }
   int size() {
      return list.size();
   }
   public String toString() {
      return list.toString();
   }
}

public class generics_02 {

   public static void main(String[] args) {
      Box2<Fruit2> fruitBox2 = new Box2<Fruit2>();
      FruitBox2<Apple2> appleBox2 = new FruitBox2<Apple2>();
      FruitBox2<Grape2> grapeBox2 = new FruitBox2<Grape2>();
//    FruitBox2<Grape2> grapeBox3 = new FruitBox2<Apple2>(); 
      --> Error occured. type mismatch
//    FruitBox<Toy2> toyBox2 = new FruitBox2<Toy2>();  
      --> Error occured. Toy2 is not an extension either Fruit2 and Eatable2
      
      fruitBox2.add(new Fruit2());
      fruitBox2.add(new Apple2());
      fruitBox2.add(new Grape2());   
      appleBox2.add(new Apple2());
//    appleBox2.add(new Grape2());  
      --> Error occured. grape2 is not a child of apple2
      grapeBox2.add(new Grape2());
      
      System.out.println(fruitBox2);
      System.out.println(appleBox2);
      System.out.println(grapeBox2);
   }
```