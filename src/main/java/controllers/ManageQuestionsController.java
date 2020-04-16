package main.java.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
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
 *
 * @author Mario
 */
public class ManageQuestionsController implements Initializable {
    private Stage stage;                //The window.
    private Scene prepre;
    private Scene preScene;             //The previous screens scene while using the back button.
    private Scene nextScene;            //The to be next scene.
    private double preMinHeight;        //The previous minimum screens height.
    private double preMinWidth;         //The previous minimum screens width.

    private String preTitle;            //The previous screens title.
    protected final String title = "Manage Questions";       //The current stages title.

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
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
     * @param event
     */
    //open up forms. Takes file path to form
    @FXML
    private void addQuestion(ActionEvent event) {

        String fxml = "/main/resources/view/addQuestion.fxml";

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

            AddQuestionController newAthlete = (AddQuestionController) ((Initializable) loader.getController());
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

    @FXML
    private void updateQuestion(ActionEvent event) {

        String fxml = "/main/resources/view/updateQuestion.fxml";

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

            UpdateQuestionController newAthlete = (UpdateQuestionController) ((Initializable) loader.getController());
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
    }
}
