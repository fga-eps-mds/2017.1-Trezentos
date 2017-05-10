package fga.mds.gpp.trezentos.View;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ListView;
import java.util.ArrayList;
import fga.mds.gpp.trezentos.Exception.UserException;
import fga.mds.gpp.trezentos.Model.About;
import fga.mds.gpp.trezentos.R;

public class AboutFragment extends Fragment{

    private ArrayList<About> about;
    private static AboutAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_about, container, false);
        final ListView listView = (ListView) view.findViewById(R.id.about_list_view);

        aboutItem();

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                openUrl(position);
            }
        });

        return view;
    }
    
    public void aboutItem(){
        about = new ArrayList<>();

        try {
            about.add(new About(getResources().getString(R.string.about_site),
                    getResources().getString(R.string.about_metodo_trezentos)));
            about.add(new About(getResources().getString(R.string.about_trezentos),
                    getResources().getString(R.string.about_tedx)));
            about.add(new About(getResources().getString(R.string.about_metodologia),
                    getResources().getString(R.string.about_entenda_metodo)));
            about.add(new About(getResources().getString(R.string.about_para_professores),
                    getResources().getString(R.string.about_detalhes)));
            about.add(new About(getResources().getString(R.string.about_artigo),
                    getResources().getString(R.string.about_pdf_download)));
            about.add(new About(getResources().getString(R.string.about_record),
                    getResources().getString(R.string.about_reportagem)));
            about.add(new About(getResources().getString(R.string.about_unbtv),
                    getResources().getString(R.string.about_reportagem)));
        }

        catch (UserException e){
            e.printStackTrace();
        }
        adapter = new AboutAdapter(about,getActivity().getApplicationContext());
    }

    public void openUrl(int position){
        Intent myWebLink = new Intent(android.content.Intent.ACTION_VIEW);

        if(position == 0){
            myWebLink.setData(Uri.parse("http://metodo300.com"));
            startActivity(myWebLink);
        }
        else if(position == 1){
            myWebLink.setData(Uri.parse("https://youtu.be/gay6TYwVwf4"));
            startActivity(myWebLink);
        }
        else if(position == 2){
            myWebLink.setData(Uri.parse("https://youtu.be/s0g1AgGFP5k"));
            startActivity(myWebLink);
        }
        else if(position == 3){
            myWebLink.setData(Uri.parse("https://youtu.be/QLJtwsX8NqU"));
            startActivity(myWebLink);
        }
        else if(position == 4){
            myWebLink.setData(Uri.parse("http://www.scielo.br/pdf/er/n63/1984-0411-er-63-00253.pdf"));
            startActivity(myWebLink);
        }
        else if(position == 5){
            myWebLink.setData(Uri.parse("https://youtu.be/zQsaUjWw330"));
            startActivity(myWebLink);
        }
        else{
            myWebLink.setData(Uri.parse("https://youtu.be/7cfNcn-zge0"));
            startActivity(myWebLink);
        }
    }
}