package main.java;

import main.java.controllers.AuthenticationController;
import org.h2.command.Prepared;

import java.io.*;
import java.sql.*;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class holds the functions pertaining to the database, including creating a database, 
 * opening a connection to the database, closing the connection to the database, executing
 * an insert/delete sql statement, and executing an sql query.
 *
 * @author nicolenelson
 */
public class Database {
    /**
     * Driver name of the database
     */
    static final String DRIVER = "org.h2.Driver";
    /**
     * URL of the database
     */
    static String URL = "jdbc:h2:~/LuckyLanes";

    /**
     * Database username
     */
    static String USERNAME = "luckylanes";
    /**
     * Database password
     */
    static String PASSWORD = "lucky";

    /**
     * The connection to the database
     */
    public static Connection conn = null;
    /**
     * The statement to be executed on the database
     */
    public static Statement state = null;
    
    /**
     * Seems to have been left here by a previous semester. Can anyone see a reason why we need to keep this?
     *
     * Previous Semester Comment: "Just testing stuff...Relax"
     */
    private String u;

    /**
     * Constructor for the Database class
     *
     * @param u Unsure what this parameter does
     */
    public Database(String u) {
        this.u = u;
    }

    /**
     * Used when creating/loading databases. Will set up a connection to the database
     * 
     * @param url The URL of the database
     * @return True if the database was successfully opened, false if an exception occured when opening the database
     */
    public static boolean connect(String url) {
        URL = "jdbc:h2:file:" + url;
        URL = URL.split("\\.", 2)[0];
        URL += ";IFEXISTS=TRUE";
        Trace.print("URL ON CONNECT:" + URL);

        try {
            Trace.print("Grabbing driver...");
            Class.forName(DRIVER);

            Trace.print("Connecting to the database...");
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            Trace.print("Connected...");
            close();
            return true;
        } catch (SQLException ex) {
            Trace.print("Connection was unsuccessful.");
            return false;
        } catch (ClassNotFoundException ex) {
            Trace.print("Driver detection was unsuccessful.");
            return false;
        }
    }

    /**
     * Used when using search options for profiles. Will set up a connection to the database
     */
    public static void connect() {
        System.out.println("URL ON CONNECT, NO PARAM:" + URL);

        try {
            System.out.println("Grabbing driver...");
            Class.forName(DRIVER);

            System.out.println("Connecting to the database...");
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("Connected...");
        } catch (SQLException ex) {
            System.out.println("Connection was unsuccessful.");
        } catch (ClassNotFoundException ex) {
            System.out.println("Driver detection was unsuccessful.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Closes the connection to the database
     */
    public static void close() {
        try {
            System.out.println("Closing the database...");
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Will create the database and tables of the database
     *
     * @param url The URL of the database
     */
    public static void createDatabase(String url) {
        // declare variables
        String sql;

        // url = "C\:\\Users\\HomePC\\Desktop\\Database files\\database";

        URL = "jdbc:h2:file:" + url;

        System.out.println("The url upon creating: " + URL);

        try {
            // grab driver
            Class.forName(DRIVER);

            // create a database of the following credentials
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            state = conn.createStatement();

            // add in athlete table
            sql = "CREATE TABLE ATHLETE (ID INT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(255), "
                    + "date VARCHAR(255), dateOfBirth VARCHAR(255), address VARCHAR(255), city VARCHAR(255), "
                    + "state VARCHAR(255), zip int, phone VARCHAR(255), school VARCHAR(255), "
                    + "height double, weight double, age int, gender VARCHAR(255), handDominance "
                    + "VARCHAR(255), legDominance VARCHAR(255), primarySport VARCHAR(255), primaryPosition VARCHAR(255));";

            state.execute(sql);
            System.out.println("Created a Athlete table.");

            // add table for Authentication Data
            sql = "CREATE TABLE AUTHENTICATION (ID INT PRIMARY KEY AUTO_INCREMENT, username VARCHAR(256), "
                    + "password VARCHAR(256), salt VARCHAR(256), authLevel VARCHAR(256));";

            state.execute(sql);
            System.out.println("Created an authentication table.");

            AuthenticationController.newAccount("admin","defaultPassword".toCharArray(),"Admin");
            System.out.println("Created default admin account with credentials:");
            System.out.println("username: admin");
            System.out.println("password: defaultPassword");
            System.out.println("It is strongly recommended to change this password.");

            sql = "CREATE TABLE FMS (ID INT PRIMARY KEY AUTO_INCREMENT, "
                    + "deepSquatRaw int, deepSquatFinal int,"
                    + "hurdleStepRawL int, hurdleStepRawR int, hurdleStepFinal int,"
                    + "inlineLoungeRawL int, inlineLoungeRawR int, inlineLoungeFinal int,"
                    + "shoulderMobilityRawL int, shoulderMobilityRawR int, shoulderMobilityFinal int,"
                    + "shoulderClearingL boolean, shoulderClearingR boolean,"
                    + "legRaiseRawL int, legRaiseRawR int, legRaiseFinal int,"
                    + "trunkStabilityRaw int, trunkStabilityFinal int,"
                    + "extensionClearing boolean,"
                    + "rotaryRawL int, rotaryRawR int, rotaryFinal int,"
                    + "flexionClearing boolean,"
                    + "total int);";

            state.execute(sql);
            System.out.println("Created a FMS table.");

            sql = "CREATE TABLE YBALANCE (ID INT PRIMARY KEY AUTO_INCREMENT, "
                    + "rightLimbLength double,"
                    + "antRightMean double, antLeftMean double,"
                    + "pmRightMean double, pmLeftMean double,"
                    + "plRightMean double, plLeftMean double,"
                    + "antR1 double, antR2 double, antR3 double,"
                    + "antL1 double, antL2 double, antL3 double,"
                    + "pmR1 double, pmR2 double, pmR3 double,"
                    + "pmL1 double, pmL2 double, pmL3 double,"
                    + "plR1 double, plR2 double, plR3 double,"
                    + "plL1 double, plL2 double, plL3 double,"
                    + "compositeLeft double,compositeRight double);";

            state.execute(sql);
            System.out.println("Created a YBalance table.");

            // EDITED BY: JOSHUA BOLSTAD

            sql = "CREATE TABLE FITNESSDATA (ID INT PRIMARY KEY AUTO_INCREMENT, "
                    + "age int, restingHR int, restingHR1 int, restingHR2 int, height double, bodyWeight double, bmi double, "
                    + "peakFlow double, gender VARCHAR(255), ant1 double, ant2 double, antAvg double, waistCirc double, hipCirc double, "
                    + "midThighCirc double, flexArmCirc double, hamCSA double,quadCSA double, totalThighCSA double, biCirc double, "
                    + "triSkin double, subSkin double, abdSkin double, supSkin double, thighSkin double, pecSkin double, wallsit double, "
                    + "startDist double, "
                    + "endDist1 double, endDist2 double, endDist3 double, finalDist double, hgR1 double, hgR2 double, hgR3 double, "
                    + "hgL1 double, hgL2 double, hgL3 double,proneTime double, kneeExtForceR1 double, kneeExtForceR2 double, "
                    + "kneeExtForceL1 double, kneeExtForceL2 double,jh1 double, jh2 double, medPass1 double, medPass2 double,"
                    + "vO2Max double, postVO2Max double, ageRating double,postHR int, walkTime double, rockVO2Max double, rockHR int, "
                    + "walkDistance double, walkVO2Max double, ACSMpercentile double);";

            state.execute(sql);
            System.out.println("Created a Fitness Data table.");

            sql = "CREATE TABLE PARQ (ID INT PRIMARY KEY AUTO_INCREMENT, "
                    + "q1Ans boolean, q2Ans boolean, q3Ans boolean, q4Ans boolean, q5Ans boolean, q6Ans boolean, q7Ans VARCHAR(255));";

            state.execute(sql);
            System.out.println("Created Par-Q table");

            sql = "CREATE TABLE IBSSN (ID INT PRIMARY KEY AUTO_INCREMENT, targetAccuracy double, pocketPercentage double, singlePinSpareConv double, multiplePinSpareConv double, "
            		+ "avgThrowsToPocket int, avgThrowsToPocketAdjusted int, entryAngle double, ballSpeedConsistency double, "
            		+ "ballSpeed double, gender int, revRate double);";

            state.execute(sql);
            System.out.println("Created IBSSN table");

            sql = "CREATE TABLE IQPSYCH (ID INT PRIMARY KEY AUTO_INCREMENT, "
                    + "IQ double, Psych VARCHAR(255));";

            state.execute(sql);
            System.out.println("Created a IQ AND Psych Test table");

            sql = "CREATE TABLE TEST (ID INT PRIMARY KEY AUTO_INCREMENT, "
                    + "TestName VARCHAR(255));";

            state.execute(sql);
            System.out.println("Created TEST table");
            
            sql = "CREATE TABLE ANSWER (ID INT PRIMARY KEY AUTO_INCREMENT, "
                    + "TestId INT,"
                    + "AnswersName VARCHAR(255),"
                    + "TestTaker VARCHAR (255));";

            state.execute(sql);
            System.out.println("Created TEST table");


            sql = "CREATE TABLE QUESTION (ID INT PRIMARY KEY AUTO_INCREMENT, "
                    + "Question VARCHAR(255), Option1 VARCHAR(255), Option1Value boolean,"
                    + "Option2 VARCHAR(255), Option2Value boolean, Option3 VARCHAR(255), Option3Value boolean, Option4 VARCHAR(255), Option4Value boolean);";

            state.execute(sql);
            System.out.println("Created a Question table");
            

            /*more Nicoles muahahah
            for (int i = 0; i < 10000; i++)
            {
                sql = "INSERT INTO ATHLETE VALUES (null, 'Nicole', '2/3/19', '12/11/1994',"
                        + "'a box', 'whitewater', 'wi', 53045, '208-5039', 'UW-Whitewater',"
                        + "5.3, 10000000, 23, 'female', 'right', 'left', 'eating', 'front end');";
                state.executeUpdate(sql);
            }
            
            for (int i = 0; i < 10000; i++)
            {
                sql = "INSERT INTO FMS VALUES (null,3,3,2,2,2,3,3,3,2,3,2,false,false,3,1,1,3,3,false,2,3,2,false,14);";
                state.executeUpdate(sql);
            }
            */

            sql = "SELECT name, school, zip FROM ATHLETE;";
            ResultSet rs = state.executeQuery(sql);
            ResultSetMetaData rsmd = rs.getMetaData();
            int columns = rsmd.getColumnCount();

            while (rs.next()) {
                for (int j = 1; j <= columns; j++) {
                    if (j > 1) {
                        //          System.out.println(", "); 
                    }

                    String colVal = rs.getString(j);

                    //       System.out.print(colVal + " " + rsmd.getColumnName(j));
                }

                //   System.out.println("");
            }

            System.out.println("Executed Query....");

            // close database 
            System.out.println("Closing the database...");
            state.close();
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            System.out.println("Driver detection was unsuccessful.");
        }
    }

    /**
     * Takes an SQL statement sent in as a parameter and executes that statement. Use this for
     * the insert, update, and delete functions
     *
     * @param sql An SQL insert, update, or delete statement that will be executed on the database
     */
    public static void executeUpdate(String sql) {
        System.out.println("THE ON EXECUTE: " + URL);

        try {
            // connect
            connect();

            // execute the SQL statement
            state = conn.createStatement();
            state.executeUpdate(sql);

            // close the database
            close();
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Acts like execute update, but allows for non-string arguments to be passed through and in place of '?' in
     * the insert, update, and delete functions.
     *
     * vars is a String array, so that the function scales with number of ? inputs
     *
     * @param sql An SQL insert, update, or delete statement that will be executed on the database
     * @param vars A list of Strings to insert into ?s in the sql statement.
     *
     * returns a boolean variable validating that no error occurred.
     */
    public static boolean executeAsyncUpdate(String sql, String[] vars) {
        System.out.println("THE ON EXECUTE: " + URL);

        boolean updated = false;

        try {
            // connect
            connect();

            // execute the SQL statement
            PreparedStatement pstmt = conn.prepareStatement(sql);
            for (int i=0; i<vars.length; i++) {
                pstmt.setString(i+1,vars[i]);
            }
            pstmt.executeUpdate();
            pstmt.close();

            // close the database
            close();
            updated = true;
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
            updated = false;
        }
        return updated;
    }

    /**
     * Takes an SQL statement sent in as a parameter and executes that statement. The resulting
     * set will then be returned. Use this for a search query
     *
     * @param sql An SQL query statement that will be executed on the database
     * @return ResultSet The resulting data returned from the search query
     */
    public static ResultSet searchQuery(String sql) {
        ResultSet rs = null;

        try {
            // call the sql query, place in RS.
            state = conn.createStatement();
            rs = state.executeQuery(sql);

            // close the database
            //close();
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }

        return rs;
    }

    /**
     * Saves the properties to the config.properties file
     * 
     * @param url The database URL to be saved in the properties file
     */
    public static void saveProperties(String url) {
        Properties prop = new Properties();
        OutputStream output = null;

        try {

            output = new FileOutputStream(new File("").getAbsolutePath() + "/config.properties");

            prop.setProperty("url", url);

            prop.store(output, null);

        } catch (IOException io) {
            io.printStackTrace();
        } finally {
            if (output != null) {
                try {
                    output.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    /**
     * Loads the properties from the config.properties file
     *
     * @return database URL stored in the properties file
     */
    public static String loadProperties() {
        Properties prop = new Properties();
        InputStream input = null;
        String url = "";

        try {
            input = new FileInputStream(new File("").getAbsolutePath() + "/config.properties");
            prop.load(input);
            url = prop.getProperty("url");
            System.out.println("Changed database path.");
        } catch (IOException ex) {
            //A properties file was not found, create one with empty url.
            return null;

            //ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return url;
    }
}
