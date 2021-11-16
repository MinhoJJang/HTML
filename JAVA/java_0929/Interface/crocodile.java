package java_0929.Interface;

public class crocodile extends animal implements predator, bark{
    public String getFood(){
        return "fish";
    }
    public void barkable(){
        System.out.println("crocrocroc");
    }
}
