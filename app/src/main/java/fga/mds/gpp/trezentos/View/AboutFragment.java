package fga.mds.gpp.trezentos.View;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import fga.mds.gpp.trezentos.Exception.UserException;
import fga.mds.gpp.trezentos.Model.About;
import fga.mds.gpp.trezentos.Model.AboutAdapter;
import fga.mds.gpp.trezentos.R;


public class AboutFragment extends Fragment {

    public ArrayList<About> about;
    private static AboutAdapter adapter;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private OnFragmentInteractionListener mListener;

    public AboutFragment() {

    }


    public static AboutFragment newInstance(String param1, String param2) {
        AboutFragment fragment = new AboutFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_about, container, false);
        final ListView listView = (ListView) view.findViewById(R.id.about_list_view);

        about = new ArrayList<>();

        try {
            about.add(new About("Trezentos", "TEDx"));
            about.add(new About("nome2","descrição2"));

        } catch (UserException e) {
            e.printStackTrace();
        }

        adapter = new AboutAdapter(about,getActivity().getApplicationContext());
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //UserClass userClasses= userClasses.get(position);

                //Snackbar.make(view, dataModel.getName()+"\n"+dataModel.getType()+" API: "+dataModel.getVersion_number(), Snackbar.LENGTH_LONG)
                //        .setAction("No action", null).show();
            }
        });
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
