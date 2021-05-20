package entity.base;

import java.util.ArrayList;

import application.Main;
import application.NormalMode;
import javafx.application.Platform;
import javafx.scene.image.ImageView;
import logic.GameController;

public abstract class Shooter extends Plant {
	private int ammo;
	protected int fireRate;
	private ArrayList<Pea> peaList= new ArrayList<Pea>();
	private boolean shot;
	//private int fireRateTimer;
	public Shooter(int hp, String plantName,int ammo) {
		super(hp,plantName);
		this.ammo = ammo;
		this.shot = false;
		//this.fireRateTimer=0;
	}
	public void startShooting() {
		//setFireRateTimer(0);
		Thread t = new Thread(new Runnable() {
			public void run() {
				for(Pea pea: peaList) {
					pea.setX(pea.getStartingX());
					pea.setPeaDead(false);
					Thread thread = new Thread(new Runnable(){
						@Override
						public void run() {
							while(GameController.shouldIShoot(getX(),getY())&&getHp()>0&&!GameController.is_over()) {
								if(pea.getFireRateTimer()>=getFireRate()) {
									pea.setPeaDead(false);
									pea.setX(pea.getStartingX());
									pea.setFireRateTimer(0);
							        pea.getPeaImageView().relocate((double)(pea.getX()), (double)(pea.getY()));
									pea.getPeaImageView().setVisible(true);
									pea.getPeaImageView().setDisable(false);
								}else {
									pea.setFireRateTimer(pea.getFireRateTimer()+10);
								}
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
							while(!pea.isPeaDead()&&!GameController.is_over()) {
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
						}
					});
					thread.start();
					pea.getPeaImageView().setVisible(true);
					pea.getPeaImageView().setDisable(false);
					try {
						Thread.sleep(200);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
			}
			
		});
		t.start();
		
	}
	public void shoot(Pea pea) {
		if(!pea.isPeaDead()) {
			if(pea.getX()<Main.getWidth()) {
				if(pea.checkZombieCollision(this)) {
					pea.setPeaDead(true);
					pea.getPeaImageView().setVisible(false);
					pea.getPeaImageView().setDisable(true);
				}else {
					pea.setX(pea.getX()+pea.getSpeed());
				}
			}else {
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
//	public int getFireRateTimer() {
//		return fireRateTimer;
//	}
//	public void setFireRateTimer(int fireRateTimer) {
//		this.fireRateTimer = fireRateTimer;
//	}
	public int getFireRate() {
		return fireRate;
	}
	public void setFireRate(int fireRate) {
		this.fireRate = fireRate;
	};
}
