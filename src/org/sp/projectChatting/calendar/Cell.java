package org.sp.projectChatting.calendar;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

//달력에 칸칸이 나위는 셀의 최상위 객체
public class Cell  extends JPanel{
	JLabel la_title;
	
	public Cell(Color color) {
		
		
		la_title =  new JLabel();
		la_title.setPreferredSize(new Dimension(13,17));
		la_title.setFont(new Font("dialog", Font.BOLD, 10));
		
		//setSize(23, 28);
		setBackground(color);
		
		add(la_title);
	}
	
	public void setTitle(String title) {
		la_title.setText(title);
	}
	
}
