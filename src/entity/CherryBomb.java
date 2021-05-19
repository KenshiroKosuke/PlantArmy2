package entity;

import application.NormalMode;
import entity.base.Explodable;
import entity.base.Plant;
import entity.base.Zombie;
import logic.FieldPane;
import logic.GameController;
import logic.Cell;
public class CherryBomb extends Plant implements Explodable{
	public CherryBomb() {
		super(99999,"CherryBomb");
	}
	public CherryBomb(int hp, String plantName) {
		super(hp, plantName);
		// TODO Auto-generated constructor stub
	}
	public void explode() {
		Thread thread = new Thread(new Runnable() {		
			@Override
			public void run() {
				// TODO Auto-generated method stub
				int row,column;
				try {
					Thread.sleep(2200);
					for(Zombie zombie:GameController.getCurrentZombies()) {
						row = zombie.getRow();
						column = zombie.checkGridXPosition();
						if(row==getY()||row==getY()+1||row==getY()-1) {
							if(column==getX()||column==getX()+1||column==getX()-1) {
								zombie.zombieKill("exploded");
							}
						}
					}
					FieldPane fieldPane = NormalMode.getField();
					Cell cell = (Cell) (fieldPane.getChildren().get(getY()*9+getX()));
					cell.removePlant();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		thread.start();
	}
	
}
