package fga.mds.gpp.trezentos.View;

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
import android.widget.ProgressBar;


import fga.mds.gpp.trezentos.Controller.UserClassControl;
import fga.mds.gpp.trezentos.Exception.UserException;
import fga.mds.gpp.trezentos.Model.UserAccount;
import fga.mds.gpp.trezentos.Model.UserClass;
import fga.mds.gpp.trezentos.R;

public class CreateClassActivity extends AppCompatActivity {

    private Button buttonOk;
    public UserClass userClass;
    public UserAccount userAccount;
    private ProgressBar progressBar;
    private EditText classNameField;
    private EditText institutionField;
    private EditText passwordField;
    private EditText sizeGroupsField;
    private EditText additionField;
    private EditText cutOffField;


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
        if(id == R.id.search_classes){

            return true;
        }

        return super.onOptionsItemSelected(item);
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_class);
        initFields();
        initButtonAndProgressBar();


        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Criação de Salas");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        /*buttonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);

                UserClassControl userClassControl = UserClassControl.getInstance(CreateClassActivity.this);

                if (validateFields(userClassControl)){
                    try {
                        userClassControl.validateCreateClass(
                                classNameField.getText().toString(),
                                institutionField.getText().toString(),
                                Float.valueOf(cutOffField.getText().toString()),
                                passwordField.getText().toString(),
                                Float.valueOf(additionField.getText().toString()),
                                Integer.valueOf(sizeGroupsField.getText().toString()),
                                initSharedPreferences());
                    } catch (UserException e) {
                        e.printStackTrace();
                    }
                    progressBar.setVisibility(View.GONE);
                    onBackPressed();
                }
            }
        });

    */
    }

    private void initFields(){
//        classNameField = (EditText) findViewById(R.id.edit_text_class_name);
//        institutionField = (EditText) findViewById(R.id.edit_text_institution);
//        passwordField = (EditText) findViewById(R.id.edit_text_class_password);
//        cutOffField = (EditText) findViewById(R.id.edit_text_cut_grade);
//        sizeGroupsField = (EditText) findViewById(R.id.edit_text_size_groups);
//        additionField = (EditText) findViewById(R.id.edit_text_addition);
    }

    private void initButtonAndProgressBar(){
        buttonOk = (Button) findViewById(R.id.button_save);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
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

    private String initSharedPreferences(){
        SharedPreferences session = PreferenceManager.getDefaultSharedPreferences(
                CreateClassActivity.this.getApplicationContext());
        return session.getString("userEmail", "");
    }

    public boolean confirmInformation(UserClassControl userClassControl, String className,
                                      String institution, String password,
                                      String cutOff, String sizeGroups,
                                      String addition) throws UserException {
        if(cutOff.isEmpty() || addition.isEmpty() || sizeGroups.isEmpty()){
            classNameField.setError("Preencha todos os campos!");
        }
        progressBar.setVisibility(View.GONE);
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