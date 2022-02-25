package model.board;

import java.util.ArrayList;

public class BoardSet {
//	BoardVo 1개에 ReplyVO N개가 붙어있는 형태의 게시글 
	private BoardVO board;
	private ArrayList<ReplyVO> rdatas = new ArrayList<ReplyVO>();
	
	public BoardVO getBoard() {
		return board;
	}
	public void setBoard(BoardVO board) {
		this.board = board;
	}
	public ArrayList<ReplyVO> getRdatas() {
		return rdatas;
	}
	public void setRdatas(ArrayList<ReplyVO> rdatas) {
		this.rdatas = rdatas;
	}
	
	
}
