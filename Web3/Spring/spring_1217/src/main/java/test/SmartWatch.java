package test;

public class SmartWatch implements Watch{
	public SmartWatch() {
		System.out.println("스마트워치 객체생성");
	}
	public void volumeUp() {
		System.out.println("스마트워치 소리 ++");
	}
	public void volumeDown() {
		System.out.println("스마트워치 소리 --");
	}	
}
