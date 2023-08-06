package org.sp.worktalk.view.chat;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ProfilePage extends Page{
	Main main;
	JPanel p_main;
	
	ImageIcon la_exit;
	JLabel icon1;
	JLabel la_title;
	ImageIcon la_status;
	JLabel icon2;
	
	JLabel la_team;
	JLabel la_name;
	ImageIcon userImage;
	JLabel icon3;
	JLabel la_empno;
	JLabel la_empnoInfo;
	JLabel la_email;
	JLabel la_emailInfo;
	
	ImageIcon chat;
	JLabel icon4;
	ImageIcon schedule;
	JLabel icon5;
	ImageIcon password;
	JLabel icon6;
	
	JPanel p_north;
	JPanel p_center;
	JPanel p_south;
	
	public ProfilePage(Main main) {
		this.main = main;
		p_main=new JPanel();
		la_exit=new ImageIcon("res/back.png");
		icon1=new JLabel(la_exit);
		la_title=new JLabel("송파여성인력개발센터");
		la_status=new ImageIcon("res/play.png");
		icon2=new JLabel(la_status);
		
		la_team=new JLabel("회계1팀",JLabel.CENTER);
		la_name=new JLabel("사원 최승아",JLabel.CENTER);
		userImage=new ImageIcon("res/profile_w.png");
		icon3=new JLabel(userImage);
		la_empno=new JLabel("사원번호",JLabel.LEFT);
		la_empnoInfo=new JLabel("230712",JLabel.RIGHT);
		la_email=new JLabel("이메일",JLabel.CENTER);
		la_emailInfo=new JLabel("dunkin14@sp.or.kr",JLabel.RIGHT);
		
		chat=new ImageIcon("res/talk.png");
		icon4=new JLabel(chat);
		schedule=new ImageIcon("res/calendar.png");
		icon5=new JLabel(schedule);
		password=new ImageIcon("res/lock.png"); 
		icon6=new JLabel(password);
		
		p_north=new JPanel();
		p_center=new JPanel();
		p_south=new JPanel();
		
		
		p_main.setPreferredSize(new Dimension(300,450));
		p_north.setPreferredSize(new Dimension(270,50));
		p_center.setPreferredSize(new Dimension(270,250));
		p_south.setPreferredSize(new Dimension(270,100));
		

		
		la_team.setPreferredSize(new Dimension(270,20));
		la_name.setPreferredSize(new Dimension(270,20));
		icon3.setPreferredSize(new Dimension(270,100));
		la_empno.setPreferredSize(new Dimension(135,20));
		la_email.setPreferredSize(new Dimension(135,20));
		
		icon4.setPreferredSize(new Dimension(80,80));
		icon5.setPreferredSize(new Dimension(80,80));
		icon6.setPreferredSize(new Dimension(80,80));
		
		p_north.setBackground(new Color(226,240,217));
		p_center.setBackground(new Color(226,240,217));
		p_south.setBackground(new Color(226,240,217));
		
		
		
		p_north.add(icon1);
		p_north.add(la_title);
		p_north.add(icon2);
		
	
		p_center.add(la_team);
		p_center.add(la_name);
		p_center.add(icon3);
		p_center.add(la_empno);
		p_center.add(la_empnoInfo);
		p_center.add(la_email);
		p_center.add(la_emailInfo);
		
		p_south.add(icon4);
		p_south.add(icon5);
		p_south.add(icon6);
		
		p_main.add(p_north,BorderLayout.NORTH);
		p_main.add(p_center,BorderLayout.CENTER);
		p_main.add(p_south,BorderLayout.SOUTH);
	
		
		

		icon6.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				main.showHide(main.CHANGEPASS);
			}
		});
		
		
		

		
		this.add(p_main);
		this.setSize(300,450);


		
		

		
	}
	

}
