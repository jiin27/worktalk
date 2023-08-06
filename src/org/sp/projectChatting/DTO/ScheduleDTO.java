package org.sp.projectChatting.DTO;

//한직원의 스케줄 정보를 가지고 있는 DTO
public class ScheduleDTO {
	private int schedule_idx;
	private int yy;
	private int mm;
	private int dd;
	
	EmployeeDTO empDTO;

	
	
	public int getSchedule_idx() {
		return schedule_idx;
	}

	public void setSchedule_idx(int schedule_idx) {
		this.schedule_idx = schedule_idx;
	}

	public int getYy() {
		return yy;
	}

	public void setYy(int yy) {
		this.yy = yy;
	}

	public int getMm() {
		return mm;
	}

	public void setMm(int mm) {
		this.mm = mm;
	}

	public int getDd() {
		return dd;
	}

	public void setDd(int dd) {
		this.dd = dd;
	}

	public EmployeeDTO getEmpDTO() {
		return empDTO;
	}

	public void setEmpDTO(EmployeeDTO empDTO) {
		this.empDTO = empDTO;
	}
	
	
	
}
