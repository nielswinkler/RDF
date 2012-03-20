/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package datagather;

/**
 *
 * @author Niels
 */
public class GatherQuerys {

    public static final String QUERY_TOWN_DBPEDIA = "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> \n"
            + "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> \n"
            + "SELECT * WHERE { \n"
            + "     { ?episode <http://www.w3.org/1999/02/22-rdf-syntax-ns#type>\n"
            + "	<http://dbpedia.org/class/yago/TownsInSouthYorkshire> .}\n"
            + "UNION{	?episode <http://www.w3.org/1999/02/22-rdf-syntax-ns#type>\n"
            + "	<http://dbpedia.org/class/yago/TownsInNorthYorkshire> .}\n"
            + "UNION{ ?episode <http://www.w3.org/1999/02/22-rdf-syntax-ns#type>\n"
            + "	<http://dbpedia.org/class/yago/TownsInWestYorkshire> .}\n"
            + "UNION{ ?episode <http://www.w3.org/1999/02/22-rdf-syntax-ns#type>\n"
            + "	<http://dbpedia.org/class/yago/TownsInNottinghamshire> .}\n"
            + "UNION{ ?episode <http://dbpedia.org/ontology/ceremonialCounty> \n"
            + "	<http://dbpedia.org/resource/East_Riding_of_Yorkshire> .}\n"
            + "	?episode rdfs:label ?label .\n"
            + "FILTER langMatches( lang(?label), 'en')}\n"
            + "ORDER BY (?label)\n";
    public static final String QUERY_PEOPLE_DBPEDIA = "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> \n"
            + "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> \n"
            + "SELECT ?peopleresource ?peoplelabel WHERE {\n"
            + "{ ?peopleresource ?property <http://dbpedia.org/resource/Category:14th-century_English_people> } \n"
            + "UNION \n"
            + "{ ?peopleresource ?property <http://dbpedia.org/resource/Category:13th-century_English_people> } \n"
            + "UNION \n"
            + "{ ?peopleresource ?property <http://dbpedia.org/resource/Category:12th-century_English_people> } \n"
            + "UNION \n"
            + "{ ?peopleresource ?property <http://dbpedia.org/resource/Category:11th-century_English_people> } \n"
            + "UNION \n"
            + "{ ?peopleresource ?property <http://dbpedia.org/resource/Category:10th-century_English_people> } \n"
            + "?peopleresource rdfs:label ?peoplelabel \n"
            + "FILTER langMatches( lang(?peoplelabel), 'en')} \n"
            + "ORDER BY (?peoplelabel) \n";
    //query for List of english monarchs output(name , resource)
    public static final String QUERY_KINGS_DBPEDIA = 
            "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> \n"
            + "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> \n"
            + "SELECT ?label ?kingress WHERE {{ ?kingress <http://dbpedia.org/property/title> \n"
            + "<http://dbpedia.org/resource/List_of_English_monarchs> .\n"
            + "?kingress rdfs:label ?label }\n"
            + "FILTER langMatches( lang(?label), 'en')}\n"
            + "ORDER BY (?label)";
    public static final String QUERY_ONTOLOGY_SERVICE_DBPEDIA = "http://dbpedia.org/sparql/";
    public static final String QUERY_ENDPOINT_DBPEDIA = "otee:Endpoints";
}
