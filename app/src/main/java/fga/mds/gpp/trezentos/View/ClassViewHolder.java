package fga.mds.gpp.trezentos.View;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import fga.mds.gpp.trezentos.R;

public class ClassViewHolder extends RecyclerView.ViewHolder {

    final TextView name;
    final TextView institution;

    public ClassViewHolder(View itemView) {
        super(itemView);
        name = (TextView) itemView.findViewById(R.id.name);
        institution = (TextView) itemView.findViewById(R.id.type);

    }



}
