package main.java.controllers;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;
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
 * FXML Controller class for BowlTest.fxml
 * <p>
 * This is the controller for taking the questionaire when inside a bowler.
 * The form that this is for is when you start up the application, click Search 
 * Athletes, and then click open on a bowler, and then would click take questionaire
 *
 * @author Mario
 */
public class BowlTestController implements Initializable {
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
     * The previous screen's minimum height.
     */
    private double preMinHeight;
    /**
     * The previous screen's minimum width.
     */
    private double preMinWidth;
    /**
     * The home scene
     */
    private Scene homeScene;
    /**
     * Unknown what this variable is because it only shows up here inside of this class
     */
    private ObservableList<ObservableList> data;
    /**
     * The bowler's ID
     */
    String id;
    /**
     * The previous screen's title.
     */
    private String preTitle;
    /**
     * The current stage's title
     */
    protected final String title = "Add Question";
    /**
     * The result set from an SQL query
     */
    private ResultSet rs;
    /**
     * The score for a bowler taking the test
     */
    double score = 0;
    /**
     * Unknown what this variable is because it only shows up here inside of this class
     */
    double testTotal = 1;
    /**
     * The question being asked
     */
    @FXML
    Label mainQuestion;
    /**
     * The first option to choose from
     */
    @FXML
    Label option1;
    /**
     * The second option to choose from
     */
    @FXML
    Label option2;
    /**
     * The third option to choose from
     */
    @FXML
    Label option3;
    /**
     * The fourth option to choose from
     */
    @FXML
    Label option4;
    /**
     * The checkbox for the first option
     */
    @FXML
    CheckBox option1Value;
    /**
     * The checkbox for the second option
     */
    @FXML
    CheckBox option2Value;
    /**
     * The checkbox for the third option
     */
    @FXML
    CheckBox option3Value;
    /**
     * The checkbox for the fourth option
     */
    @FXML
    CheckBox option4Value;
    /**
     * The button to submit your answer
     */
    @FXML
    Button submitQuestion;
    /**
     * The button to load the next question
     */
    @FXML
    Button nextQuestion;
    /**
     * Label showing your current score
     */
    @FXML
    Label currentScore;
    private Executor exec;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
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
     * Sets the id of the bowler you are in right now
     *
     * @param id ID number of a bowler
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Updates the score shown in the currentScore label
     *
     * @param id ID of the bowler
     */
    public void updateScore(String id) {
        // String SQL = "SELECT IQ FROM IQPSYCH;";
        System.out.println("id = " + id);
        String SQL = "SELECT * FROM IQPSYCH WHERE ID=" + id + ";";
        ResultSet turkey = Database.searchQuery(SQL);//3 Strikes? Why bowling terminology in programming for the psych test
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

            if (turkey != null) {
                if (turkey.first()) {
                    currentScore.setText(Double.toString(turkey.getDouble(2)));
                } else {
                    System.out.println("User has not taken test yet");
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(BowlTestController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Gets a questionaire question from the database and displays it
     */
    public void createTest() {
        submitQuestion.setDisable(true);
        //updateScore();

        Database.connect();

        String SQL = "SELECT q.Question, q.Option1, q.Option2, q.Option3, q.Option4, q.Option1Value, q.Option2Value, q.Option3Value, q.Option4Value FROM QUESTION q, TEST t WHERE t.QuestionID = q.ID ORDER BY RAND();";
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

    /**
     * Calculates the total score by finding the percent of the points they scored. It then updates the database and
     * calls the goBack method to exit this scene
     *
     * @param event
     */
    public void submit(ActionEvent e) {

        double totalScore = (score / testTotal) * 100;
        totalScore = Math.round(totalScore * 100);
        totalScore = totalScore / 100;

        //save value into DB
        try {
            System.out.println("ID FROM SUBMIT " + id);
            ResultSet questionInfo = Database.searchQuery("SELECT * FROM IQPSYCH WHERE ID=" + id + ";");
            if (questionInfo.first()) {
                //if user is in Database, Update score
                String sql = "UPDATE IQPSYCH SET IQ = " + totalScore + " WHERE ID =" + id + ";";
                Database.executeUpdate(sql);
                System.out.println("Updated score correctly");
            } else {
                //If user is not in db, Add user
                String sql = "INSERT INTO IQPSYCH VALUES (" + id + " , " + totalScore + " ,null);";
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
     * Clears the previous answers, and loads up the next questionaire question.
     * <p>
     * Ran when the Next button is clicked
     *
     * @param event
     */
    @FXML
    private void next(ActionEvent event) {
        System.out.println("NEXT PRESS");
        try {
            System.out.println(option1Value.isSelected());
            if (rs.getBoolean(6) == option1Value.isSelected() && rs.getBoolean(7) == option2Value.isSelected() && rs.getBoolean(8) == option3Value.isSelected() && rs.getBoolean(9) == option4Value.isSelected()) {
                score++;
            }
            if (rs.next()) {
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
            } else {
                nextQuestion.setDisable(true);
                submitQuestion.setDisable(false);
            }
        } catch (SQLException ex) {
            System.out.println("CATCH");
            Logger.getLogger(BowlTestController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
