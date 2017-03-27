package fga.mds.gpp.trezentos;

import android.app.DialogFragment;
import android.content.Context;
import android.support.v7.app.AlertDialog;


public class UserDialog extends DialogFragment {

    private AlertDialog.Builder alert;
    private Context context;

    public UserDialog() {

    }


    public void alertDialog(String message){
        alert = new AlertDialog.Builder(context);
        alert.setPositiveButton("Ok", null);
        alert.create().show();

    }

    public void setContext(Context context){
        this.context = context;
    }



}
