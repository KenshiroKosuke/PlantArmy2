package entity.base;

import application.Main;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import logic.FieldPane;
import logic.GameController;

public class Pea {
	private int speed;
	private int x,y;
	private int startingX;
	private ImageView PeaImageView;
	private boolean isPeaDead;
	private int fireRateTimer;
	private static final Image IMAGE_NORMAL_PEA= new Image(ClassLoader.getSystemResource("Pea.png").toString());
	private static final double PEA_WIDTH = IMAGE_NORMAL_PEA.getWidth();
	public Pea(int x,int y) {
		this.speed = 12;
		PeaImageView = new ImageView(IMAGE_NORMAL_PEA);
		PeaImageView.setVisible(false);
		this.y =  (int) ((Main.getHeight()-FieldPane.getFieldHeight())+(FieldPane.getFieldHeight()*y/5)-20); 
		this.startingX = (int)((Main.getWidth()-FieldPane.getFieldWidth())+(FieldPane.getFieldWidth()*x/9)+35);
		this.isPeaDead = true;
		this.fireRateTimer=0;
		}
	public boolean checkZombieCollision(Shooter shooter) {
		boolean collide = false;
		int zombieLocation;
		if(!isPeaDead) {
		for(Zombie zombie: GameController.getCurrentZombies()) {
			if(zombie.getRow()==shooter.getY()){
				zombieLocation = zombie.getX()+Zombie.getWidth()/2;
				if(zombieLocation>getX()&&zombieLocation<getX()+PEA_WIDTH ) {
					collide = true;
					zombie.damage();
					break;
				}
			}
		}
		}
		return collide;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getStartingX() {
		return startingX;
	}
	public ImageView getPeaImageView() {
		return PeaImageView;
	}
	public int getY() {
		return y;
	}
	public int getSpeed() {
		return speed;
	}
	public void setPeaImageView(ImageView peaImageView) {
		PeaImageView = peaImageView;
	}
	public boolean isPeaDead() {
		return isPeaDead;
	}
	public void setPeaDead(boolean isPeaDead) {
		this.isPeaDead = isPeaDead;
	}
	public int getFireRateTimer() {
		return fireRateTimer;
	}
	public void setFireRateTimer(int fireRateTimer) {
		this.fireRateTimer = fireRateTimer;
	}
	
	
}
