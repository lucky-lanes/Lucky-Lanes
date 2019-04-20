
package main.formObjects;

import main.java.Database;

/**
 *
 * @author bentz
 * EDITED BY: Joshua Bolstad
 */
public class FitnessTest
{
    ////////Global Variables
    //vitals
    private int age, restingHR, restingHR1, restingHR2;
    private double height, bodyWeight, bmi, peakFlow;
    private String gender;

    //anthropomeetrics 
    private double ant1, ant2, antAvg, waistCirc, hipCirc, midThighCirc, flexArmCirc, hamCSA,
            quadCSA, totalThighCSA, biCirc; 
    
    //skinfold 
    private double triSkin, subSkin, abdSkin, supSkin, thighSkin, pecSkin, wallsit;

    //sit&reach
    private double startDist, endDist1, endDist2, endDist3, finalDist;
    
    //Muscle strength & Endurance Power
    private double hgR1, hgR2, hgR3, hgL1, hgL2, hgL3,
            proneTime, kneeExtForceR1, kneeExtForceR2, kneeExtForceL1, kneeExtForceL2,
            jh1, jh2, medPass1, medPass2;

    //Estimated Aerobic Capacity
    ////Ymca step test
    private double vO2Max, postVO2Max, ageRating;
    private int postHR;

    ////rockport walk test
    private int rockHR;
    private double walkTime, rockVO2Max;

    ////12-min walk test
    private double walkDistance, walkVO2Max, ACSMpercentile;

    /**
     *
     */
    public FitnessTest()
    {

    }

    /**
     * ******************* MASS SETTERS **********************
     * Sets all variables under "Vitals."
     * @param age
     * @param restingHR
     * @param restingHR2
     * @param restingHR1
     * @param height
     * @param bodyWeight
     * @param gender
     * @param bmi
     * @param peakFlow
     */
    public void setVitals(int age, int restingHR, int restingHR1, int restingHR2, double height,
            double bodyWeight, double bmi, String gender, double peakFlow)
    {
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
     * Sets all vars under anthropomeetrics.
     * EDITED BY: Joshua Bolstad
     * @param ant1
     * @param ant2
     * @param waistCirc
     * @param hipCirc
     * @param midThighCirc
     * @param flexArmCirc
     * @param hamCSA
     * @param quadCSA
     * @param totalThighCSA
     * @param biCirc
     */
    public void setAnthro(double ant1, double ant2, double waistCirc, double hipCirc, double midThighCirc, double flexArmCirc,
            double hamCSA, double quadCSA, double totalThighCSA, double biCirc)
    {
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
     * Sets all vars under skinfold
     * @author Joshua Bolstad
     * @param triSkin
     * @param subSkin
     * @param abdSkin
     * @param supSkin
     * @param thighSkin
     * @param pecSkin
     * @param wallsit
     */
    public void setSkin(double triSkin, double subSkin, double abdSkin, double supSkin, double thighSkin, double pecSkin, double wallsit)
    {
    	this.triSkin = triSkin;
    	this.subSkin = subSkin;
    	this.abdSkin = abdSkin;
    	this.supSkin = supSkin;
    	this.thighSkin = thighSkin;
    	this.pecSkin = pecSkin;
    	this.wallsit = wallsit;
    }

    /**
     * Sets all vars under sit and reach.
     * @param startDist
     * @param endDist1
     * @param endDist2
     * @param endDist3
     * @param finalDist
     */
    public void setSitAndReach(double startDist, double endDist1, double endDist2, double endDist3, double finalDist)
    {
        this.startDist = startDist;
        this.endDist1 = endDist1;
        this.endDist2 = endDist2;
        this.endDist3 = endDist3;
        this.finalDist = finalDist;
    }

    /**
     * Sets all vars under Muscle and Strength.
     * @param hgR1
     * @param hgR2
     * @param hgR3
     * @param hgL1
     * @param hgL2
     * @param hgL3
     * @param proneTime
     * @param kneeExtForceR1
     * @param kneeExtForceR2
     * @param kneeExtForceL1
     * @param kneeExtForceL2
     * @param jh1
     * @param jh2
     * @param medPass1
     * @param medPass2
     */
    public void setMuscleAndStrength(double hgR1, double hgR2, double hgR3, double hgL1, double hgL2,
            double hgL3, double proneTime, double kneeExtForceR1, double kneeExtForceR2, double kneeExtForceL1,
            double kneeExtForceL2, double jh1, double jh2, double medPass1, double medPass2)
    {

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
     * Sets all vars in Aerobic Capacity.
     * @param vO2Max
     * @param postHR
     * @param postVO2Max
     * @param ageRating
     * @param rockHR
     * @param walkTime
     * @param rockVO2Max
     * @param walkDistance
     * @param walkVO2Max
     * @param ACSMpercentile
     */
    public void setAerobicCapacity(double vO2Max, int postHR, double postVO2Max,
            double ageRating, int rockHR, double walkTime, double rockVO2Max,
            double walkDistance, double walkVO2Max, double ACSMpercentile)
    {
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
     * Adds a row to the database (class). 
     * It is used in conjunction with the other forms, since the value for 
     * each table is autoincremented. 
     */
    public void addRow()
    {
        String sql;
            sql = "INSERT INTO FITNESSDATA VALUES ("
                + "null,"
                + age + "," + restingHR + "," + restingHR1 + "," + restingHR2 + "," + height + "," + bodyWeight + "," + bmi + ","
                + peakFlow + ",'" + gender + "'," + ant1 + "," + ant2 + "," + antAvg + "," + waistCirc + "," + hipCirc + "," + midThighCirc
                + "," + flexArmCirc + "," + hamCSA + "," +quadCSA + "," + totalThighCSA + "," + biCirc + "," + triSkin + "," + subSkin + "," 
                + abdSkin + "," + supSkin + "," + thighSkin + "," + pecSkin + "," + wallsit + ", "+ startDist + "," + endDist1 + ","
                + endDist2 + "," + endDist3 + "," + finalDist + "," + hgR1 + "," + hgR2 + "," + hgR3 + "," + hgL1 + "," + hgL2 + ","
                + hgL3 + "," + proneTime + "," + kneeExtForceR1 + "," + kneeExtForceR2 + "," + kneeExtForceL1 + "," + kneeExtForceL2 + ","
                + jh1 + "," + jh2 + "," + medPass1 + "," + medPass2 + "," + vO2Max + "," + postVO2Max + "," + ageRating + "," + postHR
                + "," + walkTime + "," + rockVO2Max + "," + rockHR + "," + walkDistance + "," + walkVO2Max + "," + ACSMpercentile+ ");";
            
        Database.executeUpdate(sql);
    }

    /**
     * String representation of an html table of this object.
     * @return
     */
    public String toHTML()
    {
        String rowStart = "<tr><td>";
        String rowMid = "</td><td>";
        String rowEnd = "</td></tr>";
        String html = "<br><br><br><h2>Functional Movement Screen</h2>"
                + "<table><tr><th>Vitals</th><th></th></tr>"
                + "<tr><td>Age</td><td>" + this.age + "</td></tr>"
                +"<tr><td>Resting HeartRate</td><td>"+restingHR1+"</td></tr>"
                +"<tr><td>Resting HeartRate 1:</td><td>"+this.restingHR1+"</td></tr>"
                +"<tr><td>Resting HeartRate 2:</td><td>"+this.restingHR2+"</td></tr>"
                +rowStart +"Height:" + rowMid + this.height + rowEnd
                +rowStart + "Body Weight" + rowMid + this.bodyWeight + rowEnd
                +rowStart + "Body Mass Index (BMI)" + rowMid + this.bmi + rowEnd
                + rowStart + "Peak Flow" + rowMid + this.peakFlow + rowEnd
                + "</table>"+"<table><tr><th>Anthropometrics</th><th></th></tr>"
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
        
        html+= "<table><tr><th>Muscle & Strength</th><th></th></tr>"
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
        
        html+= "<table><tr><th>Aerobic Capacity</th><th></th></tr>"
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

    /***
     * gets/sets each individual variable. 
     * @return 
     */
    public int getAge()
    {
        return age;
    }

    /**
     *
     * @param age
     */
    public void setAge(int age)
    {
        this.age = age;
    }

    /**
     *
     * @return
     */
    public int getRestingHR()
    {
        return restingHR;
    }

    /**
     *
     * @param restingHR
     */
    public void setRestingHR(int restingHR)
    {
        this.restingHR = restingHR;
    }

    /**
     *
     * @return
     */
    public int getRestingHR1()
    {
        return restingHR1;
    }

    /**
     *
     * @param restingHR1
     */
    public void setRestingHR1(int restingHR1)
    {
        this.restingHR1 = restingHR1;
    }

    /**
     *
     * @return
     */
    public int getRestingHR2()
    {
        return restingHR2;
    }

    /**
     *
     * @param restingHR2
     */
    public void setRestingHR2(int restingHR2)
    {
        this.restingHR2 = restingHR2;
    }

    /**
     *
     * @return
     */
    public double getHeight()
    {
        return height;
    }

    /**
     *
     * @param height
     */
    public void setHeight(double height)
    {
        this.height = height;
    }

    /**
     *
     * @return
     */
    public double getBodyWeight()
    {
        return bodyWeight;
    }

    /**
     *
     * @param bodyWeight
     */
    public void setBodyWeight(double bodyWeight)
    {
        this.bodyWeight = bodyWeight;
    }

    /**
     *
     * @return
     */
    public double getBmi()
    {
        return bmi;
    }

    /**
     *
     * @param bmi
     */
    public void setBmi(double bmi)
    {
        this.bmi = bmi;
    }

    /**
     *
     * @return
     */
    public double getPeakFlow()
    {
        return peakFlow;
    }

    /**
     *
     * @param peakFlow
     */
    public void setPeakFlow(double peakFlow)
    {
        this.peakFlow = peakFlow;
    }

    /**
     *
     * @return
     */
    public String getGender()
    {
        return gender;
    }

    /**
     *
     * @param gender
     */
    public void setGender(String gender)
    {
        this.gender = gender;
    }

    /**
     *
     * @return
     */
    public double getAnt1()
    {
        return ant1;
    }

    /**
     *
     * @param ant1
     */
    public void setAnt1(double ant1)
    {
        this.ant1 = ant1;
    }

    /**
     *
     * @return
     */
    public double getAnt2()
    {
        return ant2;
    }

    /**
     *
     * @param ant2
     */
    public void setAnt2(double ant2)
    {
        this.ant2 = ant2;
    }

    /**
     *
     * @return
     */
    public double getAntAvg()
    {
        return antAvg;
    }

    /**
     *
     * @param antAvg
     */
    public void setAntAvg(double antAvg)
    {
        this.antAvg = antAvg;
    }

    /**
     *
     * @return
     */
    public double getWaistCirc()
    {
        return waistCirc;
    }

    /**
     *
     * @param waistCirc
     */
    public void setWaistCirc(double waistCirc)
    {
        this.waistCirc = waistCirc;
    }

    /**
     *
     * @return
     */
    public double getHipCirc()
    {
        return hipCirc;
    }

    /**
     *
     * @param hipCirc
     */
    public void setHipCirc(double hipCirc)
    {
        this.hipCirc = hipCirc;
    }

    /**
     *
     * @return
     */
    public double getMidThighCirc()
    {
        return midThighCirc;
    }

    /**
     *
     * @param midThighCirc
     */
    public void setMidThighCirc(double midThighCirc) 
    {
        this.midThighCirc = midThighCirc;
    }

    /**
     *
     * @return
     */
    public double getFlexArmCirc() 
    {
        return flexArmCirc;
    }

    /**
     *
     * @param flexArmCirc
     */
    public void setFlexArmCirc(double flexArmCirc)
    {
        this.flexArmCirc = flexArmCirc;
    }

    /**
     *
     * @return
     */
    public double getHamCSA()
    {
        return hamCSA;
    }

    /**
     *
     * @param hamCSA
     */
    public void setHamCSA(double hamCSA)
    {
        this.hamCSA = hamCSA;
    }

    /**
     *
     * @return
     */
    public double getQuadCSA()
    {
        return quadCSA;
    }

    /**
     *
     * @param quadCSA
     */
    public void setQuadCSA(double quadCSA)
    {
        this.quadCSA = quadCSA;
    }

    /**
     *
     * @return
     */
    public double getTotalThighCSA()
    {
        return totalThighCSA;
    }
    
    /**
     *
     * @param totalThighCSA
     */
    public void setTotalThighCSA(double totalThighCSA)
    {
        this.totalThighCSA = totalThighCSA;
    }
    
    public double getBiCirc() 
    {
		return biCirc;
	}
    
    public void setBiCirc(double biCirc) 
    {
		this.biCirc = biCirc;
	}
    
    public double getTriSkin() 
    {
		return triSkin;
	}
    
    public void setTriSkin(double triSkin)
    {
		this.triSkin = triSkin;
	}
    
    public double getSubSkin()
    {
		return subSkin;
	}
    
    public void setSubSkin(double subSkin) 
    {
		this.subSkin = subSkin;
	}
    
    public double getAbdSkin() 
    {
		return abdSkin;
	}
    
    public void setAbdSkin(double abdSkin) 
    {
		this.abdSkin = abdSkin;
	}
    
    public double getSupSkin() 
    {
		return supSkin;
	}
    
    public void setSupSkin(double supSkin) 
    {
		this.supSkin = supSkin;
	}
    
    public double getThighSkin() 
    {
		return thighSkin;
	}
    
    public void setThighSkin(double thighSkin)
    {
		this.thighSkin = thighSkin;
	}
    
    
    public double getPecSkin() 
    {
		return pecSkin;
	}
    
    public void setPecSkin(double pecSkin) 
    {
		this.pecSkin = pecSkin;
	}
    

    public double getWallsit() 
    {
		return wallsit;
	}

    public void setWallsit(double wallsit) 
    {
		this.wallsit = wallsit;
	}
    
    /**
     *
     * @return
     */
    public double getStartDist()
    {
        return startDist;
    }

    /**
     *
     * @param startDist
     */
    public void setStartDist(double startDist)
    {
        this.startDist = startDist;
    }

    /**
     *
     * @return
     */
    public double getEndDist1()
    {
        return endDist1;
    }

    /**
     *
     * @param endDist1
     */
    public void setEndDist1(double endDist1)
    {
        this.endDist1 = endDist1;
    }

    /**
     *
     * @return
     */
    public double getEndDist2()
    {
        return endDist2;
    }

    /**
     *
     * @param endDist2
     */
    public void setEndDist2(double endDist2)
    {
        this.endDist2 = endDist2;
    }

    /**
     *
     * @return
     */
    public double getEndDist3()
    {
        return endDist3;
    }

    /**
     *
     * @param endDist3
     */
    public void setEndDist3(double endDist3)
    {
        this.endDist3 = endDist3;
    }

    /**
     *
     * @return
     */
    public double getFinalDist()
    {
        return finalDist;
    }

    /**
     *
     * @param finalDist
     */
    public void setFinalDist(double finalDist)
    {
        this.finalDist = finalDist;
    }

    /**
     *
     * @return
     */
    public double getHgR1()
    {
        return hgR1;
    }

    /**
     *
     * @param hgR1
     */
    public void setHgR1(double hgR1)
    {
        this.hgR1 = hgR1;
    }

    /**
     *
     * @return
     */
    public double getHgR2()
    {
        return hgR2;
    }

    /**
     *
     * @param hgR2
     */
    public void setHgR2(double hgR2)
    {
        this.hgR2 = hgR2;
    }

    /**
     *
     * @return
     */
    public double getHgR3()
    {
        return hgR3;
    }

    /**
     *
     * @param hgR3
     */
    public void setHgR3(double hgR3)
    {
        this.hgR3 = hgR3;
    }

    /**
     *
     * @return
     */
    public double getHgL1()
    {
        return hgL1;
    }

    /**
     *
     * @param hgL1
     */
    public void setHgL1(double hgL1)
    {
        this.hgL1 = hgL1;
    }

    /**
     *
     * @return
     */
    public double getHgL2()
    {
        return hgL2;
    }

    /**
     *
     * @param hgL2
     */
    public void setHgL2(double hgL2)
    {
        this.hgL2 = hgL2;
    }

    /**
     *
     * @return
     */
    public double getHgL3()
    {
        return hgL3;
    }

    /**
     *
     * @param hgL3
     */
    public void setHgL3(double hgL3)
    {
        this.hgL3 = hgL3;
    }

    /**
     *
     * @return
     */
    public double getProneTime()
    {
        return proneTime;
    }

    /**
     *
     * @param proneTime
     */
    public void setProneTime(double proneTime)
    {
        this.proneTime = proneTime;
    }

    /**
     *
     * @return
     */
    public double getKneeExtForceR1()
    {
        return kneeExtForceR1;
    }

    /**
     *
     * @param kneeExtForceR1
     */
    public void setKneeExtForceR1(double kneeExtForceR1)
    {
        this.kneeExtForceR1 = kneeExtForceR1;
    }

    /**
     *
     * @return
     */
    public double getKneeExtForceR2()
    {
        return kneeExtForceR2;
    }

    /**
     *
     * @param kneeExtForceR2
     */
    public void setKneeExtForceR2(double kneeExtForceR2)
    {
        this.kneeExtForceR2 = kneeExtForceR2;
    }

    /**
     *
     * @return
     */
    public double getKneeExtForceL1()
    {
        return kneeExtForceL1;
    }

    /**
     *
     * @param kneeExtForceL1
     */
    public void setKneeExtForceL1(double kneeExtForceL1)
    {
        this.kneeExtForceL1 = kneeExtForceL1;
    }

    /**
     *
     * @return
     */
    public double getKneeExtForceL2()
    {
        return kneeExtForceL2;
    }

    /**
     *
     * @param kneeExtForceL2
     */
    public void setKneeExtForceL2(double kneeExtForceL2)
    {
        this.kneeExtForceL2 = kneeExtForceL2;
    }

    /**
     *
     * @return
     */
    public double getJh1()
    {
        return jh1;
    }

    /**
     *
     * @param jh1
     */
    public void setJh1(double jh1)
    {
        this.jh1 = jh1;
    }

    /**
     *
     * @return
     */
    public double getJh2()
    {
        return jh2;
    }

    /**
     *
     * @param jh2
     */
    public void setJh2(double jh2)
    {
        this.jh2 = jh2;
    }

    /**
     *
     * @return
     */
    public double getMedPass1()
    {
        return medPass1;
    }

    /**
     *
     * @param medPass1
     */
    public void setMedPass1(double medPass1)
    {
        this.medPass1 = medPass1;
    }

    /**
     *
     * @return
     */
    public double getMedPass2()
    {
        return medPass2;
    }

    /**
     *
     * @param medPass2
     */
    public void setMedPass2(double medPass2)
    {
        this.medPass2 = medPass2;
    }

    /**
     *
     * @return
     */
    public double getvO2Max()
    {
        return vO2Max;
    }

    /**
     *
     * @param vO2Max
     */
    public void setvO2Max(double vO2Max)
    {
        this.vO2Max = vO2Max;
    }

    /**
     *
     * @return
     */
    public double getPostVO2Max()
    {
        return postVO2Max;
    }

    /**
     *
     * @param postVO2Max
     */
    public void setPostVO2Max(double postVO2Max)
    {
        this.postVO2Max = postVO2Max;
    }

    /**
     *
     * @return
     */
    public double getAgeRating()
    {
        return ageRating;
    }

    /**
     *
     * @param ageRating
     */
    public void setAgeRating(double ageRating)
    {
        this.ageRating = ageRating;
    }

    /**
     *
     * @return
     */
    public int getPostHR()
    {
        return postHR;
    }

    /**
     *
     * @param postHR
     */
    public void setPostHR(int postHR)
    {
        this.postHR = postHR;
    }

    /**
     *
     * @return
     */
    public int getRockHR()
    {
        return rockHR;
    }

    /**
     *
     * @param rockHR
     */
    public void setRockHR(int rockHR)
    {
        this.rockHR = rockHR;
    }

    /**
     *
     * @return
     */
    public double getWalkTime()
    {
        return walkTime;
    }

    /**
     *
     * @param walkTime
     */
    public void setWalkTime(double walkTime)
    {
        this.walkTime = walkTime;
    }

    /**
     *
     * @return
     */
    public double getRockVO2Max()
    {
        return rockVO2Max;
    }

    /**
     *
     * @param rockVO2Max
     */
    public void setRockVO2Max(double rockVO2Max)
    {
        this.rockVO2Max = rockVO2Max;
    }

    /**
     *
     * @return
     */
    public double getWalkDistance()
    {
        return walkDistance;
    }

    /**
     *
     * @param walkDistance
     */
    public void setWalkDistance(double walkDistance)
    {
        this.walkDistance = walkDistance;
    }

    /**
     *
     * @return
     */
    public double getWalkVO2Max()
    {
        return walkVO2Max;
    }

    /**
     *
     * @param walkVO2Max
     */
    public void setWalkVO2Max(double walkVO2Max)
    {
        this.walkVO2Max = walkVO2Max;
    }

    /**
     *
     * @return
     */
    public double getACSMpercentile()
    {
        return ACSMpercentile;
    }

    /**
     *
     * @param ACSMpercentile
     */
    public void setACSMpercentile(double ACSMpercentile)
    {
        this.ACSMpercentile = ACSMpercentile;
    }
}