package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

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
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class RecipeViewController implements Initializable{

	@FXML
	private FlowPane listItemArea;
	
	@FXML
	private ComboBox<String> selectFind;
	@FXML 
	private ComboBox<String> selectArray;
	@FXML
	private ComboBox<String> selectPrice;

	ObservableList<String> findList = FXCollections.observableArrayList("전체",
			"작성자명",
			"레시피명",
			"재료명");
	
	ObservableList<String> arrayList = FXCollections.observableArrayList("정확도순",
			"최신순",
			"평점순",
			"가격순"); 
	
	ObservableList<String> priceList = FXCollections.observableArrayList("1만원 이하",
			"1만원대",
			"2만원대",
			"3만원대",
			"4만원대",
			"5만원대"); 
	
	
	/*public void listItemAdd(ActionEvent evenet)
	{
		RecipeListItem recipeListItem = new RecipeListItem(290, 350);
		listItemArea.getChildren().add(recipeListItem);
	}*/

	
@Override
public void initialize(URL location, ResourceBundle resources) {
	// TODO Auto-generated method stub
	
	selectPrice.setManaged(false);
	selectPrice.setVisible(false);
	
	selectFind.setItems(findList);
	selectFind.getSelectionModel().select("전체");
	
	selectArray.setItems(arrayList);
	//selectArray.getSelectionModel().select("전체");
	
	selectPrice.setItems(priceList);
	//selectFind.getSelectionModel().select("전체");
	
}

public void priceBtn(ActionEvent event) {

	if (selectArray.getValue().equals("가격순")) {
		selectPrice.setManaged(true);
		selectPrice.setVisible(true);
		
	}
		else {
			selectPrice.setVisible(false);
			selectPrice.setManaged(false);
		}
	}


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
}
