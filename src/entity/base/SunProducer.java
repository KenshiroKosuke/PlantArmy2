package entity.base;

import java.util.ArrayList;
import java.util.Random;

import application.Main;
import logic.FieldPane;

public class SunProducer extends Plant implements sunProducable {
	private ArrayList<Sun> sunList = new ArrayList<Sun>();
	private int sunProduceTimer;
	private int sunProduceTime;

	public SunProducer(int hp, String plantName, int sunCount, int sunProducetime) {
		super(10, plantName);
		this.sunProduceTimer = 0;
		this.sunProduceTime = sunProducetime;
		for (int i = 0; i < sunCount; i++) {
			sunList.add(new Sun());

		}
	}

	public void produceSun() {
		double sunX, sunY;
		for (Sun sun : sunList) {
			Random rand = new Random();
			sunX = Main.getWidth() - FieldPane.getFieldWidth()
					+ FieldPane.getFieldWidth() * (getX() - 1 + rand.nextDouble()) / 9;
			sunY = Main.getHeight() - FieldPane.getFieldHeight() + getY() * FieldPane.getFieldHeight() / 5;
			sun.getSunImageView().relocate(sunX, sunY);
		}
	}

	public int getSunProduceTimer() {
		return sunProduceTimer;
	}

	public void setSunProduceTimer(int sunProduceTimer) {
		this.sunProduceTimer = sunProduceTimer;
	}

	public int getSunProduceTime() {
		return sunProduceTime;
	}

	public void setSunProduceTime(int sunProduceTime) {
		this.sunProduceTime = sunProduceTime;
	}

	public ArrayList<Sun> getSunList() {
		return sunList;
	}

	public void setSunList(ArrayList<Sun> sunList) {
		this.sunList = sunList;
	}
}
