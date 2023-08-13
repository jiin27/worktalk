package org.sp.worktalk.view.chat;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.sp.worktalk.domain.Employee;
import org.sp.worktalk.util.ImageUtil;
import org.sp.worktalk.view.schedule.ScheduleHomePage;

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
	Employee emp;
	
	ChatRoomPage chatRoomPage;

	
	public ProfilePage(Main main) {
		this.main = main;
		p_main=new JPanel();
		la_exit=new ImageIcon(ImageUtil.getImage("res/back.png", 25,25));
		icon1=new JLabel(la_exit);
		la_title=new JLabel("송파여성인력개발센터");
		la_status=new ImageIcon(ImageUtil.getImage("res/play.png", 25, 25));
		icon2=new JLabel(la_status);
		
		la_team=new JLabel("회계1팀",JLabel.CENTER);
		la_name=new JLabel("사원 최승아",JLabel.CENTER);
		userImage=new ImageIcon(ImageUtil.getImage("res/profile_w.png", 50, 50));
		
		icon3=new JLabel(userImage);
		la_empno=new JLabel("사원번호",JLabel.LEFT);
		la_empnoInfo=new JLabel("230712",JLabel.RIGHT);
		la_email=new JLabel("이메일",JLabel.CENTER);
		la_emailInfo=new JLabel("dunkin14@sp.or.kr",JLabel.RIGHT);
		
		chat=new ImageIcon(ImageUtil.getImage("res/talk.png", 50, 50));
		icon4=new JLabel(chat);
		schedule=new ImageIcon(ImageUtil.getImage("res/calendar.png", 50, 50));
		icon5=new JLabel(schedule);
		password=new ImageIcon(ImageUtil.getImage("res/lock.png", 50, 50)); 
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
		
		icon1.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				main.showHide(main.HOME);
			}
		});

		icon4.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				chatRoomPage.setVisible(true);
				new ChatRoomPage();
			}
		});
		
		icon5.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				
				//현재 내가 보유한 DTO 를 스케줄 페이지에 전달 
				ScheduleHomePage scheduleHomePage=(ScheduleHomePage)main.pages[Main.SCHEDULE];
				scheduleHomePage.showUser(emp);
				
				main.showHide(main.SCHEDULE);
			}
		});
		
		icon6.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				main.showHide(main.CHANGEPASS);
			}
		});
		this.add(p_main);
		this.setSize(300,450);
	}
	
	//화면에 1사원의 정보 출력 
	public void printInfo() {
		la_team.setText(emp.getDeptDTO().getDname());
		la_name.setText(emp.getName());
		la_empno.setText(emp.getJob());
		la_empnoInfo.setText(Integer.toString(emp.getEmpno()));
		la_emailInfo.setText(emp.getEmail());
	}

}
