package entity;

import entity.base.Plant;
import entity.base.Upgradeable;

public class Pea extends Plant implements Upgradeable {
	
	private int fireRate;
	private int atk;

	public Pea() {
		super(20,"Pea");
		this.fireRate = 1300;
		this.atk = 5;
	}

	@Override
	public void upgrade() {
		// TODO Auto-generated method stub
	}
}
