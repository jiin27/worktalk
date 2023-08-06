package org.sp.projectChatting.main;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

//각기 다른 부서마다 다른 직원 목록을 가지고 있는 패널들의 최상위 패널
public class ListPanel extends JPanel{

	public ListPanel() {
		setPreferredSize(new Dimension(260,300));
		setBackground(Color.WHITE);
	}
}
