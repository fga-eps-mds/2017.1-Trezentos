package fga.mds.gpp.trezentos.View;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import fga.mds.gpp.trezentos.Controller.UserClassControl;
import fga.mds.gpp.trezentos.Controller.UserExamControl;
import fga.mds.gpp.trezentos.Exception.UserException;
import fga.mds.gpp.trezentos.Model.UserAccount;
import fga.mds.gpp.trezentos.Model.UserClass;
import fga.mds.gpp.trezentos.R;

public class CreateExamActivity extends AppCompatActivity implements View.OnClickListener {

    public UserClass userClass;
    public UserAccount userAccount;
    public EditText examNameField;
    public String userClassName;
    public String classOwnnerEmail;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_exam);

        recoverIntent();
        recoverSharedPreferences();

        userClassName = userClass.getClassName();

        final Button buttonOk = (Button) findViewById(R.id.ok_create_button);
        final Button buttonReturn = (Button) findViewById(R.id.return_exam_button);

        examNameField = (EditText) findViewById(R.id.exam_name);

        buttonOk.setOnClickListener(this);
        buttonReturn.setOnClickListener(this);
    }


    /*
    Purpose: Recover the last intent initiated before this class.
    Without @params and @return
     */

    private void recoverIntent(){
        Intent intent = getIntent();
        userClass = (UserClass) intent.getSerializableExtra("Class");
    }

    /*
    Purpose: this method recover SharedPreferences about userEmail.
    Without @params and @return
     */

    private void recoverSharedPreferences(){
        SharedPreferences session = PreferenceManager
                .getDefaultSharedPreferences(getApplicationContext());

        classOwnnerEmail = session.getString("userEmail","");
    }

    /*
    Purpose: Overriding method onClick to improve AMLOC metric.
    @params: View where button pressed exists.
     */

    @Override
    public void onClick(View v) {

        boolean isValid = false;

        UserExamControl userExamControl = UserExamControl
                .getInstance(getApplicationContext());

        switch (v.getId()){
            case R.id.return_exam_button:{
                Intent returnToClass = new Intent(CreateExamActivity.this, ClassActivity.class);
                startActivity(returnToClass);
                finish();
                break;
            }
            case R.id.ok_create_button:{
                try {
                    isValid = confirmInformation(userExamControl,
                            examNameField, userClassName, classOwnnerEmail);

                    if(isValid){
                        String examName = examNameField.getText().toString();

                        userExamControl.validateCreateExam
                                (examName, userClassName, classOwnnerEmail);

                        onBackPressed();
                    }

                } catch (UserException e) {
                    e.printStackTrace();
                }
                break;
            }
        }
    }

    /*
    Purpose: Method to validate information about Exam params
    @params: Controller of Exam, field of exam name and Strings about the class
    where a exam will be created and the owner email.
    @return: boolean to confirm all fields of parameters.
     */

    public boolean confirmInformation
            (UserExamControl userExamControl,
                EditText examNameField, String userClassName,
                    String classOwnnerEmail) throws UserException {

        boolean isValid = false;
        String examName = examNameField.getText().toString();
        String errorMessage;

        if(examName.isEmpty()){
            examNameField.setError("Preencha todos os campos!");
            isValid = false;
        }

        errorMessage = userExamControl
                .validateInformation(examName,userClassName, classOwnnerEmail );

        if (errorMessage.equals("Preencha todos os campos!")) {
            examNameField.setError("Preencha todos os campos!");
            isValid = false;
        }else if (errorMessage.equals("O nome da prova " +
                "deve ter entre 2 e 15 caracteres.")){
            examNameField.requestFocus();
            examNameField.setError("O nome da prova " +
                    "deve ter entre 2 e 15 caracteres.");
            isValid = false;
        }else if (errorMessage.equals("Sucesso")) {
            isValid = true;
        }

        return isValid;
    }
}