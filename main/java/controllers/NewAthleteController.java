
package main.java.controllers;

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
public class NewAthleteController implements Initializable
{
    private Stage stage;                //The window.
    
    private Scene preScene;             //The previous screens scene while using the back button.
    private Scene nextScene;            //The to be next scene.
    private double preMinHeight;        //The previous minimum screens height.
    private double preMinWidth;         //The previous minimum screens width.
    
    private String preTitle;            //The previous screens title.
    protected final String title = "New Athlete";       //The current stages title.
    
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
    @FXML
    private void createBowler(ActionEvent event)
    {
        String fxml = "/main/resources/view/bowler.fxml";
        
        BorderPane root;
        try
        {
            FXMLLoader loader = new FXMLLoader();
            InputStream in = LuckyLanes.class.getResourceAsStream(fxml);
            
            loader.setBuilderFactory(new JavaFXBuilderFactory());
            loader.setLocation(LuckyLanes.class.getResource(fxml));

            try
            {
                root = (BorderPane) loader.load(in);
            }
            finally
            {
                in.close();
            }
            
            //Stage stage = new Stage();
            nextScene = stage.getScene();
            stage.setScene(new Scene(root));
            stage.show();
            
            //stage.setMinHeight(stage.getHeight());
            //stage.setMinWidth(stage.getWidth());
            
            BowlerController newBowler = (BowlerController)((Initializable) loader.getController());
            
            newBowler.setStage(stage);
            newBowler.setPreScene(nextScene);
            
            newBowler.createListeners();
            
            // Hide this current window (if this is what you want)
            //((Node) (event.getSource())).getScene().getWindow().hide();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    
    /**
     * 
     * @param event 
     */
    @FXML
    private void createBaseballPlayer(ActionEvent event)
    {
        String fxml = "/main/resources/view/bowler.fxml";
        
        BorderPane root;
        
        try
        {
            FXMLLoader loader = new FXMLLoader();
            InputStream in = LuckyLanes.class.getResourceAsStream(fxml);
            
            loader.setBuilderFactory(new JavaFXBuilderFactory());
            loader.setLocation(LuckyLanes.class.getResource(fxml));

            try
            {
                root = (BorderPane) loader.load(in);
            }
            finally
            {
                in.close();
            }
            
            //Stage stage = new Stage();
            //stage.setTitle("My New Stage Title");
            stage.setScene(new Scene(root));
            stage.show();
            
            
            BowlerController newBowler = (BowlerController)((Initializable) loader.getController());
            newBowler.setStage(stage);
            newBowler.createListeners();
            
            stage.setOnCloseRequest((WindowEvent we) ->
            {
                //((OLD)) ((Stage) (((Node) (event.getSource())).getScene().getWindow())).show();  ((OLD))
                ((Stage) (stage.getScene()).getWindow()).show();
            });
            
            // Hide this current window (if this is what you want)
            //((Node) (event.getSource())).getScene().getWindow().hide();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
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