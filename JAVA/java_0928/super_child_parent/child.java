package java_0928.super_child_parent;

public class child extends parent{
    
    void method() {
        System.out.println("x=" + x);
        System.out.println("this.x" + this.x);
        System.out.println("super.x" + super.x);
    }
}
