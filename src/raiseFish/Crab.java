package raiseFish;

public class Crab extends Thread { // 다이아를 생성해 주는 꽃게
	int x;
	int xDir;
	int coin;
	boolean isCoinHave;
	boolean isLive;

	Crab() {
		x = (int) (Math.random() * (Main.SCREEN_WIDTH - 200) + 75); // 초기 위치

		xDir = (int) (Math.random() * 2 + 1); // 초기 이동방향 속도
		if (((int) (Math.random() * 2 + 1)) == 2) {
			xDir = -xDir;
		}

		coin = 0;
		isCoinHave = false;
		isLive = true;
		this.start();
	}

	public void move() { // 움직임

		if (x < 20) { // 부딧치면 방향을 바꾼다
			xDir = -xDir;
		} else if (x > Main.SCREEN_WIDTH - 60) {
			xDir = -xDir;
		} else {
			int r = (int) (Math.random() * 100 + 1); // 1 ~ 100
			if (r < 3) { // 10퍼의 확률
				xDir = -(int) (Math.random() * 2 + 1); // 속도 조절
			}
		}
		x += xDir;
	}

	@Override
	public void run() {
		while (isLive) {
			move();
			coin++;	
			if (coin > 1000) { // 10초 마다 다이아 생성
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