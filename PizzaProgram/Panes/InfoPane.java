package PizzaProgram.Panes;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class InfoPane extends VBox{
	private Button loginBtn; 
	private Button signUpBtn;
	private Button continueGuestBtn;
	private Button cancelBtn;
	public InfoPane(){
		continueGuestBtn = new Button("Continue As Guest");
		continueGuestBtn.setMinWidth(100);
		
		setAlignment(Pos.CENTER);
		setSpacing(5);
		
		Text logTxt = new Text("You are not logged in!");
		logTxt.setFont(Font.font(30));
		this.getChildren().addAll(logTxt,new Text(""));
		
		HBox hb = new HBox();
		loginBtn = new Button("Login");
		loginBtn.setMinWidth(continueGuestBtn.getWidth()/2);
		hb.getChildren().add(loginBtn);
		signUpBtn = new Button("Sign Up");
		signUpBtn.setMinWidth(continueGuestBtn.getWidth()/2);
		hb.getChildren().add(signUpBtn);
		hb.setAlignment(Pos.CENTER);
		this.getChildren().add(hb);

		this.getChildren().add(continueGuestBtn);
		
		cancelBtn = new Button("Cancel Order");
		cancelBtn.setMinWidth(continueGuestBtn.getWidth());
		this.getChildren().add(cancelBtn);
		
	
	}
	public void setCancelAction(EventHandler<ActionEvent> e){
		cancelBtn.setOnAction(e);
	}
	public void setLoginAction(EventHandler<ActionEvent> e){
		loginBtn.setOnAction(e);
	}
	public void setSignUpAction(EventHandler<ActionEvent> e){
		signUpBtn.setOnAction(e);
	}
	public void setContinueAction(EventHandler<ActionEvent> e){
		continueGuestBtn.setOnAction(e);
	}
}
