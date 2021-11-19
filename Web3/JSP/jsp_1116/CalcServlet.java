package jsp_1116;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CalcServlet
 */
@WebServlet("/CalcServlet")
public class CalcServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CalcServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		  int num1,num2,res;
	      String op;
	      
	      // request.getParameter 은 반환값이 String 이기 때문에, int타입으로 형변환 해주어야 한다. 
	      num1 = Integer.parseInt(request.getParameter("num1"));
	      num2 = Integer.parseInt(request.getParameter("num2"));
	      op = request.getParameter("op");
	      res = calc(num1,num2,op);
	      
	      response.setContentType("text/html; charset=UTF-8");
	      
	      PrintWriter out=response.getWriter();
	      out.println("<HTML>");
	      out.println("<BODY>");
	      out.println("<h1>"+ num1 + op + num2 + "= </h1>");
	      out.println("<h1>계산결과는"+ res + "입니다.</h1>");
	      out.println("</BODY>");
	      out.println("</HTML>");
	}
	
	public int calc(int num1, int num2, String op) {
		
		int result;
		
		if(op.equals("+")) {
			result = num1+num2;
		}
		else {
			result = num1 - num2;
		}
		
		return result;
	}

}
