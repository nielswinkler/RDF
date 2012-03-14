/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package datagather;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Resource;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Niels
 */
public class DataGatherMain {
    
    private static ArrayList<String> dataListDBPedia_Towns;
    private static ArrayList<String> dataListDBPedia_People;
    private static ArrayList<String> dataListDBPedia_Kings;
    private static ArrayList<String> dataListGeoNames;
    private static ArrayList<String> dataListRivers;
    private static ArrayList<String> readedchapter = new ArrayList<>();
    
    private static int counter;
    private static String line = " ";
    private static Resource Chapter;
    private static Resource Book;
    
    //ArrayList<String> dataListBDPedia = new ArrayList<>();
    
    public static void main(String [] args) throws Exception{
        
        Model modelChapter = ModelFactory.createDefaultModel();
        Model modelBook = ModelFactory.createDefaultModel();
        
        dataListDBPedia_Towns = GatherDBPedia.getresulttowns(GatherQuerys.QUERY_ONTOLOGY_SERVICE_DBPEDIA,
                GatherQuerys.QUERY_ENDPOINT_DBPEDIA, GatherQuerys.QUERY_TOWN_DBPEDIA);
        dataListDBPedia_Towns = GatherGeoNames.ohneDoppelte(dataListDBPedia_Towns);
        
        //System.out.println(dataListDBPedia_Towns.toString());
        
        
        dataListDBPedia_People = GatherDBPedia.getresultpeople(GatherQuerys.QUERY_ONTOLOGY_SERVICE_DBPEDIA,
                GatherQuerys.QUERY_ENDPOINT_DBPEDIA, GatherQuerys.QUERY_PEOPLE_DBPEDIA);
        dataListDBPedia_People = GatherGeoNames.ohneDoppelte(dataListDBPedia_People);
        
        //System.out.println(dataListDBPedia_People.toString());
        
        dataListDBPedia_Kings = GatherDBPedia.getresultkings(GatherQuerys.QUERY_ONTOLOGY_SERVICE_DBPEDIA,
                GatherQuerys.QUERY_ENDPOINT_DBPEDIA, GatherQuerys.QUERY_KINGS_DBPEDIA);
        dataListDBPedia_Kings = GatherGeoNames.ohneDoppelte(dataListDBPedia_Kings);
        
        //System.out.println(dataListDBPedia_Kings.toString());
        
        dataListGeoNames = GatherGeoNames.getresultgeonames();
        dataListGeoNames = GatherGeoNames.ohneDoppelte(dataListGeoNames);
        //System.out.println(dataListGeoNames.toString());
    
        dataListRivers = GatherRiverList.getRiverList();
        dataListRivers = GatherGeoNames.ohneDoppelte(dataListRivers);
        
      try{
          
     for(int b = 1; b < 601;b++){
      FileReader fread = new FileReader("York_Daten_Chapter"+b+".txt"); 
      BufferedReader in = new BufferedReader(fread);	
            
      for(int a = 0;(line = in.readLine())!=null; a++){
            readedchapter.add(line); 
            }
      
      String br = readedchapter.toString();
      br = br.replaceAll(",","");
      //System.out.println(br);
      
      String chapterfilepath    = "http://somewhere/York_Daten_Chapter"+b+".txt";
      String bookfilepath    = "http://somewhere/York_Daten.txt";
      
      Book = modelBook.createResource(bookfilepath);
      Chapter = modelChapter.createResource(chapterfilepath);
      
      Book = createRDF.CreateRDF.createbookrdf(Book, chapterfilepath);
      Chapter = createRDF.CreateRDF.createcontent(Chapter,br,"York_Daten_Chapter"+b,bookfilepath);
      
      if(br.matches("(.*)Roger(.*)") & (br.matches("(.*)archbishop of York(.*)") || br.matches("(.*)archbishop(.*)") )){
          
          Chapter = createRDF.CreateRDF.createarchbishop(Chapter);
          
          //System.out.println("hi");
          
          counter++;}
      if(br.matches("(.*)casati(.*)")){
          Chapter = createRDF.CreateRDF.createcasati(Chapter);
          counter++;
      }      
      for(int i=0; i < dataListDBPedia_Towns.size(); i++){
            String towndbpedia = dataListDBPedia_Towns.get(i).toString();
            String rdftowndbpedia = dataListDBPedia_Towns.get(i).toString();
            towndbpedia = towndbpedia.split("&")[0].replace(" ","").replace("\n","");
            if(br.matches("(.*)"+towndbpedia+"(.*)")){
                Chapter = createRDF.CreateRDF.createtowndbpedia(Chapter,rdftowndbpedia.split("&")[1].replace(" ","").replace("\n",""));
                //System.out.println(rdftown.split("&")[1].replace("\n","") + " exists in ");
                counter++;
                }
        }
      for(int i=0; i < dataListDBPedia_Kings.size(); i++){
            String kingnames = dataListDBPedia_Kings.get(i).toString();
            String rdfkingnames = dataListDBPedia_Kings.get(i).toString();
            kingnames = kingnames.split("of")[0].split(",")[0].replace("\n","");
            //System.out.println(town);
	    if(br.replace(" ","").matches("(.*)"+kingnames.replace(" ","")+"(.*)")& !(kingnames.replace(" ","").equals("John")| kingnames.replace(" ","").equals("Stephen"))){
                Chapter = createRDF.CreateRDF.createkingsdbpedia(Chapter,rdfkingnames.split("&")[1].replace(" ",""));
                //System.out.println(kingnames + " exists in ");
                counter++;
                }
        }
      for(int i=0; i < dataListGeoNames.size(); i++){
            String towngeonames = dataListGeoNames.get(i).toString();
            String rdftowngeonames = dataListGeoNames.get(i).toString();
            towngeonames=towngeonames.split("&")[0].replace(" ","").replace("\n","");
            //System.out.println(town);
	    if(br.matches("(.*)"+towngeonames+"(.*)")){
                Chapter = createRDF.CreateRDF.creategeonames(Chapter, rdftowngeonames.split("&")[0].replace(" ",""),rdftowngeonames.split("&")[1].replace(" ",""),
                        rdftowngeonames.split("&")[2].replace(" ",""),rdftowngeonames.split("&")[3].replace(" ",""));
                //System.out.println("<http://dbpedia.org/page/"+town+">");
                //System.out.println(town + " exists in ");
                //counter++;
                }
        }
      //System.out.println(dataListRivers.size());
      for(int c=0; c < dataListRivers.size(); c++){
           String river = dataListRivers.get(c);
           if(br.matches("(.*)"+river+"(.*)") & ((br.matches("(.*)river(.*)"))|(br.matches("(.*)sea(.*)")))){
               //System.out.println("<http://dbpedia.org/page/River_"+river+">");
                //System.out.println(river + " exists in ");
                //counter++;
                }
      }
      for(int i=0; i < dataListDBPedia_People.size(); i++){
            String people = dataListDBPedia_People.get(i).toString();
            String rdfpeopledbpedia = dataListDBPedia_People.get(i).toString();
            people = people.split("&")[0].split(",")[0].replace("Æ", "A").replace("(","!");
            people = people.split("!")[0].replace("\n","");
            //System.out.println(people);
	    if(br.replace(" ","").matches("(.*)"+people.replace(" ","")+"(.*)")& !(people.replace(" ","").equals("John")| people.replace(" ","").equals("Richard") | people.replace(" ","").equals("Margaret"))){
                Chapter = createRDF.CreateRDF.createpeopledbpedia(Chapter,rdfpeopledbpedia.split("&")[1].replace(" ",""));
                //System.out.println(rdfpeople.split("&")[0].replace("\n","") + " exists in ");
                //counter++;
                }
        }
      readedchapter.clear();
   }
 }
  catch(IOException e){
	System.out.println("IO-Fehler!");
  }
  modelBook.write(System.out,"N-TRIPLE");
  modelChapter.write(System.out,"N-TRIPLE");
  System.out.println(counter);
}
}