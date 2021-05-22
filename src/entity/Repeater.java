package entity;

import application.NormalMode;
import entity.base.Pea;
import entity.base.Shooter;
import entity.base.Upgradeable;
import logic.Cell;
import logic.FieldPane;
import logic.GameController;
import logic.ShopController;
import logic.UpgradeButton;

public class Repeater extends Shooter implements Upgradeable {

	public Repeater() {
		super(10, "Repeater", 2);
		this.fireRate = 1000;
	}

	@Override
	public void upgrade() {
		if (ShopController.getSun() >= 250) {
			FieldPane fieldPane = NormalMode.getField();
			Cell cell = (Cell) (fieldPane.getChildren().get(getY() * 9 + getX()));
			cell.removePlant();
			cell.changePlant(new GatlingPea());
			cell.getMyPlant().setX(FieldPane.getColumnIndex(cell));
			cell.getMyPlant().setY(FieldPane.getRowIndex(cell));
			for (int i = 0; i < ((Shooter) cell.getMyPlant()).getAmmo(); i++) {
				((Shooter) cell.getMyPlant()).getPeaList()
						.add(new Pea(cell.getMyPlant().getX(), cell.getMyPlant().getY(), "normal"));
			}
			GameController.getShooters().add((Shooter) cell.getMyPlant());
			ShopController.setSun(ShopController.getSun() - cell.getMyPlant().getPrice());
			UpgradeButton.resetUpgradeButton();
			NormalMode.getShop().getChildren().get(6).setDisable(true);
			NormalMode.getShop().getChildren().get(6).setVisible(false);
			Thread thread = new Thread(new Runnable() {
				@Override
				public void run() {
					UpgradeButton.startRechargingTimer();
				}
			});
			thread.start();
		}
	}
}
