package PizzaProgram;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import PizzaProgram.Panes.ConfirmPurchasePane;
import PizzaProgram.Panes.GuestCreatePane;
import PizzaProgram.Panes.HomePane;
import PizzaProgram.Panes.InfoPane;
import PizzaProgram.Panes.LoginPane;
import PizzaProgram.Panes.OrderPane;
import PizzaProgram.Panes.ProfilePane;
import PizzaProgram.Panes.SignUpPane;
import PizzaProgram.Panes.InfoPane;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class PizzaProgram  extends Application{
	static ArrayList<CustomerAccount> customerList = new ArrayList<CustomerAccount>();
	
	private boolean isOrdering;	
	private boolean isLoggedIn;
	private int loggedIndex;
	
	private static Connection connection;
	
	public static void main(String[] args){
		try{
			try{
				Class.forName("com.mysql.jdbc.Driver");
				System.out.println("Driver loaded.");
				
				connection = DriverManager.getConnection("jdbc:mysql://localhost/PizzaData", "PizzaCook", "pizzaisgood");
				System.out.println("Database connected");
			}
			catch(SQLException sqle){
				
			}
		}
		catch(ClassNotFoundException cnfe){
			
		}
		
		fillLists();
		/*
		CustomerAccount ca1 = new CustomerAccount("test1","12345","8736-9754-0397-8563","Max Johnson", new Address("99 Oak Ave","Mill Valley",94941));
		ca1.addToHistory(new Order(ca1,new Pizza("Medium",22.49,new Topping[]{new Topping("Peperoni","Meat",1.5)}),new Side[]{new Side("NMNs","Snack",1)}));
		ca1.addToHistory(new Order(ca1,new Pizza("Small",18.49,new Topping[]{new Topping("Peperoni","Meat",1.5),new Topping("Sauce","Tomato",1)}),new Side[]{new Side("Caesar","Salad",1.5)}));
		
		CustomerAccount ca2 = new CustomerAccount("test2","12345","8365-0346-1283-7638","Avery Dority", new Address("125 Castle Rock Dr","Marin",94941));
		CustomerAccount ca3 = new CustomerAccount("test3","12345","9385-2134-9634-9537","Mason Rylander", new Address("300 West Ave","Marin",94941));
		
		customerList.add(ca1);
		customerList.add(ca2);
		customerList.add(ca3);*/
		
		Application.launch(args);
		
	}
	
	public void start(Stage primaryStage){
		
		isOrdering = false;
		
		isLoggedIn = false;
		loggedIndex = -1;
		
		HomePane home = new HomePane();
		
		SignUpPane sup = new SignUpPane();
		LoginPane lp = new LoginPane();
		
		OrderPane op = new OrderPane();
		ConfirmPurchasePane cpp = new ConfirmPurchasePane();
		InfoPane ip = new InfoPane();
		GuestCreatePane gcp = new GuestCreatePane();
		
		ProfilePane pp = new ProfilePane();
		
		Scene loginScene = new Scene(lp, 1000,500, Color.LIGHTSTEELBLUE);
		Scene signUpScene = new Scene(sup, 1000,500, Color.LIGHTSTEELBLUE);
		Scene homeScene = new Scene(home, 1000,500, Color.LIGHTBLUE);
		Scene orderScene = new Scene(op, 1000,500, Color.LIGHTSTEELBLUE);
		Scene confirmScene = new Scene(cpp,1000,500,Color.LIGHTSTEELBLUE);
		Scene infoScene = new Scene(ip,1000,500,Color.LIGHTSTEELBLUE);
		Scene guestCreateScene = new Scene(gcp,1000,500,Color.LIGHTSTEELBLUE);
		Scene profileScene = new Scene(pp,1000,500,Color.LIGHTSTEELBLUE);
		
		//Order pane actions
		op.setPurchaseBtn(e -> {
			if(isLoggedIn){
				op.setLogged(customerList.get(loggedIndex));
				cpp.displayOrder(op.getOrder());
				op.resetValues();
				primaryStage.setScene(confirmScene);
			}
			else{
				isOrdering = true;
				op.resetValues();
				primaryStage.setScene(infoScene);
			}
			
		});
		op.setCancelBtn(e -> {
			op.resetValues();
			primaryStage.setScene(homeScene);
		});
		op.setCancelBtn1(e -> {
			op.resetValues();
			primaryStage.setScene(homeScene);
		});
		
		//Home pane actions;
		home.setSignInAction(e -> {
			primaryStage.setScene(loginScene);
		});
		home.setSignUpAction(e -> {
			primaryStage.setScene(signUpScene);
			sup.moveToScene(0);
		});
		home.setOrderAction(e -> {
			primaryStage.setScene(orderScene); //
		});
		home.setLogOutAction(e -> {
			isLoggedIn = false;
			loggedIndex = -1;
			home.logOut();
		});
		home.setProfileAction(e -> {
			primaryStage.setScene(profileScene);
			pp.displayProfile(customerList.get(loggedIndex));
		});
		//Login pane actions.
		
		lp.setCancelAction(e -> {
			lp.resetValues();
			if(isOrdering){
				primaryStage.setScene(infoScene);
			}
			else{
				primaryStage.setScene(homeScene);
			}
		});
		lp.setLoginAction(e -> {
			int accountIndex = binarySearch(customerList, new CustomerAccount(lp.getUsernameFld().getText(),lp.getPasswordFld().getText()));
			System.out.println(customerList.size());
			//System.out.println(accountIndex);
			if(accountIndex >= 0){
				if(customerList.get(accountIndex).getUsername().equals(lp.getUsernameFld().getText()) && customerList.get(accountIndex).getPassword().equals(lp.getPasswordFld().getText())){
					lp.resetValues();
					isLoggedIn = true;
					loggedIndex = accountIndex;
					home.logIn(customerList.get(accountIndex));
					if(isOrdering){
						op.setLogged(customerList.get(accountIndex));
						cpp.displayOrder(op.getOrder());
						primaryStage.setScene(confirmScene);
					}
					else{
						primaryStage.setScene(homeScene);
					}
				}
				else{
					lp.displayReason();
				}
			}
			
		});
		//Sign up pane actions.
		sup.setCancelAction(e -> {
			sup.moveToScene(0);
			sup.resetValues();
			if(isOrdering){
				primaryStage.setScene(infoScene);
			}
			else{
				primaryStage.setScene(homeScene);
			}
		});
		sup.setNextAction(e -> {
			/*System.out.println(binarySearch(customerList, sup.getFirstPageDetails()));
			System.out.println(sup.getFirstPageDetails().toString());*/
			if(sup.accountParams1() && binarySearch(customerList, sup.getFirstPageDetails()) < 0){
				sup.moveToScene(sup.getStage()+1);
			}
			else {
				sup.setReason1(sup.getReason1());
			}
		});
		
		sup.setCancelAction1(e -> {
			sup.resetValues();
			sup.moveToScene(0);
			if(isOrdering){
				primaryStage.setScene(infoScene);
			}
			else{
				primaryStage.setScene(homeScene);
			}
		});
		sup.setNextAction1(e -> {
			//System.out.println((sup.accountParams2() && binarySearch(customerList, sup.getCustomerAccount()) < 0) + " - " + binarySearch(customerList, sup.getCustomerAccount()));
			if(sup.accountParams2() && binarySearch(customerList, sup.getCustomerAccount()) < 0){
				customerList = insert(customerList,sup.getCustomerAccount());
				isLoggedIn = true;
				loggedIndex = binarySearch(customerList, sup.getCustomerAccount());
				home.logIn(customerList.get(loggedIndex));
				
				try{
					
						
					
					//Statement statement = connection.createStatement();
					String query = "insert into Users (username,password,name,zipCode,street,city,cardNum) values (?, ?, ?, ?, ?, ?, ?);";
					/*values ('" + 
					sup.getCustomerAccount().getUsername() + "','" + sup.getCustomerAccount().getPassword() + "','"
					+ sup.getCustomerAccount().getName()+ "," + sup.getCustomerAccount().getAdress().getZip() + "','"
					+ sup.getCustomerAccount().getAdress().getStreetAddress() + "','" + sup.getCustomerAccount().getAdress().getCity()
					+ "','" + sup.getCustomerAccount().getCardNumber() + "');";*/
					
					PreparedStatement preStatem = connection.prepareStatement(query);
					preStatem.setString(1, sup.getCustomerAccount().getUsername());
					preStatem.setString(2, sup.getCustomerAccount().getPassword());
					preStatem.setString(3, sup.getCustomerAccount().getName());
					preStatem.setString(4, Integer.toString(sup.getCustomerAccount().getAdress().getZip()));
					preStatem.setString(5, sup.getCustomerAccount().getAdress().getStreetAddress());
					preStatem.setString(6, sup.getCustomerAccount().getAdress().getCity());
					preStatem.setString(7, sup.getCustomerAccount().getCardNumber());
					
					preStatem.execute();
					//connection.close();
					
				}
				catch(SQLException sqle){
					sqle.printStackTrace();
					//System.out.println("SQL Exception");
				}
				
				if(isOrdering){	
					op.setLogged(customerList.get(loggedIndex));
					cpp.displayOrder(op.getOrder());
					primaryStage.setScene(confirmScene);
				}
				else{
					primaryStage.setScene(homeScene);
				}
				sup.resetValues();
			}
			else {
				sup.setReason2(sup.getReason2());
			}
		});
		sup.setBackAction1(e -> {
			sup.moveToScene(sup.getStage()-1);
		});
		
		cpp.setCancelAction(e -> {
			primaryStage.setScene(homeScene);
		});
		//TODO: set confirm action
		cpp.setConfirmAction(e -> {
			primaryStage.setScene(homeScene);
			if(isLoggedIn){
				String query = "insert into History (username,pizzaSize,pizzaCost,toppingCount,toppingCost,sideCount,sideCost) values (?, ?, ?, ?, ?, ?, ?);";
				
				try {
					PreparedStatement preStatem = connection.prepareStatement(query);
					preStatem.setString(1, customerList.get(loggedIndex).getUsername());
					preStatem.setString(2, op.getPizza().getSize());
					preStatem.setString(3, "$"+op.getPizza().getCost());
					preStatem.setString(4, Integer.toString(op.getToppingCount()));
					preStatem.setString(5, "$"+op.getOrder().getPizza().toppingCost());
					preStatem.setString(6, Integer.toString(op.getSideCount()));
					preStatem.setString(7, "$"+op.getOrder().getSidesCost());
					
					preStatem.execute();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				customerList.get(loggedIndex).addToHistory(cpp.getOrder());
			}
		});
		//TODO: set info pane actions
		ip.setLoginAction(e -> {
			primaryStage.setScene(loginScene);
		});
		ip.setSignUpAction(e -> {
			primaryStage.setScene(signUpScene);
		});
		ip.setCancelAction(e -> {
			op.resetValues();
			isOrdering = false;
			primaryStage.setScene(homeScene);
		});
		ip.setContinueAction(e -> {
			primaryStage.setScene(guestCreateScene);
		});
		gcp.setContinueAction(e -> {
			if(gcp.hasFilledFields()){
				primaryStage.setScene(confirmScene);
				if(op.getSide().size() > 0){
					cpp.displayOrder(new Order(gcp.getAccount(),op.getPizza(),(Side[])op.getSide().toArray()));
				}
				else{
					cpp.displayOrder(new Order(gcp.getAccount(),op.getPizza()));
				}
			}
			else{
				gcp.setReason(gcp.getReason());
			}
		});
		gcp.setBackAction(e -> {
			gcp.resetValues();
			primaryStage.setScene(infoScene);
		});
		pp.setHomeExitAction(e -> {
			primaryStage.setScene(homeScene);
		});
		pp.setChangePasswordAction(e -> {
			//UPDATE query
			if(pp.checkMatch()){
				//update Course set numOfCredits = 4 where title = 'Database Systems';
				try {
					Statement statement = connection.createStatement();
					statement.execute("update Users set password = '" + pp.newPassword() + "' where username = '" + customerList.get(loggedIndex).getUsername() +"';");
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				customerList.get(loggedIndex).setPassword(pp.newPassword());
			}
		});
		pp.setDeleteButton(e -> {
			
			try {
				Statement statement = connection.createStatement();
				statement.execute("delete from Users where username = '" + customerList.get(loggedIndex).getUsername() +"';");
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			customerList.remove(loggedIndex);
			isLoggedIn = false;
			loggedIndex = -1;
			home.logOut();
			primaryStage.setScene(homeScene);
			
		});
		
		primaryStage.setResizable(false);
		primaryStage.setScene(homeScene);
		primaryStage.setTitle("Max's Pizzaroni");
		primaryStage.show();
	}
	public static <E extends Comparable<E>> int binarySearch(ArrayList<E> list, E key){
		int searchIndex = (int)(list.size() /2);
		int searchMax = list.size()-1;
		int searchMin = 0;
		//int searchRange = list.length;
		boolean searching;
		
		if(list.size() == 0){
			searching = false;
		}
		else {
			searching = true;
		}
			
		
		while(searching){
			//searchRange = searchMax - searchMin + 1;
			if(list.get(searchIndex).compareTo(key)==0){
				return searchIndex;
			}
			else if (list.get(searchIndex).compareTo(key)<0 && searchIndex < searchMax){
				searchMin = searchIndex + 1;
				searchIndex = (int)((searchMax + searchMin) / 2);
			}
			else if (list.get(searchIndex).compareTo(key)>0 && searchIndex > searchMin){
				searchMax = searchIndex - 1;
				searchIndex = (int)((searchMax + searchMin) / 2);
			}
			else{
				searching = false;
			}
			
		}
		
		return (searchIndex + 1) * -1;
	}
	public static <E extends Comparable<E>> ArrayList<E> insert(ArrayList<E> list, E key){
		boolean placed = false;
		for(int i = 0; i < list.size(); i++){			
			if(list.get(i).compareTo(key) >= 0){
				if(!placed){
					list.add(i, key);
					i = list.size() + 1;
					placed = true;
				}
			}

		}
		if(list.size() == 0 || !placed){
			list.add(key);
		}
		return list;
	}
	public static void fillLists(){
		try{
			
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("select username, password, name, zipCode, street, city, cardNum from Users where username is not null group by username;");
			
			while(resultSet.next()){
				customerList.add(new CustomerAccount(resultSet.getString(1),resultSet.getString(2),resultSet.getString(7),resultSet.getString(3), new Address(resultSet.getString(5),resultSet.getString(6),Integer.parseInt(resultSet.getString(4)))));
			}
			
			resultSet = statement.executeQuery("select username, pizzaCost, pizzaSize, toppingCost, toppingCount, sideCost, sideCount from History where username is not null group by username;");
			
			while(resultSet.next()){
				customerList.get(binarySearch(customerList, new CustomerAccount(resultSet.getString(1),"123"))).addToHistory(new Order(new String[]{resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),resultSet.getString(5),resultSet.getString(6),resultSet.getString(7)}));;
			}
			
			//connection.close();
			
			
		}
		
		catch(SQLException sqle){
			sqle.printStackTrace();
			//System.out.println("SQL Exception");
		}
		
	}
	/*
	public static String[] runQuery(String query){
		try{
			try{
				
				Class.forName("com.mysql.jdbc.Driver");
				System.out.println("Driver loaded.");
				
				Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/javabook", "scott", "tiger");
				System.out.println("Database connected");
				
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery(query);
				
				String[] toReturn;
				int i = 0;
				while(resultSet.next()){
					toReturn[i] = resultSet.get;
				}
			}
			catch(ClassNotFoundException cnfe){
				
			}
		}
		catch(SQLException sqle){
			
		}
		return new String[] {""};
	}*/
}



