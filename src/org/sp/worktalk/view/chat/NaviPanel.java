package org.sp.worktalk.view.chat;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.JPanel;

import org.sp.worktalk.domain.Dept;

//부서이름을 가지고 있는 네이바패널 클릭하면 하단의 리스트 배널이 변환된다
public class NaviPanel extends JPanel{
	JLabel la_title;
	HomePage homePage;
	Dept deptDTO;
	
	public NaviPanel( HomePage homePage, Dept deptDTO) {
		this.homePage = homePage; 
		this.deptDTO = deptDTO;
		la_title = new JLabel();
		la_title.setText(deptDTO.getDname());

		add(la_title);
		setPreferredSize(new Dimension(48,30));
		setBackground(Color.WHITE);
		

		addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				//Object obj = e.getSource();
				homePage.listPanel.removeAll();
				
				//여기도 에러 널포인트..deptDTO 값이 없음..왜지?
				homePage.createEmpList(deptDTO.getDeptno());

			}
			public void mouseEntered(MouseEvent e) {
				setBackground(new Color(230,242,223));
			}
			public void mouseExited(MouseEvent e) {
				setBackground(Color.WHITE);
			}
		});
	}
	
	
}
