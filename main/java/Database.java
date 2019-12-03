
package main.java;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.*;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nicolenelson this class holds the functions pertaining to the
 * database, including creating a database, opening a connection to the
 * database, closing the connection to the database, executing an insert/delete
 * sql statement, and executing an sql query.
 */
public class Database
{
    // driver name and database
    static final String DRIVER = "org.h2.Driver";
    static String URL = "jdbc:h2:~/LuckyLanes";

    // database credentials
    static String USERNAME = "luckylanes";
    static String PASSWORD = "lucky";

    public static Connection conn = null;
    public static Statement state = null;
    private String u;//Just testing stuff...Relax
    
    /**
     * 
     * @param u 
     */
    public Database(String u)
    {
        this.u = u;
    }

    /**
     * Used when creating/loading databases.
     * 
     * Function: Connect Return: Void Parameters: String - url Description: Will
     * set up a connection to the database.
     */
    public static boolean connect(String url)
    {
        URL = "jdbc:h2:file:" + url;
        URL = URL.split("\\.", 2)[0];
        URL += ";IFEXISTS=TRUE";
        Trace.print("URL ON CONNECT:" + URL);
        
        try
        {
            Trace.print("Grabbing driver...");
            Class.forName(DRIVER);

            Trace.print("Connecting to the database...");
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            Trace.print("Connected...");
            close();
            return true;
        }
        catch (SQLException ex)
        {
            Trace.print("Connection was unsuccessful.");
            return false;
        }
        catch (ClassNotFoundException ex)
        {
            Trace.print("Driver detection was unsuccessful.");
            return false;
        }
    }

    /**
     * Used when using search options for profiles.
     * 
     * Function: Connect Return: Void Parameters: None Description: Will set up
     * a connection to the database, no parameter needed.
     */
    public static void connect()
    {
        System.out.println("URL ON CONNECT, NO PARAM:" + URL);
        
        try
        {
            System.out.println("Grabbing driver...");
            Class.forName(DRIVER);
            
            System.out.println("Connecting to the database...");
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("Connected...");
        }
        catch (SQLException ex)
        {
            System.out.println("Connection was unsuccessful.");
        }
        catch (ClassNotFoundException ex)
        {
            System.out.println("Driver detection was unsuccessful.");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Close database
     * Function: Close Return: Void Parameters: None Description: Will close the
     * connection to the databse.
     */
    public static void close()
    {
        try
        {
            System.out.println("Closing the database...");
            conn.close();
        }
        catch (SQLException ex)
        {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Function: createDatabase Return: Void, none. Parameters: None
     * Description: Will create the database and tables of the database
     */
    public static void createDatabase(String url)
    {
        // declare variables
        String sql;
        
        // url = "C\:\\Users\\HomePC\\Desktop\\Database files\\database";

        URL = "jdbc:h2:file:" + url;

        System.out.println("The url upon creating: " + URL);

        try
        {
            // grab driver
            Class.forName(DRIVER);

            // create a database of the following credentials
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            // add in athlete table
            state = conn.createStatement();
            
            sql = "CREATE TABLE ATHLETE (ID INT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(255), "
                    + "date VARCHAR(255), dateOfBirth VARCHAR(255), address VARCHAR(255), city VARCHAR(255), "
                    + "state VARCHAR(255), zip int, phone VARCHAR(255), school VARCHAR(255), "
                    + "height double, weight double, age int, gender VARCHAR(255), handDominance "
                    + "VARCHAR(255), legDominance VARCHAR(255), primarySport VARCHAR(255), primaryPosition VARCHAR(255));";
            
            state.execute(sql);
            System.out.println("Created a Athlete table.");

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
            
            // AUTHOR JOSHUA BOLSTAD
            
            sql = "CREATE TABLE PARQ (ID INT PRIMARY KEY AUTO_INCREMENT, "
            		+ "q1Ans boolean, q2Ans boolean, q3Ans boolean, q4Ans boolean, q5Ans boolean, q6Ans boolean, q7Ans VARCHAR(255));";
            
            state.execute(sql);
            System.out.println("Created Par-Q table");

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
            
            while (rs.next())
            {
                for (int j = 1; j <= columns; j++)
                {
                    if (j > 1)
                    {
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
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        catch (ClassNotFoundException ex)
        {
            System.out.println("Driver detection was unsuccessful.");
        }
    }

    /**
     * Function: executeUpdate Return: Void, none. Parameters: String sql - the
     * sql statement that will be executed. Description: insert, update, delete
     * functions of SQL statements will be executed
     */
    public static void executeUpdate(String sql)
    {
        System.out.println("THE ON EXECUTE: " + URL);

        try
        {
            // connect
            connect();

            // execute the SQL statement
            state = conn.createStatement();
            state.executeUpdate(sql);

            // close the database
            close();
        }
        catch (SQLException ex)
        {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Connection to database
     * @param sql
     * @return 
     */
    public static ResultSet searchQuery(String sql)
    {
        ResultSet rs = null;

        try
        {
            // call the sql query, place in RS.
            state = conn.createStatement();
            rs = state.executeQuery(sql);
            
            // close the database
            //close();
        }
        catch (SQLException ex)
        {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return rs;
    }
    
    /**
     * 
     * @param url 
     */
    public static void saveProperties(String url)
    {
        Properties prop = new Properties();
        OutputStream output = null;

        try
        {

            output = new FileOutputStream(new File("").getAbsolutePath() + "/config.properties");

            prop.setProperty("url", url);

            prop.store(output, null);

        }
        catch (IOException io)
        {
            io.printStackTrace();
        }
        finally
        {
            if (output != null)
            {
                try
                {
                    output.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }

        }
    }
    
    /**
     * 
     * @return 
     */
    public static String loadProperties()
    {
        Properties prop = new Properties();
        InputStream input = null;
        String url = "";
        
        try
        {
            input = new FileInputStream(new File("").getAbsolutePath() + "/config.properties");
            prop.load(input);
            url = prop.getProperty("url");
            System.out.println("Changed database path.");
        }
        catch (IOException ex)
        {
            //A properties file was not found, create one with empty url.
            return null;
            
            //ex.printStackTrace();
        }
        finally
        {
            if (input != null)
            {
                try
                {
                    input.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }

        return url;
    }
}