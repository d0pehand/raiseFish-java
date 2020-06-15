package raiseFish;

public class Fish extends Thread {

	int x, y, xDir, yDir, eat, level, hungry, coin;

	int goalReach = 0;
	int goalX = 0;
	int goalY = 0;

	boolean live;

	public Fish() {
		setPosition();
		eat = 0; // 2렙 = 2개 이상, 3렙 = 5개 이상
		level = 1;
		hungry = 2000; // 10초 마다 배고픔 //20초 되면 죽음
		live = true;
		coin = 0;

		this.start();
	}

	public void setPosition() { // 물고기 생성 시 위치 지정
		int randomx = (int) (Math.random() * (Main.SCREEN_WIDTH - 25) + 5);
		int randomy = (int) (Math.random() * (Main.SCREEN_HEIGHT - 50) + 5);
		int randomxDir = (int) (Math.random() * 3 + 1); // 1 ~ 3
		int randomyDir = (int) (Math.random() * 1 + 1); // 1

		x = randomx / 2 + (Main.SCREEN_WIDTH - 25) / 4;
		y = randomy / 2 + (Main.SCREEN_HEIGHT - 50) / 4;
		xDir = randomxDir;
		yDir = randomyDir;
	}

	public void move() { // 평상 시 움직임

		// border (5,5) ~ (SCREEN_WIDTH - 25, SCREEN_HEIGHT - 50)

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

	public void hungryMove() { // 배고플 때 움직임

		int reach;

		if (Game.peedList.size() > 0) { // 먹이가 있을때

			/* 가까운 먹이를 구하는 알고리즘 */
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
			/* 가까운 먹이를 구하는 알고리즘 */
			
			

			/* 가까운 먹이로 빠르게 이동 */
			if (goalX - x > 0) { // 먹이의 왼쪽에 있다.
				xDir = 2;
				x += xDir;
			} else { // 먹이의 오른쪽에 있다.
				xDir = -2;
				x += xDir;
			}
			if (goalY - y > 0) { // 먹이의 위에 있다.
				yDir = 2;
				y += yDir;
			} else {
				yDir = -2;
				y += yDir;
			}
			/* 가까운 먹이로 빠르게 이동 */

		} else { // 먹이가 없을 때(평상 시 처럼)
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

			if (coin > 500) { // 5초 마다 코인을 떨굼
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
