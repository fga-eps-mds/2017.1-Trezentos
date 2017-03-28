package fga.mds.gpp.trezentos.View;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;



public class UserDialog extends AsyncTask<Void, Void, Void> {

    private AlertDialog.Builder alert;
    private Context context;
    ProgressDialog asyncDialog;

    private String progressMessage;

    //Shows an alert dialog with custom message
    public void alert(String alertTitle, String  alertMessage){

        new AlertDialog.Builder(context)
                .setTitle(alertTitle)
                .setMessage(alertMessage)
                .setCancelable(false)
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //TODO
                    }
                }).show();

    }

    //Creates an progres dialog with custom message
    public void progressCreate(String progressMessage){
        asyncDialog = new ProgressDialog(context);
        String typeStatus;
        asyncDialog.setMessage(progressMessage);
        asyncDialog.show();

    }

    //Ends an progress dialog with custom message
    public void progressDismiss(){
        asyncDialog.dismiss();


    }

    public void setContext(Context context){
        this.context = context;
    }

    public void setProgressMessage(String progressMessage){
        this.progressMessage = progressMessage;

    }

    @Override
    protected void onPreExecute() {
        progressCreate(progressMessage);
    }

    @Override
    protected void onPostExecute(Void result) {
        progressDismiss();

    }

    @Override
    protected Void doInBackground(Void... params) {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return null;
    }

}
