package Model;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class RecipeListItem extends Button {
	
	private ImageView userAvatarImgView = new ImageView();
	private Label nickNameLab = new Label();
	private ImageView recipeImg = new ImageView();
	private Label recipeTitle = new Label();
	private Label priceLab = new Label();
	private Label priceDateLab = new Label();
	
	public void setPriceLab(String price)
	{
		priceLab.setText(price);
	}
	
	public void setPriceDateLab(String priceDateStr)
	{
		priceDateLab.setText(priceDateStr);
	}
	public void setRecipeTitle(String title)
	{
		recipeTitle.setText(title);
	}
	
	public void setAvatarImage(String url)
	{
		userAvatarImgView.setImage(new Image(url));
	}
	
	public void setNickName(String nickname)
	{
		nickNameLab.setText(nickname);
	}
	
	public void setRecipeImage(String url) {
		recipeImg.setImage(new Image(url));
	}
	
	public RecipeListItem(int width, int height) {
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
		
		userAvatarImgView.setFitHeight(40.0);
		userAvatarImgView.setFitWidth(40.0);
		userAvatarImgView.setPickOnBounds(true);
		userAvatarImgView.setPreserveRatio(true);
		
		
		nickNameLab.setAlignment(Pos.BOTTOM_CENTER);
		
		titleArea.getChildren().add(userAvatarImgView);
		titleArea.getChildren().add(nickNameLab);
		
		
		recipeImg.setFitHeight(230.0);
		recipeImg.setFitWidth(width);
		recipeImg.setPickOnBounds(false);
		recipeImg.setPreserveRatio(false);
		
		
		FlowPane bottomLabArea = new FlowPane();
		
		HBox priceArea = new HBox();


		priceArea.getChildren().add(priceLab);
		//priceArea.getChildren().add(perPriceLab);
		//priceArea.getChildren().add(ingredientItemIdLab);
		priceArea.setHgrow(priceLab, Priority.ALWAYS);

		
		//priceLab.setText("15,800원");
		//priceDateLab.setText("2018.05.04");
		
		bottomLabArea.getChildren().add(priceLab);
		bottomLabArea.getChildren().add(priceDateLab);
		bottomLabArea.setMargin(priceDateLab, new Insets(0, 0, 0, 10.0));
		
	
		vbox.getChildren().add(titleArea); // HBOX
		vbox.getChildren().add(recipeImg); // imgView
		vbox.getChildren().add(recipeTitle);
		vbox.setMargin(recipeTitle, new Insets(0.0, 0.0, 0.0, 10.0));
		vbox.getChildren().add(priceArea);
		vbox.getChildren().add(bottomLabArea);
		vbox.setMargin(bottomLabArea, new Insets(0, 0, 0, 10.0));
		
		vbox.getStylesheets().add("ViewCss/RecipeFindView.css");
		vbox.getStyleClass().add("listItemVbox");
		
		super.setGraphic(vbox);
		
	}

}
