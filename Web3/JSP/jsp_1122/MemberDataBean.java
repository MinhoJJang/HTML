package jsp_1122;

import java.util.ArrayList;

public class MemberDataBean {
	ArrayList<MemberBean> datas=new ArrayList<MemberBean>();

	public ArrayList<MemberBean> getDatas() {
		return datas;
	}

	public void add(MemberBean mb) {
		datas.add(mb);
	}
	public MemberBean search(String sname) {
		// 로깅
		// console.log 찍듯이... 
		System.out.println("sname: "+sname);
		for(MemberBean v:datas) {
			if(v.getUname().equals(sname)) {
				return v;
			}
		}
		return null;
	}
}
