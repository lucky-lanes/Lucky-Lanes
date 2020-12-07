package main.java.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import main.java.LuckyLanes;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * FXML Controller class
 * <p>
 * This is the controller for the creation of an athlete. It gives the user
 * the option to choose between a list of specific athletes.
 * <p>
 * This window is the window that comes up when you click the Add New Athlete button from the administration screen
 *
 * @author Mario
 */
public class NewAthleteController implements Initializable {
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
    protected final String title = "New Athlete";

    /**
     * Initializes the controller class.
     *
     * @param url URL of the database
     * @param rb Resource bundle
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    /**
     * Puts things in the current window. Changes the scene to the current one.
     *
     * @param stage The window.
     */
    public void setStage(Stage stage) {
        preTitle = stage.getTitle();
        this.stage = stage;
        this.stage.setTitle(title);

        preMinHeight = stage.getMinHeight();
        preMinWidth = stage.getMinWidth();

        stage.setMinHeight(stage.getHeight());
        stage.setMinWidth(stage.getWidth());
    }

    /**
     * Loads up the form for adding a bowler to the database.
     * Ran when the Bowling button is clicked.
     *
     * @param event
     */
    @FXML
    private void createBowler(ActionEvent event) {
        String fxml = "/main/resources/view/bowler.fxml";

        BorderPane root;
        try {
            FXMLLoader loader = new FXMLLoader();
            InputStream in = LuckyLanes.class.getResourceAsStream(fxml);

            loader.setBuilderFactory(new JavaFXBuilderFactory());
            loader.setLocation(LuckyLanes.class.getResource(fxml));

            try {
                root = (BorderPane) loader.load(in);
            } finally {
                in.close();
            }

            //Stage stage = new Stage();
            nextScene = stage.getScene();
            stage.setScene(new Scene(root));
            stage.show();

            //stage.setMinHeight(stage.getHeight());
            //stage.setMinWidth(stage.getWidth());

            BowlerController newBowler = (BowlerController) ((Initializable) loader.getController());

            newBowler.setStage(stage);
            newBowler.setPreScene(nextScene);

            newBowler.createListeners();

            // Hide this current window (if this is what you want)
            //((Node) (event.getSource())).getScene().getWindow().hide();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads up the form for adding a baseball player to the database.
     * Ran when the Baseball button is clicked.
     *
     * @param event
     */
    @FXML
    private void createBaseballPlayer(ActionEvent event) {
        //NOTE: Currently it will load up the add bowler page. You need to modify the fxml
        //      variable below in order to get it to go to the baseball page when one is made.
        //      When implementing that, you will also need to modify the controller created in
        //      the class
        String fxml = "/main/resources/view/bowler.fxml";

        BorderPane root;

        try {
            FXMLLoader loader = new FXMLLoader();
            InputStream in = LuckyLanes.class.getResourceAsStream(fxml);

            loader.setBuilderFactory(new JavaFXBuilderFactory());
            loader.setLocation(LuckyLanes.class.getResource(fxml));

            try {
                root = (BorderPane) loader.load(in);
            } finally {
                in.close();
            }

            //Stage stage = new Stage();
            //stage.setTitle("My New Stage Title");
            stage.setScene(new Scene(root));
            stage.show();


            BowlerController newBowler = (BowlerController) ((Initializable) loader.getController());
            newBowler.setStage(stage);
            newBowler.createListeners();

            stage.setOnCloseRequest((WindowEvent we) ->
            {
                //((OLD)) ((Stage) (((Node) (event.getSource())).getScene().getWindow())).show();  ((OLD))
                ((Stage) (stage.getScene()).getWindow()).show();
            });

            // Hide this current window (if this is what you want)
            //((Node) (event.getSource())).getScene().getWindow().hide();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Adds the previous scene into the object to allow the user to go back to it with the back button.
     *
     * @param pre The previous scene.
     */
    public void setPreScene(Scene pre) {
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
    }  
}
