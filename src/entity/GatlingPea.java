package entity;

import entity.base.Shooter;

public class GatlingPea extends Shooter {
	private int atk;

	public GatlingPea() {
		super(10,"GatlingPea",4); 
		this.fireRate = 1000;
		this.atk = 5;
	}
}
