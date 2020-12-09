package main.java.controllers;

import javafx.application.Platform;
import javafx.scene.text.Font;
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
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


/**
 * FXML Controller class
 * <p>
 * This is the controller for the window that appears when you click the Update Questions button
 *
 * @author Mario
 */
public class ResultsController implements Initializable {
    /**
     * Table to hold the questions
     */
    @FXML
    VBox table;
    
    @FXML
    Label testname;
    
    @FXML
    Label titleSlide;
    
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
    protected final String title = "Results";
    
   
    @FXML
    Button btnSubmit;
    
    Label Questions[];
    int QuestionID[];
    
    RadioButton Choices[];
    ToggleGroup toggle[];
    Label Answers[];
    
    String test;
    String user;
    
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
            System.out.println("init " + test);
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
                        
                        // prepare SQL statement
                        String SQL = "SELECT q.QUESTION, a.ANSWER FROM ANSWER__"+test+""+user + " a, QUESTION q where a.QUESTIONID = q.ID";  
                        
                        titleSlide.setText(user);
                        testname.setText(test);
                        
                        try {
                            
                            //String SQL = "SELECT * FROM TEST__"+test;
                            ResultSet rs = Database.searchQuery(SQL);
                            // 2nd result set only for getting row count
                            ResultSet rs2 = Database.searchQuery(SQL);
                            //testname.setText(test);
                          
                            int i=0;

                            rs2.last();
                            // getting row count
                            Answers = new Label[rs2.getRow()];
                            
                            Questions = new Label[rs2.getRow()];
                            
                            
                            Label spacer;
                            Label temp;
                           
                            // gets data from result set and adds elements to page
                            while(rs.next()) {
                                 
                                HBox hbox = new HBox(5);
                                HBox ahbox = new HBox(5);
                                
                                temp = new Label("Question "+(i+1)+": ");
                                temp.setFont(new Font("Georgia", 24));
                                hbox.getChildren().add(temp);
                                
                                temp = new Label(rs.getString(1));
                                temp.setFont(new Font("Georgia", 24));
                                hbox.getChildren().add(temp);
                                
                                temp = new Label("Answer: ");
                                temp.setFont(new Font("Georgia", 24));
                                ahbox.getChildren().add(temp);
                                
                                temp = new Label(rs.getString(2));
                                temp.setFont(new Font("Georgia", 24));
                                ahbox.getChildren().add(temp);
                                
                                table.getChildren().add(hbox);
                                table.getChildren().add(ahbox);
                                
                                spacer = new Label();
                                spacer.setMinWidth(300);

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
    
    
    protected void setTest(String quiz, String name){
        System.out.println(quiz + " " + name);
        this.test = quiz;
        this.user = name;
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
    
    
    
}