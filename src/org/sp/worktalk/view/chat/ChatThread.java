package org.sp.worktalk.view.chat;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

//클라이언트, chatroompage용 쓰레드
public class ChatThread extends Thread{
	ChatRoomPage chatRoomPage;
	Socket socket;
	BufferedReader buffr;
	BufferedWriter buffw;
	boolean loopFlag=true;
	
	public ChatThread(ChatRoomPage chatRoomPage, Socket socket) {
		this.socket=socket; //클라이언트의 소켓 넘겨받기
		this.chatRoomPage=chatRoomPage;
		
		try {
			buffr=new BufferedReader(new InputStreamReader(socket.getInputStream()));
			buffw=new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void listen() {
		String msg=null;
		try {
			msg=buffr.readLine(); //서버로부터 메시지 받아서
			chatRoomPage.area.append(msg+"\n"); //화면에 뿌리기
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void sendMsg(String client_msg) {
		try {
			
			buffw.write(client_msg+"\n");
			buffw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void run() {
		while(loopFlag) {
			listen();
		}
	}
	
	
}
