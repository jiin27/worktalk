package org.sp.worktalk.view.chat;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JFrame;

import org.sp.worktalk.domain.Employee;
import org.sp.worktalk.view.schedule.ScheduleAddPage;
import org.sp.worktalk.view.schedule.ScheduleHomePage;

public class Main extends JFrame{
	Page[] pages;
	public static final int LOGIN = 0;
	public static final int CHANGEPASS = 1;
	public static final int HOME = 2;
	public static final int SCHEDULE = 3;
	public static final int SCHEDULEADD = 4;
	public static final int PROFILE = 5;
	
	
	public static Employee employeeDTO; //처음에는 널이지만 로그인 후에는 계속 접속한 한명의  유저의 정보만 담고있다
	
	
	
	public Main() {
		
		pages = new Page[6];
		pages[0] = new LoginPage(this);
		pages[1] = new ChangePassPage(this);
		pages[2] = new HomePage(this);
		pages[3] = new ScheduleHomePage(this);
		pages[4] = new ScheduleAddPage(this);
		pages[5] = new ProfilePage(this);
	

		setLayout(new FlowLayout());
		
		
		for(int i=0;i<pages.length;i++) {
			//System.out.println("i="+i+"번째 "+pages[i]);
			this.add(pages[i]);
		
		}

		setSize(new Dimension(300,500));
		setTitle("Worltalk");
		setLocation(1050,200);
		setVisible(true);
		setResizable(false); 
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		showHide(LOGIN); //디자인확인용으로 잠시 막아둠
		
		
	}
	
	//화면 전환을 위한 메서드(로그인과 회원가입페이지 화면전환용)
	public void showHide(int n) {
		for(int i=0;i<pages.length;i++) {			
			if(i==n) {
				pages[i].setVisible(true);	
			}else {
				pages[i].setVisible(false);			
			}
		}
	}

	
	public static void main(String[] args) {
		new Main();

	}

}
