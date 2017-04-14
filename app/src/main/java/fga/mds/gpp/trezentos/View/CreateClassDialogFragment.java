package fga.mds.gpp.trezentos.View;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.DialogFragment;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import fga.mds.gpp.trezentos.Controller.UserClassControl;
import fga.mds.gpp.trezentos.Exception.UserException;
import fga.mds.gpp.trezentos.Model.UserAccount;
import fga.mds.gpp.trezentos.Model.UserClass;
import fga.mds.gpp.trezentos.R;


public class CreateClassDialogFragment extends DialogFragment {

    public CreateClassDialogFragment(){}
    public UserClass userClass;
    public UserAccount userAccount;

    public void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);


    }



    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){

        super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.fragment_createclassdialog, container);

        getDialog().setTitle("Criar Sala");

        Button buttonOk = (Button) view.findViewById(R.id.ok_create_button);
        Button buttonCancel = (Button) view.findViewById(R.id.cancel_create_button);


        final EditText classNameField = (EditText) view.findViewById(R.id.classname);
        final EditText institutionField = (EditText) view.findViewById(R.id.institution);
        final EditText passwordField = (EditText) view.findViewById(R.id.password);
        final EditText cutOffField = (EditText) view.findViewById(R.id.cutoff);
        final EditText sizeGroupsField = (EditText) view.findViewById(R.id.sizegroups);
        final EditText additionField = (EditText) view.findViewById(R.id.addition);

        buttonOk.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {


                    confirmInformation(userAccount,classNameField, institutionField, passwordField, cutOffField,
                            sizeGroupsField, additionField);

                    dismiss();
                }

        });

        buttonCancel.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        return (view);
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);



    }

    /*
    @Override
    public onAttach(Activity activity){
        super.onAttach(activity);



    }*/


    @Override
    public void onCancel(DialogInterface dialog){
        super.onCancel(dialog);
    }

    public void confirmInformation(UserAccount userAccount,EditText classNameField,
                                      EditText institutionField,
                                        EditText passwordField, EditText cutOffField,
                                      EditText sizeGroupsField, EditText additionField){


        String className  = classNameField.getText().toString();
        String institution = institutionField.getText().toString();
        String password = passwordField.getText().toString();
        String cutOff = cutOffField.getText().toString();
        String sizeGroups = sizeGroupsField.getText().toString();
        String addition = additionField.getText().toString();

        String errorMessage;

        try {

            UserClassControl userClassControl = new UserClassControl();
            userClassControl.validateCreatsClass(className, institution, cutOff, password,
                    userAccount.getIdUserAccount(), addition, sizeGroups);

        } catch (UserException userException) {

            errorMessage = userException.getMessage();

            if (errorMessage.equals("O nome da sala deve ter de 3 a 20 caracteres.")) {
                classNameField.requestFocus();
                classNameField.setError("O nome da sala deve ter de 3 a 20 caracteres.");
            }

            if (errorMessage.equals("A senha deve ter de 6 a 16 caracteres.")) {
                passwordField.requestFocus();
                passwordField.setError("A senha deve ter de 6 a 16 caracteres.");
            }

            if (errorMessage.equals("O tamanho do grupo nao pode ser zero.")) {
                sizeGroupsField.requestFocus();
                sizeGroupsField.setError("O tamanho do grupo nao pode ser zero.");
            }

            if (errorMessage.equals("O acrescimo nao pode ser zero.")) {
                additionField.requestFocus();
                additionField.setError("O acrescimo nao pode ser zero.");
            }
            if (errorMessage.equals("A nota de corte nao pode ser zero.")){
                cutOffField.requestFocus();
                cutOffField.setError("A nota de corte nao pode ser zero.");

            }
            if (errorMessage.equals("Preencha todos os campos!")){
                classNameField.setError("Preencha todos os campos!");

            }

        }





    }


}
