package entity;

import entity.base.Shooter;

public class PeaShooter extends Shooter {
	
	private int atk;

	public PeaShooter() {
		super(10,"PeaShooter",1);
		this.fireRate = 1000;
		this.atk = 5;
	}
}
