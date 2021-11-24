package jsp_1123;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Test1 {

	public static void main(String[] args) {
		
		String driver="oracle.jdbc.driver.OracleDriver";
		String url="jdbc:oracle:thin:@localhost:1521:xe";
		String user="kim";
		String password="1234";
		
		System.out.print("입력: ");
		Scanner sc=new Scanner(System.in);
		String num=sc.next();
		String sql_delete="delete from test where a<="+num;
		String sql_select="select * from test";
		
		Connection conn=null;
		Statement stmt=null;
		try {
			Class.forName(driver);
			
			conn=DriverManager.getConnection(url,user,password);
			stmt=conn.createStatement();
			
			int cnt=stmt.executeUpdate(sql_delete);
			System.out.println(cnt+"개 데이터 삭제됨!");
			
			ResultSet rs=stmt.executeQuery(sql_select);
			while(rs.next()) {
				System.out.println(rs.getInt("a")+" : "+rs.getString("b"));
			}
			rs.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
	}

}
