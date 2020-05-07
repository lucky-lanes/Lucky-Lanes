package main.formObjects;

import main.java.Database;

/**
 * FMS Score Sheet Model Object.
 * <p>
 * It represents the form used to score each athlete.
 * </p>
 * <p>
 * It contains a total of 10 tests and uses their final score to generate a final report or score.
 * </p>
 */
public class FMS {
    /**
     * Raw score for the deep squat test
     */
    private int deepSquatRaw;
    /**
     * Final score for the deep squat test
     */
    private int deepSquatFinal;
    /**
     * Raw score for the left for the hurdle step test
     */
    private int hurdleStepRawL;
    /**
     * Raw score for the right for the hurdle step test
     */
    private int hurdleStepRawR;
    /**
     * Final score for the hurdle step test
     */
    private int hurdleStepFinal;
    /**
     * Raw score for the left for the inline lounge test
     */
    private int inlineLoungeRawL;
    /**
     * Raw score for the right for the inline lounge test
     */
    private int inlineLoungeRawR;
    /**
     * Final score for the inline lounge test
     */
    private int inlineLoungeFinal;
    /**
     * Raw score for the left for the shoulder mobility test
     */
    private int shoulderMobilityRawL;
    /**
     * Raw score for the left for the shoulder mobility test
     */
    private int shoulderMobilityRawR;
    /**
     * Final score for the shoulder mobility test
     */
    private int shoulderMobilityFinal;
    /**
     * Raw score for the left for the shoulder clearing test
     */
    private boolean shoulderClearingL;
    /**
     * Raw score for the right for the shoulder clearing test
     */
    private boolean shoulderClearingR;
    /**
     * Raw score for the left for the active straight-leg raise test
     */
    private int legRaiseRawL;
    /**
     * Raw score for the right for the active straight-leg raise test
     */
    private int legRaiseRawR;
    /**
     * Final score for the active straight-leg raise test
     */
    private int legRaiseFinal;
    /**
     * Raw score for the Trunk Stability Push-Up test
     */
    private int trunkStabilityRaw;
    /**
     * Final score for the Trunk Stability Push-Up test
     */
    private int trunkStabilityFinal;
    /**
     * Boolean raw score for the extension clearing test
     */
    private boolean extensionClearing;
    /**
     * Raw score for the left for the rotary stability test
     */
    private int rotaryRawL;
    /**
     * Raw score for the right for the rotary stability test
     */
    private int rotaryRawR;
    /**
     * Final score for the rotary stability test
     */
    private int rotaryFinal;
    /**
     * Boolean raw score for the flexion clearing test
     */
    private boolean flexionClearing;
    /**
     * Total final score for the FMS Score Sheet
     */
    private int total;
    /**
     * Comments on the Deep Squat test
     */
    private String deepSquatComment;
    /**
     * Comments on the Hurdle Step test
     */
    private String hurdleStepComment;
    /**
     * Comments on the Inline Lounge test
     */
    private String inlineLoungeComment;
    /**
     * Comments on the Shoulder Mobility test
     */
    private String shoulderMobilityComment;
    /**
     * Comments on the Shoulder Clearing test
     */
    private String shoulderClearingComment;
    /**
     * Comments on the Active Straight-leg Raise test
     */
    private String legRaiseComment;
    /**
     * Comments on the Trunk Stability Push-up test
     */
    private String trunkStabilityComment;
    /**
     * Comments on the Extension Clearing test
     */
    private String extensionClearingComment;
    /**
     * Comments on the Rotary Stability test
     */
    private String rotaryComment;
    /**
     * Comments on the Flexion Clearing test
     */
    private String flexionComment;

    /**
     * Constructor for the class.
     * <p>
     * Sets all variables under the "Raw Score" and "Final Score" sections. Additionally, sets the comments to be cleared out
     * </p>
     *
     * @param deepSquatRaw Raw score for the deep squat test
     * @param hurdleStepRawL Raw score for the left for the hurdle step test
     * @param hurdleStepRawR Raw score for the right for the hurdle step test
     * @param inlineLoungeRawL Raw score for the left for the inline lounge test
     * @param inlineLoungeRawR Raw score for the right for the inline lounge test
     * @param shoulderMobilityRawL Raw score for the left for the shoulder mobility test
     * @param shoulderMobilityRawR Raw score for the right for the shoulder mobility test
     * @param shoulderClearingL Raw score for the left for the shoulder clearing test
     * @param shoulderClearingR Raw score for the right for the shoulder clearing test
     * @param legRaiseRawL Raw score for the left for the active straight-leg raise test
     * @param legRaiseRawR Raw score for the right for the active straight-leg raise test
     * @param trunkStabilityRaw Raw score for the Trunk Stability Push-Up test
     * @param extensionClearing Boolean raw score for the extension clearing test
     * @param rotaryRawL Raw score for the left for the rotary stability test
     * @param rotaryRawR Raw score for the right for the rotary stability test
     * @param flexionClearing Boolean raw score for the flexion clearing test
     * @param total Total final score for the FMS Score Sheet
     */
    public FMS(int deepSquatRaw, int hurdleStepRawL, int hurdleStepRawR, int inlineLoungeRawL, int inlineLoungeRawR,
               int shoulderMobilityRawL, int shoulderMobilityRawR, boolean shoulderClearingL, boolean shoulderClearingR,
               int legRaiseRawL, int legRaiseRawR, int trunkStabilityRaw, boolean extensionClearing, int rotaryRawL, int rotaryRawR,
               boolean flexionClearing, int total) {
        this.deepSquatRaw = deepSquatRaw;
        this.deepSquatFinal = deepSquatRaw;
        this.hurdleStepRawL = hurdleStepRawL;
        this.hurdleStepRawR = hurdleStepRawR;
        this.hurdleStepFinal = Math.min(hurdleStepRawL, hurdleStepRawR);
        this.inlineLoungeRawL = inlineLoungeRawL;
        this.inlineLoungeRawR = inlineLoungeRawR;
        this.inlineLoungeFinal = Math.min(inlineLoungeRawL, inlineLoungeRawR);
        this.shoulderMobilityRawL = shoulderMobilityRawL;
        this.shoulderMobilityRawR = shoulderMobilityRawR;

        //If clearing test are true then set score to 0

        this.shoulderClearingL = shoulderClearingL;
        this.shoulderClearingR = shoulderClearingR;
        this.shoulderMobilityFinal = (shoulderClearingL || shoulderClearingR) ? 0 : Math.min(shoulderMobilityRawL, shoulderMobilityRawR);
        this.legRaiseRawL = legRaiseRawL;
        this.legRaiseRawR = legRaiseRawR;
        this.legRaiseFinal = Math.min(legRaiseRawL, legRaiseRawR);
        this.trunkStabilityRaw = trunkStabilityRaw;
        this.extensionClearing = extensionClearing;
        this.trunkStabilityFinal = (extensionClearing) ? 0 : trunkStabilityRaw;
        this.rotaryRawL = rotaryRawL;
        this.rotaryRawR = rotaryRawR;
        this.flexionClearing = flexionClearing;
        this.rotaryFinal = (flexionClearing) ? 0 : Math.min(rotaryRawL, rotaryRawR);
        this.total = total;

        this.deepSquatComment = "none";   // deepSquatComment;
        this.hurdleStepComment = "none";   // hurdleStepComment;
        this.inlineLoungeComment = "none";   // inlineLoungeComment;
        this.shoulderMobilityComment = "none";   // shoulderMobilityComment;
        this.shoulderClearingComment = "none";   // shoulderClearingComment;
        this.legRaiseComment = "none";   // legRaiseComment;
        this.trunkStabilityComment = "none";   // trunkStabilityComment;
        this.extensionClearingComment = "none";   // extensionClearingComment;
        this.rotaryComment = "none";   // rotaryComment;
        this.flexionComment = "none";   // flexionComment;
    }

    /**
     * Sets all variables under the "Comments" sections
     *
     * @param deepSquatComment Comments on the Deep Squat test
     * @param hurdleStepComment Comments on the Hurdle Step test
     * @param inlineLoungeComment Comments on the Inline Lounge test
     * @param shoulderMobilityComment Comments on the Shoulder Mobility test
     * @param shoulderClearingComment Comments on the Shoulder Clearing test
     * @param legRaiseComment Comments on the Active Straight-leg Raise test
     * @param trunkStabilityComment Comments on the Trunk Stability Push-up test
     * @param extensionClearingComment Comments on the Extension Clearing test
     * @param rotaryComment Comments on the Rotary Stability test
     * @param flexionComment Comments on the Flexion Clearing test
     */
    public void setComments(String deepSquatComment, String hurdleStepComment, String inlineLoungeComment, String shoulderMobilityComment,
                            String shoulderClearingComment, String legRaiseComment, String trunkStabilityComment, String extensionClearingComment,
                            String rotaryComment, String flexionComment) {
        this.deepSquatComment = "";   // deepSquatComment;
        this.hurdleStepComment = "";   // hurdleStepComment;
        this.inlineLoungeComment = "";   // inlineLoungeComment;
        this.shoulderMobilityComment = "";   // shoulderMobilityComment;
        this.shoulderClearingComment = "";   // shoulderClearingComment;
        this.legRaiseComment = "";   // legRaiseComment;
        this.trunkStabilityComment = "";   // trunkStabilityComment;
        this.extensionClearingComment = "";   // extensionClearingComment;
        this.rotaryComment = "";   // rotaryComment;
        this.flexionComment = "";   // flexionComment;
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
            sql = "INSERT INTO FMS VALUES ("
                    + "null,"
                    + deepSquatRaw + "," + deepSquatFinal + ","
                    + hurdleStepRawL + "," + hurdleStepRawR + "," + hurdleStepFinal + ","
                    + inlineLoungeRawL + "," + inlineLoungeRawR + "," + inlineLoungeFinal + ","
                    + shoulderMobilityRawL + "," + shoulderMobilityRawR + "," + shoulderMobilityFinal + ","
                    + shoulderClearingL + "," + shoulderClearingR + ","
                    + legRaiseRawL + "," + legRaiseRawR + "," + legRaiseFinal + ","
                    + trunkStabilityRaw + "," + trunkStabilityFinal + ","
                    + extensionClearing + ","
                    + rotaryRawL + "," + rotaryRawR + "," + rotaryFinal + ","
                    + flexionClearing + "," + total + ");";

            Database.executeUpdate(sql);
        }
        if (viewInfo == true) {
            String sql;
            sql = "UPDATE FMS SET"
                    + " deepSquatRaw = " + deepSquatRaw + ","
                    + " deepSquatFinal = " + deepSquatFinal + ","
                    + " hurdleStepRawL = " + hurdleStepRawL + ","
                    + " hurdleStepRawR = " + hurdleStepRawR + ","
                    + " hurdleStepFinal = " + hurdleStepFinal + ","
                    + " inlineLoungeRawL = " + inlineLoungeRawL + ","
                    + " inlineLoungeRawR = " + inlineLoungeRawR + ","
                    + " inlineLoungeFinal = " + inlineLoungeFinal + ","
                    + " shoulderMobilityRawL = " + shoulderMobilityRawL + ","
                    + " shoulderMobilityRawR = " + shoulderMobilityRawR + ","
                    + " shoulderMobilityFinal = " + shoulderMobilityFinal + ","
                    + " shoulderClearingL = " + shoulderClearingL + ","
                    + " shoulderClearingR = " + shoulderClearingR + ","
                    + " legRaiseRawL = " + legRaiseRawL + ","
                    + " legRaiseRawR = " + legRaiseRawR + ","
                    + " legRaiseFinal = " + legRaiseFinal + ","
                    + " trunkStabilityRaw = " + trunkStabilityRaw + ","
                    + " trunkStabilityFinal = " + trunkStabilityFinal + ","
                    + " extensionClearing = " + extensionClearing + ","
                    + " rotaryRawL = " + rotaryRawL + ","
                    + " rotaryRawR = " + rotaryRawR + ","
                    + " rotaryFinal = " + rotaryFinal + ","
                    + " flexionClearing = " + flexionClearing + ","
                    + " total = " + rotaryRawL
                    + " WHERE ID = " + DBindex + ";";

            Database.executeUpdate(sql);
            Database.executeUpdate(sql);
        }
    }

    /**
     * Creates an html table representation of the FMS Score sheet object
     *
     * @return String with the HTML table representation of the FMS Score Sheet object.
     */
    public String toHTML() {
        String html = "<br><br><br><h2>Functional Movement Screen</h2>"
                + "<table>"
                + "<tr><th>Test</th><th></th><th>Raw Score</th><th>Final Score</th><th>Comments</th></tr>"
                + "<tr><th>DEEP SQUAT:</th><th></th><td>" + deepSquatRaw + "</td><td>" + deepSquatFinal + "</td><td>" + deepSquatComment
                + "</td></tr>"
                + "<tr><th rowspan='2'>HURDLE STEP:</th><th>L</th><td>" + hurdleStepRawL + "</td><td rowspan='2'>" + hurdleStepFinal
                + "</td><td rowspan='2'>" + hurdleStepComment + "</td></tr><tr><th>R</th><td>" + hurdleStepRawR + "</td></tr>"
                + "<tr><th rowspan='2'>INLINE LUNGE:</th><th>L</th><td>" + inlineLoungeRawL + "</td><td rowspan='2'>" + inlineLoungeFinal
                + "</td><td rowspan='2'>" + inlineLoungeComment + "</td></tr><tr><th>R</th><td>" + inlineLoungeRawR + "</td></tr>"
                + "<tr><th rowspan='2'>SHOULDER MOBILITY:</th><th>L</th><td>" + shoulderMobilityRawL + "</td><td rowspan='4'>"
                + shoulderMobilityFinal + "</td><td rowspan='2'>" + shoulderMobilityComment + "</td></tr><tr><th>R</th><td>"
                + shoulderMobilityRawR + "</td></tr>"
                + "<tr><th rowspan='2'>SHOULDER CLEARING  TEST:</th><th>L</th><td>" + ((shoulderClearingL) ? "+" : "-")
                + "</td><td rowspan='2'>" + shoulderClearingComment + "</td></tr><tr><th>R</th><td>"
                + ((shoulderClearingR) ? "+" : "-") + "</td></tr>"
                + "<tr><th rowspan='2'>ACTIVE STRAIGHT-LEG RAISE:</th><th>L</th><td>" + legRaiseRawL + "</td><td rowspan='2'>"
                + legRaiseFinal + "</td><td rowspan='2'>" + legRaiseComment + "</td></tr><tr><th>R</th><td>" + legRaiseRawR
                + "</td></tr>"
                + "<tr><th>TRUNK STABILITY PUSHUP:</th><th></th><td>" + trunkStabilityRaw + "</td><td rowspan='2'>" + trunkStabilityFinal
                + "</td><td>" + trunkStabilityComment + "</td></tr>"
                + "<tr><th>EXTENSION CLEARING TEST:</th><th></th><td>" + ((extensionClearing) ? "+" : "-") + "</td><td>"
                + extensionClearingComment + "</td></tr>"
                + "<tr><th rowspan='2'>ROTARY STABILITY:</th><th>L</th><td>" + rotaryRawL + "</td><td rowspan='3'>" + rotaryFinal
                + "</td><td rowspan='2'>" + rotaryComment + "</td></tr><tr><th>R</th><td>" + rotaryRawR + "</td></tr>"
                + "<tr><th>FLEXION CLEARING TEST:</th><th></th><td>" + ((flexionClearing) ? "+" : "-") + "</td><td>" + flexionComment
                + "</td></tr>"
                + "<tr><th>TOTAL:</th><th></th><td colspan='2'>" + total + "</td><td></td></tr>"
                + "</table>";

        return html;
    }

    /**
     * Method to generate a string representation of the FMS Score Sheet for use in generating a pdf report on the athlete.
     *
     * @return A string containing all of the information of the FMS Score Sheet. Each field is separated by a "|" character
     */
    public String toPDF() {
        String pdf = "Functional Movement Screen|" + deepSquatRaw + "|" + deepSquatFinal + "|" + deepSquatComment + "|" + hurdleStepRawL + "|" + hurdleStepFinal
                + "|" + hurdleStepComment + "|" + hurdleStepRawR + "|" + inlineLoungeRawL + "|" + inlineLoungeFinal + "|" + inlineLoungeComment + "|" + inlineLoungeRawR
                + "|" + shoulderMobilityRawL + "|" + shoulderMobilityFinal + "|" + shoulderMobilityComment + "|" + shoulderMobilityRawR + "|" + shoulderClearingL
                + "|" + shoulderClearingComment + "|" + shoulderClearingR + "|" + legRaiseRawL + "|" + legRaiseFinal + "|" + legRaiseComment + "|" + legRaiseRawR + "|"
                + trunkStabilityRaw + "|" + trunkStabilityFinal + "|" + trunkStabilityComment + "|" + extensionClearing + "|" + extensionClearingComment + "|"
                + rotaryRawL + "|" + rotaryFinal + "|" + rotaryComment + "|" + rotaryRawR + "|" + flexionClearing + "|" + flexionComment + "|" + total;
        return pdf;
    }
}