package fga.mds.gpp.trezentos.View;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.DialogFragment;
import android.text.Editable;
import android.util.Log;
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

    public CreateClassDialogFragment() {
    }

    public UserClass userClass;
    public UserAccount userAccount;

    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);


    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

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

        buttonOk.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean isValid;

                UserClassControl userClassControl = UserClassControl.getInstance(getActivity());


                try {

                    isValid = confirmInformation(userClassControl,
                            classNameField, institutionField, passwordField,
                            cutOffField, sizeGroupsField,
                            additionField);
/*                    userClass.setClassName(classNameField.getText().toString());
                    userClass.setInstitution(institutionField.getText().toString());
                    userClass.setPassword(passwordField.getText().toString());
                    userClass.setCutOff(Float.parseFloat(cutOffField.getText().toString()));
                    userClass.setSizeGroups(Integer.parseInt(sizeGroupsField.getText().toString()));
                    userClass.setAddition(Float.parseFloat(additionField.getText().toString()));*/
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
                    //userClass.setInstitution(institutionField.getText().toString());

                    try {
                        userClass = new UserClass(className, institution, Float.parseFloat(cutOff),
                                password, Float.parseFloat(addition), Integer.parseInt(sizeGroups));
                        userClassControl.validateCreateClass(userClass.getClassName(),
                                userClass.getInstitution(), userClass.getCutOff(), userClass.getPassword(),
                                userClass.getAddition(), userClass.getSizeGroups());
                    } catch (UserException e) {
                        e.printStackTrace();
                    }

                    dismiss();
                }
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
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    /*
    @Override
    public onAttach(Activity activity){
        super.onAttach(activity);



    }*/


    @Override
    public void onCancel(DialogInterface dialog) {
        super.onCancel(dialog);
    }

    public boolean confirmInformation(UserClassControl userClassControl,
                                         EditText classNameField,
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

        String errorMessage;


            errorMessage = userClassControl.validateInformation(className, institution, cutOff, password,
                    addition, sizeGroups);

              if (errorMessage.equals("Preencha todos os campos!")) {
                  classNameField.setError("Preencha todos os campos!");
                isValid = false;
            }
            if (errorMessage.equals("O nome da sala deve ter de 3 a 20 caracteres.")) {
                classNameField.requestFocus();
                classNameField.setError("O nome da sala deve ter de 3 a 20 caracteres.");
                isValid = false;
            }

            if(errorMessage.equals("A instituição deve ter de 2 a 30 caracteres.")){
                institutionField.requestFocus();
                passwordField.setError("A instituição deve ter de 2 a 30 caracteres.");
                isValid = false;
            }

            if (errorMessage.equals("A senha deve ter de 6 a 16 caracteres.")) {
                passwordField.requestFocus();
                institutionField.setError("A senha deve ter de 6 a 16 caracteres.");
                isValid = false;
            }

            if (errorMessage.equals("O tamanho do grupo nao pode ser zero.")) {
                sizeGroupsField.requestFocus();
                sizeGroupsField.setError("O tamanho do grupo nao pode ser zero.");
                isValid = false;
            }

            if (errorMessage.equals("O acrescimo não pode ser zero.")) {
                additionField.requestFocus();
                additionField.setError("O acrescimo não pode ser zero.");
                isValid = false;
            }
            if (errorMessage.equals("A nota de corte nao pode ser zero.")) {
                cutOffField.requestFocus();
                cutOffField.setError("A nota de corte nao pode ser zero.");
                isValid = false;
            }


        return isValid;
    }
}


