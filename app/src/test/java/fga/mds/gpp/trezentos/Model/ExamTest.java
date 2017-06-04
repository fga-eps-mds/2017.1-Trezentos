package fga.mds.gpp.trezentos.Model;

import org.junit.Test;

import fga.mds.gpp.trezentos.Exception.UserException;

import static org.junit.Assert.assertEquals;

public class ExamTest {

    @Test
    public void shouldValidateClassOwnerEmail() throws UserException {
        Exam exam = new Exam();
        exam.setClassOwnerEmail("Joao123@email.com");
        assertEquals("Joao123@email.com", exam.getClassOwnerEmail());
    }

    @Test
    public void shouldValidateNameExam() throws UserException {
        Exam exam = new Exam();
        exam.setNameExam("Prova 1");
        assertEquals("Prova 1", exam.getNameExam());
    }

    @Test
    public void shouldValidateUserClassName() throws UserException {
        Exam exam = new Exam();
        exam.setUserClassName("Calculo 1");
        assertEquals("Calculo 1", exam.getUserClassName());
    }

    @Test
    public void shouldValidateExamConstructor() throws UserException {
        Exam exam = new Exam("Prova 1", "Calculo 1", "Joao123@email.com");
        assertEquals("Prova 1", exam.getNameExam());
        assertEquals("Calculo 1", exam.getUserClassName());
        assertEquals("Joao123@email.com", exam.getClassOwnerEmail());
    }

    @Test
    public void shouldValidateSecondGrades() {
        Exam exam = new Exam();
        exam.setSecondGrades("Teste300");
        assertEquals("Teste300", exam.getSecondGrades());
    }
}
