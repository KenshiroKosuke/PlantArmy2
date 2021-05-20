package logic;
import java.util.ArrayList;

import application.Main;
import application.MainMenu;
import application.NormalMode;
import entity.base.Pea;
import entity.base.Shooter;
import entity.base.SunProducer;
import entity.base.Zombie;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;

public class GameController {
	private static int gameMode = 0;
	private static int level = 1;
	private static boolean is_over = false;
	private static ArrayList<Zombie> CurrentZombies = new ArrayList<Zombie>();
	private static ArrayList<Shooter> shooters = new ArrayList<Shooter>();
	private static ArrayList<Pea> peaToRemove = new ArrayList<Pea>();
	private static ArrayList<SunProducer> sunProducers = new ArrayList<SunProducer>();
	private static boolean isUpgrading = false;
	
	public static int getGameMode() {
		return gameMode;
	}
	public static void setGameMode(int gameMode) {
		GameController.gameMode = gameMode;
	}
	public static int getLevel() {
		return level;
	}
	public static void setLevel(int level) {
		GameController.level = level;
	}
	public static boolean is_over() {
		return is_over;
	}
	public static void setIs_over(boolean is_over) {
		GameController.is_over = is_over;
	}
	public static ArrayList<Zombie> getCurrentZombies() {
		return CurrentZombies;
	}
	public static void setCurrentZombies(ArrayList<Zombie> currentZombies) {
		CurrentZombies = currentZombies;
	}
	public static ArrayList<Shooter> getShooters() {
		return shooters;
	}
	public static void setShooters(ArrayList<Shooter> shooters) {
		GameController.shooters = shooters;
	}
	public static boolean shouldIShoot(int x,int y) {
		boolean shoot = false;
		for(Zombie zombie : getCurrentZombies()) {
			if(zombie.getRow()==y&&x<=zombie.checkGridXPosition()&&zombie.getX()<Main.getWidth()) {
				shoot = true;
				break;
			}
		}
		return shoot;
	}
	public static void removeShooterFromList(Shooter shooter) {
		shooters.remove(shooter);
	}
	public static ArrayList<Pea> getPeaToRemove() {
		return peaToRemove;
	}
	public static void setPeaToRemove(ArrayList<Pea> peaToRemove) {
		GameController.peaToRemove = peaToRemove;
	}
	public static void setGameOver() {
		
		is_over = true;
		
		//RESET ZOMBIE
		for (Zombie zombie: CurrentZombies) {
			zombie.setDead(true);
		}
		GameController.getCurrentZombies().clear();
		
		//RESET PLANT
		for (Shooter shooter:shooters) {
			shooter.getPeaList().clear();
		}
		GameController.getShooters().clear();
		GameController.getPeaToRemove().clear();
		GameController.getSunProducers().clear();
		GameController.setUpgrading(false);
		
		//RESET BUTTON AND SHOPCONTROL BUTTON
//		for (BuyPlantButton button: ShopPane.getBuyPlantButtonList()) {
//			button.setEffect(null); button.setTime(0); button.setDisable(false); button.setOpacity(1.0);
//		}
		
		ShopController.setSun(ShopController.getInitialSun());
		ShopController.setSelectedButton(null);
		ShopPane.getBuyPlantButtonList().clear();
		System.out.println("From Zombie.java -> GameController.java : "+GameController.getCurrentZombies().size());	
		ShopController.setSelectedButton(null);
		Main.startMainMenu();
	}
	public static ArrayList<SunProducer> getSunProducers() {
		return sunProducers;
	}
	public static void setSunProducers(ArrayList<SunProducer> sunProducers) {
		GameController.sunProducers = sunProducers;
	}
	public static boolean isUpgrading() {
		return isUpgrading;
	}
	public static void setUpgrading(boolean isUpgrading) {
		GameController.isUpgrading = isUpgrading;
	}
	
//	public static void changeLevel() {
//		level += 1;
//		changeScene();
//	}
//	
//	
	
}
