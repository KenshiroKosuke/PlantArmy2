package application;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.media.AudioClip;
import logic.GameController;


public class GameClear extends AnchorPane {

	public GameClear() {
		String image_path = ClassLoader.getSystemResource("GameClear.png").toString();
		Image img = new Image(image_path);
		BackgroundSize bgSize = new BackgroundSize(Main.getWidth(),Main.getHeight(),false,false,false,false);
		BackgroundImage bgImg = new BackgroundImage(img, null, null, null, bgSize);
		BackgroundImage[] bgImgA = {bgImg};
		this.setBackground(new Background(null,bgImgA));
		
		this.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
				GameController.setKillCount(0);
				GameController.setWave(1);
				GameController.setWaveType(0);
				Main.getGameClearSound().stop();
				Main.startMainMenu();
			}
		});
	}

}