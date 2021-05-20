package logic;

import entity.base.Plant;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.effect.Blend;
import javafx.scene.effect.BlendMode;
import javafx.scene.effect.ColorInput;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

public class ShopPane extends GridPane{
	private static ObservableList<BuyPlantButton> buyPlantButtonList = FXCollections.observableArrayList();
	private static int timer = 0;
	public ShopPane() {
		this.setAlignment(Pos.CENTER);
		this.setHgap(10);
		this.setVgap(0);
		buyPlantButtonList.addAll(new BuyPlantButton("PeaShooter"),
								  new BuyPlantButton("Sunflower"),
								  new BuyPlantButton("SnowPeaShooter"),
								  new BuyPlantButton("Repeater"),
								  new BuyPlantButton("Walnut"),
								  new BuyPlantButton("CherryBomb")
								  );
		//add other plants here
		int i = 0;
		for (BuyPlantButton button : buyPlantButtonList) {
			setSwitchEnable(button);
			button.addEventHandler(MouseEvent.MOUSE_CLICKED, 
				new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent buy) {
						// TODO Auto-generated method stub
						if(!GameController.isUpgrading()) {
							if (ShopController.getSelectedButton() != null) {
								if (ShopController.getSelectedButton().getPlant() == button.getPlant()) {
									System.out.println("unselect");
									ShopController.getSelectedButton().unhighlight();
									ShopController.setSelectedButton(null);
								} else {
									System.out.println("swap button");
									setSelectedButton(button);
								}
							} else if (ShopController.getSun() >= button.getPlant().getPrice()) {
								//can be selected with current sun
								System.out.println("set new button");
								setSelectedButton(button);
							}
						}
					}
				}
			);
			this.add(button,0,i );
			i++;
		}
		this.add(new UpgradeButton(),0,i);
	}
	
	public void setSelectedButton(BuyPlantButton selectedButton) {
		resetButtonsBackGroundColor();
		selectedButton.highlight();
		ShopController.setSelectedButton(selectedButton);
	}
	public static void resetButtonsBackGroundColor() {
		for (BuyPlantButton button : buyPlantButtonList) {
			button.unhighlight();
		}
	}
	public void setSwitchEnable(Button button) {
		button.addEventHandler(MouseEvent.MOUSE_CLICKED,
				new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent  e) {
						// TODO fill in this method					
						if (ControlPane.isShovel_On())
							ControlPane.resetShovel();
					}
			});
	}
	
//	public void updateButtonStatus() {
//		Thread thread = new Thread(new Runnable(){
//			@Override
//			public void run() {
//				while(true) {
//					try {
//						Thread.sleep(1000);
//					} catch (InterruptedException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//					timer++;
//					for (BuyPlantButton button : buyPlantButtonList) {
//						
//					}
//				}
//			}
//		});
//		thread.start();
//	}
	
//	public static BuyPlantButton getButton(Plant plant) {
//		for (BuyPlantButton button : buyPlantButtonList) {
//			if (button.getPlant().getName() == plant.getName()) {
//				return button;
//			}
//		} 
//		return null;
//	}
}
