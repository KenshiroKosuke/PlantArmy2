package entity;

import entity.base.Plant;
import entity.base.Shooter;
import entity.base.Upgradeable;

public class Peashooter extends Shooter implements Upgradeable {
	
	private int fireRate;
	private int atk;

	public Peashooter() {
		super(10,"Pea",1);
		this.fireRate = 1300;
		this.atk = 5;
	}

	@Override
	public void upgrade() {
		// TODO Auto-generated method stub
	}
}
