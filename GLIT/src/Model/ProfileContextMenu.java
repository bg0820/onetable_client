package Model;

import java.io.IOException;

import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ProfileContextMenu extends AnchorPane {

	private VBox contextMenu = new VBox();
	private Label[] contextLabel = new Label[3];

	public ProfileContextMenu() {
		contextLabel[0] = new Label();
		contextLabel[1] = new Label();
		contextLabel[2] = new Label();

		contextMenu.getStylesheets().add("ViewCss/Common.css");
		contextMenu.getStyleClass().add("contextMenuArea");
		contextMenu.setPrefWidth(130);
		contextMenu.setPrefHeight(150);
		contextMenu.setAlignment(Pos.TOP_CENTER);

		contextLabel[0].setAlignment(Pos.CENTER);
		contextLabel[0].setText("마이페이지");
		contextLabel[1].setAlignment(Pos.CENTER);
		contextLabel[1].setText("즐겨찾기");
		contextLabel[2].setAlignment(Pos.CENTER);
		contextLabel[2].setText("로그아웃");

		contextLabel[0].setPrefHeight(50);
		contextLabel[1].setPrefHeight(50);
		contextLabel[2].setPrefHeight(50);

		contextLabel[0].setMaxWidth(Double.MAX_VALUE);
		contextLabel[1].setMaxWidth(Double.MAX_VALUE);
		contextLabel[2].setMaxWidth(Double.MAX_VALUE);

		contextLabel[2].getStylesheets().add("ViewCss/Common.css");
		contextLabel[2].getStyleClass().add("contextLogout");

		contextLabel[0].getStyleClass().add("contextLabel");
		contextLabel[1].getStyleClass().add("contextLabel");
		contextLabel[2].getStyleClass().add("contextLabel");

		contextMenu.getChildren().add(contextLabel[0]);
		contextMenu.getChildren().add(contextLabel[1]);
		contextMenu.getChildren().add(contextLabel[2]);


	}

	public void showContextMenu(MouseEvent event) {
		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		Parent root = window.getScene().getRoot();
		AnchorPane anp = ((AnchorPane) root);

		AnchorPane backAnchor = new AnchorPane();

		backAnchor.setMinWidth(Double.MAX_VALUE);
		backAnchor.setMinHeight(Double.MAX_VALUE);
		backAnchor.setMaxWidth(Double.MAX_VALUE);
		backAnchor.setMaxHeight(Double.MAX_VALUE);

		backAnchor.getChildren().add(contextMenu);
		backAnchor.setLeftAnchor(contextMenu, event.getSceneX()-130);
		backAnchor.setTopAnchor(contextMenu, event.getSceneY());

		backAnchor.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				if (event.getTarget() instanceof AnchorPane) {
					if (anp.getChildren().contains(backAnchor))
						anp.getChildren().remove(backAnchor);

				}
			}
		});
		anp.getChildren().add(backAnchor);

		for (int i = 0; i < contextLabel.length; i++) {
			contextLabel[i].setOnMouseClicked(new EventHandler<MouseEvent>() {

				@Override
				public void handle(MouseEvent event) {
					try {
						Scene scene = null;

						if (contextLabel[0] == event.getSource()) {
							scene = new Scene(FXMLLoader
									.load(getClass().getResource("/View/LoginView.fxml")));

						} else if (contextLabel[1] == event.getSource()) {
							scene = new Scene(FXMLLoader
									.load(getClass().getResource("/View/LoginView.fxml")));
						} else if (contextLabel[2] == event.getSource()) {
							scene = new Scene(FXMLLoader
									.load(getClass().getResource("/View/LoginView.fxml")));
						}

						Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
						window.setScene(scene);
					} catch (IOException e) {

					}
				}

			});
		}
	}
}
