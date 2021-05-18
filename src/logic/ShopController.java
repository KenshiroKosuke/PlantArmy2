package logic;

public class ShopController {
	private static int sun = 500000;
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
		//add code to update sun label
	}
	public static void boughtPlant() {
		ShopController.sun -= ShopController.getSelectedButton().getPlant().getPrice();
		//add code to update sun label
		if(ShopController.sun < ShopController.getSelectedButton().getPlant().getPrice()) {
			ShopController.getSelectedButton().unhighlight();
			ShopController.setSelectedButton(null);
		}
		
	}
}
