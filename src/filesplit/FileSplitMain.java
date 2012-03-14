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

    public static void main(String [] args) throws IOException{
	
        int a = 2;
        int b = 0;
        
        ArrayList<String> array ;
                
	array = filesplit.FileRead.readfile("York_Daten.txt");
              
        
        BufferedWriter out = new BufferedWriter(
                    new OutputStreamWriter(
                    new FileOutputStream( "York_Daten_Chapter1.txt" ) ) );

        for(int i=0; i < array.size(); i++){

            if(array.get(i).equals("")){
                out.close();
                out = new BufferedWriter(new OutputStreamWriter(
                        new FileOutputStream( "York_Daten_Chapter"+a+".txt" ) ));
                a++;i++;                
            }

            out.write(""+array.get(i).toString());
            out.newLine();



        }
        out.close();
                
                
}
}

