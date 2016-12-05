package se.humanus.example.app;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.event.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.application.*;
import javafx.fxml.FXML;

import org.apache.commons.dbcp.BasicDataSource;
import org.flywaydb.core.Flyway;

public class Main{
	
/*	private Stage primaryStage;
	private BorderPane mainLayout;
	
	public void start(Stage primaryStage) throws IOException{
		
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("employee app");
		showMainView();
	}

	private void showMainView() throws IOException{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("src/main/resources/JavafxUI/Employee.fxml"));
		mainLayout =loader.load();
		Scene scene = new Scene(mainLayout);
		primaryStage.setScene(scene);
		primaryStage.show();
		
	} */
	
	public static void main(final String[] args) throws SQLException {
		

		//Launch(args);
		
		BasicDataSource ds = new BasicDataSource();
		ds.setDriverClassName("org.h2.Driver");
		ds.setUrl("jdbc:h2:~/HumanusTest");
		ds.setUsername("sa");
		ds.setPassword("");

		try {

			Flyway flyway = new Flyway();
			flyway.setDataSource(ds);
			//flyway.repair();
			flyway.migrate();
			

			try (Connection connection = ds.getConnection(); Statement stmt = connection.createStatement()) {

				stmt.executeUpdate("INSERT INTO employee (name, age, contacts, salary, username, password) VALUES ('Martin Blackwood','31', 'Martin@testing.com','65346.43', 'mabl', '31')");
				
				System.out.println("Employee");
				try (ResultSet rs = stmt.executeQuery("SELECT * FROM employee")){
					while(rs.next()){
						System.out.printf(" >> Employee ID:%d | Employee name:%s | Employee age:%d | Employee contact:(%s) | Employee salary:%f | Employee username:%s | Employess password:%s %n",rs.getInt("id"), rs.getString("name"), rs.getInt("age"), rs.getString("contacts"), rs.getDouble("salary"), rs.getString("username"), rs.getString("password") );
					}
				}

			}

		} finally {
			ds.close();
		}
	}

/*	private static void Launch(String[] args) {
		// TODO Auto-generated method stub
		
	}*/

	
	

}