package fga.mds.gpp.trezentos.Controller;

/*
* File: SortStudentsUtilTest.java
* Purpose: To perform SortStudentsUtil.java class tests
* */

import org.junit.Test;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import fga.mds.gpp.trezentos.Controller.Util.SortStudentsUtil;

public class SortStudentsUtilUnitTest{
    SortStudentsUtil sortStudentsUtil = new SortStudentsUtil();

    // Tested method: sortByTestScore();
    @Test
    public void shouldOrdenateStudentsByScore(){

        Map<String, Double> map = new HashMap<>(populateMap());

        List<Map.Entry<String, Double>> listOrdered = sortStudentsUtil.sortByTestScore(map);
        Map<String, Double> mapOut = new HashMap<>();

        for (Map.Entry<String, Double> entry : listOrdered) {
            mapOut.put(entry.getKey(), entry.getValue());
        }

        Map<String, Double> expected = new HashMap<>(populateOrderedMapByScore());

        assertThat(mapOut, is(expected));
    }

    // Tested method: newMapStudents();
    @Test
    public void shouldOrdenateStudentsByGroup(){
        Map<String, Double> map = populateMap();
        List<Map.Entry<String, Double>> list = new LinkedList<>(map.entrySet());

        Collections.sort(list, new Comparator<Map.Entry<String, Double>>() {
            public int compare(Map.Entry<String, Double> obj1, Map.Entry<String, Double> obj2) {
                return obj2.getValue().compareTo(obj1.getValue());
            }
        });

        Map<String, Double> mapOut = sortStudentsUtil.newMapStudents(list, 3, 10);
        Map<String, Double> expected = populateOrderedMapByGroupNumber();

        assertThat(mapOut, is(expected));
    }

    // Tested method: sortGroups();
    @Test
    public void shouldOrdenateGroup(){
        Map<String, Double> mapOut = sortStudentsUtil.sortGroups(populateMap());
        Map<String, Double> expected = populateOrderedMapByGroupNumber();

        assertThat(mapOut, is(expected));
    }


    public Map <String, Double> populateMap(){
        Map<String, Double> map = new HashMap<>();

        map.put("emailA@hotmail.com", 7.0);
        map.put("emailB@hotmail.com", 9.5);
        map.put("emailC@hotmail.com", 8.0);

        map.put("emailD@hotmail.com", 5.5);
        map.put("emailE@hotmail.com", 9.0);
        map.put("emailF@hotmail.com", 7.5);

        map.put("emailG@hotmail.com", 10.0);
        map.put("emailH@hotmail.com", 6.0);
        map.put("emailI@hotmail.com", 6.5);
        map.put("emailJ@hotmail.com", 8.5);

        return map;
    }

    public Map <String, Double> populateOrderedMapByScore(){
        Map<String, Double> mapOut = new HashMap<>();

        mapOut.put("emailG@hotmail.com", 10.0);
        mapOut.put("emailB@hotmail.com", 9.5);
        mapOut.put("emailE@hotmail.com", 9.0);

        mapOut.put("emailJ@hotmail.com", 8.5);
        mapOut.put("emailC@hotmail.com", 8.0);
        mapOut.put("emailF@hotmail.com", 7.5);

        mapOut.put("emailA@hotmail.com", 7.0);
        mapOut.put("emailI@hotmail.com", 6.5);
        mapOut.put("emailH@hotmail.com", 6.0);
        mapOut.put("emailD@hotmail.com", 5.5);

        return mapOut;
    }

    public Map <String, Double> populateOrderedMapByGroupNumber(){
        Map<String, Double> map = new HashMap<>();

        map.put("emailG@hotmail.com", 1.0);
        map.put("emailB@hotmail.com", 2.0);
        map.put("emailE@hotmail.com", 3.0);

        map.put("emailJ@hotmail.com", 3.0);
        map.put("emailC@hotmail.com", 2.0);
        map.put("emailF@hotmail.com", 1.0);

        map.put("emailA@hotmail.com", 3.0);
        map.put("emailI@hotmail.com", 2.0);
        map.put("emailH@hotmail.com", 1.0);
        map.put("emailD@hotmail.com", 3.0);

        return map;
    }

}
