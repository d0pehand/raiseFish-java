package raiseFish;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Game extends JPanel implements ActionListener {

	static Music backgroundMusic = new Music("basicMusic.mp3", true);
	Music coinMusic;
	Music effectMusic;
	Listen listen = new Listen();

	// 게임의 상태 정보
	boolean isGameStarted = false;

	// 플레이어의 정보
	int money;
	int peedLevel;
	int peedMax;
	int attack;

	static int xPos = 0, yPos = 0, eHp = 10;

	// 가지고 있는 객체의 목록
	static ArrayList<Jellyfish> jellyFishList = new ArrayList<Jellyfish>();
	static ArrayList<Fish> fishList = new ArrayList<Fish>();
	static ArrayList<Crab> crabList = new ArrayList<Crab>();
	static ArrayList<Peed> peedList = new ArrayList<Peed>();
	static ArrayList<Coin> coinList = new ArrayList<Coin>();
	static ArrayList<Item> itemList = new ArrayList<Item>();
	static ArrayList<Enemy> enemyList = new ArrayList<Enemy>();

	EnemyCreate enemyCreate = new EnemyCreate();

	// 배경 이미지
	Image backgroundImage = new ImageIcon(Main.class.getResource("../images/background.png")).getImage();

	// 버튼 이미지 70x70
	Image peed1Up = new ImageIcon(Main.class.getResource("../images/peed1Up.png")).getImage();
	Image peed2Up = new ImageIcon(Main.class.getResource("../images/peed2Up.png")).getImage();
	Image peed3Up = new ImageIcon(Main.class.getResource("../images/peed3Up.png")).getImage();

	Image peed1Max = new ImageIcon(Main.class.getResource("../images/peed1Max.png")).getImage();
	Image peed2Max = new ImageIcon(Main.class.getResource("../images/peed2Max.png")).getImage();
	Image peed3Max = new ImageIcon(Main.class.getResource("../images/peed3Max.png")).getImage();

	Image weaponImage = new ImageIcon(Main.class.getResource("../images/weapon.png")).getImage();
	Image moneyImage = new ImageIcon(Main.class.getResource("../images/money.png")).getImage();

	// 물고기 이미지
	Image rightFish1Image = new ImageIcon(Main.class.getResource("../images/오른쪽물고기6464.png")).getImage();
	Image rightHungryFish1Image = new ImageIcon(Main.class.getResource("../images/오른쪽물고기배고픔6464.png")).getImage();
	Image rightFish2Image = new ImageIcon(Main.class.getResource("../images/오른쪽물고기9696.png")).getImage();
	Image rightHungryFish2Image = new ImageIcon(Main.class.getResource("../images/오른쪽물고기배고픔9696.png")).getImage();
	Image rightFish3Image = new ImageIcon(Main.class.getResource("../images/오른쪽물고기128128.png")).getImage();
	Image rightHungryFish3Image = new ImageIcon(Main.class.getResource("../images/오른쪽물고기배고픔128128.png")).getImage();

	Image leftFish1Image = new ImageIcon(Main.class.getResource("../images/왼쪽물고기6464.png")).getImage();
	Image leftHungryFish1Image = new ImageIcon(Main.class.getResource("../images/왼쪽물고기배고픔6464.png")).getImage();
	Image leftFish2Image = new ImageIcon(Main.class.getResource("../images/왼쪽물고기9696.png")).getImage();
	Image leftHungryFish2Image = new ImageIcon(Main.class.getResource("../images/왼쪽물고기배고픔9696.png")).getImage();
	Image leftFish3Image = new ImageIcon(Main.class.getResource("../images/왼쪽물고기128128.png")).getImage();
	Image leftHungryFish3Image = new ImageIcon(Main.class.getResource("../images/왼쪽물고기배고픔128128.png")).getImage();

	// 해파리 이미지
	Image rightJellyfish1Image = new ImageIcon(Main.class.getResource("../images/해파리오른쪽6464.png")).getImage();
	Image rightHungryJellyfish1Image = new ImageIcon(Main.class.getResource("../images/해파리오른쪽배고픔6464.png")).getImage();
	Image rightJellyfish2Image = new ImageIcon(Main.class.getResource("../images/해파리오른쪽9696.png")).getImage();
	Image rightHungryJellyfish2Image = new ImageIcon(Main.class.getResource("../images/해파리오른쪽배고픔9696.png")).getImage();

	Image leftJellyfish1Image = new ImageIcon(Main.class.getResource("../images/해파리왼쪽6464.png")).getImage();
	Image leftHungryJellyfish1Image = new ImageIcon(Main.class.getResource("../images/해파리왼쪽배고픔6464.png")).getImage();
	Image leftJellyfish2Image = new ImageIcon(Main.class.getResource("../images/해파리왼쪽9696.png")).getImage();
	Image leftHungryJellyfish2Image = new ImageIcon(Main.class.getResource("../images/해파리왼쪽배고픔9696.png")).getImage();

	// 꽃게 이미지
	Image crabImage = new ImageIcon(Main.class.getResource("../images/꽃게.png")).getImage();
	Image crabDiaImage = new ImageIcon(Main.class.getResource("../images/꽃게다이아.png")).getImage();

	// 몬스터 이미지
	Image monster1Image = new ImageIcon(Main.class.getResource("../images/monster1.png")).getImage();
	Image monster2Image = new ImageIcon(Main.class.getResource("../images/monster2.png")).getImage();

	// 먹이 이미지 32x32
	Image peed1Image = new ImageIcon(Main.class.getResource("../images/peed1.png")).getImage();
	Image peed2Image = new ImageIcon(Main.class.getResource("../images/peed2.png")).getImage();
	Image peed3Image = new ImageIcon(Main.class.getResource("../images/peed3.png")).getImage();

	// 코인 이미지 48x48
	Image coin1Image = new ImageIcon(Main.class.getResource("../images/coin1.png")).getImage();
	Image coin2Image = new ImageIcon(Main.class.getResource("../images/coin2.png")).getImage();
	Image coin3Image = new ImageIcon(Main.class.getResource("../images/coin3.png")).getImage();
	Image coin4Image = new ImageIcon(Main.class.getResource("../images/coin4.png")).getImage();
	Image coin5Image = new ImageIcon(Main.class.getResource("../images/coin5.png")).getImage();

	// 시간의 흐름
	private Timer timer;
	private int delay = 0;

	public Game() {

		money = 10000;
		peedLevel = 1;
		peedMax = 3;
		attack = 1;

		addMouseListener(listen);
		addMouseMotionListener(listen);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);

		// 초기화
		fishList.add(new Fish());
		jellyFishList.add(new Jellyfish());
		crabList.add(new Crab());

		enemyCreate.start();

		// 시간의 흐름 //계속 그려 줌
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		timer = new Timer(delay, this);
		timer.start();

		backgroundMusic.start();

	}

	public void paint(Graphics g) {

		// 해파리 상태 변경
		if (jellyFishList.size() > 0) {
			for (int i = 0; i < jellyFishList.size(); i++) {
				if (jellyFishList.get(i).hungry < 0) { // 굶으면 죽음
					jellyFishList.get(i).live = false;
					jellyFishList.remove(i);
					break;
				} else if (jellyFishList.get(i).hungry < 1500) { // 배고프면 먹이 근처에 있는지 조사

					if (peedList.size() > 0) {
						for (int j = 0; j < peedList.size(); j++) { // 먹이 량 만큼 조사
							if (peedList.get(j).x - 10 < jellyFishList.get(i).x
									&& jellyFishList.get(i).x < peedList.get(j).x + 10
									&& peedList.get(j).y - 10 < jellyFishList.get(i).y
									&& jellyFishList.get(i).y < peedList.get(j).y + 10) { // 먹이 근처에 있다면 = 먹.는.다
								jellyFishList.get(i).hungry += peedList.get(j).satiety;
								jellyFishList.get(i).eat += 1;
								peedList.remove(j);
								break;
							}
						}
					}
				}

				if (jellyFishList.get(i).eat < 5) { // 해파리 레벨 세팅
					jellyFishList.get(i).level = 4;
				} else {
					jellyFishList.get(i).level = 5;
				}
				if (enemyList.size() > 0) { // 적과 닿으면 = 죽.는.다
					for (int k = 0; k < enemyList.size(); k++) {
						if (enemyList.get(k).x < jellyFishList.get(i).x
								&& jellyFishList.get(i).x < enemyList.get(k).x + 100
								&& enemyList.get(k).y < jellyFishList.get(i).y
								&& jellyFishList.get(i).y < enemyList.get(k).y + 100) {
							jellyFishList.get(i).live = false;
							jellyFishList.remove(i);
							effectMusic = new Music("버튼음.mp3", false);
							effectMusic.start();
							break;
						}
					}
				}
			}
		}

		// 물고기 상태 변경
		if (fishList.size() > 0) {
			for (int i = 0; i < fishList.size(); i++) {
				if (fishList.get(i).hungry < 0) { // 굶으면 죽음
					fishList.get(i).live = false;
					fishList.remove(i);
					break;
				} else if (fishList.get(i).hungry < 1500) { // 배고프면 먹이 근처에 있는지 조사

					if (peedList.size() > 0) {
						for (int j = 0; j < peedList.size(); j++) { // 먹이 량 만큼 조사
							if (peedList.get(j).x - 10 < fishList.get(i).x && fishList.get(i).x < peedList.get(j).x + 10
									&& peedList.get(j).y - 10 < fishList.get(i).y
									&& fishList.get(i).y < peedList.get(j).y + 10) { // 먹이 근처에 있다면 = 먹.는.다
								fishList.get(i).hungry += peedList.get(j).satiety;
								fishList.get(i).eat += 1;
								peedList.remove(j);
								break;
							}
						}
					}
				}

				if (fishList.get(i).eat < 2) { // 물고기 레벨 세팅
					fishList.get(i).level = 1;
				} else if (fishList.get(i).eat < 5) {
					fishList.get(i).level = 2;
				} else {
					fishList.get(i).level = 3;
				}

				if (enemyList.size() > 0) { // 적과 닿으면 = 죽.는.다
					for (int k = 0; k < enemyList.size(); k++) {
						if (enemyList.get(k).x < fishList.get(i).x && fishList.get(i).x < enemyList.get(k).x + 100
								&& enemyList.get(k).y < fishList.get(i).y
								&& fishList.get(i).y < enemyList.get(k).y + 100) {
							fishList.get(i).live = false;
							fishList.remove(i);
							effectMusic = new Music("버튼음.mp3", false);
							effectMusic.start();
							break;
						}
					}
				}
			}
		}

		// 여기부터 화면에 그린다.

		// background
		g.drawImage(backgroundImage, 0, 0, null);

		// buttonImage 70x70
		g.drawImage(rightFish1Image, 30, 15, null);
		g.drawImage(rightJellyfish2Image, 80, 8, null);
		g.drawImage(weaponImage, 160, 15, null);

		if (peedLevel == 1) {
			g.drawImage(peed1Max, 230, 12, null);
		} else if (peedLevel == 2) {
			g.drawImage(peed2Max, 230, 12, null);
		} else {
			g.drawImage(peed3Max, 230, 12, null);
		}

		if (peedLevel == 1) {
			g.drawImage(moneyImage, Main.SCREEN_WIDTH - 170, 10, null);
			g.drawImage(peed1Up, Main.SCREEN_WIDTH - 100, 10, null);
		} else if (peedLevel == 2) {
			g.drawImage(moneyImage, Main.SCREEN_WIDTH - 170, 10, null);
			g.drawImage(peed2Up, Main.SCREEN_WIDTH - 100, 10, null);
		} else {
			g.drawImage(moneyImage, Main.SCREEN_WIDTH - 100, 10, null);
		}

		// 경계 선
		// border (5,5) ~ (SCREEN_WIDTH - 25, SCREEN_HEIGHT - 50)
		g.setColor(Color.black);
		g.fillRect(5, 0, 5, Main.SCREEN_HEIGHT);
		g.fillRect(0, 5, Main.SCREEN_WIDTH, 5);
		g.fillRect(Main.SCREEN_WIDTH - 25, 0, 5, Main.SCREEN_HEIGHT);
		g.fillRect(0, Main.SCREEN_HEIGHT - 50, Main.SCREEN_WIDTH, 5);

		// 먹이
		for (int i = 0; i < peedList.size(); i++) {
			if (peedList.get(i).y < Main.SCREEN_HEIGHT - 75) {

				switch (peedList.get(i).level) { // 먹이 레벨에 따라 다르게 출력
				case 1:
					g.drawImage(peed1Image, peedList.get(i).x, peedList.get(i).y, null);
					break;
				case 2:
					g.drawImage(peed2Image, peedList.get(i).x, peedList.get(i).y, null);
					break;
				case 3:
					g.drawImage(peed3Image, peedList.get(i).x, peedList.get(i).y, null);
					break;
				default:
					break;
				}

			} else { // 화면을 벗어나면 먹이 삭제
				peedList.remove(i);
			}
		}

		// 물고기
		for (int i = 0; i < fishList.size(); i++) {
			if (fishList.get(i).xDir > 0) {
				switch (fishList.get(i).level) { // 오른쪽 물고기 레벨에 따라 다르게 출력
				case 1:
					if (fishList.get(i).hungry > 1000) {
						g.drawImage(rightFish1Image, fishList.get(i).x, fishList.get(i).y, null);
					} else {
						g.drawImage(rightHungryFish1Image, fishList.get(i).x, fishList.get(i).y, null);
					}
					break;
				case 2:
					if (fishList.get(i).hungry > 1000) {
						g.drawImage(rightFish2Image, fishList.get(i).x, fishList.get(i).y, null);
					} else {
						g.drawImage(rightHungryFish2Image, fishList.get(i).x, fishList.get(i).y, null);
					}
					break;
				case 3:
					if (fishList.get(i).hungry > 1000) {
						g.drawImage(rightFish3Image, fishList.get(i).x, fishList.get(i).y, null);
					} else {
						g.drawImage(rightHungryFish3Image, fishList.get(i).x, fishList.get(i).y, null);
					}
					break;
				default:
					break;
				}
			} else {
				switch (fishList.get(i).level) { // 왼쪽 물고기 레벨에 따라 다르게 출력
				case 1:
					if (fishList.get(i).hungry > 1000) {
						g.drawImage(leftFish1Image, fishList.get(i).x, fishList.get(i).y, null);
					} else {
						g.drawImage(leftHungryFish1Image, fishList.get(i).x, fishList.get(i).y, null);
					}
					break;
				case 2:
					if (fishList.get(i).hungry > 1000) {
						g.drawImage(leftFish2Image, fishList.get(i).x, fishList.get(i).y, null);
					} else {
						g.drawImage(leftHungryFish2Image, fishList.get(i).x, fishList.get(i).y, null);
					}
					break;
				case 3:
					if (fishList.get(i).hungry > 1000) {
						g.drawImage(leftFish3Image, fishList.get(i).x, fishList.get(i).y, null);
					} else {
						g.drawImage(leftHungryFish3Image, fishList.get(i).x, fishList.get(i).y, null);
					}
					break;
				default:
					break;
				}
			}
		}

		// 해파리
		for (int i = 0; i < jellyFishList.size(); i++) {
			if (jellyFishList.get(i).xDir > 0) {
				switch (jellyFishList.get(i).level) { // 오른쪽 해파리 레벨에 따라 다르게 출력
				case 4:
					if (jellyFishList.get(i).hungry > 1000) {
						g.drawImage(rightJellyfish1Image, jellyFishList.get(i).x, jellyFishList.get(i).y, null);
					} else {
						g.drawImage(rightHungryJellyfish1Image, jellyFishList.get(i).x, jellyFishList.get(i).y, null);
					}
					break;
				case 5:
					if (jellyFishList.get(i).hungry > 1000) {
						g.drawImage(rightJellyfish2Image, jellyFishList.get(i).x, jellyFishList.get(i).y, null);
					} else {
						g.drawImage(rightHungryJellyfish2Image, jellyFishList.get(i).x, jellyFishList.get(i).y, null);
					}
					break;
				default:
					break;
				}
			} else {
				switch (jellyFishList.get(i).level) { // 왼쪽 해파리 레벨에 따라 다르게 출력
				case 4:
					if (jellyFishList.get(i).hungry > 1000) {
						g.drawImage(leftJellyfish1Image, jellyFishList.get(i).x, jellyFishList.get(i).y, null);
					} else {
						g.drawImage(leftHungryJellyfish1Image, jellyFishList.get(i).x, jellyFishList.get(i).y, null);
					}
					break;
				case 5:
					if (jellyFishList.get(i).hungry > 1000) {
						g.drawImage(leftJellyfish2Image, jellyFishList.get(i).x, jellyFishList.get(i).y, null);
					} else {
						g.drawImage(leftHungryJellyfish2Image, jellyFishList.get(i).x, jellyFishList.get(i).y, null);
					}
					break;
				default:
					break;
				}
			}
		}

		// 동전
		for (int i = 0; i < coinList.size(); i++) {
			if (coinList.get(i).y < Main.SCREEN_HEIGHT - 75) {
				switch (coinList.get(i).level) { // 동전 레벨에 따라 다르게 출력
				case 1:
					g.drawImage(coin1Image, coinList.get(i).x, coinList.get(i).y, null);
					break;
				case 2:
					g.drawImage(coin2Image, coinList.get(i).x, coinList.get(i).y, null);
					break;
				case 3:
					g.drawImage(coin3Image, coinList.get(i).x, coinList.get(i).y, null);
					break;
				case 4:
					g.drawImage(coin4Image, coinList.get(i).x, coinList.get(i).y, null);
					break;
				case 5:
					g.drawImage(coin5Image, coinList.get(i).x, coinList.get(i).y, null);
					break;
				default:
					break;
				}
			} else { // 화면을 벗어나면 동전 삭제
				coinList.remove(i);
			}
		}

		// 적
		g.setColor(Color.black);
		g.setFont(new Font("Arial", Font.BOLD, 10));
		if (enemyList.size() > 0) {
			for (int i = 0; i < enemyList.size(); i++) {
				if (enemyList.get(i).type == 1) {
					g.drawImage(monster1Image, enemyList.get(i).x, enemyList.get(i).y, null);
				} else {
					g.drawImage(monster2Image, enemyList.get(i).x, enemyList.get(i).y, null);
				}
				g.drawString("" + enemyList.get(i).hp, enemyList.get(i).x + 85, enemyList.get(i).y + 100);
			}
		}

		// 꽃게
		for (int i = 0; i < crabList.size(); i++) {
			if (!crabList.get(i).isCoinHave) {
				g.drawImage(crabImage, crabList.get(i).x, Main.SCREEN_HEIGHT - 100, null);
			} else {
				g.drawImage(crabDiaImage, crabList.get(i).x, Main.SCREEN_HEIGHT - 100, null);
			}
		}

		// info
		g.setColor(Color.white);
		g.setFont(new Font("Arial", Font.BOLD, 10));
		g.drawString("x : " + xPos + "   y : " + yPos, 30, 100);

		g.setColor(Color.black);
		g.setFont(new Font("Arial", Font.BOLD, 15));

		g.drawString("" + attack, 200, 70);
		g.drawString("" + peedMax, 285, 70);

		if (peedLevel < 3) {
			g.drawString("" + money, Main.SCREEN_WIDTH - 160, 72);
		} else {
			g.drawString("" + money, Main.SCREEN_WIDTH - 90, 72);
		}

		g.dispose(); // 리소스 해제 // 중요

	}

	@Override
	public void actionPerformed(ActionEvent e) { // 시간의 흐름에 따라
		timer.start();
		repaint();
	}

	public class Listen implements MouseListener, MouseMotionListener {
		@Override
		public void mousePressed(MouseEvent e) { // 마우스를 눌렀을 때
			Game.xPos = e.getX();
			Game.yPos = e.getY();

			// 몬스터 공격
			if (enemyList.size() > 0) {
				for (int i = 0; i < enemyList.size(); i++) {
					if (enemyList.get(i).x < Game.xPos && Game.xPos < enemyList.get(i).x + 100
							&& enemyList.get(i).y < Game.yPos && Game.yPos < enemyList.get(i).y + 100) {
						enemyList.get(i).hp -= attack;
						effectMusic = new Music("hit.mp3", false);
						effectMusic.start();

						if (enemyList.get(i).hp <= 0) { // 몬스터가 죽으면

							// itemList.add(new Item(enemyList.get(i).x, enemyList.get(i).y)); // 아이템을 떨군다.

							int drop = (int) (Math.random() * 100);
							if (crabList.size() < 4 && drop < 30 - 5 * crabList.size()) {
								crabList.add(new Crab());
							}

							enemyList.get(i).isLive = false;
							enemyList.remove(i);
							effectMusic = new Music("적처치.mp3", false);
							effectMusic.start();
						}
						return;
					}
				}
			}

			// size 70x70
			if (30 < Game.xPos && Game.xPos < 100 && 15 < Game.yPos && Game.yPos < 85) { // 물고기 생성 버튼
				if (money >= 100) {
					money -= 100;
					fishList.add(new Fish());
					effectMusic = new Music("물소리.mp3", false);
					effectMusic.start();
					return;
				}
			}

			if (80 < Game.xPos && Game.xPos < 150 && 8 < Game.yPos && Game.yPos < 90) { // 해파리 생성 버튼
				if (money >= 1000) {
					money -= 1000;
					jellyFishList.add(new Jellyfish());
					effectMusic = new Music("물소리.mp3", false);
					effectMusic.start();
					return;
				}
			}

			if (160 < Game.xPos && Game.xPos < 230 && 15 < Game.yPos && Game.yPos < 85) { // 공격력 업 버튼
				if (money >= 100) {
					money -= 100;
					attack++;
					effectMusic = new Music("버튼음.mp3", false);
					effectMusic.start();
					return;
				}
			}

			if (230 < Game.xPos && Game.xPos < 300 && 12 < Game.yPos && Game.yPos < 82) { // 먹이 최대치 업 버튼
				if (money >= 200) {
					money -= 200;
					peedMax++;
					effectMusic = new Music("버튼음.mp3", false);
					effectMusic.start();
					return;
				}
			}

			if (Main.SCREEN_WIDTH - 100 < Game.xPos && Game.xPos < Main.SCREEN_WIDTH - 30 && 10 < Game.yPos
					&& Game.yPos < 80) { // 먹이 레벨 업 버튼
				if (money >= 300 && peedLevel < 3) {
					money -= 300;
					peedLevel++;
					effectMusic = new Music("버튼음.mp3", false);
					effectMusic.start();
					return;
				}
			}

			for (int i = 0; i < crabList.size(); i++) {
				if (crabList.get(i).isCoinHave && Game.xPos > crabList.get(i).x && Game.yPos > Main.SCREEN_HEIGHT - 100
						&& Game.xPos < crabList.get(i).x + 80 && Game.yPos < Main.SCREEN_HEIGHT - 30) { // 꽃게 다이아 먹기
					crabList.get(i).coin = 0;
					crabList.get(i).isCoinHave = false;
					money += 200;
					coinMusic = new Music("동전먹었을때.mp3", false);
					coinMusic.start();
					break;
				}
			}

			boolean isCoinInside = false;

			if (coinList.size() > 0) { // 범위 내에 코인이 있는지 조사
				for (int i = 0; i < coinList.size(); i++) {
					if (Game.xPos > coinList.get(i).x && Game.yPos > coinList.get(i).y
							&& Game.xPos < coinList.get(i).x + 80 && Game.yPos < coinList.get(i).y + 80) {
						isCoinInside = true;
						break;
					}
				}
			}

			if (!isCoinInside) {// 범위 내에 코인이 없다면 먹이를 생성한다.
				int cost = 0;
				switch (peedLevel) {
				case 1:
					cost = 10;
					break;
				case 2:
					cost = 20;
					break;
				case 3:
					cost = 50;
					break;
				}

				if (peedList.size() < peedMax && money >= cost) { // 먹이 코스트
					peedList.add(new Peed(xPos, yPos, peedLevel));
					money -= cost;
				}
			}
		}

		@Override
		public void mouseMoved(MouseEvent e) { // 마우스가 이동할때 자동으로 실행
			Game.xPos = e.getX();
			Game.yPos = e.getY();

			if (coinList.size() > 0) {// 범위 내에 코인이 있다면 먹는다.
				for (int i = 0; i < coinList.size(); i++) {
					if (Game.xPos > coinList.get(i).x && Game.yPos > coinList.get(i).y
							&& Game.xPos < coinList.get(i).x + 50 && Game.yPos < coinList.get(i).y + 50) {
						switch (coinList.get(i).level) {
						case 1:
							money += 10;
							break;
						case 2:
							money += 50;
							break;
						case 3:
							money += 100;
							break;
						case 4:
							money += 100;
							break;
						case 5:
							money += 200;
							break;
						}
						coinList.remove(i);
						coinMusic = new Music("동전먹었을때.mp3", false);
						coinMusic.start();
						break;
					}
				}
			}
		}

		@Override
		public void mouseClicked(MouseEvent e) {
		}

		@Override
		public void mouseReleased(MouseEvent e) {
		}

		@Override
		public void mouseEntered(MouseEvent e) {
		}

		@Override
		public void mouseExited(MouseEvent e) {
		}

		@Override
		public void mouseDragged(MouseEvent e) {
		}
	}
}
