package main.java.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import main.java.Database;
import main.java.LuckyLanes;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * FXML Controller class for AthleteMenu.fxml
 * <p>
 * This is the controller for the creation of an athlete. It gives the user
 * the option to choose between a list of specific athletes.
 * The form that this is for is when you start up the application, click Search 
 * Athletes, and then click open on a bowler
 *
 * @author Mario
 */
public class AthleteMenuController implements Initializable {
    @FXML
    Button btnBowlTest;
    /**
     * The window
     */
    private Stage stage;
    /**
     * The previous screen's scene while using the back button.
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
     * The previous screen's title.
     */
    private String preTitle;
    /**
     * The current stage's title.
     */
    protected final String title = "Menu";
    /**
     * The bowler's ID
     */
    String id;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        checkQuestions();
    }

    /**
     * Enables the Take Questionaire button if there is results from the sql query
     * "SELECT * FROM TEST;". Otherwise it stays disabled
     * <p>
     * Ran when the controller class is initialized
     *
     * @param stage The window.
     */
    public void checkQuestions() {
        btnBowlTest.setDisable(true);
        Database.connect();
        String SQL = "SELECT * FROM TEST;";
        ResultSet turkey = Database.searchQuery(SQL);
        if (turkey != null) {
            try {
                if (turkey.next()) {
                    btnBowlTest.setDisable(false);
                }
            } catch (SQLException ex) {
                Logger.getLogger(AthleteMenuController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        Database.close();
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
     * This is an injected method used by JAVAFX,
     * It creates a new stage to display the form with the bowler's information,
     * Y Balance test, fms score,  fitness testing data sheet, and medical survey 
     * <p>
     * Ran when the View/Edit Athlete Info button is clicked
     *
     * @param event
     */
    @FXML
    private void athleteInfo(ActionEvent event) {
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

            Stage stage = new Stage();
            //stage.setTitle("My New Stage Title");
            stage.setScene(new Scene(root));
            stage.show();

            BowlerController newBowler = (BowlerController) ((Initializable) loader.getController());
            newBowler.setFromRecord(id);
            newBowler.setUpViewWindow();
            System.out.println(id);
            newBowler.setStage(stage);
            newBowler.createListeners();

            //So correct errors can be displayed in BowlerController
            newBowler.setSuccessful(true);

            //newBowler.setUpViewWindow();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This is an injected method used by JAVAFX,
     * It creates a new stage to display the form to take the questionaire
     * <p>
     * Ran when the Take Questionaire button is clicked
     *
     * @param event
     */
    @FXML
    private void bowlTest(ActionEvent event) {
        String fxml = "/main/resources/view/bowlTest.fxml";

        AnchorPane root;

        try {
            FXMLLoader loader = new FXMLLoader();
            InputStream in = LuckyLanes.class.getResourceAsStream(fxml);

            loader.setBuilderFactory(new JavaFXBuilderFactory());
            loader.setLocation(LuckyLanes.class.getResource(fxml));

            try {
                root = (AnchorPane) loader.load(in);
            } finally {
                in.close();
            }

            //Stage stage = new Stage();
            preScene = stage.getScene();
            stage.setScene(new Scene(root));
            stage.show();

            BowlTestController newBowler = (BowlTestController) ((Initializable) loader.getController());
            newBowler.setId(id);
            newBowler.updateScore(id);
            System.out.println(id);
            newBowler.setStage(stage);
            newBowler.setPreScene(preScene);

            stage.setOnCloseRequest((WindowEvent we) ->
            {
                ((Stage) (stage.getScene()).getWindow()).show();
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This is an injected method used by JAVAFX,
     * It creates a new stage to display the form to take the psych test
     * <p>
     * Ran when the Psych Test button is clicked
     *
     * @param event
     */
    @FXML
    private void psychTest(ActionEvent event) {

        String fxml = "/main/resources/view/PersonalityTest.fxml";

        AnchorPane root;

        try {
            FXMLLoader loader = new FXMLLoader();
            InputStream in = LuckyLanes.class.getResourceAsStream(fxml);

            loader.setBuilderFactory(new JavaFXBuilderFactory());
            loader.setLocation(LuckyLanes.class.getResource(fxml));

            try {
                root = (AnchorPane) loader.load(in);
            } finally {
                in.close();
            }

            //Stage stage = new Stage();
            preScene = stage.getScene();
            stage.setScene(new Scene(root));
            stage.show();

            PersonalityTestController newBowler = (PersonalityTestController) ((Initializable) loader.getController());
            newBowler.setId(id);
            newBowler.updateScore(id);
            System.out.println(id);
            newBowler.setStage(stage);
            newBowler.setPreScene(preScene);

            stage.setOnCloseRequest((WindowEvent we) ->
            {
                ((Stage) (stage.getScene()).getWindow()).show();
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Adds the previous scene into the object to allow the user to go back to it with the back button.
     *
     * @param pre The previous scene.
     */
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
