package fga.mds.gpp.trezentos.Model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class StudentTest {

    @Test
    public void shouldValidateToString(){
        Student student = new Student();
        assertEquals("Aluno", student.toString());
    }
}
