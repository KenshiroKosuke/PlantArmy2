package application;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Duration;
import logic.GameController;

public class Main extends Application {

	private final static int WIDTH = 1024;
	private final static int HEIGHT = 589;
	private static Stage primaryStage;
	private static Scene mainMenuScene;
	private static Scene normalModeScene;
	private static Scene gameOverScene;
	private static Scene gameClearScene;
	private static MediaPlayer gameMusic = new MediaPlayer(new Media(ClassLoader.getSystemResource("audio/GameBGM.mp3").toString()));
	//private static AudioClip gameMusic = new AudioClip(ClassLoader.getSystemResource("audio/GameBGM.mp3").toString());
	private static AudioClip menuMusic = new AudioClip(
			ClassLoader.getSystemResource("audio/MainMenuBGM.mp3").toString());
	private static AudioClip gameOverSound = new AudioClip(
			ClassLoader.getSystemResource("audio/LoseMusic.mp3").toString());
	private static AudioClip gameClearSound = new AudioClip(
			ClassLoader.getSystemResource("audio/WinMusic.mp3").toString());

	@Override
	public void start(Stage primaryStage) throws Exception {
		Main.primaryStage = primaryStage;
		primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {

			@Override
			public void handle(WindowEvent arg0) {
				// TODO Auto-generated method stub
				GameController.setIs_over(true);
				menuMusic.stop();
				gameMusic.stop();
				NormalMode.getZombieComingSound().stop();
				gameOverSound.stop();
				gameClearSound.stop();
			}
		});
		startMainMenu();
		primaryStage.show();
		menuMusic.setCycleCount(AudioClip.INDEFINITE);
		menuMusic.play();
	}

	public static void startGame(Scene normalModeScene) {
		menuMusic.stop();
		gameMusic.setCycleCount(AudioClip.INDEFINITE);
		gameMusic.setOnEndOfMedia(new Runnable() {
		       public void run() {
		    	   gameMusic.seek(Duration.ZERO);
		       }
		   });
		gameMusic.play();
		primaryStage.setScene(normalModeScene);
	}

	public static void goToNormalModeScene() {
		normalModeScene = new Scene(new NormalMode(), 1024, 589);
		startGame(normalModeScene);
	}

	public static void main(String[] args) {
		launch(args);
	}

	public static void startMainMenu() {
		MainMenu mainMenu = new MainMenu();
		mainMenuScene = new Scene(mainMenu, WIDTH, HEIGHT);
		primaryStage.setResizable(false);
		primaryStage.setTitle("Plant Army");
		primaryStage.setScene(mainMenuScene);
	}

	public static void goToGameOverScreen() {
		gameMusic.stop();
		NormalMode.getZombieComingSound().stop();
		GameOver gameOver = new GameOver();
		gameOverScene = new Scene(gameOver, WIDTH, HEIGHT);
		gameOverSound.play();
		primaryStage.setScene(gameOverScene);
	}

	public static void goToGameClearScreen() {
		gameMusic.stop();
		NormalMode.getZombieComingSound().stop();
		GameClear gameClear = new GameClear();
		gameClearScene = new Scene(gameClear, WIDTH, HEIGHT);
		gameClearSound.play();
		primaryStage.setScene(gameClearScene);
	}

	public static int getHeight() {
		return HEIGHT;
	}

	public static int getWidth() {
		return WIDTH;
	}

	public static Scene getNormalModeScene() {
		return normalModeScene;
	}

	public static Stage getPrimaryStage() {
		return primaryStage;
	}

	public static AudioClip getGameOverSound() {
		return gameOverSound;
	}

	public static AudioClip getGameClearSound() {
		return gameClearSound;
	}

}