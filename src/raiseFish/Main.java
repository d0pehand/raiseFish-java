package raiseFish;

import javax.swing.JFrame;

public class Main {

	public static final int SCREEN_WIDTH = 800; // ���� â ũ��
	public static final int SCREEN_HEIGHT = 600; // ���� â ũ��

	public static void main(String[] args) {
		// ������ ����
		JFrame obj = new JFrame();

		// �г� ����
		Game game = new Game();

		obj.setBounds(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);// ���⿡ �� �������
		obj.setTitle("����� Ű��� -SJ"); // ����
		obj.setResizable(false); // ũ�⺯�� �Ұ�
		obj.setVisible(true); // ���̰�
		obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // ������ ���ҽ� ����
		obj.add(game); // ���� �ִ´�.
	}

}