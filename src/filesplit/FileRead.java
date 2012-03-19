/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package filesplit;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Niels
 */
public class FileRead {

    static String line;
    static ArrayList<String> array = new ArrayList<>();

    public ArrayList<String> readfile(String filename) {
        try {
            // opens Text File
            FileReader fread = new FileReader(filename); 
            // loads the Text File into the Buffered Reader
            BufferedReader in = new BufferedReader(fread);
            //reads every line in and saves it into the String line
            //if the line = null -> end of file
            for (int i = 0; (line = in.readLine()) != null; i++) { 
                array.add(line);	
            }
        } catch (IOException e) {
            System.out.println("IO-Fehler!");
        }
        return array;
    }
}
