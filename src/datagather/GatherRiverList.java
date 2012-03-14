/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package datagather;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

/**
 *
 * @author Niels
 */
public class GatherRiverList {

    private static ArrayList<String> riverlist = new ArrayList<>();
    private static String line;

    public ArrayList<String> getRiverList() {

        try {
            FileReader fread = new FileReader("rivers.txt");
            BufferedReader in = new BufferedReader(fread);

            for (int a = 0; (line = in.readLine()) != null; a++) {
                riverlist.add(line);
            }
            riverlist = new ArrayList<>(new HashSet<>(riverlist));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return riverlist;
    }
}
