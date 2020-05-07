package main.formObjects;

import main.java.Database;

/**
 * YBalance Object.
 * <p>
 * It represents the form used for the YBalance test.
 */
public class YBalance {
    /**
     * Right limb length in centimeters (cm)
     */
    private double rightLimbLength;
    /**
     * Average of the three measurements for the anterior with the right. Unit is centimeters (cm)
     */
    private double antRightMean;
    /**
     * Average of the three measurements for the anterior with the left. Unit is centimeters (cm)
     */
    private double antLeftMean;
    /**
     * Average of the three measurements for the posteromedial with the right. Unit is centimeters (cm)
     */
    private double pmRightMean;
    /**
     * Average of the three measurements for the posteromedial with the left. Unit is centimeters (cm)
     */
    private double pmLeftMean;
    /**
     * Average of the three measurements for the posterolateral with the right. Unit is centimeters (cm)
     */
    private double plRightMean;
    /**
     * Average of the three measurements for the posterolateral with the left. Unit is centimeters (cm)
     */
    private double plLeftMean;
    /**
     * First measurement, in centimeters (cm), for the right anterior
     */
    private double antR1;
    /**
     * Second measurement, in centimeters (cm), for the right anterior
     */
    private double antR2;
    /**
     * Third measurement, in centimeters (cm), for the right anterior
     */
    private double antR3;
    /**
     * First measurement, in centimeters (cm), for the left anterior
     */
    private double antL1;
    /**
     * Second measurement, in centimeters (cm), for the left anterior
     */
    private double antL2;
    /**
     * Third measurement, in centimeters (cm), for the left anterior
     */
    private double antL3;
    /**
     * First measurement, in centimeters (cm), for the right posteromedial
     */
    private double pmR1;
    /**
     * Second measurement, in centimeters (cm), for the right posteromedial
     */
    private double pmR2;
    /**
     * Third measurement, in centimeters (cm), for the right posteromedial
     */
    private double pmR3;
    /**
     * First measurement, in centimeters (cm), for the left posteromedial
     */
    private double pmL1;
    /**
     * Second measurement, in centimeters (cm), for the left posteromedial
     */
    private double pmL2;
    /**
     * Third measurement, in centimeters (cm), for the left posteromedial
     */
    private double pmL3;
    /**
     * First measurement, in centimeters (cm), for the right posterolateral
     */
    private double plR1;
    /**
     * Second measurement, in centimeters (cm), for the right posterolateral
     */
    private double plR2;
    /**
     * Third measurement, in centimeters (cm), for the right posterolateral
     */
    private double plR3;
    /**
     * First measurement, in centimeters (cm), for the left posterolateral
     */
    private double plL1;
    /**
     * Second measurement, in centimeters (cm), for the left posterolateral
     */
    private double plL2;
    /**
     * Third measurement, in centimeters (cm), for the left posterolateral
     */
    private double plL3;
    private double compositeLeft;
    private double compositeRight;

    /**
     * Constructor that takes in all required fields.
     *
     * @param rightLimbLength Right limb length in centimeters (cm)
     * @param antR1 First measurement, in centimeters (cm), for the right anterior
     * @param antR2 Second measurement, in centimeters (cm), for the right anterior
     * @param antR3 Third measurement, in centimeters (cm), for the right anterior
     * @param antL1 First measurement, in centimeters (cm), for the left anterior
     * @param antL2 Second measurement, in centimeters (cm), for the left anterior
     * @param antL3 Third measurement, in centimeters (cm), for the left anterior
     * @param pmR1 First measurement, in centimeters (cm), for the right posteromedial
     * @param pmR2 Second measurement, in centimeters (cm), for the right posteromedial
     * @param pmR3 Third measurement, in centimeters (cm), for the right posteromedial
     * @param pmL1 First measurement, in centimeters (cm), for the left posteromedial
     * @param pmL2 Second measurement, in centimeters (cm), for the left posteromedial
     * @param pmL3 Third measurement, in centimeters (cm), for the left posteromedial
     * @param plR1 First measurement, in centimeters (cm), for the right posterolateral
     * @param plR2 Second measurement, in centimeters (cm), for the right posterolateral
     * @param plR3 Third measurement, in centimeters (cm), for the right posterolateral
     * @param plL1 First measurement, in centimeters (cm), for the left posterolateral
     * @param plL2 Second measurement, in centimeters (cm), for the left posterolateral
     * @param plL3 Third measurement, in centimeters (cm), for the left posterolateral
     */
    public YBalance(double rightLimbLength, double antR1, double antR2, double antR3, double antL1,
                    double antL2, double antL3, double pmR1, double pmR2, double pmR3, double pmL1, double pmL2,
                    double pmL3, double plR1, double plR2, double plR3, double plL1, double plL2, double plL3) {
        this.pmL1 = pmL1;
        this.pmL2 = pmL2;
        this.pmL3 = pmL3;
        this.pmR1 = pmR1;
        this.pmR2 = pmR2;
        this.pmR3 = pmR3;
        this.plL1 = pmL1;
        this.plL2 = pmL2;
        this.plL3 = pmL3;
        this.plR1 = pmR1;
        this.plR2 = pmR2;
        this.plR3 = pmR3;
        this.antL1 = antL1;
        this.antL2 = antL2;
        this.antL3 = antL3;
        this.antR1 = antR1;
        this.antR2 = antR2;
        this.antR3 = antR3;
        this.rightLimbLength = rightLimbLength;
        this.antRightMean = Math.max(Math.max(antR1, antR2), antR3);
        this.antLeftMean = Math.max(Math.max(antL1, antL2), antL3);
        this.pmRightMean = Math.max(Math.max(pmR1, pmR2), pmR3);
        this.pmLeftMean = Math.max(Math.max(pmL1, pmL2), pmL3);
        this.plRightMean = Math.max(Math.max(plR1, plR2), plR3);
        this.plLeftMean = Math.max(Math.max(plL1, plL2), plL3);

        this.compositeLeft = ((this.antLeftMean + this.pmLeftMean + this.plLeftMean) / (3 * this.rightLimbLength)) * 100;
        this.compositeRight = ((this.antRightMean + this.pmRightMean + this.plRightMean) / (3 * this.rightLimbLength)) * 100;
    }

    /**
     * Creates an html table representation of the YBalance object
     *
     * @return String with the HTML table representation of the YBalance object.
     */
    public String toHTML() {
        String rowStart = "<tr><td>";
        String rowMid = "</td><td>";
        String rowEnd = "</td></tr>";
        String html = "</br></br></br><table><tr><th>Y-Balance</th><th></th></tr>";
        html += rowStart + "Left Posteromedial 1" + rowMid + this.pmL1 + rowEnd
                + rowStart + "Left Posteromedial 2" + rowMid + this.pmL2 + rowEnd
                + rowStart + "Left Posteromedial 3" + rowMid + this.pmL3 + rowEnd
                + rowStart + "Right Posteromedial 1" + rowMid + this.pmR1 + rowEnd
                + rowStart + "Right Posteromedial 2" + rowMid + this.pmR2 + rowEnd
                + rowStart + "Right Posteromedial 3" + rowMid + this.pmR3 + rowEnd
                + rowStart + "Left Posterolateral 1" + rowMid + this.plL1 + rowEnd
                + rowStart + "Left Posterolateral 2" + rowMid + this.plL2 + rowEnd
                + rowStart + "Left Posterolateral 3" + rowMid + this.plL3 + rowEnd
                + rowStart + "Right Posterolateral 1" + rowMid + this.plR1 + rowEnd
                + rowStart + "Right Posterolateral 2" + rowMid + this.plR2 + rowEnd
                + rowStart + "Right Posterolateral 3" + rowMid + this.plR3 + rowEnd
                + rowStart + "Left Anterior 1" + rowMid + this.antL1 + rowEnd
                + rowStart + "Left Anterior 2" + rowMid + this.antL2 + rowEnd
                + rowStart + "Left Anterior 3" + rowMid + this.antL3 + rowEnd
                + rowStart + "Right Anterior 1" + rowMid + this.antR1 + rowEnd
                + rowStart + "Right Anterior 2" + rowMid + this.antR2 + rowEnd
                + rowStart + "Right Anterior 3" + rowMid + this.antR3 + rowEnd
                + rowStart + "Right Limb Length" + rowMid + this.rightLimbLength + rowEnd
                + rowStart + "Right Anterior Mean " + rowMid + this.antRightMean + rowEnd
                + rowStart + "Left Anterior Mean " + rowMid + this.antLeftMean + rowEnd
                + rowStart + "Left Posteromedial Mean" + rowMid + this.pmLeftMean + rowEnd
                + rowStart + "Right Posteromedial Mean" + rowMid + this.pmRightMean + rowEnd
                + rowStart + "Left Posterolateral Mean" + rowMid + this.plLeftMean + rowEnd
                + rowStart + "Right Posterolateral Mean" + rowMid + this.plRightMean + rowEnd
                + "</table>";
        return html;
    }

    /**
     * Method to generate a string representation of the YBalance for use in generating a pdf report on the athlete.
     *
     * @return A string containing all of the information of the YBalance test. Each field is separated by a "|" character
     */
    public String toPDF() {
        String pdf = "Y-Balance|" + this.pmL1 + "|" + this.pmL2 + "|" + this.pmL3 + "|" + this.pmR1 + "|" + this.pmR2 + "|" + this.pmR3 + "|" + this.plL1 + "|"
                + this.plL2 + "|" + this.plL3 + "|" + this.plR1 + "|" + this.plR2 + "|" + this.plR3 + "|" + this.antL1 + "|" + this.antL2 + "|" + this.antL3 + "|"
                + this.antR1 + "|" + this.antR2 + "|" + this.antR3 + "|" + this.rightLimbLength + "|" + this.antRightMean + "|" + this.antLeftMean + "|"
                + this.pmLeftMean + "|" + this.pmRightMean + "|" + this.plLeftMean + "|" + this.plRightMean;
        return pdf;
    }

    /**
     * Empty constructor
     */
    public YBalance() {

    }

    /**
     * Sets the rightLimbLength in cm
     *
     * @param rightLimbLength Right limb length in centimeters (cm)
     */
    public void setRightLimbLength(double rightLimbLength) {
        this.rightLimbLength = rightLimbLength;
    }

    /**
     * Takes in three measurements for right anterior and sets antRightMean to the average of the 3
     *
     * @param ant1 1st measurement for the right anterior in centimeters (cm)
     * @param ant2 2nd measurement for the right anterior in centimeters (cm)
     * @param ant3 3rd measurement for the right anterior in centimeters (cm)
     */
    public void setRightAntMean(double ant1, double ant2, double ant3) {
        this.antRightMean = (ant1 + ant2 + ant3) / 3.0;
    }

    /**
     * Takes in three measurements for left anterior and sets antLeftMean to the average of the 3
     *
     * @param ant1 1st measurement for the left anterior in centimeters (cm)
     * @param ant2 2nd measurement for the left anterior in centimeters (cm)
     * @param ant3 3rd measurement for the left anterior in centimeters (cm)
     */
    public void setLeftAntMean(double ant1, double ant2, double ant3) {
        this.antLeftMean = (ant1 + ant2 + ant3) / 3.0;
    }

    /**
     * Takes in 3 measurements for right posteromedial and sets pmRightMean to the average of the 3
     *
     * @param num1 1st measurement for the right posteromedial in centimeters (cm)
     * @param num2 2nd measurement for the right posteromedial in centimeters (cm)
     * @param num3 3rd measurement for the right posteromedial in centimeters (cm)
     */
    public void setPmRightMean(double num1, double num2, double num3) {
        this.pmRightMean = (num1 + num2 + num3) / 3.0;
    }

    /**
     * Takes in 3 measurements for left posteromedial and sets pmLeftMean to the average of the 3
     *
     * @param num1 1st measurement for the left posteromedial in centimeters (cm)
     * @param num2 2nd measurement for the left posteromedial in centimeters (cm)
     * @param num3 3rd measurement for the left posteromedial in centimeters (cm)
     */
    public void setPmLeftMean(double num1, double num2, double num3) {
        this.pmLeftMean = (num1 + num2 + num3) / 3.0;
    }

    /**
     * Takes in 3 measurements for right posterolateral and sets pmRightMean to the average of the 3
     *
     * @param num1 1st measurement for the right posterolateral in centimeters (cm)
     * @param num2 2nd measurement for the right posterolateral in centimeters (cm)
     * @param num3 3rd measurement for the right posterolateral in centimeters (cm)
     */
    public void setPLRightMean(double num1, double num2, double num3) {
        this.plRightMean = (num1 + num2 + num3) / 3.0;
    }

    /**
     * Takes in 3 measurements for left posterolateral and sets pmLeftMean to the average of the 3
     *
     * @param num1 1st measurement for the left posterolateral in centimeters (cm)
     * @param num2 2nd measurement for the left posterolateral in centimeters (cm)
     * @param num3 3rd measurement for the left posterolateral in centimeters (cm)
     */
    public void setPLLeftMean(double num1, double num2, double num3) {
        this.plLeftMean = (num1 + num2 + num3) / 3.0;
    }

    /**
     * Calculates the difference between the averages for the left and right for anterior
     *
     * @return The difference between the averages for the left and right for anterior
     */
    public double getAntDiff() {
        return this.antRightMean - this.antLeftMean;
    }

    /**
     * Calculates the difference between the averages for the left and right for posteromedial
     *
     * @return The difference between the averages for the left and right for posteromedial
     */
    public double getPmDiff() {
        return this.pmRightMean - this.pmLeftMean;
    }

    /**
     * Calculates the difference between the averages for the left and right for posterolateral
     *
     * @return The difference between the averages for the left and right for posterolateral
     */
    public double getPlDiff() {
        return this.plRightMean - this.plLeftMean;
    }

    /**
     * Calculates the composite score for the right
     *
     * @return The composite score for the right
     */
    public double getRightScore() {
        return this.pmRightMean + this.plRightMean + this.antRightMean;
    }

    /**
     * Calculates the composite score for the left
     *
     * @return The composite score for the left
     */
    public double getLeftScore() {
        return this.pmLeftMean + this.plLeftMean + this.antLeftMean;
    }

    /**
     * Getter method for the right limb length
     *
     * @return The right limb length in centimeters (cm)
     */
    public double getRightLimbLength() {
        return rightLimbLength;
    }

    /**
     * Getter method for the average of the right anterior measurements
     *
     * @return The average of the right anterior measurements. Unit is centimeters
     */
    public double getAntRightMean() {
        return antRightMean;
    }

    /**
     * Getter method for the average of the left anterior measurements
     *
     * @return The average of the left anterior measurements. Unit is centimeters
     */
    public double getAntLeftMean() {
        return antLeftMean;
    }

    /**
     * Getter method for the average of the right posteromedial measurements
     *
     * @return The average of the right posteromedial measurements. Unit is centimeters
     */
    public double getPmRightMean() {
        return pmRightMean;
    }

    /**
     * Getter method for the average of the left posteromedial measurements
     *
     * @return The average of the left posteromedial measurements. Unit is centimeters
     */
    public double getPmLeftMean() {
        return pmLeftMean;
    }

    /**
     * Getter method for the average of the right posterolateral measurements
     *
     * @return The average of the right posterolateral measurements. Unit is centimeters
     */
    public double getPlRightMean() {
        return plRightMean;
    }

    /**
     * Getter method for the average of the left posterolateral measurements
     *
     * @return The average of the left posterolateral measurements. Unit is centimeters
     */
    public double getPlLeftMean() {
        return plLeftMean;
    }

    /**
     * Adds a row to the database class.
     * It is used in conjunction with the other forms, since the value for each table is autoincremented.
     *
     * @param viewInfo Boolean value for if you should insert the data into the database (false), or if
     *                 you should update the athlete in the database (true)
     * @param DBindex Index of the athlete in the database. The Athlete's ID. Used when updating the athlete
     *                in teh database (when the viewInfo param holds value of true)
     */
    public void addRow(boolean viewInfo, String DBindex) {
        if (viewInfo == false) {
            String sql;
            sql = "INSERT INTO YBALANCE VALUES ("
                    + "null,"
                    + rightLimbLength + ","
                    + antRightMean + "," + antLeftMean + ","
                    + pmRightMean + "," + pmLeftMean + ","
                    + plRightMean + "," + plLeftMean + ","
                    + antR1 + "," + antR2 + "," + antR3 + ","
                    + antL1 + "," + antL2 + "," + antL3 + ","
                    + pmR1 + "," + pmR2 + "," + pmR3 + ","
                    + pmL1 + "," + pmL2 + "," + pmL3 + ","
                    + plR1 + "," + plR2 + "," + plR3 + ","
                    + plL1 + "," + plL2 + "," + plL3 + ","
                    + compositeLeft + "," + compositeRight + ");";

            Database.executeUpdate(sql);
        }
        if (viewInfo == true) {
            String sql;
            sql = "UPDATE YBALANCE SET"
                    + " rightLimbLength = " + rightLimbLength + ","
                    + " antRightMean = " + antRightMean + ","
                    + " antLeftMean = " + antLeftMean + ","
                    + " pmRightMean = " + pmRightMean + ","
                    + " pmLeftMean = " + pmLeftMean + ","
                    + " plRightMean = " + plRightMean + ","
                    + " plLeftMean = " + plLeftMean + ","
                    + " antR1 = " + antR1 + ","
                    + " antR2 = " + antR2 + ","
                    + " antR3 = " + antR3 + ","
                    + " antL1 = " + antL1 + ","
                    + " antL2 = " + antL2 + ","
                    + " antL3 = " + antL3 + ","
                    + " pmR1 = " + pmR1 + ","
                    + " pmR2 = " + pmR2 + ","
                    + " pmR3 = " + pmR3 + ","
                    + " plR1 = " + plR1 + ","
                    + " plR2 = " + plR2 + ","
                    + " plR3 = " + plR3 + ","
                    + " plL1 = " + plL1 + ","
                    + " plL2 = " + plL2 + ","
                    + " plL3 = " + plL3 + ","
                    + " compositeLeft = " + compositeLeft + ","
                    + " compositeRight = " + compositeRight
                    + " WHERE ID = " + DBindex + ";";
            Database.executeUpdate(sql);
        }
    }
}