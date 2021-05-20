package entity;

import application.NormalMode;
import entity.base.Pea;
import entity.base.Shooter;
import entity.base.SunProducer;
import entity.base.Upgradeable;
import logic.Cell;
import logic.FieldPane;
import logic.GameController;
import logic.ShopController;

public class Repeater extends Shooter implements Upgradeable {
	
	private int atk;

	public Repeater() {
		super(10,"Repeater",2);
		this.fireRate = 1000;
		this.atk = 5;
	}

	@Override
	public void upgrade() {
		if(ShopController.getSun()>=250) {
			System.out.println("upgraded");
			FieldPane fieldPane = NormalMode.getField();
			Cell cell = (Cell) (fieldPane.getChildren().get(getY()*9+getX()));
			Shooter shooter = (Shooter) cell.getMyPlant();
			cell.removePlant();
			cell.changePlant(new GatlingPea());
			cell.getMyPlant().setX(FieldPane.getColumnIndex(cell));
			cell.getMyPlant().setY(FieldPane.getRowIndex(cell));
			for(int i=0;i<((Shooter) cell.getMyPlant()).getAmmo();i++) {
				((Shooter) cell.getMyPlant()).getPeaList().add(new Pea(cell.getMyPlant().getX(),cell.getMyPlant().getY(),"normal"));
			}
			GameController.getShooters().add((Shooter) cell.getMyPlant());
			ShopController.setSun(ShopController.getSun()-cell.getMyPlant().getPrice());
		}
	}
}
