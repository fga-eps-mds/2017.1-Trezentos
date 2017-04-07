package fga.mds.gpp.trezentos.View;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
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
            about.add(new About("Site","Método Trezentos"));
            about.add(new About("Trezentos", "TEDx Universidade de Brasilia"));
            about.add(new About("Metodologia Trezentos", "Entenda o método em 3 minutos"));
            about.add(new About("Trezentos para professores", "Detalhes técnicos"));
            about.add(new About("Artigo sobre o Trezentos",""));
            about.add(new About("Record", "Reportagem sobre o Trezentos"));
            about.add(new About("UnBTV","Reportagem sobre o Trezentos"));

        } catch (UserException e) {
            e.printStackTrace();
        }

        /*final WebView webView = (WebView) view.findViewById(R.id.about_web_view);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setSupportZoom(false);*/

        adapter = new AboutAdapter(about,getActivity().getApplicationContext());
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                /*switch (position){
                    case 0:
                        webView.loadUrl("http://www.google.com");

                }*/

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
