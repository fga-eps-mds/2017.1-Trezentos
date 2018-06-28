package fga.mds.gpp.trezentos.View.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import fga.mds.gpp.trezentos.R;

public class ExamViewHolder extends RecyclerView.ViewHolder {

    public TextView examName;


    public ExamViewHolder(View view) {
        super(view);
        examName = view.findViewById(R.id.exam_name);

    }
}
