package org.sp.worktalk.view.chat;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.Socket;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

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
	
	Socket socket;
	ChatThread cht;

	
	public ChatRoomPage() {
		p_main = new JPanel();
		
		//아이콘
		try {
			BufferedImage buffimg_back = ImageIO.read(new File("res/back.png"));
			BufferedImage buffimg_search = ImageIO.read(new File("res/search.png"));
			BufferedImage buffimg_plus = ImageIO.read(new File("res/plus.png"));
			BufferedImage buffimg_smile = ImageIO.read(new File("res/smile.png"));
			BufferedImage buffimg_send = ImageIO.read(new File("res/ok.png"));
			BufferedImage buffimg_sender = ImageIO.read(new File("res/profile_w.png"));//프로필 계정 아이콘			
			BufferedImage buffimg_minus = ImageIO.read(new File("res/minus.png"));			
			BufferedImage buffimg_file = ImageIO.read(new File("res/clip.png"));			
			BufferedImage buffimg_img = ImageIO.read(new File("res/image.png"));			
			
			Image imgBack=buffimg_back.getScaledInstance(25, 25, Image.SCALE_SMOOTH);
			Image imgSearch=buffimg_search.getScaledInstance(25, 25, Image.SCALE_SMOOTH);
			Image imgPlus=buffimg_plus.getScaledInstance(25, 25, Image.SCALE_SMOOTH);
			Image imgSmile=buffimg_smile.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
			Image imgSend=buffimg_send.getScaledInstance(25, 25, Image.SCALE_SMOOTH);
			Image imgSender=buffimg_sender.getScaledInstance(25, 25, Image.SCALE_SMOOTH);
			Image imgMinus=buffimg_minus.getScaledInstance(25, 25, Image.SCALE_SMOOTH);
			Image imgClip=buffimg_file.getScaledInstance(35, 35, Image.SCALE_SMOOTH);
			Image img=buffimg_img.getScaledInstance(35, 35, Image.SCALE_SMOOTH);
			
			ic_back = new ImageIcon(imgBack);
			ic_search = new ImageIcon(imgSearch);
			ic_plus = new ImageIcon(imgPlus);
			ic_smile = new ImageIcon(imgSmile);
			ic_send = new ImageIcon(imgSend);
			
			ic_profile = new ImageIcon(imgSender);
			
			ic_minus = new ImageIcon(imgMinus);
			ic_clip = new ImageIcon(imgClip);
			ic_img = new ImageIcon(img);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
				
		//라벨에 아이콘이미지 붙이기
		la_back = new JLabel();
		la_back.setIcon(ic_back);
		la_search = new JLabel();
		la_search.setIcon(ic_search);
		la_plus = new JLabel();
		la_plus.setIcon(ic_plus);
		la_smile = new JLabel();
		la_smile.setIcon(ic_smile);
		la_send = new JLabel();
		la_send.setIcon(ic_send);
		
		//메시지 주고 받는 중심 패널
		p_content = new JPanel();
		p_sender = new JPanel();
		p_sender.setLayout(new BorderLayout());
		p_sender.setPreferredSize(new Dimension(270, 30)); //
		p_sender.setBackground(new Color(0, 0, 0, 0));
		la_profile = new JLabel();
		la_profile.setIcon(ic_profile);
		la_profile.setPreferredSize(new Dimension(30, 30));
		la_name = new JLabel("회계 1팀 사원 박지인");
		la_name.setFont(new Font("SansSerif", Font.PLAIN, 11));
		la_msg = new JLabel("보고서 제출 부탁드립니다.");
		JPanel p_profile=new JPanel(); //이름과 메시지를 묶을 패널
		p_profile.setBackground(new Color(0, 0, 0, 0));
		p_profile.setLayout(new BorderLayout());
		p_profile.add(la_name, BorderLayout.NORTH);
		p_profile.add(la_msg);
		p_sender.add(la_profile, BorderLayout.WEST);
		p_sender.add(p_profile);
		
		//상단 패널_ 검색창, 뒤로가기 버튼
		p_top = new JPanel();
		t_search = new JTextField(20);
		t_search.setBackground(new Color(242, 242, 242));
		p_top.setLayout(new FlowLayout());
		p_top.setPreferredSize(new Dimension(300, 30));
		p_top.setBackground(new Color(0, 0, 0, 0));
		p_top.add(la_back);
		p_top.add(t_search);
		p_top.add(la_search);
		
		//메시지 입력창 
		p_msgInput = new JPanel();
		t_input = new JTextField(20);
		
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
		p_content.setPreferredSize(new Dimension(270, 450));
		p_content.setBackground(new Color(197, 224, 180));
		p_content.add(p_top);
		p_content.add(p_sender);
		scroll = new JScrollPane(p_content);
		
		p_main.add(scroll);
		
		setSize(300, 500);
		setLayout(new BorderLayout());
		add(p_main);
		//add(p_sending, BorderLayout.SOUTH);
		add(p_msgInput, BorderLayout.SOUTH);
		setVisible(true);
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
	}	
	
	public void send() {
		String msg=t_input.getText();
		cht.sendMsg(msg);
		
		t_input.setText("");
	}
	
	public void createMSGPanel() {
		la_msg.setText(getTitle());
	}
	
	public static void main(String[] args) {
		new ChatRoomPage();
	}
	
}
