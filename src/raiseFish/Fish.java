package raiseFish;

public class Fish extends Thread {

	int x, y, xDir, yDir, eat, level, hungry, coin;

	int goalReach = 0;
	int goalX = 0;
	int goalY = 0;

	boolean live;

	public Fish() {
		setPosition();
		eat = 0; // 2�� = 2�� �̻�, 3�� = 5�� �̻�
		level = 1;
		hungry = 2000; // 10�� ���� ����� //20�� �Ǹ� ����
		live = true;
		coin = 0;

		this.start();
	}

	public void setPosition() { // ����� ���� �� ��ġ ����
		int randomx = (int) (Math.random() * (Main.SCREEN_WIDTH - 25) + 5);
		int randomy = (int) (Math.random() * (Main.SCREEN_HEIGHT - 50) + 5);
		int randomxDir = (int) (Math.random() * 3 + 1); // 1 ~ 3
		int randomyDir = (int) (Math.random() * 1 + 1); // 1

		x = randomx / 2 + (Main.SCREEN_WIDTH - 25) / 4;
		y = randomy / 2 + (Main.SCREEN_HEIGHT - 50) / 4;
		xDir = randomxDir;
		yDir = randomyDir;
	}

	public void move() { // ��� �� ������

		// border (5,5) ~ (SCREEN_WIDTH - 25, SCREEN_HEIGHT - 50)

		if (x <= 5) { // �ε�ġ�� ������ �ٲٰ� y�ӵ� �ٽ� ����
			int randomyDir = (int) (Math.random() * 5 - 2); // -2 ~ 2

			xDir = -xDir;
			yDir = randomyDir;
		}
		if (x >= Main.SCREEN_WIDTH - 50) { // �ε�ġ�� ������ �ٲٰ� y�ӵ� �ٽ� ����
			int randomyDir = (int) (Math.random() * 5 - 2); // -2 ~ 2

			xDir = -xDir;
			yDir = randomyDir;
		}
		if (y <= 5) { // �ε�ġ�� ������ �ٲٰ� x�ӵ� �ٽ� ����
			int randomxDir = (int) (Math.random() * 2 + 1); // 1 ~ 2

			yDir = randomxDir;
		}
		if (y >= Main.SCREEN_HEIGHT - 75) {
			int randomxDir = (int) (Math.random() * 2 + 1); // 1 ~ 2

			yDir = -randomxDir;
		}

		x += xDir;
		y += yDir;
	}

	public void hungryMove() { // ����� �� ������

		int reach;

		if (Game.peedList.size() > 0) { // ���̰� ������

			/* ����� ���̸� ���ϴ� �˰��� */
			goalReach = (Game.peedList.get(0).x - x) * (Game.peedList.get(0).x - x)
					+ (Game.peedList.get(0).y - y) * (Game.peedList.get(0).y - y);
			goalX = Game.peedList.get(0).x;
			goalY = Game.peedList.get(0).y;

			for (int i = 0; i < Game.peedList.size(); i++) {
				reach = (Game.peedList.get(i).x - x) * (Game.peedList.get(i).x - x)
						+ (Game.peedList.get(i).y - y) * (Game.peedList.get(i).y - y);
				if (reach < goalReach) {
					goalReach = reach;
					goalX = Game.peedList.get(i).x;
					goalY = Game.peedList.get(i).y;
				}
			}
			/* ����� ���̸� ���ϴ� �˰��� */
			
			

			/* ����� ���̷� ������ �̵� */
			if (goalX - x > 0) { // ������ ���ʿ� �ִ�.
				xDir = 2;
				x += xDir;
			} else { // ������ �����ʿ� �ִ�.
				xDir = -2;
				x += xDir;
			}
			if (goalY - y > 0) { // ������ ���� �ִ�.
				yDir = 2;
				y += yDir;
			} else {
				yDir = -2;
				y += yDir;
			}
			/* ����� ���̷� ������ �̵� */

		} else { // ���̰� ���� ��(��� �� ó��)
			move();
		}

	}

	@Override
	public void run() {
		while (live) {
			if (hungry > 1000) {
				move();
			} else {
				hungryMove();
			}

			coin++;
			hungry--;

			if (coin > 500) { // 5�� ���� ������ ����
				Game.coinList.add(new Coin(x, y, level));
				coin = (int) (Math.random() * 10 + 1);
			}

			try {
				sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
