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
import entity.CherryBomb;
import entity.PeaShooter;
import entity.Repeater;
import entity.SnowPeaShooter;
import entity.Sunflower;
import entity.Walnut;
import entity.base.Plant;
public class BuyPlantButton extends Button {
	private Plant plant;
	//private String buttonUrl;
	public BuyPlantButton(String plantName) {
		super();
		switch(plantName) {
		case "PeaShooter": 		this.plant = new PeaShooter();		break;
		case "Sunflower" : 		this.plant = new Sunflower(); 		break;
		case "SnowPeaShooter": 	this.plant = new SnowPeaShooter();	break;
		case "Repeater": 		this.plant = new Repeater();		break;
		case "Walnut":			this.plant = new Walnut();			break;
		case "CherryBomb":		this.plant = new CherryBomb(); 		break;
		default			 : 		this.plant = null;					break;
		}
		//this.setPrefHeight(100);
		//this.setPrefWidth(200);
		String image_path = ClassLoader.getSystemResource("Button_"+this.plant.getName()+".png").toString();
	    ImageView view = new ImageView(new Image(image_path));
	    this.setGraphic(view);
	    this.setStyle("-fx-background-color: transparent;");
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
