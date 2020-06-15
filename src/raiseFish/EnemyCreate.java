package raiseFish;

public class EnemyCreate extends Thread {

	int count; // 적이 폭주하는 시간을 제어

	EnemyCreate() {
		count = 0;
	}

	@Override
	public void run() {

		while (true) {
			if (count < 4) { // 평상시 상태 // 기본 배경음
				try {
					sleep(10000); // 10초 마다
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				if (Game.enemyList.size() == 0) {
					int r = (int) (Math.random() * 10); // 0 ~ 9
					if (r < 7) { // 70퍼
						Game.enemyList.add(new Enemy(Game.eHp));
					}
					Game.eHp++;
					Game.eHp++; // 적을 방치하면 더 빨리 강해진다.
				} else {
					Game.eHp++;
				}
				count++;
			} else { // 적 폭주 상태
				Game.backgroundMusic.close();
				Game.backgroundMusic = new Music("enemyMusic.mp3", true);
				Game.backgroundMusic.start();

				count = 0;

				Game.enemyList.add(new Enemy(Game.eHp * 2, 2));

				while (Game.enemyList.size() > 0) { // 적을 다 잡아야 폭주상태가 끝난다.
					Game.enemyList.add(new Enemy(Game.eHp / 5));

					try {
						sleep(4000); // 4초 마다 적 생성
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

					Game.eHp++;
				}
				Game.backgroundMusic.close();
				Game.backgroundMusic = new Music("basicMusic.mp3", true);
				Game.backgroundMusic.start();
			}
		}
	}

}
