package fga.mds.gpp.trezentos.View.Fragment;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.PopupMenu;
import android.widget.TextView;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import fga.mds.gpp.trezentos.Controller.UserClassControl;
import fga.mds.gpp.trezentos.Controller.UserExamControl;
import fga.mds.gpp.trezentos.Model.Exam;
import fga.mds.gpp.trezentos.Model.Student;
import fga.mds.gpp.trezentos.Model.UserAccount;
import fga.mds.gpp.trezentos.Model.UserClass;
import fga.mds.gpp.trezentos.R;
import fga.mds.gpp.trezentos.View.Activity.ExamActivity;

import static fga.mds.gpp.trezentos.R.id.gradeLayout;
import static fga.mds.gpp.trezentos.R.id.student_email;
import static fga.mds.gpp.trezentos.R.id.student_name;
import static fga.mds.gpp.trezentos.R.id.text_view_grade;


public class StudentsFragment extends Fragment {

    public ArrayList<String> students = new ArrayList<>();
    private UserClass userClass;
    private Exam userExam;
    public UserClassControl userClassControl;
    public ArrayList<Student> userFromClass;


    public View view;

    private static HashMap<String, Double> mapEmailAndGrade = new HashMap<>();

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

        //students = array; // userClass.getStudents();
        //populateMapValues(students); //clear map and populates it
        //arrangeMap(students);//creates a new array of students that are enrolled at this class

        view = inflater.inflate(R.layout.fragment_students, container, false); // Inflate the layout for this fragment

        userClassControl = UserClassControl.getInstance(getActivity());

        try {
            userFromClass = userClassControl.getUsersFromClass(userClass.getIdClass(), userExam.getId());
//            Log.d("PARAMS", userId + "  " + userClass.getIdClass());
//            Log.d("EXAM", String.valueOf(userExams.size()));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        for (UserAccount u: userFromClass) {
            students.add(u.getEmail());
        }

        RecyclerView recyclerView = view.findViewById(R.id.recyclerStudents);
        recyclerView.setAdapter(new StudentsFragment.AdapterStudents(userFromClass, getActivity().getApplicationContext()));

        final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        return view;
    }

    private class AdapterStudents extends RecyclerView.Adapter implements View.OnClickListener {
        public  ArrayList<Student> userAccounts;
        private Context context;
        private StudentsFragment.ViewHolder holder;


        public AdapterStudents(ArrayList<Student> userAccounts, Context context) {
            this.userAccounts = userAccounts;
            this.context = context;

        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

//            Exam exam = new Exam();
//
//            exam.getFirstGrades();

            View view = LayoutInflater.from(context)
                    .inflate(R.layout.student_item, parent, false);

//            if(!(getActivity() instanceof ExamActivity)){
//
//                view.findViewById(gradeLayout).setVisibility(View.GONE);
//                view.findViewById(text_view_grade).setVisibility(View.GONE);
//            }else{
//                // do nothing
//            }
            StudentsFragment.ViewHolder holder = new StudentsFragment.ViewHolder(view);
            view.setOnClickListener(this);

            return holder;
        }

        @RequiresApi(api = Build.VERSION_CODES.N)
        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {

            holder = (StudentsFragment.ViewHolder) viewHolder;

            Student userAccount = userAccounts.get(position);
            holder.userAccountName.setText(userAccount.getFisrtName() + " " + userAccount.getLastName());
            holder.userAccountEmail.setText(userAccount.getEmail());
            holder.gradeTextView.setText(userAccount.getFirstGrade().toString());
            holder.secondGradeTextView.setText(userAccount.getSecondGrade().toString());
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
        final TextView userAccountEmail;
        final TextView gradeTextView;
        final TextView secondGradeTextView;
        final LinearLayout gradeLayout;
        final LinearLayout secondGradeLayout;

        final ImageButton imageButtonMore;


        public ViewHolder(View view) {
            super(view);
            userAccountName = view.findViewById(student_name);
            userAccountEmail = view.findViewById(student_email);
            gradeLayout = view.findViewById(R.id.gradeLayout);
            secondGradeLayout = view.findViewById(R.id.second_grade_layout);
            gradeTextView = view.findViewById(R.id.text_view_grade);
            secondGradeTextView = view.findViewById(R.id.text_view_second_grade);

            imageButtonMore = view.findViewById(R.id.more_options);

            gradeLayout.setOnClickListener(this);
            secondGradeLayout.setOnClickListener(this);
            imageButtonMore.setOnClickListener(this);
        }

        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.more_options:
                    showPopup(view);
                    break;

//                case R.id.gradeLayout:
//                    showGradePicker(1);
//                    break;
//
//                case R.id.second_grade_layout:
//                    showGradePicker(2);
//                    break;
            }
        }
        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
        public void showPopup(View v) {

            ContextThemeWrapper contextThemeWrapper = new ContextThemeWrapper(getActivity(), R.style.PopupMenuOverlapAnchor);
//            PopupMenu popupMenu = new PopupMenu(contextThemeWrapper, this);

            PopupMenu popup = new PopupMenu(contextThemeWrapper, getView(), Gravity.RIGHT);

            MenuInflater inflater = popup.getMenuInflater();
            inflater.inflate(R.menu.menu_student, popup.getMenu());
            popup.show();
        }

        @Override
        public void onValueChange(NumberPicker picker, int oldVal, int newVal) {

        }

        int showGradePicker(final int CLICK) {
            final Dialog d = new Dialog(getContext());
            d.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            d.setContentView(R.layout.dialog);

            final NumberPicker np1 = d.findViewById(R.id.numberPicker1);
            final NumberPicker np2 = d.findViewById(R.id.numberPicker2);

            Button b1 = d.findViewById(R.id.button1);
            Button b2 = d.findViewById(R.id.button2);
            Button buttonOK300 = d.findViewById(R.id.button1);

            np1.setMinValue(0);  // min value 0
            np1.setMaxValue(10); // max value 100
            np1.setWrapSelectorWheel(false);
            np1.setOnValueChangedListener(this);

            np2.setMinValue(0); // min value 0
            np2.setMaxValue(99); // max value 100
            np2.setWrapSelectorWheel(false);
            np2.setOnValueChangedListener(this);

            if(CLICK == 1) {
                b1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        gradeTextView.setText(String.valueOf(np1.getValue()) + "." +
                            String.valueOf(String.format("%02d", np2.getValue()))); //set the value to textview

                        Double grade = Double.valueOf(gradeTextView.getText().toString());
                        String email = userAccountName.getText().toString();

                        mapEmailAndGrade.put(email, grade);
                        userExam.setFirstGrades(mapEmailAndGrade.toString());

                        grade = Double.valueOf(gradeTextView.getText().toString());
                        email = userAccountName.getText().toString();

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

                secondGradeTextView.setText(userExam.getSecondGrades());

                buttonOK300.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String EMAIL;
                        Double GRADE;
                        secondGradeTextView.setText(String.valueOf(np1.getValue()) + "." +
                                String.valueOf(String.format
                                        ("%02d", np2.getValue())));

                        GRADE = Double.valueOf(secondGradeTextView.getText().toString());

                        EMAIL = userAccountName.getText().toString();

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

    public HashMap<String, Double> populateMapValues(ArrayList<String> students) {
        mapEmailAndGrade.clear();

        for (int i = 0; i < students.size(); i++) {
            mapEmailAndGrade.put(students.get(i), 0.00);
        }
        Log.d("DEBUGMAP", Integer.toString(mapEmailAndGrade.size()));
        return mapEmailAndGrade;
    }

    public static HashMap<String, Double> getHashEmailAndGrade() {
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