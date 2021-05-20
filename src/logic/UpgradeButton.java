package logic;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

public class UpgradeButton extends Button {
	public UpgradeButton() {
		this.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if(GameController.isUpgrading()) {
					GameController.setUpgrading(false);
				}else {
					GameController.setUpgrading(true);
					if(ShopController.getSelectedButton()!=null) {
						ShopController.getSelectedButton().unhighlight();
						ShopController.setSelectedButton(null);
					}
				}
			}
		});
	}
}
