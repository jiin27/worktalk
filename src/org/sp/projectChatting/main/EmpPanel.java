package org.sp.projectChatting.main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.sp.projectChatting.DTO.EmployeeDTO;

//패널하나에 오롯이 한 직원의 정보만 포함한다
//클릭하면 직원의 상세 페이지로 전환된다.
public class EmpPanel extends JPanel{
	JLabel photo; //사람사진이 들어갈 패널
	JPanel p_dname,p_job,p_name;
	JLabel la_dname; //부서이름이 적힐 라벨
	JLabel la_job;//직급이 적힐 라벨
	JLabel la_name;//이름이 적힐 라벨
	JLabel icon; //상태 아이콘이 보여질 라벨
	EmployeeDTO employeeDTO;
	
	public EmpPanel(EmployeeDTO employeeDTO) {
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
				System.out.println("혜령이파트~");
			}
		});
		
	}
	
	public void createIcon() {
			BufferedImage buffImg;
			BufferedImage buffImg_icon;
			try {
				buffImg = ImageIO.read(new File("res/avatar.png"));
				buffImg_icon = ImageIO.read(new File("res/pause.png"));
				
				Image image = buffImg;
				Image image_icon = buffImg_icon;
				
				image = image.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
				image_icon = image_icon.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
				
				photo = new JLabel(new ImageIcon(image));
				icon = new JLabel(new ImageIcon(image_icon));

			} catch (IOException e) {

				e.printStackTrace();
			}
	}
	
	public void setTitle(String dname, String job, String name) {
		la_dname.setText(dname);
		la_job.setText(job);
		la_name.setText(name);
	}
}
