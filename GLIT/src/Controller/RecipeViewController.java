package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Model.ProfileContextMenu;
//import Model.RecipeListItem;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class RecipeViewController implements Initializable{

	@FXML
	private ComboBox<String> selectMenu;
	
	ObservableList<String> menuList = FXCollections.observableArrayList("bg0820", "마이페이지", "즐겨찾기", "로그아웃");

	// private boolean isIDDuplicate = true;

	public void selectMenuChange(ActionEvent event) {

		if (selectMenu.getValue().equals("마이페이지")) {

		} else if (selectMenu.getValue().equals("즐겨찾기")) {

		} else if (selectMenu.getValue().equals("로그아웃")) {

		}

	}
	
@Override
public void initialize(URL location, ResourceBundle resources) {
	// TODO Auto-generated method stub


	
	
}
	/*
	 * public void listItemAdd(ActionEvent evenet) { RecipeListItem recipeListItem =
	 * new RecipeListItem(290, 350); listItemArea.getChildren().add(recipeListItem);
	 * }
	 */

	public void HomeBtn(ActionEvent event) throws IOException {
		// 창 전환
		Scene registerScene = new Scene(FXMLLoader.load(getClass().getResource("/View/LoginView.fxml")));
		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		window.setScene(registerScene);

	}

	public void IngredientBtn(ActionEvent event) throws IOException {
		// 창 전환
		Scene registerScene = new Scene(FXMLLoader.load(getClass().getResource("/View/IngredientView.fxml")));
		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		window.setScene(registerScene);

	}

	public void contextMenu(MouseEvent event) {
		ProfileContextMenu pcm = new ProfileContextMenu();
		pcm.showContextMenu(event);
	}

}
