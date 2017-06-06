package fga.mds.gpp.trezentos.View;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import fga.mds.gpp.trezentos.R;

public class StudentsAdapter  extends RecyclerView.Adapter implements View.OnClickListener {

    private final ArrayList<String> userAccounts;
    private Context context;
    private  RecyclerView recyclerView;
    private  StudentsViewHolder holder;


    public StudentsAdapter(ArrayList<String> userAccounts, Context context,  RecyclerView recyclerView) {
        this.userAccounts = userAccounts;
        this.context = context;
        this.recyclerView = recyclerView;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.evaluation_item, parent, false);
        StudentsViewHolder holder = new StudentsViewHolder(view);
        view.setOnClickListener(this);

        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {

        holder = (StudentsViewHolder) viewHolder;

        String userAccount = userAccounts.get(position);
        holder.userAccountName.setText("Avalie a ajuda do aluno " + userAccount);//
        //holder.circleImageView.setImageResource(userAccount.getPhoto());
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
        int itemPosition = recyclerView.getChildLayoutPosition(v);
    }


}
