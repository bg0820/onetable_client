package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.jsoup.Connection.Method;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;

import Model.IngredientListItem;
import Model.ProfileContextMenu;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class IngredientViewController implements Initializable {

	@FXML
	private FlowPane listArea;
	@FXML
	private ComboBox<String> selectMenu;
	@FXML
	private Button contextMenuBtn;
	@FXML
	private TextField inputSearch;

	public void searchBtn(ActionEvent event) {


		String searchText = inputSearch.getText();
		System.out.println(searchText);

		listArea.getChildren().clear();


		try {
			Response response = Jsoup.connect("http://1.240.181.56:8080/ingredient/search").ignoreContentType(true)
					.ignoreHttpErrors(true).method(Method.GET).header("API_Version", "1.0").data("query", searchText).execute();

			if (response.statusCode() == 200) {
				System.out.println(response.body());
				JSONParser jsonParser = new JSONParser();
				JSONObject jsonObj = (JSONObject) jsonParser.parse(response.body());

				if (jsonObj.get("status").toString().equals("SUCCESS")) {
					JSONArray jsonArray = (JSONArray) jsonObj.get("data");
					System.out.println(jsonArray.size());
					for (int i = 0; i < jsonArray.size(); i++) {
						JSONObject dataItem = (JSONObject) jsonArray.get(i);

						if (dataItem.get("priceDate") == null || dataItem.get("imgUrl") == null
								|| dataItem.get("ingredientItemId") == null)
							continue;

						System.out.println(i);
						IngredientListItem ingredientListItem = new IngredientListItem(295, 450);
						ingredientListItem.setPrice(dataItem.get("price").toString());
						ingredientListItem.setPriceDate(dataItem.get("priceDate").toString());
						ingredientListItem.setDisplayName(dataItem.get("displayName").toString());
						// System.out.println(dataItem.get("imgUrl").toString());
						ingredientListItem.setImageUrl(dataItem.get("imgUrl").toString());
						ingredientListItem.setIngredientItemId(dataItem.get("ingredientItemId").toString());


						listArea.getChildren().add(ingredientListItem);

					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}


	}

	public void contextMenu(MouseEvent event) {
		ProfileContextMenu pcm = new ProfileContextMenu();
		pcm.showContextMenu(event);
	}

	ObservableList<String> menuList = FXCollections.observableArrayList("bg0820", "마이페이지", "즐겨찾기", "로그아웃");

	// private boolean isIDDuplicate = true;

	public void selectMenuChange(ActionEvent event) {

		if (selectMenu.getValue().equals("마이페이지")) {

		} else if (selectMenu.getValue().equals("즐겨찾기")) {

		} else if (selectMenu.getValue().equals("로그아웃")) {

		}

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		// selectMenu.setItems(menuList);
		// selectMenu.setValue(menuList.get(0));

		try {
			Response response = Jsoup.connect("http://1.240.181.56:8080/ingredient/search/all").ignoreContentType(true)
					.ignoreHttpErrors(true).method(Method.GET).header("API_Version", "1.0").data("startNum", "530")
					.data("itemNum", "30").execute();

			if (response.statusCode() == 200) {
				System.out.println(response.body());
				JSONParser jsonParser = new JSONParser();
				JSONObject jsonObj = (JSONObject) jsonParser.parse(response.body());

				if (jsonObj.get("status").toString().equals("SUCCESS")) {
					JSONArray jsonArray = (JSONArray) jsonObj.get("data");
					System.out.println(jsonArray.size());
					for(int i = 0; i < jsonArray.size(); i++) {
						JSONObject dataItem = (JSONObject) jsonArray.get(i);

						if (dataItem.get("priceDate") == null || dataItem.get("imgUrl") == null
								|| dataItem.get("ingredientItemId") == null)
							continue;

						System.out.println(i);
						IngredientListItem ingredientListItem = new IngredientListItem(295, 450);
						ingredientListItem.setPrice(dataItem.get("price").toString());
						ingredientListItem.setPriceDate(dataItem.get("priceDate").toString());
						ingredientListItem.setDisplayName(dataItem.get("displayName").toString());
						// System.out.println(dataItem.get("imgUrl").toString());
						ingredientListItem.setImageUrl(dataItem.get("imgUrl").toString());
						ingredientListItem.setIngredientItemId(dataItem.get("ingredientItemId").toString());
						ingredientListItem.setOnAction(new EventHandler<ActionEvent>() {

							@Override
							public void handle(ActionEvent event) {
								// TODO Auto-generated method stub
								try {
									//Popup popup = new Popup();
									IngredientPriceViewController controller = new IngredientPriceViewController(
											dataItem.get("ingredientItemId").toString(),dataItem.get("displayName").toString());


									FXMLLoader loader = new FXMLLoader(
											getClass().getResource("/View/IngredientDetailView.fxml"));
									loader.setController(controller);
									//popup.getContent().add();

									Scene secondScene = new Scene((Parent) loader.load(), 450, 400);

									Stage newWindow = new Stage();
					                newWindow.setTitle("Second Stage");
					                newWindow.setScene(secondScene);
					                newWindow.show();
								} catch (IOException e) {
									e.printStackTrace();
								}

							}
						});

						listArea.getChildren().add(ingredientListItem);

					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
