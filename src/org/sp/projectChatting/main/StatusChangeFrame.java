package org.sp.projectChatting.main;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class StatusChangeFrame extends JFrame{
	JPanel p_main;
	JRadioButton r_button;
	String[] imageList = {"1","2","3","4","5"};
	
	public StatusChangeFrame() {
		p_main = new JPanel();
		r_button = new JRadioButton();
		
		p_main.setPreferredSize(new Dimension(200,150));
		p_main.setBackground(new Color(230,242,223));
		
		createImages();
		createRButton();
		
		
		add(p_main);
		
		setBounds(1000, 500, 200, 150);
		setVisible(true);
		
	}
	
	public void createImages() {
		//for() {
			//이미지 4개 별열로된거 끌고와야함
		//}
	}
	public void createRButton() {
		for(int i=0;i<imageList.length;i++) {
			r_button = new JRadioButton();
			p_main.add(r_button);
			//선택된 라디오버튼의 결과는 홈페이지 나의 상태에 반영되어야 함으로
			//그에맞는 이벤트 구현이 되어얗 한다
		}
	}
}
