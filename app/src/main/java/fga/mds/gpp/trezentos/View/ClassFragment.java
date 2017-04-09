package fga.mds.gpp.trezentos.View;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
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
import fga.mds.gpp.trezentos.Model.UserAccount;
import fga.mds.gpp.trezentos.Model.UserClass;
import fga.mds.gpp.trezentos.R;


public class ClassFragment extends Fragment {


    public ArrayList<UserClass> userClasses;
    private OnFragmentInteractionListener mListener;
    private static CustomAdapter adapter;
    private FloatingActionButton floatingActionButton;
    private FragmentTransaction fragmentTransaction;

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

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){


        final View view = inflater.inflate(R.layout.fragment_class, container, false);
        final ListView listView = (ListView) view.findViewById(R.id.class_list_view);


        floatingActionButton = (FloatingActionButton) view.findViewById(R.id.class_image_button);


        UserClass userClass = new UserClass();
        UserAccount userAccount = new UserAccount();


        String className;
        String teacherName;
        String password;
        int sizeGroups;
        float cutOff;
        float addition;

        //className = userClass.getClassName();
        //teacherName = userAccount.getName();
        //password = userClass.getPassword();
        //sizeGroups = Integer.parseInt(userClass.getPassword());
        //cutOff = Float.parseFloat(String.valueOf(userClass.getCutOff()));
        //addition = Float.parseFloat(String.valueOf(userClass.getAddition()));

        userClasses = new ArrayList<>();

        try {



            userClasses.add(new UserClass("nome1","Fragellão", 4.5f, "123", 1, 1.5f));
            userClasses.add(new UserClass("nome1","Fragellão", 4.5f, "123", 1, 1.5f));
            userClasses.add(new UserClass("nome1","Fragellão", 4.5f, "123", 1, 1.5f));
            userClasses.add(new UserClass("nome1","Fragellão", 4.5f, "123", 1, 1.5f));
            userClasses.add(new UserClass("nome1","Fragellão", 4.5f, "123", 1, 1.5f));
            userClasses.add(new UserClass("nome1","Fragellão", 4.5f, "123", 1, 1.5f));
            userClasses.add(new UserClass("nome1","Fragellão", 4.5f, "123", 1, 1.5f));
            userClasses.add(new UserClass("nome1","Fragellão", 4.5f, "123", 1, 1.5f));
            userClasses.add(new UserClass("nome1","Fragellão", 4.5f, "123", 1, 1.5f));
            userClasses.add(new UserClass("nome1","Fragellão", 4.5f, "123", 1, 1.5f));
            userClasses.add(new UserClass("nome1","Fragellão", 4.5f, "123", 1, 1.5f));
            userClasses.add(new UserClass("nome1","Fragellão", 4.5f, "123", 1, 1.5f));



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

        floatingActionButton.setOnClickListener(new FloatingActionButton.OnClickListener() {
            @Override
            public void onClick(View v) {

                openDialogFragment(v);


            }
        });


        return view;
    }

    public void openDialogFragment (View view){

        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        CreateClassDialogFragment ccdf = new CreateClassDialogFragment();
        ccdf.show(fragmentTransaction, "dialog");

    }

    public void turnOffDialogFragment(){
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        CreateClassDialogFragment ccdf = (CreateClassDialogFragment) getFragmentManager()
                .findFragmentByTag("dialog");
        if(ccdf != null){
            ccdf.dismiss();
            fragmentTransaction.remove(ccdf);
        }
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }




    public interface OnFragmentInteractionListener {

        void onFragmentInteraction(Uri uri);
    }


}
