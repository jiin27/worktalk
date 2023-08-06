package org.sp.worktalk.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.sp.worktalk.domain.Message;
import org.sp.worktalk.util.DBManager;

public class MessageDAO {
	DBManager dbManager;
	
	public MessageDAO(DBManager dbManager) {
		this.dbManager=dbManager;
		
	}
	
	//서버로부터 수신받은 메시지를 저장, 
	public int insert(Message message) {
		Connection	con=null;
		PreparedStatement pstmt=null;
		int result=0;
		
		con=dbManager.connect();
		
		StringBuilder sb=new StringBuilder();
		sb.append("insert into Message(message_idx, room_mate_idx, msg)");
		sb.append(" values(seq_message.nextval, ?, ?)");
		
		//바인드변수 정의
		try {
			pstmt=con.prepareStatement(sb.toString());
			pstmt.setInt(1, message.getRoom_mate_idx());
			pstmt.setString(2, message.getMsg());
			
			result=pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			dbManager.release(con, pstmt);
		}
		return result;
	}
}
