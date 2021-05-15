package entity;

import entity.base.Zombie;
import javafx.animation.Animation;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class NormalZombie extends Zombie {

	public NormalZombie() {
		//default stats
		super(20,2,25, "NormalZombie");
		createSprite();
	}
}
