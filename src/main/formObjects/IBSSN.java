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
     * Array of 10 values to hold the short pattern target accuracy
     */
    private int targetAccuracyShortPattern[] = new int[10];

    /**
     * Array of 10 values to hold the long pattern target accuracy
     */
    private int targetAccuracyLongPattern[] = new int[10];

    /**
     * Array of 10 values to hold the short pattern pocket hits
     */
    private boolean pocketPercentageShortPattern[] = new boolean[10];

    /**
     * Array of 10 values to hold the long pattern pocket hits
     */
    private boolean pocketPercentageLongPattern[] = new boolean[10];

    /**
     * Array of 9 values to hold the short pattern single-pin spare hits
     */
    private boolean singlePinShortPattern[] = new boolean[9];

    /**
     * Array of 9 values to hold the long pattern single-pin spare hits
     */
    private boolean singlePinLongPattern[] = new boolean[9];

    /**
     * Array of 10 values to hold the short pattern multi-pin spare hits
     */
    private boolean multiPinShortPattern[] = new boolean[10];

    /**
     * Array of 10 values to hold the long pattern multi-pin spare hits
     */
    private boolean multiPinLongPattern[] = new boolean[10];

    /**
     * The number of shots (out of 10 max) that it takes to hit the pocket on the short pattern
     * <p>
     * So how many shots does it take until you hit the pocket for the first time
     * </p>
     */
    private int versatilityQuickToPocketShortPattern;

    /**
     * The number of shots (out of 10 max) that it takes to hit the pocket on the long pattern
     * <p>
     * So how many shots does it take until you hit the pocket for the first time
     * </p>
     */
    private int versatilityQuickToPocketLongPattern;

    /**
     * The number of shots (out of 10 max) that it takes to hit the pocket on the short pattern after moving over 3 boards
     * <p>
     * So how many shots does it take until you hit the pocket for the first time after moving over 3 boards
     * </p>
     */
    private int versatilityMove3BoardsShortPattern;

    /**
     * The number of shots (out of 10 max) that it takes to hit the pocket on the long pattern after moving over 3 boards
     * <p>
     * So how many shots does it take until you hit the pocket for the first time after moving over 3 boards
     * </p>
     */
    private int versatilityMove3BoardsLongPattern;

    /**
     * The number of shots (out of 10 max) that it takes to hit the pocket on the short pattern after moving over 5 boards
     * <p>
     * So how many shots does it take until you hit the pocket for the first time after moving over 5 boards
     * </p>
     */
    private int versatilityMove5BoardsShortPattern;

    /**
     * The number of shots (out of 10 max) that it takes to hit the pocket on the long pattern after moving over 5 boards
     * <p>
     * So how many shots does it take until you hit the pocket for the first time after moving over 5 boards
     * </p>
     */
    private int versatilityMove5BoardsLongPattern;

    /**
     * Angle of entry into the pocket for 10 shots on the short pattern
     */
    private double entryAngleShortPattern[] = new double[10];

    /**
     * Angle of entry into the pocket for 10 shots on the long pattern
     */
    private double entryAngleLongPattern[] = new double[10];

    /**
     * Ball speeds over 10 shots for the short pattern
     */
    private double ballSpeedConsistShortPattern[] = new double[10];

    /**
     * Ball speeds over 10 shots for the long pattern
     */
    private double ballSpeedConsistLongPattern[] = new double[10];

    /**
     * Ball speeds at release for 10 shots on the short pattern
     */
    private double ballSpeedReleaseShortPattern[] = new double[10];

    /**
     * Ball speeds at release for 10 shots on the long pattern
     */
    private double ballSpeedReleaseLongPattern[] = new double[10];

    /**
     * Gender of the bowler. 0 = female, 1 = male
     */
    private int gender = 0;

    /**
     * Rev rates at release for 10 shots on the short pattern
     */
    private double revRateReleaseShortPattern[] = new double[10];

    /**
     * Rev rates at release for 10 shots on the long pattern
     */
    private double revRateReleaseLongPattern[] = new double[10];

    /**
     * Holds the total points earned from the IBSSN test
     */
    private int totalPoints;

    /**
     * Constructor method for the IBSSN object
     *
     * @param targetAccuracyShortPattern Array of 10 values to hold the short pattern target accuracy
     * @param targetAccuracyLongPattern Array of 10 values to hold the long pattern target accuracy
     * @param pocketPercentageShortPattern Array of 10 values to hold the short pattern pocket hits
     * @param pocketPercentageLongPattern Array of 10 values to hold the long pattern pocket hits
     * @param singlePinShortPattern Array of 9 values to hold the short pattern single-pin spare hits
     * @param singlePinLongPattern Array of 9 values to hold the long pattern single-pin spare hits
     * @param multiPinShortPattern Array of 10 values to hold the short pattern multi-pin spare hits
     * @param multiPinLongPattern Array of 10 values to hold the long pattern multi-pin spare hits
     * @param versatilityQuickToPocketShortPattern The number of shots (out of 10 max) that it takes to hit the pocket on the short pattern
     * @param versatilityQuickToPocketLongPattern The number of shots (out of 10 max) that it takes to hit the pocket on the long pattern
     * @param versatilityMove3BoardsShortPattern The number of shots (out of 10 max) that it takes to hit the pocket on the short pattern after moving over 3 boards
     * @param versatilityMove3BoardsLongPattern The number of shots (out of 10 max) that it takes to hit the pocket on the long pattern after moving over 3 boards
     * @param versatilityMove5BoardsShortPattern The number of shots (out of 10 max) that it takes to hit the pocket on the short pattern after moving over 5 boards
     * @param versatilityMove5BoardsLongPattern The number of shots (out of 10 max) that it takes to hit the pocket on the long pattern after moving over 5 boards
     * @param entryAngleShortPattern Array holding angle of entry into the pocket for 10 shots on the short pattern
     * @param entryAngleLongPattern Array holding angle of entry into the pocket for 10 shots on the long pattern
     * @param ballSpeedConsistShortPattern Array holding ball speeds over 10 shots for the short pattern
     * @param ballSpeedConsistLongPattern Array holding ball speeds over 10 shots for the long pattern
     * @param ballSpeedReleaseShortPattern Array holding ball speeds at release for 10 shots on the short pattern
     * @param ballSpeedReleaseLongPattern Array holding ball speeds at release for 10 shots on the long pattern
     * @param gender Gender of the bowler. 0 = female, 1 = male
     * @param revRateReleaseShortPattern Array holding rev rates at release for 10 shots on the short pattern
     * @param revRateReleaseLongPattern Array holding rev rates at release for 10 shots on the long pattern
     */
    public IBSSN(int[] targetAccuracyShortPattern, int[] targetAccuracyLongPattern, boolean[] pocketPercentageShortPattern,
                 boolean[] pocketPercentageLongPattern, boolean[] singlePinShortPattern, boolean[] singlePinLongPattern,
                 boolean[] multiPinShortPattern, boolean[] multiPinLongPattern, int versatilityQuickToPocketShortPattern,
                 int versatilityQuickToPocketLongPattern, int versatilityMove3BoardsShortPattern, int versatilityMove3BoardsLongPattern,
                 int versatilityMove5BoardsShortPattern, int versatilityMove5BoardsLongPattern, double[] entryAngleShortPattern,
                 double[] entryAngleLongPattern, double[] ballSpeedConsistShortPattern, double[] ballSpeedConsistLongPattern,
                 double[] ballSpeedReleaseShortPattern, double[] ballSpeedReleaseLongPattern, int gender,
                 double[] revRateReleaseShortPattern, double[] revRateReleaseLongPattern){
        //copy contents of the passed in arrays into the class' private arrays
        copyArray(targetAccuracyShortPattern, this.targetAccuracyShortPattern);
        copyArray(targetAccuracyLongPattern, this.targetAccuracyLongPattern);
        copyArray(pocketPercentageShortPattern, this.pocketPercentageShortPattern, false);
        copyArray(pocketPercentageLongPattern, this.pocketPercentageLongPattern, false);
        copyArray(singlePinShortPattern, this.singlePinShortPattern, true);
        copyArray(singlePinLongPattern, this.singlePinLongPattern, false);
        copyArray(multiPinShortPattern, this.multiPinShortPattern, false);
        copyArray(multiPinLongPattern, this.multiPinLongPattern, false);
        this.versatilityQuickToPocketShortPattern = versatilityQuickToPocketShortPattern;
        this.versatilityQuickToPocketLongPattern = versatilityQuickToPocketLongPattern;
        this.versatilityMove3BoardsShortPattern = versatilityMove3BoardsShortPattern;
        this.versatilityMove3BoardsLongPattern = versatilityMove3BoardsLongPattern;
        this.versatilityMove5BoardsShortPattern = versatilityMove5BoardsShortPattern;
        this.versatilityMove5BoardsLongPattern = versatilityMove5BoardsLongPattern;
        copyArray(entryAngleShortPattern, this.entryAngleShortPattern);
        copyArray(entryAngleLongPattern, this.entryAngleLongPattern);
        copyArray(ballSpeedConsistShortPattern, this.ballSpeedConsistShortPattern);
        copyArray(ballSpeedConsistLongPattern, this.ballSpeedConsistLongPattern);
        copyArray(ballSpeedReleaseShortPattern, this.ballSpeedReleaseShortPattern);
        copyArray(ballSpeedReleaseLongPattern, this.ballSpeedReleaseLongPattern);
        this.gender = gender;
        copyArray(revRateReleaseShortPattern, this.revRateReleaseShortPattern);
        copyArray(revRateReleaseLongPattern, this.revRateReleaseLongPattern);

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
     *                in teh database (when the viewInfo param holds value of true)
     */
    public void addRow(boolean viewInfo, String DBindex) {
        if (viewInfo == false) {
            String sql;
            sql = "INSERT INTO IBSSN VALUES ("
                    + "null, ";
            for(int i = 0; i < 10; i++){
                sql.concat(targetAccuracyShortPattern[i] + ", ");
            }
            for(int i = 0; i < 10; i++){
                sql.concat(targetAccuracyLongPattern[i] + ", ");
            }
            for(int i = 0; i < 10; i++){
                sql.concat(pocketPercentageShortPattern[i] + ", ");
            }
            for(int i = 0; i < 10; i++){
                sql.concat(pocketPercentageLongPattern[i] + ", ");
            }
            for(int i = 0; i < 9; i++){
                sql.concat(singlePinShortPattern[i] + ", ");
            }
            for(int i = 0; i < 9; i++){
                sql.concat(singlePinLongPattern[i] + ", ");
            }
            for(int i = 0; i < 10; i++){
                sql.concat(multiPinShortPattern[i] + ", ");
            }
            for(int i = 0; i < 10; i++){
                sql.concat(multiPinLongPattern[i] + ", ");
            }
            sql.concat(versatilityQuickToPocketShortPattern + ", " + versatilityQuickToPocketLongPattern + ", " + versatilityMove3BoardsShortPattern +
                    ", " + versatilityMove3BoardsLongPattern + ", " + versatilityMove5BoardsShortPattern + ", " + versatilityMove5BoardsLongPattern + ", ");
            for(int i = 0; i < 10; i++){
                sql.concat(entryAngleShortPattern[i] + ", ");
            }
            for(int i = 0; i < 10; i++){
                sql.concat(entryAngleLongPattern[i] + ", ");
            }
            for(int i = 0; i < 10; i++){
                sql.concat(ballSpeedConsistShortPattern[i] + ", ");
            }
            for(int i = 0; i < 10; i++){
                sql.concat(ballSpeedConsistLongPattern[i] + ", ");
            }
            for(int i = 0; i < 10; i++){
                sql.concat(ballSpeedReleaseShortPattern[i] + ", ");
            }
            for(int i = 0; i < 10; i++){
                sql.concat(ballSpeedReleaseLongPattern[i] + ", ");
            }
            sql.concat(gender + ", ");
            for(int i = 0; i < 10; i++){
                sql.concat(revRateReleaseShortPattern[i] + ", ");
            }
            for(int i = 0; i < 9; i++){
                sql.concat(revRateReleaseLongPattern[i] + ", ");
            }
            sql.concat("revRateReleaseLongPattern9 = " + revRateReleaseLongPattern[9] + ");");

            Database.executeUpdate(sql);
        }
        if (viewInfo == true) {
            String sql;
            sql = "UPDATE IBSSN SET ";
            for(int i = 0; i < 10; i++){
                sql.concat("targetAccuracyShortPattern" + i + " = " + targetAccuracyShortPattern[i] + ", ");
            }
            for(int i = 0; i < 10; i++){
                sql.concat("targetAccuracyShortPattern" + i + " = " + targetAccuracyLongPattern[i] + ", ");
            }
            for(int i = 0; i < 10; i++){
                sql.concat("pocketPercentageShortPattern" + i + " = " + pocketPercentageShortPattern[i] + ", ");
            }
            for(int i = 0; i < 10; i++){
                sql.concat("pocketPercentageLongPattern" + i + " = " + pocketPercentageLongPattern[i] + ", ");
            }
            for(int i = 0; i < 9; i++){
                sql.concat("singlePinShortPattern" + i + " = " + singlePinShortPattern[i] + ", ");
            }
            for(int i = 0; i < 9; i++){
                sql.concat("singlePinLongPattern" + i + " = " + singlePinLongPattern[i] + ", ");
            }
            for(int i = 0; i < 10; i++){
                sql.concat("multiPinShortPattern" + i + " = " + multiPinShortPattern[i] + ", ");
            }
            for(int i = 0; i < 10; i++){
                sql.concat("multiPinLongPattern" + i + " = " + multiPinLongPattern[i] + ", ");
            }
            sql.concat("versatilityQuickToPocketShortPattern = " + versatilityQuickToPocketShortPattern +
                    ", versatilityQuickToPocketLongPattern = " + versatilityQuickToPocketLongPattern +
                    ", versatilityMove3BoardsShortPattern = " + versatilityMove3BoardsShortPattern +
                    ", versatilityMove3BoardsLongPattern = " + versatilityMove3BoardsLongPattern +
                    ", versatilityMove5BoardsShortPattern = " + versatilityMove5BoardsShortPattern +
                    ", versatilityMove5BoardsLongPattern = " + versatilityMove5BoardsLongPattern + ", ");
            for(int i = 0; i < 10; i++){
                sql.concat("entryAngleShortPattern" + i + " = " + entryAngleShortPattern[i] + ", ");
            }
            for(int i = 0; i < 10; i++){
                sql.concat("entryAngleLongPattern" + i + " = " + entryAngleLongPattern[i] + ", ");
            }
            for(int i = 0; i < 10; i++){
                sql.concat("ballSpeedConsistShortPattern" + i + " = " + ballSpeedConsistShortPattern[i] + ", ");
            }
            for(int i = 0; i < 10; i++){
                sql.concat("ballSpeedConsistLongPattern" + i + " = " + ballSpeedConsistLongPattern[i] + ", ");
            }
            for(int i = 0; i < 10; i++){
                sql.concat("ballSpeedReleaseShortPattern" + i + " = " + ballSpeedReleaseShortPattern[i] + ", ");
            }
            for(int i = 0; i < 10; i++){
                sql.concat("ballSpeedReleaseLongPattern" + i + " = " + ballSpeedReleaseLongPattern[i] + ", ");
            }
            sql.concat("gender = " + gender + ", ");
            for(int i = 0; i < 10; i++){
                sql.concat("revRateReleaseShortPattern" + i + " = " + revRateReleaseShortPattern[i] + ", ");
            }
            for(int i = 0; i < 9; i++){
                sql.concat("revRateReleaseLongPattern" + i + " = " + revRateReleaseLongPattern[i] + ", ");
            }
            sql.concat("revRateReleaseLongPattern9 = " + revRateReleaseLongPattern[9]);
            sql.concat(" WHERE ID = " + DBindex + ";");

            Database.executeUpdate(sql);
            Database.executeUpdate(sql);
        }
    }

    /**
     * Method to calculate the points earned from the target accuracy
     *
     * @param shortPattern Array holding the target accuracy for 10 shots for the short pattern
     * @param longPattern Array holding the target accuracy for 10 shots for the long pattern
     * @return An integer between 1-10 of how many points they earned
     */
    public int calculateTargetAccuracyPoints(int[] shortPattern, int[] longPattern){
        return 0;
    }

    /**
     * Method to calculate the points earned from the pocket percentage. The pocket is when you hit
     *
     * @param shortPattern Array holding the pocket percentage for 10 shots for the short pattern
     * @param longPattern Array holding the pocket percentage for 10 shots for the long pattern
     * @return An integer between 1-10 of how many points they earned
     */
    public int calculatePocketPercentagePoints(boolean[] shortPattern, boolean[] longPattern){
        //count up the number of times they hit the pocket
        int counter = 0;
        for(int i = 0; i < 10; i++){
            if(shortPattern[i]){
                counter++;
            }
            if(longPattern[i]){
                counter++;
            }
        }

        //convert to a percentage
        double pocketPercentage = (counter/20.0)*100;

        //determine what percentage range they fit in and assign points
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
     * @param shortPattern Array holding the hits for the 9 shots with the short pattern
     * @param longPattern Array holding the hits for the 9 shots with the long pattern
     * @return An integer between 1-10 of how many points they earned
     */
    public int calculateSinglePinSparePoints(boolean[] shortPattern, boolean[] longPattern){
        //count up the number of times they hit the pocket
        int counter = 0;
        for(int i = 0; i < 9; i++){
            if(shortPattern[i]){
                counter++;
            }
            if(longPattern[i]){
                counter++;
            }
        }

        //calculate single pin spare percentage
        double singlePinSparePercentage = (counter / 18.0) * 100;

        //determine what percentage range they fit in and assign points
        int pointsEarned = 1;
        int lowerBound = 0;
        int upperBound = 55;
        for(int i = 0; i < 10; i++){
            if(lowerBound <= singlePinSparePercentage && singlePinSparePercentage < upperBound){
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
     * @param shortPattern Array holding the hits for the 10 spare setups with the short pattern
     * @param longPattern Array holding the hits for the 10 spare setups with the long pattern
     * @return An integer between 1-10 of how many points they earned
     */
    public int calculateMultiPinSparePoints(boolean[] shortPattern, boolean[] longPattern){
        //count up the number of times they hit the pocket
        int counter = 0;
        for(int i = 0; i < 10; i++){
            if(shortPattern[i]){
                counter++;
            }
            if(longPattern[i]){
                counter++;
            }
        }

        //calculate single pin spare percentage
        double multiPinSparePercentage = (counter/20.0)*100;

        //determine what percentage range they fit in and assign points
        int pointsEarned = 1;
        int lowerBound = 0;
        int upperBound = 50;
        for(int i = 0; i < 10; i++){
            if(lowerBound <= multiPinSparePercentage && upperBound < multiPinSparePercentage){
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
     * Method to calculate the points earned from how many shots it took on average to hit the pocket for the first time
     *
     * @param shortPattern Int of shots until they hit the pocket on the short pattern
     * @param longPattern Int of shots until they hit the pocket on the long pattern
     * @return An integer between 1-10 of how many points they earned
     */
    public int calculateNoAdjustVersatilityPoints(int shortPattern, int longPattern){
        double average = Math.ceil((shortPattern + longPattern)/2.0);

        return (int)(10 - average);
    }

    /**
     * Method to calculate the points earned from how many shots, after moving 3 boards over, it took on average
     * to hit the pocket for the first time
     *
     * @param shortPattern3 Int of shots until they hit the pocket on the short pattern after moving 3 boards
     * @param longPattern3 Int of shots until they hit the pocket on the long pattern after moving 3 boards
     * @param shortPattern5 Int of shots until they hit the pocket on the short pattern after moving 5 boards
     * @param longPattern5 Int of shots until they hit the pocket on the long pattern after moving 5 boards
     * @return An integer between 1-10 of how many points they earned
     */
    public int calculateLaneAdjustVersatilityPoints(int shortPattern3, int longPattern3, int shortPattern5, int longPattern5){
        double average = Math.ceil((shortPattern3 + longPattern5 + shortPattern5 + longPattern5)/4.0);

        return (int)(10 - average);
    }

    /**
     * Method to calculate the points earned from the entry angle into the pocket
     *
     * @param shortPattern Array of doubles with the entry angle for each shot (10 total) on the short pattern
     * @param longPattern Array of doubles with the entry angle for each shot (10 total) on the long pattern
     * @return An integer between 1-10 of how many points they earned
     */
    public int calculateEntryAnglePoints(double[] shortPattern, double[] longPattern){
        //sum up the entry angles of all shots
        double sumEntryAngles = 0;
        for(int i = 0; i < 10; i++){
            sumEntryAngles += shortPattern[i];
            sumEntryAngles += longPattern[i];
        }

        //determine the average
        double averageEntryAngle = (sumEntryAngles / 20.0);

        //determine the points earned
        int pointsEarned;

        if(averageEntryAngle < 2.0){
            pointsEarned = 1;
        } else if (2.0 <= averageEntryAngle && averageEntryAngle < 2.3){
            pointsEarned = 2;
        } else if (2.3 <= averageEntryAngle && averageEntryAngle < 2.6){
            pointsEarned = 3;
        } else if (2.6 <= averageEntryAngle && averageEntryAngle < 3.0){
            pointsEarned = 4;
        } else if (3.0 <= averageEntryAngle && averageEntryAngle < 3.5){
            pointsEarned = 5;
        } else if (3.5 <= averageEntryAngle && averageEntryAngle < 3.9){
            pointsEarned = 6;
        } else if (3.9 <= averageEntryAngle && averageEntryAngle < 4.3){
            pointsEarned = 7;
        } else if (4.3 <= averageEntryAngle && averageEntryAngle < 4.8){
            pointsEarned = 8;
        } else if (4.8 <= averageEntryAngle && averageEntryAngle <= 5.2){
            pointsEarned = 9;
        } else {
            pointsEarned = 10;
        }

        return pointsEarned;
    }

    /**
     * Method to calculate the points earned from the consistency of speeds among multiple shots
     *
     * @param shortPattern Array of doubles with the speed of the ball for 10 shots on the short pattern
     * @param longPattern Array of doubles with the speed of the ball for 10 shots on the long pattern
     * @return An integer between 1-10 of how many points they earned
     */
    public int calculateBallSpeedConsistPoints(double[] shortPattern, double[] longPattern){
        //find the ranges of both the short and the long pattern
        double minShort = Double.MAX_VALUE;
        double maxShort = Double.MIN_VALUE;
        double minLong = Double.MAX_VALUE;
        double maxLong = Double.MIN_VALUE;
        //find the min and max of each pattern
        for(int i = 0; i < 10; i++){
            if(shortPattern[i] < minShort){
                minShort = shortPattern[i];
            } else if (shortPattern[i] > maxShort){
                maxShort = shortPattern[i];
            }

            if(longPattern[i] < minLong){
                minLong = longPattern[i];
            } else if (longPattern[i] > maxLong){
                maxLong = longPattern[i];
            }
        }

        //calculate the range of each pattern and ball consistency
        double rangeShort = maxShort - minShort;
        double rangeLong = maxLong - minLong;
        double ballSpeedConsistency = (rangeShort + rangeLong)/2.0;

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
     * @param shortPattern Array of doubles with the speed of the ball, at release, for 10 shots on the short pattern
     * @param longPattern Array of doubles with the speed of the ball, at release, for 10 shots on the long pattern
     * @param gender An integer holding the gender of the bowler. 0 = female, 1 = male
     * @return An integer between 1-10 of how many points they earned
     */
    public int calculateBallSpeedReleasePoints(double[] shortPattern, double[] longPattern, int gender){
        //get the average of the ball speed at release from all shots
        double sumSpeeds = 0;
        for(int i = 0; i < 10; i++){
            sumSpeeds += shortPattern[i];
            sumSpeeds += longPattern[i];
        }
        double avgAllSpeeds = sumSpeeds/20.0;

        //determine which speed section this falls into
        double speedRanges[][] = {{15.3, 15.6, 16.0, 16.4, 16.8, 17.1, 17.5}, //women's range
                                  {16.3, 16.8, 17.4, 17.8, 18.4, 18.8, 19.0}};//men's range
        int earnedPoints = 0;
        if(avgAllSpeeds < speedRanges[gender][0]){
            earnedPoints = 3;
        } else {
            for(int i = 0; i < 6; i++){
                if(speedRanges[gender][i] <= avgAllSpeeds && avgAllSpeeds < speedRanges[gender][i+1]){
                    earnedPoints = 3 + i;
                    break;
                }
            }
            if(avgAllSpeeds > speedRanges[gender][6]){
                earnedPoints = 10;
            }
        }

        return earnedPoints;
    }

    /**
     * Method to calculate the points earned from the rev rate of the ball when it is released
     *
     * @param shortPattern Array of doubles with the rev rate of the ball, at release, for 10 shots on the short pattern
     * @param longPattern Array of doubles with the rev rate of the ball, at release, for 10 shots on the long pattern
     * @return An integer between 1-10 of how many points they earned
     */
    public int calculateRevRateReleasePoints(double[] shortPattern, double[] longPattern){
        //sum all values and find average rev rate over all shots
        double sumRevRate = 0;
        for(int i = 0; i < 10; i++){
            sumRevRate += shortPattern[i];
            sumRevRate += longPattern[i];
        }

        double avgRevRate = sumRevRate / 20.0;

        //find which section it fits in and assign points
        int earnedPoints = 0;
        int ranges[] = {135, 161, 185, 217, 238, 274, 289, 362, 375};

        if(avgRevRate < ranges[0]){
            earnedPoints = 1;
        } else {
            for(int i = 0; i < 8; i++){
                if(ranges[i] <= avgRevRate && avgRevRate < ranges[i+1]){
                    earnedPoints = i + 2;
                    break;
                }
            }
            if(ranges[8] < avgRevRate){
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
        int totalPointsEarned = 0;
        totalPointsEarned += calculateTargetAccuracyPoints(targetAccuracyShortPattern, targetAccuracyLongPattern);
        totalPointsEarned += calculatePocketPercentagePoints(pocketPercentageShortPattern, pocketPercentageLongPattern);
        totalPointsEarned += calculateSinglePinSparePoints(singlePinShortPattern, singlePinLongPattern);
        totalPointsEarned += calculateMultiPinSparePoints(multiPinShortPattern, multiPinLongPattern);
        totalPointsEarned += calculateNoAdjustVersatilityPoints(versatilityQuickToPocketShortPattern, versatilityQuickToPocketLongPattern);
        totalPointsEarned += calculateLaneAdjustVersatilityPoints(versatilityMove3BoardsShortPattern, versatilityMove3BoardsLongPattern,
                                                                versatilityMove5BoardsShortPattern, versatilityMove5BoardsLongPattern);
        totalPointsEarned += calculateEntryAnglePoints(entryAngleShortPattern, entryAngleLongPattern);
        totalPointsEarned += calculateBallSpeedConsistPoints(ballSpeedConsistShortPattern, ballSpeedConsistLongPattern);
        totalPointsEarned += calculateBallSpeedReleasePoints(ballSpeedReleaseShortPattern, ballSpeedConsistLongPattern, gender);
        totalPointsEarned += calculateRevRateReleasePoints(revRateReleaseShortPattern, revRateReleaseLongPattern);

        this.totalPoints = totalPointsEarned;
    }

    /**
     * Takes two double arrays in and copies array1 into array2
     *
     * @param array1 Array to be copied into array2
     * @param array2 Array to hold the information being copied in
     */
    private void copyArray(double[] array1, double[] array2){
        for(int i = 0; i < 10; i++){
            array2[i] = array1[i];
        }
    }

    /**
     * Takes two int arrays in and copies array1 into array2
     *
     * @param array1 Array to be copied into array2
     * @param array2 Array to hold the information being copied in
     */
    private void copyArray(int[] array1, int[] array2){
        for(int i = 0; i < 10; i++){
            array2[i] = array1[i];
        }
    }

    /**
     * Takes two boolean arrays in and copies array1 into array2
     *
     * @param array1 Array to be copied into array2
     * @param array2 Array to hold the information being copied in
     * @param only9 set to true if the array only has 9 values, otherwise it defaults to arrays of size 10
     */
    private void copyArray(boolean[] array1, boolean[] array2, boolean only9) {
        if (only9) {
            for (int i = 0; i < 9; i++) {
                array2[i] = array1[i];
            }
        } else {
            for (int i = 0; i < 10; i++) {
                array2[i] = array1[i];
            }
        }
    }
}