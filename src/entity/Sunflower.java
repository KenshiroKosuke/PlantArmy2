package entity;

import application.NormalMode;
import entity.base.Plant;
import entity.base.SunProducer;
import entity.base.Upgradeable;
import entity.base.sunProducable;
import logic.Cell;
import logic.FieldPane;
import logic.GameController;
import logic.ShopController;
import logic.UpgradeButton;

public class Sunflower extends SunProducer implements Upgradeable{
	
	public Sunflower() {
		super(10,"Sunflower",1,10);
		
	}
	public void upgrade() {
		if(ShopController.getSun()>=150) {
			System.out.println("upgraded");
			FieldPane fieldPane = NormalMode.getField();
			Cell cell = (Cell) (fieldPane.getChildren().get(getY()*9+getX()));
			cell.removePlant();
			cell.changePlant(new TwinSunflower());
			cell.getMyPlant().setX(FieldPane.getColumnIndex(cell));
			cell.getMyPlant().setY(FieldPane.getRowIndex(cell));
			GameController.getSunProducers().add((SunProducer) cell.getMyPlant());
			ShopController.setSun(ShopController.getSun()-cell.getMyPlant().getPrice());
			UpgradeButton.resetUpgradeButton();
			NormalMode.getShop().getChildren().get(6).setDisable(true);
			NormalMode.getShop().getChildren().get(6).setVisible(false);
			Thread thread = new Thread(new Runnable(){
				@Override
				public void run() {
					UpgradeButton.startRechargingTimer();
				}
			});
			thread.start();
		}
	}
}
