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

    public static final String QUERY_TOWN_DBPEDIA = "PREFIX owl: <http://www.w3.org/2002/07/owl#> \n"
            + "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#> \n"
            + "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> \n"
            + "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> \n"
            + "PREFIX foaf: <http://xmlns.com/foaf/0.1/> \n"
            + "PREFIX dc: <http://purl.org/dc/elements/1.1/> \n"
            + "PREFIX : <http://dbpedia.org/resource/> \n"
            + "PREFIX dbpedia2: <http://dbpedia.org/property/> \n"
            + "PREFIX dbpedia: <http://dbpedia.org/> \n"
            + "PREFIX skos: <http://www.w3.org/2004/02/skos/core#> \n"
            + "SELECT * WHERE { \n"
            + "     { ?episode <http://www.w3.org/1999/02/22-rdf-syntax-ns#type>\n"
            + "	<http://dbpedia.org/class/yago/TownsInSouthYorkshire> .}\n"
            + "UNION{	?episode <http://www.w3.org/1999/02/22-rdf-syntax-ns#type>\n"
            + "	<http://dbpedia.org/class/yago/TownsInNorthYorkshire> .}\n"
            + "UNION{ ?episode <http://www.w3.org/1999/02/22-rdf-syntax-ns#type>\n"
            + "	<http://dbpedia.org/class/yago/TownsInWestYorkshire> .}\n"
            + "UNION{ ?episode <http://www.w3.org/1999/02/22-rdf-syntax-ns#type>\n"
            + "	<http://dbpedia.org/class/yago/TownsInNottinghamshire> .}\n"
            + "UNION{ ?episode <http://www.w3.org/1999/02/22-rdf-syntax-ns#type>\n"
            + "	<http://dbpedia.org/class/yago/TownsInAberdeenshire> .}\n"
            + "UNION{ ?episode <http://dbpedia.org/ontology/ceremonialCounty> \n"
            + "	<http://dbpedia.org/resource/East_Riding_of_Yorkshire> .}\n"
            + "UNION{ ?episode <http://dbpedia.org/ontology/ceremonialCounty> \n"
            + "	<http://http://dbpedia.org/page/Lincolnshire> .}\n"
            + "	?episode rdfs:label ?label .\n"
            + "FILTER langMatches( lang(?label), 'en')}\n"
            + "ORDER BY (?label)\n";
    public static final String QUERY_PEOPLE_DBPEDIA = "PREFIX owl: <http://www.w3.org/2002/07/owl#> \n"
            + "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#> \n"
            + "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> \n"
            + "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> \n"
            + "PREFIX foaf: <http://xmlns.com/foaf/0.1/> \n"
            + "PREFIX dc: <http://purl.org/dc/elements/1.1/> \n"
            + "PREFIX : <http://dbpedia.org/resource/> \n"
            + "PREFIX dbpedia2: <http://dbpedia.org/property/> \n"
            + "PREFIX dbpedia: <http://dbpedia.org/> \n"
            + "PREFIX skos: <http://www.w3.org/2004/02/skos/core#> \n"
            + "SELECT * WHERE {\n"
            + "{ ?isValueOf ?property <http://dbpedia.org/resource/Category:14th-century_English_people> } \n"
            + "UNION \n"
            + "{ ?isValueOf ?property <http://dbpedia.org/resource/Category:13th-century_English_people> } \n"
            + "UNION \n"
            + "{ ?isValueOf ?property <http://dbpedia.org/resource/Category:12th-century_English_people> } \n"
            + "UNION \n"
            + "{ ?isValueOf ?property <http://dbpedia.org/resource/Category:11th-century_English_people> } \n"
            + "UNION \n"
            + "{ ?isValueOf ?property <http://dbpedia.org/resource/Category:10th-century_English_people> } \n"
            + "?isValueOf rdfs:label ?label \n"
            + "FILTER langMatches( lang(?label), 'en')} \n"
            + "ORDER BY (?label) \n";
    public static final String QUERY_KINGS_DBPEDIA = "PREFIX owl: <http://www.w3.org/2002/07/owl#> \n"
            + "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#> \n"
            + "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> \n"
            + "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> \n"
            + "PREFIX foaf: <http://xmlns.com/foaf/0.1/> \n"
            + "PREFIX dc: <http://purl.org/dc/elements/1.1/> \n"
            + "PREFIX : <http://dbpedia.org/resource/> \n"
            + "PREFIX dbpedia2: <http://dbpedia.org/property/> \n"
            + "PREFIX dbpedia: <http://dbpedia.org/> \n"
            + "PREFIX skos: <http://www.w3.org/2004/02/skos/core#> \n"
            + "SELECT * WHERE {{ ?name <http://dbpedia.org/property/title> \n"
            + "<http://dbpedia.org/resource/List_of_English_monarchs> .\n"
            + "?name rdfs:label ?label }\n"
            + "FILTER langMatches( lang(?label), 'en')}\n"
            + "ORDER BY (?label)";
    public static final String QUERY_ONTOLOGY_SERVICE_DBPEDIA = "http://dbpedia.org/sparql/";
    public static final String QUERY_ENDPOINT_DBPEDIA = "otee:Endpoints";
}
