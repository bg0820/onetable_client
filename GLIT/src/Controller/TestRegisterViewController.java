package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.jsoup.Connection;
import org.jsoup.Connection.Method;
import org.jsoup.Jsoup;
import Util.Util;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class TestRegisterViewController implements Initializable {

	@FXML
	private Button backBtn;
	@FXML
	private TextField inputID;
	@FXML
	private Label idMsg;
	@FXML
	private PasswordField inputPW;
	@FXML
	private PasswordField inputPWConfirm;
	@FXML
	private TextField inputNickname;
	@FXML
	private TextField inputEmailID;
	@FXML
	private TextField inputEmail;
	@FXML
	private ComboBox<String> selectEmail;
	@FXML
	private ComboBox<String> selectYear;
	@FXML
	private ComboBox<String> selectMonth;
	@FXML
	private ComboBox<String> selectDay;
	@FXML
	private Label errorMsg;

	ObservableList<String> emailList = FXCollections.observableArrayList("naver.com",
			"daum.net",
			"gmail.com",
			"hanmail.net",
			"nate.com",
			"paran.com",
			"직접입력");
	ObservableList<String> yearList = FXCollections.observableArrayList("1940");
	ObservableList<String> monthList = FXCollections.observableArrayList("1");
	ObservableList<String> dayList = FXCollections.observableArrayList("1");

	private boolean isIDDuplicate = true;


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// 자리 차지 안하게 하기
		idMsg.setManaged(false);

		// TODO Auto-generated method stub
		Date date = new Date();
		int currentYear = 1900 + date.getYear();

		for (int i = 1941; i <= currentYear; i++)
			yearList.add(String.valueOf(i));

		for (int i = 2; i <= 12; i++) {
			monthList.add(String.valueOf(i));
			dayList.add(String.valueOf(i));
		}

		FXCollections.reverse(yearList);
		selectEmail.setItems(emailList);
		selectYear.setItems(yearList);
		selectMonth.setItems(monthList);
		selectDay.setItems(dayList);

		selectEmail.getSelectionModel().select("직접입력");
		selectYear.getSelectionModel().select(String.valueOf(currentYear));
		selectMonth.getSelectionModel().select("1");
		selectDay.getSelectionModel().select("1");

	}

	public void emailSelectChange(ActionEvent event) {
		if (selectEmail.getValue().equals("직접입력")) {
			inputEmail.setText("");
			inputEmail.setDisable(false);
		} else {
			inputEmail.setText(selectEmail.getValue());
			inputEmail.setDisable(true);
		}



	}

	// ( pg - 1) / 10 * 10 + 1
	public void register(ActionEvent event) {

		String id = inputID.getText();
		String pw = inputPW.getText();
		String pwConfirm = inputPWConfirm.getText();
		String email = inputEmailID.getText() + "@" + inputEmail.getText();
		String nickname = inputNickname.getText();
		int year = Integer.parseInt(selectYear.getSelectionModel().getSelectedItem());
		int month = Integer.parseInt(selectMonth.getSelectionModel().getSelectedItem());
		int day = Integer.parseInt(selectDay.getSelectionModel().getSelectedItem());
		String yearStr = year < 10 ? "0" + year : String.valueOf(year);
		String monthStr = month < 10 ? "0" + month : String.valueOf(month);
		String dayStr = day < 10 ? "0" + day : String.valueOf(day);
		String birthday = yearStr + monthStr + dayStr;

		if (!pw.equals(pwConfirm)) {
			errorMsg.setVisible(true);
			errorMsg.setText("비밀번호가 일치하지 않습니다.");
			return;
		}
		if (!isIDDuplicate) {
			errorMsg.setVisible(true);
			errorMsg.setText("아이디 중복확인을 해주세요");
			return;
		}

		String URL = "http://1.240.181.56:8080/auth/register";
		try {
			Connection.Response resp = Jsoup.connect(URL).method(Method.POST)
					.ignoreContentType(true).ignoreHttpErrors(true).header("API_Version", "1.0")
					.data("id", id).data("pw", pw).data("email", email).data("nickname", nickname)
					.data("birthday", birthday).execute();
			
			String json = resp.body();

			JSONParser jsonParser = new JSONParser();
			JSONObject jsonObj = (JSONObject) jsonParser.parse(json);

			if (jsonObj.get("status").equals("SUCCESS")) {
				errorMsg.setVisible(true);
				errorMsg.setText("회원가입 성공");

				Alert alert = new Alert(AlertType.NONE);
				alert.getDialogPane().getButtonTypes().addAll(ButtonType.OK);
			    alert.getDialogPane().setContentText("회원가입 성공");
			    // Fool the dialog into thinking there is some expandable content
			    // a Group won't take up any space if it has no children
			    alert.getDialogPane().setExpandableContent(new Group());
			    alert.getDialogPane().setExpanded(true);
			    // Reset the dialog graphic using the default style
			    Node graphic = alert.getDialogPane().getGraphic();
			    alert.getDialogPane().setGraphic(graphic);
				alert.setTitle("알림창");
			    alert.setHeaderText("알림창");
			    
		    
				// 창 전환
				Scene registerScene = new Scene(
						FXMLLoader.load(getClass().getResource("/View/LoginView.fxml")));
				Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
				window.setScene(registerScene);


			} else {
				if (jsonObj.get("errorCode").equals("CREATE_DUPLICATE")) {
					errorMsg.setVisible(true);
					errorMsg.setText("이미 존재하는 아이디 또는 이메일 또는 닉네임 입니다.");
				}
			}


		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
 
	public void idDuplicateCheck(ActionEvent event) {
		
	}


	public void backBtn(ActionEvent event) throws IOException{
		// 창 전환
		Scene registerScene =
				new Scene(FXMLLoader.load(getClass().getResource("/View/LoginView.fxml")));
		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		window.setScene(registerScene);

	}

}
