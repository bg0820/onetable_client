package Controller;

import java.net.URL;
import java.util.ResourceBundle;
import Model.ProfileContextMenu;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class IngredientController implements Initializable {
	@FXML
	private Button contextMenuBtn;
	

	public void contextMenu(MouseEvent event) {
		ProfileContextMenu pcm = new ProfileContextMenu();
		pcm.showContextMenu(event);
	}



	@Override
	public void initialize(URL location, ResourceBundle resources) {



		// contextRect.getC

	}
}
