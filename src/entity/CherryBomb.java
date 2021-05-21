package entity;

import java.util.ConcurrentModificationException;

import application.NormalMode;
import entity.base.Explodable;
import entity.base.Plant;
import entity.base.Zombie;
import javafx.scene.media.AudioClip;
import logic.FieldPane;
import logic.GameController;
import logic.Cell;
public class CherryBomb extends Plant implements Explodable{
	private static AudioClip cherryBombSound = new AudioClip(ClassLoader.getSystemResource("audio/CherryBomb.mp3").toString());
	private static AudioClip prepareExplodeSound = new AudioClip(ClassLoader.getSystemResource("audio/PrepareExplode.mp3").toString());

	public CherryBomb() {
		super(99999,"CherryBomb");
	}
	public CherryBomb(int hp, String plantName) {
		super(hp, plantName);
		// TODO Auto-generated constructor stub
	}
	public void explode() {
		prepareExplodeSound.play();
		Thread thread = new Thread(new Runnable() {		
			@Override
			public void run() {
				// TODO Auto-generated method stub
				int row,column;
				try {
					Thread.sleep(2000);
					cherryBombSound.play(0.6);
					for(Zombie zombie:GameController.getCurrentZombies()) {
						row = zombie.getRow();
						column = zombie.checkGridXPosition();
						if(row==getY()||row==getY()+1||row==getY()-1) {
							if(column==getX()||column==getX()+1||column==getX()-1) {
								zombie.zombieKill("exploded");
							}
						}
					}
					Thread.sleep(400);
					FieldPane fieldPane = NormalMode.getField();
					Cell cell = (Cell) (fieldPane.getChildren().get(getY()*9+getX()));
					cell.removePlant();
				} catch (ConcurrentModificationException e) {
					// TODO Auto-generated catch block
					FieldPane fieldPane = NormalMode.getField();
					Cell cell = (Cell) (fieldPane.getChildren().get(getY()*9+getX()));
					cell.removePlant();
				} catch (InterruptedException e) {
					FieldPane fieldPane = NormalMode.getField();
					Cell cell = (Cell) (fieldPane.getChildren().get(getY()*9+getX()));
					cell.removePlant();
				}
			}
		});
		thread.start();
	}
	
}
