package java_0930;

public class Exception_throw extends RuntimeException{

    public static void main(String[] args) {
        
        // 1) 0���� ������ ���

        // int c = 4 / 0;
        // System.out.println(c);

        // Exception in thread "main" java.lang.ArithmeticException: / by zero

        // 2) �迭���� �ʰ��Ѱ��� ������ ���

        // int[] arr = {1,2,3};
        // System.out.println(arr[3]);

        // Exception in thread "main" java.lang.ArrayIndexOutOfBoundsException: Index 3 out of bounds for length 3

    //     int c;
    //     shouldBeRun run = new shouldBeRun();
    //     try { //������ �� ���ɼ��� �ִ� �ڵ带 try�� �ȿ� �־��ش�. 
    //         c = 4 / 0;
    //         System.out.println(c);
    //     } catch (ArithmeticException e){ //���� ���� ��ü��
    //         c = -1;  //���ܹ߻��� �����ϴ� �ڵ�
    //     } finally {
    //         System.out.println(c);
    //         run.should();
    //     }
    // }

    // public static class shouldBeRun {
    //     public void should(){
    //         System.out.println("RUN!!");
    //     }
    // }

    // Exception_throw example = new Exception_throw();
    //     example.say("fool"); 
    //     example.say("genious");         
       
    // } // main end

    // public void say(String nick) {
    // try {
    //     if ("fool".equals(nick)){
    //         throw new Exception_throw();
    //         //--> ���ǿ� ���� ���, ���� ������ �߻���Ŵ. 
    //     }
    //     System.out.println("����� ������ "+nick+"�Դϴ�");
    // } catch (Exception_throw e) {
    //     System.out.println("������ �߻��߽��ϴ�. ");
    // }
    
    Exception_throw example = new Exception_throw();

  try {
    
    example.say("genious");
    example.say("fool");
  } catch(Exception_throw e) {
    System.out.println("������ �߻��߽��ϴ�. ");
  }
    }
  public void say(String nick) throws Exception_throw {

      if("fool".equals(nick)) {
        throw new Exception_throw();
      }
      System.out.println("����� ������ "+nick+"�Դϴ�. ");
  }


    // Exception_throw example = new Exception_throw();

    // try {
    //     example.say("fool"); 
    //     example.say("genious");
    // } catch(Exception e) {
    //     System.out.println("������ �߻��߽��ϴ�. ");
    // }

    // } // main end

    // public void say(String nick) throws Exception_throw{
    //     if ("fool".equals(nick)){
    //         throw new Exception_throw();
    //     }
    //     System.out.println("����� ������ "+nick+"�Դϴ�");
    // }
    

        

    }