/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package datagather;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Resource;
import java.io.*;
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
    public static void main(String[] args) throws FileNotFoundException, IOException {

        Model modelChapter = ModelFactory.createDefaultModel();
        Model modelBook = ModelFactory.createDefaultModel();
        
        GatherDBPedia GatherDBPediaObj = new GatherDBPedia();
        dataListDBPedia_Towns = GatherDBPediaObj.getresulttowns(GatherQuerys.QUERY_ONTOLOGY_SERVICE_DBPEDIA,
                GatherQuerys.QUERY_ENDPOINT_DBPEDIA, GatherQuerys.QUERY_TOWN_DBPEDIA);

        GatherGeoNames GatherGeoNamesObj = new GatherGeoNames();

        dataListDBPedia_Towns = GatherGeoNamesObj.removeMultipleEntries(dataListDBPedia_Towns);

        //System.out.println(dataListDBPedia_Towns.toString());


        dataListDBPedia_People = GatherDBPediaObj.getresultpeople(GatherQuerys.QUERY_ONTOLOGY_SERVICE_DBPEDIA,
                GatherQuerys.QUERY_ENDPOINT_DBPEDIA, GatherQuerys.QUERY_PEOPLE_DBPEDIA);
        dataListDBPedia_People = GatherGeoNamesObj.removeMultipleEntries(dataListDBPedia_People);

        //System.out.println(dataListDBPedia_People.toString());

        dataListDBPedia_Kings = GatherDBPediaObj.getresultkings(GatherQuerys.QUERY_ONTOLOGY_SERVICE_DBPEDIA,
                GatherQuerys.QUERY_ENDPOINT_DBPEDIA, GatherQuerys.QUERY_KINGS_DBPEDIA);
        dataListDBPedia_Kings = GatherGeoNamesObj.removeMultipleEntries(dataListDBPedia_Kings);

        //System.out.println(dataListDBPedia_Kings.toString());
        dataListGeoNames = GatherGeoNamesObj.getResultGeoNames();
        dataListGeoNames = GatherGeoNamesObj.removeMultipleEntries(dataListGeoNames);
        //System.out.println(dataListGeoNames.toString());

        GatherRiverList GatherRiverListObj = new GatherRiverList();
        dataListRivers = GatherRiverListObj.getRiverList();
        dataListRivers = GatherGeoNamesObj.removeMultipleEntries(dataListRivers);

        try {

            for (int b = 1; b < 601; b++) {
                FileReader fread = new FileReader("York_Daten_Chapter" + b + ".txt");
                BufferedReader in = new BufferedReader(fread);

                for (int a = 0; (line = in.readLine()) != null; a++) {
                    readedchapter.add(line);
                }

                String br = readedchapter.toString();
                br = br.replaceAll(",", "");

                String chapterfilepath = "https://raw.github.com/nielswinkler/RDF/master/York_Daten_Chapter" + b + ".txt";
                String bookfilepath = "https://raw.github.com/nielswinkler/RDF/master/York_Daten.txt";

                Book = modelBook.createResource(bookfilepath);
                Chapter = modelChapter.createResource(chapterfilepath);

                Book = rdfcreator.CreateRDF.createBookRDF(Book, chapterfilepath);
                Chapter = rdfcreator.CreateRDF.createContent(Chapter, br, "York_Daten_Chapter" + b, bookfilepath);

                if (br.matches("(.*)Roger(.*)") & (br.matches("(.*)archbishop of York(.*)") || br.matches("(.*)archbishop(.*)"))) {
                    Chapter = rdfcreator.CreateRDF.createBishopRoger(Chapter);
                }
                if (br.matches("(.*)casati(.*)")) {
                    Chapter = rdfcreator.CreateRDF.createCasati(Chapter);
                }
                if (br.matches("(.*)York(.*)")) {
                    Chapter = rdfcreator.CreateRDF.createYork(Chapter);
                }
                if (br.matches("(.*)Nottingham(.*)")) {
                    Chapter = rdfcreator.CreateRDF.createNottingham(Chapter);
                }
                for (int i = 0; i < dataListDBPedia_Towns.size(); i++) {
                    String towndbpedia = dataListDBPedia_Towns.get(i).toString();
                    String rdftowndbpedia = dataListDBPedia_Towns.get(i).toString();
                    towndbpedia = towndbpedia.split("&")[0].replace(" ", "").replace("\n", "");
                    if (br.matches("(.*)" + towndbpedia + "(.*)")) {
                        Chapter = rdfcreator.CreateRDF.createTownDBPedia(Chapter, rdftowndbpedia.split("&")[1].replace(" ", "").replace("\n", ""));
                    }
                }
                for (int i = 0; i < dataListDBPedia_Kings.size(); i++) {
                    String kingnames = dataListDBPedia_Kings.get(i).toString();
                    String rdfkingnames = dataListDBPedia_Kings.get(i).toString();
                    kingnames = kingnames.split("of")[0].split(",")[0].replace("\n", "");
                    if (br.replace(" ", "").matches("(.*)" + kingnames.replace(" ", "") + "(.*)") & !(kingnames.replace(" ", "").equals("John") | kingnames.replace(" ", "").equals("Stephen"))) {
                        Chapter = rdfcreator.CreateRDF.createKingsDBPedia(Chapter, rdfkingnames.split("&")[1].replace(" ", ""));
                    }
                }
                for (int i = 0; i < dataListGeoNames.size(); i++) {
                    String towngeonames = dataListGeoNames.get(i).toString();
                    String rdftowngeonames = dataListGeoNames.get(i).toString();
                    towngeonames = towngeonames.split("&")[0].replace(" ", "").replace("\n", "");
                    if (br.matches("(.*)" + towngeonames + "(.*)")) {
                        Chapter = rdfcreator.CreateRDF.createGeonames(Chapter, rdftowngeonames.split("&")[0].replace(" ", ""), rdftowngeonames.split("&")[1].replace(" ", ""),
                                rdftowngeonames.split("&")[2].replace(" ", ""), rdftowngeonames.split("&")[3].replace(" ", ""));
                    }
                }
                for (int c = 0; c < dataListRivers.size(); c++) {
                    String river = dataListRivers.get(c);
                    if (br.matches("(.*)" + river + "(.*)") & ((br.matches("(.*)river(.*)")) | (br.matches("(.*)sea(.*)")))) {
                    }
                }
                for (int i = 0; i < dataListDBPedia_People.size(); i++) {
                    String people = dataListDBPedia_People.get(i).toString();
                    String rdfpeopledbpedia = dataListDBPedia_People.get(i).toString();
                    people = people.split("&")[0].split(",")[0].replace("Æ", "A").replace("(", "!");
                    people = people.split("!")[0].replace("\n", "");
                    if (br.replace(" ", "").matches("(.*)" + people.replace(" ", "") + "(.*)") & !(people.replace(" ", "").equals("John") 
                            | people.replace(" ", "").equals("Richard") | people.replace(" ", "").equals("Margaret"))) {
                        Chapter = rdfcreator.CreateRDF.createPeopleDBPedia(Chapter, rdfpeopledbpedia.split("&")[1].replace(" ", ""));
                    }
                }
                readedchapter.clear();
            }
        } catch (IOException e) {
            System.out.println("IO-Fehler!");
        }
        BufferedWriter out = new BufferedWriter(
                new OutputStreamWriter(
                new FileOutputStream("Yorl_Daten_Trippel.txt")));
        
        modelBook.write(System.out, "N-TRIPLE");
        modelChapter.write(System.out, "N-TRIPLE");
        modelBook.write(out,"N-TRIPLE");
        modelChapter.write(out,"N-TRIPLE");
        out.close();
    }
}