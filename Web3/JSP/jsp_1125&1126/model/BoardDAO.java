package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class BoardDAO {
	
	// DAO = Data Access Object
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
	
	String sql_selectAll = "select * from board order by bid desc";
	String sql_selectOne = "select * from board where bid = ?";
	String sql_insert = "insert into board values(?,?,?,?)";
	// mysql은 oracle의 nvl이나, ifnull을 사용하는 것보다 아예 테이블 설정에서부터 bid값을 1씩 올려서 받도록 할 수 있다. 
/*	
 * create table board(
		bid int primary key auto_increment,
		writer varchar(20) not null,
		title varchar(30) not null,
		content varchar(50) not null
	);
*/	
	String sql_delete = "delete from board where bid =?";
	String sql_update = "update board set title=?, writer=?, content=? where bid =?";
	
	public ArrayList<BoardVO> selectAll(){
		
		conn = JDBCUtil.connect(); // db에 연결 
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
				// 이렇게 가져온 데이터를 리턴해야 하니까, 이 데이터들을 vo에 넣은 뒤에 ArrayList<BoardVO> 타입의 데이터들을 add 해준다. 
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
	
	public BoardVO selectOne(BoardVO vo){ 
		
		// (int num) 이런 코드는 jsp식 코드임. 별로 좋지 않다. 
		// 조건식 자체가 여러가지일 수 있다. 
		// bid 뿐만 아니라, writer, title 등을 검색하서 찾을 수도 있음
		// 그러니까 int num 이 아니라 String writer 이런 게 들어갈수도 있음
		// 인자를 고정해버리면 유지보수가 힘들어진다. 
		
		// 그래서 input 파라미터로 BoardVO를 사용하자. 
		// 그러면 데이터를 가져올 때 vo.getBid로 가져오자. 
		
		// 한가지 데이터만 리턴하려면, 어떻게 해야 할까? 
		// 마찬가지로 연결 및 연결해제 과정은 똑같을 것이다.
		// 한가지 데이터만 본다는 의미는 곧 사용자가 bid를 선택한 상태. 
		// bid에 따른 데이터만 보여주면 된다. 
		
		conn = JDBCUtil.connect(); // db에 연결 
		BoardVO data = null; // 리턴데이터를 보관할 arraylist생성
		
		/*// 이제 사용자에게 bid를 입력하도록 하자. 
		Scanner sc = new Scanner(System.in);
		int userBid = sc.nextInt();*/
		
		// sql문 실행하고, bid의 존재여부에 따라 경우가 나뉠 것이다. 
		try {
			pstmt=conn.prepareStatement(sql_selectOne); // 사용자가 선택한 bid에 따른 값의 존재여부 확인 
			pstmt.setInt(1, vo.getBid()); // ? 에 userBid를 넣어준다. 
			
			ResultSet rs = pstmt.executeQuery(); 
			
			// 검사 시작 
			// 만약 해당 bid에 해당하는 글이 없으면 아래 while문은 실행되지 않을 것임 
			if(rs.next()) {
				data = new BoardVO(); 
				
				data.setBid(rs.getInt("bid"));
				data.setContent(rs.getString("content"));
				data.setTitle(rs.getString("title"));
				data.setWriter(rs.getString("writer"));	
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} 	
		return data;
	}
	
	public boolean insert(BoardVO vo) {
		
		conn = JDBCUtil.connect();
		try {
			pstmt = conn.prepareStatement(sql_insert);
			pstmt.setInt(1, vo.getBid());
			pstmt.setString(2, vo.getWriter());
			pstmt.setString(3, vo.getTitle());
			pstmt.setString(4, vo.getContent());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			JDBCUtil.disconnect(pstmt, conn);
		}
		return true;	
	}
	
	public boolean update(BoardVO vo) {
		//String sql_update = "update board set title=?, writer=?, content=? where bid =?";
		conn = JDBCUtil.connect();
		try {
			pstmt = conn.prepareStatement(sql_update);
			// 일단 현재 글 번호를 가져온다. 
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getWriter());
			pstmt.setString(3, vo.getContent());
			pstmt.setInt(4, vo.getBid());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			JDBCUtil.disconnect(pstmt, conn);
		}
		return true;	
	}
	
	public boolean delete(BoardVO vo) {
		
		conn = JDBCUtil.connect();
		try {
			pstmt = conn.prepareStatement(sql_delete);
			pstmt.setInt(1, vo.getBid());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			JDBCUtil.disconnect(pstmt, conn);
		}
		return true;	
	}
	
	// boolean의 이유: 올바르게 사용되면 true, 아니면 false리턴 
	

}
