package main.java.controllers;

import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Callback;
import main.java.Database;
import main.java.LuckyLanes;
import main.java.Report;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * FXML Controller class for Search.fxml
 * <p>
 * This is the controller for the Search Athletes page that comes up when you click on the Search Athletes button on the admin screen
 *
 * @author nicolenelson
 */
public class SearchController implements Initializable {
    /**
     * Table that holds the information on all athletes
     */
    @FXML
    TableView table;
    /**
     * Textfield under the search section for entering a name to search by
     */
    @FXML
    TextField txtName;
    /**
     * Textfield under the search section for entering an address to search by
     */
    @FXML
    TextField txtAddress;
    /**
     * Textfield under the search section for entering a state to search by
     */
    @FXML
    TextField txtState;
    /**
     * Textfield under the search section for entering a city to search by
     */
    @FXML
    TextField txtCity;
    /**
     * Textfield under the search section for entering a school to search by
     */
    @FXML
    TextField txtSchool;
    /**
     * Textfield under the search section for entering a sport to search by
     */
    @FXML
    TextField txtSport;
    /**
     * Textfield under the search section for entering a zip code to search by
     */
    @FXML
    TextField txtZip;
    /**
     * Textfield under the search section for entering an ID to search by
     */
    @FXML
    TextField txtID;
    /**
     * Submit/Refresh button under the search section on the left hand side of the screen
     */
    @FXML
    Button buttonSubmit;
    /**
     * Spinning wheel icon that appears on the screen when the submit/refresh button is clicked
     */
    @FXML
    ProgressIndicator progressIndicator;

    // variables for sql/database
    private ObservableList<ObservableList> data;
    private Executor exec;
    private ResultSet rs;
    private Report report;

    /**
     * The window.
     * <p>
     * Note from prev. semester: Used to allow the user to go back to the previous scene. Otherwise not used.
     */
    private Stage stage;
    /**
     * The previous screen's scene while using the back button
     */
    private Scene preScene;
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
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        exec = Executors.newCachedThreadPool(runnable ->
        {
            Thread t = new Thread(runnable);
            t.setDaemon(true);
            return t;
        });

        table.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        buildData();
    }

    /**
     * Method to print reports on all athletes
     * <p>
     * Ran when the Save all Reports button is clicked
     *
     * @param e
     */
    @FXML
    public void printAllReport(ActionEvent e) {
        if (table.getItems().size() == 0) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Error Saving reports");
            alert.setHeaderText("There are no reports to save");
            alert.setContentText("Search for reports and try again.");

            alert.showAndWait();
        } else {

            Report p = new Report();

            for (Object item : table.getItems()) {
                ObservableList<String> row = (ObservableList<String>) item;
                p.addID(Integer.parseInt(row.get(0)));
            }

            Task<Void> print = new Task<Void>() {

                /**
                 *
                 * @return
                 * @throws Exception
                 */
                @Override
                public Void call() throws Exception {
                    progressIndicator.setVisible(false);
                    System.out.println("Creating Objects to save");
                    p.createObjects();
                    p.toDocs();

//                    progressIndicator.setVisible(false);
                    return null;
                }
            };

            progressIndicator.progressProperty().bind(print.progressProperty());
            progressIndicator.setStyle(" -fx-progress-color: red;");
            progressIndicator.setVisible(false);
            progressIndicator.setOnMouseClicked(new EventHandler<MouseEvent>() {

                /**
                 *
                 * @param arg0
                 */
                @Override
                public void handle(MouseEvent arg0) {
                    if (progressIndicator.getProgress() == 1) {
                        progressIndicator.setVisible(false);
                    }
                }
            });

            print.setOnSucceeded(error ->
            {
                //this.rs=databaseQuery.getValue();

                progressIndicator.progressProperty().unbind();
                progressIndicator.setVisible(false);
                progressIndicator.setProgress(1);
            });

            exec.execute(print);
        }
    }

    /**
     * Method to print reports on a selected user.
     * <p>
     * Ran when the Save Selected Reports button is clicked
     *
     * @param e
     */
    @FXML
    public void printSelectedReport(ActionEvent e) {
        if (table.getSelectionModel().getSelectedItems().isEmpty()) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Error Saving reports");
            alert.setHeaderText("There are no selected reports.");
            alert.setContentText("Select at least one report and try again");

            alert.showAndWait();
        } else {
            Report p = new Report();

            for (Object item : table.getSelectionModel().getSelectedItems()) {
                ObservableList<String> row = (ObservableList<String>) item;
                p.addID(Integer.parseInt(row.get(0)));
            }

            Task<Void> print = new Task<Void>() {

                /**
                 *
                 * @return
                 * @throws Exception
                 */
                @Override
                public Void call() throws Exception {
                    progressIndicator.setVisible(false);
                    System.out.println("Creating Objets to save");
                    p.createObjects();
                    p.toDocs();
                    Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Selected Report Saved");
                    alert.setHeaderText("Selected Report Saved Successfully!");
                    alert.setContentText("Successful");

                    alert.showAndWait();
                    return null;
                }
            };

            progressIndicator.progressProperty().bind(print.progressProperty());
            progressIndicator.setStyle(" -fx-progress-color: green;");

            progressIndicator.setOnMouseClicked(new EventHandler<MouseEvent>() {

                /**
                 *
                 * @param arg0
                 */
                @Override
                public void handle(MouseEvent arg0) {
                    if (progressIndicator.getProgress() == 1) {
                        progressIndicator.setVisible(false);
                    }
                }
            });
            print.setOnSucceeded(error ->
            {
                //this.rs=databaseQuery.getValue();

                System.out.println("It's Alive!!!");
                progressIndicator.progressProperty().unbind();
                progressIndicator.setProgress(1);
            });

            exec.execute(print);
        }
    }

    /**
     * Ran when the Submit/Refresh button is clicked
     * <p>
     * Note from prev Semester: Does it need a parameter? Action Events can only be created by event handlers tied to ui elements
     *
     * @param e
     */
    @FXML
    public void buildData(/*ActionEvent e*/) {
        //table.getItems().clear();

        table.getColumns().clear();

        System.out.println("Running");

        // prepare SQL statement
        String SQL = "SELECT ID,name, address, school,age, gender FROM athlete WHERE "
                + "(UPPER(name) LIKE UPPER('%" + txtName.getText() + "%') or name is null) "
                + "and (UPPER(address) LIKE UPPER('%" + txtAddress.getText() + "%') or address is null) "
                + "and (UPPER(state) LIKE UPPER('%" + txtState.getText() + "%') or state is null) "
                + "and (UPPER(city) LIKE UPPER('%" + txtCity.getText() + "%') or city is null) "
                + "and (zip LIKE ('%" + txtZip.getText() + "%') or zip is null) "
                + "and (UPPER(school) LIKE UPPER('%" + txtSchool.getText() + "%') or school is null) "
                + "and (UPPER(primarysport) LIKE UPPER('%" + txtSport.getText() + "%') or primarysport is null) "
                + "and (ID LIKE ('%" + txtID.getText() + "') or ID is null) "; // data to grab

        // grab the result set of the equation
        //ResultSet rs = Database.searchQuery(SQL);

        Task<Void> databaseQuery = new Task<Void>() {

            /**
             *
             * @return
             * @throws Exception
             */
            @Override
            public Void call() throws Exception {
                //BEHOLD
                progressIndicator.setVisible(true);

                //Platform.runlater is used to update an UI control inside a different thread.
                Database.connect();

                ResultSet rs = Database.searchQuery(SQL);
                /*
                rs.last();
                int rsLen = rs.getRow();
                rs.beforeFirst();
                */

                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        data = FXCollections.observableArrayList();

                        try {
                            // go through the columns and add them
                            for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
                                final int j = i;
                                TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i + 1));

                                col.setCellValueFactory(new Callback<CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {

                                    /**
                                     *
                                     * @param param
                                     * @return
                                     */
                                    @Override
                                    public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {
                                        return new SimpleStringProperty(param.getValue().get(j).toString());
                                    }
                                });

                                table.getColumns().addAll(col);

                                //System.out.println("Column ["+i+"] ");
                            }

                            // add data to the observable list
                            while (rs.next()) {
                                //Iterate Row
                                ObservableList<String> row = FXCollections.observableArrayList();

                                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                                    //Iterate Column
                                    row.add(rs.getString(i));
                                }

                                //System.out.println("Row [1] added "+row );
                                data.add(row);
                            }

                            // add to the tableview
                            table.setItems(data);
                            Database.close();

                            /*
                             * Create Separate column to insert a button for each record to view more information
                             * @author JacobMatuszak
                             */
                            TableColumn editCol = new TableColumn("View/Edit");
                            editCol.setStyle("-fx-alignment: CENTER;");

                            // Create a new cellFactory to allow buttons to be created in a column
                            Callback<TableColumn<ObservableList, String>, TableCell<ObservableList, String>> cellFactory = new Callback<TableColumn<ObservableList, String>, TableCell<ObservableList, String>>() {

                                // Call tells column what should be placed in the column, in this case just a button
                                @Override
                                public TableCell<ObservableList, String> call(final TableColumn param) {
                                    final TableCell<ObservableList, String> cell = new TableCell<ObservableList, String>() {
                                        private final Button btn = new Button("Open");

                                        {
                                            // Creates an action to open a new instance of the BowlerController with inserted Values
                                            EventHandler<ActionEvent> open = e -> {
                                                ObservableList<String> tempID = getTableView().getItems().get(getIndex());
                                                editAthlete(tempID.get(0));
                                            };
                                            btn.setOnAction(open);
                                        }

                                        @Override
                                        public void updateItem(String item, boolean empty) {
                                            super.updateItem(item, empty);

                                            if (empty) {
                                                setGraphic(null);
                                            } else {
                                                setGraphic(btn);
                                            }
                                        }
                                    };

                                    return cell;
                                };
                            };

                            editCol.setCellFactory(cellFactory);

                            table.getColumns().add(editCol);
                        } catch (SQLException ex) {
                            //Logger.getLogger(SearchController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });

                return null;
            }
        };

        progressIndicator.progressProperty().bind(databaseQuery.progressProperty());

        databaseQuery.setOnFailed(error ->
        {
            //databaseQuery.getException().printStackTrace();
            //Abandon the ship.
            System.out.println("Women and kids first");     //Titanic 2 anyone?
        });

        databaseQuery.setOnSucceeded(error ->
        {
            //this.rs=databaseQuery.getValue();
            System.out.println("It's Alive!!!");
            progressIndicator.progressProperty().unbind();
            progressIndicator.setProgress(1);
            progressIndicator.setVisible(false);
        });

        // Task.getValue() gives the value returned from call()...
        //rs = widgetSearchTask.getValue())
        // start converting the result set into tableview
        exec.execute(databaseQuery);
    }

    /**
     * This is the method ran for an athlete when the Open button on their line is clicked
     * It will open up the athlete's menu
     *
     * @param id Id of an athlete
     */
    private void editAthlete(String id) {

        String fxml = "/main/resources/view/AthleteMenu.fxml";

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

            AthleteMenuController newAthlete = (AthleteMenuController) ((Initializable) loader.getController());
            newAthlete.setId(id);
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
     * Puts things in the current window. Changes the scene to the current one.
     * Sets the stage object with data so the back button can change the scene to go back.
     *
     * @param stage The window.
     */
    protected void setStage(Stage stage, String title) {
        preTitle = title;
        this.stage = stage;

        preMinHeight = stage.getMinHeight();
        preMinWidth = stage.getMinWidth();

        stage.setMinHeight(stage.getHeight());
        stage.setMinWidth(stage.getWidth());
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
