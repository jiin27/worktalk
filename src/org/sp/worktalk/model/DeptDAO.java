package org.sp.worktalk.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.sp.worktalk.domain.Dept;
import org.sp.worktalk.util.DBManager;

//오직 비밀번호 교체를 위한 
public class DeptDAO {
	DBManager dbManager;
	
	public DeptDAO(DBManager dbManager) {
		this.dbManager = dbManager;
	}
	
	public List selectAllDept() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Dept> deptList= new ArrayList<Dept>();
		
		con = dbManager.connect();
		String sql = "select * from dept"; 
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Dept deptDTO = new Dept(); //비어있는 dto 생성해놓기
				deptDTO.setDeptno(rs.getInt("deptno"));
				deptDTO.setDname(rs.getString("dname"));
				deptList.add(deptDTO);


			}
		} catch (SQLException e) {

			e.printStackTrace();
		}finally {
			dbManager.release(con, pstmt, rs);
		}
		return deptList;

		
	}
	

	
}
