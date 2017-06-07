package fga.mds.gpp.trezentos.View;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.renderscript.Double2;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;


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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_class);


//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setDisplayShowHomeEnabled(true);
//        setTitle("Criar Sala");

        final EditText classNameField = (EditText) findViewById(R.id.edit_text_class_name);
        final EditText institutionField = (EditText) findViewById(R.id.edit_text_institution);
        final EditText passwordField = (EditText) findViewById(R.id.edit_text_class_password);
        final EditText cutOffField = (EditText) findViewById(R.id.edit_text_cut_grade);
        final EditText sizeGroupsField = (EditText) findViewById(R.id.edit_text_size_groups);
        final EditText additionField = (EditText) findViewById(R.id.edit_text_addition);
        buttonOk = (Button) findViewById(R.id.button_save);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        buttonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isValid;

                progressBar.setVisibility(View.VISIBLE);

                UserClassControl userClassControl = UserClassControl.getInstance(CreateClassActivity.this);


                try {

                    isValid = confirmInformation(userClassControl,
                            classNameField, institutionField, passwordField,
                            cutOffField, sizeGroupsField, additionField);

                } catch (Exception e) {
                    e.printStackTrace();
                    return;
                }



                if (isValid) {

                    String className = classNameField.getText().toString();
                    String institution = institutionField.getText().toString();
                    String cutOff = cutOffField.getText().toString();
                    String password = passwordField.getText().toString();
                    String addition = additionField.getText().toString();
                    String sizeGroups = sizeGroupsField.getText().toString();


                    try {

                        SharedPreferences session = PreferenceManager.getDefaultSharedPreferences(
                                CreateClassActivity.this.getApplicationContext());
                        String userEmail = session.getString("userEmail", "");

                        userClassControl.validateCreateClass(className,
                                institution, Float.parseFloat(cutOff), password,
                                Float.parseFloat(addition), Integer.parseInt(sizeGroups), userEmail);
                    } catch (UserException e) {
                        e.printStackTrace();
                    }

                    progressBar.setVisibility(View.GONE);
                    onBackPressed();

                }


            }
        });

    }


    public boolean confirmInformation(UserClassControl userClassControl, EditText classNameField,
                                      EditText institutionField, EditText passwordField,
                                      EditText cutOffField, EditText sizeGroupsField,
                                      EditText additionField) throws UserException {

        String className = classNameField.getText().toString();
        String institution = institutionField.getText().toString();
        String cutOff = cutOffField.getText().toString();
        String password = passwordField.getText().toString();
        String addition = additionField.getText().toString();
        String sizeGroups = sizeGroupsField.getText().toString();

        boolean isValid = false;


        if(cutOff.isEmpty() == true || addition.isEmpty() == true || sizeGroups.isEmpty() == true){

            classNameField.setError("Preencha todos os campos!");
            isValid = false;
        }



        String errorMessage;

        progressBar.setVisibility(View.GONE);
        errorMessage = userClassControl.validateInformation(className, institution,
                cutOff, password, addition, sizeGroups);

        if (errorMessage.equals("Preencha todos os campos!")) {
            classNameField.setError("Preencha todos os campos!");
            isValid = false;
        }else if(errorMessage.equals("O nome da sala deve ter de 3 a 20 caracteres.")) {
            classNameField.requestFocus();
            classNameField.setError("O nome da sala deve ter de 3 a 20 caracteres.");
            isValid = false;
        }else if(errorMessage.equals("A senha deve ter entre 6 e 16 caracteres")) {
            passwordField.requestFocus();
            passwordField.setError("A senha deve ter entre 6 e 16 caracteres");
            isValid = false;
        }else if(errorMessage.equals("O tamanho do grupo nao pode ser zero.")) {
            sizeGroupsField.requestFocus();
            sizeGroupsField.setError("O tamanho do grupo nao pode ser zero.");
            isValid = false;
        }else if(errorMessage.equals("O acrescimo nao pode ser zero.")) {
            additionField.requestFocus();
            additionField.setError("O acrescimo nao pode ser zero.");
            isValid = false;
        }else if(errorMessage.equals("A nota de corte nao pode ser zero.")) {
            cutOffField.requestFocus();
            cutOffField.setError("A nota de corte nao pode ser zero.");
            isValid = false;
        }else if(errorMessage.equals("O nome da instituicao deve ter de 3 a 20 caracteres.")){
            institutionField.requestFocus();
            institutionField.setError("O nome da instituicao deve ter de 3 a 20 caracteres.");
            isValid = false;
        }else if (errorMessage.equals("Sucesso")) {

            isValid = true;
        }


        return isValid;
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }



    //Setting upp
    public void moveName(View view){
        ImageView image = (ImageView)findViewById(R.id.nameImageView);
        Animation animation1 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.move);
        image.startAnimation(animation1);
    }

    public void moveInstituition(View view){
        ImageView image = (ImageView)findViewById(R.id.institutionImageView);
        Animation animation1 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.move);
        image.startAnimation(animation1);
    }




}








