package fga.mds.gpp.trezentos.Controller;

import org.junit.Before;
import org.junit.Test;
import org.robolectric.Robolectric;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import fga.mds.gpp.trezentos.Model.Student;
import fga.mds.gpp.trezentos.View.ClassActivity;
import fga.mds.gpp.trezentos.View.ExamActivity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class GroupControllerUnitTest {

    @Test
    public void shouldValidateSetSpecificGroupAndGrades(){

        HashMap<String, Integer> groups = new HashMap<>(populateGroups());
        HashMap<String, Double> firstGrades = new HashMap<>(populateFirstGrades());

        GroupController groupController = new GroupController();

        ArrayList<Student> arrayTest = groupController.setSpecificGroupAndGrades(
                "emailA@hotmail.com", firstGrades, groups);

        assertEquals("emailC@hotmail.com", arrayTest.get(0).getStudentEmail());
        assertEquals(8.0, arrayTest.get(0).getFirstGrade(), 0.1);
        assertEquals("emailA@hotmail.com", arrayTest.get(1).getStudentEmail());
        assertEquals(7.0, arrayTest.get(1).getFirstGrade(), 0.1);

    }

    private HashMap<String, Integer> populateGroups(){
        HashMap<String, Integer> map = new HashMap<>();

        map.put("emailA@hotmail.com", 1);
        map.put("emailB@hotmail.com", 2);
        map.put("emailC@hotmail.com", 1);

        return map;
    }

    private HashMap<String, Double> populateFirstGrades(){
        HashMap<String, Double> map = new HashMap<>();

        map.put("emailA@hotmail.com", 7.0);
        map.put("emailB@hotmail.com", 9.5);
        map.put("emailC@hotmail.com", 8.0);

        return map;
    }
}
