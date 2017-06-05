package fga.mds.gpp.trezentos.View;

import android.content.Context;
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
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

import fga.mds.gpp.trezentos.Controller.UserClassControl;
import fga.mds.gpp.trezentos.Controller.UserExamControl;
import fga.mds.gpp.trezentos.Model.Exam;

import fga.mds.gpp.trezentos.Model.UserClass;
import fga.mds.gpp.trezentos.R;

public class ExamsFragment extends Fragment{

    public ArrayList<Exam> userExams;
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
        new ServerOperation().execute();
    }

    private void loadRecover(){

        SharedPreferences session = PreferenceManager.getDefaultSharedPreferences(getActivity());
        userEmail = session.getString("userEmail","");

        Intent intent = getActivity().getIntent();
        userClass = (UserClass) intent.getSerializableExtra("Class");

        userExamControl = UserExamControl.getInstance(getActivity());

        ArrayList <HashMap<String, Double>> aluno = new ArrayList<HashMap<String, Double>>();

    }

    public void initListView(){

        if (getActivity() != null) {
            progressBar.setVisibility(View.GONE);

            RecyclerView recyclerView = (RecyclerView) getActivity().findViewById(R.id.recyclerExam);
            recyclerView.setAdapter(new ExamsFragment.Adapter(userExams, getActivity().getApplicationContext(), recyclerView));

            final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            recyclerView.setLayoutManager(layoutManager);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_exams, container, false);

        progressBar = (ProgressBar) view.findViewById(R.id.progressBarExam);
        progressBar.setVisibility(View.VISIBLE);

        new ServerOperation().execute();
        return view;
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

    private class Adapter extends RecyclerView.Adapter implements View.OnClickListener {

        private final ArrayList<Exam> exams;
        private Context context;
        private  RecyclerView recyclerView;


        public Adapter(ArrayList<Exam> exams, Context context,  RecyclerView recyclerView) {
            this.exams = exams;
            this.context = context;
            this.recyclerView = recyclerView;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(context).inflate(R.layout.exam_item, parent, false);
            ExamsFragment.ViewHolder holder = new ExamsFragment.ViewHolder(view);
            view.setOnClickListener(this);

            return holder;
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
            ExamsFragment.ViewHolder holder = (ExamsFragment.ViewHolder) viewHolder;

            Exam exam  = exams.get(position) ;
            holder.className.setText(exam.getNameExam());
        }

        @Override
        public int getItemCount() {
            return exams.size();
        }

        @Override
        public long getItemId(int position) {
            return super.getItemId(position);
        }


        @Override
        public void onClick(View v) {
            int itemPosition = recyclerView.getChildLayoutPosition(v);
            Exam exam = exams.get(itemPosition);

            Intent goExam = new  Intent(context, ExamActivity.class);
            Bundle extras = new Bundle();
            extras.putSerializable("Exam", exam);
            extras.putSerializable("Class", userClass);
            goExam.putExtras(extras);
            goExam.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(goExam);
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        final TextView className;


        public ViewHolder(View view) {
            super(view);
            className = (TextView) view.findViewById(R.id.class_name);

        }
    }

}



