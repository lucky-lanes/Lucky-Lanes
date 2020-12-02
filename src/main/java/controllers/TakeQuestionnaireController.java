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
    
    Label Questions;
    
    RadioButton Choices;
    ToggleGroup toggle[];
    int Answers[][];
    
    String test = "QUIZ1";
    
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
        //table.getItems().clear();

        //table.getColumns().clear();

        System.out.println("Running");

        // prepare SQL statement
        String SQL = "SELECT * FROM TEST__"+test;

        // grab the result set of the equation
        //ResultSet rs = Database.searchQuery(SQL);

        Task<Void> databaseQuery = new Task<Void>() {

            @Override
            public Void call() throws Exception {
                //System.out.print("fasldfkj;asldfkj");
                //BEHOLD
                progressIndicator.setVisible(true);//FUCK YES!!!!

                //Platform.runlater is used to update an UI control inside a different thread.
                Database.connect();

                
                
                /*
                rs.last();
                int rsLen = rs.getRow();
                rs.beforeFirst();
                */

                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        data = FXCollections.observableArrayList();
                           System.out.println("here1");
                        try {
                            String SQL = "SELECT * FROM TEST__"+test;
                            ResultSet rs = Database.searchQuery(SQL);
                            // 2nd result set only for getting row count
                            ResultSet rs1 = Database.searchQuery(SQL);

                            
                            int i=0;

                            rs1.last();
                            // getting row count
                            Answers = new int[rs1.getRow()][];
                            toggle = new ToggleGroup[rs1.getRow()];

                            // gets data from result set and adds elements to page
                            while(rs.next()) {

                                Questions = new Label(rs.getString(2));

                                table.getChildren().add(Questions);
                                toggle[i] = new ToggleGroup();
                                
                                for(int j=0;j<rs.getMetaData().getColumnCount()-2;j++)
                                {
                                    Choices = new RadioButton();
                                    Choices.setText(rs.getString(j+3));
                                    Choices.setToggleGroup(toggle[i]);
                                    table.getChildren().add(Choices);
                                }
                            }

                            
                        } catch (SQLException ex) {
                            //Logger.getLogger(SearchController.class.getName()).log(Level.SEVERE, null, ex);

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
            //this.rs=databaseQuery.getValue();

            System.out.println("It's Alive!!!");
            progressIndicator.progressProperty().unbind();
            progressIndicator.setProgress(1);
            progressIndicator.setVisible(false);
        });

        // Task.getValue() gives the value returned from call()...
        //rs = widgetSearchTask.getValue())
        // start converting the result set into tableview
        exec.execute(databaseQuery);
        
    }

    /**
     * Opens the Edit Questons window when the open button is clicked for a question
     *
     * @param id ID of the question
     */

    //public void setTableName(String name) {
    //    this.nameOfTable = name;
    //}
    
    //public String getTableName() {
    //    return nameOfTable;
    //}

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
}