package Beans;

public class CalcBean {

    private int x;
    private int y;
    private String op;
    private int res;

    public String getOp() {
        return op;
    }
    public void setOp(String op) {
        this.op = op;
    }
    public int getRes() {
        return res;
    }
    public void setRes(int res) {
        this.res = res;
    }
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
    
    public void Calc(){
        if(op.equals("+")){
            res = x + y;
        }
        else {
            res = x - y;
        }
    }

}
