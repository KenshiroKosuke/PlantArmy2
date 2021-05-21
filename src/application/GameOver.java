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
import javafx.scene.layout.Region;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import logic.GameController;


public class GameOver extends AnchorPane {

	public GameOver() {
		String image_path = ClassLoader.getSystemResource("GameOver.png").toString();
		Image img = new Image(image_path);
		BackgroundSize bgSize = new BackgroundSize(Main.getWidth(),Main.getHeight(),false,false,false,false);
		BackgroundImage bgImg = new BackgroundImage(img, null, null, null, bgSize);
		BackgroundImage[] bgImgA = {bgImg};
		this.setBackground(new Background(null,bgImgA));
		
		Text scoreText = new Text();
		scoreText.setText(""+GameController.getWave()+"."+GameController.getWaveType()+"\n"+GameController.getKillCount());
		scoreText.setTextAlignment(TextAlignment.RIGHT);
		Font font = Font.font("EucrosiaDSE", FontWeight.NORMAL, FontPosture.REGULAR, 100);
		scoreText.setFont(font);
		scoreText.setLineSpacing(40);
		scoreText.setFill(Color.WHITE);
		scoreText.relocate(740, 300);
		
		this.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
				GameController.setKillCount(0);
				GameController.setWave(1);
				GameController.setWaveType(0);
				Main.getGameOverSound().stop();
				Main.startMainMenu();
			}
		});
		this.getChildren().add(scoreText);
	}

}
