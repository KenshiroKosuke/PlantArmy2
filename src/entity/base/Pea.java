package entity.base;

import application.Main;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import logic.FieldPane;

public class Pea {
	private int speed;
	private int x,y;
	private int startingX;
	private ImageView PeaImageView;
	
	private static final Image IMAGE_NORMAL_PEA= new Image(ClassLoader.getSystemResource("Pea.png").toString());
	public Pea(int x,int y) {
		this.speed = 12;
		PeaImageView = new ImageView(IMAGE_NORMAL_PEA);
		PeaImageView.setVisible(false);
		this.y =  (int) ((Main.getHeight()-FieldPane.getFieldHeight())+(FieldPane.getFieldHeight()*y/5)-20); 
		this.startingX = (int)((Main.getWidth()-FieldPane.getFieldWidth())+(FieldPane.getFieldWidth()*x/9)+35);
		}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getStartingX() {
		return startingX;
	}
	public ImageView getPeaImageView() {
		return PeaImageView;
	}
	public int getY() {
		return y;
	}
	public int getSpeed() {
		return speed;
	}
	public void setPeaImageView(ImageView peaImageView) {
		PeaImageView = peaImageView;
	}
	
	
}
