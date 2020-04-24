package main.java.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * FXML Controller class for showForms.fxml
 * <p>
 * This is the controller for the Forms window that comes up when from 
 * the administrator window you click the Printable Forms button
 *
 * @author Mario
 */
public class ShowFormsController implements Initializable {
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
    protected final String title = "Forms";

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
     * Open up a form. Takes file path to form
     *
     * @param fileName the file path to a form
     */
    public void handle(String fileName) {
        Desktop desktop = Desktop.getDesktop();
        File file = new File(fileName);
        try {
            desktop.open(file);
            System.out.println(fileName);
        } catch (IOException ex) {
            System.out.println("failed");
        }
    }

    /**
     * Open up the ASCI form. Ran when the ASCI button is clicked
     */
    @FXML
    private void acsi(ActionEvent event) {
        handle("./src/main/DocumentsPDF/ACSI.pdf");
    }

    /**
     * Open up the Bowler Assessment form. Ran when the Bowler Assessment button is clicked
     */
    @FXML
    private void bowlerAssessment(ActionEvent event) {
        handle("./src/main/DocumentsPDF/Bowler_Assessment.pdf");
    }

    /**
     * Open up the Bowling IQ Questions form. Ran when the Bowler IQ Questions button is clicked
     */
    @FXML
    private void bowlingQuestion(ActionEvent event) {
        handle("./src/main/DocumentsPDF/Bowling_IQ_Topics.pdf");

    }

    /**
     * Open up the MBTI form. Ran when the MBTI button is clicked
     */
    @FXML
    private void mbti(ActionEvent event) {
        handle("./src/main/DocumentsPDF/MBTI_Personality_Type_Test.pdf");
    }
    
    /**
     * Open up the IBSSN form. Ran when the IBSSN button is clicked
     */
    @FXML
    private void ibssn(ActionEvent event) {
        handle("./src/main/DocumentsPDF/IBSSN_International_Bowling_Skill_Score_Number.xlsx");
    }
    
    /**
     * Open up the Y-Balance form. Ran when the Y-Balance button is clicked
     */
    @FXML
    private void ybalance(ActionEvent event) {
        handle("./src/main/DocumentsPDF/Y_Balance_Test.pdf");
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
    }
}
