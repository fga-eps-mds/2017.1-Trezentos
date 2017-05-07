package fga.mds.gpp.trezentos.View;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
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


public class StudensFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public StudensFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static StudensFragment newInstance(String param1, String param2) {
        StudensFragment fragment = new StudensFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        final View view = inflater.inflate(R.layout.fragment_studens, container, false);



        final ArrayList<Student> students = new ArrayList<Student>();


        final ListView listView = (ListView) view.findViewById(R.id.list);

        students.add(new Student());

        ArrayAdapter arrayAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, students);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {



                Snackbar.make(view, "Click List", Snackbar.LENGTH_LONG).setAction("No action", null).show();
            }
        });

//        FloatingActionButton floatingActionButton = (FloatingActionButton) view.findViewById(R.id.floating_btn_add_student);
//        floatingActionButton.setOnClickListener(new FloatingActionButton.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                //openDialogFragment(v);
//                Toast.makeText(getActivity(),"Criar Estudante", Toast.LENGTH_SHORT).show();
//
//            }
//        });


        return view;

    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
