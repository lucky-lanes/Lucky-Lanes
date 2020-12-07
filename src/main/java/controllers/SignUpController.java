/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.controllers;

//import main.resources.view.*;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;

import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import main.java.LuckyLanes;


//import main.java.controllers.AuthenticationController;

/**
 * FXML Controller class
 *
 * @author Zach
 */
public class SignUpController implements Initializable {

    //Application used to go back to main scene.
    private LuckyLanes app;

    //Used for dragging window
    private double initX;
    private double initY;

    @FXML
    BorderPane root;

    @FXML
    Label lblMsgError;
    @FXML
    Button btnLogin;

    @FXML
    TextField txfUsername;

    @FXML
    PasswordField txfPassword;
    
    @FXML
    TextField createemail;   // new field for email 

    /**
     *
     */
    public void createListeners() {
        Stage stage = (Stage) root.getScene().getWindow();

        root.setOnMousePressed(new EventHandler<MouseEvent>() {

            /**
             *
             * @param me
             */
            public void handle(MouseEvent me) {
                initX = me.getScreenX() - stage.getX();
                initY = me.getScreenY() - stage.getY();
            }
        });

        //when screen is dragged, translate it accordingly
        root.setOnMouseDragged(new EventHandler<MouseEvent>() {

            /**
             *
             * @param me
             */
            public void handle(MouseEvent me) {
                stage.setX(me.getScreenX() - initX);
                stage.setY(me.getScreenY() - initY);
            }
        });
    }

    /**
     * Initializes the controller class.
     *
     * @param url URL of the database
     * @param rb Resource Bundle
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        txfUsername.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) ->
        {
            if (txfPassword.getText().equals("") || newValue.equals("")) {
                btnLogin.setDisable(true);
            } else {
                btnLogin.setDisable(false);
            }
        });

        txfPassword.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) ->
        {
            if (newValue.equals("") || txfUsername.getText().equals("")) {
                btnLogin.setDisable(true);
            } else {
                btnLogin.setDisable(false);
            }
        });

    }

    /**
     * @param app Current application
     */
    public void setApp(LuckyLanes app) {
        this.app = app;
    }

    /**
     * @param event Action event
     */
    @FXML
    private void exit(ActionEvent event) {
        Platform.exit();
    }

    /**
     * @param event Action event
     */
    @FXML
    private void authenticateUser(ActionEvent event) {
        
        boolean valid = AuthenticationController.newAccount(txfUsername.getText(), txfPassword.getText().toCharArray());
        //,"", createemail.getText()

        if (valid) {
           lblMsgError.setVisible(false);
           this.app.gotoAthleteMenu();
        }
        else {
           // pendingverif.setText(" Account creation failed, username already taken.");
             //Invalid password or username.
            lblMsgError.setText("Account creation failed, username already taken.");
            lblMsgError.setVisible(true);
        }
    }

    /**
     * @param event Action event
     */
    @FXML
    private void loginEvent(ActionEvent event) {
        this.app.gotoLogin();
    }
    
}
