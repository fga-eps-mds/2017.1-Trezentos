package fga.mds.gpp.trezentos.Model;

import org.junit.Test;

import fga.mds.gpp.trezentos.Exception.UserException;

import static org.junit.Assert.assertEquals;

public class StudentTest {

    @Test
    public void shouldValidateToString(){
        Student student = new Student();
        assertEquals("Aluno", student.toString());
    }

    @Test
    public void shouldValidateStudentEmail(){
        Student student = new Student();
        student.setStudentEmail("teste@teste.com");
        assertEquals("teste@teste.com", student.getStudentEmail());
    }

    @Test
    public void shouldValidateStudentFirstGrades(){
        Student student = new Student();
        student.setFirstGrade(5.0);
        assertEquals(5.0, student.getFirstGrade(), 0.1);
    }

    @Test
    public void shouldValidateStudentSecondGrades(){
        Student student = new Student();
        student.setSecondGrade(6.0);
        assertEquals(6.0, student.getSecondGrade(), 0.1);
    }
}
