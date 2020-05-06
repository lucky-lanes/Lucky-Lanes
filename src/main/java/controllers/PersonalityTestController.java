package main.java.controllers;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import main.formObjects.personalityTest;
import main.java.Database;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.concurrent.Executor;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * FXML Controller class
 * <p>
 * This is the controller for the psych test. This is opened when, from the Admin screen, you click 
 * Search Athletes, then open on an athlete, and then psych test
 *
 * @author Mario
 */
public class PersonalityTestController implements Initializable {
    /**
     * The window
     */
    private Stage stage;
    personalityTest pt = new personalityTest();
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
     * The home screen
     */
    private Scene homeScene;
    private ObservableList<ObservableList> data;
    String id;
    /**
     * The previous screen's title
     */
    private String preTitle;
    /**
     * The current screen's title
     */
    protected final String title = "Personality Test";
    private ResultSet rs;
    /**
     * Variable to hold the number of questions answered
     */
    int questionCount = 0;
    /**
     * String array to hold the questions and the two answer options
     */
    String[][] test = new String[70][3];
    /**
     * String array to hold the selected answers to the questions
     */
    String[] anwsers = new String[70];

    /**
     * Label to display the queston
     */
    @FXML
    Label mainQuestion;
    /**
     * First answer option text
     */
    @FXML
    Label option1;
    /**
     * Second answer option text
     */
    @FXML
    Label option2;
    /**
     * First answer option radio button
     */
    @FXML
    RadioButton option1Value;
    /**
     * Second answer option radio button
     */
    @FXML
    RadioButton option2Value;
    /**
     * ToggleGroup to hold the two radio buttons in
     */
    @FXML
    ToggleGroup personalityTest;
    /**
     * The submit button
     */
    @FXML
    Button submitQuestion;
    /**
     * The next button
     */
    @FXML
    Button nextQuestion;
    /**
     * The label holding your current score
     */
    @FXML
    Label currentScore;
    private Executor exec;

    /**
     * Initializes the controller class.
     *
     * @param url URL of the database
     * @param rb Resource bundle
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        option1Value.setSelected(true);
        test = pt.getPersonalityTest();
        createTest();
    }

    /**
     * Setter for the id variable
     *
     * @param id Id of question
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Method to update the score. From what I can tell this method is not called anywhere
     *
     * @param id Id of question
     */
    public void updateScore(String id) {
        Database.connect();
        String SQL = "SELECT * FROM IQPSYCH WHERE ID=" + id + ";";
        ResultSet turkey = Database.searchQuery(SQL);
        try {
            if (turkey != null) {
                if (turkey.first()) {
                    System.out.println("TESTING" + turkey.getString(3));
                    currentScore.setText(turkey.getString(3));
                } else {
                    System.out.println("User has not taken test yet");
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(BowlTestController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Database.close();

    }

    /**
     * Create the test and display the first queston. Ran when the window opens
     */
    public void createTest() {

        //disable submit button until test is completed
        if (questionCount == 69) {
            submitQuestion.setDisable(false);
            nextQuestion.setDisable(true);
        } else {
            submitQuestion.setDisable(true);
            nextQuestion.setDisable(false);
        }
        //display test questions
        mainQuestion.setText(test[questionCount][0]);
        option1.setText(test[questionCount][1]);
        option2.setText(test[questionCount][2]);
    }

    /**
     * Submits the test. Ran when the submit button is clicked.
     *
     * @param e Action event
     */
    public void submit(ActionEvent e) {
        Database.connect();
        pt.setPersonalityTestResults(anwsers);
        //return the test results and get the type
        String personalityType = pt.createTestResults();
        //save value into DB
        System.out.println(personalityType);
        try {
            System.out.println("ID FROM SUBMIT " + id);
            ResultSet questionInfo = Database.searchQuery("SELECT * FROM IQPSYCH WHERE ID=" + id + ";");
            if (questionInfo.first()) {
                //if user is in Database, Update score
                String sql = "UPDATE IQPSYCH SET Psych = '" + personalityType + "'" + "WHERE ID=" + id + ";";
                Database.executeUpdate(sql);
                System.out.println("Updated score correctly");
            } else {
                //If user is not in db, Add user
                String sql = "INSERT INTO IQPSYCH VALUES (" + id + ", null, '" + personalityType + "');";
                Database.executeUpdate(sql);
                System.out.println("Has been added correctly");
            }
        } catch (Exception Turkey) {
            System.out.println("BYE");
            Database.close();
        }
        try {
            //once added go back
            goBack();
        } catch (IOException ex) {
            Logger.getLogger(EditQuestionsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Database.close();
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
     * Loads up the next question of the test. Ran when the next button is clicked
     */
    @FXML
    private void next(ActionEvent event) {
        System.out.println("NEXT PRESS");
        System.out.println(option1Value.isSelected());
        if (option1Value.isSelected()) {
            anwsers[questionCount] = "a";
        } else {
            anwsers[questionCount] = "b";
        }
        questionCount++;
        createTest();

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
        Database.close();
        stage.setMinHeight(preMinHeight);
        stage.setMinWidth(preMinWidth);
        stage.setScene(preScene);
        stage.sizeToScene();
        stage.setTitle(preTitle);
    }
}
