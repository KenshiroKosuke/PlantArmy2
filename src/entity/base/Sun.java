package entity.base;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Sun {
	private static final Image IMAGE_SUN= new Image(ClassLoader.getSystemResource("Sun.png").toString());
	private ImageView SunImageView;
	public void Sun() {
		SunImageView = new ImageView(IMAGE_SUN);
	}
}
