package entity.base;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;

import application.Main;
import javafx.application.Platform;
import logic.GameController;

public abstract class Shooter extends Plant {
	private int ammo;
	protected int fireRate;
	private ArrayList<Pea> peaList = new ArrayList<Pea>();
	private boolean shot;

	public Shooter(int hp, String plantName, int ammo) {
		super(hp, plantName);
		this.ammo = ammo;
		this.shot = false;
	}

	public void startShooting() {
		Thread t = new Thread(new Runnable() {
			public void run() {
				try {
					for (Pea pea : peaList) {
						pea.setX(pea.getStartingX());
						pea.setPeaDead(false);
						Thread thread = new Thread(new Runnable() {
							@Override
							public void run() {
								// if shooter isn't dead
								while (GameController.shouldIShoot(getX(), getY()) && getHp() > 0
										&& !GameController.is_over()
										&& GameController.getShooters().contains(getShooter())) {
									manageFireTime(pea);
									try {
										Thread.sleep(50);
										Platform.runLater(new Runnable() {
											@Override
											public void run() {
												shoot(pea);
											}
										});
									} catch (InterruptedException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
								}
								// after shooter die
								while (!pea.isPeaDead() && !GameController.is_over()) {
									try {
										Thread.sleep(50);
										Platform.runLater(new Runnable() {
											@Override
											public void run() {
												shoot(pea);
											}
										});
									} catch (InterruptedException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
								}
								setShot(false);
							}
						});
						thread.start();
						Platform.runLater(new Runnable() {
							@Override
							public void run() {
								pea.getPeaImageView().setVisible(true);
								pea.getPeaImageView().setDisable(false);
							}
						});
						try {
							Thread.sleep(200);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				} catch (ConcurrentModificationException e) {
					startShooting();
				}

			}

		});
		t.start();

	}

	public void manageFireTime(Pea pea) {
		if (pea.getFireRateTimer() >= getFireRate()) {
			pea.setPeaDead(false);
			pea.setX(pea.getStartingX());
			pea.setFireRateTimer(0);
			pea.getPeaImageView().relocate((double) (pea.getX()), (double) (pea.getY()));
			pea.getPeaImageView().setVisible(true);
			pea.getPeaImageView().setDisable(false);
		} else {
			pea.setFireRateTimer(pea.getFireRateTimer() + 10);
		}
	}

	public void shoot(Pea pea) {
		// move pea + check collision
		if (!pea.isPeaDead()) {
			if (pea.getX() < Main.getWidth()) {
				if (pea.checkZombieCollision(this)) {
					pea.setPeaDead(true);
					pea.getPeaImageView().setVisible(false);
					pea.getPeaImageView().setDisable(true);
				} else {
					pea.setX(pea.getX() + pea.getSpeed());
				}
			} else {
				pea.setPeaDead(true);
				pea.getPeaImageView().setVisible(false);
				pea.getPeaImageView().setDisable(true);
			}
		}
	}

	public ArrayList<Pea> getPeaList() {
		return peaList;
	}

	public void setPeaList(ArrayList<Pea> peaList) {
		this.peaList = peaList;
	}

	public int getAmmo() {
		return ammo;
	}

	public void setAmmo(int ammo) {
		this.ammo = ammo;
	}

	public boolean isShot() {
		return shot;
	}

	public void setShot(boolean shot) {
		this.shot = shot;
	}

	public int getFireRate() {
		return fireRate;
	}

	public void setFireRate(int fireRate) {
		this.fireRate = fireRate;
	};

	public Shooter getShooter() {
		return this;
	}
}
