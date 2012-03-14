/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package createRDF;


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
    static String ROGER_RESS_DBPEDIA = "<http://www.dbpedia.org/resource/Roger_de_Pont_L'Évêque>";
    static String CASATI_RESS_DBPEDIA = "<http://en.wikipedia.org/wiki/Cotter_(farmer)>";
    static String RIVER_RESS_DBPEDIA = "<http://dbpedia.org/page/River_";
    
    
    //static Property dublinhaspart;
    static Property geonames_location_map;
    static Property geoname_name;
    static Property wgs_85_pos_long;
    static Property wgs_85_pos_lat;
    static Property bibocontent;
    static Property bibochapter;
    static Property hascontent;
    static Property dcisPartof;
    
    
    static Resource resourcebish,resourcecasati,resourcetown,resourcepeople,resourcecontent;
    static Resource resourcegeonames,resourcekings,resourceriver,resourcebook;
    
 public static Resource createbookrdf(Resource mainresource,String chapterfilepath){
     Property dublinhaspart = model.createProperty("http://dublincore.org/2010/10/11/dcterms.rdf#hasPart");
     
     resourcebook = mainresource;
     resourcebook.addProperty(RDF.type,OWL.Thing);
     resourcebook.addProperty(dublinhaspart, model.createResource(chapterfilepath));
     return resourcebook;
 }   
 public static Resource createarchbishop(Resource mainresource1){
     hascontent = model.createProperty( "http://inference-web.org/2.0/pml-provenance.owl#hasContent" );
     
     resourcebish = mainresource1;
     resourcebish.addProperty(hascontent,ROGER_RESS_DBPEDIA);
     
     return resourcebish;
 }
 public static Resource createcasati(Resource mainresource2){
     hascontent = model.createProperty( "http://inference-web.org/2.0/pml-provenance.owl#hasContent" );
     
     resourcecasati = mainresource2;
     resourcecasati.addProperty(hascontent ,CASATI_RESS_DBPEDIA );
     
     return resourcecasati;
 }
 public static Resource createtowndbpedia(Resource mainresource3,String ressStringtowndbpedia){
     hascontent = model.createProperty( "http://inference-web.org/2.0/pml-provenance.owl#hasContent" );
     
     resourcetown = mainresource3;
     resourcetown.addProperty(hascontent,model.createResource(ressStringtowndbpedia));
     
     return resourcetown;
 }

 public static Resource createcontent(Resource mainresource4, String Inhalt , String name,String bookfilepath){
     bibocontent = model.createProperty( "http://purl.org/ontology/bibo/content" );
     dcisPartof = model.createProperty("http://dublincore.org/2010/10/11/dcterms.rdf#isPartOf");
     
     resourcecontent = mainresource4;
     resourcecontent.addProperty(bibocontent,Inhalt);
     resourcecontent.addProperty(RDFS.label,name);
     resourcecontent.addProperty(RDF.type,OWL.Thing);
     resourcecontent.addProperty(dcisPartof,model.createResource(bookfilepath));
     
     
     return resourcecontent;
 }
 public static Resource creategeonames(Resource mainresource5, String geonamesname , String location_map,String wgs_long ,String wgs_lat){
     String save = "http://www.geonames.org/"+location_map+"/";
     geoname_name = model.createProperty( "http://www.geonames.org/ontology#name" );
     geonames_location_map = model.createProperty("http://www.geonames.org/ontology#locationMap");
     wgs_85_pos_long = model.createProperty( "http://www.w3.org/2003/01/geo/wgs84_pos#long" );
     wgs_85_pos_lat = model.createProperty("http://www.w3.org/2003/01/geo/wgs84_pos#lat");
     
     resourcegeonames = mainresource5;
     resourcegeonames.addProperty(geoname_name,geonamesname);
     resourcegeonames.addProperty(geonames_location_map,model.createResource(save));
     resourcegeonames.addProperty(wgs_85_pos_long,wgs_long);
     resourcegeonames.addProperty(wgs_85_pos_lat,wgs_lat);
     
     return resourcegeonames;
 }
  public static Resource createkingsdbpedia(Resource mainresource6,String ressString){
     hascontent = model.createProperty( "http://inference-web.org/2.0/pml-provenance.owl#hasContent" );

     resourcekings = mainresource6;
     resourcekings.addProperty(hascontent,model.createResource(ressString));
     
     return resourcekings;
 }
   public static Resource createpeopledbpedia(Resource mainresource7,String ressStringtowndbpedia){
     hascontent = model.createProperty( "http://inference-web.org/2.0/pml-provenance.owl#hasContent" );
     
     resourcepeople = mainresource7;
     resourcepeople.addProperty(hascontent,model.createResource(ressStringtowndbpedia));
     
     return resourcepeople;
 }
   public static Resource createriver(Resource mainresource8,String river){
     String riversave = "http://dbpedia.org/resource/River_"+river;
     hascontent = model.createProperty( "http://inference-web.org/2.0/pml-provenance.owl#hasContent" );
     
     resourceriver = mainresource8;
     resourceriver.addProperty(hascontent,model.createResource(riversave));
     
     return resourceriver;
 }

}