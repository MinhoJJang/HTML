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
    // �ٵ� ��ǻ� �ֵ� ���� ���ö����� �����Լ��� �ٲ�������Ѵ�. �翬�� �̰͵� �������̽��� �ѱ�� �� 

    public void barkAnimal(bark a1){
        a1.barkable();
    }

    public static void main(String[] args) {
        tiger t1 = new tiger();
        lion l1 = new lion();

        bouncerMain b1 = new bouncerMain();
        b1.barkAnimal(t1);
        b1.barkAnimal(l1);
        
        // barkAnimal �ż���� �Է����� ���� animal ��ü�� tiger Ŭ������ �ν��Ͻ��� ��� 'I'm tiger' ���
        // lionŬ������ ~

        // t1, l1 ��ü���� tiger, lion�� ��ü�̱⵵ ������, ���������� Ŭ���� animal, �������̽� predator�� bark�� ��ü�̴�. ���� barkAnimal�� �Է� �ڷ����� animal���� bark�� �ٲپ� ��� �����ϴ�. 

        // �̷���, �ϳ��� ��ü�� �������� �ڷ��� Ÿ���� ���� �� �ִ� ���� �������̶�� �Ѵ�. 

        // tiger t1 = new tiger();
        // animal a1 = new tiger();
        // predator p1 = new tiger();
        // bark b1 = new tiger(); 

        // ��, ������ ���̵���, �׻� new�� �����ϴ� ���� �� ������ Ŀ���Ѵ�. 
    }
}
