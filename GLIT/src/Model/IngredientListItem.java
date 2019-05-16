package Model;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class IngredientListItem extends Button {
	private double width;
	private double height;
	private Label priceDateLab = new Label();
	private Label displayNameLab = new Label();
	private Label priceLab = new Label();
	private Label perPriceLab = new Label();
	private ImageView imgView = new ImageView();
	private String itemId;

	/*
	public void RecipeListItem(int width, int height) {
		super.setPrefWidth(width);
		super.setPrefHeight(height);
		super.setGraphicTextGap(0.0);
		super.setMnemonicParsing(false);
		super.getStylesheets().add("ViewCss/RecipeFindView.css");
		super.getStyleClass().add("listItemBtn");


		VBox vbox = new VBox();

		HBox titleArea = new HBox();
		titleArea.setAlignment(Pos.CENTER_LEFT);
		titleArea.setPadding(new Insets(5.0, 0.0, 5.0, 5.0));
		ImageView userAvatarImgView = new ImageView();
		userAvatarImgView.setImage(new Image("img/ava.png"));
		userAvatarImgView.setFitHeight(40.0);
		userAvatarImgView.setFitWidth(40.0);
		userAvatarImgView.setPickOnBounds(true);
		userAvatarImgView.setPreserveRatio(true);

		Label nickNameLab = new Label();
		nickNameLab.setAlignment(Pos.CENTER);
		nickNameLab.setText("bg0820님");
		titleArea.getChildren().add(userAvatarImgView);
		titleArea.getChildren().add(nickNameLab);


		ImageView recipeImg = new ImageView();
		recipeImg.setImage(new Image("img/me.jpg"));
		recipeImg.setFitHeight(200.0);
		recipeImg.setFitWidth(width);
		recipeImg.setPickOnBounds(false);
		recipeImg.setPreserveRatio(false);

		Label recipeTitle = new Label();
		recipeTitle.setText("레시피 제목");

		FlowPane bottomLabArea = new FlowPane();
		Label priceLab = new Label();
		Label priceDateLab = new Label();

		priceLab.setText("15,800원");
		priceDateLab.setText("2018.05.04");

		bottomLabArea.getChildren().add(priceLab);
		bottomLabArea.getChildren().add(priceDateLab);
		bottomLabArea.setMargin(priceDateLab, new Insets(0, 0, 0, 10.0));

		vbox.getChildren().add(titleArea); // HBOX
		vbox.getChildren().add(recipeImg); // imgView
		vbox.getChildren().add(recipeTitle);
		vbox.setMargin(recipeTitle, new Insets(0.0, 0.0, 0.0, 10.0));
		vbox.getChildren().add(bottomLabArea);
		vbox.setMargin(bottomLabArea, new Insets(0, 0, 0, 10.0));

		vbox.getStylesheets().add("ViewCss/RecipeFindView.css");
		vbox.getStyleClass().add("listItemVbox");

		super.setGraphic(vbox);

	}
*/

	public IngredientListItem(double width, double height) {
		this.width = width;
		this.height = height;

		super.prefWidth(width);
		super.prefHeight(height);
		super.setGraphicTextGap(0.0);

		super.setMnemonicParsing(false);
		super.getStylesheets().add("ViewCss/IngredientView.css");
		super.getStyleClass().add("listItemBtn");

		VBox vbox = new VBox();
		vbox.getStylesheets().add("ViewCss/IngredientView.css");
		vbox.getStyleClass().add("listItemVBox");
		HBox titleHBox = new HBox();
		titleHBox.setAlignment(Pos.CENTER_RIGHT);

		titleHBox.getChildren().add(priceDateLab);
		titleHBox.setMargin(priceDateLab, new Insets(10, 10, 10, 0));

		imgView.getStyleClass().add("imageItem");
		imgView.setFitWidth(width);
		imgView.setFitHeight(230);
		imgView.setPickOnBounds(true);
		imgView.setPreserveRatio(false);
		HBox priceArea = new HBox();


		priceArea.getChildren().add(priceLab);
		//priceArea.getChildren().add(perPriceLab);
		//priceArea.getChildren().add(ingredientItemIdLab);
		priceArea.setHgrow(priceLab, Priority.ALWAYS);

		vbox.getChildren().add(titleHBox);
		vbox.getChildren().add(imgView);
		vbox.getChildren().add(displayNameLab);
		vbox.getChildren().add(priceArea);
		vbox.setMargin(displayNameLab, new Insets(20, 0, 10, 0));
		super.setGraphic(vbox);
	}

	/*
	public void IngredientDetailViewListItem() {


		super.setMnemonicParsing(false);
		super.getStylesheets().add("ViewCss/IngredientDetailView.css");


		VBox vbox = new VBox();
		vbox.getStylesheets().add("ViewCss/IngredientDetailView.css");
		vbox.getStyleClass().add("listItemVBox");
		HBox titleHBox = new HBox();
		titleHBox.setAlignment(Pos.CENTER_RIGHT);

		titleHBox.getChildren().add(priceDateLab);
		titleHBox.setMargin(priceDateLab, new Insets(10, 10, 10, 0));


		HBox priceArea = new HBox();


		priceArea.getChildren().add(priceLab);
		priceArea.setHgrow(priceLab, Priority.ALWAYS);

		vbox.getChildren().add(titleHBox);
		vbox.getChildren().add(displayNameLab);
		vbox.getChildren().add(priceArea);
		vbox.setMargin(displayNameLab, new Insets(20, 0, 10, 0));
		super.setGraphic(vbox);
	}

*/
	public void setImageUrl(String url)
	{
		imgView.setImage(new Image(url));
	}

	public void setPriceDate(String priceDate)
	{
		priceDateLab.setText(priceDate);
	}

	public void setDisplayName(String displayName)
	{
		displayNameLab.setMaxWidth(width);
		displayNameLab.setText(displayName);
	}

	public void setPrice(String price)
	{
		priceLab.setText(price);
	}

	public void setPerPriceStr(String priceStr)
	{
		perPriceLab.setText(priceStr);
	}

	public IngredientListItem getIngredientListItem() {
		return this;
	}

	public void setIngredientItemId(String ingredientItemId) {
		// TODO Auto-generated method stub

	}

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}
}
