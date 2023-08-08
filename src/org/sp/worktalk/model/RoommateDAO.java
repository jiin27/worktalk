package org.sp.worktalk.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.sp.worktalk.domain.Employee;
import org.sp.worktalk.domain.Roommate;
import org.sp.worktalk.util.DBManager;

public class RoommateDAO {
	DBManager dbManager;
	
	public RoommateDAO(DBManager dbManager) {
		this.dbManager=dbManager;
	}
	
	public int insert(Roommate roommate) {
		Connection con=null;
		PreparedStatement pstmt=null;
		int result=0;
		
		con=dbManager.connect();
		
		StringBuilder sb=new StringBuilder();
		try {
			sb.append("insert into room_mate(room_mate_idx, chatroom_idx, empno)");
			sb.append(" values(seq_room_mate.nextval, ?, ?)");
			pstmt=con.prepareStatement(sb.toString());
			pstmt.setInt(1, roommate.getChatroom().getChatroom_idx());
			pstmt.setInt(2, roommate.getEmployee().getEmpno());
			
			result=pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			dbManager.release(con, pstmt);
		}
		
		return result;
	}
	
	public void selectAll(Roommate roommate) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		Roommate dto=null;
		
		try {
			con=dbManager.connect();
			
			//select r.room_mate_idx, e.empno from room_mate_idx r, employee e where r.empno=e.empno;
			StringBuilder sb=new StringBuilder();
			
			sb.append("select r.room_mate_idx, e.empno"); 
			sb.append("from room_mate r, employee e"); 
			sb.append("where r.empno=e.empno");
			sb.append("and e.empno=?");			
			
			pstmt=con.prepareStatement(sb.toString());
			pstmt.setInt(1, roommate.getEmployee().getEmpno());
			
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				dto=new Roommate();
				Employee emp=new Employee();
				
				emp.setEmpno(rs.getInt("empno"));
				
				dto.setEmployee(emp);
				dto.setRoom_mate_idx(rs.getInt("room_mate_idx"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			dbManager.release(con, pstmt, rs);
		}
	}
}
