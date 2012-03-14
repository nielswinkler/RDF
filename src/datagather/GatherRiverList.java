/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package datagather;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Niels
 */
public class GatherRiverList {
    private static ArrayList<String> riverlist = new ArrayList<>();
    private static String line ;
    public static ArrayList<String> getRiverList(){

    try{
      FileReader fread = new FileReader("rivers.txt"); 
      BufferedReader in = new BufferedReader(fread);	
            
      for(int a = 0;(line = in.readLine())!=null; a++){
            riverlist.add(line); 
            }
        riverlist = GatherGeoNames.ohneDoppelte(riverlist);
        //System.out.println(riverlist.size());
        }

    catch(IOException e){
	System.out.println("IO-Fehler!");
    }
    
    return riverlist;
}
}
