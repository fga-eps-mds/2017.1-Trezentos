package fga.mds.gpp.trezentos.View.Fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import fga.mds.gpp.trezentos.Controller.GroupController;
import fga.mds.gpp.trezentos.Model.Exam;
import fga.mds.gpp.trezentos.Model.UserClass;
import fga.mds.gpp.trezentos.R;
import fga.mds.gpp.trezentos.View.RecyclerViewOnClickListener;

public class GroupsFragment  extends Fragment implements RecyclerViewOnClickListener {
    private RecyclerView mRecyclerView;
    private GroupController groupController;
    private Map<String, Double> firstGrades;
    private Map<String, Integer> groupses;
    private Exam exam;
    private ProgressBar progressBar;
    private UserClass userClass;
    private TextView groupWarning;

    @Override
    public void onResume() {
        super.onResume();
        if(userClass.getStudents() != null) {
            new ServerOperation().execute();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container
            , Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_groups, container, false);
        Intent intent = getActivity().getIntent();
        exam = (Exam) intent.getSerializableExtra("Exam");
        userClass = (UserClass) intent.getSerializableExtra("Class");

        progressBar = (ProgressBar) view.findViewById(R.id.progressBarGroups);
        progressBar.setVisibility(View.VISIBLE);

        groupWarning = (TextView) view.findViewById(R.id.not_defined_groups);

        checkGroups();

        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        if(userClass.getStudents().size() < userClass.getSizeGroups()) {
            menu.findItem(R.id.action_sort_groups).setVisible(false);
        } else {
            menu.findItem(R.id.action_sort_groups).setVisible(true);
        }
    }

    private void checkGroups() {
        if(userClass.getStudents().size() < userClass.getSizeGroups()) {
            groupWarning.setVisibility(View.VISIBLE);
        } else {
            groupWarning.setVisibility(View.GONE);
        }
    }

    private class ServerOperation extends AsyncTask<String, Void, String> {

        ServerOperation(){
        }

        @Override
        protected String doInBackground(String... params) {
            if(isNetworkAvailable(getContext()) && isInternetAvailable()){ //If internet is ok
//                groupses = GroupController.getGroups(exam.getNameExam(),
//                        exam.getUserClassName(), exam.getClassOwnerEmail());
//                firstGrades = GroupController.getFirstGrades(exam.getNameExam(),
//                        userClass.getClassName(), userClass.getOwnerEmail());
                return "true";

            }else{
                return null;

            }
        }

        @Override
        protected void onPostExecute(String result) {
            if (getActivity() != null) {
                progressBar.setVisibility(View.GONE);
                checkGroups();

                RecyclerView recyclerView = (RecyclerView) getActivity().findViewById(R.id.recycler_groups);
                recyclerView.setVisibility(View.VISIBLE);
                recyclerView.setAdapter(new GroupsFragment.Adapter(groupses,
                        getActivity().getApplicationContext(), userClass,
                        recyclerView, firstGrades));

                final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
                layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                recyclerView.setLayoutManager(layoutManager);
            }
        }

        @Override
        protected void onPreExecute() {
            SharedPreferences session = PreferenceManager.getDefaultSharedPreferences(getActivity());
        }

        @Override
        protected void onProgressUpdate(Void... values) {}

        public boolean isNetworkAvailable(Context context) {
            if (context == null){return false;}

            final ConnectivityManager connectivityManager = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE));
            return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
        }

        public boolean isInternetAvailable() {
            try {
                final InetAddress address = InetAddress.getByName("www.google.com");
                if(!address.equals("")){
                    return true;
                }

            } catch (UnknownHostException e) {

            }
            return false;
        }
    }

    private class Adapter extends RecyclerView.Adapter implements View.OnClickListener {

        private final Map<String, Integer> groupses;
        private final Map<String, Double> firstGrades;
        private final Context context;
        private final UserClass userClass;
        private  RecyclerView recyclerView;


        Adapter(Map<String, Integer> groupses, Context context, UserClass userClass,
                RecyclerView recyclerView, Map<String, Double> firstGrades) {
            this.groupses = groupses;
            this.context = context;
            this.userClass = userClass;
            this.recyclerView = recyclerView;
            this.firstGrades = firstGrades;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(context).inflate(R.layout.groups_item, parent, false);
            GroupsFragment.ViewHolder holder = new GroupsFragment.ViewHolder(view);
            view.setOnClickListener(this);

            return holder;
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
            GroupsFragment.ViewHolder holder = (GroupsFragment.ViewHolder) viewHolder;

            holder.groupTitle.setText("Grupo " + Integer.valueOf(position + 1));

            List<Map.Entry<String, Integer>> groupMembers = new LinkedList<>(groupses.entrySet());

            String helpers = "";
            String helped = "";

            for (Map.Entry<String, Integer> group : groupMembers) {
                if (group.getValue() == position + 1) {
                    if (firstGrades.get(group.getKey()) >= userClass.getCutOff()) {
                        helpers = helpers.concat(group.getKey() + ", ");
                    } else {
                        helped = helped.concat(group.getKey() + ", ");
                    }
                }
            }

            if (!helpers.equals("") && !helped.equals("")) {
                holder.helpers.setText(helpers.substring(0, helpers.length()-2));
                holder.helped.setText(helped.substring(0, helped.length()-2));
            }
        }

        @Override
        public int getItemCount() {
            if (groupses == null) return 0;

            return groupses.size()/userClass.getSizeGroups();
        }

        @Override
        public long getItemId(int position) {
            return super.getItemId(position);
        }


        @Override
        public void onClick(View v) {
            // do nothing for now
        }
    }

    private class ViewHolder extends RecyclerView.ViewHolder {

        TextView groupTitle;
        TextView helpers;
        TextView helped;

        ViewHolder(View view) {
            super(view);
            groupTitle = (TextView) view.findViewById(R.id.group_number);
            helpers = (TextView) view.findViewById(R.id.helpers_content);
            helped = (TextView) view.findViewById(R.id.helped_content);
        }
    }

    @Override
    public void onClickListener(View view, int position) {
        Toast.makeText(getActivity(), "Position: " + position, Toast.LENGTH_SHORT).show();
    }
}