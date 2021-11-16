package java_0929.polymorphism.elementary;

public class housedog extends dog{

    // method overriding
    // same method from extender but different run code
    @Override
    public void sleep() { 
        System.out.println(this.name+" zzz in house");
    }

    // method overloading
    // -> same name method, different parameter in a class
    public void sleep(int hour) {
        System.out.println(this.name+" zzz in house for"+hour+"hour(s)");
    }

    public static void main(String[] args) {
        housedog housedog1 = new housedog();
        housedog1.setName("happy"); 
        // housedog -> dog -> animal¿« setName
        housedog1.sleep(3);
    }
}
