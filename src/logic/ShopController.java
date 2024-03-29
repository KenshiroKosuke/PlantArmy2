package logic;

import application.NormalMode;

public class ShopController {
	private static final int INITIAL_SUN = 400;
	private static int sun = INITIAL_SUN;
	private static BuyPlantButton selectedButton;

	public static int getSun() {
		return sun;
	}

	public static BuyPlantButton getSelectedButton() {
		return selectedButton;
	}

	public static void setSelectedButton(BuyPlantButton selectedButton) {
		ShopController.selectedButton = selectedButton;
	}

	public static void collectSun() {
		ShopController.sun += 50;
	}

	public static void boughtPlant() {
		setSun(sun - ShopController.getSelectedButton().getPlant().getPrice());
		selectedButton.fillButton(1.0);
		selectedButton.setDisable(true);
		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				selectedButton.unhighlight();
				selectedButton.startRechargingTimer();
			}
		});
		thread.start();
	}

	public static void setSun(int sun) {
		if (sun <= 99999) {
			ShopController.sun = sun;
		} else {
			ShopController.sun = 99999;
		}
		NormalMode.getControl().labelSunUpdate();

	}

	public static int getInitialSun() {
		return INITIAL_SUN;
	}
}