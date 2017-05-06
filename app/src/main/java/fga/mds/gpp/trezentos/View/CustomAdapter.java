package fga.mds.gpp.trezentos.View;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;

import fga.mds.gpp.trezentos.Model.UserClass;
import fga.mds.gpp.trezentos.R;

public class CustomAdapter extends ArrayAdapter<UserClass> implements View.OnClickListener{

    private ArrayList<UserClass> dataSet;
    Context mContext;

    // View lookup cache
    private static class ViewHolder{
        TextView className;
        TextView institution;
        double cutOff;
        ImageView info;

    }

    public CustomAdapter(ArrayList<UserClass> data, Context context){
        super(context, R.layout.row_item, data);
        this.dataSet = data;
        this.mContext = context;
    }

    @Override
    public void onClick(View v){
        int position= (Integer) v.getTag();
        Object object= getItem(position);
        UserClass userClass = (UserClass) object;

        switch(v.getId()){
            case R.id.item_info:

                break;
        }
    }

    private int lastPosition = -1;

    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        // Get the data item for this position
        UserClass userClass = getItem(position);

        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // View lookup cache stored in tag

        final View result;

        if(convertView == null){

            viewHolder = new ViewHolder();

            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.row_item, parent, false);

            viewHolder.className = (TextView) convertView.findViewById(R.id.name);
            viewHolder.institution = (TextView) convertView.findViewById(R.id.type);
            viewHolder.info = (ImageView) convertView.findViewById(R.id.item_info);

            result = convertView;

            convertView.setTag(viewHolder);

        }
        else{
            viewHolder = (ViewHolder) convertView.getTag();
            result = convertView;
        }

        int ordenation = (position > lastPosition) ? R.anim.up_from_bottom : R.anim.down_from_top;
        Animation animation = AnimationUtils.loadAnimation(mContext, ordenation);
        result.startAnimation(animation);
        lastPosition = position;

        viewHolder.className.setText(userClass.getClassName());
        viewHolder.institution.setText(userClass.getInstitution());
        viewHolder.info.setOnClickListener(this);
        viewHolder.info.setTag(position);

        // Return the completed view to render on screen
        return convertView;
    }
}