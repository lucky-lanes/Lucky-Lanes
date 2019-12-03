
package main.java.scene.control;

import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;

/**
 *
 * @author Mario
 */
public class TextFieldRequired extends TextField
{
    public static String ALPHA = "^[a-zA-Z ]+$";
    public static String ALPHANUMERIC = "^[a-zA-Z0-9 ]+$";
    public static String NUMERIC = "^[1-9]+[0-9]*$";
    public static String NUMERICDEC = "^[0-9]\\d*(\\.\\d+)?$";
    
    private String validationRegex;
    private String validationPrompt;
    private boolean isRequired;
    
    /**
     * 
     */
    public TextFieldRequired()
    {
        super("");
        validationRegex = "";
        validationPrompt = "";
        isRequired = false;
    }
    
    /**
     * 
     * @param r 
     */
    public void setRequired(boolean r)
    {
        this.isRequired = r;
    }
    
    /**
     * 
     * @return 
     */
    public boolean isRequired()
    {
        return this.isRequired;
    }
    
    /**
     * 
     * @param regex
     * @param validationPrompt 
     */
    public void setValidation(String regex, String validationPrompt)
    {
        this.validationRegex = regex;
        this.validationPrompt = validationPrompt;
        
        this.setTooltip(new Tooltip(validationPrompt));

        this.textProperty().addListener((observable, oldValue, newValue) ->
        {
            if (isRequired)
            {
                if (isValid(newValue))
                {
                    this.setStyle("-fx-effect: dropshadow( gaussian , rgba(51, 174, 44,1) , 10,0,0,0 );");
                }
                else
                {
                    this.setStyle("-fx-effect: dropshadow( gaussian , rgba(212, 27, 27,1) , 10,0,0,0 );");
                }
            }
            else
            {
                if (isValid(newValue))
                {
                    this.setStyle("-fx-effect: dropshadow( gaussian , rgba(51, 174, 44,1) , 10,0,0,0 );");
                }
                else if(newValue.matches("^[ ]*$"))
                {
                     this.setStyle("-fx-effect: null;");
                }
                else
                {
                    this.setStyle("-fx-effect: dropshadow( gaussian , rgba(212, 27, 27,1) , 10,0,0,0 );");
                }
            }
        });
    }
    
    /**
     * 
     * @return 
     */
    public boolean validate()
    {
        return isRequired && isValid();
    }
    
    /**
     * 
     * @return 
     */
    public boolean isValid()
    {
        if (isRequired)
        {
            if (isValid(this.getText()))
            {
                this.setStyle("-fx-effect: dropshadow( gaussian , rgba(51, 174, 44,1) , 10,0,0,0 );");
                return true;
            }
            
            this.setStyle("-fx-effect: dropshadow( gaussian , rgba(212, 27, 27,1) , 10,0,0,0 );");
            return false;
        }
        
        //If the textfield is not empty, it must validate
        if(!this.getText().equals(""))
        {
            if(isValid(this.getText()))
            {
                return true;
            }
            
            return false;
        }
        
        return true;
    }
    
    
    /**
     * Used to validate a field without affecting its style
     * @return 
     */
    public boolean isValidNoEffect()
    {
        if (isRequired)
        {
            if (isValid(this.getText()))
            { 
                return true;
            }
            return false;
        }
        
        //If the textfield is not empty, it must validate
        if(!this.getText().equals(""))
        {
            if(isValid(this.getText()))
            {
                return true;
            }
            
            return false;
        }
        
        return true;
    }
    
    /**
     * 
     * @param text
     * @return 
     */
    public boolean isValid(String text)
    {
        return text.matches(validationRegex);
    }
}
