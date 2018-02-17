package fga.mds.gpp.trezentos.Controller;

import junit.framework.Assert;

import org.junit.Test;
import java.util.ArrayList;
import java.util.HashMap;
import fga.mds.gpp.trezentos.Model.Student;
import static org.junit.Assert.assertEquals;

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

//    @Test
//    public void shouldGetGroups(){
//        HashMap<String, Integer> groups;
//        String classOwnerEmail = "teste@gmail.com";
//        String examName = "P1";
//        String userClassName = "testeteste";
//
//        groups = GroupController.getGroups(examName, userClassName, classOwnerEmail);
//
//        boolean isValid = false;
//
//        if(groups.containsKey("teste@email.com") ||
//                groups.containsKey("testandosedamerda@email.com") ||
//                groups.containsKey("agora@da.com") ||
//                groups.containsKey("gui.988@hotmail.com")){
//
//            isValid = true;
//        }
//
//        Assert.assertEquals(isValid, true);
//
//    }

    @Test
    public void shouldGetFirstGrades(){
        HashMap<String, Double> grades;
        String classOwnerEmail = "teste@gmail.com";
        String examName = "P1";
        String userClassName = "testeteste";

        boolean isValid = false;

        grades = GroupController.getFirstGrades(examName, userClassName, classOwnerEmail);

        if(grades.containsKey("teste@email.com") ||
                grades.containsKey("testandosedamerda@email.com") ||
                grades.containsKey("agora@da.com") ||
                grades.containsKey("gui.988@hotmail.com")){

            isValid = true;
        }

        Assert.assertEquals(isValid, true);
    }

}
