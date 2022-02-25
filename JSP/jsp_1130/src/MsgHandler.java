package src;

import java.io.IOException;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class MsgHandler extends SimpleTagSupport {

	public void doTag() throws IOException {
		JspWriter out=getJspContext().getOut();
		out.println("태그 핸들러클래스기반 출력결과!");
	}
	
}
