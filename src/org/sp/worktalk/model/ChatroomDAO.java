package org.sp.worktalk.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.sp.worktalk.domain.Chatroom;
import org.sp.worktalk.util.DBManager;

//채팅방 목록. 채팅방 별 고유 번호, 채팅방 이름, 라스트 메시지 보유
public class ChatroomDAO {
	DBManager dbManager;
	
	public ChatroomDAO(DBManager dbManager) {
		this.dbManager = dbManager;
	}
	
	//채팅이 시작되면, 생성된 채팅방 idx생성, 채팅방 이름은 접속자의 '사원이름'넣기, 마지막 메시지 들어가기(lastindex?)
	public int insert(Chatroom chatroom) {
		Connection con=null;
		PreparedStatement pstmt=null;
		int result=0;
		
		con=dbManager.connect();
		
		StringBuilder sb=new StringBuilder();
		sb.append("insert into chatroom(chatroom_idx, chatroom_name, lastmessage)");
		sb.append(" values(seq_chatroom.nextval, ?, ?)");
		//employee.empno 
		
		try {
			pstmt=con.prepareStatement(sb.toString());
			pstmt.setString(1, chatroom.getChatroom_name());
			pstmt.setString(2, chatroom.getLastmessage());
			
			result=pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			dbManager.release(con, pstmt);
		}
		
		return result;
	}
	
	public void selectAllChatroom() {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		Chatroom dto=null;
		
		con=dbManager.connect();
		
		//String sql="select * from where chatroom_idx=?"; chat
	}
}

