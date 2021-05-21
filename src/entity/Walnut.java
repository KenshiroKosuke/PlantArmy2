package entity;
import application.NormalMode;
import entity.base.Plant;
import javafx.application.Platform;
import logic.Cell;

public class Walnut extends Plant{

	public Walnut() {
		super(70, "Walnut");
	}

//	public void update() {
//		boolean update = true;
//		System.out.println("updating");
//		while(update) {
//			if (this.getHp() <= 20) {
//				System.out.println("change profile walnut");
//				Thread thread = new Thread(()->{
//					int x = this.getX();
//					int y = this.getY();
//					Platform.runLater(new Runnable() {
//						@Override
//						public void run() {
//							((Cell) NormalMode.getField().getChildren().get(x+y*9)).changeGraphicPlant("Walnut_Half.gif");
//						}
//					});
//				});
//				thread.start();	
//				update= false;
//			}	
//		}
//	}

}
