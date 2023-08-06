package org.sp.projectChatting.DTO;

public class PlanDTO {
	private int plan_idx;
	private String time;
	private String title;
	private String detail;
	private String filename;
	private String imagename;
	
	private ScheduleDTO scheduleDTO;

	
	
	public int getPlan_idx() {
		return plan_idx;
	}

	public void setPlan_idx(int plan_idx) {
		this.plan_idx = plan_idx;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getImagename() {
		return imagename;
	}

	public void setImagename(String imagename) {
		this.imagename = imagename;
	}

	public ScheduleDTO getScheduleDTO() {
		return scheduleDTO;
	}

	public void setScheduleDTO(ScheduleDTO scheduleDTO) {
		this.scheduleDTO = scheduleDTO;
	}
	
	
	
	
	
}
