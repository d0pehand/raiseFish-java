package raiseFish;

public class Enemy extends Thread {

	int x, y, xDir, yDir, hp;
	boolean isLive;
	int type;

	public Enemy(int hp) {
		setPosition();
		this.hp = hp;
		isLive = true;
		this.type = 1;

		this.start();
	}

	public Enemy(int hp, int type) {
		setPosition();
		this.hp = hp;
		isLive = true;
		this.type = type;

		this.start();
	}

	public void setPosition() { // 나오는 위치
		int randomx = (int) (Math.random() * (Main.SCREEN_WIDTH - 25) + 5);
		int randomy = (int) (Math.random() * (Main.SCREEN_HEIGHT - 50) + 5);
		int randomxDir = (int) (Math.random() * 2 + 1); // 1 ~ 2
		int randomyDir = (int) (Math.random() * 1 + 1); // 1

		x = randomx / 2 + (Main.SCREEN_WIDTH - 25) / 4;
		y = randomy / 2 + (Main.SCREEN_HEIGHT - 50) / 4;
		xDir = randomxDir;
		yDir = randomyDir;
	}

	public void move() {
		if (x <= 5) { // 부딧치면 방향을 바꾸고 y속도 다시 받음
			int randomyDir = (int) (Math.random() * 5 - 2); // -2 ~ 2

			xDir = -xDir;
			yDir = randomyDir;
		}
		if (x >= Main.SCREEN_WIDTH - 50) { // 부딧치면 방향을 바꾸고 y속도 다시 받음
			int randomyDir = (int) (Math.random() * 5 - 2); // -2 ~ 2

			xDir = -xDir;
			yDir = randomyDir;
		}
		if (y <= 5) { // 부딧치면 방향을 바꾸고 x속도 다시 받음
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

	@Override
	public void run() {
		while (isLive) {
			move();
			try {
				sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
