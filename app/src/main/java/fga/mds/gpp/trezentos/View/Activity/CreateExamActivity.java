package fga.mds.gpp.trezentos.View.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import fga.mds.gpp.trezentos.Controller.UserClassControl;
import fga.mds.gpp.trezentos.Controller.UserExamControl;
import fga.mds.gpp.trezentos.Exception.UserException;
import fga.mds.gpp.trezentos.Model.UserAccount;
import fga.mds.gpp.trezentos.Model.UserClass;
import fga.mds.gpp.trezentos.R;

public class CreateExamActivity extends AppCompatActivity {

    public UserClass userClass;
    public UserAccount userAccount;
    public EditText examNameField;
    private String userId;
    private Toolbar toolbar;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_exam);

        initToolbar();
        recoverIntent();
        recoverSharedPreferences();


        examNameField = findViewById(R.id.exam_name);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_create_exam, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        // Noinspection SimplifiableIfStatement
        if(id == R.id.save_button){
            UserExamControl userExamControl = UserExamControl.getInstance(getApplicationContext());

            String examName = examNameField.getText().toString();
            try {
                String erroMessage = userExamControl.validateCreateExam( examName,
                                                    userId,
                                                    userClass.getIdClassCreator(),
                                                    userClass.getIdClass());
                if(erroMessage.equals("")){
                    String response = userExamControl.createExam();
                    JSONObject obj = new JSONObject(response);

                    if (!obj.getBoolean("error")) {
                        Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();

                        finish();

                    } else {
                        Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();
                    }
                }else{
                    verifyValidMessage(erroMessage);
                }



            } catch (UserException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return super.onOptionsItemSelected(item);
    }

    private void initToolbar() {
        toolbar = findViewById(R.id.toolbar_exam);
        setSupportActionBar(toolbar);
        setTitle("Criação de Teste");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
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
        userId = session.getString("userId", "");

    }

    /*
    Purpose: Overriding method onClick to improve AMLOC metric.
    @params: View where button pressed exists.
     */
    private boolean verifyValidMessage(String errorMessage){
        switch (errorMessage) {
            case "O nome não pode ser vazio.":
                examNameField.requestFocus();
                examNameField.setError("O nome não pode ser vazio.");
                break;
            case "O nome da prova deve ter entre 2 e 15 caracteres.":
                examNameField.requestFocus();
                examNameField.setError("O nome da prova deve ter entre 2 e 15 caracteres.");
                break;
            case "Sucesso":
                return true;
        }

        return false;
    }
}