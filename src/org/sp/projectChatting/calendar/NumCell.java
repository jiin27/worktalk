package org.sp.projectChatting.calendar;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class NumCell extends Cell{

	public NumCell(Color color) {
		super(color);
		
		//마우스 클릭이벤트 구현
		this.addMouseListener(new MouseAdapter() {
			//셀을 클릭하면 셀 색이 변하고 하단에 있는 라벨에 날짜가 기록된다 
			public void mouseClicked(MouseEvent e) {
				//셀색은 오직 하나만 색생이 변할수 있다.
				//선택한 셀 이외에는 무조건 디폴트 색을 유지해야한다.
				//그럴려면 배열로 담아야 한다. 그럼 여기서아니라 프레임에서
				//이벤트를 구현해야하는걸까?
				Object obj = e.getSource();
				
				setBackground(Color.RED);
				//선택한 셀의 날짜 정보를 하단에 라벨에 기록되어야 한다
			}
		});
	}

	

}
