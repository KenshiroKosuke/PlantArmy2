package entity.base;

import java.util.Random;

import application.Main;
import javafx.animation.Animation;
import javafx.animation.PathTransition;
import javafx.animation.Transition;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public abstract class Zombie {
	private int hp, speed, coinDrop, x, y, roll;
	private double height;
	private String name;
	//public static Random random = new Random();
	ImageView imageView;
	boolean isDead;
	

	public Zombie(int hp, int speed, int coinDrop, String zombieName) {
		this.isDead = false;
		this.hp = hp;
		this.speed = speed;
		this.coinDrop = coinDrop;
		this.name = zombieName;
		this.x = 0;
		this.y = 0;
	}

	private static final Image IMAGE_NORMAL_ZOMBIE = new Image(ClassLoader.getSystemResource("NormalZombie_Idle.gif").toString());
	private static final Image IMAGE_CONE_ZOMBIE = new Image(ClassLoader.getSystemResource("ConeZombie_Idle.gif").toString());
	//private static final int COLUMNS  =   12;
	//private static final int COUNT    = 12;
	//private static final int OFFSET_X =  0;
	//private static final int OFFSET_Y =  0;
	//private static final int WIDTH    = 85;
	//private static final int HEIGHT   = 140;
	
	public void createSprite() {
		if(isDead) {
			//zombie is now dead
			imageView.setImage(null);
			//animation.stop();
			//dead image
			//imageView = new ImageView(); 
		}
		else {
			switch(name) {
			case "NormalZombie" : imageView = new ImageView(IMAGE_NORMAL_ZOMBIE); height=130; break	;
			case "ConeZombie"	: imageView = new ImageView(IMAGE_CONE_ZOMBIE); height = 160; break	;
			default: imageView = new ImageView(IMAGE_NORMAL_ZOMBIE);					;
			}
		}
		//imageView.setViewport(new Rectangle2D(OFFSET_X, OFFSET_Y, WIDTH, HEIGHT));
		//imageView.relocate((double)(getX()), (double)(getY()));
		

	}

	public ImageView getImageView() {
		return imageView;
	}

	public void setImageView(ImageView imageView) {
		this.imageView = imageView;
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public int getCoinDrop() {
		return coinDrop;
	}

	public void setCoinDrop(int coinDrop) {
		this.coinDrop = coinDrop;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isDead() {
		return isDead;
	}

	public void setDead(boolean isDead) {
		this.isDead = isDead;
	}

	public void update() {
		// TODO Auto-generated method stub
		// +speed, x, y etc
		if(!isDead) {
			this.x -= this.speed;
			}
	}

	public int getRoll() {
		return roll;
	}

	public void setRoll(int roll) {
		this.roll = roll;
	}
	
}