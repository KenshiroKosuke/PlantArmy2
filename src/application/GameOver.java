package application;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.AudioClip;
import logic.GameController;


public class GameOver extends AnchorPane {

	public GameOver() {
		Label scoreLabel = new Label();
		scoreLabel.setText("Zombie killed : "+GameController.getKillCount());
		
		Button backButton = new Button();
		backButton.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				GameController.setKillCount(0);
				Main.getGameOverSound().stop();
				Main.startMainMenu();
			}
		});

		this.getChildren().addAll(scoreLabel,backButton);
	}

}
