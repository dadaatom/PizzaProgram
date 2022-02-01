package PizzaProgram.Panes;

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
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class ProfilePane extends BorderPane{
	private Button exitBtn;
	private Button changePassword;
	private int page;
	
	private Button leftBtn;
	private Button rightBtn;
	
	private Button deleteBtn;
	
	private Text reasonTxt;
	private PasswordField p1Fld;
	private PasswordField p2Fld;
	
	public ProfilePane(){
		exitBtn = new Button("Exit");
		deleteBtn = new Button("Delete Account");
		changePassword = new Button("Change Password");
		
		leftBtn = new Button("<");
		rightBtn = new Button(">");
		
		page = 0;

	}
	public void displayProfile(CustomerAccount ca){
		page = 0;
		
		BorderPane topBp = new BorderPane();
		
		HBox hb = new HBox();
		
		TextField userNameFld = new TextField();
		userNameFld.setEditable(false);
		userNameFld.setText(ca.getUsername()+"'s Profile");
		hb.getChildren().addAll(userNameFld,exitBtn);
		hb.setAlignment(Pos.TOP_RIGHT);
		hb.setSpacing(5);
		
		topBp.setRight(hb);
		topBp.setLeft(deleteBtn);
		
		setTop(topBp);
		
		HBox spine = new HBox();
		
		VBox vbb = new VBox();
		
		GridPane gp = new GridPane();
		
		gp.setAlignment(Pos.CENTER_LEFT);
		
		p1Fld = new PasswordField();
		p2Fld = new PasswordField();
		reasonTxt = new Text("");
		
		gp.add(new Text("New Password:"), 0, 0);
		gp.add(p1Fld, 1, 0);
		gp.add(new Text("Confirm Password:"), 0, 1);
		gp.add(p2Fld, 1, 1);
		gp.add(changePassword, 1, 2);
		gp.add(reasonTxt, 1, 3);
		
		vbb.getChildren().addAll(new Text(""), new Text(""), new Text("Change Password:"), new Text(""), gp);
		vbb.setAlignment(Pos.CENTER);
		
		VBox vb = new VBox();
		vb.setAlignment(Pos.CENTER_RIGHT);
		Text topText = new Text("Order History "+(1+page)+"/"+ca.getHistory().size());
		
		HBox hbb = new HBox();		
		hbb.getChildren().addAll(leftBtn,topText,rightBtn);
		hbb.setSpacing(10);
		hbb.setAlignment(Pos.CENTER);
		
		Text noHistoryTxt = new Text();
		noHistoryTxt.setFont(Font.font(25));
		noHistoryTxt.setText("No Order History");
		
		vb.getChildren().addAll(hbb,new Text(""));
		vb.getChildren().add(noHistoryTxt);
		
		if(ca.getHistory().size() > 0){
			OrderHistoryPane ohp = new OrderHistoryPane();
			ohp.displayOrder(ca.getHistory().get(page));
			vb.getChildren().set(2, ohp);
		}
		
		leftBtn.setOnAction(e -> {
			if(page > 0){
				page--;
				OrderHistoryPane ohp = new OrderHistoryPane();
				ohp.displayOrder(ca.getHistory().get(page));
				vb.getChildren().set(2, ohp);
				topText.setText("Order History "+(1+page)+"/"+ca.getHistory().size());
			}
		});
		rightBtn.setOnAction(e -> {
			if(page < ca.getHistory().size()-1){
				page++;
				OrderHistoryPane ohp = new OrderHistoryPane();
				ohp.displayOrder(ca.getHistory().get(page));
				vb.getChildren().set(2, ohp);
				topText.setText("Order History "+(1+page)+"/"+ca.getHistory().size());
			}
		});
		
		spine.setSpacing(50);
		spine.setAlignment(Pos.CENTER);
		spine.getChildren().addAll(vbb,vb);
		setCenter(spine);
	}
	public void setHomeExitAction(EventHandler<ActionEvent> e){
		exitBtn.setOnAction(e);
	}
	public void setChangePasswordAction(EventHandler<ActionEvent> e){
		changePassword.setOnAction(e);
	}
	public void setDeleteButton(EventHandler<ActionEvent> e){
		deleteBtn.setOnAction(e);
	}
	public boolean checkMatch(){
		if(p1Fld.getText().equals(p2Fld.getText())){
			return true;
		}
		else{
			reasonTxt.setFill(Color.RED);
			reasonTxt.setText("Passwords don't match.");
			return false;
		}
	}
	public String newPassword(){
		return p1Fld.getText();
	}
	public int getPage(){
		return page;
	}
}
