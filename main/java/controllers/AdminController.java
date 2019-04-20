
package main.java.controllers;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import main.java.Database;
import main.java.LuckyLanes;
import main.java.Trace;

/**
 * FXML Controller class
 *
 * This class handles the interaction from the user in the Administration scene.
 *
 * @author Mario
 */
public class AdminController implements Initializable
{
    private Database db;
    private LuckyLanes app;
    private Stage stage;                            //The window.
    
    private final String title = "Lucky Lanes";     //The current stages title.
    private Scene preScene;                         //The previous screens scene while using the back button.
    
    @FXML
    Button btnNewAthlete;
    @FXML
    Button btnSearch;
    
    @FXML
    Button btnTesting;
    @FXML
    ImageView imgDatabase;
    
    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        Trace.createFile();
    }
    
    /**
     * Creates an alert when the program is loaded to let the user know if a database is laoded.
     */
    private void showDatabaseAlert()
    {
        imgDatabase.setImage(new Image("/main/resources/icons/dbDisconnected.png"));
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Database Not Found.");
        alert.setHeaderText("Please create or open a database file");
        alert.setContentText("Select an option");

        ButtonType buttonTypeCreate = new ButtonType("Create");
        ButtonType buttonTypeOpen = new ButtonType("Open");

        alert.getButtonTypes().addAll(buttonTypeCreate, buttonTypeOpen);

        alert.initModality(Modality.APPLICATION_MODAL);
        alert.initOwner(stage);
        Optional<ButtonType> result = alert.showAndWait();

        if (result.get() == buttonTypeCreate)
        {
            this.createDatabase(null);
        }
        else if (result.get() == buttonTypeOpen)
        {
            this.openDatabase(null);
        }
        else
        {

        }
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
     * Puts things in the current window.
     * @param stage The window.
     */
    public void setStage(Stage stage)
    {
        this.stage = stage;
        stage.setTitle(title);
    }
    
    /**
     * Used to test the connection of a database using the properites file. 
     * If the database is loaded, an icon showing success will appear. 
     * Otherwise it will change to red "X".
     */
    public void loadDatabase()
    {
        imgDatabase.setVisible(true);
        String url = Database.loadProperties();
        
        if (url == null)
        {
            showDatabaseAlert();

        }
        else
        {
            System.out.println("Loading Database:" + url);
            
            if (Database.connect(url))
            {
                imgDatabase.setImage(new Image("/main/resources/icons/dbConnected.png"));
                btnSearch.setDisable(false);
                btnNewAthlete.setDisable(false);
            }
            else
            {
                //lblTest.setText("Couldn't connect with that url");
                
                showDatabaseAlert();
                imgDatabase.setImage(new Image("/main/resources/icons/dbDisconnected.png"));
                btnSearch.setDisable(true);
                btnNewAthlete.setDisable(true);
            }
        }
    }

    /**
     *
     * @param e
     */
    @FXML
    public void testThis(ActionEvent e)
    {

    }

    /**
     *  Creates a database in the location specified by the user. 
     * @param e
     */
    @FXML
    public void createDatabase(ActionEvent e)
    {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Database File");
        
        //fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Database file(*.db)", "*.db"));
        
        File databasePath = fileChooser.showSaveDialog(stage);
        
        if (databasePath != null)
        {
            Database.createDatabase(databasePath.getPath());
            Database.saveProperties(databasePath.getPath());
            loadDatabase();
        }

    }

    /**
     * Opens the database specified by the user. 
     * @param e
     */
    @FXML
    public void openDatabase(ActionEvent e)
    {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Database File");
        
        //   fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Database file(*.db)", "*.db"));
        
        File databasePath = fileChooser.showOpenDialog(stage);
        
        if (databasePath != null)
        {
            Database.saveProperties(databasePath.toString());
            loadDatabase();
        }
    }
    
    /**
     * This is an injected method used by JAVAFX,
     * It creates a new stage to display the form to add new athletes.
     * @param event 
     */
    @FXML
    private void showNewAthlete(ActionEvent event)
    {
        String fxml = "/main/resources/view/newAthlete.fxml";
        
        AnchorPane root;
        try
        {
            FXMLLoader loader = new FXMLLoader();
            InputStream in = LuckyLanes.class.getResourceAsStream(fxml);

            loader.setBuilderFactory(new JavaFXBuilderFactory());
            loader.setLocation(LuckyLanes.class.getResource(fxml));

            try
            {
                root = (AnchorPane) loader.load(in);
            }
            finally
            {
                in.close();
            }
        
            //Stage stage = new Stage();
            preScene = stage.getScene();
            stage.setScene(new Scene(root));
            stage.show();

            NewAthleteController newAthlete = (NewAthleteController) ((Initializable) loader.getController());
            newAthlete.setStage(stage);
            newAthlete.setPreScene(preScene);

            stage.setOnCloseRequest((WindowEvent we) ->
            {
                //((OLD)) ((Stage) (((Node) (event.getSource())).getScene().getWindow())).show(); ((OLD))
                ((Stage) (stage.getScene()).getWindow()).show();
            });
            
            // ((OLD))  Hide this current window (if this is what you want)          ((OLD))
            // ((OLD))  ((Node) (event.getSource())).getScene().getWindow().hide();  ((OLD))
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    
    /**
     * This is an injected method used by JAVAFX,
     * It creates a new stage to display the search functionality.
     * @param event 
     */
    @FXML
    private void showSearch(ActionEvent event)
    {
        String fxml = "/main/resources/view/Search.fxml";

        AnchorPane root;
        try
        {
            FXMLLoader loader = new FXMLLoader();
            InputStream in = LuckyLanes.class.getResourceAsStream(fxml);

            loader.setBuilderFactory(new JavaFXBuilderFactory());
            loader.setLocation(LuckyLanes.class.getResource(fxml));

            try
            {
                root = (AnchorPane) loader.load(in);
            }
            finally
            {
                in.close();
            }
            
            //Stage stage = new Stage();
            preScene = stage.getScene();
            stage.setTitle("Search Athletes");
            stage.setScene(new Scene(root));
            stage.show();
            
            SearchController newAthlete = (SearchController) ((Initializable) loader.getController());
            newAthlete.setStage(stage, title);
            newAthlete.setPreScene(preScene);
            
            //newAthlete.setStage(stage);

            stage.setOnCloseRequest((WindowEvent we) ->
            {
                //((OLD)) ((Stage) (((Node) (event.getSource())).getScene().getWindow())).show(); ((OLD))
                ((Stage) (stage.getScene()).getWindow()).show();
            });
            
            //((OLD)) Hide this current window (if this is what you want) ((OLD))
            //((OLD)) ((Node) (event.getSource())).getScene().getWindow().hide(); ((OLD))
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    
    @FXML
    private void testing(ActionEvent event)
    {
        ((Button) event.getSource()).setStyle("-fx-background-color:red;");
    }
    
    /**
     * FXML calls it close the application from the title bar close option.
     * Closes the application.
     */
    @FXML
    private void close()
    {
        stage.close();
    }
}
