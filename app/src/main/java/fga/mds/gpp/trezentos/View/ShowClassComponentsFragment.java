package fga.mds.gpp.trezentos.View;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import java.util.ArrayList;

import fga.mds.gpp.trezentos.Model.Exam;
import fga.mds.gpp.trezentos.Model.UserAccount;
import fga.mds.gpp.trezentos.Controller.UserExamControl;
import fga.mds.gpp.trezentos.Model.Exam;
import fga.mds.gpp.trezentos.Model.UserClass;
import fga.mds.gpp.trezentos.R;

import static fga.mds.gpp.trezentos.R.id.gradeLayout;
import static fga.mds.gpp.trezentos.R.id.student_email;
import static fga.mds.gpp.trezentos.R.id.student_name;
import static fga.mds.gpp.trezentos.R.id.text_view_grade;


public class ShowClassComponentsFragment extends Fragment {

    private ArrayList<String> students;
    private UserClass userClass;
    private Exam userExam;


    public ShowClassComponentsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Intent intent = getActivity().getIntent();
        userClass = (UserClass) intent.getSerializableExtra("Class");
        userExam = (Exam) intent.getSerializableExtra("Exam");
        students = userClass.getStudents();


        if (students.get(0).equals("")){
            students.remove(0);
        }

        View view = inflater.inflate(R.layout.fragment_show_class_components
                , container, false); // Inflate the layout for this fragment

        RecyclerView recyclerView = (RecyclerView) view
                .findViewById(R.id.recycler_class_components);
        recyclerView.setAdapter(new ShowClassComponentsFragment
                .ShowClassComponentAdapter(students, getActivity()
                .getApplicationContext(), recyclerView));


        final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        return view;
    }

    private class ShowClassComponentAdapter extends RecyclerView.Adapter implements View.OnClickListener {
        private final ArrayList<String> students;
        private Context context;
        private RecyclerView recyclerView;
        private ViewHolder holder;


        public ShowClassComponentAdapter(ArrayList<String> students, Context context, RecyclerView recyclerView) {
            this.students = students;
            this.context = context;
            this.recyclerView = recyclerView;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View view = LayoutInflater.from(context)
                    .inflate(R.layout.class_component_item, parent, false);

            ShowClassComponentsFragment.ViewHolder holder =
                    new ShowClassComponentsFragment.ViewHolder(view);
            view.setOnClickListener(this);

            return holder;
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {

            holder = (ShowClassComponentsFragment.ViewHolder) viewHolder;

            String student = students.get(position);
            holder.studentEmail.setText(student);
        }

        @Override
        public int getItemCount() {
            return students.size();
        }

        @Override
        public long getItemId(int position) {
            return super.getItemId(position);
        }


        @Override
        public void onClick(View v) {

        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {

        final TextView studentEmail;

        public ViewHolder(View view) {
            super(view);
            studentEmail = (TextView) view.findViewById(student_email);
        }

        @Override
        public void onClick(View v) {
        }

    }

}