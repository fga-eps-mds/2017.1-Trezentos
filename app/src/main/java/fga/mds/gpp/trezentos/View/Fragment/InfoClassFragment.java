package fga.mds.gpp.trezentos.View.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;
import fga.mds.gpp.trezentos.Model.UserClass;
import fga.mds.gpp.trezentos.R;


public class InfoClassFragment extends Fragment {

    private UserClass userClass;
    private TextView className;
    private TextView institution;
    private TextView classPassword;
    private TextView cutGrade;
    private TextView groupsSize;
    private TextView addition;

    private View view;

    public InfoClassFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_info_class
                , container, false); // Inflate the layout for this fragment

        recoverInformation();
        initFields();
        initFillFields();

        return view;
    }
    private void recoverInformation(){
        Intent intent = getActivity().getIntent();
        userClass = (UserClass) intent.getSerializableExtra("Class");
    }

    private void initFields(){
        className = view.findViewById(R.id.text_view_class_name_x);
        institution = view.findViewById(R.id.text_view_institution_x);
        classPassword = view.findViewById(R.id.text_view_class_password_x);
        cutGrade = view.findViewById(R.id.text_view_cut_grade_x);
        groupsSize = view.findViewById(R.id.text_view_size_groups_x);
        addition = view.findViewById(R.id.text_view_addition_x);
    }

    private void initFillFields(){
        className.setText(userClass.getClassName());
        institution.setText(userClass.getInstitution());
        classPassword.setText(userClass.getPassword());
        cutGrade.setText(String.valueOf(userClass.getCutOff()));
        groupsSize.setText(String.valueOf(userClass.getSizeGroups()));
        addition.setText(String.valueOf(userClass.getAddition()));
    }
}