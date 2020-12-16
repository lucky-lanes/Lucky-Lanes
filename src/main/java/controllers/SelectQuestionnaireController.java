/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Callback;
import main.java.Database;
import main.java.LuckyLanes;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;


/**
 *
 * @author potte
 */
public class SelectQuestionnaireController implements Initializable {
    
    
        /**
     * Table to hold the questions
     */
    @FXML
    TableView table;
    @FXML
    ProgressIndicator progressIndicator;
    private Executor exec;
    private ObservableList<ObservableList> data;
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
    protected final String title = "Select Qustionnare";

    /**
     * Initializes the controller class.
     *
     * @param url URL of the database
     * @param rb Resource bundle
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        {
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
     * Gets ids and questions from the database and populates the table.
     */
    @FXML
    public void buildData(/*ActionEvent e*/) {
        //table.getItems().clear();

        table.getColumns().clear();

        System.out.println("Running");

        // prepare SQL statement
        String SQL = "SELECT ID, TESTNAME FROM TEST";

        // grab the result set of the equation
        //ResultSet rs = Database.searchQuery(SQL);

        Task<Void> databaseQuery = new Task<Void>() {

            @Override
            public Void call() throws Exception {
                System.out.print("fasldfkj;asldfkj");
                //BEHOLD
                progressIndicator.setVisible(true);//FUCK YES!!!!

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

                                col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {

                                    /**
                                     *
                                     * @param param
                                     * @return
                                     */
                                    @Override
                                    public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList, String> param) {
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
                                    if(i == 2)
                                        row.add(rs.getString(i).substring(6));
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
                            TableColumn editCol = new TableColumn("Take");
                            editCol.setStyle("-fx-alignment: CENTER;");

                            // Create a new cellFactory to allow buttons to be created in a column
                            Callback<TableColumn<ObservableList, String>, TableCell<ObservableList, String>> cellFactory = new Callback<TableColumn<ObservableList, String>, TableCell<ObservableList, String>>() {

                                // Call tells column what should be placed in the column, in this case just a button
                                @Override
                                public TableCell<ObservableList, String> call(final TableColumn param) {
                                    final TableCell<ObservableList, String> cell = new TableCell<ObservableList, String>() {
                                        private final Button btn = new Button("Take");

                                        {
                                            // Creates an action to open a new instance of the BowlerController with inserted Values
                                            EventHandler<ActionEvent> open = e -> {
                                                ObservableList<String> tempID = getTableView().getItems().get(getIndex());
                                                editQuestion(tempID.get(1));
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
                                }

                                ;
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
     * Opens the Edit Questons window when the open button is clicked for a question
     *
     * @param id ID of the question
     */
    public void editQuestion(String id) {
        String fxml = "/main/resources/view/takeQuestionnaire.fxml";

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

            TakeQuestionnaireController newAthlete = (TakeQuestionnaireController) ((Initializable) loader.getController());
            newAthlete.setFromRecord("TEST__"+id);
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
        stage.close();
    }
}
