package org.sp.projectChatting.main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import org.sp.projectChatting.DTO.EmployeeDTO;

public class ChangePassPage extends Page {
	JPanel p_main;//디자인을 위해 모든 페널을 담은 메인페널
	JPanel p_north; //앱이름 넣기위한 패널
	JPanel p_center; //실질적으로 모든 컴포넌트틀을 담고 있는 패널
	JPanel p_south; //비밀번호 변경 라벨을 넣을 패널
	
	JLabel space; //오롯이 공백을 위한 디자인을 위한 기능(임시방편)
	JLabel la_title; //앱이름
	JLabel space2; //오롯이 공백을 위한 디자인을 위한 기능(임시방편)
	JLabel la_id;
	JLabel la_pass;
	JLabel la_newPass;
	JLabel la_passCheck;
	JTextField t_id;
	JPasswordField t_pass;
	JPasswordField t_newPass;
	JPasswordField t_passCheck;
	
	JButton bt_passChage;
	Main main;

	
	
	
	
	public ChangePassPage(Main main) {
		this.main = main;
		p_main = new JPanel();
		p_north = new JPanel();
		p_center = new JPanel();
		p_south = new JPanel();
		
		space = new JLabel("");
		la_title = new JLabel("비밀번호 변경");
		space2 = new JLabel(""); 
		la_id = new JLabel("사용자 아이디");
		la_pass = new JLabel("기존 비밀번호");
		la_newPass = new JLabel("새   비밀번호");
		la_passCheck = new JLabel("비밀번호 확인");
		t_id = new JTextField();
		t_pass = new JPasswordField();
		t_newPass = new JPasswordField();
		t_passCheck = new JPasswordField();
		
		bt_passChage = new JButton("비밀번호 변경");



		
		//사이즈랑 디자인(색상) 조절
		Dimension d1 = new Dimension(80,15);
		Dimension d2 = new Dimension(170,30);
		Color c1 = new Color(68,84,106);
		Font f1 = new Font("dialog", Font.BOLD, 30);
		Font f2 = new Font("dialog", Font.BOLD, 18);
		
		space.setPreferredSize(new Dimension(270,20));
		p_main.setPreferredSize(new Dimension(270,470));
		p_north.setPreferredSize(new Dimension(270,80));
		p_center.setPreferredSize(new Dimension(270,350));
		p_south.setPreferredSize(new Dimension(270,80));
		space2.setPreferredSize(new Dimension(270,40));

		la_id.setPreferredSize(d1);
		la_pass.setPreferredSize(d1);
		la_newPass.setPreferredSize(d1);
		la_passCheck.setPreferredSize(d1);
		t_id.setPreferredSize(d2);
		t_pass.setPreferredSize(d2);
		t_newPass.setPreferredSize(d2);
		t_passCheck.setPreferredSize(d2);
		
		bt_passChage.setPreferredSize(new Dimension(100,30));
		

		la_title.setFont(f1);

		
		la_title.setForeground(c1);
		la_id.setForeground(c1);
		la_pass.setForeground(c1);
		la_newPass.setForeground(c1);
		la_passCheck.setForeground(c1);
		bt_passChage.setBorder(new LineBorder(c1,1,false));

		
		p_main.setBackground(null);
		p_north.setBackground(null);
		p_center.setBackground(Color.WHITE);
		p_south.setBackground(null);
		
		
		//컴포넌트 페널에 붙이기
		p_north.add(space);
		p_north.add(la_title);
		p_center.add(space);
		p_center.add(la_id);
		p_center.add(t_id);
		p_center.add(la_pass);
		p_center.add(t_pass);
		p_center.add(la_newPass);
		p_center.add(t_newPass);
		p_center.add(la_passCheck);
		p_center.add(t_passCheck);
		p_center.add(bt_passChage);

		
		//페널을 페이지에 붙이기
		p_main.add(p_north,BorderLayout.NORTH);
		p_main.add(p_center);
		p_main.add(p_south);
		add(p_main);
			
		bt_passChage.addMouseListener(new MouseAdapter() {
			
			public void mouseClicked(MouseEvent e) {
				changePass();
			}
		});

	}

	public void changePass() {
		//dao로 비밀번호 교체하고  성공과 실패 여부에 따라 팝업창을 띄우고 화면 전환필요
		String id =t_id.getText();
		char[] password = t_pass.getPassword();
		char[] newPassword = t_pass.getPassword();
		char[] passCheck = t_pass.getPassword();
		
		if(newPassword !=passCheck) {
			JOptionPane.showMessageDialog(this, "비밀번호가 다름니다");
		}else {
			EmployeeDTO dto = new EmployeeDTO(); //비어있는 DTO 준비
			dto.setEmail(id);
			//dto.setPass((String)password);
			
		}
		

		
	}
}