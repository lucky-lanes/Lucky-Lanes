
package main.formObjects;

import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 *
 * @author nicholas bentzen
 * This class is to implement the equations in "Aerobic Test Instructions and Norms"
 * pdf document.
 * 
 */
public class Aerobic
{
    private int sexInt, age;
    private double v02bench, v02walk, v02Rockport;
    private String sex, benchRating;

    /**
     * Constructor takes an integer age, and string sex.
     * @param age
     * @param sex
     * @throws FileNotFoundException
     */
    public Aerobic(int age, String sex) throws FileNotFoundException
    {
        this.age = age;
        
        if(sex == "male")
        {
            this.sexInt = 1;
        }
        else
        {
            this.sexInt = 0;
        }
    }

    /**
     * Sets the v02max for the benchtest.(15 second test)
     * @param heartRate
     */
    public void setBench(int heartRate)
    {
        this.v02bench = (-0.9675 * heartRate) + 76.710;
    }

    /**
     *
     * @return The bench rating (v02max for the benchtest).
     */
    public double getBench()
    {
        return this.v02bench;
    }
    
    /**
     * Determines the bench rating. Ex: poor, very poor, excellent, etc.
     * @param heartRate
     * @param fileName
     * @throws FileNotFoundException
     */
    public void setBenchRating(int heartRate, String fileName) throws FileNotFoundException
    {
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
     *
     * @return The rating, Ex: Excellent, Good, etc.
     */
    public String getBenchRating()
    {
        return this.benchRating;
    }

    /**
     * Sets the v02max for the RockPort test.
     * @param weight
     * @param walkTime
     * @param heartRate
     */
    public void setRockport(double weight, double walkTime, int heartRate)
    {
        double a, b, c, d, e;
        
        if(this.age <= 25)
        {
            a = 88.767;
            b = 8.892;
            c = 0.2109;
            d = 1.4537;
            e = 0.1194;
        }
        else
        {
            a = 88.767;
            b = 6.315;
            c = 0.3877;
            d = 0.1692;
            e = 3.2649;
        }
        
        this.v02Rockport = a + (this.sexInt * 8.892) - (weight * 0.2109) - (walkTime * 1.4537) - (heartRate * 0.1194);
    }

    /**
     * @return The v02max for the rockport test.
     */
    public double getRockport()
    {
        return this.v02Rockport;
    }

    /**
     * Calculates 12 min walk in meters.
     * @param distance
     */
    public void set12minWalk(double distance)
    {
        this.v02walk = (distance - 504.9)/44.73;
    }

    /**
     *
     * @return The v02max for the 12 min walk test.
     */
    public double get12minWalk()
    {
        return this.v02walk;
    }
    
    /**
     * Helper method: calculates the age demographic of an athlete to determine
     * appropriate range of heart beats for a given score.
     * @param num
     * @param arr
     * @return 
     */
    private int calcIndex(int num, int[] arr)
    {
        for(int i = 0; i < arr.length; i++)
        {
            if(i == arr.length - 1)
            {
                if(arr[i] > num)
                {
                    return i;
                }
            }
            else
            {
                if(compareNums(num, arr[i], arr[i+1]))
                {
                    return i;
                }
            }
        }
        
        return -1;
    }
    
    /**
     * Method takes in 3 numbers: mid, low  high; method returns if mid is >= low and < high.
     * @param mid
     * @param low
     * @param high
     * @return 
     */
    private boolean compareNums(int mid, int low, int high)
    {
        return low <= mid && mid < high;
    }
}
