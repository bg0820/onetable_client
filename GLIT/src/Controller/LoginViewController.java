package Controller;

import java.io.IOException;
import java.net.URL;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginViewController {
	private OpenScene openScene;

	@FXML
	private TextField idInput;
	@FXML
	private TextField pwInput;
	@FXML
	private Button idFind;
	@FXML
	private Button newRegister;
	@FXML
	private Label message;

	/**public void initialize() throws Exception {
        PrintWriter writer = ... ;
        openScene = new OpenScene(writer);
    }
	 * @throws IOException

    @FXML // handler for connect button:
    private void btnConnect() throws Exception {
        Stage stage = (Stage) idFind.getScene().getWindow();
        openScene.start(stage);
    }**/

	Stage thisStage;

	public void setStage (Stage stage){
	    thisStage = stage;
	}

	public void showStage(){
	    thisStage.setTitle("Titel in der MainController.java geändert");
	    thisStage.show();
	}

	public void FindId(ActionEvent event) {
		URL location = getClass().getResource("/View/TestIdPwFindView.fxml");
	    FXMLLoader fxmlLoader = new FXMLLoader();
	    fxmlLoader.setLocation(location);
	    fxmlLoader.setBuilderFactory(new JavaFXBuilderFactory());
	    Parent root;
		try {
			root = (Parent) fxmlLoader.load(location.openStream());
			 Scene scene = new Scene(root);
			    thisStage.setScene(scene);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}


	public void RegisterPage(ActionEvent event) {
		URL location = getClass().getResource("/View/TestRegisterView.fxml");
	    FXMLLoader fxmlLoader = new FXMLLoader();
	    fxmlLoader.setLocation(location);
	    fxmlLoader.setBuilderFactory(new JavaFXBuilderFactory());
	    Parent root;
		try {
			root = (Parent) fxmlLoader.load(location.openStream());
			 Scene scene = new Scene(root);
			    thisStage.setScene(scene);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void Login(ActionEvent event) throws Exception {

		String id = idInput.getText();
		String pw = pwInput.getText();

		//private boolean isIDDuplicate = false;

		if(id.equals(""))
		{
			message.setText("Please enter your ID");
		};

		if(pw.equals(""))
		{
			message.setText("Please enter your PASSWORD");
		};

		 if(id.equals("user") && pw.equals("pass")){
			 	message.setText("Login Success");
	            Stage primaryStage = new Stage();
	            Parent root = FXMLLoader.load(getClass().getResource("/application/TestMain.fxml"));
	            Scene scene = new Scene(root);
	            scene.getStylesheets().add(getClass().getResource("LoginView.css").toExternalForm());
	            primaryStage.setScene(scene);
	            primaryStage.show();
	        }else{
	            message.setText("Login Failed");
	        }


		System.out.println(idInput.getText() + ", " + pwInput.getText());

		try {
			String doc = Jsoup.connect("http://1.240.181.56:8080/auth/login").ignoreContentType(true)
					.header("API_Version", "1.0").data("id", idInput.getText()).data("pw", pwInput.getText()).execute()
					.body();
   
			JSONParser jsonParse = new JSONParser();
			// JSONParse에 json데이터를 넣어 파싱한 다음 JSONObject로 변환한다.
			JSONObject jsonObj = (JSONObject) jsonParse.parse(doc);
			JSONObject jsonData = (JSONObject) jsonObj.get("data");

			jsonData.get("userUUID").toString();
			jsonData.get("nickname").toString();
 

			System.out.println(doc);
		} catch (HttpStatusException er) {
			if (er.getStatusCode() == 409) {
				System.out.println("아이디 또는 비밀번호가 잘못되었음");
			}  
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("Login Click");
	}
}
