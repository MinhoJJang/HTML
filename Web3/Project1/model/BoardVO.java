package model;

public class BoardVO {
	private int bid;
	private String writer;
	private String title;
	private String content;
	
	public int getBid() {
		return bid;
	}
	public void setBid(int bid) {
		this.bid = bid;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	@Override
	// Object에 있는 메서드이므로 오버라이드 해줌 
	// 데이터가 잘 들어갔는지 중간중간 확인을 위해 만든 toString. 
	public String toString() {
		return "BoardVO [bid=" + bid + ", writer=" + writer + ", title=" + title + ", content=" + content + "]";
	}
	
}
