package logic;

import application.Main;
import javafx.event.EventHandler;
import javafx.scene.ImageCursor;
import javafx.scene.control.Label;
import javafx.scene.effect.Blend;
import javafx.scene.effect.BlendMode;
import javafx.scene.effect.ColorInput;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class ControlPane extends Pane {

	private final double HEIGHT = 70;
	private static ImageView imageView_Label_Sun = new ImageView(
			new Image(ClassLoader.getSystemResource("Label_Sun.png").toString()));
	private static ImageView imageView_Shovel = new ImageView(
			new Image(ClassLoader.getSystemResource("Button_Shovel.png").toString()));
	private static Image IMAGE_ICON_SHOVEL = new Image(ClassLoader.getSystemResource("Icon_Shovel.png").toString());
	private static Label label_Sun = new Label();
	private static Text text_Wave = new Text();
	private static boolean shovel_On = false;

	public ControlPane() {
		imageView_Label_Sun.setFitHeight(HEIGHT);
		imageView_Label_Sun.setPreserveRatio(true);
		imageView_Label_Sun.relocate(0, -10);
		this.getChildren().add(imageView_Label_Sun);
		Font sunFont = Font.loadFont(ClassLoader.getSystemResource("font/OLVR93W.TTF").toString(), 28);
		label_Sun.setFont(sunFont);
		label_Sun.setPrefWidth(120);
		label_Sun.setWrapText(false);
		label_Sun.relocate(80, 8);
		labelSunUpdate();
		this.getChildren().add(label_Sun);

		Font waveFont = Font.loadFont(ClassLoader.getSystemResource("font/ERASBD.TTF").toString(), 45);
		text_Wave.setFont(waveFont);
		text_Wave.setFill(Color.WHITE);
		text_Wave.setStrokeWidth(3);
		text_Wave.setStroke(Color.BLACK);
		text_Wave.relocate(650, 2);
		waveUpdate();
		this.getChildren().add(text_Wave);

		imageView_Shovel.setFitHeight(HEIGHT - 10);
		imageView_Shovel.setPreserveRatio(true);
		imageView_Shovel.relocate(215, -5);
		imageView_Shovel.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
				if (shovel_On == false) {
					GameController.resetAnyButton();
					shovel_On = true;
					ShopController.setSelectedButton(null); // Deselecting the previous BuyPlantButton if any
					Blend greenEffect = new Blend(BlendMode.GREEN, null,
							new ColorInput(0, 0, imageView_Shovel.getBaselineOffset(), imageView_Shovel.getFitHeight(),
									Color.LIGHTSEAGREEN));
					greenEffect.setOpacity(0.6);
					imageView_Shovel.setEffect(greenEffect);
					Main.getNormalModeScene().setCursor(new ImageCursor(IMAGE_ICON_SHOVEL));
				} else if (shovel_On == true) {
					resetShovel();
				}
			}
		});

		this.getChildren().add(imageView_Shovel);
	}

	public static void waveUpdate() {
		// TODO Auto-generated method stub
		text_Wave.setText("WAVE " + GameController.getWave() + "." + GameController.getWaveType());
	}

	public void labelSunUpdate() {
		label_Sun.setText("" + ShopController.getSun());
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
	}
}
