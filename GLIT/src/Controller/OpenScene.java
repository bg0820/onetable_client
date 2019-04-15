package Controller;

import java.io.PrintWriter;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class OpenScene {
	private final PrintWriter writer ;

    public OpenScene(PrintWriter writer) {
        this.writer = writer ;
    }

    // doesn't need to be called "start" any more...
    public void start(Stage window) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FXML/TestMain.fxml"));
        Scene scene =  new Scene(root, 200 ,200);
        window.setScene(scene);
        window.show();
    }
}
