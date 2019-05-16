package Controller;

import java.io.IOException;

import Model.ProfileContextMenu;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class MainViewController {

	public void IngredientBtn(ActionEvent event) throws IOException {
		// 창 전환
		Scene registerScene = new Scene(FXMLLoader.load(getClass().getResource("/View/IngredientView.fxml")));
		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		window.setScene(registerScene);

	}


public void RecipeBtn(ActionEvent event) throws IOException {
	// 창 전환
	Scene registerScene = new Scene(FXMLLoader.load(getClass().getResource("/View/RecipeFind.fxml")));
	Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
	window.setScene(registerScene);

}
	public void contextMenu(MouseEvent event) {
		ProfileContextMenu pcm = new ProfileContextMenu();
		pcm.showContextMenu(event);
	}
}
