package fga.mds.gpp.trezentos.View;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;

import fga.mds.gpp.trezentos.Exception.UserException;
import fga.mds.gpp.trezentos.Model.CustomAdapter;
import fga.mds.gpp.trezentos.Model.UserClass;
import fga.mds.gpp.trezentos.R;


public class ClassFragment extends Fragment {


    public ArrayList<UserClass> userClasses;
    private OnFragmentInteractionListener mListener;
    private static CustomAdapter adapter;

    public ClassFragment() {

    }

    public static ClassFragment newInstance(String param1, String param2) {
        ClassFragment fragment = new ClassFragment();
        Bundle args = new Bundle();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }


    // Here, the program will show all classes registreds into classe.json

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        final View view = inflater.inflate(R.layout.fragment_class, container, false);
        final ListView listView = (ListView) view.findViewById(R.id.class_list_view);

        final ImageButton imageButton = (ImageButton) view.findViewById(R.id.class_image_button);

        userClasses = new ArrayList<>();

        try {
            userClasses.add(new UserClass("nome1", "instituicao1", 1, "123", 001, 4.5f));
            userClasses.add(new UserClass("nome2","instituicao2", 2, "312", 002, 5.5f));

        } catch (UserException e) {
            e.printStackTrace();
        }

        adapter= new CustomAdapter(userClasses,getActivity().getApplicationContext());

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //UserClass userClasses= userClasses.get(position);

                //Snackbar.make(view, dataModel.getName()+"\n"+dataModel.getType()+" API: "+dataModel.getVersion_number(), Snackbar.LENGTH_LONG)
                //        .setAction("No action", null).show();
            }
        });

        imageButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {



            }
        });


        return view;
    }


    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    public void openCreateClass(DialogFragment dialogFragment){

     //   AlertDialog.Builder builder = new AlertDialog.Builder();

    }


    public interface OnFragmentInteractionListener {

        void onFragmentInteraction(Uri uri);
    }
}
