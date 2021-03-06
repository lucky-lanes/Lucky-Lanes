package main.java;

import java.io.*;

/**
 * This class was used to debug the deployed program.
 * It creates a medium to print to a text file errors or steps in a method.
 *
 * @author Mario
 */
public class Trace {
    /**
     * The file where the trace will be written to
     */
    static File file;

    /**
     * Creates the file with the name Trace.txt.
     * <p>
     * Will print to System.out as to if the file has been created successfully or 
     * if it already exists
     */
    public static void createFile() {
        try {
            file = new File("Trace.txt");

            boolean flag = file.createNewFile();
            if (flag) {
                System.out.println("File has been created successfully");
            } else {
                System.out.println("File already present at the specified location");
            }
        } catch (IOException e) {
            System.out.println("Exception Occurred:");
            e.printStackTrace();
        }
    }

    /**
     * Writes text to the file
     *
     * @param text The String of text that will be written to the file
     */
    public static void print(String text) {
        PrintWriter out = null;
        try {
            out = new PrintWriter(new BufferedWriter(new FileWriter(file, true)));
            out.println(text);
        } catch (IOException e) {
            System.err.println(e);
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }
}
