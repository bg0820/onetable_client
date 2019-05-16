package Model;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class RecipeListItem extends Button {
	
	
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

	public void setPrice(String price) {
		// TODO Auto-generated method stub
		
	}

	public void setPriceDate(String priceDate) {
		// TODO Auto-generated method stub
		
	}

	public void setDisplayName(String displayName) {
		// TODO Auto-generated method stub
		
	}

	public void setImageUrl(String url) {
		// TODO Auto-generated method stub
		
	}

	public void setRecipeItemId(String recipeItemId) {
		// TODO Auto-generated method stub
		
	}
}
