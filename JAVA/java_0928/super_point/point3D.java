package java_0928.super_point;

public class point3D extends point{
    int z;

    point3D(int x, int y, int z){

        super(x,y);
        // ���� �� super�� ȣ������ �ʴ´ٸ�, ������ �߻��Ѵ�. 
        // point3D Ŭ������ �������� ù���� �����ڸ� ȣ���ϴ� ������ �ƴϹǷ� �ڵ����� super()�� �־��ִµ�, ������ ��ӹ��� pointŬ�������� �����ڰ� �Ķ���� ���� �ֱ� ������ ���� ���� ��������� �θ�Ŭ���� �����ڸ� ȣ������ ������, ������ �߻��Ѵ�. 
        // �� ������ �����Ϸ��� pointŬ������ ����Ʈ �����ڸ� �߰����ְų�, point3D �������� ù�ٿ��� point(int x, int y)�� ȣ���ϵ��� �������ش�. 

        this.x = x;
        this.y = y;
        this.z = z;
    }

    String getLocation() {
        return "x:"+x+"y:"+y+"z:"+z;
    }
} 
