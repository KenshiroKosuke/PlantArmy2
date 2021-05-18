package entity.base;

import java.util.Random;

import application.Main;
import application.NormalMode;
import entity.Walnut;
import javafx.animation.Animation;
import javafx.animation.PathTransition;
import javafx.animation.Transition;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;
import logic.Cell;
import logic.FieldPane;
import logic.GameController;

public abstract class Zombie {
	private int hp, speed, coinDrop, x, y, row;
	private double height;
	private String name;
	private boolean isEating;
	private ImageView imageView;
	private boolean isDead;
	private Cell eatingCell;
	

	public Zombie(int hp, int speed, int coinDrop, String zombieName) {
		this.isDead = false;
		this.hp = hp;
		this.speed = speed;
		this.coinDrop = coinDrop;
		this.name = zombieName;
		this.x = 0;
		this.y = 0;
		this.isEating = false;
		this.isDead = false;
	}

	private static final Image IMAGE_NORMAL_ZOMBIE = new Image(ClassLoader.getSystemResource("NormalZombie_Idle.gif").toString());
	private static final Image IMAGE_CONE_ZOMBIE = new Image(ClassLoader.getSystemResource("ConeZombie_Idle.gif").toString());
	//private static final int COLUMNS  =   12;
	//private static final int COUNT    = 12;
	//private static final int OFFSET_X =  0;
	//private static final int OFFSET_Y =  0;
	private static final int WIDTH    = 85;
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
	public static int getWidth() {
		return WIDTH;
	}
	public int checkGridXPosition() {
		return (int)( (this.x-(Main.getWidth()-FieldPane.getFieldWidth())+this.WIDTH/1.25) / (FieldPane.getFieldWidth()/9)) ;
	}
	public int CheckPlantCollision(){
		FieldPane fieldPane = NormalMode.getField();
		ObservableList<Node> childrens = fieldPane.getChildren();
		int xPos = this.checkGridXPosition();
		try {
			if(xPos<9) {
				Cell thisCell = (Cell) childrens.get(this.row*9+xPos);
				Cell nextCell;
				if(xPos!=0) {
					nextCell = (Cell) childrens.get(this.row*9+xPos -1);
				}else {
					nextCell = null;
			}
				if(!thisCell.isEmpty()) {
					eatingCell = thisCell;
					return 1;
				}else if(nextCell!=null) {
					if(! nextCell.isEmpty()) {
						eatingCell = nextCell;
						return 2;
					}
				}
				//0 = no plant
				//1 = plant in this cell
				//2 = plant in next cell
				return 0;
			}
		} catch (IndexOutOfBoundsException e) {
			// TODO Auto-generated catch block
			System.out.println("Zombie has arrived at our door!");
			GameController.setGameOver();
		}
		return 0;
	}
	public void damage() {
		if(getHp()-5>0) {
			setHp(getHp()-5);
			System.out.println(getHp());
		}else {
			zombieKill("normal");
		}
	}
	public void zombieKill(String S) {
		if(S.equals("normal")) {
			setDead(true);
			//GameController.getCurrentZombies().remove(this);
		}else if(S.equals("exploded")) {
			//code for bombed
		}
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
			if(CheckPlantCollision()==1) {
				//call eat method
				System.out.println(CheckPlantCollision());
				if(!isEating)
					eat();
			}else if(CheckPlantCollision()==2) {
				if(((int)(this.x-(Main.getWidth()+this.checkGridXPosition()*FieldPane.getFieldWidth()/9)))==0) {
					//call eat method
					if(!isEating)
						eat();
				}else {
					this.x -= this.speed;
				}
				System.out.println(CheckPlantCollision());

			}else {
				this.x -= this.speed;
			}
		}
		if(this.x-(Main.getWidth()-FieldPane.getFieldWidth()-100) < 0) {
			// YOU LOSE
			System.out.println("Zombie has arrived at our door!");
			GameController.setGameOver();
		}
	}
	
	public void eat() {
		isEating = true;
		Thread thread = new Thread(new Runnable(){
			@Override
			public void run() {
				try {		
					while(eatingCell.getMyPlant()!=null&&isEating&&!isDead) {
						Thread.sleep(400);
							if(eatingCell.getMyPlant()!=null) {
								if(eatingCell.getMyPlant().getHp()>0) {
									eatingCell.getMyPlant().setHp(eatingCell.getMyPlant().getHp() - 1);
									//////////////////////////////////////////////////////////////
									System.out.println("HP = "+(eatingCell.getMyPlant().getHp()));
									//////////////////////////////////////////////////////////////
									if (eatingCell.getMyPlant() instanceof Walnut && eatingCell.getMyPlant().getHp()==20) {
										//eatingCell.getMyPlant()
										Platform.runLater(new Runnable() {
											@Override
											public void run() {
												eatingCell.changeGraphicPlant("Walnut_Half.gif");
											}
										});
									}
								}else {
								if(eatingCell.getMyPlant() instanceof Shooter) {
									for(Pea pea: ((Shooter)eatingCell.getMyPlant()).getPeaList()) {
										GameController.getPeaToRemove().add(pea);
									}
									GameController.removeShooterFromList((Shooter)eatingCell.getMyPlant());
								}
								eatingCell.removePlant();
								isEating=false;
								}
							}
					}
				} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
				}	
			}
		});
		thread.start();		
	}
	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}
	
}