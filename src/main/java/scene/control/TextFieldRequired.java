package main.java.scene.control;

import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;

/**
 * @author Mario
 */
public class TextFieldRequired extends TextField {
    public static String ALPHA = "^[a-zA-Z ]+$";
    public static String ALPHANUMERIC = "^[a-zA-Z0-9 ]+$";
    public static String NUMERIC = "^[1-9]+[0-9]*$";
    public static String NUMERICDEC = "^[0-9]\\d*(\\.\\d+)?$";

    private String validationRegex;
    private String validationPrompt;
    private boolean isRequired;

    /**
     * Constructor
     */
    public TextFieldRequired() {
        super("");
        validationRegex = "";
        validationPrompt = "";
        isRequired = false;
    }

    /**
     * Setter method for isRequired
     *
     * @param r Boolean value to set the TextField as required or not
     */
    public void setRequired(boolean r) {
        this.isRequired = r;
    }

    /**
     * Getter method for variable isRequired
     *
     * @return Boolean value of if the TextField is currently set to required
     */
    public boolean isRequired() {
        return this.isRequired;
    }

    /**
     * Sets the validation prompt for the TextField as well as the regular expression which the entered text
     * be tested against
     *
     * @param regex String containing a regular expression
     * @param validationPrompt String of the message that will appear when the mouse hovers over the TextField
     */
    public void setValidation(String regex, String validationPrompt) {
        this.validationRegex = regex;
        this.validationPrompt = validationPrompt;

        this.setTooltip(new Tooltip(validationPrompt));

        this.textProperty().addListener((observable, oldValue, newValue) ->
        {
            if (isRequired) {
                if (isValid(newValue)) {
                    this.setStyle("-fx-effect: dropshadow( gaussian , rgba(51, 174, 44,1) , 10,0,0,0 );");
                } else {
                    this.setStyle("-fx-effect: dropshadow( gaussian , rgba(212, 27, 27,1) , 10,0,0,0 );");
                }
            } else {
                if (isValid(newValue)) {
                    this.setStyle("-fx-effect: dropshadow( gaussian , rgba(51, 174, 44,1) , 10,0,0,0 );");
                } else if (newValue.matches("^[ ]*$")) {
                    this.setStyle("-fx-effect: null;");
                } else {
                    this.setStyle("-fx-effect: dropshadow( gaussian , rgba(212, 27, 27,1) , 10,0,0,0 );");
                }
            }
        });
    }

    /**
     * If the field is required (isRequired set to True), checks for if the entered text matches
     * the currently set regular expression
     *
     * @return Boolean value that is true if isRequired is set to true and the result of isValid() is true
     */
    public boolean validate() {
        return isRequired && isValid();
    }

    /**
     * Checks the text that is currently entered in the TextField to see if it matches the currently set
     * regular expression. Will affect the style of the TextField depending upon the returned value
     *
     * @return Boolean value that is true if the field is empty, true if the entered text matches the currently
     * set regular expression, and false if there is text and it doesn't match
     */
    public boolean isValid() {
        if (isRequired) {
            if (isValid(this.getText())) {
                this.setStyle("-fx-effect: dropshadow( gaussian , rgba(51, 174, 44,1) , 10,0,0,0 );");
                return true;
            }

            this.setStyle("-fx-effect: dropshadow( gaussian , rgba(212, 27, 27,1) , 10,0,0,0 );");
            return false;
        }

        //If the textfield is not empty, it must validate
        if (!this.getText().equals("")) {
            if (isValid(this.getText())) {
                return true;
            }

            return false;
        }

        return true;
    }
    
    /**
     * Tests the input parameter String to see if it matches the currently set regular expression pattern
     *
     * @param text String of text to be tested against the currently set regular expression
     * @return Boolean value for if it matches the set regular expression
     */
    public boolean isValid(String text) {
        return text.matches(validationRegex);
    }


    /**
     * Used to validate a field without affecting its style
     *
     * @return Boolean value of if the text in the text field matches the currently set regular expression
     */
    public boolean isValidNoEffect() {
        if (isRequired) {
            if (isValid(this.getText())) {
                return true;
            }
            return false;
        }

        //If the textfield is not empty, it must validate
        if (!this.getText().equals("")) {
            if (isValid(this.getText())) {
                return true;
            }

            return false;
        }

        return true;
    }
}
