package logic;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.effect.Blend;
import javafx.scene.effect.BlendMode;
import javafx.scene.effect.ColorInput;
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
import javafx.stage.Window;
import application.Main;
import entity.CherryBomb;
import entity.PeaShooter;
import entity.Repeater;
import entity.SnowPeaShooter;
import entity.Sunflower;
import entity.Walnut;
import entity.base.Plant;
public class BuyPlantButton extends Button {
	private Plant plant;
	private double rechargingTime;
	private double time = 0;
	private Blend rechargeEffect = new Blend(BlendMode.SRC_ATOP, null,new ColorInput(0, 0, 200, 100, Color.BLACK)
        ); 
	//private String buttonUrl;
	public BuyPlantButton(String plantName) {
		super();
		switch(plantName) {
		case "PeaShooter": 		this.plant = new PeaShooter();		this.rechargingTime = 10.0; break;
		case "Sunflower" : 		this.plant = new Sunflower(); 		this.rechargingTime = 10.0; break;
		case "SnowPeaShooter": 	this.plant = new SnowPeaShooter();	this.rechargingTime = 20.0; break;
		case "Repeater": 		this.plant = new Repeater();		this.rechargingTime = 20.0; break;
		case "Walnut":			this.plant = new Walnut();			this.rechargingTime = 35.0; break;
		case "CherryBomb":		this.plant = new CherryBomb(); 		this.rechargingTime = 40.0; break;
		default			 : 		this.plant = null;					this.rechargingTime = 10.0; break;
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
	
	public void fillButton(double opacity) {
		this.setEffect(rechargeEffect);
		rechargeEffect.setOpacity(opacity);
	}
	
	public void startRechargingTimer() {
		// TODO Auto-generated method stub
		ShopController.setSelectedButton(null);
		System.out.println("start recharging");
		time = 0;
		while(true) {
			try {
				Thread.sleep(1000);
				time += 1.0;
				System.out.println("TIME: "+time+"/"+rechargingTime);
				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						//If the game isn't over then fill the button
						fillButton(1.0-(time/rechargingTime));
					}
				});
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				System.out.println("ERROR IN RECHARGING");
				e.printStackTrace();
			}
			if (time >= rechargingTime || !Main.getPrimaryStage().isShowing() ||  GameController.is_over()) {
				this.setEffect(null);
				this.setDisable(false);
				break;
			}
		}
		
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
	public double getRechargingTime() {
		return rechargingTime;
	}

	public double getTime() {
		return time;
	}

	public void setTime(double time) {
		this.time = time;
	}

	public void setRechargingTime(double rechargingTime) {
		this.rechargingTime = rechargingTime;
	}
	
}
