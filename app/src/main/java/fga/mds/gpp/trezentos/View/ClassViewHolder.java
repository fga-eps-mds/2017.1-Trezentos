package fga.mds.gpp.trezentos.View;


import android.content.DialogInterface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import fga.mds.gpp.trezentos.R;

public class ClassViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    final TextView name;
    final TextView institution;
    // Define listener member variable
    private OnItemClickListener listener;

    // Define the listener interface
    public interface OnItemClickListener {
        void onItemClick(View itemView, int position);
    }
    // Define the method that allows the parent activity or fragment to define the listener
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public ClassViewHolder(View itemView) {
        super(itemView);
        name = (TextView) itemView.findViewById(R.id.name);
        institution = (TextView) itemView.findViewById(R.id.type);

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
