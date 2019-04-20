
package main.formObjects;

import main.java.Database;

/**
 * FMS Score Sheet Model Object.
 *
 * It represents the form used to score each athlete.
 *
 * It contains a total of 7 tests and uses their final score to generate a final
 * report or score.
 *
 */
public class FMS
{
    private int deepSquatRaw, deepSquatFinal;
    private int hurdleStepRawL, hurdleStepRawR, hurdleStepFinal;
    private int inlineLoungeRawL, inlineLoungeRawR, inlineLoungeFinal;
    private int shoulderMobilityRawL, shoulderMobilityRawR, shoulderMobilityFinal;
    private boolean shoulderClearingL, shoulderClearingR;
    private int legRaiseRawL, legRaiseRawR, legRaiseFinal;
    private int trunkStabilityRaw, trunkStabilityFinal;
    private boolean extensionClearing;
    private int rotaryRawL, rotaryRawR, rotaryFinal;
    private boolean flexionClearing;
    private int total;
    
    private String deepSquatComment,hurdleStepComment,inlineLoungeComment,shoulderMobilityComment,shoulderClearingComment,
            legRaiseComment, trunkStabilityComment,extensionClearingComment,rotaryComment,flexionComment;
    
    /**
     *
     * @param deepSquatRaw
     * @param deepSquatRaw
     * @param hurdleStepRawL
     * @param hurdleStepRawR
     * @param hurdleStepRawR
     * @param inlineLoungeRawL
     * @param inlineLoungeRawR
     * @param shoulderMobilityRawL
     * @param shoulderMobilityRawR
     * @param shoulderMobilityRawR
     * @param shoulderClearingR
     * @param trunkStabilityRaw
     * @param shoulderClearingL
     * @param shoulderClearingR
     * @param rotaryRawR
     * @param legRaiseRawL
     * @param legRaiseRawR
     * @param legRaiseRawL
     * @param legRaiseRawR
     * @param trunkStabilityRaw
     * @param extensionClearing
     * @param rotaryRawL
     * @param rotaryRawL
     * @param rotaryRawR
     * @param total
     * @param flexionClearing
     * @param total
     */
    public FMS(int deepSquatRaw, int hurdleStepRawL, int hurdleStepRawR, int inlineLoungeRawL, int inlineLoungeRawR,
            int shoulderMobilityRawL, int shoulderMobilityRawR,boolean shoulderClearingL,boolean shoulderClearingR,
            int legRaiseRawL, int legRaiseRawR, int trunkStabilityRaw,boolean extensionClearing, int rotaryRawL, int rotaryRawR,
            boolean flexionClearing, int total)
    {        
        this.deepSquatRaw = deepSquatRaw;
        this.deepSquatFinal = deepSquatRaw;
        this.hurdleStepRawL=hurdleStepRawL;
        this.hurdleStepRawR=hurdleStepRawR;
        this.hurdleStepFinal=Math.min(hurdleStepRawL, hurdleStepRawR);
        this.inlineLoungeRawL=inlineLoungeRawL;
        this.inlineLoungeRawR=inlineLoungeRawR;
        this.inlineLoungeFinal =Math.min(inlineLoungeRawL, inlineLoungeRawR);
        this.shoulderMobilityRawL=shoulderMobilityRawL;
        this.shoulderMobilityRawR=shoulderMobilityRawR;
        
        //If clearing test are true then set score to 0
        
        this.shoulderClearingL=shoulderClearingL;
        this.shoulderClearingR=shoulderClearingR;
        this.shoulderMobilityFinal =(shoulderClearingL || shoulderClearingR)?0:Math.min(shoulderMobilityRawL, shoulderMobilityRawR);               
        this.legRaiseRawL=legRaiseRawL;
        this.legRaiseRawR=legRaiseRawR;
        this.legRaiseFinal =Math.min(legRaiseRawL, legRaiseRawR);
        this.trunkStabilityRaw = trunkStabilityRaw;
        this.extensionClearing = extensionClearing;
        this.trunkStabilityFinal =(extensionClearing)?0:trunkStabilityRaw;
        this.rotaryRawL=rotaryRawL;
        this.rotaryRawR=rotaryRawR;
        this.flexionClearing = flexionClearing;
        this.rotaryFinal= (flexionClearing)? 0 : Math.min(rotaryRawL, rotaryRawR);
        this.total = total;
        
               
        this.deepSquatComment ="none";   // deepSquatComment;
        this.hurdleStepComment ="none";   // hurdleStepComment;
        this.inlineLoungeComment ="none";   // inlineLoungeComment;
        this.shoulderMobilityComment ="none";   // shoulderMobilityComment;
        this.shoulderClearingComment ="none";   // shoulderClearingComment;
        this.legRaiseComment ="none";   // legRaiseComment;
        this.trunkStabilityComment ="none";   // trunkStabilityComment;
        this.extensionClearingComment ="none";   // extensionClearingComment;
        this.rotaryComment ="none";   // rotaryComment;
        this.flexionComment ="none";   // flexionComment;
    }
    
    /**
     *
     * @param deepSquatComment
     * @param hurdleStepComment
     * @param inlineLoungeComment
     * @param shoulderMobilityComment
     * @param shoulderClearingComment
     * @param legRaiseComment
     * @param trunkStabilityComment
     * @param extensionClearingComment
     * @param rotaryComment
     * @param flexionComment
     */
    public void setComments(String deepSquatComment, String hurdleStepComment, String inlineLoungeComment, String shoulderMobilityComment,
            String shoulderClearingComment, String legRaiseComment, String trunkStabilityComment, String extensionClearingComment,
            String rotaryComment, String flexionComment)
    {
        this.deepSquatComment ="";   // deepSquatComment;
        this.hurdleStepComment ="";   // hurdleStepComment;
        this.inlineLoungeComment ="";   // inlineLoungeComment;
        this.shoulderMobilityComment ="";   // shoulderMobilityComment;
        this.shoulderClearingComment ="";   // shoulderClearingComment;
        this.legRaiseComment ="";   // legRaiseComment;
        this.trunkStabilityComment ="";   // trunkStabilityComment;
        this.extensionClearingComment ="";   // extensionClearingComment;
        this.rotaryComment ="";   // rotaryComment;
        this.flexionComment ="";   // flexionComment;
    }
    
    /**
     * Adds a row to the database class. 
     * It is used in conjunction with the other forms, since the value for 
     * each table is autoincremented. 
     */
    public void addRow()
    {
        String sql;
            sql = "INSERT INTO FMS VALUES ("
                + "null,"
                    + deepSquatRaw+","+ deepSquatFinal+","
                    + hurdleStepRawL+","+ hurdleStepRawR+","+ hurdleStepFinal+","
                    + inlineLoungeRawL+","+ inlineLoungeRawR+","+ inlineLoungeFinal+","
                    + shoulderMobilityRawL+","+ shoulderMobilityRawR+","+ shoulderMobilityFinal+","
                    + shoulderClearingL+","+ shoulderClearingR+","
                    + legRaiseRawL+","+ legRaiseRawR+","+ legRaiseFinal+","
                    + trunkStabilityRaw+","+ trunkStabilityFinal+","
                    + extensionClearing+","
                    + rotaryRawL+","+ rotaryRawR+","+ rotaryFinal+","
                    + flexionClearing+","+total+");";
            
        Database.executeUpdate(sql);
    }
    
    /**
     *
     * @return
     */
    public String toHTML()
    {
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
                + "<tr><th rowspan='2'>SHOULDER CLEARING  TEST:</th><th>L</th><td>" + ((shoulderClearingL)?"+":"-")
                        + "</td><td rowspan='2'>" + shoulderClearingComment + "</td></tr><tr><th>R</th><td>"
                        + ((shoulderClearingR)?"+":"-") + "</td></tr>"
                + "<tr><th rowspan='2'>ACTIVE STRAIGHT-LEG RAISE:</th><th>L</th><td>" + legRaiseRawL+"</td><td rowspan='2'>"
                        + legRaiseFinal + "</td><td rowspan='2'>" + legRaiseComment + "</td></tr><tr><th>R</th><td>" + legRaiseRawR
                        + "</td></tr>"
                + "<tr><th>TRUNK STABILITY PUSHUP:</th><th></th><td>" + trunkStabilityRaw + "</td><td rowspan='2'>" + trunkStabilityFinal
                        + "</td><td>" + trunkStabilityComment + "</td></tr>"
                + "<tr><th>EXTENSION CLEARING TEST:</th><th></th><td>" + ((extensionClearing)?"+":"-") + "</td><td>"
                        + extensionClearingComment + "</td></tr>"
                + "<tr><th rowspan='2'>ROTARY STABILITY:</th><th>L</th><td>" + rotaryRawL + "</td><td rowspan='3'>" + rotaryFinal
                        + "</td><td rowspan='2'>" + rotaryComment + "</td></tr><tr><th>R</th><td>" + rotaryRawR + "</td></tr>"
                + "<tr><th>FLEXION CLEARING TEST:</th><th></th><td>" + ((flexionClearing)?"+":"-") + "</td><td>" + flexionComment
                        + "</td></tr>"
                + "<tr><th>TOTAL:</th><th></th><td colspan='2'>" + total + "</td><td></td></tr>"
                + "</table>";
        
        return html;
    }

}
