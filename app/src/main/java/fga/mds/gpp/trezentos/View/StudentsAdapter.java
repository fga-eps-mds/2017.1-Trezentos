package fga.mds.gpp.trezentos.View;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import fga.mds.gpp.trezentos.R;

public class StudentsAdapter  extends RecyclerView.Adapter implements View.OnClickListener {

    private final ArrayList<String> userAccounts;
    private Context context;
    private RecyclerView recyclerView;
    private StudentsViewHolder holder;
    private ArrayList<String> className;
    private ArrayList<String> examName;

    public StudentsAdapter(ArrayList<String> userAccounts, ArrayList<String> className
            , ArrayList<String> examName, Context context, RecyclerView recyclerView) {
        this.userAccounts = userAccounts;
        this.context = context;
        this.recyclerView = recyclerView;
        this.className = className;
        this.examName = examName;
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
        String classEvaluationName = className.get(position);
        String userAccount = userAccounts.get(position);
        String examEvaluationName = examName.get(position);

        holder.userAccountName.setText("Avalie a ajuda do aluno " + userAccount);//
        //holder.circleImageView.setImageResource(userAccount.getPhoto());
        holder.className.setText(classEvaluationName);
        holder.examName.setText(examEvaluationName);
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
