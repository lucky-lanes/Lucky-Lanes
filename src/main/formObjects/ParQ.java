package main.formObjects;

import main.java.Database;

/**
 * @author Joshua Bolstad
 * ParQ Object.
 * <p>
 * It represents the form used for the Par-Q and You questionaire.
 * <p>
 * It contains a total of 7 questions. Questions 1-6 are all Yes/No questions and Question 7 has a textfield for entering additional reasons
 * why they should not be doing physical activity.
 */
public class ParQ {
    /**
     * Holds the answer for the 1st question of the ParQ Medical Survey
     * <p>
     * Question 1: "Has your doctor ever said that you have a heart condition and that you
     * should only do physical activity recommended by a doctor?"
     * </p>
     */
    private boolean q1Ans;
    /**
     * Holds the answer for the 2nd question of the ParQ Medical Survey
     * <p>
     * Question 2: "Do you feel pain in your chest when you do physical activity?"
     * </p>
     */
    private boolean q2Ans;
    /**
     * Holds the answer for the 3rd question of the ParQ Medical Survey
     * <p>
     * Question 3: "In the past month, have you had chest pain when you are not doing physical activity?"
     * </p>
     */
    private boolean q3Ans;
    /**
     * Holds the answer for the 4th question of the ParQ Medical Survey
     * <p>
     * Question 4: "Do you lose your balance because of dizziness or do you ever lose consciousness?"
     * </p>
     */
    private boolean q4Ans;
    /**
     * Holds the answer for the 5th question of the ParQ Medical Survey
     * <p>
     * Question 5: "Do you have a bone or joint problem (for example, back, neck, knee, or hip) that could be made
     * worse by a change in your physical activity?"
     * </p>
     */
    private boolean q5Ans;
    /**
     * Holds the answer for the 6th question of the ParQ Medical Survey
     * <p>
     * Question 6: Is your doctor currently prescribing drugs (for example, water pills0 for your blood pressure or heart condition?"
     * </p>
     */
    private boolean q6Ans;
    /**
     * Holds the answer for the 7th question of the ParQ Medical Survey.
     * <p>
     * Question 7: "Do you know any other reason why you should not do physical activity?"
     * </p>
     */
    private String q7Ans;

    /**
     * Constructor for the ParQ class. Sets the answers for all questions
     *
     * @param q1ans Boolean value for the answer to the first question of the ParQ Medical Survey
     * @param q2Ans Boolean value for the answer to the second question of the ParQ Medical Survey
     * @param q3Ans Boolean value for the answer to the third question of the ParQ Medical Survey
     * @param q4Ans Boolean value for the answer to the fourth question of the ParQ Medical Survey
     * @param q5Ans Boolean value for the answer to the fifth question of the ParQ Medical Survey
     * @param q6Ans Boolean value for the answer to the sixth question of the ParQ Medical Survey
     * @param q7Ans String of reasons why the athlete should not do physical activity
     */
    public ParQ(boolean q1ans, boolean q2Ans, boolean q3Ans, boolean q4Ans, boolean q5Ans, boolean q6Ans, String q7Ans) {
        this.q1Ans = q1Ans;
        this.q2Ans = q2Ans;
        this.q3Ans = q3Ans;
        this.q4Ans = q4Ans;
        this.q5Ans = q5Ans;
        this.q6Ans = q6Ans;
        this.q7Ans = q7Ans;
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
        String sql;
        sql = "INSERT INTO PARQ VALUES ("
                + "null,"
                + q1Ans + "," + q2Ans + "," + q3Ans + "," + q4Ans + "," + q5Ans + "," + q6Ans + "," + "'" + q7Ans + "'" + ");";
        if (viewInfo == false) {

            sql = "INSERT INTO PARQ VALUES ("
                    + "null,"
                    + q1Ans + "," + q2Ans + "," + q3Ans + "," + q4Ans + "," + q5Ans + "," + q6Ans + "," + "'" + q7Ans + "'" + ");";

            Database.executeUpdate(sql);
        }
        if (viewInfo == true) {

            sql = "UPDATE PARQ SET"
                    + " q1Ans = " + q1Ans + ","
                    + " q2Ans = " + q2Ans + ","
                    + " q3Ans = " + q3Ans + ","
                    + " q4Ans = " + q4Ans + ","
                    + " q5Ans = " + q5Ans + ","
                    + " q6Ans = " + q6Ans + ","
                    + " q7Ans = " + "'" + q7Ans + "'"
                    + " WHERE ID = " + DBindex + ";";

            Database.executeUpdate(sql);
        }
    }

    /**
     * Creates an html table representation of the ParQ object
     *
     * @return String with the HTML table representation of the ParQ object.
     */
    public String toHTML() {
        String rowStart = "<tr><td>";
        String rowMid = "</td><td>";
        String rowEnd = "</td></tr>";
        String html = "</br></br></br><table><tr><th>Par-Q and You</th><th></th></tr>";
        html += rowStart + "Question 1 Answer" + rowMid + this.q1Ans + rowEnd
                + rowStart + "Question 2 Answer" + rowMid + this.q2Ans + rowEnd
                + rowStart + "Question 3 Answer" + rowMid + this.q3Ans + rowEnd
                + rowStart + "Question 4 Answer" + rowMid + this.q4Ans + rowEnd
                + rowStart + "Question 5 Answer" + rowMid + this.q5Ans + rowEnd
                + rowStart + "Question 6 Answer" + rowMid + this.q6Ans + rowEnd
                + rowStart + "Question 7 Answer" + rowMid + this.q7Ans + rowEnd
                + "</table>";

        return html;
    }

    /**
     * Method to generate a string representation of the ParQ for use in generating a pdf report on the athlete.
     *
     * @return A string containing all of the information of the ParQ. Each field is separated by a "|" character
     */
    public String toPDF() {
        System.out.println(this.q2Ans);
        String pdf = "ParQ" + "|" + this.q1Ans + "|" + this.q2Ans + "|" + this.q3Ans + "|" + this.q4Ans + "|" + this.q5Ans + "|" + this.q6Ans + "|" + this.q7Ans;
        return pdf;
    }
}