package application;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundSize;

public class MainMenu extends AnchorPane{
	
	public MainMenu() {
		this.setPadding(new Insets(0));
		String logoMenuPath = ClassLoader.getSystemResource("LogoMenu.png").toString();
		Image logoMenuImage  = new Image(logoMenuPath);
		BackgroundSize bgSize = new BackgroundSize(1024,589,false,false,false,false);
		BackgroundImage bgLogoMenuImage = new BackgroundImage(logoMenuImage, null, null, null, bgSize);
		BackgroundImage[] bgImgA = {bgLogoMenuImage};
		this.setBackground(new Background(null,bgImgA));
//		this.setOnKeyPressed(new EventHandler<KeyEvent>() {
//
//			@Override
//			public void handle(KeyEvent arg0) {
//				// TODO Auto-generated method stub
//				Main.exitMenu();
//			}
//		});
	}

}
