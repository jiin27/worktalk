package org.sp.worktalk.chat.server;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*
 * [채팅 서버 구축]
- 소켓(socket)은 네트워크 상에서 수행되는 두 프로그램 간의 양방향 통신 링크의 한 쪽 끝단
- 클라이언트의 포트를 통해 서버에 연결을 요청하면 서버에서 요청을 수락할 수 있다.
- 서버는 요청을 수락한 후, 새로운 소켓을 만들어 클라이언트와 연결을 생성하고
- 이후, 클라이언트와 소켓이 연결돼 데이터를 주고 받는다.

Server
1. 서버 소켓 생성 ServerSocket
2. 연결 대기 accept();
3. 네트워크 입출력 스트림 BufferedReader/ BufferedWriter
4. 클라이언트로부터 데이터 수신 BufferedReader read(); / readline();
5. 클라이언트로 데이터 전송 BufferedWriter write(); / flush();
6. 접속 종료 sockeet.close(); / serverSocket.close(); 
 * */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/*
 * [채팅 서버 구축]
- 소켓(socket)은 네트워크 상에서 수행되는 두 프로그램 간의 양방향 통신 링크의 한 쪽 끝단
- 클라이언트의 포트를 통해 서버에 연결을 요청하면 서버에서 요청을 수락할 수 있다.
- 서버는 요청을 수락한 후, 새로운 소켓을 만들어 클라이언트와 연결을 생성하고
- 이후, 클라이언트와 소켓이 연결돼 데이터를 주고 받는다.

Server
1. 서버 소켓 생성 ServerSocket
2. 연결 대기 accept();
3. 네트워크 입출력 스트림 BufferedReader/ BufferedWriter
4. 클라이언트로부터 데이터 수신 BufferedReader read(); / readline();
5. 클라이언트로 데이터 전송 BufferedWriter write(); / flush();
6. 접속 종료 socket.close(); / serverSocket.close(); 
 * */

public class ChatServer extends JFrame{
	JPanel topPanel;
	JTextField t_port;
	JButton bt;
	
	JTextArea chatLogArea;
	JScrollPane scroll;
	
	Thread chatThread;
	
	ServerSocket serverSocket;
	Socket socket;
	
	Vector<ChatThread> vec; //감지된 접속자마다 대응되는 쓰레드 담을 벡터
	
	public ChatServer() {
		topPanel = new JPanel();
		t_port = new JTextField("9999", 10);
		bt = new JButton("서버 가동");
		chatLogArea = new JTextArea();
		scroll = new JScrollPane(chatLogArea);
		
		topPanel.add(t_port);
		topPanel.add(bt);
		
		setLayout(new BorderLayout());
		add(topPanel, BorderLayout.NORTH);
		add(scroll);
		
		setSize(300, 400);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		bt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chatThread = new Thread() {
					public void run() {
						runServer();
					}
				};
				chatThread.start();
			}
		});
		
	}
	
	//클라이언트의 접속 허용, 대화 처리
	public void runServer() {
		int port = Integer.parseInt(t_port.getText());
		
		try {
			//서버 소켓 생성
			serverSocket = new ServerSocket(port);
			chatLogArea.append("서버 생성 완료\n");
			
			while(true) {
				//접속자 연결 대기
				socket = serverSocket.accept();
				String ip=socket.getInetAddress().getHostAddress();
				//chatLogArea.append(ip에 해당하는 계정 이름+"접속\n");
				
				//접속자 감지되면, 대화용 쓰레드 생성! 그리고 socket 넘기기
				ChatThread cht=new ChatThread(this, socket);
				cht.start(); //채팅 쓰레드 수행 start
				
				vec.add(cht); //채팅 쓰레드 벡터에 담기
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	public static void main(String[] args) {
		new ChatServer();
		
	}
}
