package fga.mds.gpp.trezentos.Model;

public class Student extends UserAccount{

    public Student(){
        //An empty constructor is needed to create a new instance of object,
        //in addition is create constructors with arguments.
    }

    @Override
    public String toString(){
        return "Aluno";
    }
}