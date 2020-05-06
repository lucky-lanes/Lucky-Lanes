package main.formObjects;

import main.java.Database;

/**
 * FitnessTest object. This fitness test object is used to hold the information that is contained inside of the
 * Fitness Testing Data Sheet tab.
 * <p>
 * Holds getters/setters for all variables, as well as methods for converting the profile to HTML and a pdf, as well as
 * methods for interacting with the database
 * </p>
 * @author bentz
 * EDITED BY: Joshua Bolstad
 */
public class FitnessTest {
    //vitals
    /**
     * The athlete's age
     */
    private int age;
    /**
     * The athlete's resting heart rate in beats/minute
     */
    private int restingHR;
    /**
     * The over of the athlete's resting heart rate in mmHg
     */
    private int restingHR1;
    /**
     * The under of the athlete's resting heart rate in mmHg
     */
    private int restingHR2;
    /**
     * The athlete's height in centimeters (cm)
     */
    private double height;
    /**
     * The athlete's weight in kilograms (kg)
     */
    private double bodyWeight;
    /**
     * The athlete's BMI (kg/m^2)
     */
    private double bmi;
    /**
     * The athlete's peak flow of blood in L/minute
     */
    private double peakFlow;
    /**
     * The athlete's gender
     */
    private String gender;

    //anthropomeetrics
    /**
     * First measure of the Anterior thigh in millimeters (mm)
     */
    private double ant1;
    /**
     * Second measure of the Anterior thigh in millimeters (mm)
     */
    private double ant2;
    /**
     * Average of the two measures of the Anterior thigh in millimeters (mm)
     */
    private double antAvg;
    /**
     * Circumference of the athlete's waist in centimeters (cm)
     */
    private double waistCirc;
    /**
     * Circumference of the athlete's hip in centimeters (cm)
     */
    private double hipCirc;
    /**
     * Circumference of the athlete's mid-thigh in centimeters (cm)
     */
    private double midThighCirc;
    /**
     * Circumference of the athlete's flexed arm in centimeters (cm)
     */
    private double flexArmCirc;
    /**
     * Circumference of the athlete's bicep in centimeters (cm)
     */
    private double biCirc;
    /**
     * Cross sectional area of the athlete's hamstrings in centimeters squared (cm^2)
     */
    private double hamCSA;
    /**
     * Cross sectional area of the athlete's quadriceps in centimeters squared (cm^2)
     */
    private double quadCSA;
    /**
     * Cross sectional area of the athlete's total thigh in centimeters squared (cm^2)
     */
    private double totalThighCSA;

    //skinfold
    /**
     * Skin fold measurement for the triceps measured in millimeters (mm)
     */
    private double triSkin;
    /**
     * Skin fold measurement for the subscapular measured in millimeters (mm)
     */
    private double subSkin;
    /**
     * Skin fold measurement for the abdominal measured in millimeters (mm)
     */
    private double abdSkin;
    /**
     * Skin fold measurement for the suprailiac measured in millimeters (mm)
     */
    private double supSkin;
    /**
     * Skin fold measurement for the thigh measured in millimeters (mm)
     */
    private double thighSkin;
    /**
     * Skin fold measurement for the pectoral measured in millimeters (mm). Males only
     */
    private double pecSkin;
    /**
     * Skin fold measurement for the wallsit measured in centimeters (cm)
     */
    private double wallsit;

    //sit&reach
    /**
     * Sit and reach starting distance in centimeters (cm)
     */
    private double startDist;
    /**
     * Sit and reach end distance for the first attempt in centimeters (cm)
     */
    private double endDist1;
    /**
     * Sit and reach end distance for the second attempt in centimeters (cm)
     */
    private double endDist2;
    /**
     * Sit and reach end distance for the third attempt in centimeters (cm)
     */
    private double endDist3;
    /**
     * Sit and reach end distance for the final attempt in centimeters (cm)
     */
    private double finalDist;

    //Muscle strength & Endurance Power
    /**
     * First trial of hand grip of the right hand in kilograms (kg)
     */
    private double hgR1;
    /**
     * Second trial of hand grip of the right hand in kilograms (kg)
     */
    private double hgR2;
    /**
     * Third trial of hand grip of the right hand in kilograms (kg)
     */
    private double hgR3;
    /**
     * First trial of hand grip of the left hand in kilograms (kg)
     */
    private double hgL1;
    /**
     * Second trial of hand grip of the left hand in kilograms (kg)
     */
    private double hgL2;
    /**
     * Third trial of hand grip of the left hand in kilograms (kg)
     */
    private double hgL3;
    /**
     * Prone plank time in seconds
     */
    private double proneTime;
    /**
     * First trial of knee extension isometric force output for the right knee in kilograms (kg)
     */
    private double kneeExtForceR1;
    /**
     * Second trial of knee extension isometric force output for the right knee in kilograms (kg)
     */
    private double kneeExtForceR2;
    /**
     * First trial of knee extension isometric force output for the left knee in kilograms (kg)
     */
    private double kneeExtForceL1;
    /**
     * Second trial of knee extension isometric force output for the left knee in kilograms (kg)
     */
    private double kneeExtForceL2;
    /**
     * First trial for vertical jump height in centimeters (cm)
     */
    private double jh1;
    /**
     * Second trial for vertical jump height in centimeters (cm)
     */
    private double jh2;
    /**
     * First trial for distance of medicine ball pass in meters (m)
     */
    private double medPass1;
    /**
     * Second trial for distance of medicine ball pass in meters (m)
     */
    private double medPass2;

    //Estimated Aerobic Capacity
    /**
     * Non exercise v02max estimation
     */
    private double vO2Max;
    //Ymca step test
    /**
     * v02max calculated from the YMCA step test
     */
    private double postVO2Max;
    /**
     * Age rating of the athelete from the YMCA step test
     */
    private double ageRating;
    /**
     * The heart rate of the athlete after the YMCA step test
     */
    private int postHR;

    //rockport walk test
    /**
     * The heart rate of the athlete after the Rockport test
     */
    private int rockHR;
    /**
     * Time for the athlete to walk a mile for the rockport test
     */
    private double walkTime;
    /**
     * v02max calculated from the Rockport walk test
     */
    private double rockVO2Max;

    //12-min walk test
    /**
     * Distance covered, in meters (m), during the 12-minute walk test
     */
    private double walkDistance;
    /**
     * v02max calculated from the 12-minute walk test
     */
    private double walkVO2Max;
    /**
     * ACSM percentile from the 12-minute walk test
     */
    private double ACSMpercentile;

    /**
     * Blank generic constructor for the class
     */
    public FitnessTest() {

    }

    /**
     * Sets all variables under "Vitals" section
     *
     * @param age Age of the athlete
     * @param restingHR Resting heart rate of the athlete in beats/minute
     * @param restingHR2 Over of the resting heart rate of the athlete in mmHg
     * @param restingHR1 Under of the resting heart rate of the athlete in mmHg
     * @param height Height of the athlete in centimeters (cm)
     * @param bodyWeight Body weight of the athlete in kilograms (kg)
     * @param gender Gender of the athlete
     * @param bmi Body Mass Index value of the athlete in kg/m^2
     * @param peakFlow Peak blood flow of the athlete in Liters/minute
     */
    public void setVitals(int age, int restingHR, int restingHR1, int restingHR2, double height,
                          double bodyWeight, double bmi, String gender, double peakFlow) {
        this.age = age;
        this.restingHR = restingHR;
        this.restingHR1 = restingHR1;
        this.restingHR2 = restingHR2;
        this.height = height;
        this.bodyWeight = bodyWeight;
        this.bmi = bmi;
        this.gender = gender;
        this.peakFlow = peakFlow;
    }

    /**
     * Sets all variables under the "Anthropometrics" section
     *
     * @param ant1 First anterior thigh measurement in millimeters
     * @param ant2 Second anterior thigh measurement in millimeters
     * @param waistCirc Waist circumference in centimeters
     * @param hipCirc Hip circumference in centimeters
     * @param midThighCirc Mid-thigh circumference in centimeters
     * @param flexArmCirc Flexed arm circumference in centimeters
     * @param biCirc Bicep circumference in centimeters
     * @param hamCSA Hamstring cross sectional area in centimeters squared (cm^2)
     * @param quadCSA Quadriceps cross sectional area in centimeters squared (cm^2)
     * @param totalThighCSA Total thigh cross sectional area in centimeters squared (cm^2)
     */
    public void setAnthro(double ant1, double ant2, double waistCirc, double hipCirc, double midThighCirc, double flexArmCirc,
                          double hamCSA, double quadCSA, double totalThighCSA, double biCirc) {
        this.ant1 = ant1;
        this.ant2 = ant2;
        this.antAvg = (ant1 + ant2) / 2;
        this.waistCirc = waistCirc;
        this.hipCirc = hipCirc;
        this.midThighCirc = midThighCirc;
        this.flexArmCirc = flexArmCirc;
        this.hamCSA = hamCSA;
        this.quadCSA = quadCSA;
        this.totalThighCSA = totalThighCSA;
        this.biCirc = biCirc;
    }

    /**
     * Sets all variables under the "Skinfold" section
     *
     * @param triSkin Skinfold measurement of the triceps in millimeters (mm)
     * @param subSkin Skinfold measurement of the subscapular in millimeters (mm)
     * @param abdSkin Skinfold measurement of the abdominal in millimeters (mm)
     * @param supSkin Skinfold measurement of the suprailiac in millimeters (mm)
     * @param thighSkin Skinfold measurement of the thigh in millimeters (mm)
     * @param pecSkin Skinfold measurement of the pectoral in millimeters (mm). Males only
     * @param wallsit Skinfold measurement of the wallsit in centimeters (cm)
     */
    public void setSkin(double triSkin, double subSkin, double abdSkin, double supSkin, double thighSkin, double pecSkin, double wallsit) {
        this.triSkin = triSkin;
        this.subSkin = subSkin;
        this.abdSkin = abdSkin;
        this.supSkin = supSkin;
        this.thighSkin = thighSkin;
        this.pecSkin = pecSkin;
        this.wallsit = wallsit;
    }

    /**
     * Sets all variables under the "Sit &amp; Reach" section
     *
     * @param startDist Start distance for the sit &amp; reach measured in centimeters (cm)
     * @param endDist1 First end distance for the sit &amp; reach measured in centimeters (cm)
     * @param endDist2 Second end distance for the sit &amp; reach measured in centimeters (cm)
     * @param endDist3 Third end distance for the sit &amp; reach measured in centimeters (cm)
     * @param finalDist Final end distance for the sit &amp; reach measured in centimeters (cm)
     */
    public void setSitAndReach(double startDist, double endDist1, double endDist2, double endDist3, double finalDist) {
        this.startDist = startDist;
        this.endDist1 = endDist1;
        this.endDist2 = endDist2;
        this.endDist3 = endDist3;
        this.finalDist = finalDist;
    }

    /**
     * Sets all variables under the "Muscle Strength &amp; Endurance &amp; Power" section
     *
     * @param hgR1 First trial of the hand grip for the right hand measured in kilograms (kg)
     * @param hgR2 Second trial of the hand grip for the right hand measured in kilograms (kg)
     * @param hgR3 Third trial of the hand grip for the right hand measured in kilograms (kg)
     * @param hgL1 First trial of the hand grip for the left hand measured in kilograms (kg)
     * @param hgL2 Second trial of the hand grip for the left hand measured in kilograms (kg)
     * @param hgL3 Third trial of the hand grip for the left hand measured in kilograms (kg)
     * @param proneTime Prone plank time measured in seconds
     * @param kneeExtForceR1 First trial of knee extension isometric force output for the right knee measured in kilograms (kg)
     * @param kneeExtForceR2 Second trial of knee extension isometric force output for the right knee measured in kilograms (kg)
     * @param kneeExtForceL1 First trial of knee extension isometric force output for the left knee measured in kilograms (kg)
     * @param kneeExtForceL2 Second trial of knee extension isometric force output for the left knee measured in kilograms (kg)
     * @param jh1 First trial of vertical jump height measured in centimeters (cm)
     * @param jh2 Second trial of vertical jump height measured in centimeters (cm)
     * @param medPass1 First trial of medicine ball chest pass measured in meters (m)
     * @param medPass2 Second trial of medicine ball chest pass measured in meters (m)
     */
    public void setMuscleAndStrength(double hgR1, double hgR2, double hgR3, double hgL1, double hgL2,
                                     double hgL3, double proneTime, double kneeExtForceR1, double kneeExtForceR2, double kneeExtForceL1,
                                     double kneeExtForceL2, double jh1, double jh2, double medPass1, double medPass2) {

        this.hgR1 = hgR1;
        this.hgR2 = hgR2;
        this.hgR3 = hgR3;
        this.hgL1 = hgL1;
        this.hgL2 = hgL2;
        this.hgL3 = hgL3;
        this.proneTime = proneTime;
        this.kneeExtForceR1 = kneeExtForceR1;
        this.kneeExtForceR2 = kneeExtForceR2;
        this.kneeExtForceL1 = kneeExtForceL1;
        this.kneeExtForceL2 = kneeExtForceL2;
        this.jh1 = jh1;
        this.jh2 = jh2;
        this.medPass1 = medPass1;
        this.medPass2 = medPass2;
    }

    /**
     * Sets all variables under the "Estimated Aerobic Capacity" section
     *
     * @param vO2Max Non-exercise v02max estimate
     * @param postHR Heart rate after the YMCA step test in beats/minute
     * @param postVO2Max v02max calculated from the YMCA step test
     * @param ageRating Age rating determined from the YMCA step test
     * @param rockHR Heart rate after walking the 1 mile for the rockport test in beats/minute
     * @param walkTime Time it took to walk the mile for the rockport walk test in form of mm:ss (minutes:seconds)
     * @param rockVO2Max v02max calculated from the rockport walk test
     * @param walkDistance Distance walked in the 12-minute walk test in meters (m)
     * @param walkVO2Max v02max calculated from the 12-minute walk test
     * @param ACSMpercentile ACSM percentile determined from the 12-minute walk test
     */
    public void setAerobicCapacity(double vO2Max, int postHR, double postVO2Max,
                                   double ageRating, int rockHR, double walkTime, double rockVO2Max,
                                   double walkDistance, double walkVO2Max, double ACSMpercentile) {
        this.vO2Max = vO2Max;
        this.postHR = postHR;
        this.postVO2Max = postVO2Max;
        this.ageRating = ageRating;
        this.rockHR = rockHR;
        this.walkTime = walkTime;
        this.rockVO2Max = rockVO2Max;

        this.walkDistance = walkDistance;
        this.walkVO2Max = walkVO2Max;
        this.ACSMpercentile = ACSMpercentile;
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
            sql = "INSERT INTO FITNESSDATA VALUES ("
                    + "null,"
                    + age + "," + restingHR + "," + restingHR1 + "," + restingHR2 + "," + height + "," + bodyWeight + "," + bmi + ","
                    + peakFlow + ",'" + gender + "'," + ant1 + "," + ant2 + "," + antAvg + "," + waistCirc + "," + hipCirc + "," + midThighCirc
                    + "," + flexArmCirc + "," + hamCSA + "," + quadCSA + "," + totalThighCSA + "," + biCirc + "," + triSkin + "," + subSkin + ","
                    + abdSkin + "," + supSkin + "," + thighSkin + "," + pecSkin + "," + wallsit + ", " + startDist + "," + endDist1 + ","
                    + endDist2 + "," + endDist3 + "," + finalDist + "," + hgR1 + "," + hgR2 + "," + hgR3 + "," + hgL1 + "," + hgL2 + ","
                    + hgL3 + "," + proneTime + "," + kneeExtForceR1 + "," + kneeExtForceR2 + "," + kneeExtForceL1 + "," + kneeExtForceL2 + ","
                    + jh1 + "," + jh2 + "," + medPass1 + "," + medPass2 + "," + vO2Max + "," + postVO2Max + "," + ageRating + "," + postHR
                    + "," + walkTime + "," + rockVO2Max + "," + rockHR + "," + walkDistance + "," + walkVO2Max + "," + ACSMpercentile + ");";
            Database.executeUpdate(sql);
        }
        if (viewInfo == true) {
            String sql;
            sql = "UPDATE FITNESSDATA SET"
                    + " age = " + age + ","
                    + " restingHR = " + restingHR + ","
                    + " restingHR1 = " + restingHR1 + ","
                    + " restingHR2 = " + restingHR2 + ","
                    + " height = " + height + ","
                    + " bodyWeight = " + bodyWeight + ","
                    + " bmi = " + bmi + ","
                    + " peakFlow = " + peakFlow + ","
                    + " gender = " + "'" + gender + "'" + ","
                    + " ant1 = " + ant1 + ","
                    + " ant2 = " + ant2 + ","
                    + " antAvg = " + antAvg + ","
                    + " waistCirc = " + waistCirc + ","
                    + " hipCirc = " + hipCirc + ","
                    + " midThighCirc = " + midThighCirc + ","
                    + " flexArmCirc = " + flexArmCirc + ","
                    + " hamCSA = " + hamCSA + ","
                    + " quadCSA = " + quadCSA + ","
                    + " totalThighCSA = " + totalThighCSA + ","
                    + " biCirc = " + biCirc + ","
                    + " triSkin = " + triSkin + ","
                    + " subSkin = " + subSkin + ","
                    + " abdSkin = " + abdSkin + ","
                    + " supSkin = " + supSkin + ","
                    + " thighSkin = " + thighSkin + ","
                    + " pecSkin = " + pecSkin + ","
                    + " wallsit = " + wallsit + ","
                    + " startDist = " + startDist + ","
                    + " endDist1 = " + endDist1 + ","
                    + " endDist2 = " + endDist2 + ","
                    + " endDist2 = " + endDist3 + ","
                    + " finalDist = " + finalDist + ","
                    + " hgR1 = " + hgR1 + ","
                    + " hgR2 = " + hgR2 + ","
                    + " hgR3 = " + hgR3 + ","
                    + " hgL1 = " + hgL1 + ","
                    + " hgL2 = " + hgL2 + ","
                    + " hgL3 = " + hgL3 + ","
                    + " proneTime = " + proneTime + ","
                    + " kneeExtForceR1 = " + kneeExtForceR1 + ","
                    + " kneeExtForceR2 = " + kneeExtForceR2 + ","
                    + " kneeExtForceL1 = " + kneeExtForceL1 + ","
                    + " kneeExtFroceL2 = " + kneeExtForceL2 + ","
                    + " jh1 = " + jh1 + ","
                    + " jh2 = " + jh2 + ","
                    + " medPass1 = " + medPass1 + ","
                    + " medPass2 = " + medPass2 + ","
                    + " vO2Max = " + vO2Max + ","
                    + " postVO2Max = " + postVO2Max + ","
                    + " ageRating = " + ageRating + ","
                    + " postHR = " + postHR + ","
                    + " walkTime = " + walkTime + ","
                    + " rockVO2Max = " + rockVO2Max + ","
                    + " rockHR = " + rockHR + ","
                    + " walkDistance = " + walkDistance + ","
                    + " walkVO2Max = " + walkVO2Max + ","
                    + " ACSMpercentile = " + ACSMpercentile
                    + " WHERE ID = " + DBindex + ";";

            Database.executeUpdate(sql);
            Database.executeUpdate(sql);
        }
    }

    /**
     * Creates an html table representation of the fitness test object
     *
     * @return String with the HTML table representation of the fitness test object.
     */
    public String toHTML() {
        String rowStart = "<tr><td>";
        String rowMid = "</td><td>";
        String rowEnd = "</td></tr>";
        String html = "<br><br><br><h2>Functional Movement Screen</h2>"
                + "<table><tr><th>Vitals</th><th></th></tr>"
                + "<tr><td>Age</td><td>" + this.age + "</td></tr>"
                + "<tr><td>Resting HeartRate</td><td>" + restingHR1 + "</td></tr>"
                + "<tr><td>Resting HeartRate 1:</td><td>" + this.restingHR1 + "</td></tr>"
                + "<tr><td>Resting HeartRate 2:</td><td>" + this.restingHR2 + "</td></tr>"
                + rowStart + "Height:" + rowMid + this.height + rowEnd
                + rowStart + "Body Weight" + rowMid + this.bodyWeight + rowEnd
                + rowStart + "Body Mass Index (BMI)" + rowMid + this.bmi + rowEnd
                + rowStart + "Peak Flow" + rowMid + this.peakFlow + rowEnd
                + "</table>" + "<table><tr><th>Anthropometrics</th><th></th></tr>"
                + rowStart + "Anterior 1" + rowMid + this.ant1 + rowEnd
                + rowStart + "Anterior 2" + rowMid + this.ant2 + rowEnd
                + rowStart + "Anterior Average" + rowMid + this.antAvg + rowEnd
                + rowStart + "Waist Circumference" + rowMid + this.waistCirc + rowEnd
                + rowStart + "Hip Circumference" + rowMid + this.hipCirc + rowEnd
                + rowStart + "Mid Thigh Circumference " + rowMid + this.midThighCirc + rowEnd
                + rowStart + "Flexed Arm Circumference" + rowMid + this.flexArmCirc + rowEnd
                + rowStart + "Hamstring CSA" + rowMid + this.hamCSA + rowEnd
                + rowStart + "Quadriceps CSA" + rowMid + this.quadCSA + rowEnd
                + rowStart + "Total Thigh CSA" + rowMid + this.totalThighCSA + rowEnd
                + rowStart + "Bicep Circumference" + rowMid + this.biCirc + rowEnd
                + "</table><table><tr><th>Skinfold</th><th></th></tr>"
                + rowStart + "Tricep Skinfold" + rowMid + this.triSkin + rowEnd
                + rowStart + "Subscapular Skinfold" + rowMid + this.subSkin + rowEnd
                + rowStart + "Abdominal Skinfold" + rowMid + this.abdSkin + rowEnd
                + rowStart + "Suprailiac Skinfold" + rowMid + this.supSkin + rowEnd
                + rowStart + "Thigh Skinfold" + rowMid + this.thighSkin + rowEnd
                + rowStart + "Pectoral Skinfold" + rowMid + this.pecSkin + rowEnd
                + rowStart + "Wallsit" + rowMid + this.wallsit + rowEnd
                + "</table><table><tr><th>Sit & Reach</th><th></th></tr>"
                + rowStart + "Start Distance" + rowMid + this.startDist + rowEnd
                + rowStart + "Ending Distance 1" + rowMid + this.endDist1 + rowEnd
                + rowStart + "Ending Distance 2" + rowMid + this.endDist2 + rowEnd
                + rowStart + "Ending Distance 3" + rowMid + this.endDist3 + rowEnd
                + rowStart + "Final Distance" + rowMid + this.finalDist + rowEnd
                + "</table>";

        html += "<table><tr><th>Muscle & Strength</th><th></th></tr>"
                + rowStart + "Right Hand Grip 1" + rowMid + this.hgR1 + rowEnd
                + rowStart + "Right Hand Grip 2" + rowMid + this.hgR2 + rowEnd
                + rowStart + "Right Hand Grip 3" + rowMid + this.hgR3 + rowEnd
                + rowStart + "Left Hand Grip 1" + rowMid + this.hgL1 + rowEnd
                + rowStart + "Left Hand Grip 2" + rowMid + this.hgL2 + rowEnd
                + rowStart + "Left Hand Grip 3" + rowMid + this.hgL3 + rowEnd
                + rowStart + "Prone Time" + rowMid + this.proneTime + rowEnd
                + rowStart + "Right Knee Extension Force 1" + rowMid + this.kneeExtForceR1 + rowEnd
                + rowStart + "Right Knee Extension Force 2" + rowMid + this.kneeExtForceR2 + rowEnd
                + rowStart + "Left Knee Extension Force 1" + rowMid + this.kneeExtForceL1 + rowEnd
                + rowStart + "Left Knee Extension Force 2" + rowMid + this.kneeExtForceL2 + rowEnd
                + rowStart + "J H 1" + rowMid + this.jh1 + rowEnd
                + rowStart + "J H 2" + rowMid + this.jh2 + rowEnd
                + rowStart + "Medical Pass 1" + this.medPass1 + rowEnd
                + rowStart + "Medical Pass 2" + this.medPass2 + rowEnd
                + "</table>";

        html += "<table><tr><th>Aerobic Capacity</th><th></th></tr>"
                + rowStart + "VO2 Max" + rowMid + this.vO2Max + rowEnd
                + rowStart + "Post Heart Rate" + rowMid + this.postHR + rowEnd
                + rowStart + "Post VO2 Max" + rowMid + this.postVO2Max + rowEnd
                + rowStart + "Age Rating" + rowMid + this.ageRating + rowEnd
                + rowStart + "Rockwell Heart Rate" + rowMid + this.rockHR + rowEnd
                + rowStart + "Walk Time" + rowMid + this.walkTime + rowEnd
                + rowStart + "Rockewell VO2 Max" + rowMid + this.rockVO2Max + rowEnd
                + rowStart + "Walk Distance" + rowMid + this.walkDistance + rowEnd
                + rowStart + "Walk VO2 Max" + rowMid + this.walkVO2Max + rowEnd
                + rowStart + "ACSM Percentile" + rowMid + this.ACSMpercentile + rowEnd
                + "</table";
        return html;
    }

    /**
     * Method to generate a string representation of the fitness test object for use in generating a pdf report on the athlete.
     *
     * @return A string containing all of the information of the athlete in regards to their fitness test data sheet.
     *         Each field is separated by a "|" character
     */
    public String toPDF() {
        String pdf = "FitenessTest|" + this.age + "|" + restingHR1 + "|" + this.restingHR1 + "|" + this.restingHR2 + "|" + this.height + "|" + this.bodyWeight + "|"
                + this.bmi + "|" + this.peakFlow + "|" + this.ant1 + "|" + this.ant2 + "|" + this.antAvg + "|" + this.waistCirc + "|" + this.hipCirc + "|" + this.midThighCirc
                + "|" + this.flexArmCirc + "|" + this.hamCSA + "|" + this.quadCSA + "|" + this.totalThighCSA + "|" + this.biCirc + "|" + this.triSkin + "|" + this.subSkin + "|"
                + this.abdSkin + "|" + this.subSkin + "|" + this.thighSkin + "|" + this.pecSkin + "|" + this.wallsit + "|" + this.startDist + "|" + this.endDist1 + "|"
                + this.endDist2 + "|" + this.endDist3 + "|" + this.finalDist + "|" + this.hgR1 + "|" + this.hgR2 + "|" + this.hgR3 + "|" + this.hgL1 + "|" + this.hgL2 + "|" + this.hgL3
                + "|" + this.proneTime + "|" + this.kneeExtForceR1 + "|" + this.kneeExtForceR2 + "|" + this.kneeExtForceL1 + "|" + this.kneeExtForceL2 + "|" + this.jh1 + "|"
                + this.jh2 + "|" + this.medPass1 + "|" + this.medPass2 + "|" + this.vO2Max + "|" + this.postHR + "|" + this.postVO2Max + "|" + this.ageRating + "|" + this.rockHR + "|"
                + this.walkTime + "|" + this.rockVO2Max + "|" + this.walkDistance + "|" + this.walkVO2Max + "|" + this.ACSMpercentile;
        return pdf;
    }

    /***
     * Getter method for the age of the athlete
     *
     * @return The age of the athlete
     */
    public int getAge() {
        return age;
    }

    /**
     * Setter method for the age of the athlete
     *
     * @param age The age of the athlete
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * Getter method for the resting heart rate of the athlete in beats/minute
     *
     * @return The resting heart rate of the athlete in beats/minute
     */
    public int getRestingHR() {
        return restingHR;
    }

    /**
     * Setter method for the resting heart rate of the athlete in beats/minute
     *
     * @param restingHR The resting heart rate of the athlete in beats/minute
     */
    public void setRestingHR(int restingHR) {
        this.restingHR = restingHR;
    }

    /**
     * Getter method for the over of the resting heart rate, in mmHg, of the athlete
     *
     * @return The over of the resting heart rate, in mmHg, of the athlete
     */
    public int getRestingHR1() {
        return restingHR1;
    }

    /**
     * Setter method for the over of the resting heart rate, in mmHg, of the athlete
     *
     * @param restingHR1 The over of the resting heart rate, in mmHg, of the athlete
     */
    public void setRestingHR1(int restingHR1) {
        this.restingHR1 = restingHR1;
    }

    /**
     * Getter method for the under of the resting heart rate, in mmHg, of the athlete
     *
     * @return The under of the resting heart rate, in mmHg, of the athlete
     */
    public int getRestingHR2() {
        return restingHR2;
    }

    /**
     * Setter method for the over of the resting heart rate, in mmHg, of the athlete
     *
     * @param restingHR2 The under of the resting heart rate, in mmHg, of the athlete
     */
    public void setRestingHR2(int restingHR2) {
        this.restingHR2 = restingHR2;
    }

    /**
     * Getter method for the height of the athlete in centimeters (cm)
     *
     * @return The height of the athlete in centimeters (cm)
     */
    public double getHeight() {
        return height;
    }

    /**
     * Setter method for the height of the athlete in centimeters (cm)
     *
     * @param height The height of the athlete in centimeters (cm)
     */
    public void setHeight(double height) {
        this.height = height;
    }

    /**
     * Getter method for the weight of the athlete in kilograms (kg)
     *
     * @return The weight of the athlete in kilograms (kg)
     */
    public double getBodyWeight() {
        return bodyWeight;
    }

    /**
     * Setter method for the weight of the athlete in kilograms (kg)
     *
     * @param bodyWeight The weight of the athlete in kilograms (kg)
     */
    public void setBodyWeight(double bodyWeight) {
        this.bodyWeight = bodyWeight;
    }

    /**
     * Getter method for the BMI of the athlete
     *
     * @return The BMI of the athlete
     */
    public double getBmi() {
        return bmi;
    }

    /**
     * Setter method for the BMI of the athlete
     *
     * @param bmi The BMI of the athlete
     */
    public void setBmi(double bmi) {
        this.bmi = bmi;
    }

    /**
     * Getter method for the peak blood flow of the athlete in liters/minute
     *
     * @return The peak blood flow of the athlete in liters/minute
     */
    public double getPeakFlow() {
        return peakFlow;
    }

    /**
     * Setter method for the peak blood flow of the athlete in liters/minute
     *
     * @param peakFlow The peak blood flow of the athlete in liters/minute
     */
    public void setPeakFlow(double peakFlow) {
        this.peakFlow = peakFlow;
    }

    /**
     * Getter method for the gender of the athlete
     *
     * @return The athlete's gender
     */
    public String getGender() {
        return gender;
    }

    /**
     * Setter method for the gender of the athlete
     *
     * @param gender The athlete's gender
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * Getter method for the first anterior thigh of the athlete in millimeters (mm)
     *
     * @return The first anterior thigh of the athlete in millimeters (mm)
     */
    public double getAnt1() {
        return ant1;
    }

    /**
     * Setter method for the first anterior thigh of the athlete in millimeters (mm)
     *
     * @param ant1 The first anterior thigh of the athlete in millimeters (mm)
     */
    public void setAnt1(double ant1) {
        this.ant1 = ant1;
    }

    /**
     * Getter method for the second anterior thigh of the athlete in millimeters (mm)
     *
     * @return The second anterior thigh of the athlete in millimeters (mm)
     */
    public double getAnt2() {
        return ant2;
    }

    /**
     * Setter method for the second anterior thigh of the athlete in millimeters (mm)
     *
     * @param ant2 The second anterior thigh of the athlete in millimeters (mm)
     */
    public void setAnt2(double ant2) {
        this.ant2 = ant2;
    }

    /**
     * Getter method for the average anterior thigh of the athlete in millimeters (mm)
     *
     * @return The average anterior thigh of the athlete in millimeters (mm)
     */
    public double getAntAvg() {
        return antAvg;
    }

    /**
     * Setter method for the average anterior thigh of the athlete in millimeters (mm)
     *
     * @param antAvg The average anterior thigh of the athlete in millimeters (mm)
     */
    public void setAntAvg(double antAvg) {
        this.antAvg = antAvg;
    }

    /**
     * Getter method for the waist circumference of the athlete in millimeters (mm)
     *
     * @return The waist circumference of the athlete in millimeters (mm)
     */
    public double getWaistCirc() {
        return waistCirc;
    }

    /**
     * Setter method for the waist circumference of the athlete in millimeters (mm)
     *
     * @param waistCirc The waist circumference of the athlete in millimeters (mm)
     */
    public void setWaistCirc(double waistCirc) {
        this.waistCirc = waistCirc;
    }

    /**
     * Getter method for the hip circumference of the athlete in millimeters (mm)
     *
     * @return The hip circumference of the athlete in millimeters (mm)
     */
    public double getHipCirc() {
        return hipCirc;
    }

    /**
     * Setter method for the hip circumference of the athlete in millimeters (mm)
     *
     * @param hipCirc The hip circumference of the athlete in millimeters (mm)
     */
    public void setHipCirc(double hipCirc) {
        this.hipCirc = hipCirc;
    }

    /**
     * Getter method for the mid-thigh circumference of the athlete in millimeters (mm)
     *
     * @return The mid-thigh circumference of the athlete in millimeters (mm)
     */
    public double getMidThighCirc() {
        return midThighCirc;
    }

    /**
     * Setter method for the mid-thigh circumference of the athlete in millimeters (mm)
     *
     * @param midThighCirc The mid-thigh circumference of the athlete in millimeters (mm)
     */
    public void setMidThighCirc(double midThighCirc) {
        this.midThighCirc = midThighCirc;
    }

    /**
     * Getter method for the flexed arm circumference of the athlete in millimeters (mm)
     *
     * @return The flexed arm circumference of the athlete in millimeters (mm)
     */
    public double getFlexArmCirc() {
        return flexArmCirc;
    }

    /**
     * Setter method for the flexed arm circumference of the athlete in millimeters (mm)
     *
     * @param flexArmCirc The flexed arm circumference of the athlete in millimeters (mm)
     */
    public void setFlexArmCirc(double flexArmCirc) {
        this.flexArmCirc = flexArmCirc;
    }

    /**
     * Getter method for the bicep circumference of the athlete in millimeters (mm)
     *
     * @return The bicep circumference of the athlete in millimeters (mm)
     */
    public double getBiCirc() {
        return biCirc;
    }

    /**
     * Setter method for the bicep circumference of the athlete in millimeters (mm)
     *
     * @param biCirc The bicep circumference of the athlete in millimeters (mm)
     */
    public void setBiCirc(double biCirc) {
        this.biCirc = biCirc;
    }

    /**
     * Getter method for the cross sectional area of the hamstring of the athlete in millimeters (mm)
     *
     * @return The cross sectional area of the hamstring of the athlete in millimeters (mm)
     */
    public double getHamCSA() {
        return hamCSA;
    }

    /**
     * Setter method for the cross sectional area of the hamstring of the athlete in millimeters (mm)
     *
     * @param hamCSA The cross sectional area of the hamstring of the athlete in millimeters (mm)
     */
    public void setHamCSA(double hamCSA) {
        this.hamCSA = hamCSA;
    }

    /**
     * Getter method for the cross sectional area of the quadriceps of the athlete in millimeters (mm)
     *
     * @return The cross sectional area of the quadriceps of the athlete in millimeters (mm)
     */
    public double getQuadCSA() {
        return quadCSA;
    }

    /**
     * Setter method for the cross sectional area of the quadriceps of the athlete in millimeters (mm)
     *
     * @param quadCSA The cross sectional area of the quadriceps of the athlete in millimeters (mm)
     */
    public void setQuadCSA(double quadCSA) {
        this.quadCSA = quadCSA;
    }

    /**
     * Getter method for the cross sectional area of the total thigh of the athlete in millimeters (mm)
     *
     * @return The cross sectional area of the total thigh of the athlete in millimeters (mm)
     */
    public double getTotalThighCSA() {
        return totalThighCSA;
    }

    /**
     * Setter method for the cross sectional area of the total thigh of the athlete in millimeters (mm)
     *
     * @param totalThighCSA The cross sectional area of the total thigh of the athlete in millimeters (mm)
     */
    public void setTotalThighCSA(double totalThighCSA) {
        this.totalThighCSA = totalThighCSA;
    }

    /**
     * Getter method for the skin fold of the tricep of the athlete in millimeters (mm)
     *
     * @return The skin fold of the tricep of the athlete in millimeters (mm)
     */
    public double getTriSkin() {
        return triSkin;
    }

    /**
     * Setter method for the skin fold of the tricep of the athlete in millimeters (mm)
     *
     * @param triSkin The skin fold of the tricep of the athlete in millimeters (mm)
     */
    public void setTriSkin(double triSkin) {
        this.triSkin = triSkin;
    }

    /**
     * Getter method for the skin fold of the subscapular of the athlete in millimeters (mm)
     *
     * @return The skin fold of the subscapular of the athlete in millimeters (mm)
     */
    public double getSubSkin() {
        return subSkin;
    }

    /**
     * Setter method for the skin fold of the subscapular of the athlete in millimeters (mm)
     *
     * @param subSkin The skin fold of the subscapular of the athlete in millimeters (mm)
     */
    public void setSubSkin(double subSkin) { this.subSkin = subSkin; }

    /**
     * Getter method for the skin fold of the abdominal of the athlete in millimeters (mm)
     *
     * @return The skin fold of the abdominal of the athlete in millimeters (mm)
     */
    public double getAbdSkin() { return abdSkin; }

    /**
     * Setter method for the skin fold of the abdominal of the athlete in millimeters (mm)
     *
     * @param abdSkin The skin fold of the abdominal of the athlete in millimeters (mm)
     */
    public void setAbdSkin(double abdSkin) { this.abdSkin = abdSkin; }

    /**
     * Getter method for the skin fold of the suprailiac of the athlete in millimeters (mm)
     *
     * @return The skin fold of the suprailiac of the athlete in millimeters (mm)
     */
    public double getSupSkin() { return supSkin; }

    /**
     * Setter method for the skin fold of the suprailiac of the athlete in millimeters (mm)
     *
     * @param supSkin The skin fold of the suprailiac of the athlete in millimeters (mm)
     */
    public void setSupSkin(double supSkin) { this.supSkin = supSkin; }

    /**
     * Getter method for the skin fold of the thigh of the athlete in millimeters (mm)
     *
     * @return The skin fold of the thigh of the athlete in millimeters (mm)
     */
    public double getThighSkin() { return thighSkin; }

    /**
     * Setter method for the skin fold of the thigh of the athlete in millimeters (mm)
     *
     * @param thighSkin The skin fold of the thigh of the athlete in millimeters (mm)
     */
    public void setThighSkin(double thighSkin) { this.thighSkin = thighSkin; }

    /**
     * Getter method for the skin fold of the pectoral of the athlete in millimeters (mm). Males only
     *
     * @return The skin fold of the pectoral of the athlete in millimeters (mm)
     */
    public double getPecSkin() { return pecSkin; }

    /**
     * Setter method for the skin fold of the pectoral of the athlete in millimeters (mm). Males only
     *
     * @param pecSkin The skin fold of the pectoral of the athlete in millimeters (mm)
     */
    public void setPecSkin(double pecSkin) { this.pecSkin = pecSkin; }

    /**
     * Getter method for the skin fold of the wallsit of the athlete in centimeters (cm)
     *
     * @return The skin fold of the wallsit of the athlete in centimeters (cm)
     */
    public double getWallsit() { return wallsit; }

    /**
     * Setter method for the skin fold of the wallsit of the athlete in centimeters (cm)
     *
     * @param wallsit The skin fold of the wallsit of the athlete in centimeters (cm)
     */
    public void setWallsit(double wallsit) { this.wallsit = wallsit; }

    /**
     * Getter method for the start distance for the sit and reach in centimeters (cm)
     *
     * @return The start distance for the sit and reach in centimeters (cm)
     */
    public double getStartDist() {
        return startDist;
    }

    /**
     * Setter method for the start distance for the sit and reach in centimeters (cm)
     *
     * @param startDist The start distance for the sit and reach in centimeters (cm)
     */
    public void setStartDist(double startDist) {
        this.startDist = startDist;
    }

    /**
     * Getter method for the first end distance for the sit and reach in centimeters (cm)
     *
     * @return The first end distance for the sit and reach in centimeters (cm)
     */
    public double getEndDist1() {
        return endDist1;
    }

    /**
     * Setter method for the first end distance for the sit and reach in centimeters (cm)
     *
     * @param endDist1 The first end distance for the sit and reach in centimeters (cm)
     */
    public void setEndDist1(double endDist1) {
        this.endDist1 = endDist1;
    }

    /**
     * Getter method for the second end distance for the sit and reach in centimeters (cm)
     *
     * @return The second end distance for the sit and reach in centimeters (cm)
     */
    public double getEndDist2() {
        return endDist2;
    }

    /**
     * Setter method for the second end distance for the sit and reach in centimeters (cm)
     *
     * @param endDist2 The second end distance for the sit and reach in centimeters (cm)
     */
    public void setEndDist2(double endDist2) {
        this.endDist2 = endDist2;
    }

    /**
     * Getter method for the third end distance for the sit and reach in centimeters (cm)
     *
     * @return The third end distance for the sit and reach in centimeters (cm)
     */
    public double getEndDist3() {
        return endDist3;
    }

    /**
     * Setter method for the third end distance for the sit and reach in centimeters (cm)
     *
     * @param endDist3 The third end distance for the sit and reach in centimeters (cm)
     */
    public void setEndDist3(double endDist3) {
        this.endDist3 = endDist3;
    }

    /**
     * Getter method for the final end distance for the sit and reach in centimeters (cm)
     *
     * @return The final end distance for the sit and reach in centimeters (cm)
     */
    public double getFinalDist() {
        return finalDist;
    }

    /**
     * Setter method for the final end distance for the sit and reach in centimeters (cm)
     *
     * @param finalDist The final end distance for the sit and reach in centimeters (cm)
     */
    public void setFinalDist(double finalDist) {
        this.finalDist = finalDist;
    }

    /**
     * Getter method for the first trial of hand grip on the right hand measured in kilograms (kg)
     *
     * @return The first trial of hand grip on the right hand measured in kilograms (kg)
     */
    public double getHgR1() {
        return hgR1;
    }

    /**
     * Setter method for the first trial of hand grip on the right hand measured in kilograms (kg)
     *
     * @param hgR1 The first trial of hand grip on the right hand measured in kilograms (kg)
     */
    public void setHgR1(double hgR1) {
        this.hgR1 = hgR1;
    }

    /**
     * Getter method for the second trial of hand grip on the right hand measured in kilograms (kg)
     *
     * @return The second trial of hand grip on the right hand measured in kilograms (kg)
     */
    public double getHgR2() {
        return hgR2;
    }

    /**
     * Setter method for the second trial of hand grip on the right hand measured in kilograms (kg)
     *
     * @param hgR2 The second trial of hand grip on the right hand measured in kilograms (kg)
     */
    public void setHgR2(double hgR2) {
        this.hgR2 = hgR2;
    }

    /**
     * Getter method for the third trial of hand grip on the right hand measured in kilograms (kg)
     *
     * @return The third trial of hand grip on the right hand measured in kilograms (kg)
     */
    public double getHgR3() {
        return hgR3;
    }

    /**
     * Setter method for the third trial of hand grip on the right hand measured in kilograms (kg)
     *
     * @param hgR3 The third trial of hand grip on the right hand measured in kilograms (kg)
     */
    public void setHgR3(double hgR3) {
        this.hgR3 = hgR3;
    }

    /**
     * Getter method for the first trial of hand grip on the left hand measured in kilograms (kg)
     *
     * @return The first trial of hand grip on the left hand measured in kilograms (kg)
     */
    public double getHgL1() {
        return hgL1;
    }

    /**
     * Setter method for the first trial of hand grip on the left hand measured in kilograms (kg)
     *
     * @param hgL1 The first trial of hand grip on the left hand measured in kilograms (kg)
     */
    public void setHgL1(double hgL1) {
        this.hgL1 = hgL1;
    }

    /**
     * Getter method for the second trial of hand grip on the left hand measured in kilograms (kg)
     *
     * @return The second trial of hand grip on the left hand measured in kilograms (kg)
     */
    public double getHgL2() {
        return hgL2;
    }

    /**
     * Setter method for the second trial of hand grip on the left hand measured in kilograms (kg)
     *
     * @param hgL2 The second trial of hand grip on the left hand measured in kilograms (kg)
     */
    public void setHgL2(double hgL2) {
        this.hgL2 = hgL2;
    }

    /**
     * Getter method for the third trial of hand grip on the left hand measured in kilograms (kg)
     *
     * @return The third trial of hand grip on the left hand measured in kilograms (kg)
     */
    public double getHgL3() {
        return hgL3;
    }

    /**
     * Setter method for the third trial of hand grip on the left hand measured in kilograms (kg)
     *
     * @param hgL3 The third trial of hand grip on the left hand measured in kilograms (kg)
     */
    public void setHgL3(double hgL3) {
        this.hgL3 = hgL3;
    }

    /**
     * Getter method for the prone plank time of the athlete in seconds
     *
     * @return The prone plank time of the athlete in seconds
     */
    public double getProneTime() {
        return proneTime;
    }

    /**
     * Setter method for the prone plank time of the athlete in seconds
     *
     * @param proneTime The prone plank time of the athlete in seconds
     */
    public void setProneTime(double proneTime) {
        this.proneTime = proneTime;
    }

    /**
     * Getter method for the first trial of knee extension isometric force for the right knee in kilograms (kg)
     *
     * @return The first trial of knee extension isometric force for the right knee in kilograms (kg)
     */
    public double getKneeExtForceR1() {
        return kneeExtForceR1;
    }

    /**
     * Setter method for the first trial of knee extension isometric force for the right knee in kilograms (kg)
     *
     * @param kneeExtForceR1 The first trial of knee extension isometric force for the right knee in kilograms (kg)
     */
    public void setKneeExtForceR1(double kneeExtForceR1) {
        this.kneeExtForceR1 = kneeExtForceR1;
    }

    /**
     * Setter method for the second trial of knee extension isometric force for the right knee in kilograms (kg)
     *
     * @return The second trial of knee extension isometric force for the right knee in kilograms (kg)
     */
    public double getKneeExtForceR2() {
        return kneeExtForceR2;
    }

    /**
     * Getter method for the second trial of knee extension isometric force for the right knee in kilograms (kg)
     *
     * @param kneeExtForceR2 The second trial of knee extension isometric force for the right knee in kilograms (kg)
     */
    public void setKneeExtForceR2(double kneeExtForceR2) {
        this.kneeExtForceR2 = kneeExtForceR2;
    }

    /**
     * Setter method for the first trial of knee extension isometric force for the left knee in kilograms (kg)
     *
     * @return The first trial of knee extension isometric force for the left knee in kilograms (kg)
     */
    public double getKneeExtForceL1() {
        return kneeExtForceL1;
    }

    /**
     * Getter method for the first trial of knee extension isometric force for the left knee in kilograms (kg)
     *
     * @param kneeExtForceL1 The first trial of knee extension isometric force for the left knee in kilograms (kg)
     */
    public void setKneeExtForceL1(double kneeExtForceL1) {
        this.kneeExtForceL1 = kneeExtForceL1;
    }

    /**
     * Setter method for the second trial of knee extension isometric force for the left knee in kilograms (kg)
     *
     * @return The second trial of knee extension isometric force for the left knee in kilograms (kg)
     */
    public double getKneeExtForceL2() {
        return kneeExtForceL2;
    }

    /**
     * Getter method for the second trial of knee extension isometric force for the left knee in kilograms (kg)
     *
     * @param kneeExtForceL2 The second trial of knee extension isometric force for the left knee in kilograms (kg)
     */
    public void setKneeExtForceL2(double kneeExtForceL2) {
        this.kneeExtForceL2 = kneeExtForceL2;
    }

    /**
     * Getter method for the first trial of vertical jump height in centimeters (cm)
     *
     * @return The first trial of vertical jump height in centimeters (cm)
     */
    public double getJh1() {
        return jh1;
    }

    /**
     * Setter method for the first trial of vertical jump height in centimeters (cm)
     *
     * @param jh1 The first trial of vertical jump height in centimeters (cm)
     */
    public void setJh1(double jh1) {
        this.jh1 = jh1;
    }

    /**
     * Getter method for the second trial of vertical jump height in centimeters (cm)
     *
     * @return The second trial of vertical jump height in centimeters (cm)
     */
    public double getJh2() {
        return jh2;
    }

    /**
     * Setter method for the second trial of vertical jump height in centimeters (cm)
     *
     * @param jh2 The second trial of vertical jump height in centimeters (cm)
     */
    public void setJh2(double jh2) {
        this.jh2 = jh2;
    }

    /**
     * Getter method for the first trial of distance of the medicine ball chest pass in meters (m)
     *
     * @return The first trial of distance of the medicine ball chest pass in meters (m)
     */
    public double getMedPass1() {
        return medPass1;
    }

    /**
     * Setter method for the first trial of distance of the medicine ball chest pass in meters (m)
     *
     * @param medPass1 The first trial of distance of the medicine ball chest pass in meters (m)
     */
    public void setMedPass1(double medPass1) {
        this.medPass1 = medPass1;
    }

    /**
     * Getter method for the second trial of distance of the medicine ball chest pass in meters (m)
     *
     * @return The second trial of distance of the medicine ball chest pass in meters (m)
     */
    public double getMedPass2() {
        return medPass2;
    }

    /**
     * Setter method for the second trial of distance of the medicine ball chest pass in meters (m)
     *
     * @param medPass2 The second trial of distance of the medicine ball chest pass in meters (m)
     */
    public void setMedPass2(double medPass2) {
        this.medPass2 = medPass2;
    }

    /**
     * Getter method for the non-exercise v02max estimation
     *
     * @return The non-exercise v02max estimation
     */
    public double getvO2Max() {
        return vO2Max;
    }

    /**
     * Setter method for the non-exercise v02max estimation
     *
     * @param vO2Max The non-exercise v02max estimation
     */
    public void setvO2Max(double vO2Max) {
        this.vO2Max = vO2Max;
    }

    /**
     * Getter method for the v02max calculated from the YMCA step test
     *
     * @return The v02max calculated from the YMCA step test
     */
    public double getPostVO2Max() {
        return postVO2Max;
    }

    /**
     * Setter method for the v02max calculated from the YMCA step test
     *
     * @param postVO2Max The v02max calculated from the YMCA step test
     */
    public void setPostVO2Max(double postVO2Max) {
        this.postVO2Max = postVO2Max;
    }

    /**
     * Getter method for the age rating generated from the YMCA step test
     *
     * @return The age rating generated from the YMCA step test
     */
    public double getAgeRating() {
        return ageRating;
    }

    /**
     * Setter method for the age rating generated from the YMCA step test
     *
     * @param ageRating The age rating generated from the YMCA step test
     */
    public void setAgeRating(double ageRating) {
        this.ageRating = ageRating;
    }

    /**
     * Getter method for the heart rate after the YMCA step test measured in beats/minute
     *
     * @return The heart rate after the YMCA step test measured in beats/minute
     */
    public int getPostHR() {
        return postHR;
    }

    /**
     * Setter method for the heart rate after the YMCA step test measured in beats/minute
     *
     * @param postHR The heart rate after the YMCA step test measured in beats/minutes
     */
    public void setPostHR(int postHR) {
        this.postHR = postHR;
    }

    /**
     * Getter method for the heart rate after the Rockport walk test measured in beats/minute
     *
     * @return The heart rate after the Rockport walk test measured in beats/minute
     */
    public int getRockHR() {
        return rockHR;
    }

    /**
     * Setter method for the heart rate after the Rockport walk test measured in beats/minute
     *
     * @param rockHR The heart rate after the Rockport walk test measured in beats/minute
     */
    public void setRockHR(int rockHR) {
        this.rockHR = rockHR;
    }

    /**
     * Getter method for the time to walk the mile for the Rockport walk test. Represented in mm:ss (minutes:seconds)
     *
     * @return The time to walk the mile for the Rockport walk test. Represented in mm:ss (minutes:seconds)
     */
    public double getWalkTime() {
        return walkTime;
    }

    /**
     * Setter method for the time to walk the mile for the Rockport walk test. Represented in mm:ss (minutes:seconds)
     *
     * @param walkTime The time to walk the mile for the Rockport walk test. Represented in mm:ss (minutes:seconds)
     */
    public void setWalkTime(double walkTime) {
        this.walkTime = walkTime;
    }

    /**
     * Getter method for the v02max calculated from the Rockport Walk test
     *
     * @return The v02max calculated from the Rockport Walk test
     */
    public double getRockVO2Max() {
        return rockVO2Max;
    }

    /**
     * Setter method for the v02max calculated from the Rockport Walk test
     *
     * @param rockVO2Max The v02max calculated from the Rockport Walk test
     */
    public void setRockVO2Max(double rockVO2Max) {
        this.rockVO2Max = rockVO2Max;
    }

    /**
     * Getter method for the distance walked in meters (m) for the 12-minute walk test
     *
     * @return The distance walked in meters (m) for the 12-minute walk test
     */
    public double getWalkDistance() {
        return walkDistance;
    }

    /**
     * Setter method for the distance walked in meters (m) for the 12-minute walk test
     *
     * @param walkDistance The distance walked in meters (m) for the 12-minute walk test
     */
    public void setWalkDistance(double walkDistance) {
        this.walkDistance = walkDistance;
    }

    /**
     * Getter method for the v02max calculated from the 12-Minute Walk test
     *
     * @return The v02max calculated from the 12-Minute Walk test
     */
    public double getWalkVO2Max() {
        return walkVO2Max;
    }

    /**
     * Setter method for the v02max calculated from the 12-Minute Walk test
     *
     * @param walkVO2Max The v02max calculated from the 12-Minute Walk test
     */
    public void setWalkVO2Max(double walkVO2Max) {
        this.walkVO2Max = walkVO2Max;
    }

    /**
     * Getter method for the ACSM percentile generated from the 12-minute walk test
     *
     * @return The ACSM percentile generated from the 12-minute walk test
     */
    public double getACSMpercentile() {
        return ACSMpercentile;
    }

    /**
     * Setter method for the ACSM percentile generated from the 12-minute walk test
     *
     * @param ACSMpercentile The ACSM percentile generated from the 12-minute walk test
     */
    public void setACSMpercentile(double ACSMpercentile) {
        this.ACSMpercentile = ACSMpercentile;
    }
}