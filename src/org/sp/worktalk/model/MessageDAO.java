package org.sp.worktalk.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
	
	public Message selectAllMessage(Message message) {
		Connection con=null;
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		Message dto=null;
		
		con=dbManager.connect();
		
		String sql="select * from message where room_mate_idx = ?";
		//select r.empno, m.msg, m.time from room_mate r, message m, employee e where r.room_mate_idx=m.room_mate_idx;
		
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, message.getRoom_mate_idx()); //
			
			rs=pstmt.executeQuery();
			while(rs.next()) {
				dto = new Message();
				dto.setMessage_idx(rs.getInt("message_idx"));
				dto.setRoom_mate_idx(rs.getInt("room_mate_idx"));
				dto.setMsg(rs.getString("msg"));
				dto.setTime(rs.getString("time"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			dbManager.release(con, pstmt, rs);
		}
		return dto;
	}
}
