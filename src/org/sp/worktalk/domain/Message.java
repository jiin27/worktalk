package org.sp.worktalk.domain;

import java.sql.Date;

public class Message {
	private int message_idx;
	private int room_mate_idx;
	private String msg;
	private String time;
	
	public int getMessage_idx() {
		return message_idx;
	}
	public void setMessage_idx(int message_idx) {
		this.message_idx = message_idx;
	}
	public int getRoom_mate_idx() {
		return room_mate_idx;
	}
	public void setRoom_mate_idx(int room_mate_idx) {
		this.room_mate_idx = room_mate_idx;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}

	
	
	
}
