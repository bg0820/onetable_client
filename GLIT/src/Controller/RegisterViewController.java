package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;

import org.jsoup.Connection;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class RegisterViewController implements Initializable {
	@FXML
	private ComboBox<String> emailList;
	ObservableList<String> list = FXCollections.observableArrayList("naver.com", "daum.net", "gmail.com", "hanmail.net",
			"nate.com", "paran.com");
	@FXML
	private ComboBox<String> yearL;
	ObservableList<String> yearList = FXCollections.observableArrayList("1940");

	@FXML
	private ComboBox<String> monthL;
	ObservableList<String> monthList = FXCollections.observableArrayList("1");

	@FXML
	private ComboBox<String> dayL;
	ObservableList<String> dayList = FXCollections.observableArrayList("1");

	@FXML
	private TextField inputId;
	@FXML
	private PasswordField inputPw;
	@FXML
	private PasswordField inputPwConfirm;
	@FXML
	private TextField inputEmailId;
	@FXML
	private TextField inputEmail;
	@FXML
	private TextField inputNickname;
	
	
	Stage thisStage;

	public void setStage (Stage stage){
	    thisStage = stage;
	}

	public void showStage(){
	    thisStage.setTitle("Titel in der MainController.java geändert");
	    thisStage.show();
	}
	
	
	// ȭ�� ������ ���ʷ� ����Ǵ� �Լ�
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//System.out.println("Init");
		// Calendar time = Calendar.getInstance();
		Date date = new Date();

		for (int i = 1941; i <= 1900 + date.getYear(); i++) 
			yearList.add(String.valueOf(i)); // + "��");

		for (int i = 2; i <= 12; i++) 
			monthList.add(String.valueOf(i)); // + "��");
		
		for (int i = 2; i <= 31; i++) 
			dayList.add(String.valueOf(i));//k + "��");
		
		FXCollections.reverse(yearList);
		yearL.setItems(yearList);
		yearL.getSelectionModel().select(String.valueOf(1900 + date.getYear()) );

		monthL.setItems(monthList);
		monthL.getSelectionModel().select("1");

		dayL.setItems(dayList);
		dayL.getSelectionModel().select("1");

		emailList.setItems(list);
		emailList.getSelectionModel().select("naver.com");
	}
	
	public void Duplicate(ActionEvent action)
	{
		System.out.println("Click");
		String URL = "http://1.240.181.56:8080/auth/duplicate/id";
		try {
			String doc = Jsoup.connect(URL)
					.header("API_Version", "1.0")
					.ignoreContentType(true)
					.data("id", inputId.getText())
					.execute().body();
			
			System.out.println(doc);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void Register(ActionEvent action)
	{
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Message Here...");
		alert.setHeaderText("Look, an Information Dialog");
		alert.setContentText("I have a great message for you!");
		alert.showAndWait().ifPresent(rs -> {
		    if (rs == ButtonType.OK) {
		        System.out.println("Pressed OK.");
		        
		    }
		});
		
		/*
		System.out.println("Click");
		String URL = "http://1.240.181.56:8080/auth/register";
		try {
			// id 20, pw > 8, nickname 10, 
			String id = inputId.getText();
			String pw = inputPw.getText();
			String pwConfirm = inputPwConfirm.getText();
			String emailId = inputEmailId.getText();
			String email = inputEmail.getText();
			String emailStr = emailId + "@" + email; // bg0820 @ naver.com
			// yyyyMMdd 01
			int year = Integer.parseInt(yearL.getSelectionModel().getSelectedItem());
			int month = Integer.parseInt(monthL.getSelectionModel().getSelectedItem());
			String monthStr = month < 10 ? "0" + month : String.valueOf(month);
			int day = Integer.parseInt(dayL.getSelectionModel().getSelectedItem());
			String dayStr = day < 10 ? "0" + day : String.valueOf(day);
			
			String birthday = year + monthStr + dayStr;
			String nickname = inputNickname.getText();
			
			System.out.println(id);
			System.out.println(emailStr);
			System.out.println(birthday);
			
			if(!pw.equals(pwConfirm))
			{
				System.out.println("D");
				//label.setText("lalfqjsghrk ekfmqselk");
				return;
			}
	
			
			
			String doc = Jsoup.connect(URL)
					.method(Connection.Method.POST)
					.header("API_Version", "1.0")
					.ignoreContentType(true)
					.data("id", id).data("pw", pw).data("email", emailStr).data("nickname", nickname).data("birthday", birthday)
					.execute().body();
			
			System.out.println(doc);
		} catch (HttpStatusException er) {
			if (er.getStatusCode() == 500) {
				System.out.println("�̹� ���Ե� ���̵�");
			}
		}  catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}

}
