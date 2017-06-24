package fga.mds.gpp.trezentos.Controller.Util;

/*
* File: SortStudentsUtil.java
* Purpose: Sort students within the room groups
* */

import android.util.Log;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
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
    public static Map<String, Integer> sortGroups(Map<String, Double> map, Integer groupSize,
                                                 Integer totalStudents){
        List<Map.Entry<String, Double>> mapSortedByScore = sortByTestScore(map);
        // Note: Size of the group and total of students must be coming from the database
        Map<String, Integer> newMap = newMapStudents(mapSortedByScore, groupSize, totalStudents);

        return newMap;
    }

    /*
    * Purpose: Method that creates a new student map with the groups numbers
    * @params: List<Map> - converted list of students from database; Integer - Room group size;
    *          Integer - total number of students in the room
    * @return: Map - email(key) and student group number(Double)
    * */
    public static Map<String, Integer> newMapStudents (List<Map.Entry<String, Double>> unsortList,
                                                      Integer groupSize, Integer totalStudents){
        Double leftoverStudents = (totalStudents*1.0) % groupSize;
        Integer totalGroups = (leftoverStudents > 0) ?
                ((int) (totalStudents-leftoverStudents) / groupSize) + 1 :
                (int) (totalStudents-leftoverStudents) / groupSize;

        return saveNumberGroup(unsortList, 0, 1, totalGroups);
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

    private static Map<String, Integer> saveNumberGroup(List<Map.Entry<String, Double>> unsortList,
                                                 int sentinel, Integer it, Integer totalGroups){
        Map<String, Integer> newMap = new LinkedHashMap<>();

        // Assignment of group numbers
        for (Map.Entry<String, Double> entry : unsortList) {
            newMap.put(entry.getKey(), it);

            it = (sentinel == 0) ? it+1 : it-1;
            if(it > totalGroups){
                sentinel++; it--;
            }else if(it == 0){
                it = totalGroups;
            }
        }

        return newMap;
    }

}
