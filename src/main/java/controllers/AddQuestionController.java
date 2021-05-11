package main.java.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.java.Database;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.util.ResourceBundle;

/**
 * FXML Controller class for addQuestion.fxml
 * <p>
 * This is the controller for the creation of a question in the questionaire.
 * To navigate to this page. Start the application, click the "Questionaire Editor" button
 * and then the "Add Question" button
 *
 * @author Mario
 */
public class AddQuestionController implements Initializable {
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
     * The home screen
     */
    private Scene homeScene;
    /**
     * The previous screen's title
     */
    private String preTitle;
    /**
     * The current screen's title
     */
    protected final String title = "Add Question";
    /**
     * Textfield next to the "Question to be asked" label
     */
    @FXML
    TextField mainQuestion;
    /**
     * Textfield next to the "Option 1" label
     */
    @FXML
    TextField option1;
    /**
     * Textfield next to the "Option 2" label
     */
    @FXML
    TextField option2;
    /**
     * Textfield next to the "Option 3" label
     */
    @FXML
    TextField option3;
    /**
     * Textfield next to the "Option 4" label
     */
    @FXML
    TextField option4;
    /**
     * Checkbox, under the "Correct" column, which is next to the Option1 textfield
     */
    @FXML
    CheckBox option1Value;
    /**
     * Checkbox, under the "Correct" column, which is next to the Option2 textfield
     */
    @FXML
    CheckBox option2Value;
    /**
     * Checkbox, under the "Correct" column, which is next to the Option3 textfield
     */
    @FXML
    CheckBox option3Value;
    /**
     * Checkbox, under the "Correct" column, which is next to the Option4 textfield
     */
    @FXML
    CheckBox option4Value;
    /**
     * The submit button
     */
    @FXML
    Button Submit;

    /**
     * Initializes the controller class.
     *
     * @param url URL of the database
     * @param rb Resource bundle
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

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

        //save the previous scene's height and width
        preMinHeight = stage.getMinHeight();
        preMinWidth = stage.getMinWidth();

        //set the scene's height and width for the new scene
        stage.setMinHeight(stage.getHeight());
        stage.setMinWidth(stage.getWidth());
    }

    /**
     * Ran when the submit button is clicked. Adds the input question/answers to the database
     * <p>
     * Previous Group's Comment: Open up forms. Takes file path to form
     *
     * @param event
     */
    @FXML
    private void questionToAdd(ActionEvent event) {
        //Option option1Obj = new Option(option1.getText(), option1Value.isSelected());
        //Option option2Obj = new Option(option2.getText(), option2Value.isSelected());
        //Option option3Obj = new Option(option3.getText(), option3Value.isSelected());
        //Option option4Obj = new Option(option4.getText(), option4Value.isSelected());
        //Question questionObj = new Question(option1Obj, option2Obj, option3Obj, option4Obj);

        //questionObj.saveToDataBase();
        //This will save the question and the answers into the database
        String sql = "INSERT INTO QUESTION VALUES (null, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        try {
        	Database.connect();
        	PreparedStatement pstmt = Database.conn.prepareStatement(sql);
        	
        	pstmt.setString(1, mainQuestion.getText());
        	pstmt.setString(2, option1.getText());
        	pstmt.setBoolean(3, option1Value.isSelected());
        	pstmt.setString(4, option2.getText());
        	pstmt.setBoolean(5, option2Value.isSelected());
        	pstmt.setString(6, option3.getText());
        	pstmt.setBoolean(7, option3Value.isSelected());
        	pstmt.setString(8, option4.getText());
        	pstmt.setBoolean(9, option4Value.isSelected());
        	
        	pstmt.execute();
        	Database.close();
            goBack();
        } catch (Exception e) {
            System.out.print("failed");
            e.printStackTrace();
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
        stage.setMinHeight(preMinHeight);
        stage.setMinWidth(preMinWidth);
        stage.setScene(preScene);
        stage.sizeToScene();
        stage.setTitle(preTitle);
        stage.close();
    }
}
