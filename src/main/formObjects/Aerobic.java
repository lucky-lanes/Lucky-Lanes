package main.formObjects;

import java.io.FileNotFoundException;

/**
 * @author nicholas bentzen
 * This class is to implement the equations in "Aerobic Test Instructions and Norms"
 * pdf document.
 */
public class Aerobic {
    /**
     * The sex of the athlete. Is set in the constructor. 0 = female, 1 = male
     */
    private int sexInt;
    /**
     * The age of the athlete
     */
    private int age;
    /**
     * v02max for the YMCA bench test
     */
    private double v02bench;
    /**
     * v02max for the rockport walk test
     */
    private double v02Rockport;
    /**
     * v02max for the 12-minute walk test
     */
    private double v02walk;
    /**
     * String to hold the sex of the athlete
     */
    private String sex;
    /**
     * The age adjusted bench rating of the athlete. Ex. poor, very poor, excellent, etc.
     */
    private String benchRating;

    /**
     * Constructor takes an integer age, and string sex.
     *
     * @param age age of the athlete
     * @param sex sex of the gender
     * @throws FileNotFoundException
     */
    public Aerobic(int age, String sex) throws FileNotFoundException {
        this.age = age;

        if (sex == "male") {
            this.sexInt = 1;
        } else {
            this.sexInt = 0;
        }
    }

    /**
     * Sets the v02max for the benchtest.(15 second test)
     *
     * @param heartRate Heart rate of the athlete after the test
     */
    public void setBench(int heartRate) {
        this.v02bench = (-0.9675 * heartRate) + 76.710;
    }

    /**
     * Gets the v02max for the benchtest
     * @return The bench rating (v02max for the benchtest).
     */
    public double getBench() {
        return this.v02bench;
    }

    /**
     * Determines and sets the bench rating based off the results from the YMCA Step test. Ex: poor, very poor, excellent, etc.
     *
     * @param heartRate The heart rate after the test
     * @param fileName Location of a heart beat file
     * @throws FileNotFoundException
     */
    public void setBenchRating(int heartRate, String fileName) throws FileNotFoundException {
        int[] ageRange = {18, 26, 36, 46, 56, 65};
        int ageGroup = this.calcIndex(this.age, ageRange);
        System.out.println("age group: " + ageGroup);

        String[] rating =
                {
                        "Excellent", "Excellent/Good", "Good", "Good/Above Average", "Above Average",
                        "Above Average/Average", "Average", "Average/Below Average", "Below Average", "Below Average/Poor", "Poor",
                        "Poor/Very Poor", " Very Poor"
                };

        HeartBeatFileReader reader = new HeartBeatFileReader(fileName);
        int[] heartArr = reader.getArr(ageGroup);
        int heartGroup = this.calcIndex(heartRate, heartArr);

        System.out.println("Heart Rate Group: " + heartGroup);
        this.benchRating = rating[heartGroup];
    }

    /**
     * Getter method for the bench rating determined in setBenchRating(). Ex: poor, very poor, excellent, etc.
     *
     * @return The rating, Ex: Excellent, Good, etc.
     */
    public String getBenchRating() {
        return this.benchRating;
    }

    /**
     * Sets the v02max for the RockPort test.
     *
     * @param weight Weight of the athlete
     * @param walkTime Time it took the athlete to walk 1 mile
     * @param heartRate Heart rate of the athlete after their 1 mile walk
     */
    public void setRockport(double weight, double walkTime, int heartRate) {
        double a, b, c, d, e;

        if (this.age <= 25) {
            a = 88.767;
            b = 8.892;
            c = 0.2109;
            d = 1.4537;
            e = 0.1194;
        } else {
            a = 88.767;
            b = 6.315;
            c = 0.3877;
            d = 0.1692;
            e = 3.2649;
        }

        this.v02Rockport = a + (this.sexInt * 8.892) - (weight * 0.2109) - (walkTime * 1.4537) - (heartRate * 0.1194);
    }

    /**
     * Getter for the v02max after the rockport walk test
     *
     * @return The v02max for the rockport test. Unit is mL/kg/min
     */
    public double getRockport() {
        return this.v02Rockport;
    }

    /**
     * Calculates v02max for the 12-minute walk test
     *
     * @param distance The distance walked in meters
     */
    public void set12minWalk(double distance) {
        this.v02walk = (distance - 504.9) / 44.73;
    }

    /**
     * Getter for the v02max of the 12-minute walk test
     *
     * @return The v02max for the 12 min walk test.
     */
    public double get12minWalk() {
        return this.v02walk;
    }

    /**
     * Helper method: calculates the age demographic of an athlete to determine
     * appropriate range of heart beats for a given score.
     *
     * @param num Number to test for inside the arr array parameter
     * @param arr Array of values to determine what section the num parameter fits inside of
     * @return The value of the section the num parameter fits inside of. Doesn't return the index
     * of the spot, but rather the value held at a spot.
     */
    private int calcIndex(int num, int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            if (i == arr.length - 1) {
                if (arr[i] > num) {
                    return i;
                }
            } else {
                if (compareNums(num, arr[i], arr[i + 1])) {
                    return i;
                }
            }
        }
        return -1;
    }

    /**
     * Helper method for calcIndex(). Determines if the input variables, low, med, and high are in order.
     * <p>
     * For example, if low is 1, med is 2, and high is 3, then they are in order (1, 2, 3).
     * Another example, if low is 2, med is 1, and high is 3, then they are not in order (2, 1, 3).
     * </p><p>
     * In this, the low and mid variables can be the exact same number. For example, if low is 1, med is 1, and
     * high is 3, they are in order (1, 1, 2). 1 <= 1.
     * </p>
     *
     * @param mid
     * @param low
     * @param high
     * @return True if low is <= mid and mid < high. False if one (or both) of those conditions are not met
     */
    private boolean compareNums(int mid, int low, int high) {
        return low <= mid && mid < high;
    }
}