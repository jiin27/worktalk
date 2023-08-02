package worktalk.main;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/* worktalk 로그인 후 메인 페이지- DB 연동해 사원 출력(프로필, 부서-직급-이름, 상태 출력), 부서별 화면 전환, 하단 상태변경 버튼, 스케줄러 버튼, 검색창,  */
public class HomePage extends JFrame{
	JTextField t_search; // 상단 검색바
	JPanel p_user; //user의 계정 상태 보이기
	JPanel p_center;
	JPanel p_finance; //회계부
	JLabel la_f;
	JPanel p_sales; //영업부
	JLabel la_s;
	JPanel p_administration; //총무부
	JLabel la_a;
	JPanel p_humanresource; //인사부
	JLabel la_hs;
	JPanel p_marketing; //마케팅부
	JLabel la_m;
	
	public HomePage() {
		t_search = new JTextField();
		p_user = new JPanel();
		p_finance = new JPanel();
		la_f = new JLabel();
		p_sales = new JPanel();
		la_s = new JLabel();
		p_administration = new JPanel();
		la_a = new JLabel();
		p_humanresource = new JPanel();
		
		
		setLayout(new BorderLayout());
		setBackground(Color.PINK);
	}
	
	public static void main(String[] args) {
		new HomePage();
	}
}
