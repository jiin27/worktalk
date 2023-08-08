package org.sp.worktalk.domain;

public class Roommate {
	private int room_mate_idx;
	private Employee employee;
	private Chatroom chatroom;
	
	public int getRoom_mate_idx() {
		return room_mate_idx;
	}
	public void setRoom_mate_idx(int room_mate_idx) {
		this.room_mate_idx = room_mate_idx;
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
