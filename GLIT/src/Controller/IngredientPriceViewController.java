package Controller;

import java.net.URL;
import java.util.ResourceBundle;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.jsoup.Connection.Method;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;

public class IngredientPriceViewController implements Initializable {

	@FXML
	private LineChart<String, Number> priceChart;
	@FXML
	private NumberAxis priceY;
	@FXML
	private Label title;
	@FXML
	private Label priceLabel;

	private String ingredientItemId;
	private String name;

	public IngredientPriceViewController(String ingredientItemId, String name) {
		// TODO Auto-generated constructor stub
		this.ingredientItemId = ingredientItemId;
		this.name = name;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//System.out.println(name);
		title.setText(this.name);

		try {
			Response response = Jsoup.connect("http://1.240.181.56:8080/ingredient/price").ignoreContentType(true)
					.ignoreHttpErrors(true).method(Method.GET).header("API_Version", "1.0").data("ingredientItemId", ingredientItemId)
					.execute();

			XYChart.Series<String, Number> series = new XYChart.Series<String, Number>();

			if (response.statusCode() == 200) {
				System.out.println(response.body());
				JSONParser jsonParser = new JSONParser();
				JSONObject jsonObj = (JSONObject) jsonParser.parse(response.body());

				if (jsonObj.get("status").toString().equals("SUCCESS")) {
					JSONArray jsonArray = (JSONArray) jsonObj.get("data");

					int firstPrice = Integer.parseInt(((JSONObject)jsonArray.get(0)).get("price").toString());
					priceLabel.setText(String.valueOf(firstPrice));

					// 7만원
					// 4000원
					// price * 0.1
					priceY.setAutoRanging(false);
					priceY.setLowerBound(firstPrice - (firstPrice * 0.1));
					priceY.setUpperBound(firstPrice + (firstPrice * 0.1));

					for (int i = 0; i < jsonArray.size(); i++) {
						JSONObject dataItem = (JSONObject) jsonArray.get(i);
						int price = Integer.parseInt(dataItem.get("price").toString());
						String priceDate = dataItem.get("priceDate").toString();

						//String name = dataItem.get("name").toString();
						//System.out.println("name");
						series.getData().add(new XYChart.Data<String, Number>(priceDate, price));

						//System.out.println(i);
						//IngredientListItem ingredientListItem = new IngredientListItem(295, 450);
						//Price.setText(dataItem.get("price").toString());
						//DisplayName.setText(dataItem.get("displayName").toString());

					}

					priceChart.setLegendVisible(false);
					priceChart.getData().add(series);

					//String stringPrice = Integer.toString("price");

					//priceLabel.setText(firstPrice);

				}
			}
		} catch(Exception e)
		{
			e.printStackTrace();
		}

	}


}
