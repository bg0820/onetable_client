package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import org.jsoup.Connection.Method;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.jsoup.Connection;
import org.jsoup.Jsoup;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class RegisterViewController implements Initializable {

	@FXML
	private Button backBtn;
	@FXML
	private TextField inputID;
	@FXML
	private Label idMsg;
	@FXML
	private Label passMsg;
	@FXML
	private Label passconfirmMsg;
	@FXML
	private Label nicknameMsg;
	@FXML
	private Label emailMsg;
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

	ObservableList<String> emailList = FXCollections.observableArrayList("naver.com", "daum.net", "gmail.com",
			"hanmail.net", "nate.com", "paran.com", "직접입력");
	ObservableList<String> yearList = FXCollections.observableArrayList("1940");
	ObservableList<String> monthList = FXCollections.observableArrayList("1");
	ObservableList<String> dayList = FXCollections.observableArrayList("1");

	private boolean idDuplicateCheck = false;
	private boolean idCheck = false;
	private boolean nicknameDuplicate = false;
	private boolean nicknameDuplicateCheck = false;
	private boolean pwCheck = false;
	private boolean pwConfirmCheck = false;
	private boolean emailcheck = false;

	public void emailCheck(String email)
	{

		String URL = "http://1.240.181.56:8080/auth/register/duplicate/email";
		try {
			Connection.Response resp = Jsoup.connect(URL).method(Method.GET).ignoreContentType(true)
					.ignoreHttpErrors(true).header("API_Version", "1.0").data("email", email).execute();

			String json = resp.body();
			JSONParser jsonParser = new JSONParser();
			JSONObject jsonObj = (JSONObject) jsonParser.parse(json);
			System.out.println(json);
			System.out.println(jsonObj);
			if (Integer.parseInt(jsonObj.get("data").toString()) == 0) {

				emailMsg.setText("가입 가능합니다.");
				emailMsg.setVisible(true);
				emailMsg.setManaged(true);
				emailcheck = true;
			} else {

				emailMsg.setVisible(true);
				emailMsg.setManaged(true);
				emailMsg.setText("가입된 이메일입니다.");
				emailcheck = false;
			}

		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) { // TODO (Auto-generated) { block e.printStackTrace(); }
			e.printStackTrace();
		}
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// 자리 차지 안하게 하기
		idMsg.setManaged(false);
		passMsg.setManaged(false);
		passconfirmMsg.setManaged(false);
		nicknameMsg.setManaged(false);
		errorMsg.setManaged(false);
		emailMsg.setManaged(false);

		inputEmail.focusedProperty().addListener(new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				// TODO Auto-generated method stub
				if(!newValue)
				{
					if(inputEmailID.getText().length() > 0 && inputEmail.getText().length() > 0)
					{
						emailCheck(inputEmailID.getText() + "@" + inputEmail.getText());
					}
					System.out.println("Email Focus Out");
					
				}
			}
		});
		
		inputEmailID.focusedProperty().addListener(new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				// TODO Auto-generated method stub
				if(!newValue)
				{
					if(inputEmailID.getText().length() > 0 && inputEmail.getText().length() > 0)
					{
					
						emailCheck(inputEmailID.getText() + "@" + inputEmail.getText());
					}
					System.out.println("EmailId Focus Out");
				}
			}
		});
		
		
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

	/*
	 * public boolean isIdCheck(String id) { int num = 0; int alphbet = 0;
	 * 
	 * if(id.length() <= 12 && isIdCheck(id)) { return true; }
	 * 
	 * for(int i = 0 ; i < id.length(); i++) { char c = id.charAt(i);
	 * 
	 * if((int)c >= 48 && (int)c <= 57) { // 숫자 return true; } else if((int)c >= 65
	 * && (int)c <= 90) { // 대문자 return true; } else if((int)c >= 97 && (int)c <=
	 * 122) {// 소문자 return true; } else { return false; }
	 * 
	 * } }
	 */
	// ( pg - 1) / 10 * 10 + 1

	public boolean isValid(String text, String type) {
		if (type.equals("ID")) {
			if (text.matches("(^[A-Z|a-z|0-9]*$)"))
				return true;
			else
				return false;
		} else if (type.equals("PW")) {
			if (text.matches("(^[A-Z|a-z|0-9|!|@|%|#|$|^|&|*|(|)|_|+|-]*$)"))
				return true;
			else
				return false;
		}
		
		return false;

	}

	public void idKeyRelease(KeyEvent event) {
		// System.out.println(event.getCode());
		String id = inputID.getText();

		if (!isValid(id, "ID")) {
			idMsg.setManaged(true);
			idMsg.setVisible(true);
			idMsg.setText("영문, 숫자로  4~12자이내로 쓰시오.");
			idCheck = false;
		} else {
			if (id.length() >= 4 && id.length() <= 12) // 제대로 쓴거
			{
				idMsg.setManaged(false);
				idMsg.setVisible(false);
				idCheck = true;
			} else {
				idMsg.setManaged(true);
				idMsg.setVisible(true);
				idMsg.setText("영문, 숫자로  4~12자이내로 쓰시오.");
				idCheck = false;
			}
		}
	}

	public void IdCheckBtn(ActionEvent event) {

		if (!idDuplicateCheck) {
			errorMsg.setVisible(true);
			errorMsg.setText("아이디 중복확인을 해주세요");
			return;

		}
	}

	public void idDuplicateCheck(ActionEvent event) {

		idMsg.setVisible(true);
		idMsg.setManaged(true);

		String URL = "http://1.240.181.56:8080/auth/register/duplicate/id";
		try {
			Connection.Response resp = Jsoup.connect(URL).method(Method.GET).ignoreContentType(true)
					.ignoreHttpErrors(true).header("API_Version", "1.0").data("id", inputID.getText()).execute();

			String json = resp.body();
			JSONParser jsonParser = new JSONParser();
			JSONObject jsonObj = (JSONObject) jsonParser.parse(json);
			if (Integer.parseInt(jsonObj.get("data").toString()) == 0) {
				idMsg.setText("사용가능");
				idDuplicateCheck = true;
			} else {
				idMsg.setText("존재하는 아이디 입니다.");
				idDuplicateCheck = false;
			}

		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) { // TODO (Auto-generated) { block e.printStackTrace(); }
			e.printStackTrace();
		}
	}

	public void pwKeyRelease(KeyEvent event) {
		// System.out.println(event.getCode());
		String pw = inputPW.getText();
		

		if (!isValid(pw, "PW"))
		{
			passMsg.setManaged(true);
			passMsg.setVisible(true);
			passMsg.setText("영문, 숫자로  8~16자이내로 쓰시오.");
			pwCheck = false;
			
		}else {
			if (pw.length() >= 8 && pw.length() <= 16) // 제대로 쓴거
			{
				passMsg.setManaged(false);
				passMsg.setVisible(false);
				pwCheck = true;
			} else {
				passMsg.setManaged(true);
				passMsg.setVisible(true);
				passMsg.setText("영문, 숫자로  8~16자이내로 쓰시오.");
				pwCheck = false;
			}
		}
		
	}

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

		/*
		 * if (!isIDDuplicate) { errorMsg.setVisible(true);
		 * errorMsg.setText("아이디 중복확인을 해주세요"); return; }
		 */

		if (!emailcheck || !idCheck || !idDuplicateCheck || !nicknameDuplicate || !nicknameDuplicateCheck || !pwCheck || !pwConfirmCheck) {
			errorMsg.setVisible(true);
			errorMsg.setManaged(true);
			errorMsg.setText("회원가입 실패");
			return;
		}

		String URL = "http://1.240.181.56:8080/auth/register";
		try {
			Connection.Response resp = Jsoup.connect(URL).method(Method.POST).ignoreContentType(true)
					.ignoreHttpErrors(true).header("API_Version", "1.0").data("id", id).data("pw", pw)
					.data("email", email).data("nickname", nickname).data("birthday", birthday).execute();

			String json = resp.body();
			System.out.println(json);

			JSONParser jsonParser = new JSONParser();
			JSONObject jsonObj = (JSONObject) jsonParser.parse(json);

			if (jsonObj.get("status").equals("SUCCESS")) {
				errorMsg.setManaged(false);
				errorMsg.setVisible(false);
				errorMsg.setText("회원가입 성공");

				// 창 전환
				Scene registerScene = new Scene(FXMLLoader.load(getClass().getResource("/View/LoginView.fxml")));
				Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
				window.setScene(registerScene);

			} else {
				if (jsonObj.get("errorCode").equals("CREATE_DUPLICATE")) {
					errorMsg.setManaged(true);
					errorMsg.setVisible(true);
					errorMsg.setText(jsonObj.get("msg").toString());
				}
			}

		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void nicknameKeyRelease(KeyEvent event) {
		// System.out.println(event.getCode());
		String nickname = inputNickname.getText();

		
			if (nickname.length() <= 8) // 제대로 쓴거
			{
				nicknameMsg.setManaged(false);
				nicknameMsg.setVisible(false);
				nicknameDuplicate = true;

			} else {
				nicknameMsg.setManaged(true);
				nicknameMsg.setVisible(true);
				nicknameMsg.setText("8자이내");
				nicknameDuplicate = false;
			}
		
		
	}

	public void NicknameCheckBtn(ActionEvent event) {

		if (!nicknameDuplicate) {
			errorMsg.setVisible(true);
			errorMsg.setText("닉네임 중복확인을 해주세요");
			return;

		}
	}

	public void pwConfirmKeyRelease(KeyEvent event) {
		if(!inputPW.getText().equals(inputPWConfirm.getText()))
		{
			// 같지 않을때
			passconfirmMsg.setManaged(true);
			passconfirmMsg.setVisible(true);
			passconfirmMsg.setText("비밀번호가 일치하지 않습니다.");
			pwConfirmCheck = false;
		}
		else {
			passconfirmMsg.setManaged(false);
			passconfirmMsg.setVisible(false);
			// 같을떄
			pwConfirmCheck = true;
		}
		 
	}
	
	public void nicknameDuplicateCheck(ActionEvent event) {

		nicknameMsg.setVisible(true);
		nicknameMsg.setManaged(true);

		String URL = "http://1.240.181.56:8080/auth/register/duplicate/nickname";
		try {
			Connection.Response resp = Jsoup.connect(URL).method(Method.GET).ignoreContentType(true)
					.ignoreHttpErrors(true).header("API_Version", "1.0").data("nickname", inputNickname.getText())
					.execute();

			String json = resp.body();
			JSONParser jsonParser = new JSONParser();
			JSONObject jsonObj = (JSONObject) jsonParser.parse(json);
			if (Integer.parseInt(jsonObj.get("data").toString()) == 0) {
				nicknameMsg.setText("사용가능");
				nicknameDuplicate = true;
			} else {
				nicknameMsg.setText("존재하는  닉네임입니다.");
				nicknameDuplicate = false;
			}

		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) { // TODO (Auto-generated) { block e.printStackTrace(); }
			e.printStackTrace();
		}
	}


	
	/*
	 * public void pwKeyRelease(KeyEvent event) {
	 * 
	 * if(!pw.) }
	 */

	/*
	 * public void idDuplicateCheck(ActionEvent event) {
	 * 
	 * idMsg.setVisible(true); idMsg.setManaged(true);
	 * 
	 * String URL = "http://1.240.181.56:8080/auth/register/duplicate/id"; try {
	 * Connection.Response resp = Jsoup.connect(URL).method(Method.GET)
	 * .ignoreContentType(true) .ignoreHttpErrors(true) .header("API_Version",
	 * "1.0").data("id", inputID.getText()).execute();
	 * 
	 * String json = resp.body(); JSONParser jsonParser = new JSONParser();
	 * JSONObject jsonObj = (JSONObject) jsonParser.parse(json);
	 * if(Integer.parseInt(jsonObj.get("data").toString()) == 0) {
	 * idMsg.setText("사용가능"); isIDDuplicate = true; } else {
	 * idMsg.setText("존재하는 아이디 입니다."); isIDDuplicate = false; }
	 * 
	 * 
	 * } catch (ParseException e) { e.printStackTrace(); } catch (IOException e) {
	 * // TODO (Auto-generated) { block e.printStackTrace(); }
	 * 
	 * }
	 */

	public void backBtn(ActionEvent event) throws IOException {
		// 창 전환
		Scene registerScene = new Scene(FXMLLoader.load(getClass().getResource("/View/LoginView.fxml")));
		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		window.setScene(registerScene);

	}
}
