package jsp_1122.Beans;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Test {

	public static void main(String[] args) {
		
		String driver="com.mysql.cj.jdbc.Driver";
		String url="jdbc:mysql://localhost:3306/mhj";
		String user="mhj";
		String password="0000";
		
		System.out.print("이름검색: ");
		Scanner sc=new Scanner(System.in);
		String str=sc.next();
		String sql="select * from test where b like '%"+str+"%'";
		/*
		1. JDBC 드라이버를 JVM에 로드
		2. DB에 연결
		3. DBMS에 데이터를 read,write == SQL문을 수행
		4. DB연결 해제
		
		1,2번을 만들면 4번부터 만든다. 
		*/
		Connection conn=null;
		Statement stmt=null;
		// try문 내에서 객체를 만들면 scope문제가 발생할 수 있으니 밖에서 만들어준다. 
		try {
			Class.forName(driver);
			//System.out.println("JDBC 드라이버 로드 성공!");
			
			conn=DriverManager.getConnection(url, user, password);
			// getConnection은 connection 타입임. 따라서 Connection conn = Drivermanager... 하고 Connection을 import시켜준다. 
			// 또한 url, user, password 같은 것들은 전부 언제든지 바뀔 수 있기 때문에 따로 변수를 만들어 관리한다. 
			//System.out.println("오라클에 연결 성공!");
			
			stmt=conn.createStatement();
			// Statement 객체를 사용해서 DBMS에 데이터를 read,write 하여 SQL문을 수행할 것임 
			ResultSet rs=stmt.executeQuery(sql);
			// 이터레이터와 유사한 rs
			// rs는 null이 될 수 없다. 언제나 메모리를 갖고 있기 때문에 rs == null 은 절대 될 수가 없다. 
			int cnt=0;
			while(rs.next()) {
				System.out.println(rs.getInt("a")+" : "+rs.getString("b"));
				cnt++;
			}
			if(cnt==0) {
				// 만약 while문을 한번도 들어가지 않는다면 
				System.out.println("없는 데이터입니다!");
			}
			rs.close();
			
		} catch (Exception e) {
			// ClassNotFoundException 과 SQLException 을 합침. catch문을 따로 관리할 필요가 없기 때문
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
				// 커넥션을 하고 statment를 열었기 때문에, 닫을 때는 statement를 닫고 난 뒤에 conn을 닫아야 한다. 
				// 마치 집을 나가기 전에 가스밸브를 먼저 잠그고 나가는 것처럼 
				conn.close();
				
			} catch (SQLException e) {
				// close(); 가 던지는 예외 sqlexception
				e.printStackTrace();
			}
		}
		
	
		
		
	}

}
