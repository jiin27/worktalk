package org.sp.worktalk.chat.server;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

//서버소켓 - 포트번호 입력 후 accept() - 접속자 감지되면 쓰레드 생성되고 - 대화 처리를 쓰레드에 넘기기 - 쓰레드들은 vec에 모으기
public class TestServer extends JFrame{
	JPanel p_top;
	JTextField t_port;
	JButton bt;
	JTextArea area;
	JScrollPane scroll;
	
	ServerSocket serverSocket; //서버용 소켓 - 접속자 감지용
	
	Thread serverThread; //메인 실행부를 대기상태에 빠지지 않게 하기 위한 쓰레드
	
	Vector<ChatThread> vec;
	
	public TestServer() {
		p_top = new JPanel();
		t_port = new JTextField(7);
		bt = new JButton("서버가동");
		area = new JTextArea();
		scroll = new JScrollPane(area);
		
		p_top.add(t_port);
		p_top.add(bt);
		
		setSize(300, 400);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		bt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				serverThread = new Thread() {
					public void run() {
						runServer();
					}
				};
				serverThread.start();
			}
		});
	}
	
	public void runServer() {
		try {
			serverSocket = new ServerSocket(9999);
			area.append("서버 생성 완료\n");
			
			while(true) {
				//접속자 감지 되면 대화용 소켓이 반환된다
				Socket socket=serverSocket.accept();
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
