package java_0929.Interface;

public class bouncerMain {
    
    // public void barkAnimal(animal a1){
    //     if(a1 instanceof tiger){
    //         System.out.println("I'm tiger");
    //     }
    //     else if(a1 instanceof lion){
    //         System.out.println("I'm lion");
    //     }
    //    
    // }
    // 근데 사실상 애도 동물 들어올때마다 메인함수를 바꿔버려야한다. 당연히 이것도 인터페이스로 넘기면 됨 

    public void barkAnimal(bark a1){
        a1.barkable();
    }

    public static void main(String[] args) {
        tiger t1 = new tiger();
        lion l1 = new lion();

        bouncerMain b1 = new bouncerMain();
        b1.barkAnimal(t1);
        b1.barkAnimal(l1);
        
        // barkAnimal 매서드는 입력으로 받은 animal 객체가 tiger 클래스의 인스턴스인 경우 'I'm tiger' 출력
        // lion클래스면 ~

        // t1, l1 객체들은 tiger, lion의 객체이기도 하지만, 공통적으로 클래스 animal, 인터페이스 predator과 bark의 객체이다. 따라서 barkAnimal의 입력 자료형을 animal에서 bark로 바꾸어 사용 가능하다. 

        // 이렇게, 하나의 객체가 여러개의 자료형 타입을 가질 수 있는 것을 다형성이라고 한다. 

        // tiger t1 = new tiger();
        // animal a1 = new tiger();
        // predator p1 = new tiger();
        // bark b1 = new tiger(); 

        // 즉, 위에서 보이듯이, 항상 new로 선언하는 것이 더 범위가 커야한다. 
    }
}
