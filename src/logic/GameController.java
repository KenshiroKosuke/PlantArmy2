package logic;

import java.util.ArrayList;

import application.Main;
import entity.base.Pea;
import entity.base.Shooter;
import entity.base.Sun;
import entity.base.SunProducer;
import entity.base.Zombie;

public class GameController {
	private static int wave = 1;
	private static int waveType = 0;
	private static boolean is_over = false;
	private static ArrayList<Zombie> CurrentZombies = new ArrayList<Zombie>();
	private static ArrayList<Shooter> shooters = new ArrayList<Shooter>();
	private static ArrayList<Pea> peaToRemove = new ArrayList<Pea>();
	private static ArrayList<Zombie> zombieToRemove = new ArrayList<Zombie>();
	private static ArrayList<SunProducer> sunProducers = new ArrayList<SunProducer>();
	private static ArrayList<Sun> sunToRemove = new ArrayList<Sun>();
	private static boolean isUpgrading = false;
	private static int killCount;

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

	public static boolean shouldIShoot(int x, int y) {
		boolean shoot = false;
		for (Zombie zombie : getCurrentZombies()) {
			if (zombie.getRow() == y && x <= zombie.checkGridXPosition() && zombie.getX() < Main.getWidth()) {
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

	public static void endGame() {
		is_over = true;
		// RESET ZOMBIE
		for (Zombie zombie : CurrentZombies) {
			zombie.setDead(true);
		}
		GameController.getCurrentZombies().clear();

		// RESET PLANT
		for (Shooter shooter : shooters) {
			shooter.getPeaList().clear();
		}

		// RESET CONTROLLER
		GameController.getShooters().clear();
		GameController.getPeaToRemove().clear();
		GameController.getSunProducers().clear();
		GameController.setUpgrading(false);

		ShopController.setSun(ShopController.getInitialSun());
		ShopController.setSelectedButton(null);
		ShopPane.getBuyPlantButtonList().clear();

	}

	public static void setGameWin() {
		endGame();
		Main.goToGameClearScreen();
	}

	public static void setGameOver() {
		endGame();
		Main.goToGameOverScreen();
	}

	public static void setWaveType(int waveType) {
		GameController.waveType = waveType;
	}

	public static int getWaveType() {
		// TODO Auto-generated method stub
		return waveType;
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

	public static ArrayList<Zombie> getZombieToRemove() {
		return zombieToRemove;
	}

	public static void setZombieToRemove(ArrayList<Zombie> zombieToRemove) {
		GameController.zombieToRemove = zombieToRemove;
	}

	public static ArrayList<Sun> getSunToRemove() {
		return sunToRemove;
	}

	public static void setSunToRemove(ArrayList<Sun> sunToRemove) {
		GameController.sunToRemove = sunToRemove;
	}

}
