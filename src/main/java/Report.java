package main.java;

import main.formObjects.*;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class creates the necessary objects to generate reports, from
 * the user selection.
 * It creates instances of the Objects, using the data fetched from the
 * database.
 *
 * @author Mario
 * EDITED BY: Joshua Bolstad
 */
public class Report {
    /**
     * Array holding all ID numbers of bowlers to generate report on
     */
    ArrayList<Integer> ids;
    
    /**
     * Array to hold all FMS objects of the bowlers
     */
    ArrayList<FMS> fms;
    
    /**
     * Array to hold all Athlete objects of the bowlers
     */
    ArrayList<Athlete> athlete;
    
    /**
     * Array to hold all YBalance objects of the bowlers
     */
    ArrayList<YBalance> yBalance;
    
    /**
     * Array to hold all FitnessData objects of the bowlers
     */
    ArrayList<FitnessTest> fitnessData;
    
    /**
     * Array to hold all ParQ objects of the bowlers
     */
    ArrayList<ParQ> parQ;
    private Executor exec;

    /**
     * Instantiates the arraylists to store all the objects to be printed.
     */
    public Report() {
        ids = new ArrayList<>();
        fms = new ArrayList<>();
        athlete = new ArrayList<>();
        yBalance = new ArrayList<>();
        fitnessData = new ArrayList<>();
        parQ = new ArrayList<>();

        exec = Executors.newCachedThreadPool(runnable ->
        {
            Thread t = new Thread(runnable);
            t.setDaemon(true);

            return t;
        });
    }

    /**
     * Use to keep track of the bowlers to be printed.
     *
     * @param id ID number of the bowler to be printed
     */
    public void addID(int id) {
        ids.add(id);
    }

    /**
     * This method uses the HTML class to generate the output file.
     */
    public void toDocs() throws IOException {
        System.out.println("Saving Documents");

        HTML.mkdir();
        System.out.println("Saving Documents");

        Document_Creator dc = new Document_Creator();

        //generate a pdf for each id number in the ids list
        for (int i = 0; i < ids.size(); i++) {
            String html = "";
            FMS fmsOut = fms.get(i);
            Athlete athOut = athlete.get(i);
            FitnessTest ftdOut = fitnessData.get(i);
            YBalance ybOut = yBalance.get(i);
            ParQ parqOut = parQ.get(i);
            String athString = athOut.toPDF();
            String fmsString = fmsOut.toPDF();
            String yBString = ybOut.toPDF();
            String ftdString = ftdOut.toPDF();
            String parqString = parqOut.toPDF();
            String allPDF = athString + "|" + fmsString + "|" + yBString + "|" + ftdString + "|" + parqString;
            dc.createPDF(allPDF);
            

          /*  html += athOut.toHTML();
            html += "<br><br><br><br>";
            html += fmsOut.toHTML();
            html += "<br><br><br><br>";
            html += ybOut.toHTML();
            html += "<br><br><br><br>";
            html += ftdOut.toHTML();
            html += "<br><br><br><br>";
            html += parqOut.toHTML();
            
            HTML.createFile("Bowler-" + ids.get(i));
            HTML.print(html); 
          */
        }
    }

    /**
     * This method gets the data on each bowler, from the database, whose ID number is in the ids ArrayList
     * <p>
     * It also calls the appropriate helper methods to generate an FMS, Athlete, FitnessData, YBalance, and ParQ object
     * which it adds to the appropriate ArrayList
     */
    public void createObjects() {
        Database.connect();

        for (int id : ids) {
            ResultSet rsFMS = Database.searchQuery("SELECT * FROM FMS WHERE ID=" + id + ";");
            ResultSet rsAth = Database.searchQuery("SELECT * FROM Athlete WHERE ID=" + id + ";");
            ResultSet rsYBal = Database.searchQuery("SELECT * FROM YBalance WHERE ID=" + id + ";");
            ResultSet rsFitData = Database.searchQuery("SELECT * FROM FitnessData WHERE ID=" + id + ";");
            ResultSet rsParQ = Database.searchQuery("SELECT * FROM ParQ WHERE ID=" + id + ";");

            fms.add(fetchFMS(rsFMS));
            athlete.add(fetchAthlete(rsAth));
            fitnessData.add(fetchFitnessTest(rsFitData));
            yBalance.add(fetchYBalance(rsYBal));
            parQ.add(fetchParQ(rsParQ));

            System.out.println("Fetched Data Succesfully");
        }

        Database.close();
    }

    /**
     * Pulls the bowler's FMS data from the FMS ResultSet and makes/returns a FMS object
     *
     * @param rs A ResultSet object (from a query on the database) which contains a bowler's FMS data
     * @return An FMS object which contains a bowler's FMS data
     */
    private FMS fetchFMS(ResultSet rs) {
        FMS temp = null;

        try {
            if (!rs.next()) {

            }

            int deepSquatRaw = Integer.parseInt(rs.getString("deepSquatRaw"));

            //int deepSquatFinal = Integer.parseInt(rs.getString("deepSquatFinal"));

            int hurdleStepRawL = Integer.parseInt(rs.getString("hurdleStepRawL"));
            int hurdleStepRawR = Integer.parseInt(rs.getString("hurdleStepRawR"));

            //int hurdleStepFinal=Integer.parseInt(rs.getString("hurdleStepFinal"));

            int inlineLoungeRawL = Integer.parseInt(rs.getString("inlineLoungeRawL"));
            int inlineLoungeRawR = Integer.parseInt(rs.getString("inlineLoungeRawR"));

            //int inlineLoungeFinal =Integer.parseInt(rs.getString("inlineLoungeFinal"));

            int shoulderMobilityRawL = Integer.parseInt(rs.getString("shoulderMobilityRawL"));
            int shoulderMobilityRawR = Integer.parseInt(rs.getString("shoulderMobilityRawR"));

            //If clearing test are true 
            boolean shoulderClearingL = (rs.getString("shoulderClearingL").equals("True"));
            boolean shoulderClearingR = (rs.getString("shoulderClearingR").equals("True"));

            //int shoulderMobilityFinal =Integer.parseInt(rs.getString("shoulderMobilityFinal"));

            int legRaiseRawL = Integer.parseInt(rs.getString("legRaiseRawL"));
            int legRaiseRawR = Integer.parseInt(rs.getString("legRaiseRawR"));

            //int legRaiseFinal =Integer.parseInt(rs.getString("legRaiseFinal"));

            int trunkStabilityRaw = Integer.parseInt(rs.getString("trunkStabilityRaw"));
            boolean extensionClearing = (rs.getString("extensionClearing").equals("True"));

            // int trunkStabilityFinal =Integer.parseInt(rs.getString("trunkStabilityFinal"));

            int rotaryRawL = Integer.parseInt(rs.getString("rotaryRawL"));
            int rotaryRawR = Integer.parseInt(rs.getString("rotaryRawR"));
            boolean flexionClearing = (rs.getString("flexionClearing").equals("True"));

            //int rotaryFinal= Integer.parseInt(rs.getString("rotaryFinal"));

            int total = Integer.parseInt(rs.getString("total"));
            

            temp = (new FMS(deepSquatRaw, hurdleStepRawL, hurdleStepRawR, inlineLoungeRawL, inlineLoungeRawR,
                    shoulderMobilityRawL, shoulderMobilityRawR, shoulderClearingL, shoulderClearingR,
                    legRaiseRawL, legRaiseRawR, trunkStabilityRaw, extensionClearing, rotaryRawL, rotaryRawR,
                    flexionClearing, total));

        } catch (SQLException ex) {
            Logger.getLogger(Report.class.getName()).log(Level.SEVERE, null, ex);
        }
        return temp;
    }

    /**
     * Pulls the bowler's personal data from the personal data ResultSet and makes/returns an Athlete object
     *
     * @param rs A ResultSet object (from a query on the database) which contains a bowler's personal data
     * @return An Athlete object which contains a bowler's personal data
     */
    private Athlete fetchAthlete(ResultSet rs) {
        Athlete temp = null;

        try {
            while (rs.next()) {
                String name = rs.getString("name");
                String date = rs.getString("date");

                String address = rs.getString("address");
                String city = rs.getString("city");
                String state = rs.getString("state");
                int zip = Integer.parseInt(rs.getString("zip"));
                String phone = rs.getString("phone");
                String school = rs.getString("school");
                double height = Double.parseDouble(rs.getString("height"));
                double weight = Double.parseDouble(rs.getString("weight"));
                int age = Integer.parseInt(rs.getString("age"));
                String dateOfBirth = rs.getString("dateOfBirth");
                String gender = rs.getString("gender");
                String handDominance = rs.getString("handDominance");
                String legDominance = rs.getString("legDominance");
                String primarySport = rs.getString("primarySport");
                String primaryPosition = rs.getString("primaryPosition");

                temp = (new Athlete(name, date, dateOfBirth, address, city, state, zip, phone, school, height, weight, age, gender,
                        handDominance, legDominance, primarySport, primaryPosition));

            }
        } catch (SQLException ex) {
            Logger.getLogger(Report.class.getName()).log(Level.SEVERE, null, ex);
        }
        return temp;
    }

    /**
     * Pulls the bowler's fitness test data from the fitnesstest ResultSet and makes/returns a FitnessTest object
     *
     * @param rs A ResultSet object (from a query on the database) which contains a bowler's FitnessTest data
     * @return A FitnessTest object which contains a bowler's FitnessTest data
     */
    private FitnessTest fetchFitnessTest(ResultSet rs) {
        FitnessTest temp = null;

        try {
            while (rs.next()) {
                String gender = rs.getString("gender");

                int restingHR = Integer.parseInt(rs.getString("restingHR"));
                int restingBPA = Integer.parseInt(rs.getString("RESTINGHR1"));
                int restingBPB = Integer.parseInt(rs.getString("RESTINGHR2"));

                double bmi = Double.parseDouble(rs.getString("bmi"));
                double peakFlow = Double.parseDouble(rs.getString("peakFlow"));
                double height = Double.parseDouble(rs.getString("height"));
                double weight = Double.parseDouble(rs.getString("BODYWEIGHT"));

                int age = Integer.parseInt(rs.getString("restingHR"));

                double ant1 = Double.parseDouble(rs.getString("ant1"));
                double ant2 = Double.parseDouble(rs.getString("ant2"));
                double wCirc = Double.parseDouble(rs.getString("WAISTCIRC"));
                double hCirc = Double.parseDouble(rs.getString("HIPCIRC"));
                double midCirc = Double.parseDouble(rs.getString("MIDTHIGHCIRC"));
                double fCirc = Double.parseDouble(rs.getString("FLEXARMCIRC"));
                double hamCSA = Double.parseDouble(rs.getString("hamCSA"));
                double quadCSA = Double.parseDouble(rs.getString("quadCSA"));
                double totalCSA = Double.parseDouble(rs.getString("TOTALTHIGHCSA"));
                double startDist = Double.parseDouble(rs.getString("startDist"));
                double biCirc = Double.parseDouble(rs.getString("biCirc")); //EDITED

                double endDist1 = Double.parseDouble(rs.getString("endDist1"));
                double endDist2 = Double.parseDouble(rs.getString("endDist2"));
                double endDist3 = Double.parseDouble(rs.getString("endDist3"));
                double endDist = Double.parseDouble(rs.getString("FINALDIST"));
                double hgR1 = Double.parseDouble(rs.getString("hgR1"));
                double hgR2 = Double.parseDouble(rs.getString("hgR2"));
                double hgR3 = Double.parseDouble(rs.getString("hgR3"));

                double hgL1 = Double.parseDouble(rs.getString("hgL1"));
                double hgL2 = Double.parseDouble(rs.getString("hgL2"));
                double hgL3 = Double.parseDouble(rs.getString("hgL3"));

                double proneTime = Double.parseDouble(rs.getString("proneTime"));
                double kneeExtForceR1 = Double.parseDouble(rs.getString("kneeExtForceR1"));
                double kneeExtForceR2 = Double.parseDouble(rs.getString("kneeExtForceR2"));
                double kneeExtForceL1 = Double.parseDouble(rs.getString("kneeExtForceL1"));
                double kneeExtForceL2 = Double.parseDouble(rs.getString("kneeExtForceL2"));

                double jh1 = Double.parseDouble(rs.getString("jh1"));
                double jh2 = Double.parseDouble(rs.getString("jh2"));
                double medPass1 = Double.parseDouble(rs.getString("medPass1"));
                double medPass2 = Double.parseDouble(rs.getString("medPass2"));
                int postHR = Integer.parseInt(rs.getString("postHR"));
                double postVO2Max = Double.parseDouble(rs.getString("postVO2Max"));
                double vO2Max = Double.parseDouble(rs.getString("vO2Max"));
                int rockHR = Integer.parseInt(rs.getString("rockHR"));

                double walkTime = Double.parseDouble(rs.getString("walkTime"));
                double rockVO2Max = Double.parseDouble(rs.getString("rockVO2Max"));
                double walkDistance = Double.parseDouble(rs.getString("walkDistance"));
                double walkVO2Max = Double.parseDouble(rs.getString("walkVO2Max"));
                double ageRating = Double.parseDouble(rs.getString("ageRating"));
                double ACSMpercentile = Double.parseDouble(rs.getString("ACSMpercentile"));

                //EDITED 
                double triSkin = Double.parseDouble(rs.getString("pecSkin"));
                double subSkin = Double.parseDouble(rs.getString("subSkin"));
                double abdSkin = Double.parseDouble(rs.getString("abdSkin"));
                double supSkin = Double.parseDouble(rs.getString("supSkin"));
                double thighSkin = Double.parseDouble(rs.getString("thighSkin"));
                double pecSkin = Double.parseDouble(rs.getString("pecSkin"));
                double wallsit = Double.parseDouble(rs.getString("wallsit"));

                temp = new FitnessTest();
                temp.setVitals(age, restingHR, restingBPA, restingBPB, height, weight, bmi, gender, peakFlow);
                temp.setAnthro(ant1, ant2, wCirc, hCirc, midCirc, fCirc, hamCSA, quadCSA, totalCSA, biCirc); //EDITED
                temp.setSkin(triSkin, subSkin, abdSkin, supSkin, thighSkin, pecSkin, wallsit); //EDITED
                temp.setSitAndReach(startDist, endDist1, endDist2, endDist3, endDist);
                temp.setMuscleAndStrength(hgR1, hgR2, hgR3, hgL1, hgL2, hgL3, proneTime, kneeExtForceR1, kneeExtForceR2, kneeExtForceL1, kneeExtForceL2, jh1, jh2, medPass1, medPass2);
                temp.setAerobicCapacity(vO2Max, postHR, postVO2Max, ageRating, rockHR, walkTime, rockVO2Max, walkDistance, walkVO2Max, ACSMpercentile);

            }
        } catch (SQLException ex) {
            Logger.getLogger(Report.class.getName()).log(Level.SEVERE, null, ex);
        }

        return temp;
    }
    
    /**
     * Pulls the bowler's Y-Balance test data from the Y-Balance ResultSet and makes/returns a YBalance object
     *
     * @param rs A ResultSet object (from a query on the database) which contains a bowler's Y-Balance test data
     * @return A YBalance object which contains a bowler's Y-Balance test data
     */
    private YBalance fetchYBalance(ResultSet rs) {
        YBalance temp = null;

        try {
            while (rs.next()) {
                double rightLimbLength, antR1, antR2, antR3, antL1, antL2, antL3, pmR1, pmR2, pmR3,
                        pmL1, pmL2, pmL3, plR1, plR2, plR3, plL1, plL2, plL3;

                rightLimbLength = Double.parseDouble(rs.getString("rightLimbLength"));

                antR1 = Double.parseDouble(rs.getString("antR1"));
                antR2 = Double.parseDouble(rs.getString("antR2"));
                antR3 = Double.parseDouble(rs.getString("antR3"));
                antL1 = Double.parseDouble(rs.getString("antL1"));
                antL2 = Double.parseDouble(rs.getString("antL2"));
                antL3 = Double.parseDouble(rs.getString("antL3"));

                pmR1 = Double.parseDouble(rs.getString("pmR1"));
                pmR2 = Double.parseDouble(rs.getString("pmR2"));
                pmR3 = Double.parseDouble(rs.getString("pmR3"));
                pmL1 = Double.parseDouble(rs.getString("pmL1"));
                pmL2 = Double.parseDouble(rs.getString("pmL2"));
                pmL3 = Double.parseDouble(rs.getString("pmL3"));

                plL1 = Double.parseDouble(rs.getString("plL1"));
                plL2 = Double.parseDouble(rs.getString("plL2"));
                plL3 = Double.parseDouble(rs.getString("plL3"));
                plR1 = Double.parseDouble(rs.getString("plR1"));
                plR2 = Double.parseDouble(rs.getString("plR2"));
                plR3 = Double.parseDouble(rs.getString("plR3"));

                temp = (new YBalance(rightLimbLength, antR1, antR2, antR3, antL1, antL2, antL3, pmR1, pmR2, pmR3,
                        pmL1, pmL2, pmL3, plR1, plR2, plR3, plL1, plL2, plL3));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Report.class.getName()).log(Level.SEVERE, null, ex);
        }

        return temp;
    }
    
    /**
     * Pulls the bowler's answers from the PAR-Q (PHYSICAL ACTIVITY READINESS QUESTIONNAIRE) ResultSet 
     * and makes/returns a ParQ object
     *
     * @param rs A ResultSet object (from a query on the database) which contains a bowler's PAR-Q answers
     * @return A ParQ object which contains a bowler's PAR-Q answers
     * @author Joshua Bolstad
     */
    private ParQ fetchParQ(ResultSet rs) {
        ParQ temp = null;

        try {
            if (!rs.next()) {

            }

            boolean q1Ans = rs.getBoolean("q1Ans");
            boolean q2Ans = rs.getBoolean("q2Ans");
            boolean q3Ans = rs.getBoolean("q3Ans");
            boolean q4Ans = rs.getBoolean("q4Ans");
            boolean q5Ans = rs.getBoolean("q5Ans");
            boolean q6Ans = rs.getBoolean("q6Ans");
            String q7Ans = rs.getString("q7Ans");


            temp = (new ParQ(q1Ans, q2Ans, q3Ans, q4Ans, q5Ans, q6Ans, q7Ans));

        } catch (SQLException ex) {
            Logger.getLogger(Report.class.getName()).log(Level.SEVERE, null, ex);
        }
        return temp;
    }
}
