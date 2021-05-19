package logic;
import application.Main;
import javafx.event.EventHandler;
import javafx.scene.ImageCursor;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.effect.Blend;
import javafx.scene.effect.BlendMode;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.ColorInput;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class ControlPane extends Pane {

	private final double HEIGHT = 70;
	private static ImageView imageView_Label_Sun = new ImageView(new Image(ClassLoader.getSystemResource("Label_Sun.png").toString()));
	private static ImageView imageView_Shovel = new ImageView(new Image(ClassLoader.getSystemResource("Button_Shovel.png").toString()));
	private static Image IMAGE_ICON_SHOVEL = new Image(ClassLoader.getSystemResource("Icon_Shovel.png").toString());
	private static Label label_Sun = new Label();
	private static boolean shovel_On = false;

	public ControlPane() {
        imageView_Label_Sun.setFitHeight(HEIGHT);
        imageView_Label_Sun.setPreserveRatio(true);
        imageView_Label_Sun.relocate(0,-10);
        this.getChildren().add(imageView_Label_Sun);
       
        label_Sun.relocate(80, 8);
        label_Sun.setFont(Font.font("Antique Olive Compact", 28));
        label_Sun.setPrefWidth(120);
        label_Sun.setWrapText(false);
        label_Sun.relocate(80, 8);
        labelSunUpdate();
        this.getChildren().add(label_Sun);

        imageView_Shovel.setFitHeight(HEIGHT-10);
        imageView_Shovel.setPreserveRatio(true);
        imageView_Shovel.relocate(215,-5);
        imageView_Shovel.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
				if (shovel_On == false) {
					shovel_On = true;
					System.out.println("shovel_On = true");
					ShopPane.resetButtonsBackGroundColor();
					ShopController.setSelectedButton(null); // Deselecting the previous BuyPlantButton if any
	                Blend greenEffect = new Blend(
	                    BlendMode.GREEN,
	                    null,
	                    new ColorInput(
	                            0,
	                            0,
	                            imageView_Shovel.getBaselineOffset(),
	                            imageView_Shovel.getFitHeight(),
	                            Color.LIGHTSEAGREEN
	                    )
	                );
	                greenEffect.setOpacity(0.6);
	                imageView_Shovel.setEffect(greenEffect);
	                Main.getNormalModeScene().setCursor(new ImageCursor(IMAGE_ICON_SHOVEL));
	                //ShopPane.setSwitchEnable();
	                //Glow glow = new Glow();
	                //glow.setLevel(0.9);
	                //imageView_Shovel.setEffect(glow);
				} else if (shovel_On == true) {
					resetShovel();
				}
			}
		});
        
        this.getChildren().add(imageView_Shovel);
	}
	
	public void labelSunUpdate() {
		label_Sun.setText(""+ShopController.getSun());
		System.out.println("label should be updated");
	}

	public static boolean isShovel_On() {
		return shovel_On;
	}

	public static void setShovel_On(boolean shovel_On) {
		ControlPane.shovel_On = shovel_On;
	}
	
	public static void resetShovel() {
		shovel_On = false;
		imageView_Shovel.setEffect(null);
		Main.getNormalModeScene().setCursor(null);
		////////////////////////////
		System.out.println("shovel_On = false");
		////////////////////////////
	}
}
