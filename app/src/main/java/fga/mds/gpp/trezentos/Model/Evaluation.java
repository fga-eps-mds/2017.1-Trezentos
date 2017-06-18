package fga.mds.gpp.trezentos.Model;

public class Evaluation {

    private String studentEmail;
    private String examName;
    private String className;
    private float rateEvaluation;


    public Evaluation(){
    }

    public Evaluation(String className, String examName, String studentEmail, float rateEvaluation){
        setClassName(className);
        setExamName(examName);
        setStudentEmail(studentEmail);
        setRateEvaluation(rateEvaluation);
    }
    public Evaluation(String className, String examName, String studentEmail){
        setClassName(className);
        setExamName(examName);
        setStudentEmail(studentEmail);

    }

    public String getStudentEmail() {
        return studentEmail;
    }

    public void setStudentEmail(String studentEmail) {
        this.studentEmail = studentEmail;
    }

    public String getExamName() {
        return examName;
    }

    public void setExamName(String examName) {
        this.examName = examName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public float getRateEvaluation() {
        return rateEvaluation;
    }

    public void setRateEvaluation(float rateEvaluation) {
        this.rateEvaluation = rateEvaluation;
    }
}
