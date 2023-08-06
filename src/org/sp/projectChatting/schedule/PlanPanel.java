package org.sp.projectChatting.schedule;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

//모든 스케줄 기록시 생성될 패널
public class PlanPanel extends JPanel{
	JPanel p_date;
	JPanel p_detail;
	JLabel la_date;
	JTextArea txt_detail;
	JScrollPane scroll;
	boolean flag=false;
	

	
	public PlanPanel() {
		p_date = new JPanel();
		p_detail = new JPanel();
		la_date = new JLabel();
		txt_detail = new JTextArea();
		scroll = new JScrollPane(txt_detail);

		//선택된직원의 스케줄 테이블을 불러와야한다.
		la_date.setText("7월 3일 월요일");
		txt_detail.setText("9시 미팅\n");

		
		
		//디자인
		p_date.setPreferredSize( new Dimension(240,30));
		p_detail.setPreferredSize( new Dimension(240,80));
		p_date.setBackground(null);
		//p_detail.setBackground(Color.BLUE);
		scroll.setBackground(null);
		
		scroll.setPreferredSize(new Dimension(220,70));
		
		la_date.setPreferredSize(new Dimension(260,20));
		la_date.setFont(new Font("dialog", Font.BOLD, 15));
		//la_date.setBackground(Color.WHITE);
		
		txt_detail.setPreferredSize(new Dimension(220,80));


		this.setLayout(new BorderLayout());
		
		
		//붙이기
		add(p_date, BorderLayout.NORTH);
		add(p_detail);
		p_date.add(la_date);
		p_detail.add(scroll);



		

		setBackground(null); //테스트용 나중에 지워야함.
		
		p_date.addMouseListener(new MouseAdapter() {
			//라벨을 클릭하면 하단에 창이 띄면서 상세스케줄이 보인다
			public void mouseClicked(MouseEvent e) {
				//누르면 숨어있던 상세스케줄기록이 보인다.
				showHideDetail();
				
			}
		
		}); 
	}
	
	//날짜 라벨을 누르면 디테일 페널이 보여졌다 안보여졌다 (접았다 폈다) 할수 있는 기능
	public void showHideDetail() {
		flag=!flag;
		System.out.println(flag);
		p_detail.setVisible(flag);
		
	}
}
