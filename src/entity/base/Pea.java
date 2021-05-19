package entity.base;

import application.Main;
import entity.SnowPeaShooter;
import javafx.scene.effect.Blend;
import javafx.scene.effect.BlendMode;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.ColorInput;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
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
	private static final Image IMAGE_SNOW_PEA =  new Image(ClassLoader.getSystemResource("SnowPea.png").toString());
	private static final double PEA_WIDTH = IMAGE_NORMAL_PEA.getWidth();
	public Pea(int x,int y,String type) {
		this.speed = 12;
		if(type.equals("snow")) {
			PeaImageView = new ImageView(IMAGE_SNOW_PEA);
		}else if(type.equals("normal")) {
			PeaImageView = new ImageView(IMAGE_NORMAL_PEA);
		}
		PeaImageView.setVisible(false);
		PeaImageView.setDisable(true);
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
			if(!zombie.isExploded()) {
				if(zombie.getRow()==shooter.getY()){
					zombieLocation = (int) (zombie.getX()+Zombie.getWidth()/2);
					if(zombieLocation>getX()&&zombieLocation<getX()+PEA_WIDTH ) {
						collide = true;
						zombie.damage();
						if(shooter instanceof SnowPeaShooter) {
							zombie.setFrozenFactor(1);
							zombie.setFreezeTimer(0);
	                        ColorAdjust monochrome = new ColorAdjust();
	                        monochrome.setContrast(-1.0);
	                        monochrome.setSaturation(-0.5);
	                        Blend snowEffect = new Blend(
	                            BlendMode.SRC_ATOP,
	                            null,
	                            new ColorInput(
	                                    0,
	                                    0,
	                                    zombie.getImageView().getFitWidth(),
	                                    zombie.getImageView().getFitHeight(),
	                                    Color.BLUE
	                            )
	                        );
	                        snowEffect.setOpacity(0.4);
	                        zombie.getImageView().setEffect(snowEffect);
	                    }
						setPeaDead(true);
						break;
					}
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
