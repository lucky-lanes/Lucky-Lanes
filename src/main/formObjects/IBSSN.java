package main.formObjects;

import main.java.Database;

/**
 * IBSSN object
 * <p>
 * It represents the data held in the form of the IBSSN tab
 * </p>
 */
public class IBSSN {

    /**
     * Target accuracy score, values 0.0 to 10.0
     */
    private double targetAccuracy = 0;

    /**
     * Pocket percentage, values 0 to 100%
     */
    private double pocketPercentage = 0;

    /**
     * Single pin spare conversion score, values 0 to 100%
     */
    private double singlePinSpareConv = 0;

    /**
     * Multiple pin spare conversion score, values 0 to 100%
     */
    private double multiplePinSpareConv = 0;

    /**
     * Average of number of throws to hit the pocket for the first time, values 1 to 10
     */
    private int avgThrowsToPocket = 0;

    /**
     * Average number of throws to hit the pocket after adjustment, values 1 to 10
     */
    private int avgThrowsToPocketAdjusted = 0;

    /**
     * Entry angle of ball, values 0 to 10.0
     */
    private double entryAngle = 0;

    /**
     * Ball speed consistency, values 0.0 to 1
     */
    private double ballSpeedConsistency = 0;

    /**
     * Ball speed at release, values 0 and above
     */
    private double ballSpeed = 0;

    /**
     * Gender of the bowler. 0 = female, 1 = male
     */
    private int gender = 0;

    /**
     * Rev rate at release, values 0 and above
     */
    private double revRate = 0;

    /**
     * Array that holds the total points earned from the IBSSN tests, as well as the combined total.
     */
    private int[] totalPoints = new int[11];

    /**
     * Constructor method for the IBSSN object
     *
     * @param targetAccuracy Target accuracy score, values 0.0 to 10.0
     * @param pocketPercentage Target accuracy score, values 0 to 100%
     * @param singlePinSpareConv Single pin spare conversion score, values 0 to 100%
     * @param multiplePinSpareConv Multiple pin spare conversion score, values 0 to 100%
     * @param avgThrowsToPocket Average of number of throws to hit the pocket for the first time, values 1 to 10
     * @param avgThrowsToPocketAdjusted Average number of throws to hit the pocket after adjustment, values 1 to 10
     * @param entryAngle Entry angle of ball, values 0 to 10.0
     * @param ballSpeedConsistency Ball speed consistency, values 0.0 to 1
     * @param ballSpeed Ball speed at release, values 0 and above
     * @param gender Gender of the bowler. 0 = female, 1 = male
     * @param revRate Rev rate at release, values 0 and above
     */
    public IBSSN(double targetAccuracy, double pocketPercentage, double singlePinSpareConv, double multiplePinSpareConv, int avgThrowsToPocket,
                 int avgThrowsToPocketAdjusted, double entryAngle, double ballSpeedConsistency, double ballSpeed, int gender, double revRate){

        this.targetAccuracy = targetAccuracy;
        this.pocketPercentage = pocketPercentage;
        this.singlePinSpareConv = singlePinSpareConv;
        this.multiplePinSpareConv = multiplePinSpareConv;
        this.avgThrowsToPocket = avgThrowsToPocket;
        this.avgThrowsToPocketAdjusted = avgThrowsToPocketAdjusted;
        this.entryAngle = entryAngle;
        this.ballSpeedConsistency = ballSpeedConsistency;
        this.ballSpeed = ballSpeed;
        this.gender = gender;
        this.revRate = revRate;

        //calculate total points. This method sets the total points based off the info put into all the other vars
        setTotalPoints();
    }

    /**
     * Adds a row to the database class.
     * It is used in conjunction with the other forms, since the value for each table is autoincremented.
     *
     * @param viewInfo Boolean value for if you should insert the data into the database (false), or if
     *                 you should update the athlete in the database (true)
     * @param DBindex Index of the athlete in the database. The Athlete's ID. Used when updating the athlete
     *                in the database (when the viewInfo param holds value of true)
     */
    public void addRow(boolean viewInfo, String DBindex) {
        if (viewInfo == false) {
            String sql;
            sql = "INSERT INTO IBSSN VALUES ("
                    + "null, " + targetAccuracy + ", " + pocketPercentage + ", " + singlePinSpareConv +
                    ", " + multiplePinSpareConv + ", " + avgThrowsToPocket + ", " + avgThrowsToPocketAdjusted +
                    ", " + entryAngle + "," + ballSpeedConsistency +", " + ballSpeed + ", " + gender + ", " + revRate + ");";

            Database.executeUpdate(sql);
        }
        if (viewInfo == true) {
            String sql;
            sql = "UPDATE IBSSN SET " +
                    "targetAccuracy = " + targetAccuracy +
                    ", pocketPercentage = " + pocketPercentage +
                    ", singlePinSpareConv = " + singlePinSpareConv +
                    ", multiplePinSpareConv = " + multiplePinSpareConv +
                    ", avgThrowsToPocket = " + avgThrowsToPocket +
                    ", avgThrowsToPocketAdjusted = " + avgThrowsToPocketAdjusted +
                    ", entryAngle = " + entryAngle +
                    ", ballSpeedConsistency = " + ballSpeedConsistency +
                    ", ballSpeed = " + ballSpeed +
                    ", gender = " + gender +
                    ", revRate = " + revRate +
                    " WHERE ID = " + DBindex + ";";

            Database.executeUpdate(sql);
        }
    }

    /**
     * Method to calculate the points earned from the target accuracy
     *
     * @return An integer between 1-10 of how many points they earned
     */
    public int calculateTargetAccuracyPoints(){
        //find which section it fits in and assign points
        int earnedPoints = 0;

        if(targetAccuracy < 1.8){
            earnedPoints = 10;
        } else if (1.8 <= targetAccuracy && targetAccuracy < 2.9 ){
            earnedPoints = 9;
        } else if (2.9 <= targetAccuracy && targetAccuracy < 3.3 ){
            earnedPoints = 8;
        } else if (3.3 <= targetAccuracy && targetAccuracy < 3.7 ){
            earnedPoints = 7;
        } else if (3.7 <= targetAccuracy && targetAccuracy < 4.4 ){
            earnedPoints = 6;
        } else if (4.4 <= targetAccuracy && targetAccuracy < 4.9 ){
            earnedPoints = 5;
        } else if (4.9 <= targetAccuracy && targetAccuracy < 5.2 ){
            earnedPoints = 4;
        } else if (5.2 <= targetAccuracy && targetAccuracy < 5.6 ){
            earnedPoints = 3;
        } else if (5.6 <= targetAccuracy && targetAccuracy < 6.1 ){
            earnedPoints = 2;
        } else if (6.1 <= targetAccuracy){
            earnedPoints = 1;
        }

        return earnedPoints;
    }

    /**
     * Method to calculate the points earned from the pocket percentage. The pocket is when you hit
     *
     * @return An integer between 1-10 of how many points they earned
     */
    public int calculatePocketPercentagePoints(){
        int pointsEarned = 1;
        int lowerBound = 0;
        int upperBound = 10;
        for(int i = 0; i < 10; i++){
            if(lowerBound <= pocketPercentage && pocketPercentage < upperBound ){
                //if it is between the bounds, break from the for loop
                break;
            } else {
                //else add one point, and move the bound upward by 10 so upper and lower bounds are the next percentage group
                lowerBound = upperBound;
                upperBound += 10;
                pointsEarned++;
            }
        }

        return pointsEarned;
    }

    /**
     * Method to calculate the points earned from the single pin spare conversion
     *
     * @return An integer between 1-10 of how many points they earned
     */
    public int calculateSinglePinSparePoints(){
        //determine what percentage range they fit in and assign points
        int pointsEarned = 1;
        int lowerBound = 0;
        int upperBound = 55;
        for(int i = 0; i < 10; i++){
            if(lowerBound <= singlePinSpareConv && singlePinSpareConv < upperBound){
                //if it is between the bounds, break from the for loop
                break;
            } else {
                lowerBound = upperBound;
                upperBound += 5;
                pointsEarned++;
            }
        }

        return pointsEarned;
    }

    /**
     * Method to calculate the points earned from the multi pin spare conversion
     *
     * @return An integer between 1-10 of how many points they earned
     */
    public int calculateMultiPinSparePoints(){
        //determine what percentage range they fit in and assign points
        int pointsEarned = 1;
        int lowerBound = 0;
        int upperBound = 50;
        for(int i = 0; i < 9; i++){
            if(lowerBound <= multiplePinSpareConv && multiplePinSpareConv < upperBound){
                //if it is between the bounds, break from the for loop
                break;
            } else {
                lowerBound = upperBound;
                upperBound += 5;
                pointsEarned++;
            }
        }

        if(pointsEarned > 10){
            pointsEarned = 10;
        }

        return pointsEarned;
    }

    /**
     * Method to calculate the points earned from how many shots it took on average to hit the pocket for the first time
     *
     * @return An integer between 1-10 of how many points they earned
     */
    public int calculateNoAdjustVersatilityPoints(){
        int pointsEarned;

        if(avgThrowsToPocket >= 10){
            pointsEarned = 1;
        } else {
            pointsEarned = (11-avgThrowsToPocket);
        }

        return pointsEarned;
    }

    /**
     * Method to calculate the points earned from how many shots, after moving 3 boards over, it took on average
     * to hit the pocket for the first time
     *
     * @return An integer between 1-10 of how many points they earned
     */
    public int calculateLaneAdjustVersatilityPoints() {
        int pointsEarned;

        if(avgThrowsToPocketAdjusted >= 10){
            pointsEarned = 1;
        } else {
            pointsEarned = (11-avgThrowsToPocketAdjusted);
        }

        return pointsEarned;
    }

    /**
     * Method to calculate the points earned from the entry angle into the pocket
     *
     * @return An integer between 1-10 of how many points they earned
     */
    public int calculateEntryAnglePoints(){
        int pointsEarned;

        if(entryAngle < 2.0){
            pointsEarned = 1;
        } else if (2.0 <= entryAngle && entryAngle < 2.3){
            pointsEarned = 2;
        } else if (2.3 <= entryAngle && entryAngle < 2.6){
            pointsEarned = 3;
        } else if (2.6 <= entryAngle && entryAngle < 3.0){
            pointsEarned = 4;
        } else if (3.0 <= entryAngle && entryAngle < 3.5){
            pointsEarned = 5;
        } else if (3.5 <= entryAngle && entryAngle < 3.9){
            pointsEarned = 6;
        } else if (3.9 <= entryAngle && entryAngle < 4.3){
            pointsEarned = 7;
        } else if (4.3 <= entryAngle && entryAngle < 4.8){
            pointsEarned = 8;
        } else if (4.8 <= entryAngle && entryAngle <= 5.2){
            pointsEarned = 9;
        } else {
            pointsEarned = 10;
        }

        return pointsEarned;
    }

    /**
     * Method to calculate the points earned from the consistency of speeds among multiple shots
     *
     * @return An integer between 1-10 of how many points they earned
     */
    public int calculateBallSpeedConsistPoints(){
        //find which range it falls in and assign points
        int totalPoints = 0;
        if(ballSpeedConsistency >= 0.97){
            totalPoints = 1;
        } else if (0.91 <= ballSpeedConsistency && ballSpeedConsistency < 0.97){
            totalPoints = 2;
        } else if (0.88 <= ballSpeedConsistency && ballSpeedConsistency < 0.91){
            totalPoints = 3;
        } else if (0.79 <= ballSpeedConsistency && ballSpeedConsistency < 0.88){
            totalPoints = 4;
        } else if (0.75 <= ballSpeedConsistency && ballSpeedConsistency < 0.79){
            totalPoints = 5;
        } else if (0.66 <= ballSpeedConsistency && ballSpeedConsistency < 0.75){
            totalPoints = 6;
        } else if (0.61 <= ballSpeedConsistency && ballSpeedConsistency < 0.66){
            totalPoints = 7;
        } else if (0.54 <= ballSpeedConsistency && ballSpeedConsistency < 0.61){
            totalPoints = 8;
        } else if (0.39 <= ballSpeedConsistency && ballSpeedConsistency < 0.54){
            totalPoints = 9;
        } else if (ballSpeedConsistency < 0.39){
            totalPoints = 10;
        }

        return totalPoints;
    }

    /**
     * Method to calculate the points earned from the speed of the ball when it is released
     *
     * @return An integer between 1-10 of how many points they earned
     */
    public int calculateBallSpeedReleasePoints(){
        //determine which speed section this falls into
        double speedRanges[][] = {{15.3, 15.6, 16.0, 16.4, 16.8, 17.1, 17.5}, //women's range
                {16.3, 16.8, 17.4, 17.8, 18.4, 18.8, 19.0}};//men's range
        int earnedPoints = 0;
        if(ballSpeed < speedRanges[gender][0]){
            earnedPoints = 3;
        } else {
            for(int i = 0; i < 6; i++){
                if(speedRanges[gender][i] <= ballSpeed && ballSpeed < speedRanges[gender][i+1]){
                    earnedPoints = 3 + i;
                    break;
                }
            }
            if(ballSpeed > speedRanges[gender][6]){
                earnedPoints = 10;
            }
        }

        return earnedPoints;
    }

    /**
     * Method to calculate the points earned from the rev rate of the ball when it is released
     *
     * @return An integer between 1-10 of how many points they earned
     */
    public int calculateRevRateReleasePoints(){

        //find which section it fits in and assign points
        int earnedPoints = 0;
        int ranges[] = {135, 161, 185, 217, 238, 274, 289, 362, 375};

        if(revRate < ranges[0]){
            earnedPoints = 1;
        } else {
            for(int i = 0; i < 8; i++){
                if(ranges[i] <= revRate && revRate < ranges[i+1]){
                    earnedPoints = i + 2;
                    break;
                }
            }
            if(ranges[8] < revRate){
                earnedPoints = 10;
            }
        }

        return earnedPoints;
    }

    /**
     * Setter method for totalPoints.
     * Calculates the Bowler's IBSSN score. The minimum it can be is 12 and the max it can be is 100
     * <p>
     * All arrays in this class must be set with values prior to calling this method. This method uses the
     * the calculate...Points() methods from this class and sends in the appropriate arrays
     * </p>
     */
    public void setTotalPoints(){
        for(int i = 0; i < 11; i++) {
            totalPoints[i] = 0;
        }

        totalPoints[0] += calculateTargetAccuracyPoints();
        totalPoints[1] += calculatePocketPercentagePoints();
        totalPoints[2] += calculateSinglePinSparePoints();
        totalPoints[3] += calculateMultiPinSparePoints();
        totalPoints[4] += calculateNoAdjustVersatilityPoints();
        totalPoints[5] += calculateLaneAdjustVersatilityPoints();
        totalPoints[6] += calculateEntryAnglePoints();
        totalPoints[7] += calculateBallSpeedConsistPoints();
        totalPoints[8] += calculateBallSpeedReleasePoints();
        totalPoints[9] += calculateRevRateReleasePoints();

        for(int i = 0; i < 10; i++) {
            totalPoints[10] += totalPoints[i];
        }

    }

    /**
     * Getter for the total points earned over all of the IBSSN
     *
     * @return Array of values for all sections of the IBSSN score and the final summed up score
     */
    public int[] getTotalPoints() {
        return totalPoints;
    }

    /**
     * Creates an html table representation of the IBSSN object
     *
     * @return String with the HTML table representation of the IBSSN object.
     */
    public String toHTML() {
        String rowStart = "<tr><td>";
        String rowMid = "</td><td>";
        String rowEnd = "</td></tr>";
        String html = "</br></br></br><table><tr><th>IBSSN</th><th></th></tr>";
        html += rowStart + "Target Accuracy Value" + rowMid + this.targetAccuracy + rowEnd
                + rowStart + "Target Accuracy Score" + rowMid + calculateTargetAccuracyPoints() + "/10" + rowEnd

                + rowStart + "Pocket Percentage Value" + rowMid + this.pocketPercentage + rowEnd
                + rowStart + "Pocket Percentage Score" + rowMid + calculatePocketPercentagePoints() + "/10" + rowEnd

                + rowStart + "Single Pin Spare Percentage" + rowMid + this.singlePinSpareConv + "%" + rowEnd
                + rowStart + "Single Pin Spare Score" + rowMid + calculateSinglePinSparePoints() + "/10" + rowEnd

                + rowStart + "Multiple Pin Spare Percentage" + rowMid + this.multiplePinSpareConv + "%" + rowEnd
                + rowStart + "Multiple Pin Spare Score" + rowMid + calculateMultiPinSparePoints() + "/10" + rowEnd

                + rowStart + "Average Number of Throws to Pocket" + rowMid + this.avgThrowsToPocket + rowEnd
                + rowStart + "Pocket Percentage Score" + rowMid + calculateNoAdjustVersatilityPoints() + "/10" + rowEnd

                + rowStart + "Average Number of Throws to Pocket After Adjustment" + rowMid + this.avgThrowsToPocketAdjusted + rowEnd
                + rowStart + "Pocket Percentage Score" + rowMid + calculateLaneAdjustVersatilityPoints() + "/10" + rowEnd

                + rowStart + "Ball Speed Consistency Value" + rowMid + this.ballSpeedConsistency + rowEnd
                + rowStart + "Ball Speed Consistency Score" + rowMid + calculateBallSpeedConsistPoints() + "/10" + rowEnd

                + rowStart + "Entry Angle Value" + rowMid + this.entryAngle + rowEnd
                + rowStart + "Entry Angle Score" + rowMid + calculateEntryAnglePoints() + "/10" + rowEnd

                + rowStart + "Ball Speed at Release Value" + rowMid + this.ballSpeed + rowEnd
                + rowStart + "Ball Speed Score" + rowMid + calculateBallSpeedReleasePoints() + "/10" + rowEnd

                + rowStart + "Rev Rate Value" + rowMid + this.revRate + rowEnd
                + rowStart + "Rev Rate Score" + rowMid + calculateRevRateReleasePoints() + "/10" + rowEnd

                + "</table>";
        return html;
    }

    /**
     * Method to generate a string representation of the IBSSN for use in generating a pdf report on the athlete.
     *
     * @return A string containing all of the information of the IBSSN test. Each field is separated by a "|" character
     */
    public String toPDF() {
        setTotalPoints();
        String pdf = "IBSSN|" + this.targetAccuracy + "|" + this.totalPoints[0] + "|" + this.pocketPercentage + "|" + this.totalPoints[1] + "|" +
                + this.singlePinSpareConv + "|" + this.totalPoints[2] + "|" + this.multiplePinSpareConv + "|" + this.totalPoints[3] + "|"
                + this.avgThrowsToPocket + "|" + this.totalPoints[4] + "|" + this.avgThrowsToPocketAdjusted + "|" + this.totalPoints[5] + "|"
                + this.ballSpeedConsistency + "|" + this.totalPoints[6] +  "|" + this.entryAngle + "|" + this.totalPoints[7] + "|"
                + this.ballSpeed + "|" + this.totalPoints[8] + "|" + this.revRate + "|" + this.totalPoints[9] + "|" + this.totalPoints[10];

        return pdf;
    }
}