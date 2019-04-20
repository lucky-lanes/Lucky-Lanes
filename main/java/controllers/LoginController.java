
package main.java.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import main.java.LuckyLanes;

/**@deprecated 
 * FXML Controller class
 *
 * This class handles the interaction from the user in the Administration scene.
 * @author Mario
 */
public class LoginController implements Initializable
{
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

    /**
     *
     */
    public void createListeners()
    {
        Stage stage = (Stage) root.getScene().getWindow();
        
        root.setOnMousePressed(new EventHandler<MouseEvent>()
        {
            
            /**
             * 
             * @param me 
             */
            public void handle(MouseEvent me)
            {
                initX = me.getScreenX() - stage.getX();
                initY = me.getScreenY() - stage.getY();
            }
        });

        //when screen is dragged, translate it accordingly
        root.setOnMouseDragged(new EventHandler<MouseEvent>()
        {
            
            /**
             * 
             * @param me 
             */
            public void handle(MouseEvent me)
            {
                stage.setX(me.getScreenX() - initX);
                stage.setY(me.getScreenY() - initY);
            }
        });
    }

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        txfUsername.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) ->
        {
            if (txfPassword.getText().equals("") || newValue.equals(""))
            {
                btnLogin.setDisable(true);
            }
            else
            {
                btnLogin.setDisable(false);
            }
        });
        
        txfPassword.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) ->
        {
            if (newValue.equals("") || txfUsername.getText().equals(""))
            {
                btnLogin.setDisable(true);
            }
            else
            {
                btnLogin.setDisable(false);
            }
        });
        
    }

    /**
     *
     * @param app
     */
    public void setApp(LuckyLanes app)
    {
        this.app = app;
    }
    
    /**
     * 
     * @param event 
     */
    @FXML
    private void exit(ActionEvent event)
    {
        Platform.exit();
    }
    
    /**
     * 
     * @param event 
     */
    @FXML
    private void authenticateUser(ActionEvent event)
    {
        if (txfPassword.getText().equals("admin") && txfUsername.getText().equals("admin"))
        {
            //Go to admin scene.
            lblMsgError.setVisible(false);
            this.app.gotoAdmin();
        }
        else
        {
            //Invalid password or username.
            lblMsgError.setText("Invalid username or password");
            lblMsgError.setVisible(true);
        }
    }
    
    /**
     * 
     * @param event 
     */
    @FXML
    private void demo(ActionEvent event)
    {
        this.app.gotoAdmin();
    }
}