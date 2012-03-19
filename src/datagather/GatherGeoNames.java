/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package datagather;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.geonames.*;

/**
 *
 * @author Niels
 */
public class GatherGeoNames {

    static ArrayList<String> filelist = new ArrayList<>();
    static ArrayList<String> riverlist = new ArrayList<>();
    int startRow;
    String q;
    String countryCode;
    String ContinentCode;
    String classes;
    int maxrow;

    public <T> ArrayList<T> removeMultipleEntries(Collection<T> list) {
        return new ArrayList<>(new HashSet<>(list));
    }
    /*
     * Style set SHORT FeatureClass set P
     */

    public ToponymSearchCriteria geSearchCriteriaByParameters(SearchCriteriaParameter param) {
        ToponymSearchCriteria searchCriteria = new ToponymSearchCriteria();
        searchCriteria.setQ(param.getArea());
        searchCriteria.setStartRow(param.getStartRow());

        try {
            searchCriteria.setCountryCode(param.getContryCode());
        } catch (InvalidParameterException ex) {
            throw new RuntimeException(ex);
        }

        searchCriteria.setContinentCode(param.getContineltalCode());
        searchCriteria.setMaxRows(param.getMaxRows());
        searchCriteria.setStyle(Style.SHORT);
        searchCriteria.setFeatureClass(FeatureClass.P);
        return searchCriteria;
    }

    public ArrayList<String> getResultGeoNames() {

        WebService.setUserName("bachelorwinkler");

        ArrayList<SearchCriteriaParameter> parameterList = new ArrayList<SearchCriteriaParameter>();
        parameterList.add(new SearchCriteriaParameter("Yorkshire", 0, "GB", "EU", 800));
        parameterList.add(new SearchCriteriaParameter("Lincolnshire", 0, "GB", "EU", 800));
        parameterList.add(new SearchCriteriaParameter("Nottinghamshire", 0, "GB", "EU", 800));
        parameterList.add(new SearchCriteriaParameter("Leeds", 0, "GB", "EU", 800));
        parameterList.add(new SearchCriteriaParameter("Bradford", 0, "GB", "EU", 800));
        parameterList.add(new SearchCriteriaParameter("Calderdale", 0, "GB", "EU", 800));
        parameterList.add(new SearchCriteriaParameter("Kirklees", 0, "GB", "EU", 800));
        parameterList.add(new SearchCriteriaParameter("Wakefield", 0, "GB", "EU", 800));
        parameterList.add(new SearchCriteriaParameter("Barnsley", 0, "GB", "EU", 800));
        parameterList.add(new SearchCriteriaParameter("Sheffield", 0, "GB", "EU", 800));
        parameterList.add(new SearchCriteriaParameter("Rotherham", 0, "GB", "EU", 800));
        parameterList.add(new SearchCriteriaParameter("Doncaster", 0, "GB", "EU", 800));
        parameterList.add(new SearchCriteriaParameter("Nottingham", 0, "GB", "EU", 800));
        parameterList.add(new SearchCriteriaParameter("York", 0, "GB", "EU", 800));
        parameterList.add(new SearchCriteriaParameter("Hull", 0, "GB", "EU", 800));


        ArrayList<String> geonameslist = new ArrayList<>();

        for (SearchCriteriaParameter param : parameterList) {
            ToponymSearchCriteria criteria = geSearchCriteriaByParameters(param);
            ToponymSearchResult searchResult = null;

            try {
                searchResult = WebService.search(criteria);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }

            for (Toponym toponym : searchResult.getToponyms()) {
                geonameslist.add(toponym.getName() + "&" + toponym.getGeoNameId() + "&" + toponym.getLongitude() + "&" + toponym.getLatitude());
            }
        }

        geonameslist = removeMultipleEntries(geonameslist);

        return geonameslist;
    }

    private class SearchCriteriaParameter {

        private String area = "";
        private int startRow = 0;
        private String contryCode = "";
        private String contineltalCode = "";
        private int maxRows = 0;

        SearchCriteriaParameter(String area, int startRow, String contry, String continental, int maxRows) {
            this.area = area;
            this.startRow = startRow;
            this.contryCode = contry;
            this.contineltalCode = continental;
            this.maxRows = maxRows;
        }

        /**
         * @return the area
         */
        String getArea() {
            return area;
        }

        /**
         * @return the startRow
         */
        int getStartRow() {
            return startRow;
        }

        /**
         * @return the contryCode
         */
        String getContryCode() {
            return contryCode;
        }

        /**
         * @return the contineltalCode
         */
        String getContineltalCode() {
            return contineltalCode;
        }

        /**
         * @return the maxRows
         */
        int getMaxRows() {
            return maxRows;
        }
    }
}