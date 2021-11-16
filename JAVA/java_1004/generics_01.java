package java_1004;

import java.util.*;

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

public class generics_01 {

   public static void main(String[] args) {
   
   /*���׸���
    * '���� �� �ִ� �ڷ����� String/int... Ÿ�� ���̴�' ��� ������ Ư�� Ÿ���� �����Ѵ�. 
    * ���ʸ����� ����Ͽ� �� �� ��Ȯ�ϰ� Ÿ�� üũ�� ����������. 
    * 
    * ���ʸ����� ����ϸ� �� ���ķδ� �ڷ����� ���� ����ȯ�� �ʿ����. �̹� ���ʸ����� ���ؼ�
    * Ư�� �ڷ����� �߰� �Ǿ�� �Ѵٴ� ���� �����߱� �����̴�. 
    * ���ʿ��� �ڵ� �߸��� ����ȯ���� ������ ���� �� �ִ�. 
    */
   
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
   
      /*
       * ���׸��� ����
       * 1. Ÿ�� ������ Ȯ��
       * 2. Ÿ�� üũ�� ����ȯ�� ������ �� �־� �ڵ尡 ������ ����. 
       * 
       * ���׸��� Ŭ���� ����...
       * 
       * [without generics]
       * 
       * class Box {
       *    Object item;
       * 
       *    void setItem(Object item) {
       *       this.item = item;
       *  }
       *  
       *  Object getItem() {
       *  return item;
       *  }
       *  
       *  
       * [with generics]
       * class Box<T> {
       *    T item;
       * 
       *  void setItem(T item){
       *  this.item = item;
       *  }
       *  T getItem() {
       *  return item;
       * }
       * 
       * <T> Ÿ�Ժ��� 'T'�� ù���ڿ��� ���°��̸� �ٸ� ������ ����ص� �ȴ�. 
       * 
       * Box<String> b = new Box<String>(); // ���� ��ü �����ÿ��� ���� Ÿ���� �����Ѵ�. 
       * b.setItem(new Object());  // �����߻�!!!  String  �ܴ̿� ���� �Ұ�..
       * b.setItem("abc");         // ���� ���� String
       * String item = b.getItem(); ����ȯ�� �ʿ����. 
       * 
       * 
       * Box<String> b = new Box<String>(); 
       * ��ü �����ÿ� String�� �����߱� ������ �Ʒ��� ���� �ǹ̰� �ȴ�. 
       * 
       * class Box {
       *    String item;
       * 
       *  void setItem(String item){
       *  this.item = item;
       *  }
       *  
       *  String getItem() {
       *  return item;
       * }
       * 
       * ���׸����� ���ԵǱ� ������ �ڵ�� ȣȯ�� ���ؼ� ���׸��� Ŭ�� �ӿ��� ������ �������
       * ��ü�� �����ϴ� ���� ���ȴ�. ������ ���� �߻��ȴ�. 
       * 
       * ���׸����� ���
       * ���׸��� ������ ��򸮱� ����. 
       * 
       * class Box<T> {}
       * 
       * Box<t> ���׸��� Ŭ����. 't�� �ڽ�' �Ǵ� 't�ڽ�' �� �д´�.
       * t Ÿ�Ժ��� Ÿ�� �Ű�����  (t�� �ٸ� ���ĺ��� �� ���� ������ t�� ����ϴ�. )
       * 
       * Box<Apple> applebox = new Box<Apple>(); // Apple ��ü�� ��� ����
       * Box<Grape> grapebox = new Box<Grape>(); // Grape ��ü�� ��� ����
       * 
       */
      
      
      
      
      
   }
   
}

