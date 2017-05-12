package fga.mds.gpp.trezentos.View;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import java.util.ArrayList;
import fga.mds.gpp.trezentos.Model.UserClass;
import fga.mds.gpp.trezentos.R;

public class ClassAdapter extends RecyclerView.Adapter implements View.OnClickListener, Filterable {

    private ArrayList<UserClass> userClasses;
    private ArrayList<UserClass> filteredClasses;
    private ArrayList<UserClass> tempList = null;
    private Context context;
    private RecyclerView recyclerView;
    private FriendFilter friendFilter;
    private String email;

    public ClassAdapter(ArrayList<UserClass> userClasses, Context context,
                        RecyclerView recyclerView, String email){
        this.userClasses = userClasses;
        this.filteredClasses = userClasses;
        this.context = context;
        this.recyclerView =  recyclerView;
        this.email = email;

        getFilter();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.class_item, parent, false);
        ClassViewHolder holder = new ClassViewHolder(view);
        view.setOnClickListener(this);

        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        UserClass userClass  = filteredClasses.get(position) ;

        if(userClass.getOwnerEmail().equals(email)){

            Log.d("EQUALS", userClass.getOwnerEmail() + " = " + email);

        }else{

            ClassViewHolder classViewHolder = (ClassViewHolder) holder;

            if(userClass.getStudents().contains(email)){

            }else {

                classViewHolder.name.setText(userClass.getClassName());
                classViewHolder.institution.setText(userClass.getInstitution());

                Log.d("EQUALS", userClass.getOwnerEmail() + " != " + email);

            }
        }

    }

    @Override
    public int getItemCount() {
        return filteredClasses.size();
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public Filter getFilter() {
        if (friendFilter == null) {
            friendFilter = new FriendFilter();
        }

        return friendFilter;
    }

    @Override
    public void onClick(final View view) {
        int itemPosition = recyclerView.getChildLayoutPosition(view);
        UserClass userClass = userClasses.get(itemPosition);

    }

    private class FriendFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults filterResults = new FilterResults();
            tempList= new ArrayList<UserClass>();
            if (constraint!=null && constraint.length()>0) {

                // search content in friend list
                for (UserClass userClass : userClasses) {
                    if (userClass.getClassName().toLowerCase().contains(constraint.toString().toLowerCase())) {
                        tempList.add(userClass);
                        Log.d("PUT", userClass.getClassName());
                    }
                }

                filterResults.count = tempList.size();
                filterResults.values = tempList;
            } else {
                filterResults.count = userClasses.size();
                filterResults.values = userClasses;
            }

            return filterResults;
        }

        @SuppressWarnings("unchecked")
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            filteredClasses = (ArrayList<UserClass>) results.values;
            notifyDataSetChanged();
        }
    }
}


