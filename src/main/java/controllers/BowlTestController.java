
package main.java.controllers;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import main.java.Database;
import main.java.LuckyLanes;
import main.java.scene.control.TextFieldRequired;

/**
 * FXML Controller class
 *
 * This is the controller for the creation of an athlete. It gives the user
 * the option to choose between a list of specific athletes.
 * 
 * @author Mario
 */
public class BowlTestController implements Initializable
{
    private Stage stage;                //The window.
    
    private Scene preScene;             //The previous screens scene while using the back button.
    private Scene nextScene;            //The to be next scene.
    private double preMinHeight;        //The previous minimum screens height.
    private double preMinWidth;         //The previous minimum screens width.
    private Scene homeScene;
    private ObservableList<ObservableList> data;
    String id;
    private String preTitle;            //The previous screens title.
    protected final String title = "Add Question";       //The current stages title.
    private ResultSet rs;
    double score = 0;
    double testTotal = 1;
    @FXML
    Label mainQuestion;
    @FXML
    Label option1;
    @FXML
    Label option2;
    @FXML
    Label option3;
    @FXML
    Label option4;
    @FXML
    CheckBox option1Value;
    @FXML
    CheckBox option2Value;
    @FXML
    CheckBox option3Value;
    @FXML
    CheckBox option4Value;
    @FXML
    Button submitQuestion;
    @FXML
    Button nextQuestion;
    @FXML
    Label currentScore;
    private Executor exec;
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        /*exec = Executors.newCachedThreadPool(runnable ->
        {
            Thread t = new Thread(runnable);
            t.setDaemon(true);
            return t;
        });*/
        createTest();
        //next();
    }
    
    /**
     * Puts things in the current window. Changes the scene to the current one.
     * @param stage The window.
     */
    public void setId(String id){
        this.id = id;
    }
public void updateScore(String id){
    
   // String SQL = "SELECT IQ FROM IQPSYCH;";
    System.out.println("id = "+id);
    String SQL = "SELECT * FROM IQPSYCH WHERE ID="+id+";";
    ResultSet turkey = Database.searchQuery(SQL);
        try {
          /*       
        try{
            while(turkey.next()){
                try {
                    
                    for(int i = 1; i <= turkey.getMetaData().getColumnCount(); i++){
                        System.out.println(turkey.getString(i));
                        
                    }   }catch (SQLException ex) {
                        Logger.getLogger(BowlTestController.class.getName()).log(Level.SEVERE, null, ex);
                    }
            }
            }catch(SQLException ex) {
            Logger.getLogger(BowlTestController.class.getName()).log(Level.SEVERE, null, ex);
        }*/
    
            if(turkey != null){
            if(turkey.first()){
            currentScore.setText(Double.toString(turkey.getDouble(2)));
            }else{
                System.out.println("User has not taken test yet");
            }}
        } catch (SQLException ex) {
            Logger.getLogger(BowlTestController.class.getName()).log(Level.SEVERE, null, ex);
        }
    
}
public void createTest(){
    submitQuestion.setDisable(true);
    //updateScore();
    
    Database.connect();
    
    String SQL = "SELECT q.Question, q.Option1, q.Option2, q.Option3, q.Option4, q.Option1Value, q.Option2Value, q.Option3Value, q.Option4Value FROM QUESTION q, TEST t WHERE t.QuestionID = q.ID;";
    this.rs = Database.searchQuery(SQL);
       try {
           rs.next();
            mainQuestion.setText(rs.getString(1));
            option1.setText(rs.getString(2));
            option2.setText(rs.getString(3));
            option3.setText(rs.getString(4));
            option4.setText(rs.getString(5));
           
        } catch (SQLException ex) {
            Logger.getLogger(BowlTestController.class.getName()).log(Level.SEVERE, null, ex);
        }
       /*
        try{
            while(rs.next()){
                try {
                    
                    for(int i = 1; i <= rs.getMetaData().getColumnCount(); i++){
                        System.out.println(rs.getString(i));
                        
                    }   }catch (SQLException ex) {
                        Logger.getLogger(BowlTestController.class.getName()).log(Level.SEVERE, null, ex);
                    }
            }
            }catch(SQLException ex) {
            Logger.getLogger(BowlTestController.class.getName()).log(Level.SEVERE, null, ex);
        }
    */
}
public void submit(ActionEvent e){

        double totalScore = (score/testTotal) *100;
        totalScore = Math.round(totalScore * 100);
        totalScore = totalScore/100;
        
        //save value into DB
        try{
            System.out.println("ID FROM SUBMIT "+ id);
            ResultSet questionInfo = Database.searchQuery("SELECT * FROM IQPSYCH WHERE ID=" + id + ";"); 
           if(questionInfo.first()){
               //if user is in Database, Update score
                String sql = "UPDATE IQPSYCH SET IQ = "+totalScore+" WHERE ID ="+id+";";
                Database.executeUpdate(sql);
                System.out.println("Updated score correctly");
                }
            else{
               //If user is not in db, Add user
                String sql = "INSERT INTO IQPSYCH VALUES ("+id+" , "+totalScore+" ,null);";
                Database.executeUpdate(sql);
                System.out.println("Has been added correctly");
           }
        }catch(Exception Turkey){    
                    System.out.println("BYE");
            Database.close();
                    
        }
        try {
            //once added go back
            goBack();
        }catch (IOException ex) {
            Logger.getLogger(EditQuestionsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    Database.close();
}
    protected void setStage(Stage stage)
    {
        preTitle = stage.getTitle();
        this.stage = stage;
        this.stage.setTitle(title);
        
        preMinHeight = stage.getMinHeight();
        preMinWidth = stage.getMinWidth();
        
        stage.setMinHeight(stage.getHeight());
        stage.setMinWidth(stage.getWidth());
    }
    
    /**
     * 
     * @param event 
     */
    //open up forms. Takes file path to form
    
    @FXML
    private void next(ActionEvent event)
    {        
        System.out.println("NEXT PRESS");
        try {
            System.out.println(option1Value.isSelected());
            if(rs.getBoolean(6) == option1Value.isSelected() && rs.getBoolean(7) == option2Value.isSelected() && rs.getBoolean(8) == option3Value.isSelected() && rs.getBoolean(9) == option4Value.isSelected()){
                score++;
            }
           if(rs.next()){
           testTotal++;
           option1Value.setSelected(false);
           option2Value.setSelected(false);
           option3Value.setSelected(false);
           option4Value.setSelected(false);
            mainQuestion.setText(rs.getString(1));
            option1.setText(rs.getString(2));
            option2.setText(rs.getString(3));
            option3.setText(rs.getString(4));
            option4.setText(rs.getString(5));
           }else{
               nextQuestion.setDisable(true);
               submitQuestion.setDisable(false);
           }
        } catch (SQLException ex) {
            System.out.println("CATCH");
            Logger.getLogger(BowlTestController.class.getName()).log(Level.SEVERE, null, ex);
        }
          
    }
    @FXML
   
    protected void setPreScene(Scene pre)
    {
        preScene = pre;
    }
    /**
     * Method called by the FXML after the user pushes the back button.
     * It sets the scene to the previous one.
     * @throws IOException 
     */
    @FXML
    private void goBack() throws IOException
    {
        Database.close();
        stage.setMinHeight(preMinHeight);
        stage.setMinWidth(preMinWidth);
        stage.setScene(preScene);
        stage.sizeToScene();
        stage.setTitle(preTitle);
        
    }


}






























































