package Controller;

import java.net.URL;
import java.util.ResourceBundle;
import Model.ProfileContextMenu;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

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
