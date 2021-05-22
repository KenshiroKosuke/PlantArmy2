package logic;

import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.effect.Blend;
import javafx.scene.effect.BlendMode;
import javafx.scene.effect.ColorInput;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
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
	private Blend rechargeEffect = new Blend(BlendMode.SRC_ATOP, null, new ColorInput(0, 0, 200, 100, Color.BLACK));

	public BuyPlantButton(String plantName) {
		super();
		switch (plantName) {
		case "PeaShooter":
			this.plant = new PeaShooter();
			this.rechargingTime = 10.0;
			break;
		case "Sunflower":
			this.plant = new Sunflower();
			this.rechargingTime = 10.0;
			break;
		case "SnowPeaShooter":
			this.plant = new SnowPeaShooter();
			this.rechargingTime = 20.0;
			break;
		case "Repeater":
			this.plant = new Repeater();
			this.rechargingTime = 20.0;
			break;
		case "Walnut":
			this.plant = new Walnut();
			this.rechargingTime = 30.0;
			break;
		case "CherryBomb":
			this.plant = new CherryBomb();
			this.rechargingTime = 40.0;
			break;
		default:
			this.plant = null;
			this.rechargingTime = 10.0;
			break;
		}
		String image_path = ClassLoader.getSystemResource("Button_" + this.plant.getName() + ".png").toString();
		ImageView view = new ImageView(new Image(image_path));
		this.setGraphic(view);
		this.setStyle("-fx-background-color: transparent;");
	}

	public void fillButton(double opacity) {
		this.setEffect(rechargeEffect);
		rechargeEffect.setOpacity(opacity);
	}

	public void startRechargingTimer() {
		// TODO Auto-generated method stub
		ShopController.setSelectedButton(null);
		time = 0;
		while (!GameController.is_over()) {
			try {
				Thread.sleep(1000);
				time += 1.0;
				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						fillButton(1.0 - (time / rechargingTime));
					}
				});
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				System.out.println("ERROR IN RECHARGING");
			}
			if (time >= rechargingTime || !Main.getPrimaryStage().isShowing()) {
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
}