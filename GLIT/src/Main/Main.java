package Main;


import java.net.URL;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;


public class Main extends Application {
	@Override

	public void start(Stage primaryStage) throws Exception {
		try { 
			Font f = Font.loadFont(getClass().getResourceAsStream("/Font/BMHANNA.ttf"), 14);
			System.out.println(f);

			URL url = getClass().getResource("/View/IngredientView.fxml");
			FXMLLoader fxmlLoader = new FXMLLoader();
			fxmlLoader.setLocation(url);
			fxmlLoader.setBuilderFactory(new JavaFXBuilderFactory());
			Parent root = (Parent)fxmlLoader.load(url.openStream());  
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.setMinWidth(900);
			primaryStage.setMinHeight(600);
			primaryStage.setTitle("한상차림");
			// 창 크기 조정 못하게
			primaryStage.setResizable(false);

			primaryStage.show();

		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
