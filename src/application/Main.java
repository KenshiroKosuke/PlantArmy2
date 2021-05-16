package application;

import javafx.application.Application;
import javafx.application.Platform;
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
import javafx.scene.paint.Color;
import javafx.stage.Stage;
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
	//private static NormalMode normalMode = new NormalMode();
	private static Scene normalModeScene;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		this.primaryStage = primaryStage;
		MainMenu mainMenu = new MainMenu();
		
		Scene scene = new Scene(mainMenu, WIDTH, HEIGHT);
		
//		Canvas canvas = new Canvas(1024,589);
//		GraphicsContext gc = canvas.getGraphicsContext2D();
//		drawGameBoard(gc);
//		root.getChildren().add(canvas);
		
		
		//Register Event
		addEventListener(scene);
		
		this.primaryStage.setResizable(false);
		this.primaryStage.setTitle("Plant Army");
		this.primaryStage.setScene(scene);
		this.primaryStage.show();
	}
	
	public static void startGame(Scene normalModeScene) {
		primaryStage.setScene(normalModeScene);
	}
	
	private void addEventListener(Scene s) {
		s.setOnKeyPressed((event) -> {
			KeyCode keycode = event.getCode();
			switch(keycode) {
			case N:
				GameController.setGameMode(0);
				normalModeScene = new Scene(new NormalMode(), 1024,589);
				startGame(normalModeScene);
				break;
			case H:
				GameController.setGameMode(1);
				//startGame(hardModeScene);
				break;
			case R:
				//rule
				break;
			case ESCAPE:
				Platform.exit();
		        System.exit(0);
				break;
			default:
				System.out.println("Invalid Key.");
				break;
			}
		}
		);
	}
	
	public static void main(String[] args) {
		launch(args);
	}

	public static int getHeight() {
		return HEIGHT;
	}

	public static int getWidth() {
		return WIDTH;
	}

	private void drawGameBoard(GraphicsContext gc) {
		
		//Draw Background
		gc.setFill(Color.rgb(21,24,31));
		gc.fillRect(0, 0, 854, 480);
		
		//Draw Playable Field Background
		gc.setFill(Color.BLACK);
		
		int x = 0;
		int y = 0;
			
		}

	public static Scene getNormalModeScene() {
		return normalModeScene;
	}

}