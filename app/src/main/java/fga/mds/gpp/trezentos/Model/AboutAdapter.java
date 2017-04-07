package fga.mds.gpp.trezentos.Model;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import fga.mds.gpp.trezentos.R;

public class AboutAdapter extends ArrayAdapter{

    Context mContext;
    private ArrayList<About> dataSet;

    private static class ViewHolder {
        TextView title;
        TextView subTitle;
    }

    public AboutAdapter(ArrayList<About> data, Context context) {
        super(context, R.layout.about_item, data);
        this.dataSet = data;
        this.mContext = context;

    }

    private int lastPosition = -1;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        About about = (About) getItem(position);

        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder;
        // view lookup cache stored in tag

        final View result;

        if (convertView == null) {

            viewHolder = new ViewHolder();

            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.about_item, parent, false);

            viewHolder.title = (TextView) convertView.findViewById(R.id.title_about);
            viewHolder.subTitle = (TextView) convertView.findViewById(R.id.description);

            result=convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result=convertView;
        }

        Animation animation = AnimationUtils.loadAnimation(mContext, (position > lastPosition) ? R.anim.up_from_bottom : R.anim.down_from_top);
        result.startAnimation(animation);
        lastPosition = position;

        viewHolder.title.setText(about.getTitle());
        viewHolder.subTitle.setText(about.getSubTitle());
        //viewHolder.cutOff.setText(userClass.getCutOff());

        // Return the completed view to render on screen
        return convertView;
    }

}
