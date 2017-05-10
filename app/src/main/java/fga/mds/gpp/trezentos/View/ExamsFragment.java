package fga.mds.gpp.trezentos.View;

import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.ArrayList;
import fga.mds.gpp.trezentos.Controller.UserExamControl;
import fga.mds.gpp.trezentos.Model.Exam;
import fga.mds.gpp.trezentos.R;

public class ExamsFragment extends Fragment{

    public ArrayList<Exam> userExams;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onResume(){
        super.onResume();

        loadClasses();
    }

    private void loadClasses(){
        SharedPreferences session = PreferenceManager.getDefaultSharedPreferences(getActivity());
        UserExamControl userExamControl = UserExamControl.getInstance(getActivity());

        userExams = userExamControl.getExamsFromUser("");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){

        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_exams, container, false);

        userExams = new ArrayList<Exam>();

        final ListView listView = (ListView) view.findViewById(R.id.list);

        ArrayAdapter arrayAdapter = new ArrayAdapter(getActivity(),
                android.R.layout.simple_list_item_1, userExams);

        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){

                Snackbar.make(view, "Click List", Snackbar.LENGTH_LONG).
                        setAction("No action", null).show();
            }
        });

        FloatingActionButton floatingActionButton = (FloatingActionButton) view.
                findViewById(R.id.floating_btn_add_exams);

        floatingActionButton.setOnClickListener(new FloatingActionButton.OnClickListener(){
            @Override
            public void onClick(View v){
                openDialogFragment(v);
            }
        });

        return view;
    }



    public void openDialogFragment(View view){

        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        CreateExamDialogFragment ccdf = new CreateExamDialogFragment();
        ccdf.show(fragmentTransaction, "dialog");

    }
}
