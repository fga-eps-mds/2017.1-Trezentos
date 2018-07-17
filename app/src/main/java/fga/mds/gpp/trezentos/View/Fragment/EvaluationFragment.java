package fga.mds.gpp.trezentos.View.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.ArrayList;
import fga.mds.gpp.trezentos.R;
import fga.mds.gpp.trezentos.View.ServerOperation.ServerOperationEvaluationFragment;

public class EvaluationFragment extends Fragment {

    private static EvaluationFragment fragment;
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

        initClasses();

        return view;
    }

    private void initClasses(){
        new ServerOperationEvaluationFragment(getActivity().getApplication(), className, examName).execute();
    }

}
