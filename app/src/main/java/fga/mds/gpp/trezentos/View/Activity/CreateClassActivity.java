package fga.mds.gpp.trezentos.View.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import android.widget.Toast;


import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import fga.mds.gpp.trezentos.Controller.UserAccountControl;
import fga.mds.gpp.trezentos.Controller.UserClassControl;
import fga.mds.gpp.trezentos.Exception.UserException;
import fga.mds.gpp.trezentos.R;

import static com.facebook.FacebookSdk.getApplicationContext;

public class CreateClassActivity extends AppCompatActivity {

    private UserClassControl userClassControl;

    private EditText classNameField;
    private EditText institutionField;
    private EditText passwordField;
    private EditText passwordFieldConfirmation;
    private EditText sizeGroupsField;
    private EditText additionField;
    private EditText cutOffField;
    private EditText descriptionField;

    private String userName = "";
    private String userId = "";

    private Toolbar toolbar = null;
    ProgressDialog dialog = null;


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

            UserAccountControl userAccountControl =
                    UserAccountControl.getInstance(getApplicationContext());
            if(userAccountControl.isNetworkAvailable()) {
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


                String errorMessage = "";
                try {
                    errorMessage = userClassControl.validateCreateClass(classNameField.getText().toString(),
                            institutionField.getText().toString(),
                            cutOffField.getText().toString(),
                            passwordField.getText().toString(),
                            passwordFieldConfirmation.getText().toString(),
                            additionField.getText().toString(),
                            sizeGroupsField.getText().toString(),
                            descriptionField.getText().toString(),
                            dateCreation,
                            userId,
                            userName);
                } catch (UserException e) {
                    e.printStackTrace();
                }
                verifyMessage(errorMessage);

                return true;
            } else {
                Toast.makeText(
                        getApplicationContext(),
                        "Verifique a conexão com a internet e tente novamente!",
                        Toast.LENGTH_LONG
                ).show();
                return false;
            }

        }
        return super.onOptionsItemSelected(item);
    }

    private void verifyMessage(String errorMessage){
        if (errorMessage.equals("")){
            try {

                dialog = ProgressDialog.show(this, "Criando sua Sala",
                        "Carregando. Por favor aguarde...", true);

                String serverResponse = userClassControl.createClass();
                validateServerResponse(serverResponse);

            } catch (UserException e) {
                e.printStackTrace();
            }
        }else{
            Log.d("Erro Message", errorMessage);
            showErrorMessage(errorMessage);
        }
    }

    private void validateServerResponse(String response){
        try {
            //converting response to json object
            JSONObject obj = new JSONObject(response);
            dialog.dismiss();
            //if no error in response
            if (!obj.getBoolean("error")) {
                Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();

                finish();

            } else {
                Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_class);

        initView();
        initToolbar();
        initSharedPreferences();

        userClassControl = UserClassControl
                .getInstance(getApplicationContext());
    }

    private void initToolbar() {
        toolbar = findViewById(R.id.toolbar);
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
        passwordFieldConfirmation = (EditText) findViewById(R.id.edit_text_class_password_confirmation);
        descriptionField = (EditText) findViewById(R.id.edit_text_description);
    }



    private void initSharedPreferences(){
        SharedPreferences session = PreferenceManager.getDefaultSharedPreferences(
                CreateClassActivity.this.getApplicationContext());
        userName = session.getString("userFirstName", "") + " " + session.getString("userLastName", "");
        userId = session.getString("userId", "");

    }

    private boolean showErrorMessage(String errorMessage) {
        boolean isValid = false;

        switch (errorMessage) {
            case "Preencha todos os campos!":
                classNameField.requestFocus();
                classNameField.setError("Preencha todos os campos!");
                break;
            case "O nome da sala deve ter de 3 a 20 caracteres.":
                classNameField.requestFocus();
                classNameField.setError("O nome da sala deve ter de 3 a 20 caracteres.");
                break;
            case "Instituicao vazia":
                institutionField.requestFocus();
                institutionField.setError("Preencha todos os campos!");
                break;
            case "O nome da instituicao deve ter de 3 a 20 caracteres.":
                institutionField.requestFocus();
                institutionField.setError("O nome da instituição deve ter de 3 a 20 caracteres.");
                break;
            case "Descricao vazia":
                descriptionField.requestFocus();
                descriptionField.setError("Preencha todos os campos!");
                break;
            case "O tamanho do grupo não pode estar vazio!":
                sizeGroupsField.requestFocus();
                sizeGroupsField.setError("Preencha todos os campos!");
                break;
            case "O tamanho do grupo nao pode ser zero.":
                sizeGroupsField.requestFocus();
                sizeGroupsField.setError("O tamanho do grupo não pode ser zero.");
                break;
            case "Preencha o valor da nota de corte.":
                cutOffField.requestFocus();
                cutOffField.setError("Preencha todos os campos!");
                break;
            case "Nota de corte maior do que 10":
                cutOffField.requestFocus();
                cutOffField.setError("A nota de corte não pode ser maior do que 10.");
                break;
            case "A nota de corte nao pode ser zero.":
                cutOffField.requestFocus();
                cutOffField.setError("A nota de corte não pode ser zero.");
                break;
            case "Preencha o valor do acréscimo.":
                additionField.requestFocus();
                additionField.setError("Preencha todos os campos!");
                break;
            case "O acrescimo nao pode ser zero.":
                additionField.requestFocus();
                additionField.setError("O acréscimo não pode ser zero.");
                break;
            case "Acrescimo maior que 10.":
                additionField.requestFocus();
                additionField.setError("O acréscimo não pode ser maior do que 10");
                break;
            case "Senha vazia":
                passwordField.requestFocus();
                passwordField.setError("Preencha todos os campos!");
                break;
            case "A senha deve ter entre 6 e 16 caracteres":
                passwordField.requestFocus();
                passwordField.setError("A senha deve ter entre 6 e 16 caracteres");
                break;
            case "Sucesso":
                isValid = true;
                break;
            case "As senhas devem ser iguais":
                passwordFieldConfirmation.requestFocus();
                passwordFieldConfirmation.setError("As senhas devem ser iguais");
        }

        return isValid;
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }


}