package test;

public class GalaxyPhone implements Phone{
	
	public GalaxyPhone() {
		System.out.println("갤럭시 생성자");
	}
	
	public void volumeUp() {
		System.out.println("갤럭시 소리 ++");
	}
	
	public void volumeDown() {
		System.out.println("갤럭시 소리 --");
	}
	
	public void msg() {
		System.out.println("갤럭시!");
	}
}
