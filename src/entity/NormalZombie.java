package entity;

import entity.base.Zombie;

public class NormalZombie extends Zombie {

	public NormalZombie() {
		super(6, 1.25, "NormalZombie");
		createSprite();
	}
}
