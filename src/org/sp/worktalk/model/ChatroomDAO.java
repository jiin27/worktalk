package org.sp.worktalk.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.sp.worktalk.util.DBManager;

//채팅방 목록. 채팅방 별 고유 번호, 채팅방 이름, 라스트 메시지 보유
public class ChatroomDAO {
	DBManager dbManager;
	
	public ChatroomDAO(DBManager dbManager) {
		this.dbManager = dbManager;
	}
	
	//채팅이 시작되면, 생성된 채팅방 idx생성, 채팅방 이름은 접속자의 '사원이름'넣기, 마지막 메시지 들어가기(lastindex?)
	public void insert() {
		Connection con=null;
		PreparedStatement pstmt=null;
		int result=0;
		
		con=dbManager.connect();
		
		StringBuilder sb=new StringBuilder();
		sb.append("insert into chatroom(chatroom_idx, chatroom_name, lastmessage)");
		sb.append(" values(seq_chatroom.nextval, ?, ?)");
		
		try {
			pstmt=con.prepareStatement(sb.toString());
			pstmt.setInt(1, ); //접속자 이름
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String sql="select * from chatroom";
	}
}

