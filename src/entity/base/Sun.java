package entity.base;

import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.AudioClip;
import logic.ShopController;

public class Sun {
	private static final Image IMAGE_SUN = new Image(ClassLoader.getSystemResource("Sun.png").toString());
	private static AudioClip sunCollectSound = new AudioClip(
			ClassLoader.getSystemResource("audio/SunCollect.mp3").toString());
	private ImageView SunImageView;

	public Sun() {
		SunImageView = new ImageView(IMAGE_SUN);
		SunImageView.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
				sunCollectSound.play();
				SunImageView.setDisable(true);
				SunImageView.setVisible(false);
				ShopController.setSun(ShopController.getSun() + 50);
				System.out.println(ShopController.getSun());
			}
		});
	}

	public ImageView getSunImageView() {
		return SunImageView;
	}

	public void setSunImageView(ImageView sunImageView) {
		SunImageView = sunImageView;
	}
}
