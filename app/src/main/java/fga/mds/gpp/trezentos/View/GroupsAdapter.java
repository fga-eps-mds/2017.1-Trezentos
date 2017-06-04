package fga.mds.gpp.trezentos.View;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import fga.mds.gpp.trezentos.Model.Groups;
import fga.mds.gpp.trezentos.R;

public class GroupsAdapter extends RecyclerView.Adapter<GroupsAdapter.ViewHolder>
        implements View.OnClickListener {
    private List<Groups> groupses;
    private LayoutInflater mLayouteInflate;
    public RecyclerView recyclerView;
    private RecyclerViewOnClickListener mRecyclerViewOnClickListener;

    public GroupsAdapter(Context context, List<Groups> l){
        mLayouteInflate = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.groupses = l;
    }


    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mLayouteInflate.inflate(R.layout.groups_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        viewHolder.groupNumber.setText(groupses.get(position).getGroupNumber());
        viewHolder.leaderName.setText(groupses.get(position).getLeaderName());

    }

    @Override
    public int getItemCount() {
        return groupses.size();
    }

    @Override
    public void onClick(View v) {

    }

    public void setRecyclerViewOnClickListener(RecyclerViewOnClickListener recyclerViewOnClickListener){
        mRecyclerViewOnClickListener = recyclerViewOnClickListener;
    }

    public void addListItem(Groups group, int position){
        groupses.add(group);
        notifyItemInserted(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView groupNumber;
        public TextView leaderName;

        public ViewHolder(View itemView) {
            super(itemView);

            groupNumber = (TextView) itemView.findViewById(R.id.group_number);
            leaderName = (TextView) itemView.findViewById(R.id.leader_name);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(mRecyclerViewOnClickListener != null){
                mRecyclerViewOnClickListener.onClickListener(v, getPosition());
            }

        }
    }
}