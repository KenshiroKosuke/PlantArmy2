package logic;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import entity.PeaShooter;
import entity.Sunflower;
import entity.base.Plant;
public class BuyPlantButton extends Button {
	private Plant plant;
	private String buttonUrl;
	public BuyPlantButton(String plantName) {
		super();
		switch(plantName) {
		case "PeaShooter": this.plant = new PeaShooter(); this.buttonUrl = "Button_PeaShooter.png";	break;
		case "Sunflower" : this.plant = new Sunflower(); this.buttonUrl = "Button_Sunflower.png";	break;
		default			 : this.plant = null;				break;
		}
		//this.setPrefHeight(100);
		//this.setPrefWidth(200);
		String image_path = ClassLoader.getSystemResource(buttonUrl).toString();
	    ImageView view = new ImageView(new Image(image_path));
	    this.setGraphic(view);
	    this.setStyle("-fx-background-color: transparent;");
		//use plant name to set img
	    // ---> They are different images so let's put each button images here
		//this.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
		//this.setBorder(new Border( new BorderStroke(Color.GRAY, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
	}
	public void highlight() {
		this.setOpacity(0.5);
	}

	public void unhighlight() {
		//this.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
		this.setOpacity(1.0);
	}
	public Plant getPlant() {
		return plant;
	}
	public void setPlant(Plant plant) {
		this.plant = plant;
	}
	
}
