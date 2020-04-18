package main.java.controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import main.formObjects.*;
import main.java.Database;
import main.java.scene.control.TextFieldRequired;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.Executor;
import java.util.logging.Level;
import java.util.logging.Logger;

import static javax.swing.JOptionPane.showMessageDialog;

/**
 * FXML Controller class for bowler.fxml
 * <p>
 * This class handles the interaction from the user in the bowler.fxml file.
 * <p>
 * It shows the forms needed to register a new bowler. Athlete information, FMS Score Sheet, etc.
 * <p>
 * This form pulls up when you are inside a bowler's info at their menu, and then you click View/Edit Athlete Info.
 * <p>
 * This class requires refactoring. It contains most of the data used for this program
 *
 * @author Mario
 */
public class BowlerController implements Initializable {
    @FXML
    TableView table;
    @FXML
    ProgressIndicator progressIndicator;
    private Executor exec;
    private ObservableList<ObservableList> data;

    //Root Injected Objects
    /**
     * WebView object
     */
    @FXML
    WebView webView;
    /**
     * The tab pane with the different tabs in it
     */
    @FXML
    TabPane tabPane;
    /**
     * StackPane object for the different panes
     */
    @FXML
    StackPane stackPane;
    /**
     * The button on the bottom of the page to take you back to the previous tab
     */
    @FXML
    Button btnBack;
    /**
     * The button on the bottom of the page to take you next tab
     */
    @FXML
    Button btnNext;
    /**
     * The tab for the demographics form
     */
    @FXML
    Tab tabDemographics;
    /**
     * The tab for the Y Balance Form
     */
    @FXML
    Tab tabYBalance;
    /**
     * The tab for the FMS form
     */
    @FXML
    Tab tabFMS;
    /**
     * The tab for the Fitness Data form
     */
    @FXML
    Tab tabFitnessData;
    /**
     * The tab for the Par-Q form
     */
    @FXML
    Tab tabParQ;
    /**
     * The button on the far bottom left of the page to take you back to the menu
     */
    @FXML
    Button btnSceneBack;
    /**
     * The button on the far bottom right of the page
     */
    @FXML
    Button btnFinish;

    //**************************************************
    // Bowler Information Tab Injected Objects
    //**************************************************
    /**
     * The textfield for name on the demographics tab
     */
    @FXML
    TextFieldRequired txfName;
    /**
     * The date label on the demographics tab
     */
    @FXML
    Label lblDate;
    /**
     * The Date of Birth (DOB) field on the demographics tab
     */
    @FXML
    DatePicker dpDate;
    /**
     * The textfield for address on the demographics tab
     */
    @FXML
    TextFieldRequired txfAddress;
    /**
     * The textfield for city on the demographics tab
     */
    @FXML
    TextFieldRequired txfCity;
    /**
     * The list of states to choose from on the demographics tab
     */
    @FXML
    ComboBox cbState;
    /**
     * The textfield for zip code on the demographics tab
     */
    @FXML
    TextFieldRequired txfZip;
    /**
     * The textfield for phone number on the demographics tab
     */
    @FXML
    TextFieldRequired txfPhone;
    /**
     * The textfield for school on the demographics tab
     */
    @FXML
    TextFieldRequired txfSchool;
    /**
     * The textfield for age on the demographics tab
     */
    @FXML
    TextFieldRequired txfAge;
    /**
     * The textfield for height in centimeters on the demographics tab
     */
    @FXML
    TextFieldRequired txfHeight;
    /**
     * The textfield for weight in kilograms on the demographics tab
     */
    @FXML
    TextFieldRequired txfWeight;
    /**
     * The options for gender on the demographics tab. I do not think this is used as the ToggleGroup tgGender seems to be
     * the one that is being used for gender
     */
    @FXML
    TextFieldRequired txfGender;
    /**
     * The textfield for primary position in your sport listed in the primary sport spot on the demographics tab
     */
    @FXML
    TextFieldRequired txfPrimaryPosition;
    /**
     * The textfield for primary sport on the demographics tab
     */
    @FXML
    TextFieldRequired txfPrimarySport;
    /**
     * The toggle group for choosing gender on the demographics tab
     */
    @FXML
    ToggleGroup tgGender;
    /**
     * The toggle group for choosing hand/leg dominance on the demographics tab
     */
    @FXML
    ToggleGroup dominance;
    /**
     * The button for choosing male as a gender on the demographics tab
     */
    @FXML
    RadioButton radMale;
    /**
     * The button for choosing female as a gender on the demographics tab
     */
    @FXML
    RadioButton radFemale;
    /**
     * The button for right dominance on the demographics tab
     */
    @FXML
    RadioButton radDominanceRight;
    /**
     * The button for left dominance on the demographics tab
     */
    @FXML
    RadioButton radDominanceLeft;

    //**************************************************
    // Y-Balance Test Injected Objects
    //**************************************************
    @FXML
    VBox vbYBalanceRoot;
    /**
     * The textfield for the right limb length in centimeters on the Y-Balance Test tab
     */
    @FXML
    TextFieldRequired txfRightLimbLength;
    /**
     * The textfield for the A1 measurement for the right in centimeters on the Y-Balance Test tab
     */
    @FXML
    TextFieldRequired txfA1Right;
    /**
     * The textfield for the A2 measurement for the right in centimeters on the Y-Balance Test tab
     */
    @FXML
    TextFieldRequired txfA2Right;
    /**
     * The textfield for the A3 measurement for the right in centimeters on the Y-Balance Test tab
     */
    @FXML
    TextFieldRequired txfA3Right;
    /**
     * The textfield for the A1 measurement for the left in centimeters on the Y-Balance Test tab
     */
    @FXML
    TextFieldRequired txfA1Left;
    /**
     * The textfield for the A2 measurement for the left in centimeters on the Y-Balance Test tab
     */
    @FXML
    TextFieldRequired txfA2Left;
    /**
     * The textfield for the A3 measurement for the left in centimeters on the Y-Balance Test tab
     */
    @FXML
    TextFieldRequired txfA3Left;
    /**
     * The textfield for the PM1 measurement for the right in centimeters on the Y-Balance Test tab
     */
    @FXML
    TextFieldRequired txfPM1Right;
    /**
     * The textfield for the PM2 measurement for the right in centimeters on the Y-Balance Test tab
     */
    @FXML
    TextFieldRequired txfPM2Right;
    /**
     * The textfield for the PM3 measurement for the right in centimeters on the Y-Balance Test tab
     */
    @FXML
    TextFieldRequired txfPM3Right;
    /**
     * The textfield for the PL1 measurement for the right in centimeters on the Y-Balance Test tab
     */
    @FXML
    TextFieldRequired txfPL1Right;
    /**
     * The textfield for the PL2 measurement for the right in centimeters on the Y-Balance Test tab
     */
    @FXML
    TextFieldRequired txfPL2Right;
    /**
     * The textfield for the PL3 measurement for the right in centimeters on the Y-Balance Test tab
     */
    @FXML
    TextFieldRequired txfPL3Right;
    /**
     * The textfield for the PM1 measurement for the left in centimeters on the Y-Balance Test tab
     */
    @FXML
    TextFieldRequired txfPM1Left;
    /**
     * The textfield for the PM2 measurement for the left in centimeters on the Y-Balance Test tab
     */
    @FXML
    TextFieldRequired txfPM2Left;
    /**
     * The textfield for the PM3 measurement for the left in centimeters on the Y-Balance Test tab
     */
    @FXML
    TextFieldRequired txfPM3Left;
    /**
     * The textfield for the PL1 measurement for the left in centimeters on the Y-Balance Test tab
     */
    @FXML
    TextFieldRequired txfPL1Left;
    /**
     * The textfield for the PL2 measurement for the left in centimeters on the Y-Balance Test tab
     */
    @FXML
    TextFieldRequired txfPL2Left;
    /**
     * The textfield for the PL3 measurement for the left in centimeters on the Y-Balance Test tab
     */
    @FXML
    TextFieldRequired txfPL3Left;
    /**
     * The label of A for the right on the Y-Balance Test tab
     */
    @FXML
    Label lblARight;
    /**
     * The label of A for the left on the Y-Balance Test tab
     */
    @FXML
    Label lblALeft;
    /**
     * The label of PM for the right on the Y-Balance Test tab
     */
    @FXML
    Label lblPMRight;
    /**
     * The label of PL for the right on the Y-Balance Test tab
     */
    @FXML
    Label lblPLRight;
    /**
     * The label of PM for the left on the Y-Balance Test tab
     */
    @FXML
    Label lblPMLeft;
    /**
     * The label of PL for the left on the Y-Balance Test tab
     */
    @FXML
    Label lblPLLeft;
    /**
     * The label for the difference of A on the Y-Balance Test tab
     */
    @FXML
    Label lblADif;
    /**
     * The label for the difference of PM on the Y-Balance Test tab
     */
    @FXML
    Label lblPMDif;
    /**
     * The label for the difference of PL on the Y-Balance Test tab
     */
    @FXML
    Label lblPLDif;
    /**
     * The label for the composite score of the left on the Y-Balance Test tab
     */
    @FXML
    Label lblCompositeLeft;
    /**
     * The label for the composite score of the right on the Y-Balance Test tab
     */
    @FXML
    Label lblCompositeRight;

    //**************************************************
    // Not sure if these are used? I think they might be old?
    //**************************************************
    @FXML
    TextField txtName;
    @FXML
    TextField txtAddress;
    @FXML
    TextField txtState;
    @FXML
    TextField txtCity;
    @FXML
    TextField txtSchool;
    @FXML
    TextField txtSport;
    @FXML
    TextField txtZip;
    @FXML
    TextField txtID;
    @FXML
    Button buttonSubmit;


    //**************************************************
    // FMS Score Sheet Injected Objects
    //**************************************************
    @FXML
    VBox vbFMSRoot;
    /**
     * The toggle group for Hurdle Step for the Right on the FMS Score Sheet tab. It has the options of 0-3
     */
    @FXML
    ToggleGroup tgHurdleStepR;
    /**
     * The toggle group for Hurdle Step for the Left on the FMS Score Sheet tab. It has the options of 0-3
     */
    @FXML
    ToggleGroup tgHurdleStepL;
    /**
     * The toggle group for Deep Squat on the FMS Score Sheet tab. It has the options of 0-3
     */
    @FXML
    ToggleGroup tgDeepSquat;
    /**
     * The toggle group for Inline Lounge for the Left on the FMS Score Sheet tab. It has the options of 0-3
     */
    @FXML
    ToggleGroup tgInlineLoungeL;
    /**
     * The toggle group for Inline Lounge for the Right on the FMS Score Sheet tab. It has the options of 0-3
     */
    @FXML
    ToggleGroup tgInlineLoungeR;
    /**
     * The toggle group for Shoulder Mobility for the Left on the FMS Score Sheet tab. It has the options of 0-3
     */
    @FXML
    ToggleGroup tgShoulderMobilityL;
    /**
     * The toggle group for Shoulder Mobility for the Right on the FMS Score Sheet tab. It has the options of 0-3
     */
    @FXML
    ToggleGroup tgShoulderMobilityR;
    /**
     * The toggle group for Active Straight-Leg Raise for the Left on the FMS Score Sheet tab. It has the options of 0-3
     */
    @FXML
    ToggleGroup tgActiveStraightL;
    /**
     * The toggle group for Active Straight-Leg Raise for the Right on the FMS Score Sheet tab. It has the options of 0-3
     */
    @FXML
    ToggleGroup tgActiveStraightR;
    /**
     * The toggle group for trunk stability push-up on the FMS Score Sheet tab. It has the options of - or +
     */
    @FXML
    ToggleGroup tgTrunkStability;
    /**
     * The toggle group for rotary stability for the left on the FMS Score Sheet tab. It has the options of 0-3
     */
    @FXML
    ToggleGroup tgRotaryStabilityL;
    /**
     * The toggle group for rotary stability for the right on the FMS Score Sheet tab. It has the options of 0-3
     */
    @FXML
    ToggleGroup tgRotaryStabilityR;
    /**
     * The toggle group for Shoulder Clearing for the Left on the FMS Score Sheet tab. It has the options of - or +
     */
    @FXML
    ToggleGroup tgShoulderClearingL;
    /**
     * The toggle group for Shoulder Clearing for the Right on the FMS Score Sheet tab. It has the options of - or +
     */
    @FXML
    ToggleGroup tgShoulderClearingR;
    /**
     * The toggle group for the extension clearing test on the FMS Score Sheet tab. It has the options of - or +
     */
    @FXML
    ToggleGroup tgExtensionClearing;
    /**
     * The toggle group for the flexion clearing test on the FMS Score Sheet tab. It has the options of - or +
     */
    @FXML
    ToggleGroup tgFlexionClearing;

    /**
     * The text field showing the score for the trunk stability push up on the FMS Score Sheet tab
     */
    @FXML
    TextFieldRequired txfTrunkStability;
    /**
     * The text field showing the score for the inline lounge on the FMS Score Sheet tab
     */
    @FXML
    TextFieldRequired txfInlineLounge;
    /**
     * The text field showing the score for the shoulder mobility on the FMS Score Sheet tab
     */
    @FXML
    TextFieldRequired txfShoulderMobility;
    /**
     * The text field showing the score for the active straight-leg raise on the FMS Score Sheet tab
     */
    @FXML
    TextFieldRequired txfActiveStraight;
    /**
     * The text field showing the score for the rotary stability on the FMS Score Sheet tab
     */
    @FXML
    TextFieldRequired txfRotaryStability;
    /**
     * The text field showing the score for the hurdle step on the FMS Score Sheet tab
     */
    @FXML
    TextFieldRequired txfHurdleStep;
    /**
     * The text field showing the score for the deep squat on the FMS Score Sheet tab
     */
    @FXML
    TextFieldRequired txfDeepSquat;
    /**
     * The text field showing the total FMS score on the FMS Score Sheet tab
     */
    @FXML
    TextField txfFMSTotal;

    /**
     * The text area for comments on deep squat on the FMS score on the FMS Score Sheet tab
     */
    @FXML
    TextArea txfDeepSquatComment;
    /**
     * The text area for comments on hurdle step on the FMS score on the FMS Score Sheet tab
     */
    @FXML
    TextArea txfHurdleStepComment;
    /**
     * The text area for comments on inline lounge on the FMS score on the FMS Score Sheet tab
     */
    @FXML
    TextArea txfInlineLoungeComment;
    /**
     * The text area for comments on shoulder mobility on the FMS score on the FMS Score Sheet tab
     */
    @FXML
    TextArea txfShoulderMobilityComment;
    /**
     * The text area for comments on shoulder clearing on the FMS score on the FMS Score Sheet tab
     */
    @FXML
    TextArea txfShoulderClearingComment;
    /**
     * The text area for comments on active straight-leg raise on the FMS score on the FMS Score Sheet tab
     */
    @FXML
    TextArea txfLegRaiseComment;
    /**
     * The text area for comments on trunk stability push-up on the FMS score on the FMS Score Sheet tab
     */
    @FXML
    TextArea txfTrunkStabilityComment;
    /**
     * The text area for comments on the extension clearing test on the FMS score on the FMS Score Sheet tab
     */
    @FXML
    TextArea txfExtensionClearingComment;
    /**
     * The text area for comments on rotary stability on the FMS score on the FMS Score Sheet tab
     */
    @FXML
    TextArea txfRotaryComment;
    /**
     * The text area for comments on the flexion clearing test on the FMS score on the FMS Score Sheet tab
     */
    @FXML
    TextArea txfFlexionComment;

    
    
    //**************************************************
    // Fitness Testing Data Sheet Injected Objects
    //**************************************************
    @FXML
    VBox vbFitnessTestingRoot;
    /**
     * The scroll pane for the Fitness Testing Data Sheet tab
     */
    @FXML
    ScrollPane spDataSheet;
    /**
     * The Anthropometrics drop down area on the Fitness Testing Data Sheet tab
     */
    @FXML
    VBox vbAnthroRoot;
    /**
     * The Skinfold drop down area on the Fitness Testing Data Sheet tab
     */
    @FXML
    VBox vbSkinfoldRoot;
    /**
     * The Sit & Reach drop down area on the Fitness Testing Data Sheet tab
     */
    @FXML
    VBox vbSitReachRoot;
    /**
     * The Muscle Strength & Endurance & Power drop down area on the Fitness Testing Data Sheet tab
     */
    @FXML
    VBox vbMSEPRoot;
    /**
     * The Estemated Aerobic Activity drop down area on the Fitness Testing Data Sheet tab
     */
    @FXML
    VBox vbEstAerActRoot;
    /**
     * The textfield for age on the Fitness Testing Data Sheet tab
     */
    @FXML
    TextFieldRequired txfAge2;
    /**
     * The textfield for height in centimeters on the Fitness Testing Data Sheet tab
     */
    @FXML
    TextFieldRequired txfHeight2;
    /**
     * The textfield for body weight in kilograms on the Fitness Testing Data Sheet tab
     */
    @FXML
    TextFieldRequired txfWeight2;
    /**
     * The toggle group for gender on the Fitness Testing Data Sheet tab
     */
    @FXML
    ToggleGroup gender2;
    /**
     * The button for male on the Fitness Testing Data Sheet tab
     */
    @FXML
    RadioButton radMale2;
    /**
     * The button for female on the Fitness Testing Data Sheet tab
     */
    @FXML
    RadioButton radFemale2;
    /**
     * The textfield for the resting heart rate in beats/minute (bmp) on the Fitness Testing Data Sheet tab
     */
    @FXML
    TextFieldRequired txfRestingHR;
    /**
     * The textfield for the resting bp, measured in mmHg, on the Fitness Testing Data Sheet tab. This is the part before the "/"
     */
    @FXML
    TextFieldRequired txfRestingBPA;
    /**
     * The textfield for the resting bp, measured in mmHg, on the Fitness Testing Data Sheet tab. This is the part after the "/"
     */
    @FXML
    TextFieldRequired txfRestingBPB;
    /**
     * The textfield for the BMI, measured in kg/square meter, on the Fitness Testing Data Sheet tab
     */
    @FXML
    TextField txfBMI;
    /**
     * The textfield for the first peak flow trial, measured in liters/minute, on the Fitness Testing Data Sheet tab
     */
    @FXML
    TextFieldRequired txfPeakFLow1;
    /**
     * The textfield for the second peak flow trial, measured in liters/minute, on the Fitness Testing Data Sheet tab
     */
    @FXML
    TextFieldRequired txfPeakFLow2;
    
    //Anthropometrics drop down section
    /**
     * The textfield for the waist circumference measured in centimeters on the Fitness Testing Data 
     * Sheet tab under the anthropometrics drop down section
     */
    @FXML
    TextFieldRequired txfWCirc;
    /**
     * The textfield for the hip circumference measured in centimeters on the Fitness Testing Data 
     * Sheet tab under the anthropometrics drop down section
     */
    @FXML
    TextFieldRequired txfHipCirc;
    /**
     * The textfield for the mid-thigh circumference measured in centimeters on the Fitness Testing Data 
     * Sheet tab under the anthropometrics drop down section
     */
    @FXML
    TextFieldRequired txfMidTCirc;
    /**
     * The textfield for the flexed arm circumference measured in centimeters on the Fitness Testing Data 
     * Sheet tab under the anthropometrics drop down section
     */
    @FXML
    TextFieldRequired txfFlexArmCirc;
    /**
     * The textfield for the first anterior thigh measured in millimeters on the Fitness Testing Data 
     * Sheet tab under the anthropometrics drop down section
     */
    @FXML
    TextFieldRequired txfAntThigh1;
    /**
     * The textfield for the second anterior thigh measured in millimeters on the Fitness Testing Data 
     * Sheet tab under the anthropometrics drop down section
     */
    @FXML
    TextFieldRequired txfAntThigh2;
    /**
     * The textfield for the average anterior thigh measurement in millimeters on the Fitness Testing Data 
     * Sheet tab under the anthropometrics drop down section
     */
    @FXML
    TextField txfAntThighAVG;
    /**
     * The textfield for the hamstrings CSA on the Fitness Testing Data Sheet tab under the anthropometrics drop down section
     */
    @FXML
    TextField txfHamCSA;
    /**
     * The textfield for the quadriceps CSA on the Fitness Testing Data Sheet tab under the anthropometrics drop down section
     */
    @FXML
    TextField txfQuadCSA;
    /**
     * The textfield for the total thigh CSA on the Fitness Testing Data Sheet tab under the anthropometrics drop down section
     */
    @FXML
    TextField txfTotalCSA;
    
    //Skinfold Dropdown Section
    /**
     * The textfield for the tricep on the Fitness Testing Data Sheet tab under the skinfold drop down section
     */
    @FXML
    TextFieldRequired txfTricep;
    /**
     * The textfield for the subscapular on the Fitness Testing Data Sheet tab under the skinfold drop down section
     */
    @FXML
    TextFieldRequired txfSubscapular;
    /**
     * The textfield for the abdominal on the Fitness Testing Data Sheet tab under the skinfold drop down section
     */
    @FXML
    TextFieldRequired txfAbdominal;
    /**
     * The textfield for the suprailiac on the Fitness Testing Data Sheet tab under the skinfold drop down section
     */
    @FXML
    TextFieldRequired txfSuprailiac;
    /**
     * The textfield for the thigh on the Fitness Testing Data Sheet tab under the skinfold drop down section
     */
    @FXML
    TextFieldRequired txfThigh;
    /**
     * The textfield for the pectoral on the Fitness Testing Data Sheet tab under the skinfold drop down section
     */
    @FXML
    TextFieldRequired txfPectoral;
    /**
     * The textfield for the wallsit on the Fitness Testing Data Sheet tab under the skinfold drop down section
     */
    @FXML
    TextFieldRequired txfWallsit;
    
    //Sit & Reach
    /**
     * The textfield for the start distance on the Fitness Testing Data Sheet tab under the sit & reach drop down section
     */
    @FXML
    TextFieldRequired txfStartDist;
    /**
     * The textfield for the end distance for the first trial on the Fitness Testing Data Sheet tab under the sit & reach drop down section
     */
    @FXML
    TextFieldRequired txfEndDist1;
    /**
     * The textfield for the end distance for the second trial on the Fitness Testing Data Sheet tab under the sit & reach drop down section
     */
    @FXML
    TextFieldRequired txfEndDist2;
    /**
     * The textfield for the end distance for the third trial on the Fitness Testing Data Sheet tab under the sit & reach drop down section
     */
    @FXML
    TextFieldRequired txfEndDist3;
    /**
     * The textfield for the best difference between start and one of the end distances on the Fitness Testing Data 
     * Sheet tab under the sit & reach drop down section
     */
    @FXML
    TextField txfFinalDist;
    
    //Muscle Strength & Endurance & Power drop down section
    /**
     * The textfield for the first trial of hand grip for the right hand on the Fitness Testing Data 
     * Sheet tab under the Muscle Strength & Endurance & Power drop down section
     */
    @FXML
    TextFieldRequired txfHGR1;
    /**
     * The textfield for the second trial of hand grip for the right hand on the Fitness Testing Data 
     * Sheet tab under the Muscle Strength & Endurance & Power drop down section
     */
    @FXML
    TextFieldRequired txfHGR2;
    /**
     * The textfield for the third trial of hand grip for the right hand on the Fitness Testing Data 
     * Sheet tab under the Muscle Strength & Endurance & Power drop down section
     */
    @FXML
    TextFieldRequired txfHGR3;
    /**
     * The textfield for the first trial of hand grip for the left hand on the Fitness Testing Data 
     * Sheet tab under the Muscle Strength & Endurance & Power drop down section
     */
    @FXML
    TextFieldRequired txfHGL1;
    /**
     * The textfield for the second trial of hand grip for the left hand on the Fitness Testing Data 
     * Sheet tab under the Muscle Strength & Endurance & Power drop down section
     */
    @FXML
    TextFieldRequired txfHGL2;
    /**
     * The textfield for the third trial of hand grip for the left hand on the Fitness Testing Data 
     * Sheet tab under the Muscle Strength & Endurance & Power drop down section
     */
    @FXML
    TextFieldRequired txfHGL3;
    /**
     * The button for right hand/leg dominance on the Fitness Testing Data Sheet tab under the Muscle Strength & Endurance & Power drop down section
     */
    @FXML
    RadioButton radDominance2Right;
    /**
     * The button for left hand/leg dominance on the Fitness Testing Data Sheet tab under the Muscle Strength & Endurance & Power drop down section
     */
    @FXML
    RadioButton radDominance2Left;
    /**
     * The textfield for the prone plank time on the Fitness Testing Data Sheet tab under the Muscle Strength & Endurance & Power drop down section
     */
    @FXML
    TextFieldRequired txfProneTime;
    /**
     * The textfield for the first trial of knee extension isometric force output for the right, measured in kilograms, on the Fitness 
     * Testing Data Sheet tab under the Muscle Strength & Endurance & Power drop down section
     */
    @FXML
    TextFieldRequired txfKneeExtForceR1;
    /**
     * The textfield for the second trial of knee extension isometric force output for the right, measured in kilograms, on the Fitness 
     * Testing Data Sheet tab under the Muscle Strength & Endurance & Power drop down section
     */
    @FXML
    TextFieldRequired txfKneeExtForceR2;
    /**
     * The textfield for the first trial of knee extension isometric force output for the left, measured in kilograms, on the Fitness 
     * Testing Data Sheet tab under the Muscle Strength & Endurance & Power drop down section
     */
    @FXML
    TextFieldRequired txfKneeExtForceL1;
    /**
     * The textfield for the second trial of knee extension isometric force output for the left, measured in kilograms, on the Fitness 
     * Testing Data Sheet tab under the Muscle Strength & Endurance & Power drop down section
     */
    @FXML
    TextFieldRequired txfKneeExtForceL2;
    /**
     * The textfield for the first trial of vertical jump height, measured in centimeters, on the Fitness 
     * Testing Data Sheet tab under the Muscle Strength & Endurance & Power drop down section
     */
    @FXML
    TextFieldRequired txfJH1;
    /**
     * The textfield for the second trial of vertical jump height, measured in centimeters, on the Fitness 
     * Testing Data Sheet tab under the Muscle Strength & Endurance & Power drop down section
     */
    @FXML
    TextFieldRequired txfJH2;
    /**
     * The textfield for the first trial of medicine ball chest pass, measured in meters, on the Fitness 
     * Testing Data Sheet tab under the Muscle Strength & Endurance & Power drop down section
     */
    @FXML
    TextFieldRequired txfMedPass1;
    /**
     * The textfield for the second trial of medicine ball chest pass, measured in meters, on the Fitness 
     * Testing Data Sheet tab under the Muscle Strength & Endurance & Power drop down section
     */
    @FXML
    TextFieldRequired txfMedPass2;
    
    //Estimated Aeorobic Capacity
    /**
     * The textfield for YMCA Step Test post 15s HR on the Fitness Testing Data Sheet tab under the Estimated Aerobic Capacity drop down section
     */
    @FXML
    TextFieldRequired txfPostHR;
    @FXML
    TextField txfVO2Max;
    @FXML
    TextField txfPostVO2Max;
    /**
     * The textfield for YMCA Step Test age adjusted rating on the Fitness Testing Data Sheet tab under the Estimated Aerobic Capacity drop down section
     */
    @FXML
    TextField txfAgeAdj;
    /**
     * The textfield for Rockport walk test HR after 1 mile on the Fitness Testing Data Sheet tab under the Estimated Aerobic Capacity drop down section
     */
    @FXML
    TextFieldRequired txfRockportHR;
    /**
     * The textfield for Rockport walk test walk time on the Fitness Testing Data Sheet tab under the Estimated Aerobic Capacity drop down section
     */
    @FXML
    TextFieldRequired txfRockportTime;
    /**
     * The textfield for Rockport walk test estimated VO2 max on the Fitness Testing Data Sheet tab under the Estimated Aerobic Capacity drop down section
     */
    @FXML
    TextField txfRockportVO2Max;
    /**
     * The textfield for 12-minute walk test distance covered on the Fitness Testing Data Sheet tab under the Estimated Aerobic Capacity drop down section
     */
    @FXML
    TextFieldRequired txfWalkDistance;
    /**
     * The textfield for 12-minute walk test estimated VO2 max on the Fitness Testing Data Sheet tab under the Estimated Aerobic Capacity drop down section
     */
    @FXML
    TextField txfWalkVO2;
    /**
     * Not sure where this text field is used
     */
    @FXML
    TextFieldRequired txfBicep;
    

    //**************************************************
    // Medical Survey
    //**************************************************
    /**
     * The toggle group for question 1 on the Fitness Testing Data Sheet tab
     */
    @FXML
    ToggleGroup tgParQuest1;
    /**
     * The yes toggle button for question 1 on the Fitness Testing Data Sheet tab
     */
    @FXML
    ToggleButton tbParQ1Yes;
    /**
     * The no toggle button for question 1 on the Fitness Testing Data Sheet tab
     */
    @FXML
    ToggleButton tbParQ1No;
    /**
     * The toggle group for question 2 on the Fitness Testing Data Sheet tab
     */
    @FXML
    ToggleGroup tgParQuest2;
    /**
     * The yes toggle button for question 2 on the Fitness Testing Data Sheet tab
     */
    @FXML
    ToggleButton tbParQ2Yes;
    /**
     * The no toggle button for question 1 on the Fitness Testing Data Sheet tab
     */
    @FXML
    ToggleButton tbParQ2No;
    /**
     * The toggle group for question 3 on the Fitness Testing Data Sheet tab
     */
    @FXML
    ToggleGroup tgParQuest3;
    /**
     * The yes toggle button for question 3 on the Fitness Testing Data Sheet tab
     */
    @FXML
    ToggleButton tbParQ3Yes;
    /**
     * The no toggle button for question 3 on the Fitness Testing Data Sheet tab
     */
    @FXML
    ToggleButton tbParQ3No;
    /**
     * The toggle group for question 4 on the Fitness Testing Data Sheet tab
     */
    @FXML
    ToggleGroup tgParQuest4;
    /**
     * The yes toggle button for question 4 on the Fitness Testing Data Sheet tab
     */
    @FXML
    ToggleButton tbParQ4Yes;
    /**
     * The no toggle button for question 4 on the Fitness Testing Data Sheet tab
     */
    @FXML
    ToggleButton tbParQ4No;
    /**
     * The toggle group for question 5 on the Fitness Testing Data Sheet tab
     */
    @FXML
    ToggleGroup tgParQ5;
    /**
     * The yes toggle button for question 5 on the Fitness Testing Data Sheet tab
     */
    @FXML
    ToggleButton tbParQ5Yes;
    /**
     * The no toggle button for question 5 on the Fitness Testing Data Sheet tab
     */
    @FXML
    ToggleButton tbParQ5No;
    /**
     * The toggle group for question 6 on the Fitness Testing Data Sheet tab
     */
    @FXML
    ToggleGroup tgParQuest6;
    /**
     * The yes toggle button for question 6 on the Fitness Testing Data Sheet tab
     */
    @FXML
    ToggleButton tbParQ6Yes;
    /**
     * The no toggle button for question 6 on the Fitness Testing Data Sheet tab
     */
    @FXML
    ToggleButton tbParQ6No;
    /**
     * The text area for question 7 on the Fitness Testing Data Sheet tab
     */
    @FXML
    TextArea txtParQuest7;
    /**
     * The scroll pane that the Fitness Testing Data Sheet tab items are on
     */
    @FXML
    ScrollPane scrollPane;
    
    //Other variables
    /**
     * Used to change the tabs in the GUI
     */
    SingleSelectionModel<Tab> selectionModel;
    /**
     * The database index
     */
    String DBindex;
    /**
     * The number of tabs
     */
    private int NUM_TAB = 5;
    /**
     * The window
     */
    private Stage stage;
    /**
     * Array to hold boolean values for errors
     */
    private boolean[] errors;
    /**
     * The date format used
     */
    DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
    /**
     * The date
     */
    Date date = new Date();
    /**
     * Unknown what this variable is for as it only shows up here in this document
     */
    private boolean successful;
    /**
     * Form object for the FitnessTest.
     * This is the only form object that is a class variable because the data is not being validated on input
     */
    private FitnessTest tmp;
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
     * True when the user is viewing/editing the reports from the search option. False otherwise.
     */
    private boolean viewInfo = false;
    /*
    ChangeListener<String> textFieldListener = (observable, oldValue, newValue) -> {
        
        //allow user to press the Finish button
        if (newValue != oldValue){
            System.out.println("Test");
        }

    };
    
    txfName.addListener(textFieldListener);
    
    textFieldListener.changed(null, null, textField.getText());
    */

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        addChangeListeners();
 
        /*
        WebEngine webEngine = webView.getEngine();
        URL xd =this.getClass().getResource("index.html"); 
        webEngine.load(xd.toExternalForm());
         */
        setSuccessful(false);
        errors = new boolean[NUM_TAB];

        lblDate.setText(dateFormat.format(date));
        selectionModel = tabPane.getSelectionModel();
        initializeDemographics();
        initializeYBalance();
        initializeFMS();
        initializeFitnessData();
        //Create validation regex for all TextFieldRequired
        /*
        //Name contains at least two letters.
        
        txfWeight.setValidationRegex("^[0-9]+$");
        txfHeight.setValidationRegex("^[0-9]+$");
         */

        viewInfo = false;
    }

    /*
     *  Initializes all values from existing record
     *  Designed to create a new record for an existing athlete
     *  @Author JacobMatuszak
     */
    public void setFromRecord(String id) {
        Database.connect();
        DBindex = id;
        ResultSet rsFMS = Database.searchQuery("SELECT * FROM FMS WHERE ID=" + id + ";");
        ResultSet rsAth = Database.searchQuery("SELECT * FROM Athlete WHERE ID=" + id + ";");
        ResultSet rsYBal = Database.searchQuery("SELECT * FROM YBalance WHERE ID=" + id + ";");
        ResultSet rsFitData = Database.searchQuery("SELECT * FROM FitnessData WHERE ID=" + id + ";");
        ResultSet rsParQ = Database.searchQuery("SELECT * FROM ParQ WHERE ID=" + id + ";");

        System.out.println("Fetched Data Succesfully");

        try {
            rsFMS.next();
            rsAth.next();
            rsYBal.next();
            rsFitData.next();
            rsParQ.next();

            /*
             * Following code sets athlete data
             * These values can not have errors when importing, already validated when saved to database
             * Values saved as doubles must still be fixed and implemented
             */
            txfName.setText(rsAth.getString(2));
            txfAddress.setText(rsAth.getString(5));
            txfCity.setText(rsAth.getString(6));
            if (!rsAth.getString(8).matches("0")) {
                txfZip.setText(rsAth.getString(8));
            }
            txfPhone.setText(rsAth.getString(9));
            txfPrimaryPosition.setText(rsAth.getString(18));
            txfPrimarySport.setText(rsAth.getString(17));
            if (rsAth.getString(14).matches("Male")) {
                radMale2.setSelected(true);
            } else {
                radFemale2.setSelected(true);
            }

            if (rsAth.getString(15).matches("Right")) {
                radDominance2Right.setSelected(true);
            } else {
                radDominance2Left.setSelected(true);
            }
            cbState.setValue(rsAth.getString(7));
            txfSchool.setText(rsAth.getString(10));

            // Set the date by created a LocalDate from rsAth
            if (!rsAth.getString(4).matches("00/00/0000")) {
                LocalDate date = LocalDate.parse(rsAth.getString(4), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                dpDate.setValue(date);
            }

            // Y-Balance Test Data
            // Not all values are being saved correctly
            txfRightLimbLength.setText(rsYBal.getString(2));
            txfA1Right.setText(rsYBal.getString(9));
            txfA2Right.setText(rsYBal.getString(10));
            txfA3Right.setText(rsYBal.getString(11));
            txfA1Left.setText(rsYBal.getString(12));
            txfA2Left.setText(rsYBal.getString(13));
            txfA3Left.setText(rsYBal.getString(14));
            txfPM1Right.setText(rsYBal.getString(15));
            txfPM2Right.setText(rsYBal.getString(16));
            txfPM3Right.setText(rsYBal.getString(17));
            txfPL1Right.setText(rsYBal.getString(21));
            txfPL2Right.setText(rsYBal.getString(22));
            txfPL3Right.setText(rsYBal.getString(23));
            txfPM1Left.setText(rsYBal.getString(18));
            txfPM2Left.setText(rsYBal.getString(19));
            txfPM3Left.setText(rsYBal.getString(20));
            txfPL1Left.setText(rsYBal.getString(24));
            txfPL2Left.setText(rsYBal.getString(25));
            txfPL3Left.setText(rsYBal.getString(26));
            lblARight.setText(rsYBal.getString(3));
            lblALeft.setText(rsYBal.getString(4));
            lblPMRight.setText(rsYBal.getString(5));
            lblPLRight.setText(rsYBal.getString(7));
            lblPMLeft.setText(rsYBal.getString(6));
            lblPLLeft.setText(rsYBal.getString(8));
            double PMMaxLeft = Double.parseDouble(maxValue(rsYBal.getString(18), rsYBal.getString(19), rsYBal.getString(20)));
            double PMMaxRight = Double.parseDouble(maxValue(rsYBal.getString(15), rsYBal.getString(16), rsYBal.getString(17)));
            double PLMaxLeft = Double.parseDouble(maxValue(rsYBal.getString(24), rsYBal.getString(25), rsYBal.getString(26)));
            double PLMaxRight = Double.parseDouble(maxValue(rsYBal.getString(21), rsYBal.getString(22), rsYBal.getString(23)));
            double AMaxLeft = Double.parseDouble(maxValue(rsYBal.getString(12), rsYBal.getString(13), rsYBal.getString(14)));
            double AMaxRight = Double.parseDouble(maxValue(rsYBal.getString(9), rsYBal.getString(10), rsYBal.getString(11)));
            lblADif.setText("" + (Math.max(AMaxLeft, AMaxRight) - Math.min(AMaxLeft, AMaxRight)));
            lblPMDif.setText("" + (Math.max(PMMaxLeft, PMMaxRight) - Math.min(PMMaxLeft, PMMaxRight)));
            lblPLDif.setText("" + (Math.max(PLMaxLeft, PLMaxRight) - Math.min(PLMaxLeft, PLMaxRight)));
            lblCompositeLeft.setText(rsYBal.getString(27));
            lblCompositeRight.setText(rsYBal.getString(28));

            // FMS Data
            // Comments are not being saved
            tgHurdleStepR.selectToggle(tgHurdleStepR.getToggles().get(rsFMS.getInt(5)));
            tgHurdleStepL.selectToggle(tgHurdleStepL.getToggles().get(rsFMS.getInt(4)));
            tgDeepSquat.selectToggle(tgDeepSquat.getToggles().get(rsFMS.getInt(2)));
            tgInlineLoungeL.selectToggle(tgInlineLoungeL.getToggles().get(rsFMS.getInt(7)));
            tgInlineLoungeR.selectToggle(tgInlineLoungeR.getToggles().get(rsFMS.getInt(8)));
            tgShoulderMobilityL.selectToggle(tgShoulderMobilityL.getToggles().get(rsFMS.getInt(10)));
            tgShoulderMobilityR.selectToggle(tgShoulderMobilityR.getToggles().get(rsFMS.getInt(11)));
            tgActiveStraightL.selectToggle(tgActiveStraightL.getToggles().get(rsFMS.getInt(15)));
            tgActiveStraightR.selectToggle(tgActiveStraightR.getToggles().get(rsFMS.getInt(16)));
            tgTrunkStability.selectToggle(tgTrunkStability.getToggles().get(rsFMS.getInt(18)));
            tgRotaryStabilityL.selectToggle(tgRotaryStabilityL.getToggles().get(rsFMS.getInt(21)));
            tgRotaryStabilityR.selectToggle(tgRotaryStabilityR.getToggles().get(rsFMS.getInt(22)));
            if (rsFMS.getBoolean(13)) {
                tgShoulderClearingL.selectToggle(tgShoulderClearingL.getToggles().get(1));
            } else {
                tgShoulderClearingL.selectToggle(tgShoulderClearingL.getToggles().get(0));
            }
            if (rsFMS.getBoolean(14)) {
                tgShoulderClearingR.selectToggle(tgShoulderClearingR.getToggles().get(1));
            } else {
                tgShoulderClearingR.selectToggle(tgShoulderClearingR.getToggles().get(0));
            }
            if (rsFMS.getBoolean(20)) {
                tgExtensionClearing.selectToggle(tgExtensionClearing.getToggles().get(1));
            } else {
                tgExtensionClearing.selectToggle(tgExtensionClearing.getToggles().get(0));
            }
            if (rsFMS.getBoolean(24)) {
                tgFlexionClearing.selectToggle(tgFlexionClearing.getToggles().get(1));
            } else {
                tgFlexionClearing.selectToggle(tgFlexionClearing.getToggles().get(0));
            }
            txfTrunkStability.setText(rsFMS.getString(19));
            txfInlineLounge.setText(rsFMS.getString(9));
            txfShoulderMobility.setText(rsFMS.getString(12));
            txfActiveStraight.setText(rsFMS.getString(17));
            txfRotaryStability.setText(rsFMS.getString(23));
            txfHurdleStep.setText(rsFMS.getString(4));
            txfDeepSquat.setText(rsFMS.getString(3));
            txfFMSTotal.setText(rsFMS.getString(25));

            // Fitness Testing data
            // Found data is not being saved....
            txfAge2.setText(rsFitData.getString(2));
            txfHeight2.setText(rsFitData.getString(6));
            txfWeight2.setText(rsFitData.getString(7));
            txfRestingHR.setText(rsFitData.getString(3));
            txfRestingBPA.setText(rsFitData.getString(4));
            txfRestingBPB.setText(rsFitData.getString(5));
            txfBMI.setText(rsFitData.getString(8));
            txfPeakFLow1.setText(rsFitData.getString(9));
            txfPeakFLow2.setText(rsFitData.getString(9));
            txfWCirc.setText(rsFitData.getString(14));
            txfHipCirc.setText(rsFitData.getString(15));
            txfMidTCirc.setText(rsFitData.getString(16));
            txfFlexArmCirc.setText(rsFitData.getString(17));
            txfAntThigh1.setText(rsFitData.getString(11));
            txfAntThigh2.setText(rsFitData.getString(12));
            txfAntThighAVG.setText(rsFitData.getString(13));
            txfHamCSA.setText(rsFitData.getString(18));
            txfQuadCSA.setText(rsFitData.getString(19));
            txfTotalCSA.setText(rsFitData.getString(20));
            txfStartDist.setText(rsFitData.getString(29));
            txfEndDist1.setText(rsFitData.getString(30));
            txfEndDist2.setText(rsFitData.getString(31));
            txfEndDist3.setText(rsFitData.getString(32));
            txfFinalDist.setText(rsFitData.getString(33));
            txfHGR1.setText(rsFitData.getString(34));
            txfHGR2.setText(rsFitData.getString(35));
            txfHGR3.setText(rsFitData.getString(36));
            txfHGL1.setText(rsFitData.getString(37));
            txfHGL2.setText(rsFitData.getString(38));
            txfHGL3.setText(rsFitData.getString(39));
            txfProneTime.setText(rsFitData.getString(40));
            txfKneeExtForceR1.setText(rsFitData.getString(41));
            txfKneeExtForceR2.setText(rsFitData.getString(42));
            txfKneeExtForceL1.setText(rsFitData.getString(43));
            txfKneeExtForceL2.setText(rsFitData.getString(44));
            txfJH1.setText(rsFitData.getString(45));
            txfJH2.setText(rsFitData.getString(46));
            txfMedPass1.setText(rsFitData.getString(47));
            txfMedPass2.setText(rsFitData.getString(48));
            txfPostHR.setText(rsFitData.getString(52));
            txfVO2Max.setText(rsFitData.getString(49));
            txfPostVO2Max.setText(rsFitData.getString(50));
            txfRockportHR.setText(rsFitData.getString(55));

            Double walkSplit = rsFitData.getDouble(53);
            int min = walkSplit.intValue();
            Double dSec = (walkSplit - min) * 60;
            int sec = dSec.intValue();
            String time = "";
            if (min < 10) {
                time += "0" + min + ":";
            } else {
                time += min + ":";
            }
            if (sec < 10) {
                time += "0" + sec;
            } else {
                time += sec;
            }
            txfRockportTime.setText(time);

            txfRockportVO2Max.setText(rsFitData.getString(54));
            txfWalkDistance.setText(rsFitData.getString(56));
            txfWalkVO2.setText(rsFitData.getString(57));
            txfBicep.setText(rsFitData.getString(21));
            txfTricep.setText(rsFitData.getString(22));
            txfSubscapular.setText(rsFitData.getString(23));
            txfAbdominal.setText(rsFitData.getString(24));
            txfSuprailiac.setText(rsFitData.getString(25));
            txfThigh.setText(rsFitData.getString(26));
            txfPectoral.setText(rsFitData.getString(27));
            txfWallsit.setText(rsFitData.getString(28));

            // Medical Survey
            // Always retrieves Q1 as false?
            if (rsParQ.getBoolean(2)) {
                tbParQ1Yes.setSelected(true);
            } else {
                tbParQ1No.setSelected(true);
            }
            if (rsParQ.getBoolean(3)) {
                tbParQ2Yes.setSelected(true);
            } else {
                tbParQ2No.setSelected(true);
            }
            if (rsParQ.getBoolean(4)) {
                tbParQ3Yes.setSelected(true);
            } else {
                tbParQ3No.setSelected(true);
            }
            if (rsParQ.getBoolean(5)) {
                tbParQ4Yes.setSelected(true);
            } else {
                tbParQ4No.setSelected(true);
            }
            if (rsParQ.getBoolean(6)) {
                tbParQ5Yes.setSelected(true);
            } else {
                tbParQ5No.setSelected(true);
            }
            if (rsParQ.getBoolean(7)) {
                tbParQ6Yes.setSelected(true);
            } else {
                tbParQ6No.setSelected(true);
            }
            txtParQuest7.setText(rsParQ.getString(8));

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            Database.close();
        }
    }

    /**
     * Initializes all the required fields for the demographics tabs. It sets all the regex expressions for the textfields to avoid invalid
     * input.
     */
    private void initializeDemographics() {
        ObservableList<String> options = FXCollections.observableArrayList("Alabama", "Alaska", "Arizona", "Arkansas", "California", "Colorado", "Conneticut", "Delaware", "Florida", "Georgia", "Hawaii", "Idaho", "Indiana", "Iowa", "Kansas", "Kentucky", "Lousiana", "Maine", "Maryland", "Massachusetts", "Michigan", "Minnesota", "Mississipii", "Missouri", "Nebraska", "Nevada", "New Hampshire", "New Jersey", "New Mexico", "New York", "North Carolina", "North Dakota", "Ohio", "Oklahoma", "Oregon", "Pennsylvania", "Rhode Island", "South Carolina", "South Dakota", "Tennesse", "Texas", "Utah", "Vermont", "Virginia", "Washington", "West Virginia", "Wisconsin", "Wyoming");
        cbState.getItems().setAll(options);

        //TextField Required 
        txfName.setValidation("^([a-zA-Z&.()\\-,']+[ ]*)+$",
                "Name must be at least two characters and can contain: & . ( ) - , '");
        txfAddress.setValidation("^[a-zA-Z0-9 #]{3,}$", "Address can contain only letters, numbers and #");

        txfCity.setValidation("^[a-zA-Z]+(?:[\\s-][a-zA-Z]*)*$", "City must contain only letters.");
        txfZip.setValidation("^\\d{5}$", "Zip Code must contain 5 digits.");
        txfPhone.setValidation("^(\\d{10})|(\\+\\d{1,2}\\s)?\\(?\\d{3}\\)?[\\s.-]\\d{3}[\\s.-]\\d{4}$",
                "Phone Number must be in any of the following formats: \n###-###-####\n(###) "
                        + "###-####\n### ### ####\n###.###.####\n+## (###) ###-####");
        txfSchool.setValidation(TextFieldRequired.ALPHANUMERIC, "School can contain only letters, numbers");

        txfHeight.setValidation("^[0-9]\\d*(\\.\\d+)?$", "Height must be in centimeters and can only contain digits.");
        txfWeight.setValidation("^[0-9]\\d*(\\.\\d+)?$", "Weight must be in kilograms and can only contain digits.");
        txfAge.setValidation("^[0-9]+$", "Age must be in years and can only contain digits.");

        txfPrimarySport.setValidation(TextFieldRequired.ALPHANUMERIC,
                "Primary Sport can contain any letters or numbers");
        txfPrimaryPosition.setValidation(TextFieldRequired.ALPHANUMERIC,
                "Primary position can contain any letters or numbers");

        txfAddress.setRequired(true);
        txfName.setRequired(true);
        txfAge.setRequired(true);
        txfWeight.setRequired(true);
        txfHeight.setRequired(true);
    }

    /**
     * Initializes regex expressions and all the required textfields are set to true.
     */
    private void initializeYBalance() {
        //Initialize all YBalanace textfield boxes to be required and to validate to only digits.

        for (TextFieldRequired txf : getAllTextFieldRequired(vbYBalanceRoot)) {
            txf.setValidation("^[0-9]\\d*(\\.\\d+)?$", "Measurement must be in centimeters and only contain digits.");
            txf.setRequired(true);
        }
    }

    /**
     * Initializes regex expressions and all the required textfields are set to true.
     */
    private void initializeFMS() {
        //Initialize all YBalanace textfield boxes to be required and to validate to only digits.

        for (TextFieldRequired txf : getAllTextFieldRequired(vbFMSRoot)) {
            txf.setValidation("^[0-3]{1}$", "Select raw score to calculate final score.");
            txf.setRequired(true);
        }
    }

    /**
     * @author Joshua Bolstad
     */
    private void initializeFitnessData() {
        txfAge2.setValidation("^[0-9]+$", "Age must be in years and can only contain digits.");
        txfHeight2.setValidation("^[0-9]\\d*(\\.\\d+)?$", "Height must be in centimeters and can only contain digits or decimal.");
        txfWeight2.setValidation("^[0-9]\\d*(\\.\\d+)?$", "Weight must be in kilograms and can only contain digits or decimal.");
        txfRestingHR.setValidation("^[0-9]+$", "Heart Rate must be in BPM and can only contain digits.");
        txfRestingBPA.setValidation("^[0-9]+$", "Heart Rate must be in BPM and can only contain digits.");
        txfRestingBPB.setValidation("^[0-9]+$", "Heart Rate must be in BPM and can only contain digits.");
        txfPeakFLow1.setValidation("^[0-9]\\d*(\\.\\d+)?$", "Peak Flow must be in L/min and can only contain digits or decimal.");
        txfPeakFLow2.setValidation("^[0-9]\\d*(\\.\\d+)?$", "Peak Flow must be in L/min and can only contain digits or decimal.");
        txfWCirc.setValidation("^[0-9]\\d*(\\.\\d+)?$", "Circumference must be in centimeters and can only contain digits or decimal.");
        txfHipCirc.setValidation("^[0-9]\\d*(\\.\\d+)?$", "Circumference must be in centimeters and can only contain digits or decimal.");
        txfMidTCirc.setValidation("^[0-9]\\d*(\\.\\d+)?$", "Circumference must be in centimeters and can only contain digits or decimal.");
        txfFlexArmCirc.setValidation("^[0-9]\\d*(\\.\\d+)?$", "Circumference must be in centimeters and can only contain digits or decimal.");
        txfAntThigh1.setValidation("^[0-9]\\d*(\\.\\d+)?$", "Skinfold must be in milimeters and can only contain digits or decimal.");
        txfAntThigh2.setValidation("^[0-9]\\d*(\\.\\d+)?$", "Skinfold must be in milimeters and can only contain digits or decimal.");
        txfStartDist.setValidation("^[0-9]\\d*(\\.\\d+)?$", "Distance must be in centimeters and can only contain digits or decimal.");
        txfEndDist1.setValidation("^[0-9]\\d*(\\.\\d+)?$", "Distance must be in centimeters and can only contain digits or decimal.");
        txfEndDist2.setValidation("^[0-9]\\d*(\\.\\d+)?$", "Distance must be in centimeters and can only contain digits or decimal.");
        txfEndDist3.setValidation("^[0-9]\\d*(\\.\\d+)?$", "Distance must be in centimeters and can only contain digits or decimal.");
        txfHGR1.setValidation("^[0-9]\\d*(\\.\\d+)?$", "Grip must be in kilograms and can only contain digits or decimal.");
        txfHGR2.setValidation("^[0-9]\\d*(\\.\\d+)?$", "Grip must be in kilograms and can only contain digits or decimal.");
        txfHGR3.setValidation("^[0-9]\\d*(\\.\\d+)?$", "Grip must be in kilograms and can only contain digits or decimal.");
        txfHGL1.setValidation("^[0-9]\\d*(\\.\\d+)?$", "Grip must be in kilograms and can only contain digits or decimal.");
        txfHGL2.setValidation("^[0-9]\\d*(\\.\\d+)?$", "Grip must be in kilograms and can only contain digits or decimal.");
        txfHGL3.setValidation("^[0-9]\\d*(\\.\\d+)?$", "Grip must be in kilograms and can only contain digits or decimal.");
        txfProneTime.setValidation("^[0-9]\\d*(\\.\\d+)?$", "Time must be in seconds and can only contain digits or decimal.");
        txfKneeExtForceR1.setValidation("^[0-9]\\d*(\\.\\d+)?$", "Force must be in kilograms and can only contain digits or decimal.");
        txfKneeExtForceR2.setValidation("^[0-9]\\d*(\\.\\d+)?$", "Force must be in kilograms and can only contain digits or decimal.");
        txfKneeExtForceL1.setValidation("^[0-9]\\d*(\\.\\d+)?$", "Force must be in kilograms and can only contain digits or decimal.");
        txfKneeExtForceL2.setValidation("^[0-9]\\d*(\\.\\d+)?$", "Force must be in kilograms and can only contain digits or decimal.");
        txfJH1.setValidation("^[0-9]\\d*(\\.\\d+)?$", "Jump Height must be in centimeters and can only contain digits or decimal.");
        txfJH2.setValidation("^[0-9]\\d*(\\.\\d+)?$", "Jump Height must be in centimeters and can only contain digits or decimal.");
        txfMedPass1.setValidation("^[0-9]\\d*(\\.\\d+)?$", "Chest Pass must be in meters and can only contain digits or decimal.");
        txfMedPass2.setValidation("^[0-9]\\d*(\\.\\d+)?$", "Chest Pass must be in meters and can only contain digits or decimal.");
        txfPostHR.setValidation("^[0-9]+$", "Heart Rate must be in BPM and can only contain digits.");
        txfRockportHR.setValidation("^[0-9]+$", "Heart Rate must be in BPM and can only contain digits.");
        txfRockportTime.setValidation("^([0-5]\\d):([0-5]\\d)$", "Time bust be in exact format of mm:ss and can only contain digits and colon.");
        txfWalkDistance.setValidation("^[0-9]\\d*(\\.\\d+)?$", "Distance must be in meters and can only contain digits or decimal.");
        txfBicep.setValidation("^[0-9]\\d*(\\.\\d+)?$", "Circumference must be in centimeters and can only contain digits or decimal.");
        txfTricep.setValidation("^[0-9]\\d*(\\.\\d+)?$", "Skinfold must be in milimeters and can only contain digits or decimal.");
        txfSubscapular.setValidation("^[0-9]\\d*(\\.\\d+)?$", "Skinfold must be in milimeters and can only contain digits or decimal.");
        txfAbdominal.setValidation("^[0-9]\\d*(\\.\\d+)?$", "Skinfold must be in milimeters and can only contain digits or decimal.");
        txfSuprailiac.setValidation("^[0-9]\\d*(\\.\\d+)?$", "Skinfold must be in milimeters and can only contain digits or decimal.");
        txfThigh.setValidation("^[0-9]\\d*(\\.\\d+)?$", "Skinfold must be in milimeters and can only contain digits or decimal.");
        txfPectoral.setValidation("^[0-9]\\d*(\\.\\d+)?$", "Skinfold must be in milimeters and can only contain digits or decimal.");
        txfWallsit.setValidation("^[0-9]\\d*(\\.\\d+)?$", "Wallsit must be in centimeters and can only contain digits or decimal.");

        for (TextFieldRequired txf : getAllTextFieldRequired(vbFitnessTestingRoot)) {
            txf.setRequired(true);
        }
        for (TextFieldRequired txf : getAllTextFieldRequired(vbAnthroRoot)) {
            txf.setRequired(true);
        }
        for (TextFieldRequired txf : getAllTextFieldRequired(vbSkinfoldRoot)) {
            txf.setRequired(true);
        }
        for (TextFieldRequired txf : getAllTextFieldRequired(vbSitReachRoot)) {
            txf.setRequired(true);
        }
        for (TextFieldRequired txf : getAllTextFieldRequired(vbMSEPRoot)) {
            txf.setRequired(true);
        }
        for (TextFieldRequired txf : getAllTextFieldRequired(vbEstAerActRoot)) {
            txf.setRequired(true);
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

        preMinHeight = stage.getMinHeight();
        preMinWidth = stage.getMinWidth();

        stage.setMinHeight(stage.getHeight());
        stage.setMinWidth(stage.getWidth());
    }

    /**
     * Creates event handlers to provide a better user experience: It binds properties like the title of the form to the name of the user.
     */
    public void createListeners() {
        // stage.setOnCloseRequest(null);
        //validateTabs();

        System.out.println("STAGE TITLE BEFORE BIND: " + stage.getTitle());
        stage.titleProperty().bind(txfName.textProperty());
        //stage.setTitle("createListeners");

        this.txfAge.textProperty().bindBidirectional(txfAge2.textProperty());

        txfHeight.textProperty().bindBidirectional(txfHeight2.textProperty());
        txfWeight.textProperty().bindBidirectional(txfWeight2.textProperty());

        //Y-Balance Test Bindings.
        setYBalanceHandlers();
        setFMSScoreSheetHandlers();

        radMale.selectedProperty().bindBidirectional(radMale2.selectedProperty());
        radFemale.selectedProperty().bindBidirectional(radFemale2.selectedProperty());

        radDominanceRight.selectedProperty().bindBidirectional(radDominance2Right.selectedProperty());
        radDominanceLeft.selectedProperty().bindBidirectional(radDominance2Left.selectedProperty());

        selectionModel.selectedIndexProperty().addListener((observable, oldVal, newVal) ->
        {
            validateTabs(oldVal.intValue());
        });

        stage.setOnCloseRequest((WindowEvent we) ->
        {
            //((Stage) (((Node) (event.getSource())).getScene().getWindow())).show();

            if (!isSuccessful()) {
                Alert alert;

                if (!viewInfo) {
                    alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Data wasn't created");
                } else {
                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Data wasn't edited");
                }

                alert.setHeaderText(null);
                alert.setContentText("The user cancelled the bowler addition.");

                alert.showAndWait();
            }
        });
    }

    public void addChangeListeners() {
        //For validation upon change of text
        ChangeListener<String> textFieldListener = (ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            if (isSuccessful())
                validateTabs();
        };

        /*Demographics Changes*/
        //textfields
        txfName.textProperty().addListener(textFieldListener);
        txfAddress.textProperty().addListener(textFieldListener);
        txfCity.textProperty().addListener(textFieldListener);
        txfZip.textProperty().addListener(textFieldListener);
        txfPhone.textProperty().addListener(textFieldListener);
        txfSchool.textProperty().addListener(textFieldListener);
        txfAge.textProperty().addListener(textFieldListener);
        txfHeight.textProperty().addListener(textFieldListener);
        txfWeight.textProperty().addListener(textFieldListener);
        txfPrimaryPosition.textProperty().addListener(textFieldListener);
        txfPrimarySport.textProperty().addListener(textFieldListener);
        cbState.valueProperty().addListener(textFieldListener);

        //Not yet done...
        //dpDate.valueProperty().addListener(textFieldListener);

        //detect change in radio button selection
        //tgGender.selectedToggleProperty()?    .addListener(textFieldListener);
        //dominance.selectedToggleProperty()?   .addListener(textFieldListener);

        //Y-Balance Test Changes
        txfRightLimbLength.textProperty().addListener(textFieldListener);
        txfA1Right.textProperty().addListener(textFieldListener);
        txfA2Right.textProperty().addListener(textFieldListener);
        txfA3Right.textProperty().addListener(textFieldListener);
        txfA1Left.textProperty().addListener(textFieldListener);
        txfA2Left.textProperty().addListener(textFieldListener);
        txfA3Left.textProperty().addListener(textFieldListener);
        txfPM1Right.textProperty().addListener(textFieldListener);
        txfPM2Right.textProperty().addListener(textFieldListener);
        txfPM3Right.textProperty().addListener(textFieldListener);
        txfPL1Right.textProperty().addListener(textFieldListener);
        txfPL2Right.textProperty().addListener(textFieldListener);
        txfPL3Right.textProperty().addListener(textFieldListener);
        txfPM1Left.textProperty().addListener(textFieldListener);
        txfPM2Left.textProperty().addListener(textFieldListener);
        txfPM3Left.textProperty().addListener(textFieldListener);
        txfPL1Left.textProperty().addListener(textFieldListener);
        txfPL2Left.textProperty().addListener(textFieldListener);
        txfPL3Left.textProperty().addListener(textFieldListener);

        //FMS Score Sheet Changes
        txfDeepSquat.textProperty().addListener(textFieldListener);
        txfHurdleStep.textProperty().addListener(textFieldListener);
        txfInlineLounge.textProperty().addListener(textFieldListener);
        txfShoulderMobility.textProperty().addListener(textFieldListener);
        txfTrunkStability.textProperty().addListener(textFieldListener);
        txfActiveStraight.textProperty().addListener(textFieldListener);
        txfRotaryStability.textProperty().addListener(textFieldListener);
        txfDeepSquatComment.textProperty().addListener(textFieldListener);
        txfHurdleStepComment.textProperty().addListener(textFieldListener);
        txfInlineLoungeComment.textProperty().addListener(textFieldListener);
        txfShoulderMobilityComment.textProperty().addListener(textFieldListener);
        txfShoulderClearingComment.textProperty().addListener(textFieldListener);
        txfLegRaiseComment.textProperty().addListener(textFieldListener);
        txfTrunkStabilityComment.textProperty().addListener(textFieldListener);
        txfExtensionClearingComment.textProperty().addListener(textFieldListener);
        txfRotaryComment.textProperty().addListener(textFieldListener);
        txfFlexionComment.textProperty().addListener(textFieldListener);

        //Fitness Testing Data Sheet Survey Changes
        txfAge2.textProperty().addListener(textFieldListener);
        txfHeight2.textProperty().addListener(textFieldListener);
        txfWeight2.textProperty().addListener(textFieldListener);
        txfRestingHR.textProperty().addListener(textFieldListener);
        txfRestingBPA.textProperty().addListener(textFieldListener);
        txfRestingBPB.textProperty().addListener(textFieldListener);
        txfPeakFLow1.textProperty().addListener(textFieldListener);
        txfPeakFLow2.textProperty().addListener(textFieldListener);
        txfWCirc.textProperty().addListener(textFieldListener);
        txfHipCirc.textProperty().addListener(textFieldListener);
        txfMidTCirc.textProperty().addListener(textFieldListener);
        txfFlexArmCirc.textProperty().addListener(textFieldListener);
        txfAntThigh1.textProperty().addListener(textFieldListener);
        txfAntThigh2.textProperty().addListener(textFieldListener);
        txfStartDist.textProperty().addListener(textFieldListener);
        txfEndDist1.textProperty().addListener(textFieldListener);
        txfEndDist2.textProperty().addListener(textFieldListener);
        txfEndDist3.textProperty().addListener(textFieldListener);
        txfHGR1.textProperty().addListener(textFieldListener);
        txfHGR2.textProperty().addListener(textFieldListener);
        txfHGR3.textProperty().addListener(textFieldListener);
        txfHGL1.textProperty().addListener(textFieldListener);
        txfHGL2.textProperty().addListener(textFieldListener);
        txfHGL3.textProperty().addListener(textFieldListener);
        txfProneTime.textProperty().addListener(textFieldListener);
        txfKneeExtForceR1.textProperty().addListener(textFieldListener);
        txfKneeExtForceR2.textProperty().addListener(textFieldListener);
        txfKneeExtForceL1.textProperty().addListener(textFieldListener);
        txfKneeExtForceL2.textProperty().addListener(textFieldListener);
        txfJH1.textProperty().addListener(textFieldListener);
        txfJH2.textProperty().addListener(textFieldListener);
        txfMedPass1.textProperty().addListener(textFieldListener);
        txfMedPass2.textProperty().addListener(textFieldListener);
        txfPostHR.textProperty().addListener(textFieldListener);
        txfRockportHR.textProperty().addListener(textFieldListener);
        txfRockportTime.textProperty().addListener(textFieldListener);
        txfWalkDistance.textProperty().addListener(textFieldListener);
        txfBicep.textProperty().addListener(textFieldListener);
        txfTricep.textProperty().addListener(textFieldListener);
        txfSubscapular.textProperty().addListener(textFieldListener);
        txfAbdominal.textProperty().addListener(textFieldListener);
        txfSuprailiac.textProperty().addListener(textFieldListener);
        txfThigh.textProperty().addListener(textFieldListener);
        txfPectoral.textProperty().addListener(textFieldListener);
        txfWallsit.textProperty().addListener(textFieldListener);

        //Medical Survey Changes (currently missing changes to answers of questions)
        txtParQuest7.textProperty().addListener(textFieldListener);
    }

    /**
     * GUI Handlers
     */
    @FXML
    private void nextTab(ActionEvent event) {
        //validateTabs();

        if (!validateTabs() && !errors[selectionModel.getSelectedIndex()]) {
            //Message has been shown, set error flag to true;
            errors[selectionModel.getSelectedIndex()] = true;

            if (showMissingInformationAlert()) {
                return;
            }

        }

        //Set a variable for the number of tabs in scene.
        if (selectionModel.getSelectedIndex() == NUM_TAB - 1) {
            selectionModel.select(0);
        } else {
            selectionModel.select(selectionModel.getSelectedIndex() + 1);
        }
    }

    /**
     * @param event
     */
    @FXML
    private void prevTab(ActionEvent event) {
        //validateTabs();

        if (!validateTabs() && !errors[selectionModel.getSelectedIndex()]) {
            //Message has been shown, set error flag to true;
            errors[selectionModel.getSelectedIndex()] = true;

            if (showMissingInformationAlert()) {
                return;//Consume event
            }
        }

        //Set a variable for the number of tabs in scene.
        if (selectionModel.getSelectedIndex() == 0) {
            selectionModel.select(NUM_TAB - 1);
        } else {
            selectionModel.select(selectionModel.getSelectedIndex() - 1);
        }
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
        if (!viewInfo) {
            stage.setMinHeight(preMinHeight);
            stage.setMinWidth(preMinWidth);
            stage.setScene(preScene);
            stage.sizeToScene();

            stage.titleProperty().unbind();
            stage.setTitle(preTitle);

            if (!isSuccessful()) {
                Alert alert = new Alert(AlertType.ERROR);

                alert.setTitle("Data wasn't created");
                alert.setHeaderText(null);
                alert.setContentText("The user cancelled the bowler addition.");

                alert.showAndWait();
                setSuccessful(true);
            }
        } else        //Only ran when the user is viewing/editing the reports from the search option.
        {
            if (!isSuccessful()) {
                Alert alert = new Alert(AlertType.INFORMATION);

                alert.setTitle("Data wasn't edited");
                alert.setHeaderText(null);
                alert.setContentText("The user cancelled the bowler addition.");

                alert.showAndWait();
                setSuccessful(true);
            }

            stage.close();
        }
    }

    /**
     * @return
     */
    private boolean showMissingInformationAlert() {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Missing Information");
        alert.setHeaderText("There are required fields missing.");
        alert.setContentText("Press OK to move to the next tab.");

        alert.showAndWait().ifPresent(rs ->
        {
            if (rs == ButtonType.CANCEL) {

            }
        });

        return alert.getResult() == ButtonType.CANCEL;
    }

    /**
     * Auxiliary methods
     */
    /**
     * Returns maxValue of three strings
     */
    private String maxValue(String t1, String t2, String t3) {
        try {
            double max = Math.max(Double.parseDouble(t1), Double.parseDouble(t2));
            return String.valueOf(Math.max(max, Double.parseDouble(t3)));
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * @Returns Minimum value of two strings
     */
    private String minValue(String t1, String t2) {
        try {

            return String.valueOf((int) Math.min(Double.parseDouble(t1), Double.parseDouble(t2)));
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * Returns the difference between two strings.
     */
    private String difValue(String t1, String t2) {
        try {
            return String.valueOf(Math.abs(Double.parseDouble(t1) - Double.parseDouble(t2)));
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * Returns the composite score used in YBalance between two strings.
     */
    private String compositeValue(String rightLimbLength, String t1, String t2, String t3) {
        double a, b, c, dblRightLimbLength;

        try {
            a = Double.parseDouble(t1);
            b = Double.parseDouble(t2);
            c = Double.parseDouble(t3);

            dblRightLimbLength = Double.parseDouble(rightLimbLength);
            DecimalFormat decFor = new DecimalFormat("###.##");

            //Anterior +POsteromedial+Posterolateral / 3* RIght Limb Length *100
            return "" + decFor.format(((a + b + c) / (3 * dblRightLimbLength)) * 100);
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * Event Handlers that calculate the values when the user enters any information.
     */
    private void setYBalanceHandlers() {
        //Composite Scores

        lblARight.textProperty().addListener((observer, oldVal, newVal) ->
        {
            lblCompositeRight.setText(compositeValue(txfRightLimbLength.getText(), newVal,
                    lblPMRight.getText(), lblPLRight.getText()));
        });

        lblPMRight.textProperty().addListener((observer, oldVal, newVal) ->
        {
            lblCompositeRight.setText(compositeValue(txfRightLimbLength.getText(), newVal,
                    lblARight.getText(), lblPLRight.getText()));
        });

        lblPLRight.textProperty().addListener((observer, oldVal, newVal) ->
        {
            lblCompositeRight.setText(compositeValue(txfRightLimbLength.getText(), newVal,
                    lblPMRight.getText(), lblARight.getText()));
        });

        lblALeft.textProperty().addListener((observer, oldVal, newVal) ->
        {
            lblCompositeLeft.setText(compositeValue(txfRightLimbLength.getText(), newVal,
                    lblPMLeft.getText(), lblPLLeft.getText()));
        });

        lblPMLeft.textProperty().addListener((observer, oldVal, newVal) ->
        {
            lblCompositeLeft.setText(compositeValue(txfRightLimbLength.getText(), newVal,
                    lblALeft.getText(), lblPLLeft.getText()));
        });

        lblPLLeft.textProperty().addListener((observer, oldVal, newVal) ->
        {
            lblCompositeLeft.setText(compositeValue(txfRightLimbLength.getText(), newVal,
                    lblPMLeft.getText(), lblALeft.getText()));
        });

        txfRightLimbLength.textProperty().addListener((observer, oldVal, newVal) ->
        {
            lblCompositeLeft.setText(compositeValue(newVal, lblPMLeft.getText(),
                    lblPLLeft.getText(), lblALeft.getText()));
            lblCompositeRight.setText(compositeValue(newVal, lblPMRight.getText(),
                    lblPLRight.getText(), lblARight.getText()));
        });

        /**
         * *********************************
         */
        lblARight.textProperty().addListener((observable, oldVal, newVal) ->
        {
            lblADif.setText(difValue(newVal, lblALeft.getText()));
        });

        lblALeft.textProperty().addListener((observable, oldVal, newVal) ->
        {
            lblADif.setText(difValue(newVal, lblARight.getText()));
        });

        lblPMRight.textProperty().addListener((observable, oldVal, newVal) ->
        {
            lblPMDif.setText(difValue(newVal, lblPMLeft.getText()));
        });

        lblPMLeft.textProperty().addListener((observable, oldVal, newVal) ->
        {
            lblPMDif.setText(difValue(newVal, lblPMRight.getText()));
        });

        lblPLRight.textProperty().addListener((observable, oldVal, newVal) ->
        {
            lblPLDif.setText(difValue(newVal, lblPLLeft.getText()));
        });

        lblPLLeft.textProperty().addListener((observable, oldVal, newVal) ->
        {
            lblPLDif.setText(difValue(newVal, lblPLRight.getText()));
        });

        txfA1Right.textProperty().addListener((observable, oldval, newVal) ->
        {
            lblARight.setText(maxValue(newVal, txfA2Right.getText(), txfA3Right.getText()));
        });

        txfA2Right.textProperty().addListener((observable, oldval, newVal) ->
        {
            lblARight.setText(maxValue(newVal, txfA1Right.getText(), txfA3Right.getText()));
        });

        txfA3Right.textProperty().addListener((observable, oldval, newVal) ->
        {
            lblARight.setText(maxValue(newVal, txfA2Right.getText(), txfA1Right.getText()));
        });

        txfA1Left.textProperty().addListener((observable, oldval, newVal) ->
        {
            lblALeft.setText(maxValue(newVal, txfA2Left.getText(), txfA3Left.getText()));
        });

        txfA2Left.textProperty().addListener((observable, oldval, newVal) ->
        {
            lblALeft.setText(maxValue(newVal, txfA1Left.getText(), txfA3Left.getText()));
        });

        txfA3Left.textProperty().addListener((observable, oldval, newVal) ->
        {
            lblALeft.setText(maxValue(newVal, txfA2Left.getText(), txfA1Left.getText()));
        });

        txfPM1Right.textProperty().addListener((observable, oldval, newVal) ->
        {
            lblPMRight.setText(maxValue(newVal, txfPM2Right.getText(), txfPM3Right.getText()));

        });

        txfPM2Right.textProperty().addListener((observable, oldval, newVal) ->
        {
            lblPMRight.setText(maxValue(newVal, txfPM1Right.getText(), txfPM3Right.getText()));

        });

        txfPM3Right.textProperty().addListener((observable, oldval, newVal) ->
        {
            lblPMRight.setText(maxValue(newVal, txfPM1Right.getText(), txfPM2Right.getText()));
        });

        txfPL1Right.textProperty().addListener((observable, oldval, newVal) ->
        {
            lblPLRight.setText(maxValue(newVal, txfPL2Right.getText(), txfPL3Right.getText()));

        });

        txfPL2Right.textProperty().addListener((observable, oldval, newVal) ->
        {
            lblPLRight.setText(maxValue(newVal, txfPL1Right.getText(), txfPL3Right.getText()));

        });

        txfPL3Right.textProperty().addListener((observable, oldval, newVal) ->
        {
            lblPLRight.setText(maxValue(newVal, txfPL2Right.getText(), txfPL1Right.getText()));
        });

        txfPM1Left.textProperty().addListener((observable, oldval, newVal) ->
        {
            lblPMLeft.setText(maxValue(newVal, txfPM2Left.getText(), txfPM3Left.getText()));
        });

        txfPM2Left.textProperty().addListener((observable, oldval, newVal) ->
        {
            lblPMLeft.setText(maxValue(newVal, txfPM1Left.getText(), txfPM3Left.getText()));

        });

        txfPM3Left.textProperty().addListener((observable, oldval, newVal) ->
        {
            lblPMLeft.setText(maxValue(newVal, txfPM1Left.getText(), txfPM2Left.getText()));
        });

        txfPL1Left.textProperty().addListener((observable, oldval, newVal) ->
        {
            lblPLLeft.setText(maxValue(newVal, txfPL2Left.getText(), txfPL3Left.getText()));
        });

        txfPL2Left.textProperty().addListener((observable, oldval, newVal) ->
        {
            lblPLLeft.setText(maxValue(newVal, txfPL1Left.getText(), txfPL3Left.getText()));
        });

        txfPL3Left.textProperty().addListener((observable, oldval, newVal) ->
        {
            lblPLLeft.setText(maxValue(newVal, txfPL2Left.getText(), txfPL1Left.getText()));
        });
    }

    /**
     *
     */
    private void setFMSScoreSheetHandlers() {
        for (TextFieldRequired txf : getAllTextFieldRequired(vbFMSRoot)) {
            txf.textProperty().addListener((observable, oldValue, newValue) ->
            {
                boolean flag = true;
                int sum = 0;

                for (TextFieldRequired txf2 : getAllTextFieldRequired(vbFMSRoot)) {
                    if (txf2.isValidNoEffect()) {
                        //IT has a number in it
                        sum += Integer.parseInt(txf2.getText());
                    } else {
                        return;
                    }
                }
                txfFMSTotal.setText("" + sum);
            });
        }

        /**
         * **************************************************************
         */
        tgHurdleStepR.selectedToggleProperty().addListener((ObservableValue<? extends Toggle> ov, Toggle oldToggle, Toggle newToggle) ->
        {
            if (tgHurdleStepR.getSelectedToggle() != null && tgHurdleStepL.getSelectedToggle() != null) {
                ToggleButton r = ((ToggleButton) newToggle);
                ToggleButton l = ((ToggleButton) tgHurdleStepL.getSelectedToggle());
                txfHurdleStep.setText(minValue(l.getText(), r.getText()));
            } else {
                txfHurdleStep.setText("");
            }
        });

        tgHurdleStepL.selectedToggleProperty().addListener((ObservableValue<? extends Toggle> ov, Toggle oldToggle, Toggle newToggle) ->
        {
            if (tgHurdleStepR.getSelectedToggle() != null && tgHurdleStepL.getSelectedToggle() != null) {
                ToggleButton l = ((ToggleButton) newToggle);
                ToggleButton r = ((ToggleButton) tgHurdleStepR.getSelectedToggle());
                txfHurdleStep.setText(minValue(l.getText(), r.getText()));

            } else {
                txfHurdleStep.setText("");
            }
        });

        /**
         * **************************************************************
         */
        tgDeepSquat.selectedToggleProperty().addListener((ObservableValue<? extends Toggle> ov, Toggle oldToggle, Toggle newToggle) ->
        {
            if (tgDeepSquat.getSelectedToggle() != null) {
                ToggleButton currentToggle = ((ToggleButton) newToggle);
                txfDeepSquat.setText(currentToggle.getText());
            } else {
                txfDeepSquat.setText("");
            }
        });

        /**
         * **************************************************************
         */
        tgTrunkStability.selectedToggleProperty().addListener((ObservableValue<? extends Toggle> ov, Toggle oldToggle, Toggle newToggle) ->
        {
            if (tgTrunkStability.getSelectedToggle() != null && tgExtensionClearing.getSelectedToggle() != null) {
                ToggleButton currentToggle = ((ToggleButton) newToggle);
                ToggleButton clearingL = ((ToggleButton) tgExtensionClearing.getSelectedToggle());

                if (clearingL.getText().equals("+")) {
                    txfTrunkStability.setText("0");
                } else {
                    txfTrunkStability.setText(currentToggle.getText());
                }
            } else {
                txfTrunkStability.setText("");
            }
        });

        tgExtensionClearing.selectedToggleProperty().addListener((ObservableValue<? extends Toggle> ov, Toggle oldToggle, Toggle newToggle) ->
        {
            if (tgTrunkStability.getSelectedToggle() != null && tgExtensionClearing.getSelectedToggle() != null) {
                ToggleButton clearingL = ((ToggleButton) newToggle);
                ToggleButton r = ((ToggleButton) tgTrunkStability.getSelectedToggle());

                if (clearingL.getText().equals("+")) {
                    txfTrunkStability.setText("0");
                } else {
                    txfTrunkStability.setText(r.getText());
                }
            } else {
                txfTrunkStability.setText("");
            }
        });

        /**
         * **************************************************************
         */
        tgInlineLoungeR.selectedToggleProperty().addListener((ObservableValue<? extends Toggle> ov, Toggle oldToggle, Toggle newToggle) ->
        {
            if (tgInlineLoungeR.getSelectedToggle() != null && tgInlineLoungeL.getSelectedToggle() != null) {
                ToggleButton r = ((ToggleButton) newToggle);
                ToggleButton l = ((ToggleButton) tgInlineLoungeL.getSelectedToggle());
                txfInlineLounge.setText(minValue(l.getText(), r.getText()));
            } else {
                txfInlineLounge.setText("");
            }
        });

        tgInlineLoungeL.selectedToggleProperty().addListener((ObservableValue<? extends Toggle> ov, Toggle oldToggle, Toggle newToggle) ->
        {
            if (tgInlineLoungeR.getSelectedToggle() != null && tgInlineLoungeL.getSelectedToggle() != null) {
                ToggleButton l = ((ToggleButton) newToggle);
                ToggleButton r = ((ToggleButton) tgInlineLoungeR.getSelectedToggle());
                txfInlineLounge.setText(minValue(l.getText(), r.getText()));

            } else {
                txfInlineLounge.setText("");
            }
        });

        /**
         * **************************************************************
         */
        tgShoulderMobilityR.selectedToggleProperty().addListener((ObservableValue<? extends Toggle> ov, Toggle oldToggle, Toggle newToggle) ->
        {
            if (tgShoulderMobilityR.getSelectedToggle() != null && tgShoulderMobilityL.getSelectedToggle()
                    != null && tgShoulderClearingL.getSelectedToggle()
                    != null && tgShoulderClearingR.getSelectedToggle() != null) {
                ToggleButton r = ((ToggleButton) newToggle);
                ToggleButton l = ((ToggleButton) tgShoulderMobilityL.getSelectedToggle());

                ToggleButton clearingL = ((ToggleButton) tgShoulderClearingL.getSelectedToggle());
                ToggleButton clearingR = ((ToggleButton) tgShoulderClearingR.getSelectedToggle());

                if (clearingL.getText().equals("+") || clearingR.getText().equals("+")) {
                    txfShoulderMobility.setText("0");
                } else {
                    txfShoulderMobility.setText(minValue(l.getText(), r.getText()));
                }

            } else {
                txfShoulderMobility.setText("");
            }
        });

        tgShoulderClearingL.selectedToggleProperty().addListener((ObservableValue<? extends Toggle> ov, Toggle oldToggle, Toggle newToggle) ->
        {
            if (tgShoulderMobilityR.getSelectedToggle() != null && tgShoulderMobilityL.getSelectedToggle()
                    != null && tgShoulderClearingL.getSelectedToggle()
                    != null && tgShoulderClearingR.getSelectedToggle() != null) {
                ToggleButton clearingL = ((ToggleButton) newToggle);
                ToggleButton r = ((ToggleButton) tgShoulderMobilityR.getSelectedToggle());
                ToggleButton l = ((ToggleButton) tgShoulderMobilityL.getSelectedToggle());

                if (clearingL.getText().equals("+")) {
                    txfShoulderMobility.setText("0");
                } else {
                    txfShoulderMobility.setText(minValue(l.getText(), r.getText()));
                }
            } else {
                txfShoulderMobility.setText("");
            }
        });

        tgShoulderClearingR.selectedToggleProperty().addListener((ObservableValue<? extends Toggle> ov, Toggle oldToggle, Toggle newToggle) ->
        {
            if (tgShoulderMobilityR.getSelectedToggle() != null && tgShoulderMobilityL.getSelectedToggle()
                    != null && tgShoulderClearingL.getSelectedToggle()
                    != null && tgShoulderClearingR.getSelectedToggle() != null) {

                ToggleButton clearingR = ((ToggleButton) newToggle);
                ToggleButton r = ((ToggleButton) tgShoulderMobilityR.getSelectedToggle());
                ToggleButton l = ((ToggleButton) tgShoulderMobilityL.getSelectedToggle());

                if (clearingR.getText().equals("+")) {
                    txfShoulderMobility.setText("0");
                } else {
                    txfShoulderMobility.setText(minValue(l.getText(), r.getText()));
                }
            } else {
                txfShoulderMobility.setText("");
            }
        });

        tgShoulderMobilityL.selectedToggleProperty().addListener((ObservableValue<? extends Toggle> ov, Toggle oldToggle, Toggle newToggle) ->
        {
            if (tgShoulderMobilityR.getSelectedToggle() != null && tgShoulderMobilityL.getSelectedToggle()
                    != null && tgShoulderClearingL.getSelectedToggle()
                    != null && tgShoulderClearingR.getSelectedToggle() != null) {
                ToggleButton l = ((ToggleButton) newToggle);
                ToggleButton r = ((ToggleButton) tgShoulderMobilityR.getSelectedToggle());

                ToggleButton clearingL = ((ToggleButton) tgShoulderClearingL.getSelectedToggle());
                ToggleButton clearingR = ((ToggleButton) tgShoulderClearingR.getSelectedToggle());

                if (clearingL.getText().equals("+") || clearingR.getText().equals("+")) {
                    txfShoulderMobility.setText("0");
                } else {
                    txfShoulderMobility.setText(minValue(l.getText(), r.getText()));
                }
            } else {
                txfShoulderMobility.setText("");
            }
        });

        /**
         * **************************************************************
         */
        tgActiveStraightR.selectedToggleProperty().addListener((ObservableValue<? extends Toggle> ov, Toggle oldToggle, Toggle newToggle) ->
        {
            if (tgActiveStraightR.getSelectedToggle() != null && tgActiveStraightL.getSelectedToggle() != null) {
                ToggleButton r = ((ToggleButton) newToggle);
                ToggleButton l = ((ToggleButton) tgActiveStraightL.getSelectedToggle());
                txfActiveStraight.setText(minValue(l.getText(), r.getText()));
            } else {
                txfActiveStraight.setText("");
            }
        });

        tgActiveStraightL.selectedToggleProperty().addListener((ObservableValue<? extends Toggle> ov, Toggle oldToggle, Toggle newToggle) ->
        {
            if (tgActiveStraightR.getSelectedToggle() != null && tgActiveStraightL.getSelectedToggle() != null) {
                ToggleButton l = ((ToggleButton) newToggle);
                ToggleButton r = ((ToggleButton) tgActiveStraightR.getSelectedToggle());
                txfActiveStraight.setText(minValue(l.getText(), r.getText()));
            } else {
                txfActiveStraight.setText("");
            }
        });

        /**
         * **************************************************************
         */
        tgRotaryStabilityR.selectedToggleProperty().addListener((ObservableValue<? extends Toggle> ov, Toggle oldToggle, Toggle newToggle) ->
        {
            if (tgRotaryStabilityR.getSelectedToggle() != null && tgRotaryStabilityL.getSelectedToggle()
                    != null && tgFlexionClearing.getSelectedToggle() != null) {
                ToggleButton r = ((ToggleButton) newToggle);
                ToggleButton l = ((ToggleButton) tgRotaryStabilityL.getSelectedToggle());
                ToggleButton clearing = ((ToggleButton) tgFlexionClearing.getSelectedToggle());

                if (clearing.getText().equals("+")) {
                    txfRotaryStability.setText("0");
                } else {
                    txfRotaryStability.setText(minValue(l.getText(), r.getText()));
                }

            } else {
                txfRotaryStability.setText("");
            }
        });

        tgRotaryStabilityL.selectedToggleProperty().addListener((ObservableValue<? extends Toggle> ov, Toggle oldToggle, Toggle newToggle) ->
        {
            if (tgRotaryStabilityR.getSelectedToggle() != null && tgRotaryStabilityL.getSelectedToggle()
                    != null && tgFlexionClearing.getSelectedToggle() != null) {
                ToggleButton l = ((ToggleButton) newToggle);
                ToggleButton r = ((ToggleButton) tgRotaryStabilityR.getSelectedToggle());
                ToggleButton clearing = ((ToggleButton) tgFlexionClearing.getSelectedToggle());

                if (clearing.getText().equals("+")) {
                    txfRotaryStability.setText("0");
                } else {
                    txfRotaryStability.setText(minValue(l.getText(), r.getText()));
                }
            } else {
                txfRotaryStability.setText("");
            }
        });

        tgFlexionClearing.selectedToggleProperty().addListener((ObservableValue<? extends Toggle> ov, Toggle oldToggle, Toggle newToggle) ->
        {
            if (tgRotaryStabilityR.getSelectedToggle() != null && tgRotaryStabilityL.getSelectedToggle()
                    != null && tgFlexionClearing.getSelectedToggle() != null) {
                ToggleButton clearing = ((ToggleButton) newToggle);
                ToggleButton r = ((ToggleButton) tgRotaryStabilityR.getSelectedToggle());
                ToggleButton l = ((ToggleButton) tgRotaryStabilityL.getSelectedToggle());

                if (clearing.getText().equals("+")) {
                    txfRotaryStability.setText("0");
                } else {
                    txfRotaryStability.setText(minValue(l.getText(), r.getText()));
                }
            } else {
                txfRotaryStability.setText("");
            }
        });
    }

    /**
     * @param root
     * @return
     */
    public static ArrayList<TextFieldRequired> getAllTextFieldRequired(Parent root) {
        ArrayList<TextFieldRequired> nodes = new ArrayList<>();
        addAllTextFIeldRequired(root, nodes);

        return nodes;
    }

    /**
     * @param parent
     * @param nodes
     */
    private static void addAllTextFIeldRequired(Parent parent, ArrayList<TextFieldRequired> nodes) {
        for (Node node : parent.getChildrenUnmodifiable()) {
            if (node instanceof TextFieldRequired) {
                nodes.add((TextFieldRequired) node);
            } else if (node instanceof Pane) {
                addAllTextFIeldRequired((Parent) node, nodes);
            }
        }
    }

    /**
     * Validates allt he textfields in the tab and returns true ifk if the information entered is correct.
     *
     * @return
     */
    private boolean validateDemographics() {
        boolean flag = true;
        Parent root = (Parent) tabDemographics.getContent();

        for (TextFieldRequired txf : getAllTextFieldRequired(root)) {
            if (!txf.isValid()) {
                flag = false;
            }
        }
        if (!radMale.isSelected() && !radFemale.isSelected()) {
            flag = false;
        }

        if (flag) {
            //No errors
            tabDemographics.getStyleClass().clear();
            tabDemographics.getStyleClass().addAll("tab-valid", "tab");
        } else {
            //Errors
            tabDemographics.getStyleClass().clear();
            tabDemographics.getStyleClass().addAll("tab-invalid", "tab");
        }

        //IsValid activates the color effect. They all have to be called.
        //txfName.isValid();
        //txfAddress.isValid();
        //txfWeight.isValid();
        //txfHeight.isValid();

        //return txfName.isValid() && txfAddress.isValid() && txfWeight.isValid() && txfHeight.isValid();

        return flag;
    }

    /**
     * Validates allt he textfields in the tab and returns true ifk if the information entered is correct.
     *
     * @return
     */
    private boolean validateTabYBalanceTest() {
        boolean flag = true;

        for (TextFieldRequired txf : getAllTextFieldRequired(vbYBalanceRoot)) {
            if (!txf.isValid()) {
                flag = false;
            }
        }

        if (flag) {
            //No errors
            tabYBalance.getStyleClass().clear();
            tabYBalance.getStyleClass().addAll("tab-valid", "tab");
        } else {
            //Errors
            tabYBalance.getStyleClass().clear();
            tabYBalance.getStyleClass().addAll("tab-invalid", "tab");
        }

        //Validate fields in tab 1 and set errors flag 0 to true if any errors are found.
        /* if (txfDeepSquat.getText().equals("")) {
            
            //Red shadow effect
            txfDeepSquat.setStyle("-fx-effect: dropshadow( gaussian , rgba(212, 27, 27,1) , 10,0,0,0 );");
            //txfAddress.setStyle("-fx-effect: dropshadow( gaussian , rgba(212, 27, 27,1) , 10,0,0,0 );");

            return false;
        }*/
        return flag;
    }

    /**
     * Validates all the textfields in the tab and returns true if the information entered is correct.
     *
     * @return
     */
    private boolean validateFMS() {
        boolean flag = true;

        for (TextFieldRequired txf : getAllTextFieldRequired(vbFMSRoot)) {
            if (!txf.isValid()) {
                flag = false;
            }
        }

        if (flag) {
            //No errors
            tabFMS.getStyleClass().clear();
            tabFMS.getStyleClass().addAll("tab-valid", "tab");
        } else {
            //Errors
            tabFMS.getStyleClass().clear();
            tabFMS.getStyleClass().addAll("tab-invalid", "tab");
        }

        return flag;
    }

    /**
     * @return
     * @author Joshua Bolstad
     */
    private boolean validateTabFitnessData() {
        Parent root = (Parent) tabFitnessData.getContent();
        boolean flag = true;

        for (TextFieldRequired txf : getAllTextFieldRequired(root)) {
            if (!txf.isValid()) {
                flag = false;
            }
        }
        for (TextFieldRequired txf : getAllTextFieldRequired(vbAnthroRoot)) {
            if (!txf.isValid()) {
                flag = false;
            }
        }
        for (TextFieldRequired txf : getAllTextFieldRequired(vbSkinfoldRoot)) {
            if (!txf.isValid()) {
                flag = false;
            }
        }
        for (TextFieldRequired txf : getAllTextFieldRequired(vbSitReachRoot)) {
            if (!txf.isValid()) {
                flag = false;
            }
        }
        for (TextFieldRequired txf : getAllTextFieldRequired(vbMSEPRoot)) {
            if (!txf.isValid()) {
                flag = false;
            }
        }
        for (TextFieldRequired txf : getAllTextFieldRequired(vbEstAerActRoot)) {
            if (!txf.isValid()) {
                flag = false;
            }
        }

        if (flag) {
            //No errors
            tabFitnessData.getStyleClass().clear();
            tabFitnessData.getStyleClass().addAll("tab-valid", "tab");
        } else {
            //Errors
            tabFitnessData.getStyleClass().clear();
            tabFitnessData.getStyleClass().addAll("tab-invalid", "tab");
        }

        return flag;
    }

    /**
     * @return
     * @author Joshua Bolstad
     */
    private boolean validateTabParQ() {
        boolean flag = true;

        // Question 1 check
        if (!tbParQ1Yes.isSelected() && !tbParQ1No.isSelected()) {
            flag = false;
        }

        // Question 2 check
        if (!tbParQ2Yes.isSelected() && !tbParQ2No.isSelected()) {
            flag = false;
        }

        // Question 3 check
        if (!tbParQ3Yes.isSelected() && !tbParQ3No.isSelected()) {
            flag = false;
        }

        // Question 4 check
        if (!tbParQ4Yes.isSelected() && !tbParQ4No.isSelected()) {
            flag = false;
        }

        // Question 5 check
        if (!tbParQ5Yes.isSelected() && !tbParQ5No.isSelected()) {
            flag = false;
        }

        // Question 6 check
        if (!tbParQ6Yes.isSelected() && !tbParQ6No.isSelected()) {
            flag = false;
        }

        // Question 7 check
        if (txtParQuest7.getText().equals("")) {
            flag = false;
        }

        if (flag) {
            //No errors
            tabParQ.getStyleClass().clear();
            tabParQ.getStyleClass().addAll("tab-valid", "tab");
        } else {
            //Errors
            tabParQ.getStyleClass().clear();
            tabParQ.getStyleClass().addAll("tab-invalid", "tab");
        }

        return flag;
    }

    private boolean validateTabs(int index) {
        if (index == -1) {
            index = selectionModel.getSelectedIndex();
        }

        boolean flag = false;

        switch (index) {
            case 0:
                flag = validateDemographics();
                break;
            case 1:
                flag = validateTabYBalanceTest();
                break;
            case 2:
                flag = validateFMS();
                break;
            case 3:
                flag = validateTabFitnessData();
                break;
            case 4:
                flag = validateTabParQ();
                break;
            case 5:
                flag = true;
                break;
            case 6:
                break;
        }

        if (flag) {
            //No errors
            selectionModel.getSelectedItem().getStyleClass().clear();
            selectionModel.getSelectedItem().getStyleClass().addAll("tab-valid", "tab");
        } else {
            //Errors
            selectionModel.getSelectedItem().getStyleClass().clear();
            selectionModel.getSelectedItem().getStyleClass().addAll("tab-invalid", "tab");
        }

        return flag;
    }

    /**
     * @return
     */
    private boolean validateTabs() {

        if (validateDemographics() && validateTabYBalanceTest() && validateFMS() && validateTabFitnessData() && validateTabParQ()) {
            btnFinish.setDisable(false);
            return true;
        }

        btnFinish.setDisable(true);

        return false;
    }

    /**
     * @param e
     */
    @FXML
    private void finishDataCollection(ActionEvent e) {
        try {
            Database.connect();
            ResultSet rs = Database.searchQuery("SELECT ID FROM athlete WHERE name = " + "'" + txfName.getText() + "'");

            rs.absolute(1);

            //DBindex = rs.getInt(1);
            System.out.println("INDEX = " + DBindex);
            Database.close();

            createAthlete();
            createFMS();
            createYBalance();
            createParQ();

            if (tmp == null) {
                createFitnessData(null);
            }

            tmp.addRow(viewInfo, DBindex);
            System.out.println("Inserted Objects to tables");
            setSuccessful(true);

            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Data was created");
            alert.setHeaderText(null);
            alert.setContentText("Bowler was added to the database.");

            Optional<ButtonType> result = alert.showAndWait();

            if (!result.isPresent() || result.get() == ButtonType.OK) {
                try {
                    goBack();
                } catch (Exception ex) {

                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(BowlerController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * @param e
     */
    @FXML
    private void testingData(ActionEvent e) throws SQLException {

        String height = "";
        String weight = "";
        String age = "";
        int h = 0;
        int w = 0;
        int a = 0;

        if (txfHeight.getText().equals("") && txfAge.getText().equals("") && txfWeight.getText().equals("")) {
            showMessageDialog(null, "Fill in data uses age, height, and weight to search for similar athletes. \n please enter one, or all of these, to use this function.");

        } else {

            if (txfAge.getText().equals("")) {
            } else {
                age = txfAge.getText();
                a = Integer.parseInt(age);
            }
            if (txfHeight.getText().equals("")) {
            } else {
                height = txfHeight.getText();
                h = Integer.parseInt(height);
            }
            if (txfWeight.getText().equals("")) {
            } else {
                weight = txfWeight.getText();
                w = Integer.parseInt(weight);
            }

            //user_name.getText().trim().isEmpty()
            if (txfName.getText().equals("")) {
                txfName.setText("Enter Name");
            } else {
                //nothing
            }

            if (txfAddress.getText().equals("")) {
                txfAddress.setText("Enter School");
            }

            if (!radMale.isSelected() && !radFemale.isSelected()) {
                radMale.setSelected(true);
            } else {
                //nothing
            }

            //table.getColumns().clear();
            System.out.println("Running");

            String hRange = " a.height > " + (h - 5) + " AND a.height < " + (h + 5);
            String wRange = " a.weight > " + (w - 10) + " AND a.weight < " + (w + 10);
            String aRange = " a.age > " + (a - 3) + " AND a.age < " + (a + 3);

            String sql = "SELECT * FROM athlete WHERE ";
            String sqlend = " WHERE ";
            if (txfHeight.getText().equals("")) {
                //nothing
            } else {
                sql = sql + hRange;
                sqlend = sqlend + hRange;
                if (txfWeight.getText().equals("") && txfAge.getText().equals("")) {
                    //nothihg
                } else {
                    sql = sql + " AND ";
                    sqlend = sqlend + " AND ";
                }
            }
            if (txfWeight.getText().equals("")) {
                //nothing
            } else {

                sql = sql + wRange;
                sqlend = sqlend + wRange;
                if (txfAge.getText().equals("")) {
                    //nothihg
                } else {
                    sql = sql + " AND ";
                    sqlend = sqlend + " AND ";
                }

            }
            if (txfAge.getText().equals("")) {
                //nothing
            } else {

                sql = sql + aRange;
                sqlend = sqlend + aRange;
            }

            System.out.println("Query : " + sql);

            dominance.getToggles().get(0).setSelected(true);
            System.out.println(sqlend);
            Database.connect();

            ResultSet rsFMS = Database.searchQuery("SELECT * FROM Athlete a, FMS f " + sqlend + " and f.id = a.id;");
            ResultSet rsAth = Database.searchQuery("SELECT * FROM Athlete a " + sqlend + ";");
            ResultSet rsYBal = Database.searchQuery("SELECT * FROM Athlete a, YBalance y  " + sqlend + " and y.id = a.id;");
            ResultSet rsFitData = Database.searchQuery("SELECT * FROM Athlete a, FitnessData f  " + sqlend + ";");
            ResultSet rsParQ = Database.searchQuery("SELECT * FROM Athlete a, ParQ p  " + sqlend + " and a.id = p.id;");

            System.out.println("Fetched Data Succesfully");

            if (rsAth.next() == false) {
                showMessageDialog(null, "No athletes found with a similar height, weight and age. Try removing  or changing \n one of these and searching again. (you can add it back after the data is filled) ");
            } else {
                try {
                    rsFMS.next();
                    rsAth.next();
                    rsYBal.next();
                    rsFitData.next();
                    rsParQ.next();

                    /*
                     * Following code sets athlete data
                     * These values can not have errors when importing, already validated when saved to database
                     * Values saved as doubles must still be fixed and implemented
                     */
                    if (rsAth.getString(15).matches("Right")) {
                        radDominance2Right.setSelected(true);
                    } else {
                        radDominance2Left.setSelected(true);
                    }
                    if (txfSchool.getText().equals("")) {
                        txfSchool.setText("Enter School");
                    }

                    if (txfPrimaryPosition.getText().equals("")) {
                        txfPrimaryPosition.setText("Enter Position");
                    }
                    if (txfPrimarySport.getText().equals("")) {
                        txfPrimarySport.setText("Bowling");
                    }
                    if (txfHeight.getText().equals("")) {
                        txfHeight.setText(rsAth.getString(11));
                    }
                    if (txfWeight.getText().equals("")) {
                        txfWeight.setText(rsAth.getString(12));
                    }

                    if (txfAge.getText().equals("")) {
                        txfAge.setText(rsAth.getString(13));
                    }
                    if (txfRightLimbLength.getText().equals("")) {
                        txfRightLimbLength.setText(rsYBal.getString(2 + 18));
                    }
                    if (txfA1Right.getText().equals("")) {
                        txfA1Right.setText(rsYBal.getString(27));
                    }
                    if (txfA2Right.getText().equals("")) {
                        txfA2Right.setText(rsYBal.getString(28));
                    }
                    if (txfA3Right.getText().equals("")) {
                        txfA3Right.setText(rsYBal.getString(29));
                    }
                    if (txfA1Left.getText().equals("")) {
                        txfA1Left.setText(rsYBal.getString(30));
                    }
                    if (txfA2Left.getText().equals("")) {
                        txfA2Left.setText(rsYBal.getString(31));
                    }
                    if (txfA3Left.getText().equals("")) {
                        txfA3Left.setText(rsYBal.getString(32));
                    }
                    if (txfPM1Right.getText().equals("")) {
                        txfPM1Right.setText(rsYBal.getString(33));
                    }
                    if (txfPM2Right.getText().equals("")) {
                        txfPM2Right.setText(rsYBal.getString(34));
                    }

                    if (txfPM3Right.getText().equals("")) {
                        txfPM3Right.setText(rsYBal.getString(35));
                    }
                    if (txfPL1Right.getText().equals("")) {
                        txfPL1Right.setText(rsYBal.getString(21 + 18));
                    }
                    if (txfPL2Right.getText().equals("")) {
                        txfPL2Right.setText(rsYBal.getString(22 + 18));
                    }
                    if (txfPL3Right.getText().equals("")) {
                        txfPL3Right.setText(rsYBal.getString(23 + 18));
                    }
                    if (txfPM1Left.getText().equals("")) {
                        txfPM1Left.setText(rsYBal.getString(18 + 18));
                    }
                    if (txfPM2Left.getText().equals("")) {
                        txfPM2Left.setText(rsYBal.getString(19 + 18));
                    }
                    if (txfPM3Left.getText().equals("")) {
                        txfPM3Left.setText(rsYBal.getString(20 + 18));
                    }
                    if (txfPL1Left.getText().equals("")) {
                        txfPL1Left.setText(rsYBal.getString(24 + 18));
                    }
                    if (txfPL2Left.getText().equals("")) {
                        txfPL2Left.setText(rsYBal.getString(25 + 18));
                    }
                    if (txfPL3Left.getText().equals("")) {
                        txfPL3Left.setText(rsYBal.getString(26 + 18));
                    }
                    if (lblARight.getText().equals("")) {
                        lblARight.setText(rsYBal.getString(3 + 18));
                    }
                    if (lblALeft.getText().equals("")) {
                        lblALeft.setText(rsYBal.getString(4 + 18));
                    }
                    if (lblPMRight.getText().equals("")) {
                        lblPMRight.setText(rsYBal.getString(5 + 18));
                    }
                    if (lblPLRight.getText().equals("")) {
                        lblPLRight.setText(rsYBal.getString(7 + 18));
                    }
                    if (lblPMLeft.getText().equals("")) {
                        lblPMLeft.setText(rsYBal.getString(6 + 18));
                    }
                    if (lblPLLeft.getText().equals("")) {
                        lblPLLeft.setText(rsYBal.getString(8 + 18));
                    }
                    double PMMaxLeft = Double.parseDouble(maxValue(rsYBal.getString(18 + 18), rsYBal.getString(19 + 18), rsYBal.getString(20 + 18)));
                    double PMMaxRight = Double.parseDouble(maxValue(rsYBal.getString(15 + 18), rsYBal.getString(16 + 18), rsYBal.getString(17 + 18)));
                    double PLMaxLeft = Double.parseDouble(maxValue(rsYBal.getString(24 + 18), rsYBal.getString(25 + 18), rsYBal.getString(26 + 18)));
                    double PLMaxRight = Double.parseDouble(maxValue(rsYBal.getString(21 + 18), rsYBal.getString(22 + 18), rsYBal.getString(23 + 18)));
                    double AMaxLeft = Double.parseDouble(maxValue(rsYBal.getString(12 + 18), rsYBal.getString(13 + 18), rsYBal.getString(14 + 18)));
                    double AMaxRight = Double.parseDouble(maxValue(rsYBal.getString(9 + 18), rsYBal.getString(10 + 18), rsYBal.getString(11 + 18)));

                    if (lblADif.getText().equals("")) {
                        lblADif.setText("" + (Math.max(AMaxLeft, AMaxRight) - Math.min(AMaxLeft, AMaxRight)));
                    }
                    if (lblPMDif.getText().equals("")) {
                        lblPMDif.setText("" + (Math.max(PMMaxLeft, PMMaxRight) - Math.min(PMMaxLeft, PMMaxRight)));
                    }
                    if (lblPLDif.getText().equals("")) {
                        lblPLDif.setText("" + (Math.max(PLMaxLeft, PLMaxRight) - Math.min(PLMaxLeft, PLMaxRight)));
                    }
                    if (lblCompositeLeft.getText().equals("")) {
                        lblCompositeLeft.setText(rsYBal.getString(27 + 18));
                    }
                    if (lblCompositeRight.getText().equals("")) {
                        lblCompositeRight.setText(rsYBal.getString(28 + 18));
                    }

                    // FMS Data
                    // Comments are not being saved
                    tgHurdleStepR.selectToggle(tgHurdleStepR.getToggles().get(rsFMS.getInt(5 + 18)));
                    tgHurdleStepL.selectToggle(tgHurdleStepL.getToggles().get(rsFMS.getInt(4 + 18)));
                    tgDeepSquat.selectToggle(tgDeepSquat.getToggles().get(rsFMS.getInt(2 + 18)));
                    tgInlineLoungeL.selectToggle(tgInlineLoungeL.getToggles().get(rsFMS.getInt(7 + 18)));
                    tgInlineLoungeR.selectToggle(tgInlineLoungeR.getToggles().get(rsFMS.getInt(8 + 18)));
                    tgShoulderMobilityL.selectToggle(tgShoulderMobilityL.getToggles().get(rsFMS.getInt(10 + 18)));
                    tgShoulderMobilityR.selectToggle(tgShoulderMobilityR.getToggles().get(rsFMS.getInt(11 + 18)));
                    tgActiveStraightL.selectToggle(tgActiveStraightL.getToggles().get(rsFMS.getInt(15 + 18)));
                    tgActiveStraightR.selectToggle(tgActiveStraightR.getToggles().get(rsFMS.getInt(16 + 18)));
                    tgTrunkStability.selectToggle(tgTrunkStability.getToggles().get(rsFMS.getInt(18 + 18)));
                    tgRotaryStabilityL.selectToggle(tgRotaryStabilityL.getToggles().get(rsFMS.getInt(21 + 18)));
                    tgRotaryStabilityR.selectToggle(tgRotaryStabilityR.getToggles().get(rsFMS.getInt(22 + 18)));
            
            /* this should be inplace of lines 2430 - 2441 but it wont run properlt for me,
                if someone else wants to take a look at what could be wrong...
            
            if(tgHurdleStepR.getSelectedToggle().equals(null)){
            tgHurdleStepR.selectToggle(tgHurdleStepR.getToggles().get(rsFMS.getInt(5+18)));
            }
            if(tgHurdleStepL.getSelectedToggle().equals(null)){
            tgHurdleStepL.selectToggle(tgHurdleStepL.getToggles().get(rsFMS.getInt(4+18)));
            }
            if(tgDeepSquat.getSelectedToggle().equals(null)){
            tgDeepSquat.selectToggle(tgDeepSquat.getToggles().get(rsFMS.getInt(2+18)));            
            }
            if(tgInlineLoungeL.getSelectedToggle().equals(null)){
            tgInlineLoungeL.selectToggle(tgInlineLoungeL.getToggles().get(rsFMS.getInt(7+18)));
            }
            if(tgInlineLoungeR.getSelectedToggle().equals(null)){
            tgInlineLoungeR.selectToggle(tgInlineLoungeR.getToggles().get(rsFMS.getInt(8+18)));
            }
            if(tgShoulderMobilityL.getSelectedToggle().equals(null)){
            tgShoulderMobilityL.selectToggle(tgShoulderMobilityL.getToggles().get(rsFMS.getInt(10+18)));
            }
            if(tgShoulderMobilityR.getSelectedToggle().equals(null)){
            tgShoulderMobilityR.selectToggle(tgShoulderMobilityR.getToggles().get(rsFMS.getInt(11+18)));
            }
            if(tgActiveStraightL.getSelectedToggle().equals(null)){
            tgActiveStraightL.selectToggle(tgActiveStraightL.getToggles().get(rsFMS.getInt(15+18)));
            }
            if(tgActiveStraightR.getSelectedToggle().equals(null)){
            tgActiveStraightR.selectToggle(tgActiveStraightR.getToggles().get(rsFMS.getInt(16+18)));
            }
            if(tgTrunkStability.getSelectedToggle().equals(null)){
            tgTrunkStability.selectToggle(tgTrunkStability.getToggles().get(rsFMS.getInt(18+18)));
            }
            if(tgRotaryStabilityL.getSelectedToggle().equals(null)){
            tgRotaryStabilityL.selectToggle(tgRotaryStabilityL.getToggles().get(rsFMS.getInt(21+18)));
            }
            if(tgRotaryStabilityR.getSelectedToggle().equals(null)){
            tgRotaryStabilityR.selectToggle(tgRotaryStabilityR.getToggles().get(rsFMS.getInt(22+18)));
            }
            */

                    if (rsFMS.getBoolean(13 + 18)) {
                        tgShoulderClearingL.selectToggle(tgShoulderClearingL.getToggles().get(1));
                    } else {
                        tgShoulderClearingL.selectToggle(tgShoulderClearingL.getToggles().get(0));
                    }
                    if (rsFMS.getBoolean(14 + 18)) {
                        tgShoulderClearingR.selectToggle(tgShoulderClearingR.getToggles().get(1));
                    } else {
                        tgShoulderClearingR.selectToggle(tgShoulderClearingR.getToggles().get(0));
                    }
                    if (rsFMS.getBoolean(20 + 18)) {
                        tgExtensionClearing.selectToggle(tgExtensionClearing.getToggles().get(1));
                    } else {
                        tgExtensionClearing.selectToggle(tgExtensionClearing.getToggles().get(0));
                    }
                    if (rsFMS.getBoolean(24 + 18)) {
                        tgFlexionClearing.selectToggle(tgFlexionClearing.getToggles().get(1));
                    } else {
                        tgFlexionClearing.selectToggle(tgFlexionClearing.getToggles().get(0));
                    }


                    if (txfTrunkStability.getText().equals("")) {
                        txfTrunkStability.setText(rsFMS.getString(19 + 18));
                    }
                    if (txfInlineLounge.getText().equals("")) {
                        txfInlineLounge.setText(rsFMS.getString(9 + 18));
                    }
                    if (txfShoulderMobility.getText().equals("")) {
                        txfShoulderMobility.setText(rsFMS.getString(12 + 18));
                    }
                    if (txfActiveStraight.getText().equals("")) {
                        txfActiveStraight.setText(rsFMS.getString(17 + 18));
                    }
                    if (txfRotaryStability.getText().equals("")) {
                        txfRotaryStability.setText(rsFMS.getString(23 + 18));
                    }
                    if (txfHurdleStep.getText().equals("")) {
                        txfHurdleStep.setText(rsFMS.getString(4 + 18));
                    }
                    if (txfDeepSquat.getText().equals("")) {
                        txfDeepSquat.setText(rsFMS.getString(3 + 18));
                    }
                    if (txfFMSTotal.getText().equals("")) {
                        txfFMSTotal.setText(rsFMS.getString(25 + 18));
                    }


                    // Fitness Testing data
                    // Found data is not being saved....
                    if (txfRestingHR.getText().equals("")) {
                        txfRestingHR.setText(rsFitData.getString(3 + 18));
                    }
                    if (txfRestingBPA.getText().equals("")) {
                        txfRestingBPA.setText(rsFitData.getString(4 + 18));
                    }
                    if (txfRestingBPB.getText().equals("")) {
                        txfRestingBPB.setText(rsFitData.getString(5 + 18));
                    }
                    if (txfBMI.getText().equals("")) {
                        txfBMI.setText(rsFitData.getString(8 + 18));
                    }
                    if (txfPeakFLow1.getText().equals("")) {
                        txfPeakFLow1.setText(rsFitData.getString(9 + 18));
                    }
                    if (txfPeakFLow2.getText().equals("")) {
                        txfPeakFLow2.setText(rsFitData.getString(9 + 18));
                    }
                    if (txfWCirc.getText().equals("")) {
                        txfWCirc.setText(rsFitData.getString(14 + 18));
                    }
                    if (txfHipCirc.getText().equals("")) {
                        txfHipCirc.setText(rsFitData.getString(15 + 18));
                    }
                    if (txfMidTCirc.getText().equals("")) {
                        txfMidTCirc.setText(rsFitData.getString(16 + 18));
                    }
                    if (txfFlexArmCirc.getText().equals("")) {
                        txfFlexArmCirc.setText(rsFitData.getString(17 + 18));
                    }
                    if (txfAntThigh1.getText().equals("")) {
                        txfAntThigh1.setText(rsFitData.getString(11 + 18));
                    }
                    if (txfAntThigh2.getText().equals("")) {
                        txfAntThigh2.setText(rsFitData.getString(12 + 18));
                    }
                    if (txfAntThighAVG.getText().equals("")) {
                        txfAntThighAVG.setText(rsFitData.getString(13 + 18));
                    }
                    if (txfHamCSA.getText().equals("")) {
                        txfHamCSA.setText(rsFitData.getString(18 + 18));
                    }
                    if (txfQuadCSA.getText().equals("")) {
                        txfQuadCSA.setText(rsFitData.getString(19 + 18));
                    }
                    if (txfTotalCSA.getText().equals("")) {
                        txfTotalCSA.setText(rsFitData.getString(20 + 18));
                    }
                    if (txfStartDist.getText().equals("")) {
                        txfStartDist.setText(rsFitData.getString(29 + 18));
                    }
                    if (txfEndDist1.getText().equals("")) {
                        txfEndDist1.setText(rsFitData.getString(30 + 18));
                    }
                    if (txfEndDist2.getText().equals("")) {
                        txfEndDist2.setText(rsFitData.getString(31 + 18));
                    }
                    if (txfEndDist3.getText().equals("")) {
                        txfEndDist3.setText(rsFitData.getString(32 + 18));
                    }
                    if (txfFinalDist.getText().equals("")) {
                        txfFinalDist.setText(rsFitData.getString(33 + 18));
                    }
                    if (txfHGR1.getText().equals("")) {
                        txfHGR1.setText(rsFitData.getString(34 + 18));
                    }
                    if (txfHGR2.getText().equals("")) {
                        txfHGR2.setText(rsFitData.getString(35 + 18));
                    }
                    if (txfHGR3.getText().equals("")) {
                        txfHGR3.setText(rsFitData.getString(36 + 18));
                    }
                    if (txfHGL1.getText().equals("")) {
                        txfHGL1.setText(rsFitData.getString(37 + 18));
                    }
                    if (txfHGL2.getText().equals("")) {
                        txfHGL2.setText(rsFitData.getString(38 + 18));
                    }
                    if (txfHGL3.getText().equals("")) {
                        txfHGL3.setText(rsFitData.getString(39 + 18));
                    }
                    if (txfProneTime.getText().equals("")) {
                        txfProneTime.setText(rsFitData.getString(40 + 18));
                    }
                    if (txfKneeExtForceR1.getText().equals("")) {
                        txfKneeExtForceR1.setText(rsFitData.getString(41 + 18));
                    }
                    if (txfKneeExtForceR2.getText().equals("")) {
                        txfKneeExtForceR2.setText(rsFitData.getString(42 + 18));
                    }
                    if (txfKneeExtForceL1.getText().equals("")) {
                        txfKneeExtForceL1.setText(rsFitData.getString(43 + 18));
                    }
                    if (txfKneeExtForceL2.getText().equals("")) {
                        txfKneeExtForceL2.setText(rsFitData.getString(44 + 18));
                    }
                    if (txfJH1.getText().equals("")) {
                        txfJH1.setText(rsFitData.getString(45 + 18));
                    }
                    if (txfJH2.getText().equals("")) {
                        txfJH2.setText(rsFitData.getString(46 + 18));
                    }
                    if (txfMedPass1.getText().equals("")) {
                        txfMedPass1.setText(rsFitData.getString(47 + 18));
                    }
                    if (txfMedPass2.getText().equals("")) {
                        txfMedPass2.setText(rsFitData.getString(48 + 18));
                    }
                    if (txfVO2Max.getText().equals("")) {
                        txfVO2Max.setText(rsFitData.getString(49 + 18));
                    }
                    if (txfPostVO2Max.getText().equals("")) {
                        txfPostVO2Max.setText(rsFitData.getString(50 + 18));
                    }
                    if (txfPostHR.getText().equals("")) {
                        txfPostHR.setText(rsFitData.getString(52 + 18));
                    }
                    if (txfRockportHR.getText().equals("")) {
                        txfRockportHR.setText(rsFitData.getString(55 + 18));
                    }

                    Double walkSplit = rsFitData.getDouble(53 + 18);

                    int min = walkSplit.intValue();
                    Double dSec = (walkSplit - min) * 60;
                    int sec = dSec.intValue();
                    String time = "";
                    if (min < 10) {
                        time += "0" + min + ":";
                    } else {
                        time += min + ":";
                    }
                    if (sec < 10) {
                        time += "0" + sec;
                    } else {
                        time += sec;
                    }

                    if (txfRockportTime.getText().equals("")) {
                        txfRockportTime.setText(time);
                    }

                    if (txfRockportVO2Max.getText().equals("")) {
                        txfRockportVO2Max.setText(rsFitData.getString(54 + 18));
                    }
                    if (txfWalkDistance.getText().equals("")) {
                        txfWalkDistance.setText(rsFitData.getString(56 + 18));
                    }
                    if (txfWalkVO2.getText().equals("")) {
                        txfWalkVO2.setText(rsFitData.getString(57 + 18));
                    }
                    if (txfBicep.getText().equals("")) {
                        txfBicep.setText(rsFitData.getString(21 + 18));
                    }
                    if (txfTricep.getText().equals("")) {
                        txfTricep.setText(rsFitData.getString(22 + 18));
                    }
                    if (txfSubscapular.getText().equals("")) {
                        txfSubscapular.setText(rsFitData.getString(23 + 18));
                    }
                    if (txfAbdominal.getText().equals("")) {
                        txfAbdominal.setText(rsFitData.getString(24 + 18));
                    }
                    if (txfSuprailiac.getText().equals("")) {
                        txfSuprailiac.setText(rsFitData.getString(25 + 18));
                    }
                    if (txfThigh.getText().equals("")) {
                        txfThigh.setText(rsFitData.getString(26 + 18));
                    }
                    if (txfPectoral.getText().equals("")) {
                        txfPectoral.setText(rsFitData.getString(27 + 18));

                    }
                    if (txfWallsit.getText().equals("")) {
                        txfWallsit.setText(rsFitData.getString(28 + 18));
                    }


                    // Medical Survey
                    // Always retrieves Q1 as false?
                    if (tbParQ1Yes.isSelected() || tbParQ1No.isSelected()) {
                        //do nothing
                    } else {
                        if (rsParQ.getBoolean(2 + 18)) {
                            tbParQ1Yes.setSelected(true);
                        } else {
                            tbParQ1No.setSelected(true);
                        }
                    }
                    if (tbParQ2Yes.isSelected() || tbParQ2No.isSelected()) {
                        //do nothing
                    } else {
                        if (rsParQ.getBoolean(3 + 18)) {
                            tbParQ2Yes.setSelected(true);
                        } else {
                            tbParQ2No.setSelected(true);
                        }
                    }

                    if (tbParQ3Yes.isSelected() || tbParQ3No.isSelected()) {
                        //do nothing
                    } else {
                        if (rsParQ.getBoolean(4 + 18)) {
                            tbParQ3Yes.setSelected(true);
                        } else {
                            tbParQ3No.setSelected(true);
                        }
                    }

                    if (tbParQ4Yes.isSelected() || tbParQ4No.isSelected()) {
                        //do nothing
                    } else {
                        if (rsParQ.getBoolean(5 + 18)) {
                            tbParQ4Yes.setSelected(true);
                        } else {
                            tbParQ4No.setSelected(true);
                        }
                    }

                    if (tbParQ5Yes.isSelected() || tbParQ5No.isSelected()) {
                        //do nothing
                    } else {
                        if (rsParQ.getBoolean(6 + 18)) {
                            tbParQ5Yes.setSelected(true);
                        } else {
                            tbParQ5No.setSelected(true);
                        }
                    }

                    if (tbParQ6Yes.isSelected() || tbParQ6No.isSelected()) {
                        //do nothing
                    } else {
                        if (rsParQ.getBoolean(7 + 18)) {
                            tbParQ6Yes.setSelected(true);
                        } else {
                            tbParQ6No.setSelected(true);
                        }
                    }


                    if (txtParQuest7.getText().equals("")) {
                        txtParQuest7.setText(rsParQ.getString(8 + 18));
                    }

                } catch (Exception ex) {
                    ex.printStackTrace();
                } finally {
                    Database.close();
                }
            }
        }
    }

    /**
     * Create Athlete object.
     */
    private void createAthlete() {
        /**
         * **************Athlete Creation********************
         */
        Calendar cal = Calendar.getInstance();

        String name = txfName.getText();
        String date = lblDate.getText();

        String address = txfAddress.getText();
        String city = txfCity.getText();
        String state = (cbState.getValue() == null) ? "No State Selected" : cbState.getValue().toString();

        int zip = (txfZip.getText().equals("") ? 0 : Integer.parseInt(txfZip.getText()));
        String phone = txfPhone.getText();
        String school = txfSchool.getText();
        double height = (txfHeight.getText().equals("") ? 0 : Double.parseDouble(txfHeight.getText()));
        double weight = (txfWeight.getText().equals("") ? 0 : Double.parseDouble(txfWeight.getText()));
        int age = (txfAge.getText().equals("") ? 0 : Integer.parseInt(txfAge.getText()));

        cal.add(Calendar.YEAR, (age * -1));
        cal.set(Calendar.MONTH, 1);
        cal.set(Calendar.DAY_OF_MONTH, 1);

        String dateOfBirth = dateFormat.format(cal.getTime());
        if (dpDate.getValue() != null) {
            dateOfBirth = dpDate.getValue().toString();
        } else {
            dateOfBirth = "00/00/0000";
        }
        String gender = (tgGender.getSelectedToggle() != null)
                ? ((ToggleButton) tgGender.getSelectedToggle()).getText() : "-";
        String handDominance = (dominance.getSelectedToggle() != null)
                ? ((ToggleButton) dominance.getSelectedToggle()).getText() : "-";
        String legDominance = (dominance.getSelectedToggle() != null)
                ? ((ToggleButton) dominance.getSelectedToggle()).getText() : "-";
        String primarySport = txfPrimarySport.getText();
        String primaryPosition = txfPrimaryPosition.getText();

        Athlete temp = new Athlete(name, date, dateOfBirth, address, city, state, zip, phone, school, height, weight,
                age, gender, handDominance, legDominance, primarySport, primaryPosition);

        //Try with a catch if you get exceptions.
        System.out.println(viewInfo + DBindex + " CHECKINg");
        temp.addRow(viewInfo, DBindex);

        System.out.println("Created Athlete");
    }

    /**
     *
     */
    private void createFMS() {
        int deepSquatRaw = Integer.parseInt(((ToggleButton) tgDeepSquat.getSelectedToggle()).getText());
        int hurdleStepRawL = Integer.parseInt(((ToggleButton) tgHurdleStepL.getSelectedToggle()).getText());
        int hurdleStepRawR = Integer.parseInt(((ToggleButton) tgHurdleStepR.getSelectedToggle()).getText());
        int inlineLoungeRawL = Integer.parseInt(((ToggleButton) tgInlineLoungeL.getSelectedToggle()).getText());
        int inlineLoungeRawR = Integer.parseInt(((ToggleButton) tgInlineLoungeR.getSelectedToggle()).getText());
        int shoulderMobilityRawL = Integer.parseInt(((ToggleButton) tgShoulderMobilityL.getSelectedToggle()).getText());
        int shoulderMobilityRawR = Integer.parseInt(((ToggleButton) tgShoulderMobilityR.getSelectedToggle()).getText());

        boolean shoulderClearingL = ((ToggleButton) tgShoulderClearingR.getSelectedToggle()).getText().equals("+");
        boolean shoulderClearingR = ((ToggleButton) tgShoulderClearingR.getSelectedToggle()).getText().equals("+");
        int legRaiseRawL = Integer.parseInt(((ToggleButton) tgActiveStraightL.getSelectedToggle()).getText());
        int legRaiseRawR = Integer.parseInt(((ToggleButton) tgActiveStraightR.getSelectedToggle()).getText());
        int trunkStabilityRaw = Integer.parseInt(((ToggleButton) tgTrunkStability.getSelectedToggle()).getText());

        boolean extensionClearing = ((ToggleButton) tgExtensionClearing.getSelectedToggle()).getText().equals("+");
        int rotaryRawL = Integer.parseInt(((ToggleButton) tgRotaryStabilityL.getSelectedToggle()).getText());
        int rotaryRawR = Integer.parseInt(((ToggleButton) tgRotaryStabilityR.getSelectedToggle()).getText());
        boolean flexionClearing = ((ToggleButton) tgFlexionClearing.getSelectedToggle()).getText().equals("+");
        int total = Integer.parseInt(txfFMSTotal.getText());

        FMS temp = new FMS(deepSquatRaw, hurdleStepRawL, hurdleStepRawR, inlineLoungeRawL, inlineLoungeRawR,
                shoulderMobilityRawL, shoulderMobilityRawR, shoulderClearingL, shoulderClearingR,
                legRaiseRawL, legRaiseRawR, trunkStabilityRaw, extensionClearing, rotaryRawL, rotaryRawR,
                flexionClearing, total);

        temp.setComments(txfDeepSquatComment.getText(), txfHurdleStepComment.getText(), txfInlineLoungeComment.getText(),
                txfShoulderMobilityComment.getText(), txfShoulderClearingComment.getText(), txfLegRaiseComment.getText(),
                txfTrunkStabilityComment.getText(), txfExtensionClearingComment.getText(),
                txfRotaryComment.getText(), txfFlexionComment.getText());

        temp.addRow(viewInfo, DBindex);
    }

    /**
     *
     */
    private void createYBalance() {
        double rightLimbLength, antR1, antR2, antR3, antL1, antL2, antL3, pmR1, pmR2, pmR3,
                pmL1, pmL2, pmL3, plR1, plR2, plR3, plL1, plL2, plL3;

        //At this point all the data has been validated by the TextFieldRequired. No need to check for invalid input.
        rightLimbLength = Double.parseDouble(txfRightLimbLength.getText());
        antR1 = Double.parseDouble(txfA1Right.getText());
        antR2 = Double.parseDouble(txfA2Right.getText());
        antR3 = Double.parseDouble(txfA3Right.getText());
        antL1 = Double.parseDouble(txfA1Left.getText());
        antL2 = Double.parseDouble(txfA2Left.getText());
        antL3 = Double.parseDouble(txfA3Left.getText());

        pmR1 = Double.parseDouble(txfPM1Right.getText());
        pmR2 = Double.parseDouble(txfPM2Right.getText());
        pmR3 = Double.parseDouble(txfPM3Right.getText());
        pmL1 = Double.parseDouble(txfPM1Left.getText());
        pmL2 = Double.parseDouble(txfPM2Left.getText());
        pmL3 = Double.parseDouble(txfPM3Left.getText());

        plL1 = Double.parseDouble(txfPL1Left.getText());
        plL2 = Double.parseDouble(txfPL2Left.getText());
        plL3 = Double.parseDouble(txfPL3Left.getText());
        plR1 = Double.parseDouble(txfPL1Right.getText());
        plR2 = Double.parseDouble(txfPL2Right.getText());
        plR3 = Double.parseDouble(txfPL3Right.getText());

        YBalance temp = new YBalance(rightLimbLength, antR1, antR2, antR3, antL1, antL2, antL3, pmR1, pmR2, pmR3,
                pmL1, pmL2, pmL3, plR1, plR2, plR3, plL1, plL2, plL3);

        temp.addRow(viewInfo, DBindex);
    }

    /**
     * @param e
     */
    @FXML
    private void createFitnessData(ActionEvent e) {
        String regexNum = TextFieldRequired.NUMERIC;
        String regexDec = TextFieldRequired.NUMERICDEC;
        DecimalFormat df = new DecimalFormat("#.##");
        String gender = (((RadioButton) tgGender.getSelectedToggle()).getText().equals("Male")) ? "Male" : "Female";

        /**
         * ***************First Section********************
         */
        int restingHR = 0;
        int restingBPA = 0;
        int restingBPB = 0;
        double bmi = 0;
        double peakFlow = 0;
        double height = 0;
        double weight = 0;
        int age = 0;

        try {
            if (txfAge2.getText().matches(regexNum)) {
                age = Integer.parseInt(txfAge2.getText());
            }

            if (txfHeight2.getText().matches(regexDec) && txfWeight2.getText().matches(regexDec)) {
                height = Double.parseDouble(txfHeight2.getText());
                weight = Double.parseDouble(txfWeight2.getText());

                bmi = (weight / (Math.pow(height, 2))) * 10000;
                txfBMI.setText(df.format(bmi));
            } else {
                txfBMI.setText("");
            }

            if (txfRestingHR.getText().matches(regexNum)) {
                restingHR = Integer.parseInt(txfRestingHR.getText());
            }

            if (txfRestingBPA.getText().matches(regexNum) && txfRestingBPB.getText().matches(regexNum)) {
                restingBPA = Integer.parseInt(txfRestingHR.getText());
                restingBPB = Integer.parseInt(txfRestingHR.getText());
            }

            if (txfPeakFLow1.getText().matches(regexDec) && txfPeakFLow2.getText().matches(regexDec)) {
                double pf1 = Double.parseDouble(txfHeight2.getText());
                double pf2 = Double.parseDouble(txfWeight2.getText());

                peakFlow = Math.max(pf1, pf2);
            }
        } catch (Exception exception) {
            System.out.println("First section of fitness data has errors");
            exception.printStackTrace();
        }

        /**
         * ***************Anthropometrics Section********************
         */
        double ant1 = 0;
        double ant2 = 0;
        double wCirc = 0;
        double hCirc = 0;
        double midCirc = 0;
        double fCirc = 0;
        double hamCSA = 0;
        double quadCSA = 0;
        double totalCSA = 0;
        double biCirc = 0;
        double triSkin = 0;
        double subSkin = 0;
        double abdSkin = 0;
        double supSkin = 0;
        double thighSkin = 0;
        double pecSkin = 0;
        double wallsit = 0;

        try {
            if (txfAntThigh1.getText().matches(regexDec) && txfAntThigh2.getText().matches(regexDec)) {
                ant1 = Double.parseDouble(txfAntThigh1.getText());
                ant2 = Double.parseDouble(txfAntThigh2.getText());

                double avg = (ant1 + ant2) / 2;
                txfAntThighAVG.setText("" + df.format(avg));

                //Calculate CSA variables
                if (txfMidTCirc.getText().matches(regexDec)) {
                    midCirc = Double.parseDouble(txfMidTCirc.getText());

                    quadCSA = (2.52 * midCirc) - (1.25 * avg) - 45.13;
                    hamCSA = (1.08 * midCirc) - (0.64 * avg) - 22.69;
                    totalCSA = (4.68 * midCirc) - (2.09 * avg) - 80.99;

                    txfHamCSA.setText(df.format(hamCSA));
                    txfQuadCSA.setText(df.format(quadCSA));
                    txfTotalCSA.setText(df.format(totalCSA));
                } else {
                    midCirc = 0;
                    hamCSA = 0;
                    quadCSA = 0;
                    totalCSA = 0;

                    txfHamCSA.setText("");
                    txfQuadCSA.setText("");
                    txfTotalCSA.setText("");
                }

                if (txfWCirc.getText().matches(regexDec)) {
                    wCirc = Double.parseDouble(txfWCirc.getText());
                } else {
                    wCirc = 0;
                }

                if (txfHipCirc.getText().matches(regexDec)) {
                    hCirc = Double.parseDouble(txfHipCirc.getText());
                } else {
                    hCirc = 0;
                }

                if (txfFlexArmCirc.getText().matches(regexDec)) {
                    fCirc = Double.parseDouble(txfFlexArmCirc.getText());
                } else {
                    fCirc = 0;
                }
                if (txfBicep.getText().matches(regexDec)) {
                    biCirc = Double.parseDouble(txfBicep.getText());
                } else {
                    biCirc = 0;
                }

            } else {
                hamCSA = 0;
                quadCSA = 0;
                totalCSA = 0;

                txfHamCSA.setText("");
                txfQuadCSA.setText("");
                txfTotalCSA.setText("");
                txfAntThighAVG.setText("");
            }
        } catch (Exception exception) {
            System.out.println("Antrhopometrics section of fitness data has errors");
            exception.printStackTrace();
        }

        /**
         * **************Skinfold Section********************
         */

        try {
            if (txfTricep.getText().matches(regexDec)) {
                triSkin = Double.parseDouble(txfTricep.getText());
            } else {
                triSkin = 0;
            }

            if (txfSubscapular.getText().matches(regexDec)) {
                subSkin = Double.parseDouble(txfSubscapular.getText());
            } else {
                subSkin = 0;
            }

            if (txfAbdominal.getText().matches(regexDec)) {
                abdSkin = Double.parseDouble(txfAbdominal.getText());
            } else {
                abdSkin = 0;
            }

            if (txfSuprailiac.getText().matches(regexDec)) {
                supSkin = Double.parseDouble(txfSuprailiac.getText());
            } else {
                supSkin = 0;
            }

            if (txfThigh.getText().matches(regexDec)) {
                thighSkin = Double.parseDouble(txfThigh.getText());
            } else {
                thighSkin = 0;
            }

            if (txfPectoral.getText().matches(regexDec)) {
                pecSkin = Double.parseDouble(txfPectoral.getText());
            } else {
                pecSkin = 0;
            }

            if (txfWallsit.getText().matches(regexDec)) {
                wallsit = Double.parseDouble(txfWallsit.getText());
            } else {
                wallsit = 0;
            }
        } catch (Exception exception) {
            System.out.println("Skinfold of fitness data has errors");
            exception.printStackTrace();
        }

        /**
         * ***************Sit & Reach Section********************
         */
        double startDist = 0;
        double endDist1 = 0;
        double endDist2 = 0;
        double endDist3 = 0;
        double endDist = 0;

        try {
            if (txfStartDist.getText().matches(regexDec)) {
                startDist = Double.parseDouble(txfStartDist.getText());

                if (txfEndDist1.getText().matches(regexDec) && txfEndDist1.getText().matches(regexDec)
                        && txfEndDist1.getText().matches(regexDec)) {
                    endDist1 = Double.parseDouble(txfEndDist1.getText());
                    endDist2 = Double.parseDouble(txfEndDist2.getText());
                    endDist3 = Double.parseDouble(txfEndDist3.getText());

                    endDist = (Math.max(endDist1, Math.max(endDist2, endDist3))) - startDist;
                    txfFinalDist.setText(df.format(endDist));
                } else {
                    txfFinalDist.setText("");
                }
            } else {
                txfStartDist.setText("");
                txfFinalDist.setText("");
            }
        } catch (Exception exception) {
            System.out.println("Sit & Reach section of fitness data has errors");
            exception.printStackTrace();
        }

        /**
         * ***************Muscle & Strength Section********************
         */
        double hgR1 = 0;
        double hgR2 = 0;
        double hgR3 = 0;
        double hgR = 0;

        double hgL1 = 0;
        double hgL2 = 0;
        double hgL3 = 0;
        double hgL = 0;

        double proneTime = 0;
        double kneeExtForceR1 = 0;
        double kneeExtForceR2 = 0;
        double kneeExtForceL1 = 0;
        double kneeExtForceL2 = 0;

        double jh1 = 0;
        double jh2 = 0;
        double medPass1 = 0;
        double medPass2 = 0;

        try {
            if (txfHGR1.getText().matches(regexDec) && txfHGR2.getText().matches(regexDec) && txfHGR2.getText().matches(regexDec)) {
                hgR1 = Double.parseDouble(txfHGR1.getText());
                hgR2 = Double.parseDouble(txfHGR2.getText());
                hgR3 = Double.parseDouble(txfHGR3.getText());

                hgR = Math.max(hgR1, Math.max(hgR2, hgR3));
            } else {
                hgR = 0;
                hgR1 = 0;
                hgR2 = 0;
                hgR3 = 0;
            }

            if (txfHGL1.getText().matches(regexDec) && txfHGL2.getText().matches(regexDec) && txfHGL2.getText().matches(regexDec)) {
                hgL1 = Double.parseDouble(txfHGL1.getText());
                hgL2 = Double.parseDouble(txfHGL2.getText());
                hgL3 = Double.parseDouble(txfHGL3.getText());

                hgL = Math.max(hgL1, Math.max(hgL2, hgL3));
            } else {
                hgL = 0;
                hgL1 = 0;
                hgL2 = 0;
                hgL3 = 0;
            }

            if (txfProneTime.getText().matches(regexDec)) {
                proneTime = Double.parseDouble(txfProneTime.getText());
            } else {
                proneTime = 0;
            }

            if (txfKneeExtForceR1.getText().matches(regexDec) && txfKneeExtForceR2.getText().matches(regexDec)) {
                kneeExtForceR1 = Double.parseDouble(txfKneeExtForceR1.getText());
                kneeExtForceR2 = Double.parseDouble(txfKneeExtForceR2.getText());
            } else {
                kneeExtForceR1 = 0;
                kneeExtForceR2 = 0;
            }

            if (txfKneeExtForceL1.getText().matches(regexDec) && txfKneeExtForceL2.getText().matches(regexDec)) {
                kneeExtForceL1 = Double.parseDouble(txfKneeExtForceL1.getText());
                kneeExtForceL2 = Double.parseDouble(txfKneeExtForceL2.getText());
            } else {
                kneeExtForceL1 = 0;
                kneeExtForceL2 = 0;
            }

            if (txfJH1.getText().matches(regexDec) && txfJH2.getText().matches(regexDec)) {
                jh1 = Double.parseDouble(txfJH1.getText());
                jh2 = Double.parseDouble(txfJH2.getText());
            } else {
                jh1 = 0;
                jh2 = 0;
            }

            if (txfMedPass1.getText().matches(regexDec) && txfMedPass2.getText().matches(regexDec)) {
                medPass1 = Double.parseDouble(txfMedPass1.getText());
                medPass2 = Double.parseDouble(txfMedPass2.getText());
            } else {
                medPass1 = 0;
                medPass2 = 0;
            }

        } catch (Exception exception) {
            System.out.println("Sit & Reach section of fitness data has errors");
            exception.printStackTrace();
        }

        /**
         * ***************Rock Port Section********************
         */
        int postHR = 0;
        double postVO2Max = 0;
        double vO2Max = 0;
        int rockHR = 0;

        double walkTime = 0;
        double rockVO2Max = 0;
        double walkDistance = 0;
        double walkVO2Max = 0;
        double ageRating = 0;
        double ACSMpercentile = 0;

        try {
            vO2Max = ((0.046 * height) - (0.021 * age) - 4.93) * 1000;//ml/min
            vO2Max = vO2Max / weight; //ml/kg/min
            txfVO2Max.setText(df.format(vO2Max));

            if (txfPostHR.getText().matches(regexNum)) {
                postHR = Integer.parseInt(txfPostHR.getText());
                postVO2Max = (-0.9675 * postHR) + 77.643;
                txfPostVO2Max.setText(df.format(postVO2Max));
            } else {
                txfPostVO2Max.setText("");
            }

            //Rockport Test
            if (txfRockportHR.getText().matches(regexNum) && txfRockportTime.getText().matches("^[0-9]+(:\\d+)+$")) {
                String time[] = txfRockportTime.getText().split(":");

                rockHR = Integer.parseInt(txfRockportHR.getText());
                walkTime = Double.parseDouble(time[0]) + (Double.parseDouble(time[1]) / 60);//convert seconds to minutes.

                //Just doing Male for now.
                if (age >= 30 && age <= 65) {
                    rockVO2Max = (139.168 - (0.3877 * age) - (0.1692 * (weight * 2.20462)) - (3.2649 * walkTime) - (0.1565 * rockHR));
                } else if (age >= 18 && age <= 29) {
                    rockVO2Max = (97.660 - (0.0957 * age) - (0.1692 * (weight * 2.20462)) - (1.4537 * walkTime) - (0.1194 * rockHR));
                }

                txfRockportVO2Max.setText(df.format(rockVO2Max));
            } else {
                txfRockportVO2Max.setText("");
            }

            //12-Minute Walk Test
            if (txfWalkDistance.getText().matches(regexDec)) {
                walkDistance = Double.parseDouble(txfWalkDistance.getText());

                //VO2max(mL/kg/min)= (distance covered in meters - 504.9)/44.73
                walkVO2Max = (walkDistance - 504.9) / 44.73;
                txfWalkVO2.setText(df.format(walkVO2Max));
            } else {
                txfWalkVO2.setText("");
            }
        } catch (Exception exception) {
            System.out.println("Estimated Aerobic thing of fitness data has errors");
            exception.printStackTrace();
        }

        tmp = new FitnessTest();
        tmp.setVitals(age, restingHR, restingBPA, restingBPB, height, weight, bmi, gender, peakFlow);
        tmp.setAnthro(ant1, ant2, wCirc, hCirc, midCirc, fCirc, hamCSA, quadCSA, totalCSA, biCirc);
        tmp.setSkin(triSkin, subSkin, abdSkin, supSkin, thighSkin, pecSkin, wallsit);
        tmp.setSitAndReach(startDist, endDist1, endDist2, endDist3, endDist);
        tmp.setMuscleAndStrength(hgR1, hgR2, hgR3, hgL1, hgL2, hgL3, proneTime, kneeExtForceR1,
                kneeExtForceR2, kneeExtForceL1, kneeExtForceL2, jh1, jh2, medPass1, medPass2);
        tmp.setAerobicCapacity(vO2Max, postHR, postVO2Max, ageRating, rockHR, walkTime, rockVO2Max, walkDistance, walkVO2Max,
                ACSMpercentile);
        tmp.addRow(viewInfo, DBindex);
    }

    /**
     * @author Joshua Bolstad
     */
    public void createParQ() {
        boolean ans = true;

        //Question 1
        if (tbParQ1Yes.isSelected() == true) {
            ans = true;
        } else {
            ans = false;
        }

        boolean q1Ans = ans;

        //Question 2
        if (tbParQ2Yes.isSelected() == true) {
            ans = true;
        } else {
            ans = false;
        }

        boolean q2Ans = ans;

        //Question 3
        if (tbParQ3Yes.isSelected() == true) {
            ans = true;
        } else {
            ans = false;
        }

        boolean q3Ans = ans;

        //Question 4
        if (tbParQ4Yes.isSelected() == true) {
            ans = true;
        } else {
            ans = false;
        }

        boolean q4Ans = ans;

        //Question 5
        if (tbParQ5Yes.isSelected() == true) {
            ans = true;
        } else {
            ans = false;
        }

        boolean q5Ans = ans;

        //Question 6
        if (tbParQ6Yes.isSelected() == true) {
            ans = true;
        } else {
            ans = false;
        }

        boolean q6Ans = ans;

        //Question 7
        String q7Ans = txtParQuest7.getText();

        ParQ temp = new ParQ(q1Ans, q2Ans, q3Ans, q4Ans, q5Ans, q6Ans, q7Ans);
        temp.addRow(viewInfo, DBindex);
    }

    /**
     * @return the successful
     */
    public boolean isSuccessful() {
        return successful;
    }

    /**
     * @param successful the successful to set
     */
    public void setSuccessful(boolean successful) {
        this.successful = successful;
    }

    /**
     * If called then the user is searching the reports and has clicked on view/edit.
     * It allows the exit button for the view/edit window and the back button to do something different.
     */
    protected void setUpViewWindow() {
        viewInfo = true;
    }
}
