package application;



import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import logic.Cell;
import logic.FieldPane;
import logic.GameController;

public class Main extends Application{
	
	private final static int WIDTH = 1024;
	private final static int HEIGHT = 589;
	private int board_width;
	private int board_height;
	private int draw_originx;
	private int draw_originy;
	private static Stage primaryStage;
	private static Scene mainMenuScene;
	//private static NormalMode normalMode = new NormalMode();
	private static Scene normalModeScene;
	private static Scene gameOverScene;
	private static AudioClip menuMusic = new AudioClip(ClassLoader.getSystemResource("audio/MainMenuBGM.mp3").toString());
	private static AudioClip gameOverSound = new AudioClip(ClassLoader.getSystemResource("audio/LoseMusic.mp3").toString());
	@Override
	public void start(Stage primaryStage) throws Exception {
		this.primaryStage = primaryStage;
		primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			
			@Override
			public void handle(WindowEvent arg0) {
				// TODO Auto-generated method stub
				menuMusic.stop();
				NormalMode.getGameMusic().stop();
				NormalMode.getZombieComingSound().stop();
				gameOverSound.stop();
				Platform.exit();
		        System.exit(0);
				//GameController.setGameOver();
			}
		});
		startMainMenu();
		primaryStage.show();
		menuMusic.setCycleCount(AudioClip.INDEFINITE);
		menuMusic.play();
	}
	
	public static void startGame(Scene normalModeScene) {
		menuMusic.stop();
		primaryStage.setScene(normalModeScene);
	}
	
	public static void goToNormalModeScene() {
		GameController.setGameMode(0);
		normalModeScene = new Scene(new NormalMode(), 1024,589);
		startGame(normalModeScene);
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public static void startMainMenu() {
		MainMenu mainMenu = new MainMenu();
		mainMenuScene = new Scene(mainMenu, WIDTH, HEIGHT);
		//addEventListener(mainMenuScene);	
		primaryStage.setResizable(false);
		primaryStage.setTitle("Plant Army");
		primaryStage.setScene(mainMenuScene);
	}
	public static void goToGameOverScreen() {
		NormalMode.getGameMusic().stop();
		NormalMode.getZombieComingSound().stop();
		GameOver gameOver = new GameOver();
		gameOverScene = new Scene(gameOver, WIDTH, HEIGHT);
		gameOverSound.play();
		primaryStage.setScene(gameOverScene);
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

	public static void setPrimaryStage(Stage primaryStage) {
		Main.primaryStage = primaryStage;
	}

	public static AudioClip getGameOverSound() {
		return gameOverSound;
	}

}