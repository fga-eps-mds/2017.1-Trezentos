package fga.mds.gpp.trezentos.View;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.ArrayList;
import fga.mds.gpp.trezentos.R;

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
