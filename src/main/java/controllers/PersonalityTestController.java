
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
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import main.formObjects.personalityTest;
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
public class PersonalityTestController implements Initializable
{
    private Stage stage;                //The window.
    personalityTest pt = new personalityTest();
    private Scene preScene;             //The previous screens scene while using the back button.
    private Scene nextScene;            //The to be next scene.
    private double preMinHeight;        //The previous minimum screens height.
    private double preMinWidth;         //The previous minimum screens width.
    private Scene homeScene;
    private ObservableList<ObservableList> data;
    String id;
    private String preTitle;            //The previous screens title.
    protected final String title = "Personality Test";       //The current stages title.
    private ResultSet rs;
    int questionCount = 0;
    String[][] test = new String[70][3];
    String[] anwsers = new String[70];
    
    @FXML
    Label mainQuestion;
    @FXML
    Label option1;
    @FXML
    Label option2;
    @FXML
    RadioButton option1Value;
    @FXML
    RadioButton option2Value;
    
    @FXML
    ToggleGroup personalityTest;
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
        option1Value.setSelected(true);
        test = pt.getPersonalityTest();
        createTest();
    }
    public void setId(String id){
        this.id = id;
    }
    public void updateScore(String id){
        Database.connect();
    String SQL = "SELECT * FROM IQPSYCH WHERE ID="+id+";";
    ResultSet turkey = Database.searchQuery(SQL);
        try {
            if(turkey != null){
                if(turkey.first()){
                    System.out.println("TESTING"+turkey.getString(3));
                    currentScore.setText(turkey.getString(3));
            }else{
                System.out.println("User has not taken test yet");
            }}
        }catch (SQLException ex) {
            Logger.getLogger(BowlTestController.class.getName()).log(Level.SEVERE, null, ex);
        }
    Database.close();
    
}
    //create the test and display the first question
public void createTest(){
 
    //disable submit button until test is completed
    if(questionCount ==69){
        submitQuestion.setDisable(false);
        nextQuestion.setDisable(true);
    }else{
    submitQuestion.setDisable(true);
    nextQuestion.setDisable(false);
    }
   //display test questions
    mainQuestion.setText(test[questionCount][0]); 
    option1.setText(test[questionCount][1]);
    option2.setText(test[questionCount][2]);
}
//submit the test
public void submit(ActionEvent e){
    Database.connect();
        pt.setPersonalityTestResults(anwsers);
        //return the test results and get the type
        String personalityType = pt.createTestResults();
        //save value into DB
        System.out.println(personalityType);
        try{
            System.out.println("ID FROM SUBMIT "+ id);
            ResultSet questionInfo = Database.searchQuery("SELECT * FROM IQPSYCH WHERE ID=" + id + ";"); 
           if(questionInfo.first()){
               //if user is in Database, Update score
                String sql = "UPDATE IQPSYCH SET Psych = '"+personalityType+"'"+"WHERE ID="+id+ ";";
                Database.executeUpdate(sql);
                System.out.println("Updated score correctly");
                }
            else{
               //If user is not in db, Add user
                String sql = "INSERT INTO IQPSYCH VALUES ("+id+", null, '" +personalityType+"');";
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
    
    @FXML
    private void next(ActionEvent event)
    {        
        System.out.println("NEXT PRESS");
        System.out.println(option1Value.isSelected());
        if(option1Value.isSelected()){
            anwsers[questionCount] = "a";
        }else{
            anwsers[questionCount] = "b";
        }
        questionCount++;
        createTest();
        
    }
    @FXML
    protected void setPreScene(Scene pre)
    {
        preScene = pre;
    }
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


















