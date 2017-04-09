package fga.mds.gpp.trezentos.View;

import android.content.DialogInterface;
import android.os.Bundle;
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
import fga.mds.gpp.trezentos.Model.UserClass;
import fga.mds.gpp.trezentos.R;


public class CreateClassDialogFragment extends DialogFragment {

    public CreateClassDialogFragment(){}
    public UserClass userClass;

    public void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);


    }



    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){

        super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.fragment_createclassdialog, container);

        getDialog().setTitle("Criar salas");

        Button buttonOk = (Button) view.findViewById(R.id.ok_create_button);
        Button buttonCancel = (Button) view.findViewById(R.id.cancel_create_button);


        final EditText classNameField = (EditText) view.findViewById(R.id.classname);
        final EditText passwordField = (EditText) view.findViewById(R.id.password);
        final EditText cutOffField = (EditText) view.findViewById(R.id.cutoff);
        final EditText sizeGroupsField = (EditText) view.findViewById(R.id.sizegroups);
        final EditText additionField = (EditText) view.findViewById(R.id.addition);

        buttonOk.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                //Criar sala aqui

                UserClassControl userClassControl = new UserClassControl();

                userClass = new UserClass();
                try{
                    userClass.setClassName(classNameField.getText().toString());
                    userClass.setPassword(passwordField.getText().toString());
                    userClass.setCutOff(Float.parseFloat(cutOffField.getText().toString()));
                    userClass.setSizeGroups(Integer.parseInt(sizeGroupsField.getText().toString()));
                    userClass.setAddition(Float.parseFloat(additionField.getText().toString()));
                }
                catch (Exception e){
                    confirmInformation(userClassControl, classNameField, passwordField, cutOffField,
                            sizeGroupsField, additionField);
                    return;
                }

                boolean isValid;

                isValid = confirmInformation(userClassControl, classNameField, passwordField, cutOffField,
                        sizeGroupsField, additionField);


                if(isValid){

                    

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

    public boolean confirmInformation(UserClassControl userClassControl, EditText className,
                                      EditText password, EditText cutOff,
                                      EditText sizeGroups, EditText addition){

        boolean isValid;

        try {
            userClassControl.validateInformation(userClass);

            isValid = true;

        } catch (UserException userException) {

            String errorMessage = userException.getMessage();

            if(errorMessage.equals("Preencha todos os campos.")){
                className.setError("Preencha todos os campos.");

            }

            if(errorMessage.equals("O nome da sala deve ter de 3 a 20 caracteres.")){
                className.requestFocus();
                className.setError("O nome da sala deve ter de 3 a 20 caracteres.");
            }

            if(errorMessage.equals("A senha deve ter de 6 a 16 caracteres.")){
                password.requestFocus();
                password.setError("A senha deve ter de 6 a 16 caracteres.");

            }

            if(errorMessage.equals("O acréscimo deve ser maior que 0.")){
                addition.requestFocus();
                addition.setError("O acréscimo deve ser maior que 0.");

            }

            isValid = false;

        }

        return isValid;
    }


}
