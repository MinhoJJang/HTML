package java_0930;

public class Exception_throw extends RuntimeException{

    public static void main(String[] args) {
        
        // 1) 0으로 나누는 경우

        // int c = 4 / 0;
        // System.out.println(c);

        // Exception in thread "main" java.lang.ArithmeticException: / by zero

        // 2) 배열범위 초과한곳을 참조할 경우

        // int[] arr = {1,2,3};
        // System.out.println(arr[3]);

        // Exception in thread "main" java.lang.ArrayIndexOutOfBoundsException: Index 3 out of bounds for length 3

    //     int c;
    //     shouldBeRun run = new shouldBeRun();
    //     try { //오류가 날 가능성이 있는 코드를 try문 안에 넣어준다. 
    //         c = 4 / 0;
    //         System.out.println(c);
    //     } catch (ArithmeticException e){ //에러 역시 객체임
    //         c = -1;  //예외발생시 실행하는 코드
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
    //         //--> 조건에 맞을 경우, 직접 에러를 발생시킴. 
    //     }
    //     System.out.println("당신의 별명은 "+nick+"입니다");
    // } catch (Exception_throw e) {
    //     System.out.println("에러가 발생했습니다. ");
    // }
    
    Exception_throw example = new Exception_throw();

  try {
    
    example.say("genious");
    example.say("fool");
  } catch(Exception_throw e) {
    System.out.println("에러가 발생했습니다. ");
  }
    }
  public void say(String nick) throws Exception_throw {

      if("fool".equals(nick)) {
        throw new Exception_throw();
      }
      System.out.println("당신의 별명은 "+nick+"입니다. ");
  }


    // Exception_throw example = new Exception_throw();

    // try {
    //     example.say("fool"); 
    //     example.say("genious");
    // } catch(Exception e) {
    //     System.out.println("에러가 발생했습니다. ");
    // }

    // } // main end

    // public void say(String nick) throws Exception_throw{
    //     if ("fool".equals(nick)){
    //         throw new Exception_throw();
    //     }
    //     System.out.println("당신의 별명은 "+nick+"입니다");
    // }
    

        

    }