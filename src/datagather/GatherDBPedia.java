package datagather;

import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.query.ResultSetFormatter;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class GatherDBPedia {

    public ArrayList<String> getResultTowns(String ontology_service, String endpoint, String queryString) {
        //stating the query
        QueryExecution queryEx = QueryExecutionFactory.sparqlService(
                ontology_service, String.format(queryString, endpoint));
        //save the Queryresult as ResultSet
        ResultSet results = queryEx.execSelect();
        //save the ResultsSet result as String
        String resultsXML = ResultSetFormatter.asXMLString(results);

        
        ArrayList<String> resultListtown = new ArrayList<>();

        DocumentBuilder db;
        try {
            //set a new DocumentBuilderFactory
            db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            //set an Input Source
            InputSource is = new InputSource();
            //Creating a new CharacterStream
            is.setCharacterStream(new StringReader(resultsXML));
            org.w3c.dom.Document doc = db.parse(is);
            doc.getDocumentElement().normalize();
            //set the Tag of the 
            NodeList resultsNodes = doc.getElementsByTagName("result");
            NodeList resultNodesChilds;

            for (int i = 0; i < resultsNodes.getLength(); i++) {
                resultNodesChilds = resultsNodes.item(i).getChildNodes();
                // saves the ResultNodes 1 and 3 as String
                String townlabel = resultNodesChilds.item(1).getTextContent().toString();
                String townresource = resultNodesChilds.item(3).getTextContent().toString();
                //splits the String a "," and replaces all \n with " "
                townlabel = townlabel.split(",")[0].replace("\n", " ");
                townresource = townresource.replace("\n", " ").replace(" ", "");
                if (!townlabel.equals("")) {
                    resultListtown.add(townresource + "&" + townlabel);
                }
            }
        } catch (ParserConfigurationException | SAXException | IOException e) {
            throw new RuntimeException(e);
        }
        return resultListtown;
    }

    public ArrayList<String> getResultPeople(String ontology_service, String endpoint, String queryString) {

        QueryExecution queryEx = QueryExecutionFactory.sparqlService(
                ontology_service, String.format(queryString, endpoint));

        ResultSet results = queryEx.execSelect();
        String resultsXML = ResultSetFormatter.asXMLString(results);

        DocumentBuilder db;
        ArrayList<String> resultListpeople = new ArrayList<>();

        try {
            db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            InputSource is = new InputSource();
            is.setCharacterStream(new StringReader(resultsXML));
            org.w3c.dom.Document doc = db.parse(is);
            doc.getDocumentElement().normalize();
            NodeList resultsNodes = doc.getElementsByTagName("result");
            NodeList resultNodesChilds;

            for (int i = 0; i < resultsNodes.getLength(); i++) {
                resultNodesChilds = resultsNodes.item(i).getChildNodes();

                String resspeople = resultNodesChilds.item(3).getTextContent().toString();
                String namepeople = resultNodesChilds.item(1).getTextContent().toString();
                resspeople.replace("\n", "");
                namepeople.replace("\n", "");
                if (!resspeople.equals("")) {
                    //addes the Strings resspeople and namepeople into a String
                    //and replaces all \n with ""
                    resultListpeople.add((resspeople + "&" + namepeople).replace("\n", ""));
                }
            }
        } catch (ParserConfigurationException | SAXException | IOException e) {
        }

        return resultListpeople;
    }

    public ArrayList<String> getResultKings(String ontology_service, String endpoint, String queryString) {

        QueryExecution queryEx = QueryExecutionFactory.sparqlService(
                ontology_service, String.format(queryString, endpoint));

        ResultSet results = queryEx.execSelect();
        String resultsXML = ResultSetFormatter.asXMLString(results);

        ArrayList<String> resultListkings = new ArrayList<>();
        DocumentBuilder db;

        try {
            db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            InputSource is = new InputSource();
            is.setCharacterStream(new StringReader(resultsXML));
            org.w3c.dom.Document doc = db.parse(is);
            doc.getDocumentElement().normalize();
            NodeList resultsNodes = doc.getElementsByTagName("result");
            NodeList resultNodesChilds;

            for (int i = 0; i < resultsNodes.getLength(); i++) {
                resultNodesChilds = resultsNodes.item(i).getChildNodes();
                String nameskings = resultNodesChilds.item(1).getTextContent().toString();
                String resskings = resultNodesChilds.item(3).getTextContent().toString();
                nameskings.replace("\n", "");
                resskings.replace("\n", "");
                if (!nameskings.equals("")) {
                    resultListkings.add((nameskings + "&" + resskings).replace("\n", ""));
                }
            }
        } catch (ParserConfigurationException | SAXException | IOException e) {
            throw new RuntimeException(e);
        }
        return resultListkings;
    }
}