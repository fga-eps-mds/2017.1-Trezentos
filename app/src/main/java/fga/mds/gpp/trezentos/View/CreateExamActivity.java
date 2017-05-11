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

        Intent intent = getIntent();
        userClass = (UserClass) intent.getSerializableExtra("Class");


        SharedPreferences session = PreferenceManager
                .getDefaultSharedPreferences(getApplicationContext());

        classOwnnerEmail = session.getString("userEmail","");
        UserClassControl userClassControl = UserClassControl
                .getInstance(getApplicationContext());

        userClassName = userClass.getClassName();


        Toast.makeText(CreateExamActivity.this," Usuario: "
                + classOwnnerEmail + " "
                    + "Nome Sala: " + userClassName, Toast.LENGTH_SHORT).show();

        final Button buttonOk = (Button) findViewById(R.id.ok_create_button);
        final EditText examNameField = (EditText) findViewById(R.id.exam_name);


        buttonOk.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean isValid;

                UserExamControl userExamControl = UserExamControl
                        .getInstance(getApplicationContext());

                try {

                    isValid = confirmInformation(userExamControl,
                            examNameField, userClassName, classOwnnerEmail);

                } catch (Exception e) {
                    e.printStackTrace();
                    return;
                }

                if (isValid) {

                    String examName = examNameField.getText().toString();

                    try {
                        userExamControl.validateCreateExam
                                (examName, userClassName, classOwnnerEmail);
                    } catch (UserException e) {
                        e.printStackTrace();
                    }

                    onBackPressed();
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

        errorMessage = userExamControl
                .validateInformation(examName,userClassName, classOwnnerEmail );

        if (errorMessage.equals("Preencha todos os campos!")) {
            examNameField.setError("Preencha todos os campos!");
            isValid = false;
        }

        if (errorMessage.equals("O nome da sala deve ter de 3 a 20 caracteres.")){
            examNameField.requestFocus();
            examNameField.setError("O nome da sala deve ter de 3 a 20 caracteres.");
            isValid = false;
        }

        if (errorMessage.equals("Sucesso")) {

            isValid = true;
        }

        return isValid;
    }
}