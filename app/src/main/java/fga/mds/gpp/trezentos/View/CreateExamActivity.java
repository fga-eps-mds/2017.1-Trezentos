package fga.mds.gpp.trezentos.View;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class CreateExamActivity extends AppCompatActivity {

    public UserClass userClass;
    public UserAccount userAccount;
    public String userClassName;
    public String classOwnnerEmail;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_exam);

        recoverIntent();
        recoverSharedPreferences();

        userClassName = userClass.getClassName();

//        Toast.makeText(CreateExamActivity.this," Usuario: "
//                + classOwnnerEmail + " "
//                    + "Nome Sala: " + userClassName, Toast.LENGTH_SHORT).show();

        final Button buttonOk = (Button) findViewById(R.id.ok_create_button);
        final EditText examNameField = (EditText) findViewById(R.id.exam_name);

        confirmButtonClick(buttonOk, examNameField);

    }

    private void recoverIntent(){
        Intent intent = getIntent();
        userClass = (UserClass) intent.getSerializableExtra("Class");
    }

    private void recoverSharedPreferences(){
        SharedPreferences session = PreferenceManager
                .getDefaultSharedPreferences(getApplicationContext());

        classOwnnerEmail = session.getString("userEmail","");
    }

    private void confirmButtonClick(final Button buttonOk, final EditText examNameField){
        buttonOk.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean isValid;

                UserExamControl userExamControl = UserExamControl
                        .getInstance(getApplicationContext());

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

            }
        });

    }

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