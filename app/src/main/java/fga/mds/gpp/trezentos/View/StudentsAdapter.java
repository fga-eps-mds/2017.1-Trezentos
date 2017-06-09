package fga.mds.gpp.trezentos.View;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import fga.mds.gpp.trezentos.Controller.UserClassControl;
import fga.mds.gpp.trezentos.Exception.UserClassException;
import fga.mds.gpp.trezentos.Model.Evaluation;
import fga.mds.gpp.trezentos.Model.Student;
import fga.mds.gpp.trezentos.R;

public class StudentsAdapter  extends RecyclerView.Adapter implements View.OnClickListener {


    private ArrayList<Evaluation> evaluations;
    private Context context;
    private RecyclerView recyclerView;
    private StudentsViewHolder holder;
    private UserClassControl userClassControl;
    //private ArrayList<String> className;
    //private ArrayList<String> examName;
    //private StudentsViewHolder.OnItemClickListener listener;



    public StudentsAdapter(ArrayList<Evaluation> evaluation, Context context, RecyclerView recyclerView) {
        this.evaluations = evaluation;
        this.context = context;
        this.recyclerView = recyclerView;

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.evaluation_item, parent, false);
        StudentsViewHolder holder = new StudentsViewHolder(view);
        view.setOnClickListener(this);
        //holder.setOnItemClickListener(listener);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        holder = (StudentsViewHolder) viewHolder;


        final Evaluation evaluation = evaluations.get(position);

        holder.userAccountName.setText("Avalie a ajuda do aluno " + evaluation.getIdStudent());//
        //holder.circleImageView.setImageResource(userAccount.getPhoto());
        holder.className.setText(evaluation.getIdClass());
        holder.examName.setText(evaluation.getIdExam());
    }

    @Override
    public int getItemCount() {
        return evaluations.size();
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }


    @Override
    public void onClick(View v) {
        int itemPosition = recyclerView.getChildLayoutPosition(v);
        Evaluation evaluation = evaluations.get(itemPosition);
        showConfirm(evaluation);
    }

    private void showConfirm(final Evaluation evaluation){

        final Dialog evaluationDialog =
                new Dialog(EvaluationFragment.getInstance().getContext());
        evaluationDialog.setTitle("EvaluationConfirmation");
        evaluationDialog.setContentView(R.layout.dialog_confirm_evaluation);
        Button confirmEvaluation = (Button) evaluationDialog.findViewById(R.id.button1);
        Button cancelEvaluation = (Button) evaluationDialog.findViewById(R.id.button2);
        final RatingBar ratingBarDialog = (RatingBar) evaluationDialog.findViewById(R.id.ratingBarDialog);
        //ratingBarDialog.setRating(rate);

        evaluationDialog.show();

        confirmEvaluation.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                userClassControl = UserClassControl.getInstance(context);
                evaluation.setRateEvaluation(ratingBarDialog.getRating());
                try {
                    String result = userClassControl.validateRate(evaluation);
                } catch (UserClassException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                evaluationDialog.dismiss();

            }
        });
        cancelEvaluation.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                evaluationDialog.dismiss(); // dismiss the dialog
            }
        });

    }



}
