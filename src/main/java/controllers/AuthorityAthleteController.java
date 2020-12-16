/*
 * This file is for controlling the authority of Athletes 
 */
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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.ChoiceDialog;
import static main.java.controllers.AuthenticationController.logOut;

/**
 * FXML Controller class
 *
 * @author Zach
 */
public class AuthorityAthleteController implements Initializable {

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
    
    private LuckyLanes app;

    /**
     * Initializes the controller class.
     *
     * @param url URL of the database
     * @param rb Resource bundle
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
     * This methods is to assign the user authority
     *
     * @param event
     */
    @FXML
    private void  AuthorityAssignmentInfo(ActionEvent event) throws SQLException {
        
        System.out.println(id);
        
        List<String> choices = new ArrayList<>();
        choices.add("Admin");
        choices.add("Athlete");
        choices.add("Coach");

        ChoiceDialog<String> dialog = new ChoiceDialog<>("Athlete", choices);
        dialog.setTitle("Authority Assignment Info");
       //dialog.setHeaderText("Authority Assignment:" + rsAUTHEN.toString());
       // dialog.setContentText("Choose your:");

        // get the response value.
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()){
            System.out.println("Your choice: " + result.get());
            boolean valid = AuthenticationController.changeAuthLevel(Integer.parseInt(id), result.get());
            System.out.println(valid);
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
        String fxml = "/main/resources/view/selectQuestionnaire.fxml";

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

            Stage stage = new Stage();
            //preScene = stage.getScene();
            stage.setScene(new Scene(root));
            stage.show();

            SelectQuestionnaireController newAthlete = (SelectQuestionnaireController) ((Initializable) loader.getController());
            newAthlete.setStage(stage);
            newAthlete.setPreScene(preScene);

            stage.setOnCloseRequest((WindowEvent we) ->
            {
                //((OLD)) ((Stage) (((Node) (event.getSource())).getScene().getWindow())).show(); ((OLD))
                ((Stage) (stage.getScene()).getWindow()).show();
            });

            // ((OLD))  Hide this current window (if this is what you want)          ((OLD))
            // ((OLD))  ((Node) (event.getSource())).getScene().getWindow().hide();  ((OLD))
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This is an injected method used by JAVAFX,
     * It creates a new stage to display the form to show the printable forms.
     * <p>
     * Ran when the Printable Forms button is clicked
     */
    @FXML
    private void showForms(ActionEvent event) {
        String fxml = "/main/resources/view/showForms.fxml";

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

            ShowFormsController newAthlete = (ShowFormsController) ((Initializable) loader.getController());
            newAthlete.setStage(stage);
            newAthlete.setPreScene(preScene);

            stage.setOnCloseRequest((WindowEvent we) ->
            {
                //((OLD)) ((Stage) (((Node) (event.getSource())).getScene().getWindow())).show(); ((OLD))
                ((Stage) (stage.getScene()).getWindow()).show();
            });

            // ((OLD))  Hide this current window (if this is what you want)          ((OLD))
            // ((OLD))  ((Node) (event.getSource())).getScene().getWindow().hide();  ((OLD))
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
    public void setPreScene(Scene pre) {
        preScene = pre;
    }
    
    /**
    * @param app Current application
    */
    public void setApp(LuckyLanes app) {
        this.app = app;
    }
    
    /**
     * Method called by the FXML after the user pushes the logout button.
     * It sets the scene to the login menu.
     *
     * @throws IOException
     */
    @FXML
    private void gologOut() throws IOException {
        
        logOut();
        stage.setMinHeight(preMinHeight);
        stage.setMinWidth(preMinWidth);
        stage.setScene(preScene);
        stage.sizeToScene();
        stage.setTitle(preTitle);
        stage.close();
        this.app.gotoLogin();
    }  
}
