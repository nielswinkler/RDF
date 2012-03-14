/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package datagather;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import org.geonames.*;
/**
 *
 * @author Niels
 */


public class GatherGeoNames {

static ArrayList<String> geonameslist = new ArrayList<>();
static  ArrayList<String> filelist = new ArrayList<>();
static  ArrayList<String> riverlist = new ArrayList<>();

int startRow;
String q;
String countryCode;
String ContinentCode;
String classes;
int maxrow;
         
         
public static <T> ArrayList<T> ohneDoppelte(Collection<T> list)
{
	return new ArrayList<>(new HashSet<>(list));
}
/*
 * Style set SHORT
 * FeatureClass set P
 */
public static void setSearchCriteria(ToponymSearchCriteria searchCriteria,String q, int startRow,String countryCode, String ContinentCode,int maxrow ) throws InvalidParameterException{
  searchCriteria.setQ(q);  
  searchCriteria.setStartRow(startRow);
  searchCriteria.setCountryCode(countryCode);
  searchCriteria.setContinentCode(ContinentCode);
  searchCriteria.setMaxRows(maxrow);
  searchCriteria.setStyle(Style.SHORT);
  searchCriteria.setFeatureClass(FeatureClass.P);
}

public static ArrayList<String> getresultgeonames() throws Exception{
    ToponymSearchCriteria searchCriteria = new ToponymSearchCriteria();
    WebService.setUserName("nielswinkler");
    
    
    setSearchCriteria(searchCriteria, "Yorkshire", 0 ,"GB", "EU", 800); 

    ToponymSearchResult searchResult = WebService.search(searchCriteria);
    for (Toponym toponym : searchResult.getToponyms()) {
        geonameslist.add(toponym.getName()+"&"+toponym.getGeoNameId()+"&"+toponym.getLongitude()+"&"+toponym.getLatitude());
    }
    setSearchCriteria(searchCriteria, "Lincolnshire", 0 ,"GB", "EU", 800);
 
    searchResult = WebService.search(searchCriteria);
    for (Toponym toponym : searchResult.getToponyms()) {
        geonameslist.add(toponym.getName()+"&"+toponym.getGeoNameId()+"&"+toponym.getLongitude()+"&"+toponym.getLatitude());
    }
    setSearchCriteria(searchCriteria, "Nottinghamshire", 0 ,"GB", "EU", 800);
 
    searchResult = WebService.search(searchCriteria);
    for (Toponym toponym : searchResult.getToponyms()) {
        geonameslist.add(toponym.getName()+"&"+toponym.getGeoNameId()+"&"+toponym.getLongitude()+"&"+toponym.getLatitude());
    }
    setSearchCriteria(searchCriteria, "Leeds", 0 ,"GB", "EU", 800);
 
    searchResult = WebService.search(searchCriteria);
    for (Toponym toponym : searchResult.getToponyms()) {
        geonameslist.add(toponym.getName()+"&"+toponym.getGeoNameId()+"&"+toponym.getLongitude()+"&"+toponym.getLatitude());
    }
    setSearchCriteria(searchCriteria, "Bradford", 0 ,"GB", "EU", 800);
 
    searchResult = WebService.search(searchCriteria);
    for (Toponym toponym : searchResult.getToponyms()) {
        geonameslist.add(toponym.getName()+"&"+toponym.getGeoNameId()+"&"+toponym.getLongitude()+"&"+toponym.getLatitude());
    }
    setSearchCriteria(searchCriteria, "Calderdale", 0 ,"GB", "EU", 800);
 
    searchResult = WebService.search(searchCriteria);
    for (Toponym toponym : searchResult.getToponyms()) {
        geonameslist.add(toponym.getName()+"&"+toponym.getGeoNameId()+"&"+toponym.getLongitude()+"&"+toponym.getLatitude());
    }
    setSearchCriteria(searchCriteria, "Kirklees", 0 ,"GB", "EU", 800);
 
    searchResult = WebService.search(searchCriteria);
    for (Toponym toponym : searchResult.getToponyms()) {
        geonameslist.add(toponym.getName()+"&"+toponym.getGeoNameId()+"&"+toponym.getLongitude()+"&"+toponym.getLatitude());
    }
    setSearchCriteria(searchCriteria, "Wakefield", 0 ,"GB", "EU", 800);
 
    searchResult = WebService.search(searchCriteria);
    for (Toponym toponym : searchResult.getToponyms()) {
        geonameslist.add(toponym.getName()+"&"+toponym.getGeoNameId()+"&"+toponym.getLongitude()+"&"+toponym.getLatitude());
    }
    setSearchCriteria(searchCriteria, "Barnsley", 0 ,"GB", "EU", 800);
 
    searchResult = WebService.search(searchCriteria);
    for (Toponym toponym : searchResult.getToponyms()) {
        geonameslist.add(toponym.getName()+"&"+toponym.getGeoNameId()+"&"+toponym.getLongitude()+"&"+toponym.getLatitude());
    }
    setSearchCriteria(searchCriteria, "Sheffield", 0 ,"GB", "EU", 800);
 
    searchResult = WebService.search(searchCriteria);
    for (Toponym toponym : searchResult.getToponyms()) {
        geonameslist.add(toponym.getName()+"&"+toponym.getGeoNameId()+"&"+toponym.getLongitude()+"&"+toponym.getLatitude());
    }
    setSearchCriteria(searchCriteria, "Doncaster", 0 ,"GB", "EU", 800);
 
    searchResult = WebService.search(searchCriteria);
    for (Toponym toponym : searchResult.getToponyms()) {
        geonameslist.add(toponym.getName()+"&"+toponym.getGeoNameId()+"&"+toponym.getLongitude()+"&"+toponym.getLatitude());
    }
    setSearchCriteria(searchCriteria, "Rotherham", 0 ,"GB", "EU", 800);
 
    searchResult = WebService.search(searchCriteria);
    for (Toponym toponym : searchResult.getToponyms()) {
        geonameslist.add(toponym.getName()+"&"+toponym.getGeoNameId()+"&"+toponym.getLongitude()+"&"+toponym.getLatitude());
    }
    setSearchCriteria(searchCriteria, "York", 0 ,"GB", "EU", 800);
 
    searchResult = WebService.search(searchCriteria);
    for (Toponym toponym : searchResult.getToponyms()) {
        geonameslist.add(toponym.getName()+"&"+toponym.getGeoNameId()+"&"+toponym.getLongitude()+"&"+toponym.getLatitude());
    }
    setSearchCriteria(searchCriteria, "Nottingham", 0 ,"GB", "EU", 800);
 
    searchResult = WebService.search(searchCriteria);
    for (Toponym toponym : searchResult.getToponyms()) {
        geonameslist.add(toponym.getName()+"&"+toponym.getGeoNameId()+"&"+toponym.getLongitude()+"&"+toponym.getLatitude());
    }
    
    geonameslist = ohneDoppelte(geonameslist);
    
    return geonameslist;
}


}