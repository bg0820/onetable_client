package Controller;

import java.net.URL;
import java.util.ResourceBundle;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.jsoup.Connection.Method;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;

import Model.IngredientListItem;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.FlowPane;

public class IngredientViewController implements Initializable {

	@FXML
	FlowPane listArea;
	@FXML
	private ComboBox<String> selectMenu;

	ObservableList<String> menuList = FXCollections.observableArrayList("bg0820", "마이페이지", "즐겨찾기", "로그아웃");

	// private boolean isIDDuplicate = true;

	public void selectMenuChange(ActionEvent event) {

		if (selectMenu.getValue().equals("마이페이지")) {

		}
		else if (selectMenu.getValue().equals("즐겨찾기")) {

		}
		else if (selectMenu.getValue().equals("로그아웃")) {

		}


	}

	public void addBtn(ActionEvent evenet) {

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		selectMenu.setItems(menuList);
		selectMenu.setValue(menuList.get(0));

		try {
			Response response = Jsoup.connect("http://1.240.181.56:8080/ingredient/search").ignoreContentType(true)
					.ignoreHttpErrors(true).method(Method.GET).header("API_Version", "1.0").data("query", "물")
					.execute();

			if (response.statusCode() == 200) {
				System.out.println(response.body());
				JSONParser jsonParser = new JSONParser();
				JSONObject jsonObj = (JSONObject) jsonParser.parse(response.body());

				if (jsonObj.get("status").toString().equals("SUCCESS")) {
					JSONArray jsonArray = (JSONArray) jsonObj.get("data");

					for (int i = 0; i < jsonArray.size(); i++) {
						System.out.println("===================================");
						JSONObject dataItem = (JSONObject) jsonArray.get(i);
						System.out.println(dataItem.get("currentPrice"));
						System.out.println(dataItem.get("priceDate"));
						System.out.println(dataItem.get("queryCnt"));
						System.out.println(dataItem.get("displayName"));
						System.out.println(dataItem.get("imgUrl"));

						IngredientListItem ingredientListItem = new IngredientListItem(295, 450);
						ingredientListItem.setPrice(dataItem.get("currentPrice").toString());
						ingredientListItem.setPriceDate(dataItem.get("priceDate").toString());
						ingredientListItem.setDisplayName(dataItem.get("displayName").toString());
						ingredientListItem.setImageUrl(dataItem.get("imgUrl").toString());
						listArea.getChildren().add(ingredientListItem);

					}
				}
			}
		} catch (Exception e) {

		}

	}

}
