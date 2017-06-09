package fga.mds.gpp.trezentos.Model;


public class Evaluation {

    private String idClass;
    private String idExam;
    private String idStudent;
    private float rateEvaluation;

    public Evaluation(String idClass, String idExam, String idStudent, float rateEvaluation){
        setIdClass(idClass);
        setIdExam(idExam);
        setIdStudent(idStudent);
        setRateEvaluation(rateEvaluation);
    }
    public Evaluation(String idClass, String idExam, String idStudent){
        setIdClass(idClass);
        setIdExam(idExam);
        setIdStudent(idStudent);

    }


    public String getIdClass() {
        return idClass;
    }

    public void setIdClass(String idClass) {
        this.idClass = idClass;
    }

    public String getIdExam() {
        return idExam;
    }

    public void setIdExam(String idExam) {
        this.idExam = idExam;
    }

    public String getIdStudent() {
        return idStudent;
    }

    public void setIdStudent(String idStudent) {
        this.idStudent = idStudent;
    }

    public float getRateEvaluation() {
        return rateEvaluation;
    }

    public void setRateEvaluation(float rateEvaluation) {
        this.rateEvaluation = rateEvaluation;
    }


}
