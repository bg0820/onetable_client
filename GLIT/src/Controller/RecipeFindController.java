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

import Model.ProfileContextMenu;
import Model.RecipeListItem;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;

public class RecipeFindController implements Initializable {

	@FXML
	private HBox selectPrice;
	@FXML
	private FlowPane listItemArea;
	@FXML
	private ComboBox<String> selectFind;
	@FXML
	private ComboBox<String> selectArray;
	@FXML
	FlowPane list;
	@FXML
	TilePane listTile;
	@FXML
	private ComboBox<String> selectMenu;

	ObservableList<String> findList = FXCollections.observableArrayList("전체", "작성자명", "레시피명", "재료명");

	ObservableList<String> arrayList = FXCollections.observableArrayList("정확도순", "최신순", "가격순");

	/*
	 * ObservableList<String> priceList = FXCollections.observableArrayList("가격낮은순",
	 * "가격높은순", "1만원이하", "1만원대", "2만원대", "3만원대", "4만원대", "5만원이상");
	 */

	ObservableList<String> menuList = FXCollections.observableArrayList("bg0820", "마이페이지", "즐겨찾기", "로그아웃");

	// private boolean isIDDuplicate = true;

	public void selectMenuChange(ActionEvent event) {

		if (selectMenu.getValue().equals("마이페이지")) {

		} else if (selectMenu.getValue().equals("즐겨찾기")) {

		} else if (selectMenu.getValue().equals("로그아웃")) {

		}

	}

	/*
	 * public void listItemAdd(ActionEvent event) { RecipeListItem recipeListItem =
	 * new RecipeListItem(290, 350); listItemArea.getChildren().add(recipeListItem);
	 * }
	 */

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

		selectPrice.setManaged(false);
		selectPrice.setVisible(false);

		selectFind.setItems(findList);
		selectFind.getSelectionModel().select("전체");

		selectArray.setItems(arrayList);
		// selectArray.getSelectionModel().select("전체");

		// selectFind.getSelectionModel().select("전체");

		try {
			Response response = Jsoup.connect("http://1.240.181.56:8080/recipe/search/all").ignoreContentType(true)
					.ignoreHttpErrors(true).method(Method.GET).header("API_Version", "1.0").data("startNum", "0")
					.data("itemNum", "80").execute();

			if (response.statusCode() == 200) {
				System.out.println(response.body());
				JSONParser jsonParser = new JSONParser();
				JSONObject jsonObj = (JSONObject) jsonParser.parse(response.body());

				if (jsonObj.get("status").toString().equals("SUCCESS")) {
					JSONObject jsonData = (JSONObject) jsonObj.get("data");

					JSONArray objArray = (JSONArray) jsonData.get("obj");
					for (int i = 0; i < objArray.size(); i++) {
						JSONObject data = (JSONObject) objArray.get(i);
						String recipeIdx = data.get("recipeIdx").toString();
						String recipeName = data.get("name").toString();
						String nickName = data.get("nickName").toString();
						String recipeImg = data.get("recipeImg").toString();
						String price = "";
						if(data.containsKey("price"))
						price  = data.get("price").toString();
						
						RecipeListItem recipeListItem = new RecipeListItem(295, 450);
						recipeListItem.setRecipeImage(recipeImg);
						recipeListItem.setRecipeTitle(recipeName);
						recipeListItem.setNickName(nickName);
						recipeListItem.setPriceLab(price);
						
						recipeListItem.setOnAction(new EventHandler<ActionEvent>() {

							@Override
							public void handle(ActionEvent event) {
								// TODO Auto-generated method stub

								try {
									RecipeViewController rvc = new RecipeViewController(recipeIdx);
									FXMLLoader loader = new FXMLLoader(
											getClass().getResource("/View/RecipeView.fxml"));
									loader.setController(rvc);
									
									// 창 전환
									Scene registerScene = new Scene((Parent)loader.load());
									Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
									window.setScene(registerScene);
									
									
								} catch (Exception e) {
									e.printStackTrace();
								}

							}

						});

						listItemArea.getChildren().add(recipeListItem);

						// data.get("recipeIdx");

					}

					/*
					 * System.out.println(jsonArray.size()); for (int i = 0; i < jsonArray.size();
					 * i++) { JSONObject dataItem = (JSONObject) jsonArray.get(i);
					 * 
					 * if (dataItem.get("priceDate") == null || dataItem.get("imgUrl") == null ||
					 * dataItem.get("recipeItemId") == null) continue;
					 * 
					 * RecipeListItem recipeListItem = new RecipeListItem(295, 450);
					 * 
					 * recipeListItem.setPrice(dataItem.get("price").toString());
					 * recipeListItem.setPriceDate(dataItem.get("priceDate").toString());
					 * recipeListItem.setDisplayName(dataItem.get("displayName").toString());
					 * recipeListItem.setImageUrl(dataItem.get("imgUrl").toString());
					 * recipeListItem.setRecipeItemId(dataItem.get("recipeItemId").toString());
					 * listItemArea.getChildren().add(recipeListItem);
					 * 
					 * }
					 */
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void priceBtn(ActionEvent event) {

		if (selectArray.getValue().equals("가격순")) {
			selectPrice.setManaged(true);
			selectPrice.setVisible(true);

		} else {
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

	public void contextMenu(MouseEvent event) {
		ProfileContextMenu pcm = new ProfileContextMenu();
		pcm.showContextMenu(event);
	}

}
