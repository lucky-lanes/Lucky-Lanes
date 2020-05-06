package main.formObjects;

import main.java.Database;

/**
 * @author Ian
 * Athlete object. This athlete object is used to hold the information that is contained inside of the Demographics
 * tab.
 * <p>
 * Holds getters/setters for all variables, as well as methods for converting the profile to HTML and a pdf, as well as
 * methods for interacting with the database
 * </p>
 */
public class Athlete {
    /**
     * The name of the athlete
     */
    private String name;
    /**
     * The current date
     */
    private String date;
    /**
     * The date of birth of the athlete
     */
    private String dateOfBirth;
    /**
     * The address of the athlete (does not include city, state or zip)
     */
    private String address;
    /**
     * The city of the address of the athlete
     */
    private String city;
    /**
     * The state of the address of the athlete
     */
    private String state;
    /**
     * The zip code of the address of the athlete
     */
    private int zip;
    /**
     * The phone number of the athlete
     */
    private String phone;
    /**
     * The school of the athlete
     */
    private String school;
    /**
     * The height of the athlete in centimeters (cm)
     */
    private double height;
    /**
     * The weight of the athlete in kilograms (kg)
     */
    private double weight;
    /**
     * The age of the athlete in years
     */
    private int age;
    /**
     * The gender of the athlete. Options are "Male" and "Female"
     */
    private String gender;
    /**
     * The hand dominance of the athlete (should be same as legDominance)
     */
    private String handDominance;
    /**
     * The leg dominance of the athlete (should be same as handDominance)
     */
    private String legDominance;
    /**
     * Primary sport of the athlete
     */
    private String primarySport;
    /**
     * Primary position of the athlete
     */
    private String primaryPosition;

    /**
     * Creates an html table representation of the athlete object
     *
     * @return String with the HTML table representation of the athlete object.
     */
    public String toHTML() {
        String rowStart = "<tr><td>";
        String rowMid = "</td><td>";
        String rowEnd = "</td></tr>";

        String html = "</br></br></br><table><tr><th>Bowler Information</th><th></th></tr>";
        html += rowStart + "Name" + rowMid + this.name + rowEnd
                + rowStart + "Date" + rowMid + this.date + rowEnd
                + rowStart + "Date of Birth" + rowMid + this.dateOfBirth + rowEnd
                + rowStart + "Address" + rowMid + this.address + rowEnd
                + rowStart + "City" + rowMid + this.city + rowEnd
                + rowStart + "State" + rowMid + this.state + rowEnd
                + rowStart + "Zip Code" + rowMid + this.zip + rowEnd
                + rowStart + "Phone" + rowMid + this.phone + rowEnd
                + rowStart + "School" + rowMid + this.school + rowEnd
                + rowStart + "Height" + rowMid + this.height + rowEnd
                + rowStart + "Weight" + rowMid + this.weight + rowEnd
                + rowStart + "Age" + rowMid + this.age + rowEnd
                + rowStart + "Sex" + rowMid + this.gender + rowEnd
                + rowStart + "Hand Dominance" + rowMid + this.handDominance + rowEnd
                + rowStart + "Leg Dominance" + rowMid + this.legDominance + rowEnd
                + rowStart + "Primary Sport" + rowMid + this.primarySport + rowEnd
                + rowStart + "Primary Position" + rowMid + this.primaryPosition + rowEnd  
                	+ "</table>";
        return html;
    }

    /**
     * Method to generate a string representation of the athlete for use in generating a pdf report on the athlete.
     *
     * @return A string containing all of the information of the athlete. Each field is separated by a "|" character
     */
    public String toPDF() {
        String pdf = "Athlete|" + this.name + "|" + this.date + "|" + this.dateOfBirth + "|" + this.address + "|" + this.city + "|" + this.state + "|" + this.zip + " |"
                + this.phone + "|" + this.school + "|" + this.height + "|" + this.weight + "|" + this.age + "|" + this.gender + "|" + this.handDominance + "|"
                + this.legDominance + "|" + this.primarySport + "|" + this.primaryPosition;
        return pdf;
    }


    /**
     * Constructor of the Athlete class.
     *
     * @param name Name of the athlete
     * @param date Current date
     * @param dateOfBirth Date of birth of the athlete
     * @param address Address of the athlete (not including city, state, or zip code)
     * @param city City for the address of the athlete
     * @param state State for the address of the athlete
     * @param zip Zip code for the address of the athlete
     * @param phone Phone number of the athlete
     * @param school School of the athlete
     * @param height Height in cm of the athlete
     * @param weight Weight in kg of the athlete
     * @param age Age in years of the athlete
     * @param gender String containing the Gender of the athlete
     * @param handDominance The athete's hand dominance (left or right)
     * @param legDominance The athlete's leg dominance (left or right)
     * @param primarySport Primary sport of the athlete
     * @param primaryPosition Primary position in the primary sport of the athlete
     */
    public Athlete(String name, String date, String dateOfBirth, String address, String city, String state, int zip, String phone,
                   String school, double height, double weight, int age, String gender, String handDominance, String legDominance,
                   String primarySport, String primaryPosition) {
        this.name = name;
        this.date = date;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.city = city;
        this.state = state;

        this.zip = zip;
        this.phone = phone;
        this.school = school;
        this.height = height;
        this.weight = weight;

        this.age = age;
        this.gender = gender;
        this.handDominance = handDominance;
        this.legDominance = legDominance;
        this.primarySport = primarySport;
        this.primaryPosition = primaryPosition;
    }

    /**
     * Blank constructor of the athlete class.
     * <p>
     * Sets all fields to either blank, null, or 0 according to their data type
     * </p>
     */
    public Athlete() {
        this.name = "";
        this.date = null;
        this.dateOfBirth = null;
        this.address = "";
        this.city = "";
        this.state = "";

        this.zip = 0;
        this.phone = "";
        this.school = "";
        this.height = 0;
        this.weight = 0;

        this.age = 0;
        this.gender = "";
        this.handDominance = "";
        this.legDominance = "";
        this.primarySport = "";
        this.primaryPosition = "";
    }

    /**
     * Getter method for the name of the athlete
     *
     * @return The athlete's name
     */
    public String getName() {
        return name;
    }

    /**
     * Setter method for the name of the athlete
     *
     * @param name The athlete's name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter method for the current date
     *
     * @return The date
     */
    public String getDate() {
        return date;
    }

    /**
     * Setter method for the current date
     *
     * @param date The date
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * Getter method for the Athlete's date of birth
     *
     * @return The athlete's date of birth
     */
    public String getDateOfBirth() {
        return dateOfBirth;
    }

    /**
     * Setter method for the Athlete's date of birth
     *
     * @param dateOfBirth The date of birth of the athlete
     */
    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    /**
     * Getter method for the athlete's address
     *
     * @return The athlete's address
     */
    public String getAddress() {
        return address;
    }

    /**
     * Setter method for the athlete's address
     *
     * @param address The athlete's address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Getter method for the city of the athlete's address
     *
     * @return The city of the athlete
     */
    public String getCity() {
        return city;
    }

    /**
     * Setter method for the city of the athlete
     *
     * @param city The city of the athlete
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Getter method for the state of the athlete
     *
     * @return The athlete's state
     */
    public String getState() {
        return state;
    }

    /**
     * Setter method for the state of the athlete
     *
     * @param state The athlete's state
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * Getter method for the athlete's zip code
     *
     * @return The athlete's zip code
     */
    public int getZip() {
        return zip;
    }

    /**
     * Setter method for the athlete's zip code
     *
     * @param zip The athlete's zip code
     */
    public void setZip(int zip) {
        this.zip = zip;
    }

    /**
     * Getter method for the athlete's phone number
     *
     * @return The athlete's phone number
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Setter method for the athlete's phone number
     *
     * @param phone The athlete's phone number
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Getter method for the athlete's school
     *
     * @return The athlete's school
     */
    public String getSchool() {
        return school;
    }

    /**
     * Setter method for the athlete's school
     *
     * @param school The athlete's school
     */
    public void setSchool(String school) {
        this.school = school;
    }

    /**
     * Getter method for the athlete's height in centimeters
     * @return The athlete's height in cm
     */
    public double getHeight() {
        return height;
    }

    /**
     * Setter method for teh athlete's height in centimeters
     *
     * @param height The athlete's height in cm
     */
    public void setHeight(double height) {
        this.height = height;
    }

    /**
     * Getter method for the athlete's weight in kilograms
     *
     * @return The athlete's weight in kg
     */
    public double getWeight() {
        return weight;
    }

    /**
     * Setter method for the athlete's weight in kilograms
     *
     * @param weight The athlete's weight in kg
     */
    public void setWeight(double weight) {
        this.weight = weight;
    }

    /**
     * Getter method for the athlete's age in years
     *
     * @return The athlete's age in years
     */
    public int getAge() {
        return age;
    }

    /**
     * Setter method for the athlete's age in years
     *
     * @param age The athlete's age in years
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * Getter method for the athlete's gender
     *
     * @return The athlete's gender
     */
    public String getGender() {
        return gender;
    }

    /**
     * Setter method for the athlete's gender
     *
     * @param gender The athlete's gender
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * Getter method for the athlete's hand dominance
     *
     * @return The athlete's hand dominance
     */
    public String getHandDominance() {
        return handDominance;
    }

    /**
     * Setter method for the athlete's hand dominance
     *
     * @param handDominance The athlete's hand dominance
     */
    public void setHandDominance(String handDominance) {
        this.handDominance = handDominance;
    }

    /**
     * Getter method for the athlete's leg dominance
     *
     * @return The athlete's leg dominance
     */
    public String getLegDominance() {
        return legDominance;
    }

    /**
     * Setter method for the athlete's leg dominance
     *
     * @param legDominance The athlete's leg dominance
     */
    public void setLegDominance(String legDominance) {
        this.legDominance = legDominance;
    }

    /**
     * Getter method for the athlete's primary sport
     *
     * @return The athlete's primary sport
     */
    public String getPrimarySport() {
        return primarySport;
    }

    /**
     * Setter method for the athlete's primary sport
     *
     * @param primarySport The primary sport of the athlete
     */
    public void setPrimarySport(String primarySport) {
        this.primarySport = primarySport;
    }

    /**
     * Getter method for the primary position of the athlete
     *
     * @return The athlete's primary position
     */
    public String getPrimaryPosition() {
        return primaryPosition;
    }

    /**
     * Setter method for the primary position of the athlete
     *
     * @param primaryPosition The athlete's primary positions
     */
    public void setPrimaryPosition(String primaryPosition) {
        this.primaryPosition = primaryPosition;
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
        String sql;
        if (viewInfo == false) {
            sql = "INSERT INTO ATHLETE VALUES ("
                    + "null,"
                    + "'" + name + "'" + ","
                    + "'" + date + "'" + ","
                    + "'" + dateOfBirth + "'" + ","
                    + "'" + address + "'" + ","
                    + "'" + city + "'" + ","
                    + "'" + state + "'" + ","
                    + Integer.toString(zip) + ","
                    + "'" + phone + "'" + ","
                    + "'" + school + "'" + ","
                    + Double.toString(height) + ","
                    + Double.toString(weight) + ","
                    + Integer.toString(age) + ","
                    + "'" + gender + "'" + ","
                    + "'" + handDominance + "'" + ","
                    + "'" + legDominance + "'" + ","
                    + "'" + primarySport + "'" + ","
                    + "'" + primaryPosition + "'" + ","
                    + ");";

            Database.executeUpdate(sql);
        }
        if (viewInfo == true) {
            sql = "UPDATE ATHLETE SET"
                    + " name = " + "'" + name + "'" + ","
                    + " date = " + "'" + date + "'" + ","
                    + " dateOfBirth = " + "'" + dateOfBirth + "'" + ","
                    + " address = " + "'" + address + "'" + ","
                    + " city = " + "'" + city + "'" + ","
                    + " state = " + "'" + state + "'" + ","
                    + " zip = " + "'" + Integer.toString(zip) + "'" + ","
                    + " phone = " + "'" + phone + "'" + ","
                    + " school = " + "'" + school + "'" + ","
                    + " height = " + "'" + Double.toString(height) + "'" + ","
                    + " weight = " + "'" + Double.toString(weight) + "'" + ","
                    + " age = " + "'" + Integer.toString(age) + "'" + ","
                    + " gender = " + "'" + gender + "'" + ","
                    + " handDominance = " + "'" + handDominance + "'" + ","
                    + " legDominance = " + "'" + legDominance + "'" + ","
                    + " primarySport = " + "'" + primarySport + "'" + ","
                    + " primaryPosition = " + "'" + primaryPosition + "'"
                    + " WHERE ID = " + "'" + DBindex + "'" + ";";

            Database.executeUpdate(sql);
        }
    }
}