package fga.mds.gpp.trezentos.View;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import fga.mds.gpp.trezentos.R;

public class ShowStudentGroupViewHolder extends RecyclerView.ViewHolder
        implements View.OnClickListener{
    public TextView studentEmail;
    public TextView firstGrade;
    public TextView secondGrade;

    // Define listener member variable
    private ShowStudentGroupViewHolder.OnItemClickListener listener;

    // Define the listener interface
    public interface OnItemClickListener {
        void onItemClick(View itemView, int position);
    }
    // Define the method that allows the parent activity or fragment to define the listener
    public void setOnItemClickListener
        (ShowStudentGroupViewHolder.OnItemClickListener listener) {
        this.listener = listener;
    }

    public ShowStudentGroupViewHolder(View itemView) {
        super(itemView);
        studentEmail = (TextView) itemView.findViewById(R.id.student_email);
        firstGrade = (TextView) itemView.findViewById(R.id.student_first_grade);
        secondGrade = (TextView) itemView.findViewById(R.id.student_second_grade);

        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        // Triggers click upwards to the adapter on click
        if (listener != null) {
            int position = getAdapterPosition();
            if (position != RecyclerView.NO_POSITION) {
                listener.onItemClick(itemView, position);
            }
        }

    }
}
