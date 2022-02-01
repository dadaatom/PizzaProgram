package PizzaProgram.Panes;

import PizzaProgram.Order;
import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class OrderHistoryPane extends GridPane{
	public OrderHistoryPane(){}
	public void displayOrder(Order order){
		this.setAlignment(Pos.CENTER);
		String[] list = order.getList();
		
		TextField costFld = new TextField("$" + list[0]);
		costFld.setEditable(false);
		this.add(new Text("Cost of Order:"), 3, 0);
		this.add(costFld, 4, 0);
		TextField sizeFld = new TextField(list[1]);
		sizeFld.setEditable(false);
		this.add(new Text(" "), 2, 0);
		this.add(new Text("Size of Pizza:"), 0, 0);
		this.add(sizeFld, 1, 0);
		
		TextField toppingCountFld = new TextField(list[3]);
		toppingCountFld.setEditable(false);
		this.add(new Text("Amount of Toppings:"), 0, 1);
		this.add(toppingCountFld, 1, 1);
		TextField topCostFld = new TextField("$" + list[2]);
		topCostFld.setEditable(false);
		this.add(new Text(" "), 2, 1);
		this.add(new Text("Cost of Toppings:"), 3, 1);
		this.add(topCostFld, 4, 1);
		
		TextField sideCountFld = new TextField(list[4]);
		sideCountFld.setEditable(false);
		this.add(new Text("Amount of Sides:"), 0, 2);
		this.add(sideCountFld, 1, 2);
		TextField sideCostFld = new TextField("$" + list[5]);
		sideCostFld.setEditable(false);
		this.add(new Text(" "), 2, 2);
		this.add(new Text("Cost of Sides:"), 3, 2);
		this.add(sideCostFld, 4, 2);
		
		this.setVgap(5);
	}
}
