package entity;

import entity.base.Shooter;

public class GatlingPea extends Shooter {
	private int atk;

	public GatlingPea() {
		super(10,"Repeater",4); //เปลี่ยน !!!!!!!!!
		this.fireRate = 1000;
		this.atk = 5;
	}
}
