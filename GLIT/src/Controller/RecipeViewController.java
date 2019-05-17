package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.jsoup.Jsoup;
import org.jsoup.Connection.Method;
import org.jsoup.Connection.Response;

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
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class RecipeViewController implements Initializable {

	@FXML
	private ComboBox<String> selectMenu;
	@FXML
	private ImageView img = new ImageView();
	@FXML
	private Label nickName;
	@FXML
	private Label foodName;
	@FXML
	private Label foodExplain;
	@FXML
	private Label portion;
	@FXML
	private Label Kcal;
	@FXML
	private Label cookTime;
	@FXML
	private Label recipePrice;
	
	
	
	ObservableList<String> menuList = FXCollections.observableArrayList("bg0820", "마이페이지", "즐겨찾기", "로그아웃");

	// private boolean isIDDuplicate = true;
	private String recipeIdx;
	
	public RecipeViewController(String recipeIdx) {
		// TODO Auto-generated constructor stub
		this.recipeIdx = recipeIdx;
	}

	public void selectMenuChange(ActionEvent event) {

		if (selectMenu.getValue().equals("마이페이지")) {

		} else if (selectMenu.getValue().equals("즐겨찾기")) {

		} else if (selectMenu.getValue().equals("로그아웃")) {

		}

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
		
		try {
			Response response = Jsoup.connect("http://1.240.181.56:8080/recipe/detail").ignoreContentType(true)
					.ignoreHttpErrors(true).method(Method.GET).header("API_Version", "1.0").data("recipeIdx", this.recipeIdx).execute();

			if (response.statusCode() == 200) {
				System.out.println(response.body());
				JSONParser jsonParser = new JSONParser();
				JSONObject jsonObj = (JSONObject) jsonParser.parse(response.body());

				if (jsonObj.get("status").toString().equals("SUCCESS")) {
					JSONObject jsonData = (JSONObject) jsonObj.get("data");
					JSONArray objArray = (JSONArray) jsonData.get("obj");
		
				}
			}
			
		} catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	/*
	 * public void listItemAdd(ActionEvent event) { RecipeListItem recipeListItem =
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

	public void BackBtn(ActionEvent event) throws IOException {
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
