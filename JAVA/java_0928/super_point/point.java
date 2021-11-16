package java_0928.super_point;

public class point {
    int x,y;

    // default 생성자 추가
    point(){

    }

    point(int x, int y){
        this.x = x;
        this.y = y;
    }

    String getLocation(){
        return "x:"+x+"y:"+y;
    }
}
