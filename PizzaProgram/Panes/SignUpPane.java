package PizzaProgram.Panes;

import PizzaProgram.Address;
import PizzaProgram.CustomerAccount;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class SignUpPane extends BorderPane {
	private CustomerAccount[] customerList;
	
	private int currentStage;
	
	private String password;
	private String username;
	
	private Button nextBtn;
	private Button cancelBtn;
	
	private Button nextBtn1;
	private Button backBtn1;
	private Button cancelBtn1;
	
	private TextField nameField;
	private TextField userNameField;
	private PasswordField passwordField;
	private PasswordField passwordConfirmField;
	
	private TextField phoneNumField;
	private TextField streetAddressField;
	private TextField cityField;
	private TextField zipField;
	private TextField creditCardNumField;
	
	private HBox hb;
	private GridPane gp;
	private GridPane gp1;
	
	private Text reason1Txt;
	private Text reason2Txt;
	public SignUpPane(){
		currentStage = 0;
		gp = new GridPane();
		
		Text nameTxt = new Text("Enter Name: ");
		nameField = new TextField();
		gp.add(nameTxt, 0, 0);
		gp.add(nameField, 1, 0);
		
		Text userNameTxt = new Text("Enter Username: ");
		userNameField = new TextField();
		gp.add(userNameTxt, 0, 1);
		gp.add(userNameField, 1, 1);
		
		Text passwordTxt = new Text("Enter Password: ");
		passwordField = new PasswordField();
		gp.add(passwordTxt, 0, 2);
		gp.add(passwordField, 1, 2);
		
		Text passwordConfirmTxt = new Text("Confirm Password: ");
		passwordConfirmField = new PasswordField();
		gp.add(passwordConfirmTxt, 0, 3);
		gp.add(passwordConfirmField, 1, 3);
		
		hb = new HBox();
		
		nextBtn = new Button("Next");
		cancelBtn = new Button("Cancel");
		hb.getChildren().add(0,nextBtn);
		hb.getChildren().add(1,cancelBtn);
		gp.add(hb, 1, 4);
		
		gp.setAlignment(Pos.CENTER);
		
		gp1 = new GridPane();
		HBox hb1 = new HBox();
		
		nextBtn1 = new Button("Create Account");
		backBtn1 = new Button("Back");
		cancelBtn1 = new Button("Cancel");
		
		hb1.getChildren().add(0,nextBtn1);
		hb1.getChildren().add(1,backBtn1);
		hb1.getChildren().add(2,cancelBtn1);
		
		
		Text streetAddressTxt = new Text("Street Address: ");
		Text cityTxt = new Text("City: ");
		Text zipTxt = new Text("Zip Code: ");
		
		streetAddressField = new TextField();
		cityField = new TextField();
		zipField = new TextField();
		
		Text creditCardTxt = new Text("Credit Card Number: ");
		creditCardNumField = new TextField();
		
		gp1.add(streetAddressTxt, 0, 0);
		gp1.add(streetAddressField, 1, 0);
		
		gp1.add(cityTxt, 0, 1);
		gp1.add(cityField, 1, 1);
		
		gp1.add(zipTxt, 0, 2);
		gp1.add(zipField, 1, 2);
		
		gp1.add(creditCardTxt, 0,3);
		gp1.add(creditCardNumField, 1, 3);
		
		gp1.add(hb1, 1, 4);
		
		reason1Txt = new Text("");
		reason2Txt = new Text("");
		
		gp1.add(reason2Txt, 1, 5);
		gp.add(reason1Txt, 1, 5);
		
		gp1.setAlignment(Pos.CENTER);
		
		setCenter(gp);
	}

	public boolean accountParams1(){
		return (nameField.getText().length() > 0 && userNameField.getText().length() > 0 && userNameField.getText().length() > 0 && passwordField.getText().length() > 0 && passwordField.getText().equals(passwordConfirmField.getText()));
	}
	public boolean accountParams2(){
		return (streetAddressField.getText().length() > 0 && cityField.getText().length() > 0 && zipField.getText().length() == 5 && creditCardNumField.getText().length() >= 16);
	}
	public String getReason1(){
		if(nameField.getText().length() < 1){
			return "Enter name.";
		}
		else if(userNameField.getText().length() < 1){
			return "Enter username.";
		}
		else if(passwordField.getText().length() < 1){
			return "Enter password.";
		}
		else if(!passwordField.getText().equals(passwordConfirmField.getText())){
			return "Passwords do not match.";
		}
		return "username taken.";
	}
	public String getReason2(){
		if(streetAddressField.getText().length() < 1){
			return "Enter street address.";
		}
		else if(cityField.getText().length() < 1){
			return "Enter city.";
		}
		else if(zipField.getText().length() != 5){
			return "Enter zip code.";
		}
		else if(creditCardNumField.getText().length() < 16){
			return "Enter card number code.";
		}
		
		return "No reason";
	}
	//TODO: get this to go go go
	public void setReason1(String reason){
		reason1Txt.setText(reason);
		reason1Txt.setFill(Color.RED);
	}
	public void setReason2(String reason){
		reason2Txt.setText(reason);
		reason2Txt.setFill(Color.RED);
	}
	
	public TextField getNameFld(){
		return nameField;
	}
	public TextField getUsernameFld(){
		return userNameField;
	}
	public TextField getPasswordFld(){
		return passwordField;
	}
	public TextField getConfirmPasswordField(){
		return passwordConfirmField;
	}

	//Page 0
	
	public void setNextAction(EventHandler<ActionEvent> e){
		nextBtn.setOnAction(e);
	}
	
	public void setCancelAction(EventHandler<ActionEvent> e){
		cancelBtn.setOnAction(e);
	}
	
	//Page 1
	
	public void setNextAction1(EventHandler<ActionEvent> e){
		nextBtn1.setOnAction(e);
	}
	
	public void setCancelAction1(EventHandler<ActionEvent> e){
		cancelBtn1.setOnAction(e);
	}
	
	public void setBackAction1(EventHandler<ActionEvent> e){
		backBtn1.setOnAction(e);
	}
	
	public int getStage(){
		return currentStage;
	}
	public void setAccountList(CustomerAccount[] list){
		customerList = list;
	}
	
	public void resetValues(){
		nameField.setText("");
		userNameField.setText("");
		passwordField.setText("");
		passwordConfirmField.setText("");
		streetAddressField.setText("");
		cityField.setText("");
		zipField.setText("");
		creditCardNumField.setText("");
	}
	
	public void moveToScene(int sceneNumber){
		currentStage = sceneNumber;
		if(sceneNumber == 0){
			setCenter(gp);
		}
		else if(sceneNumber == 1){
			setCenter(gp1);
		}
	}
	public CustomerAccount getFirstPageDetails(){
		return new CustomerAccount(userNameField.getText(),passwordField.getText(),"1234567890123456",nameField.getText(),new Address("","",00000));
	}
	
	public CustomerAccount getCustomerAccount(){
		return new CustomerAccount(userNameField.getText(),passwordField.getText(),creditCardNumField.getText(),nameField.getText(),new Address(streetAddressField.getText(),cityField.getText(),Integer.parseInt(zipField.getText())));
	}
	
}
