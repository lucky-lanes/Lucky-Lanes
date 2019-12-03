
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
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import main.java.LuckyLanes;

/**
 * FXML Controller class
 *
 * This is the controller for the creation of an athlete. It gives the user
 * the option to choose between a list of specific athletes.
 * 
 * @author Mario
 */
public class ShowFormsController implements Initializable
{
    private Stage stage;                //The window.
    
    private Scene preScene;             //The previous screens scene while using the back button.
    private Scene nextScene;            //The to be next scene.
    private double preMinHeight;        //The previous minimum screens height.
    private double preMinWidth;         //The previous minimum screens width.
    
    private String preTitle;            //The previous screens title.
    protected final String title = "Forms";       //The current stages title.
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO
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
    public void handle(String fileName){
        Desktop desktop = Desktop.getDesktop();
        File file = new File(fileName);
        try{
            desktop.open(file);
            System.out.println(fileName);
        }catch(IOException ex){
            System.out.println("failed");
        }
    }
    @FXML
    private void acsi(ActionEvent event)
    {
        handle("./src/main/DocumentsPDF/ACSI.pdf");
    }
    @FXML
    private void bowlerAssessment(ActionEvent event)
    {
        handle("./src/main/DocumentsPDF/Bowler_Assessment.pdf");
    }@FXML
      private void bowlingQuestion(ActionEvent event)
    {
        handle("./src/main/DocumentsPDF/Bowling_IQ_Topics.pdf");
        
    }@FXML
      private void mbti(ActionEvent event)
    {
        handle("./src/main/DocumentsPDF/MBTI_Personality_Type_Test.pdf");
    }
    
    /**
     * Adds the previous scene into the object to allow the user to go back to it with the back button.
     * @param pre The previous scene.
     */
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
    }


}









































