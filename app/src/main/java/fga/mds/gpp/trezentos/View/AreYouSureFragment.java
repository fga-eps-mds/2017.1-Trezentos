package fga.mds.gpp.trezentos.View;

import android.app.DialogFragment;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.util.HashMap;

import fga.mds.gpp.trezentos.Controller.GroupController;
import fga.mds.gpp.trezentos.Model.Exam;
import fga.mds.gpp.trezentos.Model.UserClass;
import fga.mds.gpp.trezentos.R;

public class AreYouSureFragment extends DialogFragment implements View.OnClickListener {
    private HashMap<String, Double> firstGrades;
    private UserClass userClass;
    private Exam exam;
    private String email;
    private Button cancelButton;
    private Button confirmButton;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        getBundleArguments();

        final View view = inflater.inflate(R.layout.fragment_are_you_sure, container, false);
        initButtons(view);

        cancelButton.setOnClickListener(this);
        confirmButton.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.cancel_are_you_sure:{
                dismiss();
                break;
            }
            case R.id.confirm_are_you_sure:{
                if(firstGrades == null || firstGrades.isEmpty()){
                    Toast.makeText(getActivity(), "Preencha as notas antes de montar os grupos",
                            Toast.LENGTH_LONG).show();

                } else {
                    getSharedPreferences();
                    new GroupController().sortAndSaveGroups(firstGrades, userClass, email, exam);
                    turnOffAreYouSureFragment();
                }
            }
        }
    }

    private void getSharedPreferences(){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        email = preferences.getString("userEmail", "");
    }

    private void turnOffAreYouSureFragment(){
        Intent intent = getActivity().getIntent();
        getActivity().finish();
        startActivity(intent);
    }

    private void getBundleArguments(){
        firstGrades = (HashMap<String, Double>) getArguments().getSerializable("firstGrades");
        userClass = (UserClass) getArguments().getSerializable("userClass");
        exam = (Exam) getArguments().getSerializable("exam");
    }

    private void initButtons(View view){
        cancelButton = (Button) view.findViewById(R.id.cancel_are_you_sure);
        confirmButton = (Button) view.findViewById(R.id.confirm_are_you_sure);
    }
}