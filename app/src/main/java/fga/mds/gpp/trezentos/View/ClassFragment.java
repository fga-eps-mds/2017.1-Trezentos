package fga.mds.gpp.trezentos.View;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import fga.mds.gpp.trezentos.Controller.UserClassControl;
import fga.mds.gpp.trezentos.Model.UserClass;
import fga.mds.gpp.trezentos.R;



public class ClassFragment extends Fragment{

    public ArrayList<UserClass> userClasses;
    private FloatingActionButton floatingActionButton;
    private  String userEmail;
    public ProgressBar progressBar;
    public UserClassControl userClassControl;

    public static ClassFragment newInstance() {
        ClassFragment fragment = new ClassFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);




    }

    @Override
    public void onResume(){
        super.onResume();
        new ServerOperation().execute();
    }

    @Override
    public void onStart() {
        super.onStart();

        //loadClasses();


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        final View view = inflater.inflate(R.layout.fragment_class, container, false);

        progressBar = (ProgressBar) view.findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);
        new ServerOperation().execute();
        initFloatingActionButton(view);

        return view;
    }

    private class Adapter extends RecyclerView.Adapter {

        private final ArrayList<UserClass> category;
        private Context context;


        public Adapter(ArrayList<UserClass> category, Context context) {
            this.category = category;
            this.context = context;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View view = LayoutInflater.from(context).inflate(R.layout.user_class_item, parent, false);
            ViewHolder holder = new ViewHolder(view);

            return holder;
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {

            ViewHolder holder = (ViewHolder) viewHolder;


            UserClass userClass  = userClasses.get(position) ;
            holder.className.setText(userClass.getClassName());
            holder.classInstitution.setText(userClass.getInstitution());

        }

        @Override
        public int getItemCount() {

            return category.size();
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        final TextView className;
        final TextView classInstitution;




        public ViewHolder(View view) {
            super(view);
            className = (TextView) view.findViewById(R.id.class_name);
            classInstitution = (TextView) view.findViewById(R.id.class_institution);
        }

    }

    class ServerOperation extends AsyncTask<String, Void, String> {

        public ServerOperation(){}

        @Override
        protected String doInBackground(String... params) {

            userClasses = userClassControl.getClassesFromUser(userEmail);
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            progressBar.setVisibility(View.GONE);

            RecyclerView recyclerView = (RecyclerView) getActivity().findViewById(R.id.recycler);
            recyclerView.setAdapter(new Adapter(userClasses, getActivity().getApplicationContext()));

            final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            recyclerView.setLayoutManager(layoutManager);

        }

        @Override
        protected void onPreExecute() {
            userClassControl = UserClassControl.getInstance(getActivity());
            SharedPreferences session = PreferenceManager.getDefaultSharedPreferences(getActivity());
            userEmail = session.getString("userEmail","");
        }

        @Override
        protected void onProgressUpdate(Void... values) {}
    }

    public void initFloatingActionButton(View view){
        floatingActionButton = (FloatingActionButton) view.findViewById(R.id.class_image_button);
        floatingActionButton.setOnClickListener(new FloatingActionButton.OnClickListener(){
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getActivity(), CreateClassActivity.class));
            }
        });
    }
}