package fga.mds.gpp.trezentos.View;


import android.widget.Filter;

import java.util.ArrayList;

import fga.mds.gpp.trezentos.Model.UserClass;

public class FriendFilter extends Filter {

    private ArrayList<UserClass> tempList = null;
    private ArrayList<UserClass> userClasses;
    private ExploreFragmentAdapter classAdapter;

    public FriendFilter(ArrayList<UserClass> userClasses, ExploreFragmentAdapter classAdapter){
        this.userClasses = userClasses;
        this.classAdapter = classAdapter;
    }

    @Override
    protected FilterResults performFiltering(CharSequence constraint) {
        FilterResults filterResults = new FilterResults();
        tempList= new ArrayList<>();
        if (constraint!=null && constraint.length()>0) {

            // search content in friend list
            for (UserClass userClass : userClasses) {
                if (userClass.getClassName().toLowerCase().contains(constraint.toString().toLowerCase())) {
                    tempList.add(userClass);
                }
            }

            filterResults.count = tempList.size();
            filterResults.values = tempList;
        }else{
            filterResults.count = userClasses.size();
            filterResults.values = userClasses;
        }
        return filterResults;
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void publishResults(CharSequence constraint, FilterResults results) {
        classAdapter.filteredClasses = (ArrayList<UserClass>) results.values;
        classAdapter.notifyDataSetChanged();
    }
}