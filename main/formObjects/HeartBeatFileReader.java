
package main.formObjects;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

/**
 *@deprecated 
 * @author Nicholas Bentzen
 * This class reads in a file of numbers associated with the range of heart rates
 * within a given ranking: Excellent, very good, good, etc.
 * Not implemented.
 */
public class HeartBeatFileReader
{
    private ArrayList<ArrayList<Integer>> list;

    /**
     * Constructor
     * @param fileName
     * @throws FileNotFoundException
     */
    public HeartBeatFileReader(String fileName) throws FileNotFoundException
    {
        this.list = new ArrayList<ArrayList<Integer>>();
        Scanner fileScanner = new Scanner(new File(fileName));
        Scanner lineReader;
        String line;
        
        while(fileScanner.hasNextLine())
        {
            ArrayList<Integer> lineList = new ArrayList<Integer>();
            line = fileScanner.nextLine();
            lineReader = new Scanner(line);
            
            while(lineReader.hasNextInt())
            {
                lineList.add(lineReader.nextInt());
            }
            
            this.list.add(lineList);
            lineReader.close();
        }
        
        fileScanner.close();
    }
    
    /**
     *
     * @param index
     * @return The array of integers.
     */
    public int[] getArr(int index)
    {
        ArrayList<Integer> list = this.list.get(index);
        int[] arr = new int[list.size()];
        int i = 0;
        
        for(Integer j : list)
        {
            arr[i] = j;
            i++;
        }
        
        return arr;
    }
}
