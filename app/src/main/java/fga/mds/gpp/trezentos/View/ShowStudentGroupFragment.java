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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

import fga.mds.gpp.trezentos.Controller.GroupController;
import fga.mds.gpp.trezentos.Controller.UserClassControl;
import fga.mds.gpp.trezentos.Controller.UserExamControl;
import fga.mds.gpp.trezentos.Model.Exam;

import fga.mds.gpp.trezentos.Model.Student;
import fga.mds.gpp.trezentos.Model.UserClass;
import fga.mds.gpp.trezentos.R;

import static com.facebook.FacebookSdk.getApplicationContext;

public class ShowStudentGroupFragment extends Fragment {

    private Exam userExam;
    public UserExamControl userExamControl;
    private UserClass userClass;
    public ProgressBar progressBar;
    private TextView groupTextView;
    private GroupController groupController;
    public String userEmail;
    private ArrayList<Student> groupAndGrades;

    public ShowStudentGroupFragment() {

    }

    public static ShowStudentGroupFragment newInstance(String param1, String param2) {
        ShowStudentGroupFragment fragment = new ShowStudentGroupFragment();
        Bundle args = new Bundle();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();

        if(userClass.getStudents().size() > 1) {
            new ServerOperation().execute();
        }
    }

    private void loadRecover() {

        SharedPreferences session = PreferenceManager.getDefaultSharedPreferences(getActivity());
        userEmail = session.getString("userEmail", "");

        Intent intent = getActivity().getIntent();
        userClass = (UserClass) intent.getSerializableExtra("Class");

        userExam = (Exam) intent.getSerializableExtra("Exam");

    }

    public void initListView() {
        if (getActivity() != null) {
            progressBar.setVisibility(View.GONE);

            RecyclerView recyclerView = (RecyclerView) getActivity().findViewById(R.id.recycler_student_group);
            recyclerView.setAdapter(new ShowStudentGroupFragment.StudentGroupAdapter
                    (groupAndGrades, getActivity().getApplicationContext(), recyclerView));

            final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            recyclerView.setLayoutManager(layoutManager);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_show_student_group, container, false);
        loadRecover();

        if(userClass.getStudents().size() > 1) {
            progressBar = (ProgressBar) view.findViewById(R.id.progress_bar_student_group);
            progressBar.setVisibility(View.VISIBLE);
        }

        return view;
    }

    class ServerOperation extends AsyncTask<String, Void, String> {

        public ServerOperation() {

        }

        @Override
        protected String doInBackground(String... params){

            HashMap<String, Double> firstGrades = groupController.getFirstGrades(userExam.getNameExam(),
                    userClass.getClassName(), userExam.getClassOwnerEmail());
//            HashMap<String, Integer> groups = groupController.getGroups(userExam.getNameExam(),
//                    userClass.getClassName(), userClass.getOwnerEmail());

//            ArrayList<Student> auxGroupsAndGrades =
//                    groupController.setSpecificGroupAndGrades
//                            (userEmail, firstGrades, groups);

//            //esse metodo bloco de codigo ordena os grupos por ordem de nota crescente
//            Collections.sort(auxGroupsAndGrades, new Comparator<Student>() {
//                @Override
//                public int compare(Student o1, Student o2) {
//                return o1.getFirstGrade() < o2.getFirstGrade() ? +1 :
//                         (o1.getFirstGrade() > o2.getFirstGrade() ? -1 : 0);
//                }
//            });

            //groupAndGrades = auxGroupsAndGrades;

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
        protected void onProgressUpdate(Void... values) {
        }
    }

    private class StudentGroupAdapter extends RecyclerView.Adapter
            implements View.OnClickListener {

        private final ArrayList<Student> groupAndGrades;
        private Context context;
        private RecyclerView recyclerView;

        public StudentGroupAdapter(ArrayList<Student> groupAndGrades
                , Context context, RecyclerView recyclerView) {
            this.groupAndGrades = groupAndGrades;
            this.context = context;
            this.recyclerView = recyclerView;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder
                (ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(context)
                    .inflate(R.layout.student_in_groups_item, parent, false);
            ShowStudentGroupFragment.ViewHolder holder
                    = new ShowStudentGroupFragment.ViewHolder(view);
            view.setOnClickListener(this);

            return holder;
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
            ShowStudentGroupFragment.ViewHolder holder
                    = (ShowStudentGroupFragment.ViewHolder) viewHolder;

            Student student = groupAndGrades.get(position);
            holder.studentEmail.setText(student.getEmail());
            holder.studentFirstGrade.setText(student.getFirstGrade().toString());
            holder.studentSecondGrade.setText(" - ");
        }

        @Override
        public int getItemCount() {
            return groupAndGrades.size();
        }

        @Override
        public long getItemId(int position) {
            return super.getItemId(position);
        }

        @Override
        public void onClick(View v) {
        }
    }

        public class ViewHolder extends RecyclerView.ViewHolder {

            final TextView studentEmail;
            final TextView studentFirstGrade;
            final TextView studentSecondGrade;

            public ViewHolder(View view) {
                super(view);
                studentEmail = (TextView) view.findViewById(R.id.student_email);
                studentFirstGrade = (TextView) view.findViewById(R.id.student_first_grade);
                studentSecondGrade = (TextView) view.findViewById(R.id.student_second_grade);
            }
        }

}