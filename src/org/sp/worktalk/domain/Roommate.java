package org.sp.worktalk.domain;

public class Roommate {
	private int room_mate_idx;
	private int chatroom_idx;
	private int empno;
	private Employee employee;
	private Chatroom chatroom;
	
	public int getRoom_mate_idx() {
		return room_mate_idx;
	}
	public void setRoom_mate_idx(int room_mate_idx) {
		this.room_mate_idx = room_mate_idx;
	}
	public int getChatroom_idx() {
		return chatroom_idx;
	}
	public void setChatroom_idx(int chatroom_idx) {
		this.chatroom_idx = chatroom_idx;
	}
	public int getEmpno() {
		return empno;
	}
	public void setEmpno(int empno) {
		this.empno = empno;
	}
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	public Chatroom getChatroom() {
		return chatroom;
	}
	public void setChatroom(Chatroom chatroom) {
		this.chatroom = chatroom;
	}
	
	
}