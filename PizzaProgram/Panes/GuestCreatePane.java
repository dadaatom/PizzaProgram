package PizzaProgram.Panes;

import PizzaProgram.Account;
import PizzaProgram.Address;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;


public class GuestCreatePane extends VBox{
	private Button continueBtn;
	private Button backBtn;
	
	private TextField nameFld;
	private TextField cardFld;
	private TextField streetFld;
	private TextField cityFld;
	private TextField zipFld;
	
	private Text reasonTxt;
	
	public GuestCreatePane(){
		this.setAlignment(Pos.CENTER);
		Text titleTxt = new Text("Fill out the information:");
		titleTxt.setFont(Font.font(30));
		this.getChildren().add(titleTxt);
		
		GridPane gp = new GridPane();
		gp.setAlignment(Pos.CENTER);
		
		nameFld = new TextField();
		gp.add(new Text("Name:"), 0, 0);
		gp.add(nameFld, 1, 0);

		cardFld = new TextField();
		gp.add(new Text("Card Number:"), 0, 1);
		gp.add(cardFld, 1, 1);
		
		streetFld = new TextField();
		gp.add(new Text("Street Address:"), 0, 2);
		gp.add(streetFld, 1, 2);
		
		cityFld = new TextField();
		gp.add(new Text("City:"), 0, 3);
		gp.add(cityFld, 1, 3);
		
		zipFld = new TextField();
		gp.add(new Text("Zip Code:"), 0, 4);
		gp.add(zipFld, 1, 4);
		
		HBox hb = new HBox();
		continueBtn = new Button("Continue");
		backBtn = new Button("Back");
		hb.getChildren().addAll(continueBtn,backBtn);
		gp.add(hb,1,5);
		this.getChildren().add(gp);
		
		hb.setAlignment(Pos.CENTER);
		
		
		reasonTxt = new Text("");
		reasonTxt.setFill(Color.RED);
		this.getChildren().add(reasonTxt);
	}
	public void resetValues(){
		nameFld.setText("");
		cardFld.setText("");
		streetFld.setText("");
		cityFld.setText("");
		zipFld.setText("");
	}
	public String getReason(){
		if(nameFld.getText().length() < 1){
			return "Enter name.";
		}
		else if(cardFld.getText().length() < 1){
			return "Enter card number.";
		}
		else if(streetFld.getText().length() < 1){
			return "Enter street address.";
		}
		else if(cityFld.getText().length() < 1){
			return "Enter city.";
		}
		else if(zipFld.getText().length() < 5){
			return "Enter zip code.";
		}
		return "No Reason.";
	}
	public boolean hasFilledFields(){
		if(nameFld.getText().length() < 1){
			return false;
		}
		else if(cardFld.getText().length() < 1){
			return false;
		}
		else if(streetFld.getText().length() < 1){
			return false;
		}
		else if(cityFld.getText().length() < 1){
			return false;
		}
		else if(zipFld.getText().length() < 5){
			return false;
		}
		return true;
	}
	public void setReason(String reason){
		reasonTxt.setText(reason);
	}
	public Account getAccount(){
		return new Account(nameFld.getText(),cardFld.getText(), new Address(streetFld.getText(),cityFld.getText(),Integer.parseInt(zipFld.getText())));
	}
	public void setContinueAction(EventHandler<ActionEvent> e){
		continueBtn.setOnAction(e);
	}
	public void setBackAction(EventHandler<ActionEvent> e){
		backBtn.setOnAction(e);
	}
}
