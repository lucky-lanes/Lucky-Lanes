package main.java.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.java.Database;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * FXML Controller class
 * <p>
 * This is the controller for the creation of an athlete. It gives the user
 * the option to choose between a list of specific athletes.
 *
 * @author Mario
 */
public class EditQuestionsController implements Initializable {
    private Stage stage;                //The window.
    String id;
    private Scene preScene;             //The previous screens scene while using the back button.
    private Scene nextScene;            //The to be next scene.
    private double preMinHeight;        //The previous minimum screens height.
    private double preMinWidth;         //The previous minimum screens width.

    private String preTitle;            //The previous screens title.
    protected final String title = "Forms";       //The current stages title.

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @FXML
    TextField mainQuestion;
    @FXML
    TextField option1;
    @FXML
    TextField option2;
    @FXML
    TextField option3;
    @FXML
    TextField option4;
    @FXML
    CheckBox option1Value;
    @FXML
    CheckBox option2Value;
    @FXML
    CheckBox option3Value;
    @FXML
    CheckBox option4Value;
    @FXML
    CheckBox addToTest;
    @FXML
    Button Submit;
    String o1;
    String o2;
    String o3;
    String o4;
    String mainQ;
    boolean option1V = false;
    boolean option2V = false;
    boolean option3V = false;
    boolean option4V = false;
    boolean testQ = false;

    @Override
    //Use sql statement to populate ResultSet
    //Initialize all values declared above with the result test
    //If user clicks delete, erase question from db and go close tab
    //if user clicks confirm, send information to db as update
    public void initialize(URL url, ResourceBundle rb) {

    }

    /**
     * Puts things in the current window. Changes the scene to the current one.
     *
     * @param stage The window.
     */
    public void setId(String id) {
        this.id = id;
    }

    public void setFromRecord(String id) {
        setId(id);
        System.out.println(id);
        Database.connect();
        //Fix statement to work for questions
        ResultSet questionInfo = Database.searchQuery("SELECT * FROM QUESTION WHERE ID=" + this.id + ";");


        try {
            questionInfo.next();
            //System.out.println(questionInfo.getString(1));
            mainQuestion.setText(questionInfo.getString(2));
            option1.setText(questionInfo.getString(3));
            option2.setText(questionInfo.getString(5));
            option3.setText(questionInfo.getString(7));
            option4.setText(questionInfo.getString(9));
            if (questionInfo.getBoolean(4)) {
                option1Value.setSelected(true);

            }
            if (questionInfo.getBoolean(6)) {
                option2Value.setSelected(true);

            }
            if (questionInfo.getBoolean(8)) {
                option3Value.setSelected(true);

            }
            if (questionInfo.getBoolean(10)) {
                option4Value.setSelected(true);

            }
            try {
                double test = Double.parseDouble(id);
                questionInfo = Database.searchQuery("SELECT QuestionId FROM TEST WHERE QuestionId=" + test + ";");
                //questionInfo.next();
                if (questionInfo.first()) ;
                System.out.println("ADDTOTEST1");
                System.out.println(questionInfo.getInt(1));
                System.out.println("ADDTOTEST2");

                System.out.println("ADDTOTEST");
                addToTest.setSelected(true);

            } catch (Exception e) {

            }
        } catch (Exception e) {

        } finally {
            Database.close();
        }
    }

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
    public void delete(ActionEvent event) {
        Database.connect();
        //delete the question from database.
        Database.executeUpdate("DELETE FROM QUESTION WHERE ID=" + id + ";");
        Database.executeUpdate("DELETE FROM TEST WHERE ID=" + id + ";");
        //use sql statement to delete then go back
        try {
            Database.close();
            goBack();

        } catch (Exception e) {

        }
        Database.close();
    }

    @FXML
    public void confirm(ActionEvent event) {
        int id1 = Integer.parseInt(id);
        mainQ = mainQuestion.getText();
        o1 = option1.getText();
        option1V = option1Value.isSelected();
        o2 = option2.getText();
        option2V = option2Value.isSelected();
        o3 = option3.getText();
        option3V = option3Value.isSelected();
        o4 = option4.getText();
        option4V = option4Value.isSelected();
        Database.executeUpdate("UPDATE QUESTION SET Question = '" + mainQ + "'"
                + " ,Option1 = '" + o1 + "'" +
                " ,Option2 = '" + o2 + "'" +
                " ,Option3 = '" + o3 + "'" +
                " ,Option4 = '" + o4 + "'" +
                " ,Option1Value = '" + option1V + "'" +
                " ,Option2Value = '" + option2V + "'" +
                " ,Option3Value = '" + option3V + "'" +
                " ,Option4Value = '" + option4V + "'" +
                "WHERE ID=" + id);

        try {
            Database.connect();
            ResultSet questionInfo = Database.searchQuery("SELECT * FROM TEST WHERE QuestionId=" + id1 + ";");
            //questionInfo.next();  
            if (questionInfo.first()) {
                if (addToTest.isSelected()) {
                    System.out.println("Already in DB");
                    //do nothing
                    //Question already in the Database
                } else {
                    System.out.println("Delete from DB");
                    //Delete question from the Database
                    Database.executeUpdate("DELETE FROM TEST WHERE QuestionId=" + id1 + ";");
                }
            } else {
                if (addToTest.isSelected()) {
                    //question is not in the db. Add to DB
                    String sql = "INSERT INTO TEST VALUES (null, " + id1 + ");";
                    Database.executeUpdate(sql);
                } else {
                    //Question not in database
                    //Question is not on test
                    //Do nothing
                    System.out.println("Not in db, Not on test");
                }
            }
        } catch (Exception e) {
            System.out.println("BYE");
            Database.close();

        }
        try {
            goBack();
        } catch (IOException ex) {
            Logger.getLogger(EditQuestionsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

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
