
package main.formObjects;

import java.util.Date;
import main.java.Database;

/**
 *
 * @author Ian
 */
public class Athlete
{
    private String name;
    private String date;
    private String dateOfBirth;
    private String address;
    private String city;
    private String state;
    
    private int zip;
    private String phone;
    private String school;
    private double height;
    private double weight;
    
    private int age;
    private String gender;
    private String handDominance;
    private String legDominance;
    private String primarySport;
    private String primaryPosition;
    
    /**
     * Creates an html table representation of this object. 
     * @return
     */
    public String toHTML()
    {
     String rowStart = "<tr><td>";
     String rowMid = "</td><td>";
     String rowEnd = "</td></tr>";
     
     String html = "</br></br></br><table><tr><th>Bowler Information</th><th></th></tr>";
     html+= rowStart + "Name" + rowMid + this.name + rowEnd
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
             + rowStart + "Primary Position" + rowMid + this.primaryPosition + rowEnd + "</table>";
     return html;
    }
 
    /**
     * Constructor of the Athlete class. 
     * 
     * @param name
     * @param date
     * @param dateOfBirth
     * @param address
     * @param city
     * @param state
     * @param zip
     * @param phone
     * @param school
     * @param height
     * @param weight
     * @param age
     * @param gender
     * @param handDominance
     * @param legDominance
     * @param primarySport
     * @param primaryPosition
     */
    public Athlete(String name, String date, String dateOfBirth, String address, String city, String state, int zip, String phone,
            String school, double height, double weight, int age, String gender, String handDominance, String legDominance,
            String primarySport, String primaryPosition)
    {
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
     *
     */
    public Athlete()
    {
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
     * @return the name
     */
    public String getName()
    {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name)
    {
        this.name = name;
    }
    
    /**
     * @return the date
     */
    public String getDate()
    {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(String date)
    {
        this.date = date;
    }

    /**
     * @return the dateOfBirth
     */
    public String getDateOfBirth()
    {
        return dateOfBirth;
    }

    /**
     * @param dateOfBirth the dateOfBirth to set
     */
    public void setDateOfBirth(String dateOfBirth)
    {
        this.dateOfBirth = dateOfBirth;
    }

    /**
     * @return the address
     */
    public String getAddress()
    {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address)
    {
        this.address = address;
    }

    
    /**
     * @return the city
     */
    public String getCity()
    {
        return city;
    }
    
    /**
     * @param city the city to set
     */
    public void setCity(String city)
    {
        this.city = city;
    }
    
     /**
     * @return the state
     */
    public String getState()
    {
        return state;
    }
    
    /**
     * @param state the state to set
     */
    public void setState(String state)
    {
        this.state = state;
    }
    
    /**
     * @return the zip
     */
    public int getZip()
    {
        return zip;
    }
    
    /**
     * @param zip the zip to set
     */
    public void setZip(int zip)
    {
        this.zip = zip;
    }
    
    /**
     * @return the phone
     */
    public String getPhone()
    {
        return phone;
    }

    /**
     * @param phone the phone to set
     */
    public void setPhone(String phone)
    {
        this.phone = phone;
    }

    /**
     * @return the school
     */
    public String getSchool()
    {
        return school;
    }

    /**
     * @param school the school to set
     */
    public void setSchool(String school)
    {
        this.school = school;
    }

    /**
     * @return the height
     */
    public double getHeight()
    {
        return height;
    }
    
    /**
     * @param height the height to set
     */
    public void setHeight(double height)
    {
        this.height = height;
    }
    
    /**
     * @return the weight
     */
    public double getWeight()
    {
        return weight;
    }

    /**
     * @param weight the weight to set
     */
    public void setWeight(double weight)
    {
        this.weight = weight;
    }
        
    /**
     * @return the age
     */
    public int getAge()
    {
        return age;
    }

    /**
     * @param age the age to set
     */
    public void setAge(int age)
    {
        this.age = age;
    }
    
    /**
     * @return the gender
     */
    public String getGender()
    {
        return gender;
    }

    /**
     * @param gender the gender to set
     */
    public void setGender(String gender)
    {
        this.gender = gender;
    }

    /**
     * @return the handDominance
     */
    public String getHandDominance()
    {
        return handDominance;
    }

    /**
     * @param handDominance the handDominance to set
     */
    public void setHandDominance(String handDominance)
    {
        this.handDominance = handDominance;
    }
    
    /**
     * @return the legDominance
     */
    public String getLegDominance()
    {
        return legDominance;
    }

    /**
     * @param legDominance the legDominace to set
     */
    public void setLegDominance(String legDominance)
    {
        this.legDominance = legDominance;
    }
    
     /**
     * @return the primarySport
     */
    public String getPrimarySport()
    {
        return primarySport;
    }

    /**
     * @param primarySport the primarySport to set
     */
    public void setPrimarySport(String primarySport)
    {
        this.primarySport = primarySport;
    }
    
    /**
     * @return the primaryPosition
     */
    public String getPrimaryPosition()
    {
        return primaryPosition;
    }

    /**
     * @param primaryPosition the primaryPosition to set
     */
    public void setPrimaryPosition(String primaryPosition)
    {
        this.primaryPosition = primaryPosition;
    }

    /**
     * Adds a row to the database class. 
     * It is used in conjunction with the other forms, since the value for 
     * each table is autoincremented. 
     */
    public void addRow()
    {
        String sql;
            sql = "INSERT INTO ATHLETE VALUES ("
                + "null,"
                + "'" + name + "'" + ","
                + "'" + date + "'" + "," 
                + "'" + dateOfBirth+ "'" + ","
                + "'" + address + "'" + ","
                + "'" + city + "'" + ","
                + "'" + state +"'" +  ","
                + Integer.toString(zip) + ","
                + "'" + phone +"'" +  ","
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
}

