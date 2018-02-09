package fga.mds.gpp.trezentos.DAO;



public class URLs {

    private static final String ROOT_URL = "http://metodo300.com/android/api/";

    public static final String URL_REGISTER = ROOT_URL + "user/sign_up.php";
    public static final String URL_LOGIN = ROOT_URL + "user/login.php";
    public static final String URL_RESET_PASSWORD = ROOT_URL + "user/reset_password.php";

    public static final String  URL_CLASS_FROM_PERSON = ROOT_URL + "class/class_from_person.php"; //GET
    public static final String  URL_ALL_CLASS_AVALIABLE = ROOT_URL + "class/all_class.php"; //GET
    public static final String  URL_INSERT_STUDENT_CLASS = ROOT_URL + "class/enter_class.php"; //POST
    public static final String  URL_CREATE_CLASS = ROOT_URL + "class/create_class.php"; //POST


}