package main.formObjects;
//This is a test designed to determine the personality of an athlete

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * PersonalityTest object.
 * <p>
 * Used for holding the questions and answers when an athlete is taking the personality test.
 * </p>
 */
public class personalityTest {
    /**
     * Pointer for the current question
     */
    private int count = 0;
    /**
     * Array to hold the athlete's answers for the personality test.
     * <p>
     * It will only hold values of "a" or "b"
     * </p>
     */
    private String[] personalityTestResults = new String[70];
    /**
     * Array to hold the questions as well as possible answers for the personality test
     * <p>
     * [i][0] holds the test question while [i][1] and [i][2] hold the available answers
     * </p>
     */
    private String[][] personalityTest = new String[70][3];
    /**
     * File path for the personality test
     */
    File file = new File("./src/main/DocumentsPDF/personalityT.txt");
    /**
     * String for the results of the test
     */
    private String testResults = "";

    /**
     * Constructor for the personality test object.
     * <p>
     * Creates the test when called by reading it in from the location heald in the file variable.
     * </p>
     */
    public personalityTest() {
        //Will create the test once called
        //Read the file into a string
        String[] fileRead = new String[70];
        Scanner s;
        try {
            s = new Scanner(file);
            while (s.hasNextLine()) {
                //System.out.println(s.nextLine());
                fileRead[count++] = s.nextLine();
            } //end while
        } catch (FileNotFoundException ex) {
            System.out.println("File error");
            Logger.getLogger(personalityTest.class.getName()).log(Level.SEVERE, null, ex);
        } //end try catch

        //check to make sure file was read correctly
        for (int i = 0; i < count; i++) {
            System.out.println(fileRead[i]);
        }//end for loop

        //System.out.println(count);
        //Split up the array by a. and b.
        for (int i = 0; i < 70; i++) {
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
        }//end for loop
    }

    /**
     * Calls the methods for grading the test
     *
     * @return The test results
     */
    public String createTestResults() {
        testResultsC1();
        testResultsC2();
        testResultsC3();
        testResultsC4();
        return testResults;
    }

    //grade first column

    /**
     * Method for grading the first column.
     * <p>
     * Doesn't return anything, but adds either the character "E" or character "I" to the end of the string
     * held in the variable testResults
     * </p>
     */
    public void testResultsC1() {
        int countA = 0;
        int countB = 0;
        for (int i = 0; i < 63; i = i + 7) {
            if (personalityTestResults[i].equals("a")) {
                countA++;
            } else {
                countB++;
            }
        }
        if (countA > countB) {
            testResults += "E";
        } else {
            testResults += "I";
        }
    }

    /**
     * Method for grading the second column.
     * <p>
     * Doesn't return anything, but adds either the character "S" or character "N" to the end of the string
     * held in the variable testResults
     * </p>
     */
    public void testResultsC2() {
        int countA = 0;
        int countB = 0;
        for (int i = 1; i < 64; i = i + 7) {
            if (personalityTestResults[i].equals("a")) {
                countA++;
            } else {
                countB++;
            }
        }
        for (int i = 2; i < 65; i = i + 7) {
            if (personalityTestResults[i].equals("a")) {
                countA++;
            } else {
                countB++;
            }
        }
        if (countA > countB) {
            testResults += "S";
        } else {
            testResults += "N";
        }
    }

    /**
     * Method for grading the third column.
     * <p>
     * Doesn't return anything, but adds either the character "T" or character "F" to the end of the string
     * held in the variable testResults
     * </p>
     */
    public void testResultsC3() {
        int countA = 0;
        int countB = 0;
        for (int i = 3; i < 66; i = i + 7) {
            if (personalityTestResults[i].equals("a")) {
                countA++;
            } else {
                countB++;
            }
        }
        for (int i = 4; i < 67; i = i + 7) {
            if (personalityTestResults[i].equals("a")) {
                countA++;
            } else {
                countB++;
            }
        }
        if (countA > countB) {
            testResults += "T";
        } else {
            testResults += "F";
        }
    }

    /**
     * Method for grading the fourth column.
     * <p>
     * Doesn't return anything, but adds either the character "J" or character "P" to the end of the string
     * held in the variable testResults
     * </p>
     */
    public void testResultsC4() {
        int countA = 0;
        int countB = 0;
        for (int i = 5; i < 68; i = i + 7) {
            if (personalityTestResults[i].equals("a")) {
                countA++;
            } else {
                countB++;
            }
        }
        for (int i = 6; i < 69; i = i + 7) {
            if (personalityTestResults[i].equals("a")) {
                countA++;
            } else {
                countB++;
            }
        }
        if (countA > countB) {
            testResults += "J";
        } else {
            testResults += "P";
        }
    }

    /**
     * Getter method for the variable testResults
     *
     * @return The string holding the characters from the results from the test
     */
    public String getTestResult() {
        return testResults;
    }

    /**
     * Getter method for the counter variable (which holds the index of the current question)
     *
     * @return The index of the current question
     */
    public int getCount() {
        return count;
    }

    /**
     * Setter method for the counter variable (which holds the index of the current question)
     *
     * @param count The index of the next question to ask
     */
    public void setCount(int count) {
        this.count = count;
    }

    /**
     * Getter method for the athlete's results of the personality test.
     *
     * @return The athlete's results of the personality test. It will be an array of answers. An answer can be either "a" or "b"
     */
    public String[] getPersonalityTestResults() {
        return personalityTestResults;
    }

    /**
     * Setter method for the athlete's results of the personality test
     *
     * @param personalityTestResults An array of answers to the personality test
     */
    public void setPersonalityTestResults(String[] personalityTestResults) {
        this.personalityTestResults = personalityTestResults;
    }

    /**
     * Getter method for the personality test.
     *
     * @return The array holding the questions and possible answers of the personality test
     */
    public String[][] getPersonalityTest() {
        return personalityTest;
    }

    /**
     * Setter method for the personality test
     *
     * @param personalityTest An array of questions and possible answers
     */
    public void setPersonalityTest(String[][] personalityTest) {
        this.personalityTest = personalityTest;
    }
}