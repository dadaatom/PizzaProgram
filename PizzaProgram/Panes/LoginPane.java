package PizzaProgram.Panes;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class LoginPane extends BorderPane {
	private TextField userNameField;
	private PasswordField passwordField;
	
	private Text reasonTxt;
	
	private Button loginBtn;
	private Button cancelBtn;
	public LoginPane(){
		
		GridPane gp = new GridPane();
		
		reasonTxt = new Text("");
		gp.add(reasonTxt, 1, 0);
		
		Text userNameTxt = new Text("Username: ");
		userNameField = new TextField();
		gp.add(userNameTxt, 0, 1);
		gp.add(userNameField, 1, 1);
		
		Text passwordTxt = new Text("Password: ");
		passwordField = new PasswordField();
		gp.add(passwordTxt, 0, 2);
		gp.add(passwordField, 1, 2);
		
		HBox hb = new HBox();
		
		loginBtn = new Button("Login");
		cancelBtn = new Button("Cancel");
		hb.getChildren().add(0,loginBtn);
		hb.getChildren().add(1,cancelBtn);
		gp.add(hb, 1, 3);
		
		reasonTxt = new Text("");
		gp.add(reasonTxt, 1, 4);
		
		gp.setAlignment(Pos.CENTER);
		setCenter(gp);
	}
	public void resetValues(){
		userNameField.setText("");
		passwordField.setText("");
		reasonTxt.setText("");
	}
	public void displayReason(){
		reasonTxt.setText("Invalid username or password.");
		reasonTxt.setFill(Color.RED);
	}
	public TextField getUsernameFld(){
		return userNameField;
	}
	public PasswordField getPasswordFld(){
		return passwordField;
	}
	public void setLoginAction(EventHandler<ActionEvent> e){
		loginBtn.setOnAction(e);
	}
	public void setCancelAction(EventHandler<ActionEvent> e){
		cancelBtn.setOnAction(e);
	}
}
