package entity;

import entity.base.Shooter;
import entity.base.Upgradeable;

public class Repeater extends Shooter implements Upgradeable {
	
	private int atk;

	public Repeater() {
		super(10,"Repeater",2);
		this.fireRate = 1000;
		this.atk = 5;
	}

	@Override
	public void upgrade() {
		// TODO Auto-generated method stub
	}
}
