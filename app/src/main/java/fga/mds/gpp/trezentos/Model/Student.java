package fga.mds.gpp.trezentos.Model;

import java.util.zip.DeflaterOutputStream;

public class Student extends UserAccount{

    private String studentEmail;
    private Double firstGrade;
    private Double secondGrade;


    public Student(){
        //An empty constructor is needed to create a new instance of object,
        //in addition is create constructors with arguments.
    }

    public Student(String email){
        setStudentEmail(email);
        //An empty constructor is needed to create a new instance of object,
        //in addition is create constructors with arguments.
    }



    @Override
    public String toString(){
        return "Aluno";
    }

    public String getStudentEmail() {
        return studentEmail;
    }

    public void setStudentEmail(String studentEmail) {
        this.studentEmail = studentEmail;
    }

    public Double getFirstGrade() {
        return firstGrade;
    }

    public void setFirstGrade(Double firstGrade) {
        this.firstGrade = firstGrade;
    }

    public Double getSecondGrade() {
        return secondGrade;
    }

    public void setSecondGrade(Double secondGrade) {
        this.secondGrade = secondGrade;
    }
}