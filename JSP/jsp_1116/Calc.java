package jsp_1116;

public class Calc {
	
	int res;
	public int getRes() {
		return res;
	}
	
	// 생성자를 정의
	// 기본생성자 형태가 아닌 형태를 사용하고싶음!
	public Calc(int num1,int num2,String op) {
		if(op.equals("+")) {
			res=num1+num2;
		}
		else {
			res=num1-num2;
		}
	}

}
