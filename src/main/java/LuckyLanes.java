package main.java;

import javafx.application.Application;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import main.java.controllers.AdminController;
import main.java.controllers.LoginController;

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This is the main class to start the application.
 *
 * @author Mario
 */
public class LuckyLanes extends Application {
    /**
     * The window of the application
     */
    private Stage stage;
    
    /**
     * The database used in the application
     */
    public static Database db;

    /**
     * When the application is opened, the start method is called.
     *
     * @param primaryStage Stage to open up the application in
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        /*
        testing Aerobic class
        Aerobic ar = new Aerobic(19, "male");
        ar.setBenchRating(125, "menHB.txt");
        System.out.println(ar.getBenchRating());
        */

        try {
            //Sets the data field to the primaryStage of the application.
            stage = primaryStage;

            //----------------------------------------------
            //stage.setTitle("Lucky Lanes");
            //----------------------------------------------

            /**
             * Listener to set the minimum size once the stage is shown.
             * When an application is created, the minimum size is set to the window size,
             * including the decorations or toolbar to minimize/close the window. 
             * This ignores the minimum size set to the scene. 
             *
             * Setting the minimum size after the application is created sets 
             * the minimum size equal to the minimum size set for the scene, plus 
             * the size of the decorations.
             */
            primaryStage.showingProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean showing) ->
            {
                if (showing) {
                    primaryStage.setMinHeight(primaryStage.getHeight());
                    primaryStage.setMinWidth(primaryStage.getWidth());
                }
            });

            //Replace the content of the stage.
            //gotoLogin();
            primaryStage.initStyle(StageStyle.DECORATED);
            gotoAdmin();

            //Starts the application.
            //primaryStage.show();
        } catch (Exception ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * This method changes the stage's content to show the login scene.
     */
    public void gotoLogin() {
        try {
            LoginController root = (LoginController) replaceSceneContent("/main/resources/view/Login.fxml");

            //Create listeners once the root is displayed.
            root.createListeners();

            //Send current instance of the application class to the login controller.
            root.setApp(this);

            stage.setResizable(true);
            stage.sizeToScene();
        } catch (IOException ex) {
            Logger.getLogger(LuckyLanes.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * This method changes the stage's content to show the admin scene.
     */
    public void gotoAdmin() {
        try {
            AdminController root = (AdminController) replaceSceneContent("/main/resources/view/admin.fxml");

            //LoginController root =(LoginController) replaceSceneContent("Login.fxml");
            //root.createListeners();

            //Send current instance of the application class to the login controller.
            root.setApp(this);
            root.setStage(stage);
            stage.setResizable(true);
            stage.sizeToScene();

            //root.loadDatabase();

            stage.show();
            root.loadDatabase();
        } catch (IOException ex) {
            Logger.getLogger(LuckyLanes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //LOOK AT FXML LOGIN DEMO SAMPLE IN NETBEANS

    /**
     * This method replaces the stage's content with a new scene created from
     * the fxml argument.
     * <p>
     * Note: The root of all fxml files must have an AnchorPane. This method assumes
     * an AnchorPane is used to create the new scene.
     *
     * @param fxml The path to the fxml file to display.
     * @return A new instance of the controller class associated with the fxml file.
     * @throws IOException
     */
    public Initializable replaceSceneContent(String fxml) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        InputStream in = LuckyLanes.class.getResourceAsStream(fxml);

        loader.setBuilderFactory(new JavaFXBuilderFactory());
        loader.setLocation(LuckyLanes.class.getResource(fxml));

        //loader.setController(new MainController("/main/java/controllers/adminController.java"));

        AnchorPane root;

        try {
            root = (AnchorPane) loader.load(in);
        } finally {
            in.close();
        }

        Scene scene = new Scene(root);
        stage.setScene(scene);

        return (Initializable) loader.getController();
    }

    /**
     * Creates the database and launches the JavaFX runtime and the JavaFX Application
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Database.createDatabase("~/LuckyLanes");
        launch(args);
    }

    /**
     * When the application is closed, the stop method is called.
     * Overriding this method allows to run code just before closing
     * the program..
     */
    @Override
    public void stop() {
        System.out.println("Stage is closing");
        // Save file
    }
}



