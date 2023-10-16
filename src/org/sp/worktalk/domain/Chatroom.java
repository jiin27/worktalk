package org.sp.worktalk.domain;

public class Chatroom {
	private int chatroom_idx;
	private String chatroom_name;
	private String lastmessage;
	
	public int getChatroom_idx() {
		return chatroom_idx;
	}
	public void setChatroom_idx(int chatroom_idx) {
		this.chatroom_idx = chatroom_idx;
	}
	public String getChatroom_name() {
		return chatroom_name;
	}
	public void setChatroom_name(String chatroom_name) {
		this.chatroom_name = chatroom_name;
	}
	public String getLastmessage() {
		return lastmessage;
	}
	public void setLastmessage(String lastmessage) {
		this.lastmessage = lastmessage;
	}
}
