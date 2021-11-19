package jsp_1115;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TestServlet
 */
@WebServlet("/apple") // 어노테이션 == 애너테이션
// .xml 파일에 저장함
// .xml설정 --->> @ 처리
public class TestServlet extends HttpServlet {
   private static final long serialVersionUID = 1L;
   // 객체직렬화 코드
   //  : 객체<->파일코드
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TestServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

   /**
    * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
    */
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      
      response.setContentType("text/html; charset=UTF-8");
      
      PrintWriter out=response.getWriter();
      out.println("<HTML>");
      out.println("<BODY>");
      out.println("<h1>안녕!</h1>");
      out.println("</BODY>");
      out.println("</HTML>");              
   }

   /**
    * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
    */
   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      // TODO Auto-generated method stub
      doGet(request, response);
   }

}