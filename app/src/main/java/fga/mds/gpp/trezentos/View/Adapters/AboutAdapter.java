package fga.mds.gpp.trezentos.View.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import fga.mds.gpp.trezentos.Model.About;
import fga.mds.gpp.trezentos.R;
import fga.mds.gpp.trezentos.View.ViewHolder.AboutViewHolder;
import fga.mds.gpp.trezentos.View.ViewHolder.ClassViewHolder;

public class AboutAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener{
    Context mContext;
    ArrayList<About> dataSet;
    private AboutViewHolder.OnItemClickListener listener;

    public AboutAdapter(ArrayList<About> data, Context context){
        this.dataSet = data;
        this.mContext = context;
    }

    public void setOnItemClickListener(AboutViewHolder.OnItemClickListener listener) {
        this.listener = listener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.about_item, parent, false);
        AboutViewHolder aboutViewHolder = new AboutViewHolder(view);
        aboutViewHolder.setOnItemClickListener(listener);

        return aboutViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        About about =  dataSet.get(position);
        AboutViewHolder aboutViewHolder = (AboutViewHolder) holder;

        aboutViewHolder.title.setText(about.getTitle());
        aboutViewHolder.subTitle.setText(about.getSubTitle());
        aboutViewHolder.image.setImageResource(about.getShowImage(position));
    }

    @Override
    public int getItemCount() {
        if(dataSet == null){
            return 0;
        }else{
            return dataSet.size();
        }
    }

    @Override
    public void onClick(View v) {

    }

}
