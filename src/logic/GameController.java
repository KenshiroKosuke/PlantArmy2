package logic;
import java.util.ArrayList;
import entity.base.Zombie;

public class GameController {
	private static int gameMode = 0;
	private static int level = 1;
	private static int moneyCount = 150;
	private static boolean is_win = false;
	private static ArrayList<Zombie> CurrentZombies = new ArrayList<Zombie>();
	private static int money;
	
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
	public static int getMoneyCount() {
		return moneyCount;
	}
	public static void setMoneyCount(int moneyCount) {
		GameController.moneyCount = moneyCount;
	}
	public static boolean isIs_win() {
		return is_win;
	}
	public static void setIs_win(boolean is_win) {
		GameController.is_win = is_win;
	}
	public static ArrayList<Zombie> getCurrentZombies() {
		return CurrentZombies;
	}
	public static void setCurrentZombies(ArrayList<Zombie> currentZombies) {
		CurrentZombies = currentZombies;
	}
	public static int getMoney() {
		return money;
	}
	public static void setMoney(int money) {
		GameController.money = money;
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