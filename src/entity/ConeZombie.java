package entity;
import entity.base.Zombie;

public class ConeZombie extends Zombie {
	
	public ConeZombie() {
		super(16,1.25,25, "ConeZombie");
		createSprite();
	}

	public ConeZombie(int hp, int speed, int coinDrop, String zombieName) {
		super(hp, speed, coinDrop, zombieName);
		// TODO Auto-generated constructor stub
		//default stats
	}

}
