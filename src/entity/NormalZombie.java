package entity;
import entity.base.Zombie;

public class NormalZombie extends Zombie {

	public NormalZombie() {
		//default stats
		super(20,2,25, "NormalZombie");
		createSprite();
	}
}
