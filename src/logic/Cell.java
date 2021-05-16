package logic;

import java.util.Random;

import entity.Peashooter;
import entity.base.Pea;
import entity.base.Plant;
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
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class Cell extends Pane {
	private Plant myPlant;
	private Tooltip tooltip;
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
						onClickHandler();
					}
			});
		
		this.setBorder(new Border(new BorderStroke(null, BorderStrokeStyle.SOLID, 
					CornerRadii.EMPTY, BorderWidths.DEFAULT)));
	}
	private void onClickHandler() {
		if (ShopController.getSelectedButton() != null) {
			Plant selectedPlant = ShopController.getSelectedButton().getPlant();
			if (selectedPlant.getName().equals("DestroyTool")) {
				myPlant = null;
				//remove plant
			} else if (myPlant == null) {
				myPlant = selectedPlant;
				ShopController.boughtPlant();
				
				Peashooter peashooter = new Peashooter();
				peashooter.setX(FieldPane.getColumnIndex(this));
				peashooter.setY(FieldPane.getRowIndex(this));
				//if type shooter
				for(int i=0;i<peashooter.getAmmo();i++) {
					peashooter.getPeaList().add(new Pea(peashooter.getX(),peashooter.getY()));
				}
				peashooter.startShooting(); //ź
				changePlant(peashooter);
				//if type shooter
				GameController.getShooters().add(peashooter);
			}
		}
	}
	public boolean setPlant(Plant e) {
		if(isEmpty()) {
			changePlant(e);
			return true;
		}else {
			return false;
		}
	}
	
	
	public boolean isEmpty() {
		if (myPlant == null)
			return true;
		return false;
	}

	public Plant getMyPlant() {
		return myPlant;
	}

	public void changePlant(Plant myPlant) {
		this.myPlant = myPlant;
		String image_path = ClassLoader.getSystemResource(myPlant.getUrl()).toString();
		Image img = new Image(image_path);
		BackgroundSize bgSize = new BackgroundSize(this.getPrefWidth(),this.getPrefHeight(),false,false,false,false);
		BackgroundImage bgImg = new BackgroundImage(img, null, null,BackgroundPosition.CENTER, bgSize);
		BackgroundImage[] bgImgA = {bgImg};
		this.setBackground(new Background(null,bgImgA));
	}

	public void removePlant() {
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
