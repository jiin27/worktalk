package org.sp.worktalk.view.chat;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.sp.worktalk.domain.Employee;
import org.sp.worktalk.model.EmployeeDAO;
import org.sp.worktalk.util.DBManager;
import org.sp.worktalk.util.ImageUtil;

//패널하나에 오롯이 한 직원의 정보만 포함한다
//클릭하면 직원의 상세 페이지로 전환된다.
public class EmpPanel extends JPanel{
	Main main;
	JLabel photo; //사람사진이 들어갈 패널
	JPanel p_dname,p_job,p_name;
	JLabel la_dname; //부서이름이 적힐 라벨
	JLabel la_job;//직급이 적힐 라벨
	JLabel la_name;//이름이 적힐 라벨
	JLabel icon; //상태 아이콘이 보여질 라벨
	Employee employeeDTO;
	
	public EmpPanel(Main main, Employee employeeDTO) {
		this.main=main;
		this.employeeDTO=employeeDTO;
		
		photo = new JLabel();
		la_dname = new JLabel();
		la_job = new JLabel();
		la_name = new JLabel();
		icon = new JLabel();
		p_dname = new JPanel();
		p_job = new JPanel();
		p_name = new JPanel();
		
		
		
		photo.setPreferredSize(new Dimension(30,30));
		icon.setPreferredSize(new Dimension(30,30));
		createIcon();
		
		p_dname.setPreferredSize(new Dimension(55,30));
		p_job.setPreferredSize(new Dimension(55,30));
		p_name.setPreferredSize(new Dimension(55,30));
		
		la_dname.setBackground(Color.WHITE);
		la_job.setBackground(Color.WHITE);
		la_name.setBackground(Color.WHITE);
		
		la_dname.setText(employeeDTO.getDeptDTO().getDname());
		la_job.setText(employeeDTO.getJob());
		la_name.setText(employeeDTO.getName());
		

		p_dname.add(la_dname);
		p_job.add(la_job);
		p_name.add(la_name);
		
		
		add(photo);
		add(p_dname);
		add(p_job);
		add(p_name);
		add(icon);
		
		
		setPreferredSize(new Dimension(250,40));
		setBackground(new Color(230,242,223));
		
		
		addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				//페이지 전환 (혜령이가 만든 상세 페이지 넣어야함)
				//다른 곳으로 가야할까??
				
				//System.out.println("지금 선택한 사원번호는 "+employeeDTO.getEmpno());
				ProfilePage profilePage=(ProfilePage)main.pages[Main.PROFILE];
				profilePage.emp=employeeDTO; //프로필 페이지에 현재 사원 정보 DTO 전달 
				profilePage.printInfo(); //화면에 출력 
				
				main.showHide(main.PROFILE);
			}
		});
		

	}
	
	public void createIcon() {
			
			
			photo = new JLabel(new ImageIcon(ImageUtil.getImage("res/avatar.png", 30, 30)));
			icon = new JLabel(new ImageIcon(ImageUtil.getImage("res/pause.png", 30, 30)));
	}
	
	public void setTitle(String dname, String job, String name) {
		la_dname.setText(dname);
		la_job.setText(job);
		la_name.setText(name);
	}
}
