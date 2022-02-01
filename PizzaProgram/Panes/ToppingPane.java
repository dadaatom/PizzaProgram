package PizzaProgram.Panes;

import PizzaProgram.Topping;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class ToppingPane extends HBox{
	int ida;
	
	Button removeBtn;
	TextField toppingCost;
	
	ComboBox<String> comboType;
	ComboBox<String> meatBox;
	ComboBox<String> vegetableBox;
	ComboBox<String> sauceBox;
	
	public ToppingPane(int ida){
		this.ida = ida;
		
		meatBox = new ComboBox<>();
		meatBox.getItems().addAll("Pepperoni","Sausage","Chicken","Beef");
		meatBox.setMinWidth(200);
		meatBox.getSelectionModel().selectFirst();

		
		vegetableBox = new ComboBox<>();
		vegetableBox.getItems().addAll("Spinach","Green Peppers","Red Peppers","Tomatoes","Olives","Onions");
		vegetableBox.setMinWidth(200);
		vegetableBox.getSelectionModel().selectFirst();

		
		sauceBox = new ComboBox<>();
		sauceBox.getItems().addAll("Tomato Sauce","Premium Tomato Sauce","BBQ Sauce","Szechuan Sauce");
		sauceBox.setMinWidth(200);
		sauceBox.getSelectionModel().selectFirst();
		
		comboType = new ComboBox<>();
		comboType.getItems().addAll("Meat","Vegetable","Sauce");
		comboType.getSelectionModel().selectFirst();
		
		Text toppingText = new Text("Topping: ");
		Text costText = new Text("Cost: ");
		
		toppingCost = new TextField();
		toppingCost.setEditable(false);
		toppingCost.setMaxWidth(50);
		
		removeBtn = new Button("Remove");
		
		getChildren().addAll(toppingText,comboType,meatBox,new Text(" - "),costText,toppingCost,new Text(" - "),removeBtn);
		setSpacing(5);
		setAlignment(Pos.CENTER);

	}
	public void settupPane(){
		if(comboType.getValue().equals("Meat")){
			getChildren().remove(2);
			getChildren().add(2, meatBox);
		}
		else if(comboType.getValue().equals("Vegetable")){
			getChildren().remove(2);
			getChildren().add(2, vegetableBox);
		}
		else if(comboType.getValue().equals("Sauce")){
			getChildren().remove(2);
			getChildren().add(2, sauceBox);
		}
	}
	
	public void setComboTypeAction(EventHandler<ActionEvent> e){
		comboType.setOnAction(e);
	}
	/*public void setFoodActions(EventHandler<ActionEvent> e){
		meatBox.setOnAction(e);
		vegetableBox.setOnAction(e);
		sauceBox.setOnAction(e);
	}*/
	
	/*public void setMeatAction(EventHandler<ActionEvent> e){
		meatBox.setOnAction(e);
	}*/
	public ComboBox<String> getMeatBox(){
		return meatBox;
	}
	
	/*public void setVegeAction(EventHandler<ActionEvent> e){
		vegetableBox.setOnAction(e);
	}*/
	public ComboBox<String> getVegetableBox(){
		return vegetableBox;
	}
	
	/*public void setSauceAction(EventHandler<ActionEvent> e){
		sauceBox.setOnAction(e);
	}*/
	public ComboBox<String> getSauceBox(){
		return sauceBox;
	}
	
	public double getPrice(){
		return Double.parseDouble(toppingCost.getText().substring(1));
	}
	public void calculatePrice(){
		double newPrice = 1.5 - (comboType.getSelectionModel().getSelectedIndex()*.5);
		newPrice = ((double)((int)(newPrice * 100))) / 100;
		
		toppingCost.setText("$" + newPrice);
	}
	
	public void setRemoveAction(EventHandler<ActionEvent> e){
		removeBtn.setOnAction(e);
	}
	public int getIda(){
		return ida;
	}
	public Topping getTopping(){
		if(comboType.getValue().equals("Meat")){
			return new Topping(meatBox.getValue(),comboType.getValue(),getPrice());
		}
		else if(comboType.getValue().equals("Vegetable")){
			return new Topping(vegetableBox.getValue(),comboType.getValue(),getPrice());
		}
		else{
			return new Topping(sauceBox.getValue(),comboType.getValue(),getPrice());
		}		
	}
}
