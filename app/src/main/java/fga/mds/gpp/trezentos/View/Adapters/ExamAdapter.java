package fga.mds.gpp.trezentos.View.Adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import fga.mds.gpp.trezentos.Model.Exam;
import fga.mds.gpp.trezentos.Model.UserClass;
import fga.mds.gpp.trezentos.R;
import fga.mds.gpp.trezentos.View.Activity.ExamActivity;
import fga.mds.gpp.trezentos.View.Activity.StudentExamActivity;
import fga.mds.gpp.trezentos.View.ViewHolder.ExamViewHolder;

public class ExamAdapter extends RecyclerView.Adapter implements View.OnClickListener {

    private final ArrayList<Exam> exams;
    private Context context;
    private  RecyclerView recyclerView;
    private UserClass userClass;
    private String userId;


    public ExamAdapter(ArrayList<Exam> exams, Context context, RecyclerView recyclerView, UserClass userClass, String userId) {
        this.exams = exams;
        this.context = context;
        this.recyclerView = recyclerView;
        this.userClass = userClass;
        this.userId = userId;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.exam_item, parent, false);
        ExamViewHolder holder = new ExamViewHolder(view);
        view.setOnClickListener(this);

        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ExamViewHolder examViewHolder = (ExamViewHolder) holder;

        Exam exam  = exams.get(position);
        examViewHolder.examName.setText(exam.getNameExam());

    }

    @Override
    public int getItemCount() {
        //return 0;
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

        if(userClass.getIdClassCreator().equals(userId)){
            Log.d("INTENT1","ClassOwner");
            Intent goExam = new  Intent(context, ExamActivity.class);
            Bundle extras = new Bundle();
            extras.putSerializable("Exam", exam);
            extras.putSerializable("Class", userClass);
            goExam.putExtras(extras);
            goExam.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(goExam);
        }else{
            Log.d("INTENT1","Student");

            Intent goExam = new  Intent(context, StudentExamActivity.class);
            Bundle extras = new Bundle();
            extras.putSerializable("Exam", exam);
            extras.putSerializable("Class", userClass);
            goExam.putExtras(extras);
            goExam.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(goExam);

        }
    }
}

