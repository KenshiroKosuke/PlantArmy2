package entity.base;

import java.util.ArrayList;

import application.Main;
import application.NormalMode;
import javafx.application.Platform;
import javafx.scene.image.ImageView;

public abstract class Shooter extends Plant {
	private int ammo;
	private ArrayList<Pea> peaList= new ArrayList<Pea>();
	public Shooter(int hp, String plantName,int ammo) {
		super(hp,plantName);
		this.ammo = ammo;
	}
	public void startShooting() {
		for(Pea pea: peaList) {
			pea.setX(pea.getStartingX());
			Thread thread = new Thread(new Runnable(){
				@Override
				public void run() {
					while(true) {
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
		}
	}
	public void shoot(Pea pea) {
		if(pea.getX()<Main.getWidth()) {
			pea.setX(pea.getX()+pea.getSpeed());
		}else {
			pea.setX(pea.getStartingX());
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
	};
}
