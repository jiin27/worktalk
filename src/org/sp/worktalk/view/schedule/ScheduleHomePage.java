package org.sp.worktalk.view.schedule;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import org.sp.worktalk.view.chat.Main;
import org.sp.worktalk.view.chat.Page;

//특정직원 (선택된)의 스케줄을 볼수있는 페널 (내가 될수도 남이 될수도 있음)
public class ScheduleHomePage extends Page{
	JPanel p_main;//디자인을 위해 두페널을 감싼 페널
	JPanel p_north; //상단에 이름과 스케출추가버튼을 담을 페널
	JPanel p_center; // 모든 컴포넌트가 들어간 패널
	JScrollPane scroll;
	JLabel la_name; //부서이름과 직원이름표기
	JButton bt_add; //누르면 스케줄추가창으로 화면전환
	PlanPanel[] plans;
	
	
	Main main;
	
	
	public ScheduleHomePage(Main main) {
		this.main = main;
		p_main =  new JPanel();
		p_north = new JPanel();
		p_center = new JPanel();
		scroll = new JScrollPane(p_center);
		la_name = new JLabel("회계 1팀 박지인");
		bt_add = new JButton("+"); //이미지로대체해야함
		
		
		
		
		
		//디자인과 사이즈조절
		p_main.setPreferredSize(new Dimension(270,450));
		p_north.setPreferredSize(new Dimension(250,50));
		p_center.setPreferredSize(new Dimension(250,1000));
		scroll.setPreferredSize(new Dimension(250,370));
		la_name.setPreferredSize(new Dimension(210,40));
		bt_add.setPreferredSize(new Dimension(30,30));
		
		
		p_main.setBackground(Color.WHITE);
		p_north.setBackground(new Color(230,242,223));
		p_center.setBackground(Color.WHITE);
		
		
		//붙이기
		p_north.add(la_name);
		p_north.add(bt_add);
		p_main.add(p_north);
		p_main.add(scroll);
		
		//와일문으로 테이블의 모든 결과가 나올대까지 돌려야함
		plans = new PlanPanel[3];
		plans[0] = new PlanPanel();
		plans[1] = new PlanPanel();
		plans[2] = new PlanPanel();
		p_center.add(plans[0]); //일단 임시 방편
		p_center.add(plans[1]); ////일단 임시 방편
		p_center.add(plans[2]);
		//여기까지 while(rs.next())문
		
		add(p_main);
		
		//버튼에 화면전환 기능 넣기
		bt_add.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				System.out.println("누르면 화면전환");
			}
		});
		
		
		
		
	}
	

	public void showUser() {
		//현재 접속한 유저의 부서 지급 이름 순서로 나열해야한다.
		System.out.println("접근근 테스트 "+main.employeeDTO.getName());
		la_name.setText(main.employeeDTO.getName());
	}
	
}
