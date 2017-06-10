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
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

import fga.mds.gpp.trezentos.Model.Exam;
import fga.mds.gpp.trezentos.Model.UserAccount;
import fga.mds.gpp.trezentos.Controller.UserExamControl;
import fga.mds.gpp.trezentos.Model.Exam;
import fga.mds.gpp.trezentos.Model.UserClass;
import fga.mds.gpp.trezentos.R;

import static fga.mds.gpp.trezentos.R.id.gradeLayout;
import static fga.mds.gpp.trezentos.R.id.student_name;
import static fga.mds.gpp.trezentos.R.id.text_view_grade;


public class StudentsFragment extends Fragment {

    private ArrayList<String> students;
    private UserClass userClass;
    private Exam userExam;
    private static HashMap<String, String> mapEmailAndGrade = new HashMap<>();

    public StudentsFragment() {
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
        ArrayList<String> students = userClass.getStudents();
        Log.d("ARRAYSTUDENTS", Integer.toString(students.get(0).length()));

        //if first item is null it will be removed
        if (students.get(0).length() == 0){
            students.remove(0);
        }
            Log.d("ARRAYSTUDENTS", students.toString());

        userExam = (Exam) intent.getSerializableExtra("Exam");
                students = userClass.getStudents();

        students = userClass.getStudents();
        populateMapValues(students); //clear map and populates it
        arrangeMap(students);//creates a new array of students that are enrolled at this class

        View view = inflater.inflate(R.layout.fragment_students, container, false); // Inflate the layout for this fragment
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
        private RecyclerView recyclerView;
        private StudentsFragment.ViewHolder holder;


        public AdapterStudents(ArrayList<String> userAccounts, Context context, RecyclerView recyclerView) {
            this.userAccounts = userAccounts;
            this.context = context;
            this.recyclerView = recyclerView;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            Exam exam = new Exam();

            exam.getFirstGrades();

            View view = LayoutInflater.from(context)
                    .inflate(R.layout.student_item, parent, false);

            if(!(getActivity() instanceof ExamActivity)){

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
            holder.userAccountName.setText(userAccount);
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

        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener, NumberPicker.OnValueChangeListener {

        final TextView userAccountName;
        final TextView gradeTextView;
        final TextView secondGradeTextView;
        final LinearLayout gradeLayout;
        final LinearLayout secondGradeLayout;

        public ViewHolder(View view) {
            super(view);
            userAccountName = (TextView) view.findViewById(student_name);
            gradeLayout = (LinearLayout) view.findViewById(R.id.gradeLayout);
            secondGradeLayout = (LinearLayout) view
                    .findViewById(R.id.second_grade_layout);
            gradeTextView = (TextView) view.findViewById(R.id.text_view_grade);
            secondGradeTextView = (TextView) view
                    .findViewById(R.id.text_view_second_grade);

            gradeLayout.setOnClickListener(this);
            secondGradeLayout.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {

                case R.id.gradeLayout:
                    showGradePicker(1);
                    break;

                case R.id.second_grade_layout:
                    showGradePicker(2);
                    break;
            }
        }

        @Override
        public void onValueChange(NumberPicker picker, int oldVal, int newVal) {

        }

        public int showGradePicker(final int CLICK) {
            final Dialog d = new Dialog(getContext());
            d.setContentView(R.layout.dialog);

            final NumberPicker np1 = (NumberPicker) d.findViewById(R.id.numberPicker1);
            final NumberPicker np2 = (NumberPicker) d.findViewById(R.id.numberPicker2);

            Button b1 = (Button) d.findViewById(R.id.button1);
            Button b2 = (Button) d.findViewById(R.id.button2);
            Button buttonOK300 = (Button) d.findViewById(R.id.button1);

            np1.setMinValue(0);  // min value 0
            np1.setMaxValue(10); // max value 100
            np1.setWrapSelectorWheel(false);
            np1.setOnValueChangedListener(this);

            np2.setMinValue(0); // min value 0
            np2.setMaxValue(99); // max value 100
            np2.setWrapSelectorWheel(false);
            np2.setOnValueChangedListener(this);

            if(CLICK == 1) {

                gradeTextView.setText(userExam.getFirstGrades().toString());

                b1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String email;
                        String grade;
                        String secondGrade;

                        gradeTextView.setText(String.valueOf(np1.getValue()) + "." +
                                String.valueOf(String.format("%02d", np2.getValue()))); //set the value to textview

                        grade = gradeTextView.getText().toString();
                        secondGrade = secondGradeTextView.getText().toString();
                        email = userAccountName.getText().toString();

                        Log.i("GRADE", grade);
                        Log.i("MAP", email);
                        Log.d("TAMANHOMAPA", Integer.toString(mapEmailAndGrade.size()));

                        mapEmailAndGrade.put(email, grade);

                        userExam.setFirstGrades(mapEmailAndGrade.toString());
                        userExam.setSecondGrades(mapEmailAndGrade.toString());

                        Log.d("TAMANHOMAPA", userExam.getFirstGrades());

                        d.dismiss();
                    }
                });
            }else {

                secondGradeTextView.setText(userExam.getSecondGrades().toString());

                buttonOK300.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String EMAIL;
                        String GRADE;
                        secondGradeTextView.setText(String.valueOf(np1.getValue()) + "." +
                                String.valueOf(String.format
                                        ("%02d", np2.getValue())));

                        GRADE = secondGradeTextView.getText().toString();

                        EMAIL = userAccountName.getText().toString();

                        Log.i("MAP", GRADE);
                        Log.i("MAP", EMAIL);
                        Log.d("TAMANHOMAPA", Integer.toString(mapEmailAndGrade.size()));

                        mapEmailAndGrade.put(EMAIL, GRADE);

                        userExam.setSecondGrades(mapEmailAndGrade.toString());

                        Log.d("TAMANHOMAPA", userExam.getSecondGrades());

                        d.dismiss();
                    }
                });
            }

            b2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    d.dismiss(); // dismiss the dialog
                }
            });
            d.show();

            return CLICK;
        }

    }

    public HashMap<String, String> populateMapValues(ArrayList<String> students) {
        mapEmailAndGrade.clear();

        for (int i = 0; i < students.size(); i++) {
            mapEmailAndGrade.put(students.get(i), "0.00");
        }
        Log.d("DEBUGMAP", Integer.toString(mapEmailAndGrade.size()));
        return mapEmailAndGrade;
    }

    public HashMap<String, String> getHashEmailAndGrade() {
        Log.d("DEBUGMAP", Integer.toString(mapEmailAndGrade.size()));
        return mapEmailAndGrade;
    }

    public ArrayList<String> arrangeMap(ArrayList<String> students) {
        //if item is null it will be removed
        int i = 0;
        if (students.size() == 0) {
            return students;
        }else{
            do {
                if (students.get(i).length() == 0) {
                    students.remove(i);
                }
                ++i;
            } while (i < students.size());
            Log.d("DEBUGMAP", Integer.toString(students.size()));
            Log.d("DEBUGMAP", students.toString());
            return students;
        }
    }
}