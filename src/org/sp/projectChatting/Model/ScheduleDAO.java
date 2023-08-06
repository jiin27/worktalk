package org.sp.projectChatting.Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import org.sp.projectChatting.util.DBManager;

//스케줄의 CRUD 수행을 위한 객체
public class ScheduleDAO {
	DBManager dbManager;
	
	
	//한직원의 스케줄 정보를 모두 불러오는메서드
	public void selectALL(DBManager dbManager) {
		this.dbManager = dbManager;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		//쿼리 수행
		con = dbManager.connect();
		if(con==null) {
			System.out.println("접속실패");
		}else {
			StringBuilder sb = null;
			sb.append("select * from schedule where name=?");//디비 설계완료되면 조인문을 작성해서 준비해 둬야함
			//pstmt.setString(1, 가지고올것);//바인드 변수 사용해서 집어넣기
			try {
				con.prepareStatement(sb.toString());
				rs=pstmt.executeQuery();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
}
