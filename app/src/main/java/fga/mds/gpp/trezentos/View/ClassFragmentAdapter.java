package fga.mds.gpp.trezentos.View;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import fga.mds.gpp.trezentos.Model.UserClass;
import fga.mds.gpp.trezentos.R;

class ClassFragmentAdapter extends RecyclerView.Adapter implements View.OnClickListener {
    private final ArrayList<UserClass> userClasses;
    private Context context;
    private  RecyclerView recyclerView;
    private ClassViewHolder.OnItemClickListener listener;

    public void setOnItemClickListener(ClassViewHolder.OnItemClickListener listener) {
        this.listener = listener;
    }

    ClassFragmentAdapter(ArrayList<UserClass> userClasses, Context context, RecyclerView recyclerView) {
        this.userClasses = userClasses;
        this.context = context;
        this.recyclerView = recyclerView;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.class_item, parent, false);
        ClassViewHolder holder = new ClassViewHolder(view);
//        view.setOnClickListener(this);
        holder.setOnItemClickListener(listener);

        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        UserClass userClass  = userClasses.get(position) ;
        ClassViewHolder classViewHolder = (ClassViewHolder) holder;

        Log.d("CLASSNAME", userClass.getClassName() + " " + position);
        classViewHolder.name.setText(userClass.getClassName());
        classViewHolder.institution.setText(userClass.getInstitution());

    }

    @Override
    public int getItemCount() {
        return userClasses.size();
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public void onClick(View v) {
        int itemPosition = recyclerView.getChildLayoutPosition(v);
        UserClass userClass = userClasses.get(itemPosition);

        Intent goClass = new  Intent(context, ClassActivity.class);
        goClass.putExtra("Class", userClass);
        goClass.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(goClass);

    }
}
