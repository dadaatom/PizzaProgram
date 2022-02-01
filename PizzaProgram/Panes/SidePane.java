package PizzaProgram.Panes;

import PizzaProgram.Side;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class SidePane extends HBox{
int ida;
	
	Button removeBtn;
	TextField sideCost;
	
	ComboBox<String> comboType;
	ComboBox<String> saladBox;
	ComboBox<String> snackBox;
	ComboBox<String> sodaBox;
	
	public SidePane(int ida){
		this.ida = ida;
		
		saladBox = new ComboBox<>();
		saladBox.getItems().addAll("Caesar","Greek","Pasta","Coleslaw");
		saladBox.setMinWidth(200);
		saladBox.getSelectionModel().selectFirst();

		
		snackBox = new ComboBox<>();
		snackBox.getItems().addAll("Cookie","NMN's","Chocolate","Fun Chips");
		snackBox.setMinWidth(200);
		snackBox.getSelectionModel().selectFirst();

		
		sodaBox = new ComboBox<>();
		sodaBox.getItems().addAll("Dr Salt","Mountain Tam","Spite","W&A");
		sodaBox.setMinWidth(200);
		sodaBox.getSelectionModel().selectFirst();
		
		comboType = new ComboBox<>();
		comboType.getItems().addAll("Salad","Snack","Soda");
		comboType.getSelectionModel().selectFirst();
		
		Text sideText = new Text("Side: ");
		Text costText = new Text("Cost: ");
		
		sideCost = new TextField();
		sideCost.setEditable(false);
		sideCost.setMaxWidth(50);
		
		removeBtn = new Button("Remove");
		
		getChildren().addAll(sideText,comboType,saladBox,new Text(" - "),costText,sideCost,new Text(" - "),removeBtn);
		setSpacing(5);
		setAlignment(Pos.CENTER);

	}
	public void settupPane(){
		if(comboType.getValue().equals("Salad")){
			getChildren().remove(2);
			getChildren().add(2, saladBox);
		}
		else if(comboType.getValue().equals("Snack")){
			getChildren().remove(2);
			getChildren().add(2, snackBox);
		}
		else if(comboType.getValue().equals("Soda")){
			getChildren().remove(2);
			getChildren().add(2, sodaBox);
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
	public ComboBox<String> getSaladBox(){
		return saladBox;
	}
	
	/*public void setVegeAction(EventHandler<ActionEvent> e){
		vegetableBox.setOnAction(e);
	}*/
	public ComboBox<String> getSnackBox(){
		return snackBox;
	}
	
	/*public void setSauceAction(EventHandler<ActionEvent> e){
		sauceBox.setOnAction(e);
	}*/
	public ComboBox<String> getSodaBox(){
		return sodaBox;
	}
	
	public double getPrice(){
		return Double.parseDouble(sideCost.getText().substring(1));
	}
	public void calculatePrice(){
		double newPrice = 1.5 - (comboType.getSelectionModel().getSelectedIndex()*.5);
		newPrice = ((double)((int)(newPrice * 100))) / 100;
		
		sideCost.setText("$" + newPrice);
	}
	
	public void setRemoveAction(EventHandler<ActionEvent> e){
		removeBtn.setOnAction(e);
	}
	public int getIda(){
		return ida;
	}
	public Side getSide(){
		if(comboType.getValue().equals("Salad")){
			return new Side(saladBox.getValue(),comboType.getValue(),getPrice());
		}
		else if(comboType.getValue().equals("Snack")){
			return new Side(snackBox.getValue(),comboType.getValue(),getPrice());
		}
		else{
			return new Side(sodaBox.getValue(),comboType.getValue(),getPrice());
		}	
	}
}
