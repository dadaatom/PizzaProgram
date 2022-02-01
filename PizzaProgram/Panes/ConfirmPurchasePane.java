package PizzaProgram.Panes;

import PizzaProgram.Account;
import PizzaProgram.Order;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class ConfirmPurchasePane extends BorderPane{
	private Order order;
	
	private GridPane spine;
	
	private Button confirmBtn;
	private Button cancelBtn;
	
	public ConfirmPurchasePane(){
		confirmBtn = new Button("Confirm Purchase");
		cancelBtn = new Button("Cancel Purchase");
	}
	public void displayOrder(Order order){
		
		spine  = new GridPane();
		
		spine.setAlignment(Pos.CENTER);
		setCenter(spine);
		
		HBox bottomBar = new HBox();
		bottomBar.getChildren().addAll(confirmBtn,cancelBtn);
		bottomBar.setAlignment(Pos.CENTER);
		setBottom(bottomBar);
		
		this.order = order;
		
		HBox hb = new HBox();
		Text confirmTxt = new Text("Confirm Purchase");
		confirmTxt.setFont(Font.font(50));
		hb.getChildren().add(confirmTxt);
		hb.setAlignment(Pos.CENTER);
		setTop(hb);
		
		spine.add(new Text(""),0,0);
		
		Text nameTxt = new Text("Buyer Name: ");//12
		nameTxt.setTextAlignment(TextAlignment.LEFT);
		TextField nameFld = new TextField();
		nameFld.setEditable(false);
		nameFld.setMinWidth(200);
		nameFld.setText(order.getBuyer().getName());
		spine.add(nameTxt, 0, 1);
		spine.add(nameFld, 1, 1);
		
		Text addressTxt = new Text("Address: ");//9
		addressTxt.setTextAlignment(TextAlignment.LEFT);
		TextField addressFld = new TextField();
		addressFld.setEditable(false);
		addressFld.setMinWidth(200);
		addressFld.setText(order.getBuyer().getAdress().getStreetAddress() + ", " + order.getBuyer().getAdress().getCity() + ".");
		spine.add(addressTxt, 0, 2);
		spine.add(addressFld, 1, 2);
		
		Text zipTxt = new Text("Zip Code: ");//10
		zipTxt.setTextAlignment(TextAlignment.LEFT);
		TextField zipFld = new TextField();
		zipFld.setEditable(false);
		zipFld.setMinWidth(200);
		zipFld.setText(order.getBuyer().getAdress().getZip() + "");
		spine.add(zipTxt, 0, 3);
		spine.add(zipFld, 1, 3);
		
		Text cardTxt = new Text("Card Number: ");//13
		cardTxt.setTextAlignment(TextAlignment.LEFT);
		TextField cardFld = new TextField();
		cardFld.setEditable(false);
		cardFld.setMinWidth(200);
		cardFld.setText(order.getBuyer().getCardNumber() + "");
		spine.add(cardTxt, 0, 4);
		spine.add(cardFld, 1, 4);
		
		spine.add(new Text(""), 0, 5);
		
		Text orderTxt = new Text("Total cost of Order: ");
		orderTxt.setTextAlignment(TextAlignment.LEFT);
		TextField orderFld = new TextField();
		orderFld.setEditable(false);
		orderFld.setMinWidth(50);
		orderFld.setText("$" + order.getCost());
		spine.add(orderTxt, 0, 6);
		spine.add(orderFld, 1, 6);
		
		Text pizzaTxt = new Text("Total cost of Pizza: ");
		pizzaTxt.setTextAlignment(TextAlignment.LEFT);
		TextField pizzaFld = new TextField();
		pizzaFld.setEditable(false);
		pizzaFld.setMinWidth(50);
		pizzaFld.setText("$" + order.getPizza().getCost());
		spine.add(pizzaTxt, 0, 7);
		spine.add(pizzaFld, 1, 7);
		
		int indexer = 1;
		if(order.getPizza().hasToppings()){
			Text toppingTxt = new Text("Number of Toppings: ");
			toppingTxt.setTextAlignment(TextAlignment.LEFT);
			TextField toppingFld = new TextField();
			toppingFld.setEditable(false);
			toppingFld.setMinWidth(50);
			toppingFld.setText("" + order.getPizza().toppingCount());
			spine.add(toppingTxt, 0, 7 + indexer);
			spine.add(toppingFld, 1, 7 + indexer);
			indexer ++;
		}
		
		if(order.hasSide()){
			Text sideTxt = new Text("Total cost of Sides: ");
			sideTxt.setTextAlignment(TextAlignment.LEFT);
			TextField sideFld = new TextField();
			sideFld.setEditable(false);
			sideFld.setMinWidth(50);
			sideFld.setText("$" + order.getSidesCost());
			spine.add(sideTxt, 0, 7 + indexer);
			spine.add(sideFld, 1, 7 + indexer);
			
			Text sideTxt1 = new Text("Total amount of Sides: ");
			sideTxt1.setTextAlignment(TextAlignment.LEFT);
			TextField sideFld1 = new TextField();
			sideFld1.setEditable(false);
			sideFld1.setMinWidth(50);
			sideFld1.setText("" + order.getSides().length);
			spine.add(sideTxt1, 0, 8 + indexer);
			spine.add(sideFld1, 1, 8 + indexer);
		}
		
		spine.setAlignment(Pos.TOP_CENTER);
		setCenter(spine);
	}
	public Order getOrder(){
		return order;
	}
	public void setConfirmAction(EventHandler<ActionEvent> e){
		confirmBtn.setOnAction(e);
	}
	public void setCancelAction(EventHandler<ActionEvent> e){
		cancelBtn.setOnAction(e);
	}
	
}
