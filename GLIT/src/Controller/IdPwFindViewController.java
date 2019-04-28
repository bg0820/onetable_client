package Controller;

import java.io.IOException;

import org.jsoup.Connection.Method;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class IdPwFindViewController {
	@FXML
	private TextField emailInputToFindID;
	@FXML
	private TextField emailInputToFindPW;
	@FXML
	private TextField idInputToFindPW;

	@FXML
	private Label IderrorMsg;

	@FXML
	private Label PwerrorMsg;


	public void findIdBtn(ActionEvent event) {

			IderrorMsg.setVisible(true);
			IderrorMsg.setText("이메일이 발송되었습니다.");


		String URL = "http://1.240.181.56:8080/auth/find/id";
		try {
			Response resp = Jsoup.connect(URL)
					.method(Method.POST)
					.ignoreContentType(true)
					.ignoreHttpErrors(true)
					.header("API_Version", "1.0")
					.data("email", emailInputToFindID.getText())
					.execute();

			//Document doc = resp.parse();
			String body = resp.body();

			if(resp.statusCode() == 200)
			{
				System.out.println(body);
			} else {

				System.out.println(body);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void findPwBtn(ActionEvent event) {

		PwerrorMsg.setVisible(true);
		PwerrorMsg.setText("이메일이 발송되었습니다.");

		String URL = "http://1.240.181.56:8080/auth/find/pw";
		try {
			Response resp = Jsoup.connect(URL)
					.method(Method.POST)
					.ignoreContentType(true)
					.ignoreHttpErrors(true)
					.header("API_Version", "1.0")
					.data("id", idInputToFindPW.getText())
					.data("email", emailInputToFindPW.getText())
					.execute();

			//Document doc = resp.parse();
			String body = resp.body();

			if(resp.statusCode() == 200)
			{
				System.out.println(body);
			} else {

				System.out.println(body);
			}
		} catch (IOException e) {

		}
	}

	public void backBtn(ActionEvent event) throws IOException {
		// 창 전환
		Scene registerScene =
				new Scene(FXMLLoader.load(getClass().getResource("/View/LoginView.fxml")));
		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		window.setScene(registerScene);
	}
}
