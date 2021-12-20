package test;

public class IPhone implements Phone{
	
	//private AppleWatch watch;
	private Watch watch;
	private String uname;
	
	public Watch getWatch() {
		return watch;
	}

	public void setWatch(Watch watch) {
		this.watch = watch;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	
	public IPhone() {
		System.out.println("아이폰 생성자");
	}
	
	public IPhone(Watch watch) {
		System.out.println("아이폰을 애플워치로 생성!");
		this.watch = watch;
	}
	
	public void volumeUp() {
//		System.out.println("아이폰 소리 ++");
		// 여기서 watch의 객체를 new 로 생성해버리면..? 객체가 낭비됨.
		watch.volumeUp();
	}
	
	public void volumeDown() {
		watch.volumeDown();
	}
	
	public void msg() {
		System.out.println("아이폰!");
	}
	
	/*public void initMethod() {
		System.out.println("아이폰 초기화!");
	}
	public void destroyMethod() {
		System.out.println("아이폰 객체 삭제");
	}*/

}
