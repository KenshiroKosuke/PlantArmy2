package logic;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.GridPane;

//You might need to do something to the following line
public class FieldPane extends GridPane {

	private ObservableList<Cell> cells = FXCollections.observableArrayList();
	private static double fieldHeight = 480;
	private static double fieldWidth = 750;

	public FieldPane() {
		this.setHeight(fieldHeight);
		this.setWidth(fieldWidth);
		this.setMinHeight(BASELINE_OFFSET_SAME_AS_HEIGHT);
		this.setPadding(new Insets(0));
		this.setBackground(new Background(new BackgroundFill(null, null, null)));
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 9; j++) {
				Cell cell = new Cell();
				this.add(cell, j, i, 1, 1);
				cells.add(cell);
			}
		}
	}

	public static double getFieldHeight() {
		return fieldHeight;
	}

	public void setFieldHeight(double fieldHeight) {
		FieldPane.fieldHeight = fieldHeight;
	}

	public static double getFieldWidth() {
		return fieldWidth;
	}

	public void setFieldWidth(double fieldWidth) {
		FieldPane.fieldWidth = fieldWidth;
	}

}
