package logic;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

public class ShopPane extends GridPane {
	private static ObservableList<BuyPlantButton> buyPlantButtonList = FXCollections.observableArrayList();

	public ShopPane() {
		this.setAlignment(Pos.CENTER);
		this.setHgap(5);
		this.setVgap(0);
		buyPlantButtonList.addAll(new BuyPlantButton("PeaShooter"), new BuyPlantButton("Sunflower"),
				new BuyPlantButton("SnowPeaShooter"), new BuyPlantButton("Repeater"), new BuyPlantButton("Walnut"),
				new BuyPlantButton("CherryBomb"));
		// add other plants here
		int i = 0;
		for (BuyPlantButton button : buyPlantButtonList) {
			setSwitchEnable(button);
			button.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent buy) {
					// TODO Auto-generated method stub
					if (!GameController.isUpgrading()) {
						if (ShopController.getSelectedButton() != null
								&& ShopController.getSun() >= button.getPlant().getPrice()) {
							if (ShopController.getSelectedButton().getPlant() == button.getPlant()) {
								ShopController.getSelectedButton().unhighlight();
								ShopController.setSelectedButton(null);
							} else {
								setSelectedButton(button);
							}
						} else if (ShopController.getSun() >= button.getPlant().getPrice()) {
							// can be selected with current sun
							setSelectedButton(button);
						}
					}
				}
			});
			this.add(button, 0, i);
			i++;
		}
		this.add(new UpgradeButton(), 1, 5);
	}

	public void setSelectedButton(BuyPlantButton selectedButton) {
		resetButtonsBackGroundColor();
		selectedButton.highlight();
		ShopController.setSelectedButton(selectedButton);
	}

	public static void resetButtonsBackGroundColor() {
		for (BuyPlantButton button : buyPlantButtonList) {
			button.unhighlight();
		}
	}

	public void setSwitchEnable(Button button) {
		button.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				// TODO fill in this method
				if (ControlPane.isShovel_On())
					ControlPane.resetShovel();
				else if (GameController.isUpgrading())
					UpgradeButton.resetUpgradeButton();

			}
		});
	}

	public static ObservableList<BuyPlantButton> getBuyPlantButtonList() {
		return buyPlantButtonList;
	}

	public static void setBuyPlantButtonList(ObservableList<BuyPlantButton> buyPlantButtonList) {
		ShopPane.buyPlantButtonList = buyPlantButtonList;
	}

}
