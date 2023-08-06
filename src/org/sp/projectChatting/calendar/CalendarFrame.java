package org.sp.projectChatting.calendar;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.sp.projectChatting.util.StringManager;

//스케줄 추가할때 날짜를 선정하기 위한 달력이 모양이 포함된 팝업창
public class CalendarFrame extends JFrame{
	JPanel p_main;// 모든 패널을 다 담을 가장 큰 랩퍼패널
	JPanel p_north;//화살표와 년월타이틀을 가질 상단 패널
	JPanel p_center;// 달력과 시간 셀렉트 박스를 가질센터 패널
	JPanel p_south;//선택한 결과물과  등록 버튼을 가질 하단 패널
	JPanel p_calendar;
	JPanel p_time;
	JButton bt_prev,bt_next,bt_regist;
	JLabel la_title, la_finalDate;
	JComboBox box;
	JLabel la_selecteDate;
	
	Calendar cal; //버튼에 의해 조잘될 날짜 객체
	
	String[] dayTitle = {"일","월","화","수","목","금","토"};
	NumCell[][] numCells = new NumCell[6][7];
	
	
	
	
	
	
	
	public CalendarFrame() {
		p_main = new JPanel();
		p_north = new JPanel();
		p_center = new JPanel();
		p_south = new JPanel();
		p_calendar = new JPanel();
		p_time = new JPanel();
		
		bt_prev = new JButton("◀");
		bt_next = new JButton("▶");
		la_title = new  JLabel("2023년 8월");
		box = new JComboBox();
		for(int i=8;i<20;i++) {
			box.addItem(i+" :00");
		}
		la_selecteDate = new JLabel("날짜와 시간을 선택해 주세요");
		bt_regist = new JButton("등록");
		cal = Calendar.getInstance(); //현재날짜디폴트로 객체 생성됨
		
		

		
		
		


		
		//디자인 사이즈
		p_main.setPreferredSize(new Dimension(380,380));
		p_north.setPreferredSize(new Dimension(360,50));
		p_center.setPreferredSize(new Dimension(360,250));
		p_south.setPreferredSize(new Dimension(360,50));
		
		p_calendar.setPreferredSize(new Dimension(200,240));
		p_time.setPreferredSize(new Dimension(140,240));
		

		la_title.setPreferredSize(new Dimension(75,40));
		box.setPreferredSize(new Dimension(130,20));
		la_selecteDate.setPreferredSize(new Dimension(280,40));
		bt_regist.setPreferredSize(new Dimension(70,40));
		
		la_title.setFont(new Font("arial", Font.BOLD, 20));
		

		
		p_main.setBackground(new Color(230,242,223));
		p_north.setBackground(Color.WHITE);
		p_center.setBackground(Color.WHITE);
		p_south.setBackground(Color.WHITE);
		
		
		//붙이기
		
		p_north.add(bt_prev);
		p_north.add(la_title);
		p_north.add(bt_next);
		
		p_time.add(box);
		
		p_center.add(p_calendar);
		p_center.add(p_time);
		
		p_south.add(la_selecteDate);
		p_south.add(bt_regist);
		
		p_main.add(p_north, BorderLayout.NORTH);
		p_main.add(p_center);
		p_main.add(p_south, BorderLayout.SOUTH);
		add(p_main);
		
		
		
		p_center.add(p_calendar);
		p_center.add(p_time);
		
		
		p_main.add(p_north, BorderLayout.NORTH);
		p_main.add(p_center);
		p_main.add(p_south, BorderLayout.CENTER);
		
		add(p_main);
		
		printTitle();
		createCell();
		printNum();
		
		setVisible(true);
		setSize(400,410);
		//setDefaultCloseOperation(EXIT_ON_CLOSE); 창만 닫는 걸로 수정해야함.
		
		bt_prev.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				showPrevMonth();
			}
		});
		bt_next.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				showNextMonth();
			}
		});
		
	}
	
	public void createCell() {
		//요일셀 반복문으로 만들기
		for(int i=0; i<dayTitle.length;i++) {
			DayCell dayCell = new DayCell(Color.LIGHT_GRAY);
			dayCell.setTitle(dayTitle[i]);
			p_calendar.add(dayCell);
		}
		//날짜셀 이중반복문으로 돌리기(7*6)
		for(int a=0; a<6;a++) {
			for(int i=0; i<7;i++) {
				NumCell numCell = new NumCell(Color.WHITE);
				numCell.setTitle("0");
				numCells[a][i]=numCell;
				p_calendar.add(numCell);
			}
		}
	}

	//현재 달력창의 년과 월을 보여주는 라벨
	public void printTitle() {
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH);
		
		la_title.setText(year+"-"+StringManager.getNumString(month+1));
	}
	
	//전달 보여주기
	public void showPrevMonth() {
		int mm= cal.get(Calendar.MONTH); //현재 달을구해서
		cal.set(Calendar.MONTH,mm-1); //조작
		printTitle();
		printNum();
	}
	
	//다음달 보여주기
	public void showNextMonth() {
		int mm= cal.get(Calendar.MONTH); //현재 달을구해서
		cal.set(Calendar.MONTH,mm+1); //조작
		printTitle();
		printNum();
	}
	
	//해당월의 시작 요일을 구해서 보내준다.
	public int getStartDayOfWeek() {
		//임으로 새로운 날짜 객체 하나를 생성해서 
		//해달월의 1일로 저작해서 그날이 무슨요일인지 찾아내자
		Calendar c= Calendar.getInstance();//조작용날짜객체
		int yy = cal.get(Calendar.YEAR); //현재 보고있는 년도를 가져와서 조작한다
		int mm= cal.get(Calendar.MONTH);//현재 보고있는 월을 가져와서 조작한다
		c.set(yy,mm,1);//임의 날짜객체에 라벨에 있는 년월을 넣어 조작한다
		int day = c.get(Calendar.DAY_OF_WEEK); // 조작된 날짜의 요일을 알아낸다
		
		return day;
	}
	
	//해당월이 몇일까지 있는지 알아내 보내준다
	public int getLastDateOfMonth() {
		//현재 보고있는 월의 다음월로 조작한후 그달의 0일이 몇일인지 알아낸다
		Calendar c= Calendar.getInstance();//조작용날짜객체
		int yy = cal.get(Calendar.YEAR); //현재 보고있는 년도를 가져와서 조작한다
		int mm= cal.get(Calendar.MONTH);//현재 보고있는 월을 가져와서 조작한다
		c.set(yy,mm+1,0);//임의 날짜객체에 라벨에 있는 년월을 넣어  0일을 조작한다
		int dd = c.get(Calendar.DATE); //0일이 언제인지 알아낸다
		
		return dd;
		
	}
	
	//달력에 날짜를 입력한다
	public void printNum() {
		int startDay = getStartDayOfWeek();
		int lastDate = getLastDateOfMonth();
		int count =0;//셀순번체크변수
		int num = 0;//진짜날짜변수
		
		for(int a=0;a<numCells.length;a++) {
			for(int i=0; i<numCells[a].length;i++ ) {				
				numCells[a][i].setTitle(""); //모든 셀을 꺠끗이 만든후
				count++;
				if(count>=startDay && num<lastDate) {
					num++;
					numCells[a][i].setTitle(Integer.toString(num)); //해당 범위에만 숫자를 프린트 한다
				}
			}
		}
	}
}


