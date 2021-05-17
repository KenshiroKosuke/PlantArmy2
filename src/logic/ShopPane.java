package logic;

import logic.BuyPlantButton;
import logic.ShopController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;

public class ShopPane extends GridPane{
	private ObservableList<BuyPlantButton> buyPlantButtonList = FXCollections.observableArrayList();
	public ShopPane() {
		this.setAlignment(Pos.CENTER);
		this.setHgap(10);
		this.setVgap(0);
		buyPlantButtonList.addAll(new BuyPlantButton("PeaShooter"),
								  new BuyPlantButton("Sunflower"),
								  new BuyPlantButton("SnowPeaShooter"),
								  new BuyPlantButton("Walnut")
								  );
		//add other plants here
		int i = 0;
		for (BuyPlantButton button : buyPlantButtonList) {
			button.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent arg0) {
					// TODO Auto-generated method stub
					if (ShopController.getSelectedButton() != null) {
						if (ShopController.getSelectedButton().getPlant() == button.getPlant()) {
							System.out.println("unselect");
							ShopController.getSelectedButton().unhighlight();
							ShopController.setSelectedButton(null);
						} else {
							System.out.println("swap button");
							setSelectedButton(button);
						}
					} else if (ShopController.getSun() >= button.getPlant().getPrice()) {
						System.out.println("set new button");
						setSelectedButton(button);
					}
				}
			});
			this.add(button,0,i );
			i++;
		}
	}
	public void setSelectedButton(BuyPlantButton selectedButton) {
		resetButtonsBackGroundColor();
		selectedButton.highlight();
		ShopController.setSelectedButton(selectedButton);
	}
	public void resetButtonsBackGroundColor() {
		for (BuyPlantButton button : buyPlantButtonList) {
			button.unhighlight();
		}
	}
}
