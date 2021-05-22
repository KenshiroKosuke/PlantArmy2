package application;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Random;

import entity.BucketZombie;
import entity.ConeZombie;
import entity.NormalZombie;
import entity.base.Pea;
import entity.base.Shooter;
import entity.base.Sun;
import entity.base.SunProducer;
import entity.base.Zombie;
import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.media.AudioClip;
import logic.Cell;
import logic.ControlPane;
import logic.FieldPane;
import logic.GameController;
import logic.ShopPane;

public class NormalMode extends AnchorPane {

	private Random rand = new Random();
	private static FieldPane field;
	private static ShopPane shop;
	private static ControlPane control;
	private static AudioClip zombieComingSound = new AudioClip(
			ClassLoader.getSystemResource("audio/Zombie_Is_coming.wav").toString());
	private static AudioClip zombieGroanSound = new AudioClip(
			ClassLoader.getSystemResource("audio/Zombie_Groan.wav").toString());

	public NormalMode() {
		String image_path = ClassLoader.getSystemResource("Lawn.png").toString();
		Image img = new Image(image_path);
		BackgroundSize bgSize = new BackgroundSize(Main.getWidth(), Main.getHeight(), false, false, false, false);
		BackgroundImage bgImg = new BackgroundImage(img, null, null, null, bgSize);
		BackgroundImage[] bgImgA = { bgImg };
		field = new FieldPane();
		control = new ControlPane();
		shop = new ShopPane();
		this.setBackground(new Background(null, bgImgA));
		this.getChildren().add(field);
		this.getChildren().add(shop);
		this.getChildren().add(control);
		setRightAnchor(field, 30.0);
		setTopAnchor(field, 75.0);
		setTopAnchor(shop, 66.0);
		setLeftAnchor(shop, -3.0);
		setTopAnchor(control, 10.0);
		GameController.setIs_over(false);
		populateZombie(0);
		zombieComingSound.play();
		ammoReposition();
		checkFire();
		sunTimer();
		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				while (!GameController.is_over()) {
					try {
						if (GameController.getCurrentZombies().size() == 0) {
							Thread.sleep(2000);
							zombieComingSound.play();
							if (GameController.getWaveType() == 1) {
								GameController.setWave(GameController.getWave() + 1);
							}
							GameController.changeWaveType();
							if (GameController.getWave() == 4) {
								Platform.runLater(new Runnable() {
									@Override
									public void run() {
										GameController.setGameWin();
									}
								});
							} else {
								populateZombie(GameController.getWaveType());
							}
						}
						Random rand = new Random();
						if (rand.nextDouble() < 0.03) {
							zombieGroanSound.play();
						}
						Thread.sleep(500);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

			}
		});
		thread.start();
	}


	public void populateZombie(int waveType) {
		int enemyCount = 4 + 4 * GameController.getWave();
		double rare = 1.0, rarer = 1.0, rarest = 1.0;
		// setting the chance of getting rare Zombie
		switch (GameController.getWave()) {
		case 1:
			rare = 0.955;
			rarer = 1.0;
			rarest = 2.0;
			break;
		case 2:
			rare = 0.87;
			rarer = 0.95;
			rarest = 1.0;
			break;
		case 3:
			rare = 0.80;
			rarer = 0.94;
			rarest = 1.0;
			break;
		default:
			System.out.println("Invalid Level.");
			break;
		}

		for (int i = 0; i < enemyCount; i++) {
			double special = rand.nextDouble();
			System.out.println(i + " " + special);
			if (special < rare) {
				NormalZombie zombie = new NormalZombie();
				initalizeNewZombie(i, zombie);
				if (GameController.getWave() != 1)
					zombie.setHp(zombie.getHp() + GameController.getWave());
				if (waveType == 1) {
					zombie.setHp(zombie.getHp() + GameController.getWave()*2);
				}
			} else if (special < rarer) {
				ConeZombie zombie = new ConeZombie();
				initalizeNewZombie(i, zombie);
				if (GameController.getWave() != 1)
					zombie.setHp(zombie.getHp() + GameController.getWave());
				if (waveType == 1) {
					zombie.setHp(zombie.getHp() + GameController.getWave() * 2);
				}
			} else if (special < rarest) {
				BucketZombie zombie = new BucketZombie();
				initalizeNewZombie(i, zombie);
				if (GameController.getWave() != 1)
					zombie.setHp(zombie.getHp() + GameController.getWave()*2);
				if (waveType == 1) {
					zombie.setHp(zombie.getHp() + GameController.getWave() * 2);
				}
				;
			}
		}
	}

	protected void initalizeNewZombie(int code, Zombie zombie) {
		GameController.getCurrentZombies().add(zombie);
		int row = rand.nextInt(5); // 0-4th row from up to the bottom of field
		double factor = rand.nextDouble();
		if (GameController.getWaveType() == 1) {
			zombie.setX((int) (Main.getWidth() + 20 +0.04*Math.pow(code+3,2) * (200 - GameController.getWave() * 18)));
		} else {
			if (GameController.getWave() == 1) {
				zombie.setX((int) (Main.getWidth() + 400 + code * 260 - factor * 18 * GameController.getWave())); //wave 1.0 only
			} else {
				zombie.setX((int) (Main.getWidth() + 50 +1.1*code * (200 - GameController.getWave() * 25)- 5 * factor * GameController.getWave()));
			}
		}
		// (GameController.getWave()-3)*
		if (zombie.getName() == "NormalZombie")
			zombie.setY((int) (35 + (row * FieldPane.getFieldHeight()) / 5));
		else if (zombie.getName() == "ConeZombie")
			zombie.setY((int) (5 + (row * FieldPane.getFieldHeight()) / 5));
		else if (zombie.getName() == "BucketZombie")
			zombie.setY((int) (25 + (row * FieldPane.getFieldHeight()) / 5));

		zombie.setRow(row);
		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				updateZombieMovement(zombie);
				killZombie(zombie);
			}
		});
		thread.start();
	}

	private void updateZombieMovement(Zombie zombie) {
		try {
			while (!zombie.isDead() && !GameController.is_over()) {
				Thread.sleep(50);
				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						try {
							if (zombie.isExploded()) {
								Thread t = new Thread(new Runnable() {
									public void run() {
										try {
											Thread.sleep(5520);
											if (!GameController.is_over()) {
												zombie.setDead(true);
											}
										} catch (InterruptedException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}
									}
								});
								t.start();
								getChildren().remove(zombie.getImageView());
								zombie.setImageView(new ImageView(Zombie.getImageExplodedZombie()));
								zombie.getImageView().setFitHeight(getHeight());
								getChildren().add(zombie.getImageView());
							} else {
								zombie.update();
							}
							drawZombie(zombie);
						} catch (IndexOutOfBoundsException e) {
							// TODO Auto-generated catch block
							System.out.println("Zombie has been deleted");
						}
					}
				});
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			System.out.println("currentZombies cleared while thread was running");
		}
	}

	public void drawZombie(Zombie zombie) {
		ImageView zombieImageView = zombie.getImageView();
		this.getChildren().remove(zombieImageView);
		zombieImageView.setFitHeight(zombie.getHeight());
		zombieImageView.setFitWidth(Zombie.getWidth());
		zombieImageView.setPreserveRatio(true);
		zombieImageView.relocate((double) (zombie.getX()), (double) (zombie.getY()));
		this.getChildren().add(zombieImageView);
	}

	public void killZombie(Zombie zombie) {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				ImageView zombieImageView = zombie.getImageView();
				getChildren().remove(zombieImageView);
			}
		});
		GameController.getCurrentZombies().remove(zombie);

	}

	public static FieldPane getField() {
		return field;
	}

	public void checkFire() {
		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				while (!GameController.is_over()) {
					try {
						Thread.sleep(50);
						for (Shooter shooter : GameController.getShooters()) {
							if (GameController.shouldIShoot(shooter.getX(), shooter.getY()) && !shooter.isShot()
									&& shooter.getHp() > 0) {
								shooter.startShooting();
								shooter.setShot(true);
							}
						}

					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (ConcurrentModificationException e) {
						System.out.println("try to fire again");
						checkFire();
					}
				}
			}
		});
		thread.start();
	}

	public void ammoReposition() {
		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				while (!GameController.is_over()) {
					try {
						Thread.sleep(50);
						Platform.runLater(new Runnable() {
							@Override
							public void run() {
								drawPea();
							}
						});
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
		thread.start();
	}

	public void drawPea() {
		ArrayList<Pea> newPeaToRemove = new ArrayList<Pea>();
		for (Pea pea : GameController.getPeaToRemove()) {
			if (pea.isPeaDead()) {
				this.getChildren().remove(pea.getPeaImageView());
			} else {
				newPeaToRemove.add(pea);
				ImageView peaImage = pea.getPeaImageView();
				this.getChildren().remove(peaImage);
				peaImage.relocate((double) (pea.getX()), (double) (pea.getY()));
				this.getChildren().add(peaImage);
			}
		}
		GameController.setPeaToRemove(newPeaToRemove);
		for (Shooter shooter : GameController.getShooters()) {
			for (Pea pea : shooter.getPeaList()) {
				if (!pea.isPeaDead()) {
					ImageView peaImage = pea.getPeaImageView();
					this.getChildren().remove(peaImage);
					peaImage.relocate((double) (pea.getX()), (double) (pea.getY()));
					this.getChildren().add(peaImage);
				}
			}
		}

	}

	public void sunTimer() {
		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				while (!GameController.is_over()) {
					try {
						Thread.sleep(1650);
						for (SunProducer sunProducer : GameController.getSunProducers()) {
							sunProducer.setSunProduceTimer(sunProducer.getSunProduceTimer() + 1);
							if (sunProducer.getSunProduceTimer() >= sunProducer.getSunProduceTime()) {
								Platform.runLater(new Runnable() {
									@Override
									public void run() {
										sunProducer.produceSun();
										for (Sun sun : sunProducer.getSunList()) {
											sun.getSunImageView().setDisable(false);
											sun.getSunImageView().setVisible(true);
											getChildren().add(sun.getSunImageView());
										}
										sunProducer.setSunProduceTimer(0);
										FieldPane fieldPane = NormalMode.getField();
										Cell cell = (Cell) (fieldPane.getChildren()
												.get(sunProducer.getY() * 9 + sunProducer.getX()));
										String image_path = ClassLoader
												.getSystemResource(cell.getMyPlant().getName() + ".gif").toString();
										cell.changeGraphicPlant(image_path);
									}
								});
							} else if (sunProducer.getSunProduceTimer() >= sunProducer.getSunProduceTime() * 4 / 5) {
								Platform.runLater(new Runnable() {
									@Override
									public void run() {
										FieldPane fieldPane = NormalMode.getField();
										Cell cell = (Cell) (fieldPane.getChildren()
												.get(sunProducer.getY() * 9 + sunProducer.getX()));
										String image_path = ClassLoader
												.getSystemResource(cell.getMyPlant().getName() + "Glow.gif").toString();
										cell.changeGraphicPlant(image_path);
									}
								});
							} else if (sunProducer.getSunProduceTimer() >= sunProducer.getSunProduceTime() / 2) {
								Platform.runLater(new Runnable() {
									@Override
									public void run() {
										sunProducer.produceSun();
										for (Sun sun : sunProducer.getSunList()) {
											getChildren().remove(sun.getSunImageView());
										}
									}
								});
							}
						}

					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
		thread.start();
	}

	public static ControlPane getControl() {
		return control;
	}

	public static void setControl(ControlPane control) {
		NormalMode.control = control;
	}

	public static AudioClip getZombieComingSound() {
		return zombieComingSound;
	}

	public static ShopPane getShop() {
		return shop;
	}

	public static void setShop(ShopPane shop) {
		NormalMode.shop = shop;
	}

}