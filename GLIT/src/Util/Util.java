package Util;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

public class Util {
	// Alert message = Util.MessageBox;
	// message.show();
	public static Alert MessageBox(AlertType type,
			String title,
			String content,
			ButtonType... buttonTypes) {
		Alert alert = new Alert(type);
		alert.getDialogPane().getButtonTypes().addAll(buttonTypes);
		alert.setTitle(title);
		alert.setContentText(content);
		alert.setResizable(false);

		return alert;
	}
}
