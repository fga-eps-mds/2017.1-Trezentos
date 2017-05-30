package fga.mds.gpp.trezentos.View;

import android.content.Context;
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

public class AboutAdapter extends ArrayAdapter{
    private int lastPosition = -1;
    Context mContext;
    ArrayList<About> dataSet;

    private static class ViewHolder{
        TextView title;
        TextView subTitle;
    }

    public AboutAdapter(ArrayList<About> data, Context context){
        super(context, R.layout.about_item, data);

        this.dataSet = data;
        this.mContext = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        // Get the data item for this position
        About about = (About) getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder;
        // View lookup cache stored in tag

        final View result;

        if(convertView == null){
            viewHolder = new ViewHolder();

            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.about_item, parent, false);

            viewHolder.title = (TextView) convertView.findViewById(R.id.title_about);
            viewHolder.subTitle = (TextView) convertView.findViewById(R.id.description);

            result = convertView;
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
            result = convertView;
        }

        int ordenation = (position > lastPosition) ? R.anim.up_from_bottom : R.anim.down_from_top;
        Animation animation = AnimationUtils.loadAnimation(mContext, ordenation);
        result.startAnimation(animation);
        lastPosition = position;

        viewHolder.title.setText(about.getTitle());
        viewHolder.subTitle.setText(about.getSubTitle());

        ImageView item_about = (ImageView) convertView.findViewById(R.id.item_about);
        item_about.setImageResource(about.getShowImage(position));
        // Return the completed view to render on screen
        return convertView;
    }

}
