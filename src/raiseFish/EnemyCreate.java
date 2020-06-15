package raiseFish;

public class EnemyCreate extends Thread {

	int count; // ���� �����ϴ� �ð��� ����

	EnemyCreate() {
		count = 0;
	}

	@Override
	public void run() {

		while (true) {
			if (count < 4) { // ���� ���� // �⺻ �����
				try {
					sleep(10000); // 10�� ����
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				if (Game.enemyList.size() == 0) {
					int r = (int) (Math.random() * 10); // 0 ~ 9
					if (r < 7) { // 70��
						Game.enemyList.add(new Enemy(Game.eHp));
					}
					Game.eHp++;
					Game.eHp++; // ���� ��ġ�ϸ� �� ���� ��������.
				} else {
					Game.eHp++;
				}
				count++;
			} else { // �� ���� ����
				Game.backgroundMusic.close();
				Game.backgroundMusic = new Music("enemyMusic.mp3", true);
				Game.backgroundMusic.start();

				count = 0;

				Game.enemyList.add(new Enemy(Game.eHp * 2, 2));

				while (Game.enemyList.size() > 0) { // ���� �� ��ƾ� ���ֻ��°� ������.
					Game.enemyList.add(new Enemy(Game.eHp / 5));

					try {
						sleep(4000); // 4�� ���� �� ����
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
