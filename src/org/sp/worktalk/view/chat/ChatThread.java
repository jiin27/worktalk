package org.sp.worktalk.view.chat;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.sp.worktalk.domain.Employee;

//클라이언트, chatroompage용 쓰레드
public class ChatThread extends Thread{
	ChatRoomPage chatRoomPage;
	Socket socket;
	BufferedReader buffr;
	BufferedWriter buffw;
	boolean loopFlag=true;
	JSONParser jsonParser;
	
	public ChatThread(ChatRoomPage chatRoomPage, Socket socket) {
		this.chatRoomPage=chatRoomPage;
		
		this.socket=socket; //클라이언트의 소켓 넘겨받기
		this.chatRoomPage=chatRoomPage;
		
		try {
			buffr=new BufferedReader(new InputStreamReader(socket.getInputStream()));
			buffw=new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		jsonParser = new JSONParser();
	}
	
	public void listen() {
		String msg=null;
		try {
			msg=buffr.readLine(); //서버로부터 메시지 받아서
			
			//파싱
			JSONObject json=(JSONObject)jsonParser.parse(msg);
			String requestType=(String)json.get("requestType");
			
			switch(requestType) {
				case "msg":viewMsg(json);break; 
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void viewMsg(JSONObject json) {
		long empno=(Long)json.get("empno");
		Employee emp=Main.empDAO.select((int)empno); //말건사람 정보..
		String data = (String)json.get("data");
		
		chatRoomPage.area.append(emp.getName()+"님의 말: "+data+"\n");
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
