package fga.mds.gpp.trezentos.View;

import android.app.Application;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;

import fga.mds.gpp.trezentos.Controller.UserClassControl;
import fga.mds.gpp.trezentos.DAO.RequestHandler;
import fga.mds.gpp.trezentos.DAO.URLs;
import fga.mds.gpp.trezentos.Model.UserAccount;
import fga.mds.gpp.trezentos.Model.UserClass;
import fga.mds.gpp.trezentos.R;

import static com.facebook.FacebookSdk.getApplicationContext;

public class ServerOperationExploreFragment extends AsyncTask<String, Void, String> {

    public ArrayList<UserClass> userClasses = null;
    private  String userEmail;
    private ClassFragmentAdapter classFragmentAdapter;
    public ProgressBar progressBar;
    public UserClassControl userClassControl;
    private LinearLayout noInternetLayout;
    private String email;
    private String userId;
    private Application application;
    private ExploreFragment exploreFragment;


    private RecyclerView recyclerView;

    public ServerOperationExploreFragment(Application application,
                                          ProgressBar progressBar,
                                          LinearLayout noInternetLayout,
                                          ExploreFragment exploreFragment){
        this.application = application;
        this.progressBar = progressBar;
        this.noInternetLayout = noInternetLayout;

        this.exploreFragment = exploreFragment;
    }

    @Override
    protected void onPreExecute() {

        userClassControl =
                UserClassControl.getInstance(getApplicationContext());
        SharedPreferences session = PreferenceManager
                .getDefaultSharedPreferences(getApplicationContext());
        email = session.getString("userEmail","");
        userId = session.getString("userId","");
    }

    @Override
    protected void onProgressUpdate(Void... values) {}

    @Override
    protected String doInBackground(String... params) {

        if(isInternetAvailable() ) { //If internet is ok

            try {
                userClasses = userClassControl.getExploreClasses();
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return "true";
        }else{
            return null;
        }

    }

    @Override
    protected void onPostExecute(String result) {
        progressBar.setVisibility(View.GONE);

        if(result.equals("true")){
                if (exploreFragment.getActivity() != null) {

                    recyclerView = (RecyclerView) exploreFragment.getActivity().findViewById(R.id.recycler_explore);
                    recyclerView.setVisibility(View.VISIBLE);


                    classFragmentAdapter = new ClassFragmentAdapter(userClasses,
                            exploreFragment.getContext());


                    classFragmentAdapter.setOnItemClickListener(callJoinClass());

                    RecyclerView.LayoutManager layout = new LinearLayoutManager
                            (application, LinearLayoutManager.VERTICAL, false);
                    recyclerView.setLayoutManager(layout);

                    recyclerView.setAdapter(classFragmentAdapter);
                }

        }else{

            noInternetLayout.setVisibility(View.VISIBLE);

        }
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

        TextView textTitle = (TextView) dialog.findViewById(R.id.text_title);
        final TextView textViewClassPassword = (TextView) dialog.findViewById(R.id.class_passwort);
        textTitle.setText(userClass.getClassName());

        Button dialogButtonOk = (Button) dialog.findViewById(R.id.dialogButtonOK);
        Button dialogButtonCancel = (Button) dialog.findViewById(R.id.dialogButtonCANCEL);
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

    public HashMap<String, String> getInsertStudentParams(String userId, String classId, String classPassword) {

        HashMap<String, String> params = new HashMap<>();
        params.put("idPerson", userId);
        params.put("idClass", classId);
        params.put("classPassword", classPassword);


        return params;

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

}
