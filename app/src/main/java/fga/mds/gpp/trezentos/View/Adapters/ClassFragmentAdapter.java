package fga.mds.gpp.trezentos.View.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import fga.mds.gpp.trezentos.Model.UserClass;
import fga.mds.gpp.trezentos.R;
import fga.mds.gpp.trezentos.View.ViewHolder.ClassViewHolder;

public class ClassFragmentAdapter extends RecyclerView.Adapter implements View.OnClickListener {
    private ArrayList<UserClass> userClasses = null;
    private Context context;
    private ClassViewHolder.OnItemClickListener listener;

    public void setOnItemClickListener(ClassViewHolder.OnItemClickListener listener) {
        this.listener = listener;
    }

    public ClassFragmentAdapter(ArrayList<UserClass> userClasses, Context context) {
        this.userClasses = userClasses;
        this.context = context;
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

        UserClass userClass  = userClasses.get(position);
        ClassViewHolder classViewHolder = (ClassViewHolder) holder;

        //Log.d("CLASSNAME", userClass.getClassName() + " " + position);
        classViewHolder.name.setText(userClass.getClassName());
        classViewHolder.institution.setText(userClass.getInstitution());
        classViewHolder.description.setText(userClass.getDescription());
        classViewHolder.owner_name.setText(userClass.getCreatorName());
        classViewHolder.date_creation.setText(userClass.getCreationDate());
    }



    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }


    @Override
    public int getItemCount() {
        if(userClasses == null){
            return 0;
        }else{
            return userClasses.size();
        }
    }

    @Override
    public void onClick(View v) {

    }
}
