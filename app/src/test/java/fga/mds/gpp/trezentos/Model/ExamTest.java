package fga.mds.gpp.trezentos.Model;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import fga.mds.gpp.trezentos.Controller.SortStudentsUtilUnitTest;
import fga.mds.gpp.trezentos.Controller.Util.SortStudentsUtil;
import fga.mds.gpp.trezentos.Exception.UserException;

import static org.junit.Assert.assertEquals;

public class ExamTest {

//    @Test
//    public void shouldValidateClassOwnerEmail() throws UserException {
//        Exam exam = new Exam();
//        exam.setClassOwnerEmail("Joao123@email.com");
//        assertEquals("Joao123@email.com", exam.getClassOwnerEmail());
//    }

    @Test
    public void shouldValidateNameExam() throws UserException {
        Exam exam = new Exam();
        exam.setNameExam("Prova 1");
        assertEquals("Prova 1", exam.getNameExam());
    }

//    @Test
//    public void shouldValidateUserClassName() throws UserException {
//        Exam exam = new Exam();
//        exam.setUserClassName("Calculo 1");
//        assertEquals("Calculo 1", exam.getUserClassName());
//    }

//    @Test
//    public void shouldValidateExamConstructor() throws UserException {
//        Exam exam = new Exam("Prova 1", "Calculo 1", "Joao123@email.com");
//        assertEquals("Prova 1", exam.getNameExam());
//        assertEquals("Calculo 1", exam.getUserClassName());
//        assertEquals("Joao123@email.com", exam.getClassOwnerEmail());
//    }

    @Test
    public void shouldValidadeFirstGrades() throws UserException {
        Exam exam = new Exam();
        exam.setFirstGrades(populateHashMap().toString());
        assertEquals(populateHashMap().toString(), exam.getFirstGrades());
    }

    public HashMap<String, String> populateHashMap(){
        HashMap<String, String> hashMap= new HashMap<>();

        hashMap.put("emailA@hotmail.com", "7.0");
        hashMap.put("emailB@hotmail.com", "9.5");
        hashMap.put("emailC@hotmail.com", "8.0");

        hashMap.put("emailD@hotmail.com", "5.5");
        hashMap.put("emailE@hotmail.com", "9.0");
        hashMap.put("emailF@hotmail.com", "7.5");

        hashMap.put("emailG@hotmail.com", "10.0");
        hashMap.put("emailH@hotmail.com", "6.0");
        hashMap.put("emailI@hotmail.com", "6.5");
        hashMap.put("emailJ@hotmail.com", "8.5");

        return hashMap;
    }

    public void shouldValidateSecondGrades() {
        Exam exam = new Exam();
        exam.setSecondGrades("Teste300");
        assertEquals("Teste300", exam.getSecondGrades());
    }
}
