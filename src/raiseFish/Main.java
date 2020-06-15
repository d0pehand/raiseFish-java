package raiseFish;

import javax.swing.JFrame;

public class Main {

	public static final int SCREEN_WIDTH = 800; // 가로 창 크기
	public static final int SCREEN_HEIGHT = 600; // 세로 창 크기

	public static void main(String[] args) {
		// 프레임 생성
		JFrame obj = new JFrame();

		// 패널 생성
		Game game = new Game();

		obj.setBounds(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);// 여기에 이 사이즈로
		obj.setTitle("물고기 키우기 -SJ"); // 제목
		obj.setResizable(false); // 크기변경 불가
		obj.setVisible(true); // 보이게
		obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 나갈때 리소스 정리
		obj.add(game); // 씬을 넣는다.
	}

}