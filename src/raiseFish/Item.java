package raiseFish;

public class Item extends Thread {

	int x, y, type;

	public Item(int x, int y) {
		this.x = x;
		this.y = y;
		
		int r = (int) (Math.random() * 3 + 1); // 1 ~ 3
		switch (r) {
		case 1:
			type = 1;
			break;
		case 2:
			type = 2;
			break;
		case 3:
			type = 3;
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
