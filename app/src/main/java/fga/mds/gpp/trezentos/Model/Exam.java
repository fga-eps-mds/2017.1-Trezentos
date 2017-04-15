package fga.mds.gpp.trezentos.Model;


public class Exam {


    private String nameExam;

    public Exam(){


    }

    public Exam(String nameExam){

        this.nameExam = nameExam;
    }


    @Override
    public String toString() {
        return "\n"+"Prova: "+ nameExam+ "\n";
    }
}
