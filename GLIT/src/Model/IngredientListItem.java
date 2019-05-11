package Model;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class IngredientListItem extends VBox{
	private int width;
	private int height;
	
	public IngredientListItem(int width, int height) {
		this.width = width;
		this.height = height;  
		
		ImageView imgView = new ImageView();
		imgView.setFitWidth(this.width);
		imgView.setFitHeight(200);
		
		Label priceLabel  = new Label();
		Label displayNameLabel = new Label();
		Label priceDateLabel = new Label();
		
		priceLabel.setText("1,000원");
		displayNameLabel.setText("상추");
		priceDateLabel.setText("2018/05/04");
		     
		HBox hbox1 = new HBox();
		HBox hbox2 = new HBox();
		hbox1.getChildren().add(priceLabel);
		hbox1.getChildren().add(displayNameLabel);
		hbox1.prefWidth(USE_COMPUTED_SIZE);
		hbox1.prefHeight(USE_COMPUTED_SIZE);
		
		hbox2.setAlignment(Pos.CENTER_RIGHT);
		hbox2.getChildren().add(displayNameLabel);
		hbox1.prefWidth(USE_COMPUTED_SIZE);
		hbox2.prefHeight(USE_COMPUTED_SIZE);
		
		super.getChildren().add(imgView);
		super.getChildren().add(hbox1);
		super.getChildren().add(hbox2);
		super.getStylesheets().add("ViewCss/TestCustom.css");
		super.getStyleClass().add("listItem");
	}
	
	public IngredientListItem getIngredientListItem()
	{
		return this;
	}
}
