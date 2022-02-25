package jsp_1124;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Test {
	
	public static void main(String[] args) {
	
	String driver="com.mysql.cj.jdbc.Driver";
	String url="jdbc:mysql://localhost:3306/mhj";
	String user="mhj";
	String password="0000";
	
	String sql="select uname,content from test";
	// 원래는 select * 이렇게 쓰지만 prepareStatement의 장점을 알기 위해 uname, content을 직접 써주자
	
	Connection conn = null;
	PreparedStatement pstmt=null;
	
	try {
		Class.forName(driver);
		
		conn = DriverManager.getConnection(url, user, password);
		pstmt = conn.prepareStatement(sql);
		// 이렇게 들어온 sql문을 실행시키려면, 아래와 같이 ResultSet타입으로 리턴
		ResultSet rs = pstmt.executeQuery();
		
		rs.close();
	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		try {
			// 중요 오류 
			// close를 하기 전에 pstmt나 conn이 아예 생기지도 않은 상태로 catch이 될 수도 있음.. 예를 들어 conn을 만드는 와중에 문제가 발생해 버리면, pstmt에 값을 할당하는 부분이 실행되지도 않은 상태에서 catch쪽으로 가버릴 테니까 pstmt.close(); 이 부분이 불가능해짐. 
			if(pstmt!=null) { 
				pstmt.close();
			}
			// 나중에 열어준 것을 먼저 닫아준다. 
			if(conn!=null) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	}
}
