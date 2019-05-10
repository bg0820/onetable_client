package Controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;

public class RecipeFindController implements Initializable{


	@FXML
	private ComboBox<String> selectFind;
	@FXML
	private ComboBox<String> selectArray;

	ObservableList<String> findList = FXCollections.observableArrayList("전체",
			"작성자명",
			"레시피명");
	
	ObservableList<String> arrayList = FXCollections.observableArrayList("최신순",
			"평점순",
			"낮은가격순",
			"높은가격순"); 
	

@Override
public void initialize(URL location, ResourceBundle resources) {
	// TODO Auto-generated method stub
	selectFind.setItems(findList);
	selectFind.getSelectionModel().select("전체");
	
	selectArray.setItems(arrayList);
	//selectArray.getSelectionModel().select("전체");
}
}
 