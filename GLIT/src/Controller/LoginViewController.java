package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.jsoup.Connection;
import org.jsoup.Connection.Method;
import org.jsoup.Jsoup;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginViewController implements Initializable {

	@FXML
	private TextField inputID;
	@FXML
	private PasswordField inputPW;

	@FXML
	private Label errorMsg;


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// 자리 차지 안하게 하기
		//idMsg.setManaged(false);
	}

	//( pg - 1) / 10 * 10 + 1
	public void login(ActionEvent event) {

		System.out.println("로그인 클릭 ");

		String id = inputID.getText();
		String pw = inputPW.getText();

		//private boolean isIDDuplicate = false;

		if(id.equals(""))
		{
			errorMsg.setVisible(true);
			errorMsg.setText("아이디를 입력해주세요.");
			return;
		}

		if(pw.equals(""))
		{
			errorMsg.setVisible(true);
			errorMsg.setText("비밀번호를 입력해주세요.");
			return;
		}

		if(pw.length() < 8)
		{
			errorMsg.setVisible(true);
			errorMsg.setText("비밀번호는 8자 이상입니다.");
			return;
		}

		try {
			Connection.Response resp = Jsoup.connect("http://1.240.181.56:8080/auth/login")
					.method(Method.POST)
					.ignoreHttpErrors(true)
					.ignoreContentType(true)
					.header("API_Version", "1.0").data("id", id).data("pw", pw).execute();
			
			String json = resp.body();

			JSONParser jsonParse = new JSONParser();
			// JSONParse에 json데이터를 넣어 파싱한 다음 JSONObject로 변환한다.
			JSONObject jsonObj = (JSONObject) jsonParse.parse(json);

			if(jsonObj.get("status").equals("SUCCESS"))
			{
				JSONObject jsonData = (JSONObject) jsonObj.get("data");

				jsonData.get("token").toString();

				// 창 전환
				Scene registerScene = new Scene(FXMLLoader.load(getClass().getResource("/View/IngredientView.fxml")));
				Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
				window.setScene(registerScene);
			}
			else
			{
				if(jsonObj.get("errorCode").equals("LOGIN_FAILED"))
				{
					errorMsg.setVisible(true);
					errorMsg.setText(jsonObj.get("msg").toString());
					System.out.println("SDA");
				}
			}

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}



	}

	public void idPwBtn(ActionEvent event) throws IOException
	{
		// 창 전환
		Scene registerScene = new Scene(FXMLLoader.load(getClass().getResource("/View/IdPwFindView.fxml")));
		Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
		window.setScene(registerScene);

	}

	public void register(ActionEvent event) throws IOException
	{
		// 창 전환
		Scene registerScene = new Scene(FXMLLoader.load(getClass().getResource("/View/TestRegisterView.fxml")));
		Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
		window.setScene(registerScene);

	}

	public void backBtn(ActionEvent event) throws IOException{
		// 창 전환
		Scene registerScene =
				new Scene(FXMLLoader.load(getClass().getResource("/View/LoginView.fxml")));
		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		window.setScene(registerScene);

	}

}
