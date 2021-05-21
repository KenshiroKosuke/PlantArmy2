package application;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.ImageCursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.Blend;
import javafx.scene.effect.BlendMode;
import javafx.scene.effect.ColorInput;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import logic.GameController;
import logic.ShopController;

public class MainMenu extends AnchorPane{
	private static ImageView imageView_Logo = new ImageView(new Image(ClassLoader.getSystemResource("Logo.png").toString()));
	private static ImageView imageView_Start = new ImageView(new Image(ClassLoader.getSystemResource("Start.png").toString()));
	private static ImageView imageView_Exit = new ImageView(new Image(ClassLoader.getSystemResource("Exit.png").toString()));
	
	public MainMenu() {
		this.setPadding(new Insets(0));
		String menuPath = ClassLoader.getSystemResource("Lawn.png").toString();
		Image menuImage  = new Image(menuPath);
		BackgroundSize bgSize = new BackgroundSize(1024,589,false,false,false,false);
		BackgroundImage bgMenuImage = new BackgroundImage(menuImage, null, null, null, bgSize);
		BackgroundImage[] bgImgA = {bgMenuImage};
		this.setBackground(new Background(null,bgImgA));
		
		imageView_Logo.setFitWidth(Main.getWidth()/2);
        imageView_Logo.relocate(Main.getWidth()/2-imageView_Logo.getFitWidth()/2,30);
        this.getChildren().add(imageView_Logo);
        
        imageView_Start.setFitHeight(41);
        imageView_Start.setPreserveRatio(true);
        imageView_Start.relocate(Main.getWidth()/2+175,Main.getHeight()-100);
        this.getChildren().add(imageView_Start);

        imageView_Exit.setFitHeight(41);
        imageView_Exit.setPreserveRatio(true);
        imageView_Exit.relocate(Main.getWidth()/2+350,Main.getHeight()-100);
        this.getChildren().add(imageView_Exit);
        
        for (Node imageView: this.getChildren()) {
        	imageView.setOnMouseEntered(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent t) {
                	setGlowEffect((ImageView) imageView);
                }
            });
        	imageView.setOnMouseExited(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent t) {
                	imageView.setEffect(null);
                }
            });
        }
        
        imageView_Start.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
				Main.goToNormalModeScene();
			}
        });
        
        imageView_Exit.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
				Platform.exit();
		        System.exit(0);
			}
        });
	}
	
	public void setGlowEffect(ImageView imageView) {
		Glow glow = new Glow();
	    glow.setLevel(0.7);
	    imageView.setEffect(glow);
	}
}

