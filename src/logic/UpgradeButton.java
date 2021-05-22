package logic;

import application.Main;
import application.NormalMode;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.effect.Blend;
import javafx.scene.effect.BlendMode;
import javafx.scene.effect.ColorInput;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

public class UpgradeButton extends Button {

	private static ImageView view = new ImageView(
			new Image(ClassLoader.getSystemResource("Button_Upgrade.png").toString()));
	private static int time = 0;
	private static double rechargingTime = 30;

	public UpgradeButton() {
		view.setFitHeight(60);
		view.setPreserveRatio(true);
		this.setGraphic(view);
		this.setHeight(60);
		this.setWidth(BASELINE_OFFSET_SAME_AS_HEIGHT);
		this.setStyle("-fx-background-color: transparent;");
		this.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if (GameController.isUpgrading()) {
					resetUpgradeButton();
				} else {
					GameController.resetAnyButton();
					GameController.setUpgrading(true);
					if (ShopController.getSelectedButton() != null) {
						ShopController.getSelectedButton().unhighlight();
						ShopController.setSelectedButton(null);
					}
					Blend greenEffect = new Blend(BlendMode.GREEN, null,
							new ColorInput(0, 0, view.getBaselineOffset(), view.getFitHeight(), Color.LIGHTSEAGREEN));
					greenEffect.setOpacity(0.8);
					view.setEffect(greenEffect);
				}
			}
		});
	}

	public static void resetUpgradeButton() {
		GameController.setUpgrading(false);
		view.setEffect(null);
	}

	public static void startRechargingTimer() {
		// TODO Auto-generated method stub
		ShopController.setSelectedButton(null);
		time = 0;
		while (!GameController.is_over()) {
			try {
				Thread.sleep(1000);
				time += 1.0;
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				System.out.println("ERROR IN RECHARGING");
				e.printStackTrace();
			}
			if (time >= rechargingTime + GameController.getWave() * 2 || !Main.getPrimaryStage().isShowing()) {
				// NormalMode.getShop().getChildren().get(6).setEffect(null);
				NormalMode.getShop().getChildren().get(6).setVisible(true);
				NormalMode.getShop().getChildren().get(6).setDisable(false);
				break;
			}
		}

	}

}
