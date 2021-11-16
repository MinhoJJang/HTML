package java_0928.super_child_parent;

public class superExam {
    // super -> 자식클래스에서 부모를 가리키는데 사용되는 변수임. 멤버변수와 지역변수의 이름이 같을 때 this. 로 구별했듯, super을 통해 상속 유무를 구별한다. 
    public static void main(String[] args){

         child ch = new child();
         ch.method();
    }

 
    
}
