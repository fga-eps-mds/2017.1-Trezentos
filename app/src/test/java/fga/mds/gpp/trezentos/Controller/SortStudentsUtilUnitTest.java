package fga.mds.gpp.trezentos.Controller;

import org.junit.Test;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import fga.mds.gpp.trezentos.Controller.Util.SortStudentsUtil;

public class SortStudentsUtilUnitTest{
    SortStudentsUtil sortStudentsUtil = new SortStudentsUtil();

    @Test
    public void shouldOrdenateStudentsByScore(){

        Map<String, Double> map = new HashMap<>(populateMap());

        List<Map.Entry<String, Double>> mapSortedByScore = sortStudentsUtil.sortByTestScore(map);
        Map<String, Double> expected = new HashMap<>();

        for (Map.Entry<String, Double> entry : mapSortedByScore) {
            expected.put(entry.getKey(), entry.getValue());
        }

        Map<String, Double> mapOut = new HashMap<>(populateOrderedMapByScore());

        assertThat(mapOut, is(expected));
    }

    @Test
    public void shouldOrdenateStudentsByGroup(){
        Map<String, Double> map = new HashMap<>(populateOrderedMapByScore());
        List<Map.Entry<String, Double>> list = new ArrayList<>(map.entrySet());

        Map<String, Double> mapOut = new LinkedHashMap<>(sortStudentsUtil.newMapStudents(list, 3, 10));

        Map<String, Double> expected = new HashMap<>(populateOrderedMapByGroupNumber());

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
        Map<String, Double> mapOut = new HashMap<>();

        mapOut.put("emailG@hotmail.com", 1.0);
        mapOut.put("emailB@hotmail.com", 2.0);
        mapOut.put("emailE@hotmail.com", 3.0);

        mapOut.put("emailJ@hotmail.com", 3.0);
        mapOut.put("emailC@hotmail.com", 2.0);
        mapOut.put("emailF@hotmail.com", 1.0);

        mapOut.put("emailA@hotmail.com", 3.0);
        mapOut.put("emailI@hotmail.com", 2.0);
        mapOut.put("emailH@hotmail.com", 1.0);
        mapOut.put("emailD@hotmail.com", 3.0);

        return mapOut;
    }

}
