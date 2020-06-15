package raiseFish;

public class Peed extends Thread {

	int x, y, level, satiety; // satiety = Æ÷¸¸°¨

	public Peed(int x, int y, int level) {
		this.x = x;
		this.y = y;
		this.level = level; // 1 ~ 3

		switch (level) {
		case 1:
			satiety = 500;
			break;
		case 2:
			satiety = 1000;
			break;
		case 3:
			satiety = 2000;
			break;
		default:
			break;
		}

		this.start();
	}

	@Override
	public void run() {

		while (true) {
			y += 1;

			try {
				sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
