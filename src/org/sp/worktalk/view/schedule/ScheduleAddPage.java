package org.sp.worktalk.view.schedule;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.sp.worktalk.view.calendar.CalendarFrame;
import org.sp.worktalk.view.chat.Main;
import org.sp.worktalk.view.chat.Page;

public class ScheduleAddPage extends Page {
	Main main;
	JPanel p_main;//디자인을 위해 두페널을 감싼 페널
	JPanel p_north; //상단에 이름과 스케출추가버튼을 담을 페널
	JPanel p_center; // 모든 컴포넌트가 들어간 패널
	JPanel p_title;
	JPanel p_detail;
	JPanel p_addFile;
	JPanel p_date;
	JPanel p_start;
	JPanel p_end;
	
	JLabel la_name; //부서이름과 직원이름표기
	JButton bt_add; //누르면 스케줄추가창으로 화면전환
	JButton bt_back; //누르면 스케줄추가창으로 화면전환
	JLabel la_title;
	JTextField txt_title;
	JLabel la_detail;
	JTextArea txt_detail;
	JScrollPane scroll;
	JButton bt_file;
	JButton bt_img;
	JLabel la_start;
	JLabel la_end;
	JLabel la_startDate;
	JLabel la_endDate;
	JLabel la_startTime;
	JLabel la_endTime;
	
	

	
	public ScheduleAddPage(Main main) {
		this.main = main;
		p_main =  new JPanel();
		p_north = new JPanel();
		p_center = new JPanel();
		p_title = new JPanel();
		p_detail = new JPanel();
		p_addFile = new JPanel();
		p_date = new JPanel();  
		la_name = new JLabel("회계 1팀 박지인");
		bt_add = new JButton("+"); 
		bt_back = new JButton("x"); 
		la_title = new JLabel("제목: ");
		txt_title = new JTextField("제목을 입력하세요");
		la_detail = new JLabel("내용:");
		txt_detail = new JTextArea("설명을 입력하세요 (옵션)");
		scroll = new JScrollPane(txt_detail);
		bt_file = new JButton("파일이미지");
		bt_img = new JButton("사진이미지");
		la_start = new JLabel("날짜 : ");
		//la_end = new JLabel("종료일:"); 
		p_start = new JPanel();
		p_end = new JPanel();
		la_startDate = new JLabel("2023/07/28");
		la_endDate = new JLabel("2023/07/28");
		la_startTime = new JLabel("09:00");
		la_endTime = new JLabel("10:00");

	
		
		//디자인과 사이즈 
		Dimension d2 = new Dimension(40,40);
		Dimension d3 = new Dimension(200,40);
		Dimension d4 = new Dimension(100,60);
		Dimension d5 = new Dimension(45,40);
		Dimension d6 = new Dimension(190,40);

		p_main.setPreferredSize(new Dimension(270,450));
		p_north.setPreferredSize(new Dimension(250,50));
		p_center.setPreferredSize(new Dimension(250,1000));
		
		
		la_name.setPreferredSize(new Dimension(150,40));
		//bt_add.setPreferredSize(new Dimension(50,30));
		
		la_title.setPreferredSize(d2);
		la_detail.setPreferredSize(d2);
		txt_title.setPreferredSize(d3);
		txt_detail.setPreferredSize(new Dimension(200,150));
		scroll.setPreferredSize(new Dimension(200,80));
		
		bt_file.setPreferredSize(d4);
		bt_img.setPreferredSize(d4);
		
		p_date.setPreferredSize(new Dimension(290,60));
		la_start.setPreferredSize(d5);
		//la_end.setPreferredSize(d5);
		p_start.setPreferredSize(d6);
		p_end.setPreferredSize(d6);

		
		p_main.setBackground(Color.WHITE);
		p_north.setBackground(new Color(230,242,223));
		p_center.setBackground(Color.WHITE);
		p_addFile.setBackground(Color.WHITE);
		p_start.setBackground(Color.WHITE);
		p_end.setBackground(Color.WHITE);
		
		
		//붙이기
		p_title.add(la_title);
		p_title.add(txt_title);
		
		p_detail.add(la_detail);
		p_detail.add(scroll);
		
		p_addFile.add(bt_file);
		p_addFile.add(bt_img);
		
		p_north.add(la_name);
		p_north.add(bt_add);
		p_north.add(bt_back);
		
		p_center.add(p_title);
		p_center.add(p_detail);
		p_center.add(p_addFile);
		p_start.add(la_startDate);
		p_start.add(la_startTime);
		p_end.add(la_endDate);
		p_end.add(la_endTime);
		p_date.add(la_start);
		p_date.add(p_start);
		//p_date.add(la_end);
		//p_date.add(p_end);
		p_center.add(p_date);
		
		p_main.add(p_north);
		p_main.add(p_center);
		
		add(p_main);
		
		//시작일과 종료일 패널을 클릭하면 날짜를 선택할수 있는 팝업창이 뜬다
		p_start.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				CalendarFrame calendarFrame = new CalendarFrame();
			}
		});
		p_end.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				JOptionPane.showMessageDialog(ScheduleAddPage.this, "달력팝업창띄우기");
			}
		});
		
		bt_add.addActionListener(new ActionListener() {
			//누르면 데이터가 저장이 됟고 스케줄 홈화면으로 전환된다
			//일정이 정상적을 등록되야지만 넘어가야한다.
			public void actionPerformed(ActionEvent e) {
				main.showHide(main.SCHEDULE);
				
			}
		});
		bt_back.addActionListener(new ActionListener() {
			//누르면 데이터가 저장이 됟고 스케줄 홈화면으로 전환된다
			//일정이 정상적을 등록되야지만 넘어가야한다.
			public void actionPerformed(ActionEvent e) {
				main.showHide(main.HOME);
				
			}
		});
	}
	
	public void showUser() {
		//현재 접속한 유저의 부서 지급 이름 순서로 나열해야한다.
		
		String deptname = main.employeeDTO.getDeptDTO().getDname();
		String job = main.employeeDTO.getJob();
		String name = main.employeeDTO.getName();
		la_name.setText(deptname+" "+job+" "+name);
	}
}
