package logic;

import application.Main;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.effect.Blend;
import javafx.scene.effect.BlendMode;
import javafx.scene.effect.ColorInput;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.paint.Color;

public class UpgradeButton extends Button {
	
	private static ImageView view = new ImageView(new Image(ClassLoader.getSystemResource("Button_Upgrade.png").toString()));
	
	public UpgradeButton() {
	    view.setFitHeight(60);
	    view.setPreserveRatio(true);
	    this.setGraphic(view);
	    //this.setPrefHeight(30);
	    //this.setMaxHeight(30);
	    this.setStyle("-fx-background-color: transparent;");
		this.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if(GameController.isUpgrading()) {
					resetUpgradeButton();
				}else {
					GameController.resetAnyButton();
					GameController.setUpgrading(true);
					System.out.println("upgrading = true");
					if(ShopController.getSelectedButton()!=null) {
						ShopController.getSelectedButton().unhighlight();
						ShopController.setSelectedButton(null);
					}
					Blend greenEffect = new Blend(
		                    BlendMode.GREEN,
		                    null,
		                    new ColorInput(
		                            0,
		                            0,
		                            view.getBaselineOffset(),
		                            view.getFitHeight(),
		                            Color.LIGHTSEAGREEN
		                    )
		                );
					greenEffect.setOpacity(0.8);
		            view.setEffect(greenEffect);
				}
			}
		});
	}
	
	public static void resetUpgradeButton() {
		GameController.setUpgrading(false);
		view.setEffect(null);
		////////////////////////////
		System.out.println("upgrading = false");
		////////////////////////////
	}
	
}
