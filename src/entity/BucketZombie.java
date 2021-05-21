package entity;

import entity.base.Zombie;

public class BucketZombie extends Zombie {

	public BucketZombie() {
		super(25,1.20,25, "BucketZombie");
		createSprite();
	}
	
	public BucketZombie(int hp, double speed, int coinDrop, String zombieName) {
		super(hp, speed, coinDrop, zombieName);
		// TODO Auto-generated constructor stub
	}

}
