package main.formObjects;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author Nicholas Bentzen
 * This class reads in a file of numbers associated with the range of heart rates
 * within a given ranking: Excellent, very good, good, etc.
 * Not implemented.
 * @deprecated
 */
public class HeartBeatFileReader {
    private ArrayList<ArrayList<Integer>> list;

    /**
     * Constructor for the class
     *
     * @param fileName Filepath for the file of numbers associated with the range of heart rates
     * @throws FileNotFoundException
     */
    public HeartBeatFileReader(String fileName) throws FileNotFoundException {
        this.list = new ArrayList<ArrayList<Integer>>();
        Scanner fileScanner = new Scanner(new File(fileName));
        Scanner lineReader;
        String line;

        while (fileScanner.hasNextLine()) {
            ArrayList<Integer> lineList = new ArrayList<Integer>();
            line = fileScanner.nextLine();
            lineReader = new Scanner(line);

            while (lineReader.hasNextInt()) {
                lineList.add(lineReader.nextInt());
            }

            this.list.add(lineList);
            lineReader.close();
        }

        fileScanner.close();
    }

    /**
     * Gets and returns an array of integers associated with the range of heart rates
     *
     * @param index Unsure what this is an index for
     * @return The array of integers.
     */
    public int[] getArr(int index) {
        ArrayList<Integer> list = this.list.get(index);
        int[] arr = new int[list.size()];
        int i = 0;

        for (Integer j : list) {
            arr[i] = j;
            i++;
        }

        return arr;
    }
}