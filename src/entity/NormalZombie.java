package entity;
import entity.base.Zombie;

public class NormalZombie extends Zombie {

	public NormalZombie() {
		//default stats
		super(8,1.25,25, "NormalZombie"); //hp, speed, coinDrop, name
		createSprite();
	}
}
