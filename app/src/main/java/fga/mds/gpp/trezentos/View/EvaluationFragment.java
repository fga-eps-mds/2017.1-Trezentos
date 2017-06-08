package fga.mds.gpp.trezentos.View;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;

import fga.mds.gpp.trezentos.Controller.UserClassControl;
import fga.mds.gpp.trezentos.Controller.UserExamControl;
import fga.mds.gpp.trezentos.Model.Exam;
import fga.mds.gpp.trezentos.Model.Groups;
import fga.mds.gpp.trezentos.Model.UserClass;
import fga.mds.gpp.trezentos.R;

import static com.facebook.FacebookSdk.getApplicationContext;

public class EvaluationFragment extends Fragment {

    private static EvaluationFragment fragment;
    private ArrayList<UserClass> userClasses;
    private UserClass userClass;
    private String email;
    private UserClassControl userClassControl;
    private Exam userExam;
    private Groups userGroups;
    private TextView className;
    private TextView examName;

    public static EvaluationFragment getInstance() {

        if(fragment == null){
            fragment = new EvaluationFragment();
        }
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_evaluation, container, false);

        className = (TextView) view.findViewById(R.id.student_class);
        examName = (TextView) view.findViewById(R.id.student_exam);

        //Generates students static
        ArrayList<String> students;
//        students.add("Arthur Diniz");
//        students.add("Cafe");

        initClasses();

        //Pegar as classes em que os grupos ja foram formados
        //Definir uma estrutura para mostrar quem e ajudante e quem e ajudado
        //Colocar ajudantes ou ajudados em uma recyclerview com o layout ja feito

        return view;
    }

    private void initClasses(){
        new ServerOperationEvaluationFragment(getActivity().getApplication(), className, examName).execute();
    }

}
