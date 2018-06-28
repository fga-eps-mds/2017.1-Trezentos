package fga.mds.gpp.trezentos.View.ViewHolder;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import fga.mds.gpp.trezentos.R;


public class AboutViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView title;
    public TextView subTitle;
    public ImageView image;

    // Define listener member variable
    private AboutViewHolder.OnItemClickListener listener;

    // Define the listener interface
    public interface OnItemClickListener {
        void onItemClick(View itemView, int position);
    }
    // Define the method that allows the parent activity or fragment to define the listener
    public void setOnItemClickListener(AboutViewHolder.OnItemClickListener listener) {
        this.listener = listener;
    }

    public AboutViewHolder(View itemView) {
        super(itemView);

        title = itemView.findViewById(R.id.title_about);
        subTitle = itemView.findViewById(R.id.description);
        image = itemView.findViewById(R.id.item_about);

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
