package org.sp.worktalk.view.chat;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class ChatThread extends Thread{
	ChatRoomPage chatRoomPage;
	
	Socket socket;
	BufferedReader buffr;
	BufferedWriter buffw;
	
	public ChatThread(ChatRoomPage chatRoomPage) {
		this.chatRoomPage=chatRoomPage;
		socket=chatRoomPage.socket; //클라이언트 소켓 넘겨받기
		
		try {
			buffr=new BufferedReader(new InputStreamReader(socket.getInputStream()));
			buffw=new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void listen() {
		try {
			String msg=buffr.readLine(); //서버로부터 메시지 받기
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void sendMsg(String msg) {
		try {
			buffw.write(msg+"\n");
			buffw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void run() {
		while(true) {
			listen();
		}
	}
	
	
}
