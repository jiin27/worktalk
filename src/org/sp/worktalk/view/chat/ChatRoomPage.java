package org.sp.worktalk.view.chat;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.sp.worktalk.chat.server.ChatServer;
import org.sp.worktalk.domain.Employee;
import org.sp.worktalk.util.ImageUtil;

public class ChatRoomPage extends JFrame{
	JPanel p_main; //채팅방 모든 구성요소 담길 패널
		
	private ImageIcon ic_back;
	ImageIcon ic_search;
	ImageIcon ic_plus;
	ImageIcon ic_smile;
	ImageIcon ic_send;
	JLabel la_back;
	JLabel la_search;
	JLabel la_plus;
	JLabel la_smile;
	JLabel la_send;
	
	JPanel p_content; // 실제 채팅이 출력되는 센터 패널
	JPanel p_top; //상단 뒤로가기, 검색창 패널
	JTextField t_search; 
	JScrollPane scroll;
	JPanel p_sender; //계정, 이름, 메시지 담을 패널
	JLabel la_msg; //실제 메시지(문자) 뿌려질 영역
	JLabel la_profile; //계정 아이콘 붙일 라벨
	JLabel la_name;
	ImageIcon ic_profile;
	JTextArea area;
	int n;
	
	JPanel p_msgInput; //+ 버튼, 입력창, 이모티콘아이콘, 전송 버튼 모두 담길 하단 패널 
	JTextField t_input; // 
	
	JPanel p_sendFile; //파일 전송 기능 담당하는 패널. +버튼 클릭시 setVisible(true);
	ImageIcon ic_minus;
	JLabel la_minus;
	ImageIcon ic_clip;
	JLabel la_clip;
	ImageIcon ic_img;
	JPanel p_clip;
	JPanel p_img;
	JLabel la_img;
	JPanel p_msgInput_minus;
	boolean flag = false;
	JButton bt;
	
	Socket socket;
	ChatThread cht;
	
	ChatServer chatServer;
	
	int port=9999;
	String ip="192.168.1.221";
	
	public ChatRoomPage() {
	
		p_main = new JPanel();
						
		//라벨에 아이콘이미지 붙이기
		ic_back = new ImageIcon(ImageUtil.getImage("res/back.png", 25, 25));
		la_back= new JLabel(ic_back);
		ic_search = new ImageIcon(ImageUtil.getImage("res/search.png", 25, 25));
		la_search = new JLabel(ic_search);
		la_search.setIcon(ic_search);
		ic_plus = new ImageIcon(ImageUtil.getImage("res/plus.png", 25, 25));
		la_plus = new JLabel(ic_plus);
		ic_smile=new ImageIcon(ImageUtil.getImage("res/smile.png", 20, 20));
		la_smile = new JLabel(ic_smile);
		ic_send = new ImageIcon(ImageUtil.getImage("res/ok.png", 25, 25));
		la_send = new JLabel(ic_send);
		
		//메시지 주고 받는 중심 패널
		p_content = new JPanel();
		area = new JTextArea();
		area.setBackground(new Color(197, 224, 180));
//		p_sender = new JPanel();
//		p_sender.setLayout(new BorderLayout());
//		p_sender.setPreferredSize(new Dimension(270, 30)); //
//		p_sender.setBackground(new Color(0, 0, 0, 0));
//		la_profile = new JLabel();
//		la_profile.setIcon(ic_profile);
//		la_profile.setPreferredSize(new Dimension(30, 30));
//		la_name = new JLabel("회계 1팀 사원 박지인");
//		la_name.setFont(new Font("SansSerif", Font.PLAIN, 11));
//		la_msg = new JLabel();
//		JPanel p_profile=new JPanel(); //이름과 메시지를 묶을 패널
//		p_profile.setBackground(new Color(0, 0, 0, 0));
//		p_profile.setLayout(new BorderLayout());
//		p_profile.add(la_name, BorderLayout.NORTH);
//		p_profile.add(la_msg);
//		p_sender.add(la_profile, BorderLayout.WEST);
//		p_sender.add(p_profile);
		
		//상단 패널_ 검색창, 뒤로가기 버튼
		p_top = new JPanel();
		t_search = new JTextField(15);
		t_search.setBackground(new Color(242, 242, 242));
		p_top.setLayout(new FlowLayout());
		p_top.setPreferredSize(new Dimension(300, 30));
		p_top.setBackground(new Color(0, 0, 0, 0));
		p_top.add(la_back);
		p_top.add(t_search);
		p_top.add(la_search);
		
		//메시지 입력창 
		p_msgInput = new JPanel();
		t_input = new JTextField(15);
		
		//메시지 입력창 스타일 적용
		p_msgInput.setLayout(new FlowLayout());
		p_msgInput.setPreferredSize(new Dimension(300, 30));
		p_msgInput.setBackground(new Color(213, 229, 242));
		p_msgInput.add(la_plus);
		p_msgInput.add(t_input);
		p_msgInput.add(la_smile);
		p_msgInput.add(la_send);
				
		//p_main 스타일 적용
		p_main.setPreferredSize(new Dimension(300, 450));
		p_main.setBackground(new Color(226, 240, 217));
		
		//메시지 띄우는 패널 스타일 적용
		p_content.setLayout(new BorderLayout());
		p_content.setPreferredSize(new Dimension(270, 450));
		p_content.setBackground(new Color(197, 224, 180));
		p_content.add(p_top, BorderLayout.NORTH);
		p_content.add(area);
		bt=new JButton("접속");
		
		p_main.add(p_content);
		p_main.add(bt);
		
		setSize(300, 500);
		setLayout(new BorderLayout());
		add(p_main);
		//add(p_sending, BorderLayout.SOUTH);
		add(p_msgInput, BorderLayout.SOUTH);
		setVisible(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		la_back.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});
		
		la_plus.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				p_sendFile.setVisible(flag);
			}
		});
		
		t_input.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				int key=e.getKeyCode();
				if(key==KeyEvent.VK_ENTER) {
					send(); //서버에 msg 전송
				}
			}
		});
		
		la_send.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				send();
			}
		});
		
		bt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
	}	
	
	public void send() {
		String client_msg=t_input.getText();
		//대화 프로토콜을 구성하여 보내기 
		StringBuilder sb = new StringBuilder();

		sb.append("{");
		sb.append("\"requestType\":\"msg\",");
		sb.append("\"empno\":"+Main.employeeDTO.getEmpno()+",");
		sb.append("\"data\":\""+client_msg+"\"	");	
		sb.append("}");		
		
		cht.sendMsg(client_msg);
		
		t_input.setText("");
	}

	
}
