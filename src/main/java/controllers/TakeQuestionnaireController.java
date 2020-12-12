package main.java.controllers;

import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Callback;
import main.java.Database;
import main.java.LuckyLanes;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.ArrayList;
import javafx.scene.layout.VBox;


/**
 * FXML Controller class
 * <p>
 * This is the controller for the window that appears when you click the Update Questions button
 *
 * @author Mario
 */
public class TakeQuestionnaireController implements Initializable {
    /**
     * Table to hold the questions
     */
    @FXML
    VBox table;
    
    @FXML
    Label testname;
    
    //TableView table;
    @FXML
    ProgressIndicator progressIndicator;
    private Executor exec;
    private ObservableList<ObservableList> data;
    /**
     * The window
     */
    private Stage stage;
    /**
     * The previous screen's scene while using the back button
     */
    private Scene preScene;
    /**
     * The to be next scene
     */
    private Scene nextScene; 
    /**
     * The previous screen's minimum height
     */
    private double preMinHeight;  
    /**
     * The previous screen's minimum width
     */
    private double preMinWidth;
    /**
     * The previous screen's title
     */
    private String preTitle;
    /**
     * The current screen's title
     */
    
    private String nameOfTable = null;
    protected final String title = "Take Questionnaire";
    
   
    @FXML
    Button btnSubmit;
    
    Label Questions[];
    int QuestionID[];
    
    RadioButton Choices[];
    ToggleGroup toggle[];
    String Answers[];
    
    String test;
    
    public void setFromRecord(String id){
         test = id;
         Database.connect();
     }


    //ArrayList<String> Questions = new ArrayList<String>();

    /**
     * Initializes the controller class.
     *
     * @param url URL of the database
     * @param rb Resource bundle
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        {
            // TODO

            exec = Executors.newCachedThreadPool(runnable ->
            {
                Thread t = new Thread(runnable);
                t.setDaemon(true);
                return t;
            });

            //table.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
            buildData();
        }
    }

    /**
     * Puts things in the current window. Changes the scene to the current one.
     *
     * @param stage The window.
     */
    protected void setStage(Stage stage) {
        preTitle = stage.getTitle();
        this.stage = stage;
        this.stage.setTitle(title);

        preMinHeight = stage.getMinHeight();
        preMinWidth = stage.getMinWidth();

        stage.setMinHeight(stage.getHeight());
        stage.setMinWidth(stage.getWidth());
    }

    /**
     * Gets ids and questions from the database and populates the table.
     */
    @FXML
    public void buildData(/*ActionEvent e*/) {
   

        System.out.println("Running");

        // prepare SQL statement
        String SQL = "SELECT * FROM "+test;

        // grab the result set of the equation
        //ResultSet rs = Database.searchQuery(SQL);

        Task<Void> databaseQuery = new Task<Void>() {

            @Override
            public Void call() throws Exception {
                //System.out.print("fasldfkj;asldfkj");
                //BEHOLD
                progressIndicator.setVisible(true);//FUCK YES!!!!

               
                Database.connect();

                
               
                //Platform.runlater is used to update an UI control inside a different thread.
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        data = FXCollections.observableArrayList();
                           
                        try {
                            
                            String SQL = "SELECT * FROM "+test;
                            ResultSet rs = Database.searchQuery(SQL);
                            // 2nd result set only for getting row count
                            ResultSet rs2 = Database.searchQuery(SQL);
                            testname.setText(test);
                          
                            int i=0;

                            rs2.last();
                            // getting row count
                            Answers = new String[rs2.getRow()];
                            toggle = new ToggleGroup[rs2.getRow()];
                            Questions = new Label[rs2.getRow()];
                            QuestionID = new int[rs2.getRow()];
                            Choices = new RadioButton[4];
                            Label spacer;
                           
                            // gets data from result set and adds elements to page
                            while(rs.next()) {
                                System.out.println(rs.getString(1));
                                QuestionID[i] = Integer.parseInt(rs.getString(2));
                                ResultSet rs1 = Database.searchQuery("SELECT * FROM QUESTION WHERE ID = "+rs.getString(2));
                                rs1.next();
                                System.out.println(rs1.getString(2));
                                Questions[i] = new Label(rs1.getString(2));
                                
                                table.getChildren().add(Questions[i]);
                                toggle[i] = new ToggleGroup();
                                spacer = new Label();
                                spacer.setMinWidth(300);
                                
                               
                                    Choices[0] = new RadioButton();
                                    Choices[1] = new RadioButton();
                                    Choices[2] = new RadioButton();
                                    Choices[3] = new RadioButton();
                                    Choices[0].setText(rs1.getString(3));
                                    Choices[1].setText(rs1.getString(5));
                                    Choices[2].setText(rs1.getString(7));
                                    Choices[3].setText(rs1.getString(9));
                                    Choices[0].setToggleGroup(toggle[i]);
                                    Choices[1].setToggleGroup(toggle[i]);
                                    Choices[2].setToggleGroup(toggle[i]);
                                    Choices[3].setToggleGroup(toggle[i]);
                                    table.getChildren().add(Choices[0]);
                                    table.getChildren().add(Choices[1]);
                                    table.getChildren().add(Choices[2]);
                                    table.getChildren().add(Choices[3]);
                                    
                                
                                table.getChildren().add(spacer);
                                i++;
                               
                            }

                            
                        } catch (SQLException ex) {
                            System.out.println("Here fail");

                        }

                    }
                });

                return null;
            }
        };

        progressIndicator.progressProperty().bind(databaseQuery.progressProperty());

        databaseQuery.setOnFailed(error ->
        {
            //databaseQuery.getException().printStackTrace();
            //Abandon the ship.
            System.out.println("Women and kids first");     //Titanic 2 anyone?
        });

        databaseQuery.setOnSucceeded(error ->
        {
            

            System.out.println("It's Alive!!!");
            progressIndicator.progressProperty().unbind();
            progressIndicator.setProgress(1);
            progressIndicator.setVisible(false);
        });

       
        exec.execute(databaseQuery);
        
    }

  

    /**
     * Adds the previous scene into the object to allow the user to go back to it with the back button.
     *
     * @param pre The previous scene.
     */
    @FXML
    protected void setPreScene(Scene pre) {
        preScene = pre;
    }

    /**
     * Method called by the FXML after the user pushes the back button.
     * It sets the scene to the previous one.
     *
     * @throws IOException
     */
    @FXML
    private void goBack() throws IOException {
        stage.close();
    }
    
    /**
    *called when you click submit on takeQuestionnaire screen
    * 
    *adds a line to the ANSWER table in database that holds the testid of the test taken,
    * the name of the table created to hold questions and answers, and the test taker
    *
    * creates table in database that holds the questionid's and answers to them
    */
    @FXML
    private void submitTest() {
        //gets selected radio button and saves to answers array
        for(int i = 0;i < toggle.length; i++){
            RadioButton rb = (RadioButton) toggle[i].getSelectedToggle();
            Answers[i] = rb.getText();
        }
        
        Database.connect();
            //overwrites old results if taking test again
            String sql ="DROP TABLE IF EXISTS ANSWER__"+test.substring(6)+""+ AuthenticationController.activeUser +";";
            Database.executeUpdate(sql);
            
            //creates a table on database using the name of the test and name of current user
            sql = "CREATE TABLE ANSWER__"+test.substring(6)+"" + AuthenticationController.activeUser + "(ID INT PRIMARY KEY AUTO_INCREMENT, QUESTIONID int, ANSWER VARCHAR(255));";
            Database.executeUpdate(sql);

           
            //inputs question names and answers into newly created table
            try{
               for(int i=0;i<QuestionID.length;i++)
               {
                    sql = "INSERT INTO ANSWER__"+test.substring(6)+"" + AuthenticationController.activeUser + " VALUES (null,"
                        + "'" + QuestionID[i] + "',"
                            + "'" + Answers[i] + "');";
                        
                        System.out.println(sql);
                        Database.executeUpdate(sql);
               }
               
               //gets id of test taken for input into answers table
               Database.connect();
               ResultSet testID = Database.searchQuery("SELECT ID FROM TEST WHERE TESTNAME = \'"+test+"\';");
               testID.next();
               
               //checks if entry for this test and current user already exists in answers table to stop multiple entries of same test user combo
               ResultSet tableCheck = Database.searchQuery("SELECT * FROM ANSWER");
               String testCase = "ANSWER__"+test.substring(6)+AuthenticationController.activeUser;
               String check = "YES";
               while(tableCheck.next()){
                   System.out.println(tableCheck.getString(3));
                   if(tableCheck.getString(3).equals(testCase))
                    {
                        check = "NO";
                        break;
                    }
                   
                   
               }

               if(check.equals("YES"))
                    {
                        //if current user hasnt already taken this test, line puts entry into answers table for seach by results page
                        sql ="INSERT INTO ANSWER VALUES (null, "+testID.getString(1)+",\'ANSWER__" + test.substring(6) + ""+ AuthenticationController.activeUser + "\', \'"+ AuthenticationController.activeUser +"\');";
                        System.out.println(sql);
               
                        Database.executeUpdate(sql);  
                    }
               
                    
               Database.close();
            }catch(Exception e){
                
            }  
        Database.close();   
        //Database.executeUpdate("DROP TABLE TEST");
       
        btnSubmit.setText("Test Submitted");
    }
}