package logic;
import java.util.ArrayList;

import application.Main;
import application.MainMenu;
import entity.base.Pea;
import entity.base.Shooter;
import entity.base.SunProducer;
import entity.base.Zombie;
import javafx.scene.Scene;

public class GameController {
	private static int gameMode = 0;
	private static int level = 1;
	private static boolean is_over = false;
	private static ArrayList<Zombie> CurrentZombies = new ArrayList<Zombie>();
	private static ArrayList<Shooter> shooters = new ArrayList<Shooter>();
	private static ArrayList<Pea> peaToRemove = new ArrayList<Pea>();
	private static ArrayList<SunProducer> sunProducers = new ArrayList<SunProducer>();
	
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
		// TODO Auto-generated method stub
		is_over = true;
		//reset everything
		for (Zombie zombie: CurrentZombies) {
			zombie.setDead(true);
		}
		GameController.getCurrentZombies().clear();
		Main.startMainMenu();
		System.out.println("From Zombie.java : "+GameController.getCurrentZombies().size());
	}
	public static ArrayList<SunProducer> getSunProducers() {
		return sunProducers;
	}
	public static void setSunProducers(ArrayList<SunProducer> sunProducers) {
		GameController.sunProducers = sunProducers;
	}

	
	
	
//	public static void changeScene() {
//		// change from menu to in-game OR change from in-game to menu
//		if (isGameStarted)
//			level += 1;
//	}
	
//	public static void changeLevel() {
//		level += 1;
//		changeScene();
//	}
//	
//	
	
}
