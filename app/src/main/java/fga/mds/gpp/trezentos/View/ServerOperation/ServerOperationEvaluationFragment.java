package fga.mds.gpp.trezentos.View.ServerOperation;

import android.app.Application;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;

import fga.mds.gpp.trezentos.Controller.EvaluationControl;
import fga.mds.gpp.trezentos.Controller.UserClassControl;
import fga.mds.gpp.trezentos.Controller.UserExamControl;
import fga.mds.gpp.trezentos.Exception.UserException;
import fga.mds.gpp.trezentos.Model.Evaluation;
import fga.mds.gpp.trezentos.Model.UserAccount;
import fga.mds.gpp.trezentos.Model.UserClass;
import fga.mds.gpp.trezentos.R;
import fga.mds.gpp.trezentos.View.Fragment.EvaluationFragment;

import static com.facebook.FacebookSdk.getApplicationContext;

public class ServerOperationEvaluationFragment extends AsyncTask<String, Void, String>{

    private Application application;
    private ArrayList<UserClass> userClasses;
    private UserClass userClass;
    private String email;
    private UserClassControl userClassControl;
    private UserExamControl userExamControl;
    private ArrayList<Evaluation> evaluationList;
    private UserAccount userAccount;

    public ServerOperationEvaluationFragment
            (Application application, TextView className, TextView examName){
        this.application = application;
    }

    @Override
    protected String doInBackground(String... params) {
        if(isInternetAvailable() ){ //If internet is ok
            userAccount = new UserAccount();
            try {
                userAccount.setEmail(email);
            } catch (UserException e) {
                e.printStackTrace();
            }

            evaluationList = EvaluationControl.getInstance
                    (getApplicationContext()).getEvaluations(userAccount);

            return "true";

        }else{
            return null;
        }
    }

    @Override
    protected void onPreExecute(){
        userClassControl =
                UserClassControl.getInstance(getApplicationContext());
        userExamControl = UserExamControl.getInstance(getApplicationContext());
        SharedPreferences session = PreferenceManager
                .getDefaultSharedPreferences(EvaluationFragment.getInstance().getActivity());
        email = session.getString("userEmail","");
    }

    @Override
    protected void onPostExecute(String result){
        if(result.equals("true")) {
            RecyclerView recyclerView = (RecyclerView) EvaluationFragment
                    .getInstance()
                    .getActivity()
                    .findViewById(R.id.recyclerEvaluation);

//        recyclerView.setAdapter(new EvaluationAdapter
//                (evaluationList, EvaluationFragment.getInstance().getContext(), recyclerView));

            final LinearLayoutManager layoutManager = new LinearLayoutManager(application);
            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            recyclerView.setLayoutManager(layoutManager);
        }
    }

    private boolean isInternetAvailable() {
        try {
            final InetAddress address = InetAddress.getByName("www.google.com");
            if(!address.equals("")){
                return true;
            }
            return !address.equals("");
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        return false;
    }
}
