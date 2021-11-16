# Generics의 필요성

제너릭스를 사용하지 않고 단순한 ArrayList를 만들어보자...

```java
import java.util.ArrayList;

public class genericsExam {
    public static void main(String[] args) {
        ArrayList aList = new ArrayList();
        aList.add("web");
        aList.add("dev");
        aList.add(101);

        String web = aList.get(0); <오류발생>
        String dev = (String)aList.get(1);
        int num = (int)aList.get(2);

        System.out.println(web);
        System.out.println(dev);
        System.out.println(num);
    }
}
```
      데이터를 꺼낼 때, 데이터타입을 반드시 명시해주어야 하는 번거로움이 있으며, 데이터 수가 많아지면 여러가지 타입의 데이터가 뒤섞여 있기 때문에 데이터분석에 불리하다    

따라서 아래와 같이 Generics를 만들어 준다.
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
이렇게 제너릭스를 만들면 다음과 같은 이점이 있다. 

1. 제너릭스는 객체에 담을 수 있는 자료형을 제한하는 기능을 갖고 있다. 
2. 제너릭스를 이용하면 명확하게 타입 체크가 가능해진다. 
3. 제너릭스를 사용하면 그 뒤로 자료를 사용할 때 따로 형변환을 해줄 필요가 없다. 
4. 불필요한 코딩 & 잘못된 자료형 사용을 피할 수 있다.

# Generics in Class
이러한 제너릭스의 특징을 응용해 클래스에도 제너릭스를 사용해 보자. 

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
<T> 타입변수 'T'의 첫글자에서 따온것이며 다른 것으로 사용해도 된다. T 자체가 어떤 타입이라는 의미가 아니라, 내가 실제로 객체를 생성할 때, T 위치에 String, Integer 등등의 실제 타입을 넣어야 한다. 

### Apply 01
실제로 위 제너릭스를 구현한 클래스를 사용해 간단하게 객체를 생성해보자

```java
Box<String> b = new Box<String>(); // 실제 객체 생성시에는 실제 타입을 지정한다. 
b.setItem(new Object());  // 에러발생!!!  String  이외는 지정 불가..
b.setItem("abc");         // 문제 없음 
String item = b.getItem(); // 형변환이 필요없다. 

Box<String> b = new Box<String>(); 
// 객체 생성시에 String을 지정했기 때문에, Box 클래스는 아래와 같은 의미가 된다. 

class Box {
    String item;

void setItem(String item){
    this.item = item;
}

String getItem() {
    return item;
}

제네릭스가 도입되기 이전의 코드와 호환을 위해서 제네릭스 클라스 임에도 예전의 방식으로
객체를 생성하는 것은 허용된다. 하지만 경고는 발생된다. 

제네릭스의 용어
제네릭의 용어들은 헷갈리기 쉽다. 

class Box<T> {}

Box<t> 제네릭스 클래스. 't의 박스' 또는 't박스' 로 읽는다.
t 타입변수 타입 매개변수  (t와 다른 알파벳을 쓸 수는 있지만 일반적으로 t로 사용하다.);

Box<Apple> applebox = new Box<Apple>(); // Apple 객체만 사용 가능
Box<Grape> grapebox = new Box<Grape>(); // Grape 객체만 사용 가능
```

### Apply 02
제너릭스 클래스를 사용할 때 주의점..
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
        --> Fruit가 Apple의 부모클래스이므로, Fruit의 모든 필드와 메서드를 공유하고 있는 자식클래스인 Apple 객체를 Fruit 대신 사용할 수 있다. 
        --> 그러나 그 반대는 불가능하다. 
		
3)		appleBox.add(new Apple());
//		appleBox.add(new Toy());
        --> Error occured. Apple만 담을 수 있다. 
	
		toyBox.add(new Toy());
//		toyBox.add(new Apple());
        --> Error occured. Toy만 담을 수 있다.  
```

# Type Limit...

타입 문자로 사용할 타입을 명시하면 한 종류의 타입만 저장 할수 있도록 제한 할 수 있지만, 그래도 여전히 T에 String, Integer, Long 등등 모든 종류의 타입을 지정할 수 있다는 것은 변함이 없다. 
```java
FruitBox<Toy> fruitBox = new FruitBox<Toy>;
fruitBox.add(new Toy());
````
따라서 <T>에 들어갈 수 있는 타입 자체를 제한해보자. 
--> 제네릭스 타입에서 extends 을 사용하면 특정 타입의 객체들만 대입할 수 있게 제한 할 수 있다. 

```java
class FruitBox<T extends Fruit> { //Fruit의 자식만 타입으로 인정한다. 
}

FruitBox<Apple> appleBox = new FruitBox<Apple>();
FruitBox<Grape> grapeBox = new FruitBox<Grape>();
// FruitBox<Toy> toyBox = new FruitBox<Toy>;  
Error occured. Toy is not an extension of Fruit
```

이런 식으로 직접 예시를 만들어보자.
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
   --> Fruit2와 Eatable2을 상속받은 클래스만 FruitBox2<T>의 'T' 자리에 올 수 있다. 
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