/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finaltest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author steam
 */
public class FinalTest extends Application {
    
    // JDBC driver Name And Database URL
    static final String DRIVER = "com.mysql.jdbc.Driver";
    static final String DATABASE_URL = "jdbc:mysql://URL/DATABASENAME";
    static final String USER = "admin";
    static final String PASS = "";
    
    String cStudent="";
    String cPass="";
    
    Text message = new Text("");
    
    
    //@Override
    public void start(Stage primaryStage) {
   
    ImageView Banner = new ImageView(new Image("images/Banner.png"));
    ImageView Logo = new ImageView(new Image("images/logo.png"));
    // Login Area
       // Text Area
       Text userT = new Text ("UserName: ");
         userT.setFont(Font.font("Tahoma", FontWeight.BOLD, 19));
         userT.setId("fancytext");
       Text passT = new Text ("Password:  ");
         passT.setFont(Font.font("Tahoma", FontWeight.BOLD, 19));
         passT.setId("fancytext");
         
       // Field Area
       TextField userF = new TextField();
       PasswordField passF = new PasswordField();
       
       // Buttons
       
       Button loginB = new Button("Login");
       
       // Boxes Area
       HBox userHB = new HBox(userT,userF);
       userHB.setSpacing(10);
       userHB.setAlignment(Pos.CENTER);
       
       HBox passHB = new HBox(passT,passF);
       passHB.setSpacing(10);
       passHB.setAlignment(Pos.CENTER);
       
       VBox loginVB = new VBox(userHB,passHB,loginB,message);
       loginVB.setSpacing(10);
       loginVB.setAlignment(Pos.CENTER);
       
    // Last Pane
    BorderPane root = new BorderPane();
    HBox bannerB = new HBox(Banner);
    bannerB.setAlignment(Pos.CENTER);
    root.getStylesheets().add("Style/Style.css");
    root.setTop(bannerB);
    root.setCenter(loginVB);

        
        Scene scene = new Scene(root);
        
        
           // Button Action Event
       loginB.setOnAction(e -> {
           
       try{
         ResultSet set = getTable();

       while(set.next()){
                        if(userF.getText().equals(set.getString("userN"))){
                            cStudent = set.getString("userN");
                           if (passF.getText().equals(set.getString("pass"))){
                               message.setText("Succed");
                               message.setFont(Font.font("Tahoma", FontWeight.BOLD, 19));
                               userT.setId("fancytext4");
                               break;
                           }
                               message.setText("Password doesnt match Our Records");
                               message.setFont(Font.font("Tahoma", FontWeight.BOLD, 19));
                               userT.setId("fancytext3");
                           
                        }
                        
                                  }
            }catch (SQLException exp) {System.out.println();}
           
       
       
       
       
       });
        
        
        
        // Stage Declaration
        primaryStage.getIcons().add(new Image("images/logo.png"));
        primaryStage.setTitle("TE Admission Quiz");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    
    ResultSet getTable(){
        
        // Get UserInformation
   try{
   Connection connection = getConnection();
   Statement getTable = connection.createStatement(); //get statement reference
   ResultSet dataUser = getTable.executeQuery("Select * from student;");
   return dataUser;
   }catch(Exception e){System.out.println("Fail accessing DB!");
   return null;}
    }

Connection getConnection() throws Exception {
    try {
    Class.forName(DRIVER);
    Connection connection = DriverManager.getConnection(DATABASE_URL,USER , PASS);
    return connection;
    }catch(Exception e) {System.out.println("Error connecting to Databse! "+e);}
    return null;

    }

    
}


/*
       Text info = new Text ("              TE University             \n" +
                              "  \"Were your dream Start to grow     \n" +
                              "And your Knowledge start to develop\".\n");
        info.setFont(Font.font("Tahoma", FontWeight.BOLD, 19));
        info.setId("fancytext");
    
*/