package fga.mds.gpp.trezentos.Controller.Util;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class SortStudentsUtil {
    /*
    * Purpose: Method that must be called to sort room groups
    * @params: Student's map from the database
    * @return: Map representing key(String) being the email and the
    *                           value(Double) being the groups number
    * */
    public static Map<String, Double> sortGroups(Map<String, Double> map){

        List<Map.Entry<String, Double>> mapSortedByScore = sortByTestScore(map);
        // Note: Size of the group and total of students coming from the database
        Map<String, Double> newMap = newMapStudents(mapSortedByScore, 3, 10);

        return newMap;
    }

    /*
    * Purpose: Method that creates a new student map with the groups numbers
    * @params: List<Map> - converted list of students from database; Integer - Room group size;
    *          Integer - total number of students in the room
    * @return: Map - email(key) and student group number(Double)
    *
    * */
    public static Map<String, Double> newMapStudents (List<Map.Entry<String, Double>> unsortList,
                                                      Integer groupSize, Integer totalStudents){
        Map<String, Double> newMap = new LinkedHashMap<>();

        Double it = 1.0;
        int sent = 0;
        Double rest = (totalStudents*1.0) % groupSize;
        Double totalGroups = (totalStudents-rest) / groupSize;

        // Assignment of group numbers
        for (Map.Entry<String, Double> entry : unsortList) {
            newMap.put(entry.getKey(), it);
            if (sent == 0) {
                it++;
                if (it > totalGroups) {
                    sent++; it--;
                } else {
                    // do nothing
                }
            } else {
                it--;
                if (it == 0) {
                    it = totalGroups;
                } else {
                    // do nothing
                }
            }
        }

        return newMap;
    }

    /*
    * Purpose: Method that sort by student's score
    * @params: Map - email(key) and student's score(value); Integer - Room group size;
    *          Integer - total number of students in the room
    * @return: List
    * */
    public static List<Map.Entry<String,Double>> sortByTestScore(Map<String, Double> mapStudents){

        // Convert Map to list of Map
        List<Map.Entry<String, Double>> list = new LinkedList<>(mapStudents.entrySet());

        // Sort list
        Collections.sort(list, new Comparator<Map.Entry<String, Double>>() {
            public int compare(Map.Entry<String, Double> obj1, Map.Entry<String, Double> obj2) {
                return obj2.getValue().compareTo(obj1.getValue());
            }
        });

        return list;
    }
}
