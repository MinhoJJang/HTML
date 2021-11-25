<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.sql.*" %>
    <%
    request.setCharacterEncoding("UTF-8");
    
    String driver="com.mysql.cj.jdbc.Driver";
	String url="jdbc:mysql://localhost:3306/mhj";
	String user="mhj";
	String password="0000";
	
	String sql_insert = "insert into test values(?,?)";
	String sql_selectAll="select * from test";  
	// 원래는 select * 이렇게 쓰지만 prepareStatement의 장점을 알기 위해 uname, content을 직접 써주자
	
	Connection conn = null;
	PreparedStatement pstmt=null;
	
	try {
		Class.forName(driver); //연결 1
		conn = DriverManager.getConnection(url, user, password); //연결 2
		
		// 들어온 데이터를 db에 삽입하려면?
		// 일단 데이터가 존재하는지부터 살펴보아야 한다. if문으로 가야겠지 
		String curName = request.getParameter("writer");
		String curContent = request.getParameter("content");
		
		if(curName != null){
			
		// String putSql = "insert into test values( '"+ curName + "','" + curContent + "')";
		// 이거는 Statement에서 사용하는 방식. +를 쓰기가 귀찮으니..
		
		pstmt = conn.prepareStatement(sql_insert);
		pstmt.setString(1,curName);
		pstmt.setString(2,curContent);
		pstmt.executeUpdate();
			
		// executeQuery는 정보를 받아올때 사용함. 
		}
		
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>jdbc test</title>
</head>
<body>	

<form method="post">
	작성자: <input type="text" name ="writer">
	내용: <input type="text" name="content">
	<input type="submit" value="게시글 등록">
</form>

<hr>
<h2>게시글 목록</h2>
<!-- db -> jdbc -> 스크립트 -->	
<% 
		pstmt = conn.prepareStatement(sql_selectAll);
		// 이렇게 들어온 sql문을 실행시키려면, 아래와 같이 ResultSet타입으로 리턴
		ResultSet rs = pstmt.executeQuery();
		
		while(rs.next()){
			out.println(rs.getString("writer")+"님: "+rs.getString("content")+"<br>");
			//out.println(rs.getInt("bid")+". "+rs.getString(1)+"님: ["+rs.getString(2)+"]<br>");
			// rs.getString(index) 가 올 수도 있음. prepareStatement의 특징 
			// 그래서 위와같이 (1), (2) 이런 식으로 사용 가능함 
		}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		try{
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
%>
	
	
</body>
</html>