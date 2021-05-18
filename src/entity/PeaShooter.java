package entity;

import entity.base.Plant;
import entity.base.Shooter;
import entity.base.Upgradeable;

public class PeaShooter extends Shooter implements Upgradeable {
	
	private int atk;

	public PeaShooter() {
		super(10,"PeaShooter",2);
		this.fireRate = 1000;
		this.atk = 5;
	}

	@Override
	public void upgrade() {
		// TODO Auto-generated method stub
	}
}
