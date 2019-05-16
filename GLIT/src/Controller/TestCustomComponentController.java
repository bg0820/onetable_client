package Controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.TilePane;

public class TestCustomComponentController  implements Initializable {

	@FXML
	FlowPane list;
	@FXML
	TilePane listTile;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

	}

	public void itemAdd(ActionEvent  evenet)
	{
		System.out.println("클");
		//IngredientListItem ili = new IngredientListItem(305, 300);
		//list.getChildren().add(ili);
	}

}
