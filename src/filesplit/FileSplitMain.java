/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package filesplit;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

/**
 *
 * @author Niels
 */
public class FileSplitMain {

    public static void main(String[] args) throws IOException {

        int a = 2;
        //Read the file York_Daten.txt and saves it into the
        //ArrayList<String> array.
        ArrayList<String> array;
        FileRead FileReadObj = new FileRead();
        array = FileReadObj.readfile("York_Daten.txt");
        //creats a new Buffered Writer
        BufferedWriter out = new BufferedWriter(
                new OutputStreamWriter(
                new FileOutputStream("York_Daten_Chapter1.txt")));
        
        for (int i = 0; i < array.size(); i++) {
            if (array.get(i).equals("")) {
                out.close();
                out = new BufferedWriter(new OutputStreamWriter(
                        new FileOutputStream("York_Daten_Chapter" + a + ".txt")));
                a++;i++;
            }
            //writes the String, which is saved under the Index i of the
            //List array, into the new Textdoc
            out.write("" + array.get(i).toString());
            out.newLine();
        }
        out.close();
    }
}
