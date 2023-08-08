package org.sp.worktalk.chat.server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Vector;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.sp.worktalk.domain.Employee;

//서버에 접속하는 각각의 클라이언트마다 1:1 대응하는 대화 전용 쓰레드 정의
public class ChatThread extends Thread{
	ChatServer chatServer;
	Socket socket;
	BufferedReader buffr; //입력 스트림. 쓰레드가 보유
	BufferedWriter buffw; //출력 스트림.
	
	boolean loopFlag=true;
	JSONParser jsonParser;
	Employee employee; //채팅 중인 나에 대한 정보 
	
	Vector<ChatThread> friendList=new Vector<ChatThread>();
	
	
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
		jsonParser = new JSONParser();
	}
	
	//클라이언트로부터 데이터 수신
	public void listen() {
		String msg=null;
		try {
			msg = buffr.readLine(); //msg듣기
			
			//클라이어트가 보낸 요청 타입에 따라 적절한 업무 처리 
			////대화 : msg , 이모티콘 : emoti, 로그이정보: "login", 파일:"file"
			//로그인요청시 : 넘겨받은 회원 정보를 이용하여, 현재 메시지 쓰레드에 해당 회원 정보를 DTO로 보관
			JSONObject json=(JSONObject)jsonParser.parse(msg);
			String requestType=(String)json.get("requestType");
			
			switch(requestType) {
				case "login":getEmp(json) ;break; 
				case "msg":broadCast(json) ;break;  
			}			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
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
	
	//회정보 가져오기 
	public void getEmp(JSONObject json) {
		//기존 명단 지우고 다시 작성
		friendList.remove(friendList);
		int me=(Integer)json.get("me");
		JSONArray jsonArray=(JSONArray)json.get("roommate");
		
		for(int i=0;i<jsonArray.size();i++) {
			JSONObject emp=(JSONObject)jsonArray.get(i);
			int empno = (Integer)emp.get("empno");
			
			//친구목록에 나의 정보가 포함되어 잇따면, 나만 회원정보 보관 
			if(me==empno) {
				employee=chatServer.empDAO.select(empno);
			}
			
			//모든 접속자 명단에서 , 현재 쓰레드가 존재하는지 판단 
			//있다면, 대화 상대 명단에 추가 
			for(int a=0; a<chatServer.vec.size();a++) {
				ChatThread ct=chatServer.vec.get(a);
				if(empno == ct.employee.getEmpno()) { //친구명단과 로그인 및 접속한 사람이 같다면...
					friendList.add(ct);
				}
			}
		}
		System.out.println("현재 로그인한 유저의 친구 수는 "+friendList.size());
	}
	

	//친구를 대상으로 브로드 케스팅 
	public void broadCast(JSONObject json) {
		int empno=(Integer)json.get("empno");
		String data=(String)json.get("data");
		
		//접속한 사용자마다 1:1 대응되는 chatThread 객체의 msg전송 메서드 호출
		for(int i=0; i<friendList.size(); i++) {
			ChatThread cht=friendList.get(i); //생성된 쓰레드 vec에서 i번째 쓰레드(에 해당하는 클라이언트)
			cht.sendMsg(data); //수신받은 msg 클라이언트에게 다시 전송
		}
		
		//chatServer에 채팅 로그 남기기
		chatServer.chatServerArea.append(data+"\n");
		
	}
	
	
	public void run() {
		while(loopFlag) {
			listen(); //
		}
	}
}
