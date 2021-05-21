package application;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.AudioClip;
import logic.GameController;


public class GameClear extends AnchorPane {

	public GameClear() {
		Label scoreLabel = new Label();
		scoreLabel.setText("Congrats!!\nAll "+GameController.getKillCount()+" zombies have been eliminated!");
		
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