package main.formObjects;

import main.java.Database;

/**
 * 
 * @author Joshua Bolstad
 *
 */

public class ParQ {
	private boolean q1Ans, q2Ans, q3Ans, q4Ans, q5Ans, q6Ans;
	private String q7Ans;
	
	/**
	 * @param q1Ans
	 * @param q2Ans
	 * @param q3Ans
	 * @param q4Ans
	 * @param q5Ans
	 * @param q6Ans
	 * @param q7Ans
	 */
	public ParQ(boolean q1ans, boolean q2Ans, boolean q3Ans, boolean q4Ans, boolean q5Ans, boolean q6Ans, String q7Ans)
	{
		this.q1Ans = q1Ans;
		this.q2Ans = q2Ans;
		this.q3Ans = q3Ans;
		this.q4Ans = q4Ans;
		this.q5Ans = q5Ans;
		this.q6Ans = q6Ans;
		this.q7Ans = q7Ans;
	}
	
    /**
     * Adds a row to the database class. 
     * It is used in conjunction with the other forms, since the value for 
     * each table is autoincremented. 
     */
    public void addRow()
    {
        String sql;
            sql = "INSERT INTO PARQ VALUES ("
                + "null,"
                    + q1Ans + "," + q2Ans + "," + q3Ans + "," + q4Ans + "," + q5Ans + "," + q6Ans + "," +"'" + q7Ans +  "'" +");";
            
        Database.executeUpdate(sql);
    }
    
    public String toHTML()
    {
        String rowStart = "<tr><td>";
        String rowMid = "</td><td>";
        String rowEnd = "</td></tr>";
        String html = "</br></br></br><table><tr><th>Par-Q and You</th><th></th></tr>";
        html += rowStart + "Question 1 Answer" + rowMid + this.q1Ans + rowEnd
                + rowStart + "Question 2 Answer" + rowMid + this.q2Ans + rowEnd
                + rowStart + "Question 3 Answer" + rowMid + this.q3Ans + rowEnd
                + rowStart + "Question 4 Answer" + rowMid + this.q4Ans + rowEnd
                + rowStart + "Question 5 Answer" + rowMid + this.q5Ans + rowEnd
                + rowStart + "Question 6 Answer" + rowMid + this.q6Ans + rowEnd
                + rowStart + "Question 7 Answer" + rowMid + this.q7Ans + rowEnd
                        +"</table>";
                
        return html;
    }

}
