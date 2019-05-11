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

	public IngredientListItem(double width, double height) {
		this.width = width;
		this.height = height;

		super.prefWidth(width);
		super.prefHeight(height);
		super.setGraphicTextGap(0.0);
		super.getStylesheets().add("ViewCss/IngredientView.css");
		super.getStyleClass().add("listItemBtn");
		super.setMnemonicParsing(false);

		VBox vbox = new VBox();
		HBox titleHBox = new HBox();
		titleHBox.setAlignment(Pos.CENTER_RIGHT);



		titleHBox.getChildren().add(priceDateLab);
		titleHBox.setMargin(priceDateLab, new Insets(10, 10, 10, 0));


		imgView.setFitWidth(width);
		imgView.setFitHeight(230);


		HBox priceArea = new HBox();

  
		priceArea.getChildren().add(priceLab);
		priceArea.getChildren().add(perPriceLab);
		priceArea.setHgrow(priceLab, Priority.ALWAYS);

		vbox.getChildren().add(titleHBox);
		vbox.getChildren().add(imgView);
		vbox.getChildren().add(displayNameLab);
		vbox.getChildren().add(priceArea);
		vbox.setMargin(displayNameLab, new Insets(20, 0, 0, 0));
		super.setGraphic(vbox);
	}

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
}
