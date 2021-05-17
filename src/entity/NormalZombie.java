package entity;
import entity.base.Zombie;

public class NormalZombie extends Zombie {

	public NormalZombie() {
		//default stats
		super(20,5,25, "NormalZombie"); //hp, speed, coinDrop, name
		createSprite();
	}
}
