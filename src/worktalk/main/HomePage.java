package worktalk.main;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/* worktalk 로그인 후 메인 페이지- DB 연동해 사원 출력, 부서별 화면 전환, 하단 상태변경 버튼, 스케줄러 버튼, 검색 기능 창, */
public class HomePage extends JFrame{
	JPanel p_center;
	JPanel p_finance;
	JLabel la_f;
	JPanel p_sales;
	JLabel la_s;
	JPanel p_administration;
	JLabel la_a;
	JPanel p_humanresource;
	JLabel la_hs;
	JPanel p_marketing;
	JLabel la_m;
	
	public HomePage() {
		
	}
	
	public static void main(String[] args) {
		new HomePage();
	}
}
