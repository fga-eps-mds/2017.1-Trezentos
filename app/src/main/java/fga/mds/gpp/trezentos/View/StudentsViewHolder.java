package fga.mds.gpp.trezentos.View;

import android.app.Dialog;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

import fga.mds.gpp.trezentos.R;

public class StudentsViewHolder extends RecyclerView.ViewHolder
        implements RatingBar.OnRatingBarChangeListener{

    final TextView userAccountName;
    final RatingBar ratingBar;

    public StudentsViewHolder(View view) {
        super(view);
        userAccountName = (TextView) view.findViewById(R.id.student_name);
        ratingBar = (RatingBar) view.findViewById(R.id.ratingBar);
        ratingBar.setOnRatingBarChangeListener(this);
    }

    private void showConfirm(float rate){

        final Dialog evaluationDialog =
                new Dialog(EvaluationFragment.getInstance().getContext());
        evaluationDialog.setTitle("EvaluationConfirmation");
        evaluationDialog.setContentView(R.layout.dialog_confirm_evaluation);
        Button confirmEvaluation = (Button) evaluationDialog.findViewById(R.id.button1);
        Button cancelEvaluation = (Button) evaluationDialog.findViewById(R.id.button2);
        RatingBar ratingBarDialog = (RatingBar) evaluationDialog.findViewById(R.id.ratingBarDialog);
        ratingBarDialog.setRating(rate);

        evaluationDialog.show();

        confirmEvaluation.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                //Send API
                // evaluationDialog.dismiss();
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

    @Override
    public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
        showConfirm(rating);
    }
}
