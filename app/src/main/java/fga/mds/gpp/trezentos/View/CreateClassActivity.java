package fga.mds.gpp.trezentos.View;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import android.widget.Toast;


import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import fga.mds.gpp.trezentos.Controller.UserClassControl;
import fga.mds.gpp.trezentos.Exception.UserException;
import fga.mds.gpp.trezentos.Model.UserAccount;
import fga.mds.gpp.trezentos.Model.UserClass;
import fga.mds.gpp.trezentos.R;

public class CreateClassActivity extends AppCompatActivity {

    private Button buttonOk;
    public UserClass userClass;
    public UserAccount userAccount;
    private EditText classNameField;
    private EditText institutionField;
    private EditText passwordField;
    private EditText sizeGroupsField;
    private EditText additionField;
    private EditText cutOffField;
    private EditText descriptionField;

    private String userName = "";
    private String userId = "";

    private Toolbar toolbar = null;

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        // Inflate the menu; this adds items to the action bar if it is present.

        getMenuInflater().inflate(R.menu.menu_create_class, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        // Noinspection SimplifiableIfStatement
        if(id == R.id.create_button){
            UserClassControl userClassControl = UserClassControl.getInstance(CreateClassActivity.this);

            //Date Creates
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy  HH:mm");
            SimpleDateFormat dateFormat_hora = new SimpleDateFormat("HH:mm:ss");
            Date data = new Date();
            Calendar cal = Calendar.getInstance();
            cal.setTime(data);

            Date data_atual = cal.getTime();
            String dateCreation = dateFormat.format(data_atual);
            String hora_atual = dateFormat_hora.format(data_atual);

            ProgressDialog dialog = null;


            if (validateFields(userClassControl)){
                String serverResponse = "";
                try {
                    UserClass userClass = userClassControl.validateCreateClass(
                            classNameField.getText().toString(),
                            institutionField.getText().toString(),
                            Float.valueOf(cutOffField.getText().toString()),
                            passwordField.getText().toString(),
                            Float.valueOf(additionField.getText().toString()),
                            Integer.valueOf(sizeGroupsField.getText().toString()),
                            descriptionField.getText().toString(),
                            dateCreation,
                            userId,
                            userName);
                    dialog = ProgressDialog.show(this, "Criando sua Sala",
                            "Carregando. Por favor aguarde...", true);

                    serverResponse = userClassControl.createClass(userClass);

                } catch (UserException e) {
                    e.printStackTrace();
                }
                JSONObject object = null;
                boolean error = false;
                String message = "";
                try {
                    object = new JSONObject(serverResponse);
                    error = object.getBoolean("error");
                    message = object.getString("message");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                dialog.dismiss();

                if(error){
                    Toast.makeText(this, message,
                            Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(this, message,
                            Toast.LENGTH_LONG).show();
                    finish();
                }
            }
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_class);

        initView();
        initToolbar();
        initSharedPreferences();
    }

    private void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Criação de Salas");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    private void initView() {
        classNameField = (EditText) findViewById(R.id.edit_text_class_name);
        institutionField = (EditText) findViewById(R.id.edit_text_institution);
        sizeGroupsField = (EditText) findViewById(R.id.edit_text_size_groups);
        cutOffField = (EditText) findViewById(R.id.edit_text_cut_grade);
        additionField = (EditText) findViewById(R.id.edit_text_addition);
        passwordField = (EditText) findViewById(R.id.edit_text_class_password);
        descriptionField = (EditText) findViewById(R.id.edit_text_description);

    }

    private boolean validateFields(UserClassControl userClassControl){
        try{
            return confirmInformation(userClassControl,
                    classNameField.getText().toString(),
                    institutionField.getText().toString(),
                    passwordField.getText().toString(),
                    cutOffField.getText().toString(),
                    sizeGroupsField.getText().toString(),
                    additionField.getText().toString());
        }catch (Exception e){
            e.printStackTrace();
        }

        return false;
    }

    private void initSharedPreferences(){
        SharedPreferences session = PreferenceManager.getDefaultSharedPreferences(
                CreateClassActivity.this.getApplicationContext());
        userName = session.getString("userName", "");
        userId = session.getString("userId", "");

    }

    public boolean confirmInformation(UserClassControl userClassControl, String className,
                                      String institution, String password,
                                      String cutOff, String sizeGroups,
                                      String addition) throws UserException {
        if(cutOff.isEmpty() || addition.isEmpty() || sizeGroups.isEmpty()){
            classNameField.setError("Preencha todos os campos!");
        }
        String errorMessage;
        errorMessage = userClassControl.validateInformation(className, institution,
                cutOff, password, addition, sizeGroups);

        return verifyValidMessage(errorMessage);
    }

    private boolean verifyValidMessage(String errorMessage){
        boolean isValid = false;

        if (errorMessage.equals("Preencha todos os campos!")) {
            classNameField.setError("Preencha todos os campos!");
        } else if (errorMessage.equals("O nome da sala deve ter de 3 a 20 caracteres.")) {
            classNameField.requestFocus();
            classNameField.setError("O nome da sala deve ter de 3 a 20 caracteres.");
        } else if (errorMessage.equals("A senha deve ter entre 6 e 16 caracteres")) {
            passwordField.requestFocus();
            passwordField.setError("A senha deve ter entre 6 e 16 caracteres");
        } else if (errorMessage.equals("O tamanho do grupo nao pode ser zero.")) {
            sizeGroupsField.requestFocus();
            sizeGroupsField.setError("O tamanho do grupo nao pode ser zero.");
        } else if (errorMessage.equals("O acrescimo nao pode ser zero.")) {
            additionField.requestFocus();
            additionField.setError("O acrescimo nao pode ser zero.");
        } else if (errorMessage.equals("A nota de corte nao pode ser zero.")) {
            cutOffField.requestFocus();
            cutOffField.setError("A nota de corte nao pode ser zero.");
        } else if (errorMessage.equals("O nome da instituicao deve ter de 3 a 20 caracteres.")) {
            institutionField.requestFocus();
            institutionField.setError("O nome da instituicao deve ter de 3 a 20 caracteres.");
        } else if (errorMessage.equals("Sucesso")) {
            isValid = true;
        }

        return isValid;
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }


}