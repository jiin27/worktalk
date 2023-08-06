package org.sp.projectChatting.DTO;


//사원의 정보만을 담는 DTO
public class EmployeeDTO {
	private int empno;
	private String name;
	private String job;
	private String email;
	private String phone;
	private String pass;
	DeptDTO deptDTO;  //deptDTO 소유해야함 (부서의 모든 정보 접근 가능함)
	StatusDTO statusDTO; //statusDTO 소유해야함 (상태의 모든 정보 접근 가능함)

	
	//게터 세터
	public int getEmpno() {
		return empno;
	}
	public void setEmpno(int empno) {
		this.empno = empno;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public DeptDTO getDeptDTO() {
		return deptDTO;
	}
	public void setDeptDTO(DeptDTO deptDTO) {
		this.deptDTO = deptDTO;
	}
	public StatusDTO getStatusDTO() {
		return statusDTO;
	}
	public void setStatusDTO(StatusDTO statusDTO) {
		this.statusDTO = statusDTO;
	}
	
	
	
	
	
	
	

	
	
}
