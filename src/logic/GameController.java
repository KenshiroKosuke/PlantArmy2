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
	private static int wave = 1;
	private static int waveType = 0;
	private static int zombieCount = 24;
	private static boolean is_over = false;
	private static ArrayList<Zombie> CurrentZombies = new ArrayList<Zombie>();
	private static ArrayList<Shooter> shooters = new ArrayList<Shooter>();
	private static ArrayList<Pea> peaToRemove = new ArrayList<Pea>();
	private static ArrayList<SunProducer> sunProducers = new ArrayList<SunProducer>();
	private static boolean isUpgrading = false;
	private static int killCount;
	
	public static int getZombieCount() {
		return zombieCount;
	}
	public static void setZombieCount(int zombieCount) {
		GameController.zombieCount = zombieCount;
	}
	public static int getGameMode() {
		return gameMode;
	}
	public static void setGameMode(int gameMode) {
		GameController.gameMode = gameMode;
	}
	public static int getWave() {
		return wave;
	}
	public static void setWave(int wave) {
		GameController.wave = wave;
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
		
		//RESET CONTROLLER
		GameController.getShooters().clear();
		GameController.getPeaToRemove().clear();
		GameController.getSunProducers().clear();
		GameController.setUpgrading(false);
		waveType = 0;
		wave = 1;
		zombieCount = 0;
		//RESET BUTTON AND SHOPCONTROL BUTTON
//		for (BuyPlantButton button: ShopPane.getBuyPlantButtonList()) {
//			button.setEffect(null); button.setTime(0); button.setDisable(false); button.setOpacity(1.0);
//		}
		
		ShopController.setSun(ShopController.getInitialSun());
		ShopController.setSelectedButton(null);
		ShopPane.getBuyPlantButtonList().clear();
		System.out.println("From Zombie.java -> GameController.java : "+GameController.getCurrentZombies().size());	
		ShopController.setSelectedButton(null);
		
		Main.goToGameOverScreen();
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
	
	public static void resetAnyButton() {
		UpgradeButton.resetUpgradeButton();
		ControlPane.resetShovel();
		ShopPane.resetButtonsBackGroundColor();
	}
	public static int getWaveType() {
		// TODO Auto-generated method stub
		return waveType;
	}
	public static void changeWaveType() {
		// TODO Auto-generated method stub
		if (waveType == 1)
			waveType = 0;
		else
			waveType = 1;
		ControlPane.waveUpdate();
	}
	public static int getKillCount() {
		return killCount;
	}
	public static void setKillCount(int killCount) {
		GameController.killCount = killCount;
	}
	
//	public static void changeLevel() {
//		level += 1;
//		changeScene();
//	}
//	
//	
	
}
