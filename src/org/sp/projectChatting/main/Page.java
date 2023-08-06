package org.sp.projectChatting.main;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

//모든 화면의 가장 상위 객체(화면 전환을 위한)
public class Page extends JPanel{

	
	public Page() {
		setBackground(new Color(230,242,223));		
		setPreferredSize(new Dimension(290,460));
		setVisible(true);
	  
	}
}
