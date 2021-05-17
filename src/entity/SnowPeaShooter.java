package entity;

import entity.base.Shooter;

public class SnowPeaShooter extends Shooter {

	private int fireRate;
	private int atk;

	public SnowPeaShooter() {
		super(10,"SnowPeaShooter",1);
		this.fireRate = 1300;
		this.atk = 5;
	}

}
