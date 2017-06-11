package fga.mds.gpp.trezentos.Model;

public class Evaluation {

    private String studentEmail;
    private String examName;
    private String className;

    public Evaluation(){
    }

    public String getStudentEmail() {
        return studentEmail;
    }

    public void setStudentEmail(String studentName) {
        this.studentEmail = studentName;
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

}
