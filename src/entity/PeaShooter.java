package entity;

import entity.base.Shooter;

public class PeaShooter extends Shooter {

	public PeaShooter() {
		super(10, "PeaShooter", 1);
		this.fireRate = 1000;

	}
}
