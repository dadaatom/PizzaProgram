package PizzaProgram.Panes;

import java.util.ArrayList;

import PizzaProgram.Account;
import PizzaProgram.Order;
import PizzaProgram.Pizza;
import PizzaProgram.Side;
import PizzaProgram.Topping;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class OrderPane extends BorderPane {
	double sizePrice;
	
	private ArrayList<ToppingPane> tpList = new ArrayList<ToppingPane>();
	private ArrayList<SidePane> sList = new ArrayList<SidePane>();
	
	private ComboBox<String> pizzaSize;
	
	private Button purchaseBtn;
	private Button continueBtn;
	private Button backBtn;
	private Button cancelBtn;
	private Button cancelBtn1;
	
	private Button addSide;
	private Button addBtn;
	
	private VBox svp;
	private VBox gvp;
	private HBox hb;
	private Text qTxt;
	
	private HBox bottomBar;
	
	private HBox pizzaPriceHB;
	private TextField pizzaPriceFld;
	
	private HBox sidePriceHB;
	private TextField sidePriceFld;
	
	private int currentId;
	private int toppingCount;
	private int sideCount;
	
	private Account logged;
	
	public OrderPane(){
		logged = new Account();
		
		sizePrice = 0;
		toppingCount = 0;
		currentId = 0;
		
		gvp = new VBox();
		
		Text pizzaSizeTxt = new Text("Size of Pizza: ");
		
		/*
		VBox vb = new VBox();
		Button incBtn = new Button("↑");
		Button decBtn = new Button("↓");
		
		incBtn.setOnAction(e -> {
			if((int)Integer.parseInt(pizzaSizeFld.getText()) < 32){

				pizzaSizeFld.setText(Integer.toString((int)Integer.parseInt(pizzaSizeFld.getText())+1));
			}
		});
		decBtn.setOnAction(e -> {
			if((int)Integer.parseInt(pizzaSizeFld.getText()) > 8){

				pizzaSizeFld.setText(Integer.toString((int)Integer.parseInt(pizzaSizeFld.getText())-1));
			}
		});
		
		incBtn.setFont(Font.font(7));
		decBtn.setFont(Font.font(7));
		incBtn.setMaxHeight(7);
		decBtn.setMaxHeight(7);
		incBtn.setMaxWidth(10);
		decBtn.setMaxWidth(10);
		
		vb.getChildren().add(0, incBtn);
		vb.getChildren().add(1, decBtn);
		*/
		
		bottomBar = new HBox();
		continueBtn = new Button("Continue");
		cancelBtn = new Button("Cancel");
		
		
		bottomBar.getChildren().add(0,continueBtn);
		bottomBar.getChildren().add(1,cancelBtn);
		
		bottomBar.setSpacing(5);
		bottomBar.setAlignment(Pos.CENTER);
		setBottom(bottomBar);
		
		pizzaPriceHB = new HBox();
		Text pizzaPriceTxt = new Text("Total cost of Pizza: ");
		pizzaPriceFld = new TextField();
		pizzaPriceFld.setMaxWidth(100);
		pizzaPriceFld.setEditable(false);
		
		pizzaPriceHB.getChildren().addAll(pizzaPriceTxt, pizzaPriceFld);
		
		pizzaSize = new ComboBox<String>();
		pizzaSize.getItems().addAll("Small","Medium","Large","Extra Large");
		pizzaSize.getSelectionModel().selectFirst();
		pizzaSize.setOnAction(e -> {
			recalculatePrice();
		});
		recalculatePrice();
		
		hb = new HBox();
		hb.getChildren().addAll(pizzaSizeTxt, pizzaSize);
		
		pizzaPriceHB.setAlignment(Pos.CENTER);
		hb.setAlignment(Pos.CENTER);
		
		addBtn = new Button("Add Topping");
		
		/*ToppingPane tp = new ToppingPane(0);
		
		tpList.add(tp);*/
		
		gvp.setSpacing(5);
		
		gvp.getChildren().addAll(new Text(""),pizzaPriceHB, hb, new Text(""), addBtn);
		
		gvp.setAlignment(Pos.TOP_CENTER);
		setCenter(gvp);
		
		addBtn.setOnAction(e -> {
			if(toppingCount < 10){
				toppingCount ++;
				addToList();
				recalculateVB();
			}
		});
		
		svp = new VBox();
		HBox secondBottomBar = new HBox();
		
		backBtn = new Button("Back");
		purchaseBtn = new Button("Purchase");
		cancelBtn1 = new Button("Cancel");
		
		secondBottomBar.getChildren().addAll(purchaseBtn,backBtn,cancelBtn1);
		secondBottomBar.setAlignment(Pos.CENTER);
		secondBottomBar.setSpacing(5);
		
		
		sidePriceHB = new HBox();
		Text sidePriceTxt = new Text("Total cost of Sides: ");
		sidePriceFld = new TextField();
		sidePriceFld.setMaxWidth(100);
		sidePriceFld.setEditable(false);
		
		sidePriceHB.getChildren().addAll(sidePriceTxt, sidePriceFld);
		sidePriceHB.setAlignment(Pos.CENTER);
		
		addSide = new Button("Add Side");
		
		qTxt = new Text("Would you like a side?");
		qTxt.setFont(Font.font(20));
		
		svp.getChildren().addAll(new Text(""),qTxt, new Text(""),sidePriceHB,new Text(""),addSide);
		svp.setSpacing(5);
		svp.setAlignment(Pos.TOP_CENTER);
		
		addSide.setOnAction(e -> {
			if(sideCount < 10){
				sideCount++;
				addSideToList();
				recalculateSVB();
			}
		});
		
		continueBtn.setOnAction(e -> {
			setCenter(svp);
			recalculateSPrice();
			setBottom(secondBottomBar);
		});
		backBtn.setOnAction(e -> {
			setCenter(gvp);
			setBottom(bottomBar);
		});
	}
	public void addToList(){
		ToppingPane newtp = new ToppingPane(currentId);
		currentId++;
		tpList.add(newtp);
		newtp.calculatePrice();
		recalculatePrice();
		newtp.setComboTypeAction(e -> {
			newtp.calculatePrice();
			newtp.settupPane();
			recalculatePrice();
		});
		newtp.setRemoveAction(e -> {
			int index = 0;
			
			for(int i = 0; i < tpList.size(); i++){
				if(tpList.get(i).getIda() == newtp.getIda()){
					index = i;
					i = tpList.size();
				}
			}
			toppingCount --;
			tpList.remove(index);
			recalculatePrice();
			recalculateVB();
		});
	}
	public ArrayList<ToppingPane> getToppings(){
		return tpList;
	}
	public void removeTopping(int index){
		tpList.remove(tpList.get(index));
	}
	public void recalculatePrice(){
		double totalCost = 0;
		totalCost += 14.99 + (pizzaSize.getSelectionModel().getSelectedIndex() * 3);
		for(int i  = 0; i < tpList.size(); i++){
			totalCost += tpList.get(i).getPrice();
		}
		pizzaPriceFld.setText("$" + (((double)((int)(totalCost * 100)))/100));
	}
	public void recalculateVB(){
		gvp.getChildren().clear();
		gvp.getChildren().addAll(new Text(""),pizzaPriceHB,hb,new Text(""));
		for(int i = 0; i < tpList.size(); i++){
			gvp.getChildren().add(tpList.get(i));
		}
		if(toppingCount < 10){
			gvp.getChildren().add(addBtn);
		}
	}
	
	
	
	
	public void addSideToList(){
		SidePane newS = new SidePane(currentId);
		currentId++;
		sList.add(newS);
		newS.calculatePrice();
		recalculateSPrice();
		newS.setComboTypeAction(e -> {
			newS.calculatePrice();
			newS.settupPane();
			recalculateSPrice();
		});
		newS.setRemoveAction(e -> {
			int index = 0;
			
			for(int i = 0; i < sList.size(); i++){
				if(sList.get(i).getIda() == newS.getIda()){
					index = i;
					i = sList.size();
				}
			}
			sideCount --;
			sList.remove(index);
			recalculateSPrice();
			recalculateSVB();
		});
	}
	public ArrayList<SidePane> getSide(){
		return sList;
	}
	public void removeSide(int index){
		sList.remove(sList.get(index));
	}
	public void recalculateSPrice(){
		double totalCost = 0;
		for(int i  = 0; i < sList.size(); i++){
			totalCost += sList.get(i).getPrice();
		}
		sidePriceFld.setText("$" + (((double)((int)(totalCost * 100)))/100));
	}
	public void recalculateSVB(){
		svp.getChildren().clear();
		svp.getChildren().addAll(new Text(""),qTxt, new Text(""),sidePriceHB,new Text(""));
		for(int i = 0; i < sList.size(); i++){
			svp.getChildren().add(sList.get(i));
		}
		if(sideCount < 10){
			svp.getChildren().add(addSide);
		}
	}
	
	
	
	
	/*
	public TextField getPizzaSizeFld(){
		return pizzaSizeFld;
	}
	*/
	
	public void setCancelBtn(EventHandler<ActionEvent> e){
		cancelBtn.setOnAction(e);
	}
	public void setCancelBtn1(EventHandler<ActionEvent> e){
		cancelBtn1.setOnAction(e);
	}
	public void setPurchaseBtn(EventHandler<ActionEvent> e){
		purchaseBtn.setOnAction(e);
	}
	public void setAddBtn(EventHandler<ActionEvent> e){
		addBtn.setOnAction(e);
	}
	public int getSideCount(){
		return sideCount;
	}
	public int getToppingCount(){
		return toppingCount;
	}
	public double getPizzaPrice(){
		return Double.parseDouble(pizzaPriceFld.getText().substring(1));
	}
	
	private Topping[] getToppingsArray(){
		Topping[] toReturn = new Topping[tpList.size()];
		for(int  i = 0 ; i < tpList.size(); i++){
			toReturn[i] = tpList.get(i).getTopping();
		}
		return toReturn;
	}
	public Side[] getSides(){
		Side[] toReturn = new Side[sList.size()];
		for(int i = 0; i < toReturn.length; i++){
			toReturn[i] = sList.get(i).getSide();
		}
		return toReturn;
	}
	
	public Pizza getPizza(){
		if(tpList.size() > 0){
			return new Pizza(pizzaSize.getValue(),getPizzaPrice(),getToppingsArray());
		}
		else{
			return new Pizza(pizzaSize.getValue(),getPizzaPrice());
		}
	}
	public Order getOrder(){
		if(sList.size() > 0){
			return new Order(logged,getPizza(),getSides());
		}
		else{
			return new Order(logged,getPizza());
		}
	}
	public void resetValues(){
		tpList.clear();
		sList.clear();
		pizzaSize.getSelectionModel().selectFirst();
		recalculatePrice();
		recalculateSVB();
		recalculateVB();
		setCenter(gvp);
		setBottom(bottomBar);
	}
	public void setLogged(Account account){
		logged = account;
	}
}
