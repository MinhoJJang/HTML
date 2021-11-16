package java_0929.Interface;

public class tiger extends animal implements predator, bark{
    public String getFood(){
        return "apple";
    }

    public void barkable(){
        System.out.println("barkbarkbark");
    }
}