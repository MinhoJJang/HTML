public class CalcBean {
    private int x;
    private int y;
    private int res;

    public int getX() {
        return x;
    }
    public void setX(int x) {
        this.x = x;
    }
    public int getY() {
        return y;
    }
    public void setY(int y) {
        this.y = y;
    }
    
    public int getResult(){

        res = getX()+getY();

        return res;
    }

}
