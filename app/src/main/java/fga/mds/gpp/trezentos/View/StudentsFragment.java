package fga.mds.gpp.trezentos.View;

import android.app.Dialog;
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

import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

import fga.mds.gpp.trezentos.Model.Exam;
import fga.mds.gpp.trezentos.Model.UserAccount;
import fga.mds.gpp.trezentos.Model.UserClass;
import fga.mds.gpp.trezentos.R;

import static fga.mds.gpp.trezentos.R.id.gradeLayout;
import static fga.mds.gpp.trezentos.R.id.no_presence;
import static fga.mds.gpp.trezentos.R.id.presence;
import static fga.mds.gpp.trezentos.R.id.student_name;
import static fga.mds.gpp.trezentos.R.id.text_view_grade;


public class StudentsFragment extends Fragment {

    private ArrayList<UserAccount> userAccounts;
    private UserClass userClass;
    private Exam userExam;
    private static HashMap<String, String> mapEmailAndGrade = new HashMap<>();

    public StudentsFragment(){
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_students, container, false);
        Intent intent = getActivity().getIntent();

        //creates a new array of students that are enrolled at this class
        userClass = (UserClass) intent.getSerializableExtra("Class");
        userExam = (Exam) intent.getSerializableExtra("Exam");
        ArrayList<String> students = userClass.getStudents();
        Log.d("ARRAYSTUDENTS", Integer.toString(students.get(0).length()));
        //if first item is null it will be removed
        if (students.get(0).length() == 0){
            students.remove(0);
        }
            Log.d("ARRAYSTUDENTS", students.toString());

            RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerStudents);
            recyclerView.setAdapter(new StudentsFragment.AdapterStudents(students, getActivity().getApplicationContext(), recyclerView));

            final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            recyclerView.setLayoutManager(layoutManager);

     return view;
    }


    private class AdapterStudents extends RecyclerView.Adapter implements View.OnClickListener {
        private final ArrayList<String> userAccounts;
        private Context context;
        private  RecyclerView recyclerView;
        private  StudentsFragment.ViewHolder holder;


        public AdapterStudents(ArrayList<String> userAccounts, Context context,  RecyclerView recyclerView) {
            this.userAccounts = userAccounts;
            this.context = context;
            this.recyclerView = recyclerView;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View view = LayoutInflater.from(context)
                    .inflate(R.layout.student_item, parent, false);

            if(!(getActivity() instanceof ExamActivity)){

                view.findViewById(presence).setVisibility(View.GONE);
                view.findViewById(no_presence).setVisibility(View.GONE);
                view.findViewById(gradeLayout).setVisibility(View.GONE);
                view.findViewById(text_view_grade).setVisibility(View.GONE);

            }else{
                // do nothing
            }
            StudentsFragment.ViewHolder holder =
                    new StudentsFragment.ViewHolder(view);
            view.setOnClickListener(this);

            return holder;
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {

            holder = (StudentsFragment.ViewHolder) viewHolder;

            String userAccount = userAccounts.get(position);
            holder.userAccountName.setText(userAccount);//
            //holder.circleImageView.setImageResource(userAccount.getPhoto());
        }

        @Override
        public int getItemCount() {
            return userAccounts.size();
        }

        @Override
        public long getItemId(int position) {
            return super.getItemId(position);
        }


        @Override
        public void onClick(View v) {
            int itemPosition = recyclerView.getChildLayoutPosition(v);

//            if(v.getId() == R.id.presence){
//                holder.noPresence.setVisibility(View.VISIBLE);
//                holder.presence.setVisibility(View.GONE);
//            }
//            if(v.getId() == R.id.no_presence){
//                holder.noPresence.setVisibility(View.GONE);
//                holder.presence.setVisibility(View.VISIBLE);
//            }
            //Exam exam = exams.get(itemPosition);

        }
    }

    private class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, NumberPicker.OnValueChangeListener {
        final TextView userAccountName;
        final ImageView presence;
        final ImageView noPresence;
      //  final CircleImageView circleImageView;
        final LinearLayout gradeLayout;
        final TextView gradeTextView;

        public ViewHolder(View view) {
            super(view);
            userAccountName = (TextView) view.findViewById(student_name);
            presence = (ImageView) view.findViewById(R.id.presence);
            noPresence= (ImageView) view.findViewById(no_presence);
           // circleImageView = (CircleImageView) view.findViewById(R.id.profile_image);
            gradeLayout = (LinearLayout) view.findViewById(R.id.gradeLayout);
            gradeTextView = (TextView) view.findViewById(R.id.text_view_grade);

            presence.setOnClickListener(this);
            noPresence.setOnClickListener(this);
            gradeLayout.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            switch (v.getId()){

                case R.id.presence:
                    noPresence.setVisibility(View.VISIBLE);
                    presence.setVisibility(View.GONE);

                    break;

                case no_presence:
                    noPresence.setVisibility(View.GONE);
                    presence.setVisibility(View.VISIBLE);

                    break;


                case R.id.gradeLayout:
                    showGradePicker();
                    break;

            }
        }

        @Override
        public void onValueChange(NumberPicker picker, int oldVal, int newVal) {}

        public void showGradePicker(){

            final Dialog d = new Dialog(getContext());
            d.setTitle("NumberPicker");
            d.setContentView(R.layout.dialog);
            Button b1 = (Button) d.findViewById(R.id.button1);
            Button b2 = (Button) d.findViewById(R.id.button2);

            final NumberPicker np1 = (NumberPicker) d.findViewById(R.id.numberPicker1);
            np1.setMinValue(0);  // min value 0
            np1.setMaxValue(10); // max value 100
            np1.setWrapSelectorWheel(false);
            np1.setOnValueChangedListener(this);


            final NumberPicker np2 = (NumberPicker) d.findViewById(R.id.numberPicker2);
            np2.setMinValue(0); // min value 0
            np2.setMaxValue(99); // max value 100
            np2.setWrapSelectorWheel(false);
            np2.setOnValueChangedListener(this);


            b1.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v) {
                    gradeTextView.setText(String.valueOf(np1.getValue()) + "." +
                            String.valueOf(String.format("%02d", np2.getValue()))); //set the value to textview

                    String grade = gradeTextView.getText().toString();
                    String email = userAccountName.getText().toString();

                    Log.i("MAP", grade);
                    Log.i("MAP", email);
                    mapEmailAndGrade.put(email, grade);
                    Log.d("TAMANHOMAPA", Integer.toString(mapEmailAndGrade.size()));
                    userExam.setFirstGrade(mapEmailAndGrade.toString());
                    Log.d("TAMANHOMAPA", userExam.getFirstGrades());

                    d.dismiss();
                }
            });
            b2.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v) {
                    d.dismiss(); // dismiss the dialog
                }
            });
            d.show();
        }
    }

}