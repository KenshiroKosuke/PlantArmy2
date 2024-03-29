package entity.base;

import application.Main;
import application.NormalMode;
import entity.Walnut;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.AudioClip;
import logic.Cell;
import logic.FieldPane;
import logic.GameController;

public abstract class Zombie {
	private int hp, row;
	private double x, y, speed;
	private double height;
	private String name;
	private boolean isEating;
	private ImageView imageView;
	private boolean isDead;
	private Cell eatingCell;
	private boolean exploded;
	private double frozenFactor;
	private int freezeTimer;

	public Zombie(int hp, double speed, String zombieName) {
		this.isDead = false;
		this.hp = hp;
		this.speed = speed;
		this.name = zombieName;
		this.x = 0;
		this.y = 0;
		this.isEating = false;
		this.isDead = false;
		this.exploded = false;
		this.frozenFactor = 0;
	}

	private static final Image IMAGE_NORMAL_ZOMBIE = new Image(
			ClassLoader.getSystemResource("NormalZombie_Idle.gif").toString());
	private static final Image IMAGE_CONE_ZOMBIE = new Image(
			ClassLoader.getSystemResource("ConeZombie_Idle.gif").toString());
	private static final Image IMAGE_BUCKET_ZOMBIE = new Image(
			ClassLoader.getSystemResource("BucketZombie_Idle.gif").toString());
	private static final Image IMAGE_EXPLODED_ZOMBIE = new Image(
			ClassLoader.getSystemResource("ExplodedZombie.gif").toString());
	private static AudioClip eatSound = new AudioClip(ClassLoader.getSystemResource("audio/Eat.mp3").toString());
	private static AudioClip plantEatenSound = new AudioClip(
			ClassLoader.getSystemResource("audio/Plant_Eaten.mp3").toString());
	private static AudioClip zombieDieSound = new AudioClip(
			ClassLoader.getSystemResource("audio/ZombieDie.mp3").toString());
	private static final int WIDTH = 85;

	public void createSprite() {
		if (isDead) {
			imageView.setImage(null);
		} else {
			switch (name) {
			case "NormalZombie":
				imageView = new ImageView(IMAGE_NORMAL_ZOMBIE);
				height = 130;
				break;
			case "ConeZombie":
				imageView = new ImageView(IMAGE_CONE_ZOMBIE);
				height = 160;
				break;
			case "BucketZombie":
				imageView = new ImageView(IMAGE_BUCKET_ZOMBIE);
				height = 140;
				break;
			default:
				imageView = new ImageView(IMAGE_NORMAL_ZOMBIE);
				;
			}
		}
	}

	public static int getWidth() {
		return WIDTH;
	}

	public int checkGridXPosition() {
		//check zombie's column
		return (int) ((this.x - (Main.getWidth() - FieldPane.getFieldWidth()) + Zombie.WIDTH / 1.25)
				/ (FieldPane.getFieldWidth() / 9));
	}

	public int CheckPlantCollision() {
		FieldPane fieldPane = NormalMode.getField();
		ObservableList<Node> childrens = fieldPane.getChildren();
		int xPos = this.checkGridXPosition();
		try {
			if (xPos < 9) {
				// check for plant, 0 = no plant, 1 = plant in this cell, 2 = plant in next cell
				Cell thisCell = (Cell) childrens.get(this.row * 9 + xPos);
				Cell nextCell;
				if (xPos != 0) {
					nextCell = (Cell) childrens.get(this.row * 9 + xPos - 1);
				} else {
					nextCell = null;
				}
				if (!thisCell.isEmpty()) {
					eatingCell = thisCell;
					return 1;
				} else if (nextCell != null) {
					if (!nextCell.isEmpty()) {
						eatingCell = nextCell;
						return 2;
					}
				}
				return 0;
			}
		} catch (IndexOutOfBoundsException e) {
			// TODO Auto-generated catch block
			if (GameController.is_over() == true)
				GameController.setGameOver();
		}
		return 0;
	}

	public void damage() {
		if (getHp() - 1 > 0) {
			setHp(getHp() - 1);
		} else {
			zombieKill("normal");
		}
	}

	public void zombieKill(String S) {
		if (S.equals("normal")) {
			setDead(true);
		} else if (S.equals("exploded")) {
			setHeight(160);
			setExploded(true);
		}
		zombieDieSound.play();
		GameController.setKillCount(GameController.getKillCount() + 1);
	}

	public static Image getImageExplodedZombie() {
		return IMAGE_EXPLODED_ZOMBIE;
	}

	public ImageView getImageView() {
		return imageView;
	}

	public void setImageView(ImageView imageView) {
		this.imageView = imageView;
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isDead() {
		return isDead;
	}

	public void setDead(boolean isDead) {
		this.isDead = isDead;
	}

	public void update() {
		// TODO Auto-generated method stub
		// +speed, x, y etc
		if (!isDead && !GameController.is_over()) {
			if (CheckPlantCollision() == 1) {
				// call eat method if plant's in the way
				if (!isEating)
					eat();
			} else if (CheckPlantCollision() == 2) {
				if (((int) (this.x
						- (Main.getWidth() + this.checkGridXPosition() * FieldPane.getFieldWidth() / 9))) == 0) {
					// call eat method if plant's in the way
					if (!isEating)
						eat();
				} else {
					isEating = false;
					this.x -= (this.speed - frozenFactor / 2);
				}

			} else {
				isEating = false;
				this.x -= (this.speed - frozenFactor / 2);
			}
			//timer for when to unfreeze zombie
			if (frozenFactor == 1) {
				freezeTimer += 1;
				if (freezeTimer >= 100) {
					frozenFactor = 0;
					freezeTimer = 0;
					imageView.setEffect(null);
				}
			}
		}
		if (this.x - (Main.getWidth() - FieldPane.getFieldWidth() - 100) < 0) {
			// Game over
			GameController.setGameOver();
		}
	}

	public void eat() {
		isEating = true;
		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					while (eatingCell.getMyPlant() != null && isEating && !isDead && !exploded
							&& !GameController.is_over()) {
						if (eatingCell.getMyPlant() != null) {
							if (eatingCell.getMyPlant().getHp() > 0) {
								eatingCell.getMyPlant().setHp(eatingCell.getMyPlant().getHp() - 2);
								eatSound.play(0.2);
								//change walnut sprite if hp is less than half
								if (eatingCell.getMyPlant() instanceof Walnut
										&& eatingCell.getMyPlant().getHp() == 20) {
									Platform.runLater(new Runnable() {
										@Override
										public void run() {
											eatingCell.changeGraphicPlant("Walnut_Half.gif");
										}
									});
								}
							} else {
								isEating = false;
								eatingCell.removePlant();
								plantEatenSound.play(0.4);
							}
						}
						Thread.sleep(800);
					}
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		thread.start();
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public boolean isExploded() {
		return exploded;
	}

	public void setExploded(boolean exploded) {
		this.exploded = exploded;
	}

	public double getFrozenFactor() {
		return frozenFactor;
	}

	public void setFrozenFactor(int frozenFactor) {
		this.frozenFactor = frozenFactor;
	}

	public int getFreezeTimer() {
		return freezeTimer;
	}

	public void setFreezeTimer(int freezeTimer) {
		this.freezeTimer = freezeTimer;
	}

}