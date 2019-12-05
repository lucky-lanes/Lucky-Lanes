
package main.java.controllers;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;
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
public class AddQuestionController implements Initializable
{
    private Stage stage;                //The window.
    
    private Scene preScene;             //The previous screens scene while using the back button.
    private Scene nextScene;            //The to be next scene.
    private double preMinHeight;        //The previous minimum screens height.
    private double preMinWidth;         //The previous minimum screens width.
    private Scene homeScene;
    
    private String preTitle;            //The previous screens title.
    protected final String title = "Add Question";       //The current stages title.
    @FXML
    TextField mainQuestion;
    @FXML
    TextField option1;
    @FXML
    TextField option2;
    @FXML
    TextField option3;
    @FXML
    TextField option4;
    @FXML
    CheckBox option1Value;
    @FXML
    CheckBox option2Value;
    @FXML
    CheckBox option3Value;
    @FXML
    CheckBox option4Value;
    @FXML
    Button Submit;
    
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {

    }
    
    /**
     * Puts things in the current window. Changes the scene to the current one.
     * @param stage The window.
     */

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
    private void questionToAdd(ActionEvent event)
    {
        //Option option1Obj = new Option(option1.getText(), option1Value.isSelected());
        //Option option2Obj = new Option(option2.getText(), option2Value.isSelected());
        //Option option3Obj = new Option(option3.getText(), option3Value.isSelected());
        //Option option4Obj = new Option(option4.getText(), option4Value.isSelected());
        //Question questionObj = new Question(option1Obj, option2Obj, option3Obj, option4Obj);
        
        //questionObj.saveToDataBase();
        //This will save the question and the answers into the database
        String sql = "INSERT INTO QUESTION VALUES (null,"
                + "'" + mainQuestion.getText() + "'" + ","
                + "'" + option1.getText() + "'" + ","
                + "'" + option1Value.isSelected() + "'" + ","
                + "'" + option2.getText() + "'" + ","
                + "'" + option2Value.isSelected() + "'" + ","
                + "'" + option3.getText() + "'" + ","
                + "'" + option3Value.isSelected() + "'" + ","
                + "'" + option4.getText() + "'" + ","
                + "'" + option4Value.isSelected() + "'" + ");";
        System.out.println(sql);
                Database.executeUpdate(sql);
                
        try{
            goBack();
        }catch(Exception e){
            System.out.print("failed");
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
        stage.setMinHeight(preMinHeight);
        stage.setMinWidth(preMinWidth);
        stage.setScene(preScene);
        stage.sizeToScene();
        stage.setTitle(preTitle);
        stage.close();
        
    }


}































































