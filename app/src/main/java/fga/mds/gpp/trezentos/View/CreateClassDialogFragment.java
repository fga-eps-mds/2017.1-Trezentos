package fga.mds.gpp.trezentos.View;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import fga.mds.gpp.trezentos.Controller.UserClassControl;
import fga.mds.gpp.trezentos.Exception.UserException;
import fga.mds.gpp.trezentos.Model.UserAccount;
import fga.mds.gpp.trezentos.Model.UserClass;
import fga.mds.gpp.trezentos.R;


public class CreateClassDialogFragment extends DialogFragment{
    public UserClass userClass;
    public UserAccount userAccount;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){

        getDialog().setTitle("Criar Sala");
        super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.fragment_createclassdialog, container);
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
            public void onClick(View v){
                boolean isValid;
                UserClassControl userClassControl = UserClassControl.getInstance(getActivity());
                try{

                    isValid = confirmInformation(userClassControl,
                            classNameField, institutionField, passwordField,
                                cutOffField, sizeGroupsField, additionField);

                }catch (Exception e){
                    e.printStackTrace();

                    return;
                }

                if (isValid){
                    String className = classNameField.getText().toString();
                    String institution = institutionField.getText().toString();
                    String cutOff = cutOffField.getText().toString();

                    String password = passwordField.getText().toString();
                    String addition = additionField.getText().toString();
                    String sizeGroups = sizeGroupsField.getText().toString();

                    try {
                        SharedPreferences session = PreferenceManager.getDefaultSharedPreferences(
                                getActivity().getApplicationContext());
                        String userEmail = session.getString("userEmail", "");

                        userClassControl.validateCreateClass(className,
                                institution, Float.parseFloat(cutOff), password,
                                Float.parseFloat(addition), Integer.parseInt(sizeGroups), userEmail);
                    }catch (UserException e){
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

        return view;
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onCancel(DialogInterface dialog){
        super.onCancel(dialog);
    }

    public boolean confirmInformation(UserClassControl userClassControl, EditText classNameField,
                                      EditText institutionField, EditText passwordField,
                                            EditText cutOffField, EditText sizeGroupsField,
                                                EditText additionField) throws UserException{

        String className = classNameField.getText().toString();
        String institution = institutionField.getText().toString();
        String cutOff = cutOffField.getText().toString();

        String password = passwordField.getText().toString();
        String addition = additionField.getText().toString();
        String sizeGroups = sizeGroupsField.getText().toString();
        boolean isValid = false;

        if(cutOff.isEmpty() == true || addition.isEmpty() == true || sizeGroups.isEmpty() == true){
            classNameField.setError("Preencha todos os campos!");
        }

        String errorMessage;
            errorMessage = userClassControl.validateInformation(className, institution,
                    cutOff, password, addition, sizeGroups);

            if (errorMessage.equals(getResources().getString(R.string.msg_empty_space_error_message))){
                  classNameField.setError(getResources().getString(R.string.msg_empty_space_error_message));
            }
            else if (errorMessage.equals(getResources().getString(R.string.msg_class_name_case_error_message))){
                classNameField.requestFocus();
                classNameField.setError(getResources().getString(R.string.msg_class_name_case_error_message));

            }
            else if (errorMessage.equals(getResources().getString(R.string.msg_len_password_error_message))){
                passwordField.requestFocus();
                passwordField.setError(getResources().getString(R.string.msg_len_password_error_message));

            }
            else if (errorMessage.equals(getResources().getString(R.string.msg_len_size_group_error_message))){
                sizeGroupsField.requestFocus();
                sizeGroupsField.setError(getResources().getString(R.string.msg_len_size_group_error_message));

            }
            else if (errorMessage.equals(getResources().getString(R.string.msg_len_addition_error_message))){
                additionField.requestFocus();
                additionField.setError(getResources().getString(R.string.msg_len_addition_error_message));

            }
            else if (errorMessage.equals(getResources().getString(R.string.msg_len_cut_off_error_message))){
                cutOffField.requestFocus();
                cutOffField.setError(getResources().getString(R.string.msg_len_cut_off_error_message));

            }
            else if(errorMessage.equals(getResources().getString(R.string.msg_institution_case_error_message))){
                institutionField.requestFocus();
                institutionField.setError(getResources().getString(R.string.msg_institution_case_error_message));

            }
            else if (errorMessage.equals(getResources().getString(R.string.msg_class_success))){
                    isValid = true;
            }

        return isValid;
    }
}


