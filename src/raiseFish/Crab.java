package raiseFish;

public class Crab extends Thread { // ���̾Ƹ� ������ �ִ� �ɰ�
	int x;
	int xDir;
	int coin;
	boolean isCoinHave;
	boolean isLive;

	Crab() {
		x = (int) (Math.random() * (Main.SCREEN_WIDTH - 200) + 75); // �ʱ� ��ġ

		xDir = (int) (Math.random() * 2 + 1); // �ʱ� �̵����� �ӵ�
		if (((int) (Math.random() * 2 + 1)) == 2) {
			xDir = -xDir;
		}

		coin = 0;
		isCoinHave = false;
		isLive = true;
		this.start();
	}

	public void move() { // ������

		if (x < 20) { // �ε�ġ�� ������ �ٲ۴�
			xDir = -xDir;
		} else if (x > Main.SCREEN_WIDTH - 60) {
			xDir = -xDir;
		} else {
			int r = (int) (Math.random() * 100 + 1); // 1 ~ 100
			if (r < 3) { // 10���� Ȯ��
				xDir = -(int) (Math.random() * 2 + 1); // �ӵ� ����
			}
		}
		x += xDir;
	}

	@Override
	public void run() {
		while (isLive) {
			move();
			coin++;	
			if (coin > 1000) { // 10�� ���� ���̾� ����
				isCoinHave = true;
			}
			try {
				sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}