
package main.formObjects;
//This is a test designed to determine the personality of an athlete
import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
public class personalityTest {

    //move the pointer up one
    private int count = 0;
    //Define an array to hold all results
    //Will only hold values a or b
    //methods will count up the correct questions to determine dominate type
    private String[] personalityTestResults = new String[70]; //70 questions
    //All test questions will be in an 2d Array
    // [i][0] will hold Test question
    // [i][1] and [i][2] Will hold the options
    private String[][] personalityTest = new String[70][3];
    //Variable for path to file
    File file = new File("./src/main/DocumentsPDF/personalityT.txt");
    //test result
    private String testResults ="";
    //constructor
    public personalityTest(){
        //Will create the test once called
        //Read the file into a string
        String[] fileRead = new String[70];
        //Read in file
        Scanner s;
        try {
            s = new Scanner(file);
            while(s.hasNextLine()){
            //System.out.println(s.nextLine());
            fileRead[count++] = s.nextLine();
            }//while
        }catch (FileNotFoundException ex) {
            System.out.println("File error");
            Logger.getLogger(personalityTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //check to make sure file was read correctly
        for(int i = 0; i < count; i++){
            System.out.println(fileRead[i]);
        }//for
        
        //System.out.println(count);
        //Split up the array by a. and b.
        for(int i = 0; i < 70; i++){
            String[] data = fileRead[i].split("a\\.");
            personalityTest[i][0] = data[0];
            String[] newData = data[1].split("b\\.");
            personalityTest[i][1] = newData[0];
            personalityTest[i][2] = newData[1];
            //Test to make sure everything is correct
            /*
            for(int y = 0; y < 3; y++){
                System.out.println(personalityTest[i][y]);
            }
            */       
        }//for
        
    }//personailtyTestResults
    //calls all the methods for grading the test 
    //returns the test results
    public String createTestResults(){
        testResultsC1();
        testResultsC2();
        testResultsC3();
        testResultsC4();
        return testResults;
    }//createTestResults
    //grade first column
    public void testResultsC1(){
        int countA =0;
        int countB =0;
        for(int i =0; i <63; i=i+7){
            if(personalityTestResults[i].equals("a")){
                countA++;
            }else{
                countB++;
            }
        }if(countA > countB){
            testResults += "E";
        }else{
            testResults += "I";
        }
    }
    //grade second column
        public void testResultsC2(){
        int countA =0;
        int countB =0;
        for(int i =1; i <64; i=i+7){
            if(personalityTestResults[i].equals("a")){
                countA++;
            }else{
                countB++;
            }
        }
        for(int i =2; i <65; i=i+7){
            if(personalityTestResults[i].equals("a")){
                countA++;
            }else{
                countB++;
            }
        }if(countA > countB){
            testResults += "S";
        }else{
            testResults += "N";
        }
    }
    //grade third column
    public void testResultsC3(){
        int countA =0;
        int countB =0;
        for(int i =3; i <66; i=i+7){
            if(personalityTestResults[i].equals("a")){
                countA++;
            }else{
                countB++;
            }
        }
        for(int i =4; i <67; i=i+7){
            if(personalityTestResults[i].equals("a")){
                countA++;
            }else{
                countB++;
            }
        }if(countA > countB){
            testResults += "T";
        }else{
            testResults += "F";
        }
    }
    //grade fourth column
    public void testResultsC4(){
        int countA =0;
        int countB =0;
        for(int i =5; i <68; i=i+7){
            if(personalityTestResults[i].equals("a")){
                countA++;
            }else{
                countB++;
            }
        }
        for(int i =6; i <69; i=i+7){
            if(personalityTestResults[i].equals("a")){
                countA++;
            }else{
                countB++;
            }
        }if(countA > countB){
            testResults += "J";
        }else{
            testResults += "P";
        }
    }
    /**
     * @return the count
     */
    public String getTestResult(){
        return testResults;
    }
    public int getCount() {
        return count;
    }

    /**
     * @param count the count to set
     */
    public void setCount(int count) {
        this.count = count;
    }

    /**
     * @return the personalityTestResults
     */
    public String[] getPersonalityTestResults() {
        return personalityTestResults;
    }

    /**
     * @param personalityTestResults the personalityTestResults to set
     */
    public void setPersonalityTestResults(String[] personalityTestResults) {
        this.personalityTestResults = personalityTestResults;
    }

    /**
     * @return the personalityTest
     */
    public String[][] getPersonalityTest() {
        return personalityTest;
    }

    /**
     * @param personalityTest the personalityTest to set
     */
    public void setPersonalityTest(String[][] personalityTest) {
        this.personalityTest = personalityTest;
    } 
}


































