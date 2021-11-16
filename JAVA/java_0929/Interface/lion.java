package java_0929.Interface;

public class lion extends animal implements predator, bark{
    public String getFood(){
        return "banana";
    }

    public void barkable(){
        System.out.println("lionlionlion");
    }
}
