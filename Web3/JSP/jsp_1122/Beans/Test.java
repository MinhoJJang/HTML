package jsp_1122.Beans;

public class Test {

	public static void main(String[] args) {
		
		/*
		1. JDBC 드라이버를 JVM에 로드
		2. DB에 연결
		3. DBMS에 데이터를 read,write == SQL문을 수행
		4. DB연결 해제
		*/

		// 참고로 나는 MySQL을 사용하기 때문에 드라이버 이름이 다름 

		/* String driver="com.mysql.cj.jdbc.Driver";
		String url="jdbc:mysql://localhost:3306/mhj";
		String user="mhj";
		String password="0000"; */

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
	}

}
