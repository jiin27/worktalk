package org.sp.worktalk.chat.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/* worktalk 로그인 후 메인 페이지- DB 연동해 사원 출력(프로필, 부서-직급-이름, 상태 출력), 부서별 화면 전환, 하단 상태변경 버튼, 스케줄러 버튼, 검색창  */
public class TalkMain extends JPanel{
	JPanel p_main;
	
	JPanel p_top; //검색바와 user 계정 함께 담을 컨테이너 패널
	JTextField t_search; // 상단 검색바
	JPanel p_user; //user의 계정 상태 보이기
	
	JPanel p_finance; //회계부
	JPanel p_sales; //영업부
	JPanel p_administration; //총무부
	JPanel p_humanresource; //인사부
	JPanel p_marketing; //마케팅부
	
	JPanel p_department; //부서 라벨 담을 컨테이너 패널
	JLabel la_f;
	JLabel la_s;
	JLabel la_a;
	JLabel la_hs;
	JLabel la_m;
	
	public TalkMain() {
		p_top = new JPanel();
		t_search = new JTextField();
		p_user = new JPanel();
		
		p_finance = new JPanel();
		p_sales = new JPanel();
		p_administration = new JPanel();
		p_humanresource = new JPanel();
		p_marketing = new JPanel();
		
		la_f = new JLabel();
		la_s = new JLabel();
		la_a = new JLabel();
		la_hs = new JLabel();
		la_m = new JLabel();
		
		//상단 패널에 검색바와 user 계정 넣기
		p_top.add(t_search);
		p_top.add(p_user);
		
		//부서 라벨 붙이기
		
		
		
		
		setLayout(new BorderLayout());
		setSize(new Dimension(400, 600));
		setBackground(new Color(226, 240, 217));
	}
	
	public static void main(String[] args) {
		new TalkMain();
	}
}
