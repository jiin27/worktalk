package org.sp.worktalk.chat.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ChatRoomPage extends JPanel{
	JPanel p_main; //채팅방 모든 구성요소 담길 패널
	
	JPanel p_content; // 실제 채팅이 출력되는 센터 패널
	JPanel p_top; //상단 뒤로가기, 검색창 패널
	ImageIcon ic_back;
	JLabel la_back;
	JTextField t_search; 
	
	JPanel p_msgInput; //+ 버튼, 입력창, 이모티콘아이콘, 전송 버튼 모두 담길 하단 패널
	ImageIcon ic_plus; 
	JLabel la_plus;
	JTextField t_input; // 
	ImageIcon ic_emoticon;
	JLabel la_emoticon; //이모티콘 이미지 붙일 라벨
	ImageIcon ic_send;
	JLabel la_send; //전송 버튼 붙일 라벨
	
	public ChatRoomPage() {
		p_main = new JPanel();
		
		//메시지 주고 받는 중심 패널
		p_content = new JPanel();
		
		//중심 패널 상단에 위치할 검색창, 뒤로가기 버튼
		p_top = new JPanel();
		ic_back = new ImageIcon("res/back.png");
		la_back = new JLabel();
		la_back.setIcon(ic_back);
		t_search = new JTextField(25);
		
		//메시지 입력창 
		p_msgInput = new JPanel();
		ic_plus = new ImageIcon("res/plus.png"); //플러스 버튼
		la_plus = new JLabel(ic_plus);
		t_input = new JTextField(20);
		ic_emoticon = new ImageIcon("res/smile.png"); //이모티콘
		la_emoticon = new JLabel();
		la_emoticon.setIcon(ic_emoticon);
		ic_send = new ImageIcon("res/send.png"); //전송버튼
		la_send =new JLabel();
		la_send.setIcon(ic_send);
		
		Dimension d = new Dimension(10, 10); //아이콘 사이즈 규격
		
		//상단 패널_검색창, 뒤로가기 아이콘 
		p_top.setLayout(new FlowLayout());
		p_top.setPreferredSize(new Dimension(280, 20));
		p_top.add(la_back);
		p_top.add(t_search);
		
		//메시지 입력창 스타일
		p_msgInput.setLayout(new FlowLayout());
		p_msgInput.setPreferredSize(new Dimension(280, 20));
		p_msgInput.setBackground(new Color(213, 229, 242));
		
		//메시지 띄우는 패널 스타일 적용
		p_content.setPreferredSize(new Dimension(280, 430));
		p_content.setBackground(new Color(197, 224, 180));
		
		//p_main 스타일 적용, 붙이기
		p_main.setPreferredSize(new Dimension(300, 450));
		p_main.setBackground(new Color(226, 240, 217));
		p_main.setLayout(new BorderLayout());
		p_main.add(p_content);
		p_main.add(p_msgInput, BorderLayout.SOUTH);
		
		this.add(p_main);
		this.setVisible(true);
		
		
	}
	
	public static void main(String[] args) {
		new ChatRoomPage();
	}
	
}
