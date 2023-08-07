package org.sp.worktalk.chat.server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

//서버에 접속하는 각각의 클라이언트마다 1:1 대응하는 대화 전용 쓰레드 정의
public class ChatThread extends Thread{
	ChatServer chatServer;
	Socket socket;
	BufferedReader buffr; //입력 스트림. 쓰레드가 보유
	BufferedWriter buffw; //출력 스트림.
	
	boolean loopFlag=true;
	
	public ChatThread(ChatServer chatServer, Socket socket) {
		this.chatServer=chatServer;
		this.socket=socket;
		
		//네트워크 입출력 스트림 생성
		try {
			buffr = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			buffw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	//클라이언트로부터 데이터 수신
	public void listen() {
		String msg=null;
		try {
			msg = buffr.readLine(); //msg듣기
			
			//접속한 사용자마다 1:1 대응되는 chatThread 객체의 msg전송 메서드 호출
			for(int i=0; i<chatServer.vec.size(); i++) {
				ChatThread cht=chatServer.vec.get(i); //생성된 쓰레드 vec에서 i번째 쓰레드(에 해당하는 클라이언트)
				cht.sendMsg(msg); //수신받은 msg 클라이언트에게 다시 전송
			}
			
			//chatServer에 채팅 로그 남기기
			chatServer.chatServerArea.append(msg+"\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
				
	}
	
	//전송받은 메시지 뿌리기
	public void sendMsg(String msg) {
		try {
			buffw.write(msg+"\n");
			buffw.flush();
		} catch (IOException e) {
			e.printStackTrace();
			loopFlag=false;
			
			chatServer.vec.remove(this);
			chatServer.chatServerArea.append("접속자 수"+chatServer.vec.size()+"\n");
		}
	}
	
	public void run() {
		while(loopFlag) {
			listen(); //
		}
	}
}
