package day06;

public class Test {

	public static void main(String[] args) {
		
		/*
		1. JDBC 드라이버를 JVM에 로드
		2. DB에 연결
		3. DBMS에 데이터를 read,write == SQL문을 수행
		4. DB연결 해제
		*/
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
