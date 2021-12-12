package model.member;

public class MemberVO {
	
	/*mid varchar(20) primary key,
	mpw varchar(20) not null,
	mname varchar(15) not null*/
	
	private String mid;
	private String mpw;
	private String mname;
	
	public String getMid() {
		return mid;
	}
	public void setMid(String mid) {
		this.mid = mid;
	}
	public String getMpw() {
		return mpw;
	}
	public void setMpw(String mpw) {
		this.mpw = mpw;
	}
	public String getMname() {
		return mname;
	}
	public void setMname(String mname) {
		this.mname = mname;
	}
	
}
