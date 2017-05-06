package fga.mds.gpp.trezentos.View;


import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import fga.mds.gpp.trezentos.Controller.UserExamControl;
import fga.mds.gpp.trezentos.Exception.UserException;
import fga.mds.gpp.trezentos.Model.UserAccount;
import fga.mds.gpp.trezentos.Model.UserClass;
import fga.mds.gpp.trezentos.R;


public class CreateExamDialogFragment extends DialogFragment{

    public CreateExamDialogFragment(){
    }

    public UserClass userClass;
    public UserAccount userAccount;
    public String userClassName;
    public String classOwnnerEmail;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_create_exam_dialog, container);

        getDialog().setTitle("Criar Sala");
        Button buttonOk = (Button) view.findViewById(R.id.ok_create_button);
        Button buttonCancel = (Button) view.findViewById(R.id.cancel_create_button);

        final EditText examNameField = (EditText) view.findViewById(R.id.examname);

        buttonOk.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isValid;
                UserExamControl userExamControl = UserExamControl.getInstance(getActivity());

                try {
                    isValid = confirmInformation(userExamControl, examNameField, userClassName, classOwnnerEmail);
                }catch (Exception e){
                    e.printStackTrace();
                    return;
                }

                if (isValid){
                    String examName = examNameField.getText().toString();
                    try{

                        userExamControl.validateCreateExam(examName, userClassName, classOwnnerEmail);
                    }catch (UserException e){
                        e.printStackTrace();
                    }

                    dismiss();
                }
            }
        });

        buttonCancel.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v){
                dismiss();
            }
        });
        return (view);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onCancel(DialogInterface dialog){
        super.onCancel(dialog);
    }

    public boolean confirmInformation(UserExamControl userExamControl, EditText examNameField,
                                      String userClassName, String classOwnnerEmail)
                                        throws UserException{
    String examName = examNameField.getText().toString();
        boolean isValid = false;

        String errorMessage;
        errorMessage = userExamControl.validateInformation(examName, userClassName, classOwnnerEmail);

        if (errorMessage.equals(getResources().getString(R.string.msg_empty_space_error_message))){
            examNameField.setError(getResources().getString(R.string.msg_empty_space_error_message));
        }

        if (errorMessage.equals(getResources().getString(R.string.msg_class_name_case_error_message))){
            examNameField.requestFocus();
            examNameField.setError(getResources().getString(R.string.msg_class_name_case_error_message));
        }

        else if (errorMessage.equals(getResources().getString(R.string.msg_class_success))){
            isValid = true;
        }

        return isValid;
    }
}
