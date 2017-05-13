package fga.mds.gpp.trezentos.View;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.net.sip.SipAudioCall;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;

import java.util.ArrayList;

import fga.mds.gpp.trezentos.Controller.UserClassControl;
import fga.mds.gpp.trezentos.Controller.UserExamControl;
import fga.mds.gpp.trezentos.Model.Exam;

import fga.mds.gpp.trezentos.Model.UserClass;
import fga.mds.gpp.trezentos.R;

public class ExamsFragment extends Fragment{

    public ArrayList<Exam> userExams;
    public ListView listView;
    public ArrayAdapter arrayAdapter;
    public UserExamControl userExamControl;
    private UserClass userClass;
    public ProgressBar progressBar;
    public  String userEmail;

    public ExamsFragment() {

    }


    public static ExamsFragment newInstance(String param1, String param2) {
        ExamsFragment fragment = new ExamsFragment();
        Bundle args = new Bundle();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onResume(){
        super.onResume();

    }

    private void loadRecover(){

        SharedPreferences session = PreferenceManager.getDefaultSharedPreferences(getActivity());
        userEmail = session.getString("userEmail","");

        Intent intent = getActivity().getIntent();
        userClass = (UserClass) intent.getSerializableExtra("Class");

        userExamControl = UserExamControl.getInstance(getActivity());

    }

    public void initListView(){
        arrayAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, userExams);
        arrayAdapter.notifyDataSetChanged();

        listView = (ListView) getActivity().findViewById(R.id.list);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Snackbar.make(view, "Click List", Snackbar.LENGTH_LONG).setAction("No action", null).show();
            }
        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_exams, container, false);

        progressBar = (ProgressBar) view.findViewById(R.id.progressBarExam);
        progressBar.setVisibility(View.VISIBLE);

        new ServerOperation().execute();

        return view;
    }

    public void openDialogFragment(View view){

        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        CreateExamDialogFragment ccdf = new CreateExamDialogFragment();
        ccdf.show(fragmentTransaction, "dialog");

    }



    class ServerOperation extends AsyncTask<String, Void, String> {

        public ServerOperation(){}

        @Override
        protected String doInBackground(String... params) {

            userExams = userExamControl.getExamsFromUser(userEmail, userClass.getClassName());
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            progressBar.setVisibility(View.GONE);
            initListView();

        }

        @Override
        protected void onPreExecute() {
            loadRecover();
        }

        @Override
        protected void onProgressUpdate(Void... values) {}
    }
}
