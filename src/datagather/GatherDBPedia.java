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

    public ArrayList<String> getresulttowns(String ontology_service, String endpoint, String queryString) {


        QueryExecution queryEx = QueryExecutionFactory.sparqlService(
                ontology_service, String.format(queryString, endpoint));

        ResultSet results = queryEx.execSelect();
        String resultsXML = ResultSetFormatter.asXMLString(results);

        //System.out.println(resultsXML);

        DocumentBuilder db;

        ArrayList<String> resultListtown = new ArrayList<>();

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

                String link = resultNodesChilds.item(1).getTextContent().toString();
                String property = resultNodesChilds.item(3).getTextContent().toString();
                //String name = resultNodesChilds.item(5).getTextContent().toString();
                link = link.split(",")[0];
                link.replace("\n", " ");
                property = property.replace("\n", " ");
                property = property.replace(" ", "");
                //System.out.println(link);
                if (!link.equals("")) {
                    //System.out.println(link);
                    resultListtown.add(property + "&" + link);
                }
            }
            //System.out.print(resultList.toString());
            //test = resultListlink.toString().replace(",","");
            //System.out.println(test);

        } catch (ParserConfigurationException | SAXException | IOException e) {
            throw new RuntimeException(e);
        }


        return resultListtown;
    }

    public ArrayList<String> getresultpeople(String ontology_service, String endpoint, String queryString) {


        QueryExecution queryEx = QueryExecutionFactory.sparqlService(
                ontology_service, String.format(queryString, endpoint));

        ResultSet results = queryEx.execSelect();
        String resultsXML = ResultSetFormatter.asXMLString(results);

        //System.out.println(resultsXML);

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

                String resspeople = resultNodesChilds.item(5).getTextContent().toString();
                String namepeople = resultNodesChilds.item(1).getTextContent().toString();
                //String name = resultNodesChilds.item(5).getTextContent().toString();
                //link = link.split(",")[0];
                //link = link.split("(")[0];
                resspeople.replace("\n", "");
                namepeople.replace("\n", "");
                //System.out.println(link);
                if (!resspeople.equals("")) {
                    //System.out.println(link);
                    resultListpeople.add((resspeople + "&" + namepeople).replace("\n", ""));
                }
            }
            //System.out.print(resultList.toString());
            //test = resultListlink.toString().replace(",","");
            //System.out.println(test);

        } catch (ParserConfigurationException | SAXException | IOException e) {
        }

        return resultListpeople;
    }

    public ArrayList<String> getresultkings(String ontology_service, String endpoint, String queryString) {


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

                String nameskings = resultNodesChilds.item(3).getTextContent().toString();
                String resskings = resultNodesChilds.item(1).getTextContent().toString();
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