package PizzaProgram.Panes;

import java.util.Random;

import PizzaProgram.CustomerAccount;
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
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class HomePane extends BorderPane {
	private Button signInBtn;
	private Button signUpBtn;
	private Button orderPizza;
	
	private Button profileBtn;
	private Button logOutBtn;
	
	private HBox hbb;
	private HBox hb;
	private TextField uNameFld;
	public HomePane(){
		
		hbb = new HBox();
		uNameFld = new TextField();
		uNameFld.setEditable(false);
		profileBtn = new Button("Profile");
		logOutBtn = new Button("Log Out");
		hbb.getChildren().addAll(uNameFld,profileBtn,logOutBtn);
		hbb.setAlignment(Pos.TOP_RIGHT);
		hbb.setSpacing(5);
		//hbb.setBackground(new Background(new BackgroundFill(Color.ALICEBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
		
		hb = new HBox();
		signInBtn = new Button("Sign In");
		signUpBtn = new Button("Sign Up");
		hb.getChildren().add(0, signInBtn);
		hb.getChildren().add(1, signUpBtn);
		
		hb.setSpacing(5);

		/*
		HBox topBar = new HBox();
		
		
		Text titleTxt = new Text("Max's Pizzaroni");
		titleTxt.setFont(Font.font(50));
		*/
		
		hb.setAlignment(Pos.TOP_RIGHT);
		setTop(hb);
		
		Pane centerPane = new Pane();
		
		//titleTxt.xProperty().bind(centerPane.widthProperty().divide(2));
		//titleTxt.yProperty().bind(centerPane.heightProperty().subtract(centerPane.heightProperty()));
		
		Circle pizza = new Circle();
		pizza.centerXProperty().bind((centerPane.widthProperty().divide(2)));
		pizza.centerYProperty().bind((centerPane.heightProperty().divide(2)));
		pizza.setRadius(180);
		pizza.setFill(Color.ORANGE);
		pizza.setStroke(Color.SADDLEBROWN);
		pizza.setStrokeWidth(20);
		
		centerPane.getChildren().addAll(pizza);
		
		for(int i = 0; i < 25; i++){
			Circle pep = new Circle();
			
			if(Math.random() < .51){
				pep.centerXProperty().bind(pizza.centerXProperty().add((int)(Math.random()*120)+5));
			}
			else{
				pep.centerXProperty().bind(pizza.centerXProperty().subtract((int)(Math.random()*120)+5));
			}
			if(Math.random() < .51){
				pep.centerYProperty().bind(pizza.centerYProperty().add((int)(Math.random()*120)+5));
			}
			else{
				pep.centerYProperty().bind(pizza.centerYProperty().subtract((int)(Math.random()*120)+5));
			}
			
			
			
			pep.setRadius(15);
			pep.setFill(Color.ORANGERED);
			centerPane.getChildren().add(pep);
		}
		
		HBox bottomBox = new HBox();
		orderPizza = new Button("Order Pizza");
		orderPizza.setFont(Font.font(20));
		orderPizza.setPrefHeight(50);
		orderPizza.setPrefWidth(200);
		bottomBox.getChildren().add(orderPizza);
		bottomBox.setAlignment(Pos.CENTER);
		setBottom(bottomBox);
		
		setCenter(centerPane);
	}
	public void logIn(CustomerAccount ca){
		hbb.getChildren().clear();
		uNameFld.setText(ca.getUsername());
		hbb.getChildren().addAll(uNameFld,profileBtn,logOutBtn);
		hbb.setAlignment(Pos.TOP_RIGHT);
		setTop(hbb);
	}
	
	public void logOut(){
		hb.getChildren().clear();
		hb.getChildren().addAll(signInBtn,signUpBtn);
		hb.setAlignment(Pos.TOP_RIGHT);
		setTop(hb);
	}
	public void setSignUpAction(EventHandler<ActionEvent> e){
		signUpBtn.setOnAction(e);
	}
	public void setSignInAction(EventHandler<ActionEvent> e){
		signInBtn.setOnAction(e);
	}
	public void setOrderAction(EventHandler<ActionEvent> e){
		orderPizza.setOnAction(e);
	}
	
	public void setProfileAction(EventHandler<ActionEvent> e){
		profileBtn.setOnAction(e);
	}
	public void setLogOutAction(EventHandler<ActionEvent> e){
		logOutBtn.setOnAction(e);
	}
	
}

