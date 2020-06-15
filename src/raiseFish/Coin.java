package raiseFish;

public class Coin extends Thread {

	int x, y, level;

	public Coin(int x, int y, int level) {
		this.x = x;
		this.y = y;
		this.level = level; // 레벨에 따라 얻는 돈이 다르다. // 1 ~ 5 = [10, 50, 100, 100, 200]
		
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
