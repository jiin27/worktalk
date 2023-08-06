package org.sp.projectChatting.Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.sp.projectChatting.DTO.DeptDTO;
import org.sp.projectChatting.DTO.EmployeeDTO;
import org.sp.projectChatting.DTO.StatusDTO;
import org.sp.projectChatting.main.Main;
import org.sp.projectChatting.util.DBManager;

//오직 로그인에 필요한 DAO
public class EmployeeDAO {
	DBManager dbManager;
	Main main;
	
	public EmployeeDAO(DBManager dbManager,Main main) {
		this.dbManager = dbManager;
		this.main = main;
	}
	
	
	//로그인
	public EmployeeDTO login(EmployeeDTO dto) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		EmployeeDTO employeeDTO = null;
		DeptDTO deptDTO = null;
		StatusDTO statusDTO = null;
		
		try {
			con = dbManager.connect();
			if(con == null) {
				System.out.println("접속실패");
				//JOptionPane.showMessageDialog(????, "일치하는 로그인 정보가 없습니다.");
			}else { //접속성공시
				String sql = "select * from employee e, dept d where e.deptno = d.deptno and email = ? and pass = ?"; //바인드변수를 사용하여 쉽게 emp 테이블 불러오기
				pstmt = con.prepareStatement(sql); //쿼리 수행 객체 생성
				pstmt.setString(1, dto.getEmail()); //받아온 dto값을 대입(hes@nave.com)
				pstmt.setString(2, dto.getPass()); //받아온 dto 값을 대입(23072)
				rs = pstmt.executeQuery();//쿼리 수행후, 결과를 (Resultset)반환받기
				if(rs.next()) { //rs를 dto 에 옮겨 담자
					employeeDTO = new EmployeeDTO();//비어있는 dto 생성해놓기
					//비어있는 dto 에 검색된rs결과물을 채워놓고 밑에서 리터하자
					employeeDTO.setEmpno(rs.getInt("empno"));
					employeeDTO.setName(rs.getString("name"));
					employeeDTO.setJob(rs.getString("job"));
					employeeDTO.setPass(rs.getString("pass"));
					employeeDTO.setEmail(rs.getString("email"));
					employeeDTO.setPhone(rs.getString("phone"));

					deptDTO = new DeptDTO(); //비어있는 dto 생성해놓기
					deptDTO.setDeptno(rs.getInt("deptno"));
					deptDTO.setDname(rs.getString("dname"));
					
					statusDTO = new StatusDTO();
					statusDTO.setStatus_idx(rs.getInt("status_idx"));
					
					employeeDTO.setDeptDTO(deptDTO);
					employeeDTO.setStatusDTO(statusDTO);
					

				}
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			dbManager.release(con, pstmt, rs);
		}
		return employeeDTO;
	}
	
	//모든 직원 결과값 가지고 리스트에 뿌리기
	public List selectallemployee(int deptno) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<EmployeeDTO> empList= new ArrayList<EmployeeDTO>();
		
		
		con=dbManager.connect();
		String sql = "select * from employee e, dept d where e.deptno = d.deptno and e.deptno = ?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, deptno);
			rs = pstmt.executeQuery();
			while(rs.next()) { //rs를 dto 에 옮겨 담자
				EmployeeDTO employeeDTO = new EmployeeDTO();//비어있는 dto 생성해놓기
				//비어있는 dto 에 검색된rs결과물을 채워놓고 밑에서 리터하자
				employeeDTO.setEmpno(rs.getInt("empno"));
				employeeDTO.setName(rs.getString("name"));
				employeeDTO.setJob(rs.getString("job"));
				employeeDTO.setPass(rs.getString("pass"));
				employeeDTO.setEmail(rs.getString("email"));
				employeeDTO.setPhone(rs.getString("phone"));

				DeptDTO deptDTO = new DeptDTO(); //비어있는 dto 생성해놓기
				deptDTO.setDeptno(rs.getInt("deptno"));
				deptDTO.setDname(rs.getString("dname"));
				
				
				
				StatusDTO statusDTO = new StatusDTO();
				statusDTO.setStatus_idx(rs.getInt("status_idx"));
				//statusDTO.setIcon(rs.getString("icon"));
				
				employeeDTO.setDeptDTO(deptDTO);
				employeeDTO.setStatusDTO(statusDTO);

				empList.add(employeeDTO);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			dbManager.release(con, pstmt, rs);
		}
		return empList;
		
	}

	
	
	//회원 정보 수정 
	public int passChange(EmployeeDTO dto, String newpass) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int result=0;
		
		con = dbManager.connect();
		String sql = "update employee set pass=? where email = ? and pass = ?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, newpass); //새로운 비번 
			pstmt.setString(2, dto.getEmail()); //이메ㅣㅇㄹ 
			pstmt.setString(3, dto.getPass()); //기존 비번  
			
			result=pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			dbManager.release(con, pstmt);
		}
		return result;
		
	}
	
}
