package org.sp.worktalk.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.sp.worktalk.util.DBManager;

public class ChatroomDAO {
	DBManager dbManager;
	
	public ChatroomDAO(DBManager dbManager) {
		this.dbManager = dbManager;
	}
	
	public void selectChatroomInfo() {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		dbManager.connect();
		
		String sql="select * from chatroom";
	}
}

