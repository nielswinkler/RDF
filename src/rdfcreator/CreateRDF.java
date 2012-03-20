/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rdfcreator;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.vocabulary.OWL;
import com.hp.hpl.jena.vocabulary.RDF;
import com.hp.hpl.jena.vocabulary.RDFS;

/**
 *
 * @author Niels
 */
public class CreateRDF {

    static Model model = ModelFactory.createDefaultModel();

    public static Resource createBookRDF(Resource mainresource, String chapterfilepath) {
        Property dublinhaspart = model.createProperty(RDFResources.PROPERTY_NAMESPACE_DUBLINCORE_HASPART);
        
        Resource resourcebook = mainresource;
        resourcebook.addProperty(RDF.type, OWL.Thing);
        resourcebook.addProperty(dublinhaspart, model.createResource(chapterfilepath));
        resourcebook.addProperty(RDF.type,model.createResource(RDFResources.DOCUMENT_CLASS_RESOURCE));
        return resourcebook;
    }

    public static Resource createBishopRoger(Resource mainresource1) {
        Property hascontent = model.createProperty(RDFResources.PROPERTY_NAMESPACE_PML_HASCONTENT);

        Resource resourcebish = mainresource1;
        resourcebish.addProperty(hascontent, RDFResources.ROGER_RESS_DBPEDIA);

        return resourcebish;
    }

    public static Resource createCasati(Resource mainresource2) {
        Property hascontent = model.createProperty(RDFResources.PROPERTY_NAMESPACE_PML_HASCONTENT);

        Resource resourcecasati = mainresource2;
        resourcecasati.addProperty(hascontent, RDFResources.CASATI_RESS_DBPEDIA);

        return resourcecasati;
    }

    public static Resource createTownDBPedia(Resource mainresource3, String ressStringtowndbpedia) {
        //creating the Propertys pml:hasContent
        Property hascontent = model.createProperty(RDFResources.PROPERTY_NAMESPACE_PML_HASCONTENT);

        Resource resourcetown = mainresource3;
        //adding the Propertys and Objects to the Resource
        resourcetown.addProperty(hascontent, model.createResource(ressStringtowndbpedia));

        return resourcetown;
    }

    public static Resource createContent(Resource mainresource4, String Inhalt, String name, String bookfilepath) {
        //creating the Propertys bibo:content and dcterms:isPartOf
        Property bibocontent = model.createProperty(RDFResources.PROPERTY_NAMESPACE_BIBO_HASCONTENT);
        Property dcisPartof = model.createProperty(RDFResources.PROPERTY_NAMESPACE_DUBLINCORE_ISPARTOF);

        Resource resourcecontent = mainresource4;
        //adding the Propertys and Objects to the Resource
        resourcecontent.addProperty(bibocontent, Inhalt);
        resourcecontent.addProperty(RDFS.label, name);
        resourcecontent.addProperty(RDF.type, OWL.Thing);
        resourcecontent.addProperty(dcisPartof, model.createResource(bookfilepath));
        resourcecontent.addProperty(RDF.type,model.createResource(RDFResources.DOCUMENTPART_CLASS_RESOURCE));

        return resourcecontent;
    }

    public static Resource createGeonames(Resource mainresource5, String geonamesname, String location_map, String wgs_long, String wgs_lat) {
        String locationmapresource = "http://www.geonames.org/" + location_map + "/";
        Property geoname_name = model.createProperty(RDFResources.PROPERTY_NAMESPACE_GEONAMES_NAME);
        Property geonames_location_map = model.createProperty(RDFResources.PROPERTY_NAMESPACE_GEONAMES_LOCATION_MAP);
        Property wgs_85_pos_long = model.createProperty(RDFResources.PROPERTY_NAMESPACE_WGS84_LONG);
        Property wgs_85_pos_lat = model.createProperty(RDFResources.PROPERTY_NAMESPACE_WGS84_LAT);

        Resource resourcegeonames = mainresource5;
        resourcegeonames.addProperty(geoname_name, geonamesname);
        resourcegeonames.addProperty(geonames_location_map, model.createResource(locationmapresource));
        resourcegeonames.addProperty(wgs_85_pos_long, wgs_long);
        resourcegeonames.addProperty(wgs_85_pos_lat, wgs_lat);

        return resourcegeonames;
    }

    public static Resource createKingsDBPedia(Resource mainresource6, String ressString) {
        Property hascontent = model.createProperty(RDFResources.PROPERTY_NAMESPACE_PML_HASCONTENT);

        Resource resourcekings = mainresource6;
        resourcekings.addProperty(hascontent, model.createResource(ressString));

        return resourcekings;
    }

    public static Resource createPeopleDBPedia(Resource mainresource7, String ressStringtowndbpedia) {
        Property hascontent = model.createProperty(RDFResources.PROPERTY_NAMESPACE_PML_HASCONTENT);

        Resource resourcepeople = mainresource7;
        resourcepeople.addProperty(hascontent, model.createResource(ressStringtowndbpedia));

        return resourcepeople;
    }

    public static Resource createRiver(Resource mainresource8, String river) {
        String riverresource = "http://dbpedia.org/resource/River_" + river;
        Property hascontent = model.createProperty(RDFResources.PROPERTY_NAMESPACE_PML_HASCONTENT);

        Resource resourceriver = mainresource8;
        resourceriver.addProperty(hascontent, model.createResource(riverresource));

        return resourceriver;
    }
    
    public static Resource createYork(Resource mainresource9) {
        String yorkresource = "http://dbpedia.org/resource/York";
        Property hascontent = model.createProperty(RDFResources.PROPERTY_NAMESPACE_PML_HASCONTENT);

        Resource resourceriver = mainresource9;
        resourceriver.addProperty(hascontent, model.createResource(yorkresource));

        return resourceriver;
    }
    
    public static Resource createNottingham(Resource mainresource10) {
        String nottinghamresource = "http://dbpedia.org/resource/Nottingham";
        Property hascontent = model.createProperty(RDFResources.PROPERTY_NAMESPACE_PML_HASCONTENT);

        Resource resourceriver = mainresource10;
        resourceriver.addProperty(hascontent, model.createResource(nottinghamresource));

        return resourceriver;
    }
}