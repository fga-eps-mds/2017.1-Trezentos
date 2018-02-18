package fga.mds.gpp.trezentos.View.ServerOperation;

import android.app.Application;
import android.app.Dialog;
import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;

import fga.mds.gpp.trezentos.View.Adapters.ClassFragmentAdapter;
import fga.mds.gpp.trezentos.Controller.UserClassControl;
import fga.mds.gpp.trezentos.DAO.RequestHandler;
import fga.mds.gpp.trezentos.DAO.URLs;
import fga.mds.gpp.trezentos.Model.UserClass;
import fga.mds.gpp.trezentos.R;
import fga.mds.gpp.trezentos.View.Fragment.ExploreFragment;
import fga.mds.gpp.trezentos.View.ViewHolder.ClassViewHolder;

import static com.facebook.FacebookSdk.getApplicationContext;

public class ServerOperationExploreFragment extends AsyncTask<String, Void, ArrayList<UserClass>> {


    public UserClassControl userClassControl;
    public ArrayList<UserClass> userClasses;

    private boolean isInit;
    private ProgressBar progressBar;
    private SwipeRefreshLayout swipeRefreshLayout;
    private ClassFragmentAdapter classFragmentAdapter;
    private RecyclerView recyclerView;
    private ExploreFragment exploreFragment;

    public ServerOperationExploreFragment(boolean isInit,
                                          SwipeRefreshLayout swipeRefreshLayout,
                                          ProgressBar progressBar,
                                          RecyclerView recyclerView,
                                          ExploreFragment exploreFragment){
        this.isInit = isInit;
        this.swipeRefreshLayout = swipeRefreshLayout;
        this.progressBar = progressBar;
        this.exploreFragment = exploreFragment;
        this.recyclerView = recyclerView;

        userClassControl = UserClassControl.getInstance(getApplicationContext());

    }

    @Override
    protected void onPreExecute() {

        if(isInit){
            progressBar.setVisibility(View.VISIBLE);
        }
        super.onPreExecute();
    }

    @Override
    protected ArrayList<UserClass> doInBackground(String... strings) {

        if(isInternetAvailable() ) { //If internet is ok

            try {
                return userClassControl.getExploreClasses();
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }else{
            return null;
        }
        return null;

    }

    @Override
    protected void onPostExecute(ArrayList<UserClass> result) {

        exploreFragment.setUserClasses(result);
        userClasses = result;

        if(isInit){
            progressBar.setVisibility(View.GONE);
        }else{
            swipeRefreshLayout.setRefreshing(false);
        }
        recyclerView.setVisibility(View.VISIBLE);

        classFragmentAdapter = new ClassFragmentAdapter(result, exploreFragment.getContext());


        classFragmentAdapter.setOnItemClickListener(callJoinClass());

        RecyclerView.LayoutManager layout = new LinearLayoutManager(exploreFragment.getContext(),
                                                                    LinearLayoutManager.VERTICAL,
                                                                    false);
        recyclerView.setLayoutManager(layout);
        recyclerView.setAdapter(classFragmentAdapter);

        super.onPostExecute(result);
    }

    public void setLayout(){
        recyclerView.setVisibility(View.VISIBLE);
        userClasses = exploreFragment.getUserClasses();
        classFragmentAdapter = new ClassFragmentAdapter(exploreFragment.getUserClasses(), exploreFragment.getContext());

        classFragmentAdapter.setOnItemClickListener(callJoinClass());

        RecyclerView.LayoutManager layout = new LinearLayoutManager(exploreFragment.getContext(),
                LinearLayoutManager.VERTICAL,
                false);
        recyclerView.setLayoutManager(layout);
        recyclerView.setAdapter(classFragmentAdapter);
    }

    public boolean isNetworkAvailable(Context context) {
        final ConnectivityManager connectivityManager =
            ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE));

        return connectivityManager.getActiveNetworkInfo() != null &&
                connectivityManager.getActiveNetworkInfo().isConnected();
    }

    private boolean isInternetAvailable() {
        try {
            final InetAddress address = InetAddress.getByName("www.google.com");
            if(!address.equals("")){
                return true;
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return false;
    }

    private ClassViewHolder.OnItemClickListener callJoinClass() {
        return new ClassViewHolder.OnItemClickListener() {
            @Override
            public void onItemClick(View itemView, int position) {
                UserClass userClass = userClasses.get(position);
                showJoinClassFragment(userClass);
            }
        };
    }

    private void showJoinClassFragment(final UserClass userClass) {
        // custom dialog
        final Dialog dialog = new Dialog(exploreFragment.getContext());
        dialog.setContentView(R.layout.dialog_class_password);
        dialog.setTitle("Title...");

        TextView textTitle = dialog.findViewById(R.id.text_title);
        final TextView textViewClassPassword = dialog.findViewById(R.id.class_passwort);
        textTitle.setText(userClass.getClassName());

        Button dialogButtonOk = dialog.findViewById(R.id.dialogButtonOK);
        Button dialogButtonCancel = dialog.findViewById(R.id.dialogButtonCANCEL);
        // if button is clicked, close the custom dialog
        dialogButtonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String classPassword = String.valueOf(textViewClassPassword.getText());
                String classId = userClass.getIdClass();
                SharedPreferences session = PreferenceManager
                        .getDefaultSharedPreferences(getApplicationContext());

                String userId = session.getString("userId","");

                RequestHandler requestHandler = new RequestHandler(URLs.URL_INSERT_STUDENT_CLASS, getInsertStudentParams(userId, classId, classPassword));

                String serverResponse = "404";

                try{
                    serverResponse = requestHandler.execute().get();
                }catch(InterruptedException e){
                    e.printStackTrace();
                }catch(ExecutionException e){
                    e.printStackTrace();
                }
                Log.d("RESPONSE", "" + serverResponse);

                JSONObject object = null;
                try {
                    object = new JSONObject(serverResponse);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                boolean error = false;
                String message = "";
                try {
                    error = object.getBoolean("error");
                    message = object.getString("message");
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                if(error) {
                    Toast.makeText(getApplicationContext(),message,
                            Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getApplicationContext(),"Sala adicionada a sua salas",
                            Toast.LENGTH_SHORT).show();
                }
                dialog.dismiss();


            }
        });

        dialogButtonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    private HashMap<String, String> getInsertStudentParams(String userId, String classId, String classPassword) {

        HashMap<String, String> params = new HashMap<>();
        params.put("idPerson", userId);
        params.put("idClass", classId);
        params.put("classPassword", classPassword);


        return params;

    }


}
