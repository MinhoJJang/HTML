package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class BoardDAO {
	
	// DAO = Data Transfer Object
	// VO = Value Object
	
	// 1. DBMS와의 연동(JDBC) => 공통로직
	// 공통로직은 JDBCUtil 클래스에서 불러와 사용함. 왜? '공통' 이니까 
	// 2. 비즈니스 메서드(CRUD) => 각각의 DAO마다 사용하는 로직
	

	/*
	코드를 상세히 나누어 작성해야 하는 이유
	1) 코드의 재사용성 증가 
	2) 유지보수 용이 
	3) 협업이 용이함 
	*/
	
	Connection conn = null;
	PreparedStatement pstmt = null;
	
	String sql_selectAll = "select * from board";
	String sql_selectOne = "select * from board where bid = ?";
	
	
	public ArrayList<BoardVO> selectAll(){
		
		JDBCUtil.connect(conn); // db에 연결 
		ArrayList<BoardVO> datas = new ArrayList<BoardVO>(); // 리턴데이터를 보관할 arraylist생성
		
		try {
			pstmt=conn.prepareStatement(sql_selectAll); // board에 존재하는 모든 데이터를 가져온다.
			ResultSet rs = pstmt.executeQuery(); // rs에 pstmt에 담긴 sql문을 실행한 결과를 담는다. 
			while(rs.next()) // 만약 이 결과, rs의 다음 데이터가 존재한다면 
			{ 
				BoardVO vo = new BoardVO(); // BoardVO객체를 생성한다. 
				
				vo.setBid(rs.getInt("bid"));
				vo.setContent(rs.getString("content"));
				vo.setTitle(rs.getString("title"));
				vo.setWriter(rs.getString("writer"));
				// BoardVO 객체에 데이터를 set해준다. 
				// 게시판 데이터는 rs가 갖고 있으므로 get~(name)을 통해 데이터를 가져온다.
				
				datas.add(vo);
				// 이렇게 가져온 데이터를 호출해야 하니까, 이 데이터들을 vo에 넣은 뒤에 ArrayList<BoardVO> 타입의 데이터들을 add 해준다. 
			}
			rs.close(); // rs의 데이터가 더이상 존재하지 않으면 닫아준다. 
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.disconnect(pstmt, conn); // 할일이 끝났으니 db와 연결 해제 
		}
		
		return datas;
		// datas에 add해준 데이터들을 리턴해준다. 
	}
	
	public ArrayList<BoardVO> selectOne(){
		
		// 한가지 데이터만 리턴하려면, 어떻게 해야 할까? 
		// 마찬가지로 연결 및 연결해제 과정은 똑같을 것이다.
		// 한가지 데이터만 본다는 의미는 곧 사용자가 bid를 선택한 상태. 
		// bid에 따른 데이터만 보여주면 된다. 
		
		JDBCUtil.connect(conn); // db에 연결 
		ArrayList<BoardVO> datas = new ArrayList<BoardVO>(); // 리턴데이터를 보관할 arraylist생성
		
		// 이제 사용자에게 bid를 입력하도록 하자. 
		Scanner sc = new Scanner(System.in);
		int userBid = sc.nextInt();
		
		// sql문 실행하고, bid의 존재여부에 따라 경우가 나뉠 것이다. 
		try {
			pstmt=conn.prepareStatement(sql_selectOne); // 사용자가 선택한 bid에 따른 값의 존재여부 확인 
			pstmt.setInt(1, userBid); // ? 에 userBid를 넣어준다. 
			
			ResultSet rs = pstmt.executeQuery(); 
			
			// 검사 시작 
			// 만약 해당 bid에 해당하는 글이 없으면 아래 while문은 실행되지 않을 것임 
			while(rs.next()) {
				BoardVO vo = new BoardVO(); 
				
				vo.setBid(rs.getInt("bid"));
				vo.setContent(rs.getString("content"));
				vo.setTitle(rs.getString("title"));
				vo.setWriter(rs.getString("writer"));
				
				datas.add(vo);
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
		return datas;
	}
	
	public boolean insert() {
		return false;
		
	}
	
	public boolean update() {
		return false;
		
	}
	
	public boolean delete() {
		return false;
		
	}
	
	// boolean의 이유: 올바르게 사용되면 true, 아니면 false리턴 
	

}
