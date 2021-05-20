package logic;

import java.util.Random;

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
import entity.base.SunProducer;
import entity.base.Upgradeable;
import entity.base.sunProducable;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class Cell extends Pane {
	private Plant myPlant;
	private Tooltip tooltip;
	private static AudioClip plantSound = new AudioClip(ClassLoader.getSystemResource("audio/Plant_Sound.wav").toString());

	//	public static Random random = new Random();
	private int c;
	
	public Cell() {
		this.setPrefHeight(FieldPane.getFieldHeight()/5);
		this.setPrefWidth(FieldPane.getFieldWidth()/9);
		this.setMinHeight(10);
		this.setMinWidth(10);
		this.setPadding(new Insets(10,0,0,0)); //top right bottom left
		//this.myPlant = null;
//		if (random.nextInt(10) > 8)
//			setPlant(new Pea());
//		this.setUpTooltip();
		
		this.addEventHandler(MouseEvent.MOUSE_CLICKED, 
				new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent  e) {
						// TODO fill in this method					
						onClickPlanting();
					}
			});
		
		this.setBorder(new Border(new BorderStroke(null, BorderStrokeStyle.SOLID, 
					CornerRadii.EMPTY, BorderWidths.DEFAULT)));
	}
	private void onClickPlanting() {
		if(GameController.isUpgrading()) {
			if(myPlant instanceof Upgradeable) {
				((Upgradeable) myPlant).upgrade();
				GameController.setUpgrading(false);
			}
		}else {
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
					//if type shooter
					if (myPlant instanceof Shooter) {
						String type;
						if(myPlant instanceof SnowPeaShooter) {
							type = "snow";
						}else {
							type="normal";
						}
						for(int i=0;i<((Shooter) myPlant).getAmmo();i++) {
							((Shooter) myPlant).getPeaList().add(new Pea(myPlant.getX(),myPlant.getY(),type));
						}
						GameController.getShooters().add((Shooter) myPlant);
					}else if(myPlant instanceof SunProducer) {
						GameController.getSunProducers().add((SunProducer) myPlant);
					}else if(myPlant instanceof Explodable) {
						((CherryBomb) myPlant).explode();
					}
					//peashooter.startShooting();
					changePlant(myPlant);
				}
			}
			if (ControlPane.isShovel_On()) {
				////remove plant and remove image
				System.out.println("Try to remove plant");
				plantSound.play(0.3);
				this.removePlant();
				
			}
		}
	}
	
	public void changePlant(Plant myPlant) {
		//call this to set plant
		plantSound.play(0.3);
		this.myPlant = myPlant;
		String image_path = ClassLoader.getSystemResource(myPlant.getUrl()).toString();
		changeGraphicPlant(image_path);
	}
	
	public boolean setPlant(Plant e) {
		if(isEmpty()) {
			changePlant(e);
			return true;
		}else {
			return false;
		}
	}
	
	public void changeGraphicPlant(String image_path) {
		//call this to set graphic and do nothing to the actual plant of this cell
		Image img = new Image(image_path);
		BackgroundSize bgSize = new BackgroundSize(this.getPrefWidth(),this.getPrefHeight(),false,false,false,false);
		BackgroundImage bgImg = new BackgroundImage(img, null, null,BackgroundPosition.CENTER, bgSize);
		BackgroundImage[] bgImgA = {bgImg};
		this.setBackground(new Background(null,bgImgA));
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
		if(myPlant instanceof Shooter) {
			GameController.removeShooterFromList((Shooter)myPlant);
			for(Pea pea: ((Shooter)myPlant).getPeaList()) {
				GameController.getPeaToRemove().add(pea);
			}
		}else if(myPlant instanceof SunProducer) {
			GameController.getSunProducers().remove((SunProducer) myPlant);
		}		
		myPlant = null;
		this.setBackground(null);
	}

	public String getPlantImage() {
		return getPlantImage();
	}
	
	private void setUpTooltip() {
		tooltip = new Tooltip();
		tooltip.setFont(new Font(12));
		this.setOnMouseMoved((MouseEvent e) -> {
			if (myPlant != null)
			tooltip.show(this, e.getScreenX(), e.getScreenY()+10);
		});
		this.setOnMouseExited((MouseEvent e) -> {
			tooltip.hide();
		});
		
	}

	
	
}
