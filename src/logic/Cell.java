package logic;

import java.util.ArrayList;

import entity.CherryBomb;
import entity.PeaShooter;
import entity.Repeater;
import entity.SnowPeaShooter;
import entity.Sunflower;
import entity.Walnut;
import entity.base.Explodable;
import entity.base.Pea;
import entity.base.Plant;
import entity.base.Shooter;
import entity.base.Sun;
import entity.base.SunProducer;
import entity.base.Upgradeable;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;
import javafx.scene.media.AudioClip;

public class Cell extends Pane {
	private Plant myPlant;
	private static AudioClip plantSound = new AudioClip(
			ClassLoader.getSystemResource("audio/Plant_Sound.wav").toString());

	public Cell() {
		this.setPrefHeight(FieldPane.getFieldHeight() / 5);
		this.setPrefWidth(FieldPane.getFieldWidth() / 9);
		this.setMinHeight(10);
		this.setMinWidth(10);
		this.setPadding(new Insets(10, 0, 0, 0));
		this.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				// TODO fill in this method
				plantOnClick();
			}
		});

	}

	public void plantOnClick() {
		if (GameController.isUpgrading()) {
			if (myPlant instanceof Upgradeable) {
				((Upgradeable) myPlant).upgrade();
			}
		} else {
			if (ShopController.getSelectedButton() != null) {
				Plant selectedPlant = ShopController.getSelectedButton().getPlant();
				if (myPlant == null) {
					ShopController.boughtPlant();
					if (selectedPlant.getName().equals("PeaShooter")) {
						myPlant = new PeaShooter();
					} else if (selectedPlant.getName() == "Sunflower") {
						myPlant = new Sunflower();
					} else if (selectedPlant.getName() == "SnowPeaShooter") {
						myPlant = new SnowPeaShooter();
					} else if (selectedPlant.getName() == "Repeater") {
						myPlant = new Repeater();
					} else if (selectedPlant.getName() == "Walnut") {
						myPlant = new Walnut();
					} else if (selectedPlant.getName() == "CherryBomb") {
						myPlant = new CherryBomb();
					}
					myPlant.setX(FieldPane.getColumnIndex(this));
					myPlant.setY(FieldPane.getRowIndex(this));
					if (myPlant instanceof Shooter) {
						String type;
						if (myPlant instanceof SnowPeaShooter) {
							type = "snow";
						} else {
							type = "normal";
						}
						for (int i = 0; i < ((Shooter) myPlant).getAmmo(); i++) {
							((Shooter) myPlant).getPeaList().add(new Pea(myPlant.getX(), myPlant.getY(), type));
						}
						GameController.getShooters().add((Shooter) myPlant);
					} else if (myPlant instanceof SunProducer) {
						GameController.getSunProducers().add((SunProducer) myPlant);
					} else if (myPlant instanceof Explodable) {
						((CherryBomb) myPlant).explode();
					}
					changePlant(myPlant);
				}
			}
			if (ControlPane.isShovel_On()) {
				//// remove plant and remove image
				plantSound.play(0.3);
				this.removePlant();

			}
		}
	}

	public void changePlant(Plant myPlant) {
		// call this to set plant
		plantSound.play(0.3);
		this.myPlant = myPlant;
		String image_path = ClassLoader.getSystemResource(myPlant.getUrl()).toString();
		changeGraphicPlant(image_path);
	}

	public void changeGraphicPlant(String image_path) {
		// call this to set graphic and do nothing to the actual plant of this cell
		Image img = new Image(image_path);
		BackgroundSize bgSize = new BackgroundSize(this.getPrefWidth(), this.getPrefHeight(), false, false, false,
				false);
		BackgroundImage bgImg = new BackgroundImage(img, null, null, BackgroundPosition.CENTER, bgSize);
		BackgroundImage[] bgImgA = { bgImg };
		this.setBackground(new Background(null, bgImgA));
	}

	public boolean isEmpty() {
		if (myPlant == null)
			return true;
		return false;
	}

	public Plant getMyPlant() {
		return myPlant;
	}

	public void removePlant() {
		if (myPlant != null) {
			if (myPlant instanceof Shooter) {
				GameController.removeShooterFromList((Shooter) myPlant);
				for (Pea pea : ((Shooter) myPlant).getPeaList()) {
					GameController.getPeaToRemove().add(pea);
				}
			} else if (myPlant instanceof SunProducer) {
				GameController.getSunProducers().remove((SunProducer) myPlant);
				int sunTime = ((SunProducer) myPlant).getSunProduceTime();
				int sunTimeLeft = sunTime - ((SunProducer) myPlant).getSunProduceTimer();
				ArrayList<Sun> suns = ((SunProducer) myPlant).getSunList();
				if (sunTimeLeft > sunTime / 2) {
					Thread thread = new Thread(new Runnable() {
						@Override
						public void run() {
							// TODO Auto-generated method stub
							try {
								Thread.sleep(1650 * (sunTimeLeft - sunTime / 2));
								for (Sun sun : suns) {
									GameController.getSunToRemove().add(sun);
								}
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					});
					thread.start();
				}
			}
			myPlant = null;
			this.setBackground(null);
		}
	}

	public String getPlantImage() {
		return getPlantImage();
	}

}
