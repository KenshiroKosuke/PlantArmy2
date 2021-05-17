package application;
import java.util.Random;

import entity.ConeZombie;
import entity.NormalZombie;
import entity.base.Pea;
import entity.base.Shooter;
import entity.base.Zombie;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundSize;
import logic.FieldPane;
import logic.GameController;
import logic.ShopPane;

public class NormalMode extends AnchorPane {
	private Random rand = new Random();
	private static FieldPane field;
	private static ShopPane shop;
	public NormalMode() {
		String image_path = ClassLoader.getSystemResource("Lawn.png").toString();
		Image img = new Image(image_path);
		BackgroundSize bgSize = new BackgroundSize(Main.getWidth(),Main.getHeight(),false,false,false,false);
		BackgroundImage bgImg = new BackgroundImage(img, null, null, null, bgSize);
		BackgroundImage[] bgImgA = {bgImg};
		field = new FieldPane();
		shop = new ShopPane();
		this.setBackground(new Background(null,bgImgA));
		this.getChildren().add(field);
		this.getChildren().add(shop);
		this.setRightAnchor(field, 30.0);
		this.setTopAnchor(field, 75.0);
		populateZombie();
		ammoReposition();
		checkFire();
	}
	
	public void populateZombie() {
		int enemyCount = 18+6*GameController.getLevel();
		double rare = 1.0, rarer = 1.0, rarest = 1.0;
		//setting the chance of getting rare Zombie
		switch(GameController.getLevel()) {
		case 1:
			rare = 0.8; rarer = 1.0; rarest = 2.0;
			break;
		case 2:
			rare = 0.7; rarer = 0.92; rarest = 1.0;
			break;
		case 3:
			rare = 0.5; rarer = 0.87; rarest = 1.0;
			break;
		case 4:
			rare = 0.45; rarer = 0.75; rarest = 1.0;
			break;
		case 6:
			Platform.exit();
	        System.exit(0);
			break;
		default:
			System.out.println("Invalid Level.");
			break;
		}
		
		for (int i=0; i<enemyCount; i++) {
			double special = rand.nextDouble();
			System.out.println(i+" "+special);
			if (i<65*enemyCount/100) {
				//wave1
				if (special < rare) {
					NormalZombie zombie = new NormalZombie();
					//75+98*(int) Math.ceil(special)
					initalizeNewZombie(i, zombie);
				} else if (special < rarer) {
					ConeZombie zombie = new ConeZombie();
					initalizeNewZombie(i, zombie);
				} else if (special < rarest) {
					NormalZombie zombie = new NormalZombie();
					initalizeNewZombie(i, zombie);
				}
			} else if (i > 65*enemyCount/100) {
				//wave2
				
			}
		}
		System.out.println("From populate : "+GameController.getCurrentZombies().size());
	}
	
	protected void initalizeNewZombie(int code, Zombie zombie) {
		GameController.getCurrentZombies().add(zombie);
		int row = rand.nextInt(5); //0-4th row from up to the bottom of field 
		zombie.setX((int) ((Main.getWidth()+1.2*code*FieldPane.getFieldWidth()/9))); //1032-81*i
		if (zombie.getName() ==  "NormalZombie")
			zombie.setY((int) (35+(row*FieldPane.getFieldHeight())/5));
		else if (zombie.getName() == "ConeZombie")
			zombie.setY((int) (5+(row*FieldPane.getFieldHeight())/5));
		zombie.setRow(row);
		Thread thread = new Thread(new Runnable(){
			@Override
			public void run() {
				updateZombieMovement(code);
			}
		});
		thread.start();		
	}
	
	private void updateZombieMovement(int code) {
		try {		
			Zombie zombie = GameController.getCurrentZombies().get(code);
			while(!zombie.isDead()) {
				Thread.sleep(50);
				Thread thread = new Thread(()->{
					Platform.runLater(new Runnable() {
						@Override
						public void run() {
							try {
								//update x,y,speed,hp(?) etc.
								GameController.getCurrentZombies().get(code).update();
								//put it in this pane to see
								drawZombie(zombie);
							} catch (IndexOutOfBoundsException e) {
								// TODO Auto-generated catch block
								System.out.println("Zombie has been deleted");
							}
						}
					});
				});
				thread.start();				
				/*========================================================*/	
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			System.out.println("currentZombies cleared while thread was running");
		}		
	}
	
	public void drawZombie(Zombie zombie) {
		ImageView zombieImageView = zombie.getImageView();
        this.getChildren().remove(zombieImageView);
        zombieImageView.setFitHeight(zombie.getHeight());
        //zombieImageView.setFitWidth(100);
        zombieImageView.setPreserveRatio(true);
        zombieImageView.relocate((double)(zombie.getX()), (double)(zombie.getY()));
        this.getChildren().add(zombieImageView);
	}

	public static FieldPane getField() {
		return field;
	}
	public void checkFire() {
		Thread thread = new Thread(new Runnable(){
			@Override
			public void run() {
				while(true) {
					try {
						Thread.sleep(50);
						for(Shooter shooter:GameController.getShooters()) {
							if(GameController.shouldIShoot(shooter.getX(), shooter.getY())&&!shooter.isShot()) {
								shooter.startShooting();
								shooter.setShot(true);
							}
						}
					
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
		thread.start();		
	}
	public void ammoReposition() {
		Thread thread = new Thread(new Runnable(){
			@Override
			public void run() {
				while(true) {
					try {
						Thread.sleep(50);
						Platform.runLater(new Runnable() {
							@Override
							public void run() {
								drawPea();
							}
						});
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
		thread.start();		
	}
	public void drawPea() {
		for(Shooter shooter : GameController.getShooters()) {
			for(Pea pea : shooter.getPeaList()) {
				ImageView peaImage = pea.getPeaImageView();
				this.getChildren().remove(peaImage);
		        //peaImage.setFitHeight(zombie.getHeight());
		        //peaImage.setPreserveRatio(true);
		        peaImage.relocate((double)(pea.getX()), (double)(pea.getY()));
		        this.getChildren().add(peaImage);
			}
		}
		for(Pea pea: GameController.getPeaToRemove()) {
			this.getChildren().remove(pea.getPeaImageView());

		}
	}
}