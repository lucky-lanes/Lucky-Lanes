
package main.formObjects;

import main.java.Database;

/**
 *
 * @author Nick Bentzen
 */
public class YBalance
{
    private double rightLimbLength,
            antRightMean, antLeftMean,
            pmRightMean, pmLeftMean,
            plRightMean, plLeftMean, antR1, antR2, antR3, antL1, antL2, antL3, pmR1, pmR2, pmR3,
            pmL1, pmL2, pmL3, plR1, plR2, plR3, plL1, plL2, plL3;
    private double compositeLeft;
    private double compositeRight;

    /**
     * Constructor that takes in all required fields.
     * 
     * @param rightLimbLength
     * @param antR1
     * @param antR2
     * @param antR3
     * @param antL1
     * @param antL2
     * @param antL3
     * @param pmR1
     * @param pmR2
     * @param pmR3
     * @param pmL1
     * @param pmL2
     * @param pmL3
     * @param plR1
     * @param plR2
     * @param plR3
     * @param plL1
     * @param plL2
     * @param plL3
     */
    public YBalance(double rightLimbLength, double antR1, double antR2, double antR3, double antL1,
            double antL2, double antL3, double pmR1, double pmR2, double pmR3, double pmL1, double pmL2,
            double pmL3, double plR1, double plR2, double plR3, double plL1, double plL2, double plL3)
    {
        this.pmL1=pmL1;
        this.pmL2=pmL2;
        this.pmL3=pmL3;
        this.pmR1=pmR1;
        this.pmR2=pmR2;
        this.pmR3=pmR3;
        this.plL1=pmL1;
        this.plL2=pmL2;
        this.plL3=pmL3;
        this.plR1=pmR1;
        this.plR2=pmR2;
        this.plR3=pmR3;
        this.antL1=antL1;
        this.antL2=antL2;
        this.antL3=antL3;
        this.antR1=antR1;
        this.antR2=antR2;
        this.antR3=antR3;     
        this.rightLimbLength = rightLimbLength;
        this.antRightMean = Math.max(Math.max(antR1, antR2), antR3);
        this.antLeftMean = Math.max(Math.max(antL1, antL2), antL3);
        this.pmRightMean = Math.max(Math.max(pmR1, pmR2), pmR3);
        this.pmLeftMean = Math.max(Math.max(pmL1, pmL2), pmL3);
        this.plRightMean = Math.max(Math.max(plR1, plR2), plR3);
        this.plLeftMean = Math.max(Math.max(plL1, plL2), plL3);
        
        this.compositeLeft=((this.antLeftMean + this.pmLeftMean + this.plLeftMean) / (3 * this.rightLimbLength)) * 100;
        this.compositeRight=((this.antRightMean + this.pmRightMean + this.plRightMean) / (3 * this.rightLimbLength)) * 100;
    }

    /**
     *
     * @return
     */
    public String toHTML()
    {
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
                        +"</table>";
        
                /*
                this.antRightMean = Math.max(Math.max(antR1, antR2), antR3);
        this.antLeftMean = Math.max(Math.max(antL1, antL2), antL3);
        this.pmRightMean = Math.max(Math.max(pmR1, pmR2), pmR3);
        this.pmLeftMean = Math.max(Math.max(pmL1, pmL2), pmL3);
        this.plRightMean = Math.max(Math.max(plR1, plR2), plR3);
        this.plLeftMean = Math.max(Math.max(plL1, plL2), plL3);        
                */
                
        return html;
    }

    /**
     * Empty constructor
     */
    public YBalance()
    {

    }

    /**
     * Sets the rightLimbLength in cm
     * @param rightLimbLength
     */
    public void setRightLimbLength(double rightLimbLength)
    {
        this.rightLimbLength = rightLimbLength;
    }

    /**
     * Takes in three measurements for right anterior and sets the mean
     * @param ant1
     * @param ant2
     * @param ant3
     */
    public void setRightAntMean(double ant1, double ant2, double ant3)
    {
        this.antRightMean = (ant1 + ant2 + ant3) / 3.0;
    }

    /**
     * Takes in three measurements for left anterior and sets the mean
     * @param ant1
     * @param ant2
     * @param ant3
     */
    public void setLeftAntMean(double ant1, double ant2, double ant3)
    {
        this.antLeftMean = (ant1 + ant2 + ant3) / 3.0;
    }

    /**
     * Takes in 3 measurements for posteromedial and sets the mean
     * @param num1
     * @param num2
     * @param num3
     */
    public void setPmRightMean(double num1, double num2, double num3)
    {
        this.pmRightMean = (num1 + num2 + num3) / 3.0;
    }

    /**
     * Takes in 3 measurements for posteromedial and sets the mean
     * @param num1
     * @param num2
     * @param num3
     */
    public void setPmLeftMean(double num1, double num2, double num3)
    {
        this.pmLeftMean = (num1 + num2 + num3) / 3.0;
    }
    
    /**
     * Takes in 3 measurements for posteromedial and sets the mean.
     * @param num1
     * @param num2
     * @param num3
     */
    public void setPLRightMean(double num1, double num2, double num3)
    {
        this.plRightMean = (num1 + num2 + num3) / 3.0;
    }

    /**
     * Takes in 3 measurements for posteromedial and sets the mean.
     * @param num1
     * @param num2
     * @param num3
     */
    public void setPLLeftMean(double num1, double num2, double num3)
    {
        this.plLeftMean = (num1 + num2 + num3) / 3.0;
    }

    /**
     *
     * @return Returns anterior difference.
     */
    public double getAntDiff()
    {
        return this.antRightMean - this.antLeftMean;
    }

    /**
     * 
     * @return Returns posteromedial difference.
     */
    public double getPmDiff() {
        return this.pmRightMean - this.pmLeftMean;
    }

    /**
     *
     * @return Returns posterolateral difference.
     */
    public double getPlDiff()
    {
        return this.plRightMean - this.plLeftMean;
    }

    /**
     *
     * @return Returns composite right score.
     */
    public double getRightScore()
    {
        return this.pmRightMean + this.plRightMean + this.antRightMean;
    }

    /**
     *
     * @return Returns composite left score.
     */
    public double getLeftScore()
    {
        return this.pmLeftMean + this.plLeftMean + this.antLeftMean;
    }

    /**
     *
     * @return
     */
    public double getRightLimbLength()
    {
        return rightLimbLength;
    }

    /**
     *
     * @return
     */
    public double getAntRightMean()
    {
        return antRightMean;
    }

    /**
     *
     * @return
     */
    public double getAntLeftMean()
    {
        return antLeftMean;
    }

    /**
     *
     * @return
     */
    public double getPmRightMean()
    {
        return pmRightMean;
    }

    /**
     *
     * @return
     */
    public double getPmLeftMean()
    {
        return pmLeftMean;
    }

    /**
     *
     * @return
     */
    public double getPlRightMean()
    {
        return plRightMean;
    }

    /**
     *
     * @return
     */
    public double getPlLeftMean()
    {
        return plLeftMean;
    }
   
    /**
     * Adds a row to the database class. 
     * It is used in conjunction with the other forms, since the value for 
     * each table is autoincremented. 
     */
    public void addRow()
    {
        String sql;
            sql = "INSERT INTO YBALANCE VALUES ("
                + "null,"
                + rightLimbLength +","
                    + antRightMean +","+antLeftMean +","
                    + pmRightMean +","+pmLeftMean +","
                    + plRightMean +","+plLeftMean +","
                    + antR1 +","+antR2 +","+antR3 +","
                    + antL1 +","+antL2 +","+antL3 +","
                    + pmR1 +","+pmR2 +","+pmR3 +","
                    + pmL1 +","+pmL2 +","+pmL3 +","
                    + plR1 +","+plR2 +","+plR3 +","
                    + plL1 +","+plL2 +","+plL3 +","
                    + compositeLeft +","+compositeRight +");";
            
        Database.executeUpdate(sql);
    }
}
