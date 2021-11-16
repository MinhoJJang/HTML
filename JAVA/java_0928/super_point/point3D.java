package java_0928.super_point;

public class point3D extends point{
    int z;

    point3D(int x, int y, int z){

        super(x,y);
        // 만약 이 super을 호출하지 않는다면, 에러가 발생한다. 
        // point3D 클래스의 생성자의 첫줄이 생성자를 호출하는 문장이 아니므로 자동으로 super()을 넣어주는데, 문제는 상속받은 point클래스에서 생성자가 파라미터 값이 있기 때문에 내가 직접 명시적으로 부모클래스 생성자를 호출하지 않으면, 오류가 발생한다. 
        // 이 에러를 수정하려면 point클래스에 디폴트 생성자를 추가해주거나, point3D 생성자의 첫줄에서 point(int x, int y)를 호출하도록 변경해준다. 

        this.x = x;
        this.y = y;
        this.z = z;
    }

    String getLocation() {
        return "x:"+x+"y:"+y+"z:"+z;
    }
} 
