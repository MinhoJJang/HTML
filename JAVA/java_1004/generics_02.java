package java_1004;

import java.util.ArrayList;

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
//    FruitBox2<Grape2> grapeBox3 = new FruitBox2<Apple2>(); //Ÿ�� ����ġ
//    FruitBox(Toy2) toyBox2 = new FruitBox2<Toy2>();  //����, ��� �ƴ�
      
      fruitBox2.add(new Fruit2());
      fruitBox2.add(new Apple2());
      fruitBox2.add(new Grape2());   
      appleBox2.add(new Apple2());
//    appleBox2.add(new Grape2());  //grape2 �� apple2�� �ڽ��� �ƴ�
      grapeBox2.add(new Grape2());
      
      System.out.println(fruitBox2);
      System.out.println(appleBox2);
      System.out.println(grapeBox2);

      
   }
/* Ÿ�� ���ڷ� ����� Ÿ���� ����ϸ�  �� ������ Ÿ�Ը� ���� �Ҽ� �ֵ��� ���� �� �� ������ 
 * �׷��� ������ ��� ������ Ÿ���� ������ �� �ִٴ� ���� ������ ����. �׷��� ������ �� �ִ� Ÿ����
 * ���� �� ���� ������!!!
 * 
 * FruitBox<Toy> fruitBox = new FruitBox<Toy>;
 * fruitBox.add(new Toy());
 * 
 * ���׸��� Ÿ�Կ��� extends �� ����ϸ� Ư�� Ÿ���� ��ü�鸸 ������ �� �ְ� ���� �� �� �ִ�. 
 * 
 * class FruitBox<T extends Fruit> { //Fruit�� �ڽĸ� Ÿ������ �����Ѵ�. 
 * 
 * }
 * 
 * FruitBox<Apple> appleBox = new FruitBox<Apple>();
 * FruitBox<Grape> grapeBox = new FruitBox<Grape>();
 * FruitBox<Toy> toyBox = new FruitBox<Toy>;  //���� , toy�� �ڽ��� �ƴϴ�. 
 * 
 */
   
   
   
}