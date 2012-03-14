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
            FileReader fread = new FileReader(filename); //öffntet die gewünschte txt Datei
            BufferedReader in = new BufferedReader(fread); // txt wird in den BufferReader geladen

            for (int i = 0; (line = in.readLine()) != null; i++) { // durchlaufen, bis line = null(datei zu ende ist)
                array.add(line); // array[i] wird mit line(der aktuellen Zeile) belegt	

            }
        } catch (IOException e) {
            System.out.println("IO-Fehler!");
        }
        return array;
    }
}
