package fga.mds.gpp.trezentos.View;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import fga.mds.gpp.trezentos.Model.Student;
import fga.mds.gpp.trezentos.R;


public class StudensFragment extends Fragment{

    public StudensFragment(){
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_studens, container, false);

        final ArrayList<Student> students = new ArrayList<Student>();
        final ListView listView = (ListView) view.findViewById(R.id.list);

        students.add(new Student());

        ArrayAdapter arrayAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, students);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){

                Snackbar.make(view, "Click List", Snackbar.LENGTH_LONG)
                        .setAction("No action", null).show();
            }
        });

        return view;

    }
}
