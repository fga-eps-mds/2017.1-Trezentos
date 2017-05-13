package fga.mds.gpp.trezentos.View;

import android.app.Dialog;
import android.app.Fragment;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.ShareCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.concurrent.ExecutionException;

import fga.mds.gpp.trezentos.Controller.UserClassControl;
import fga.mds.gpp.trezentos.Exception.UserClassException;
import fga.mds.gpp.trezentos.Model.UserClass;
import fga.mds.gpp.trezentos.R;

import static com.facebook.FacebookSdk.getApplicationContext;

public class JoinClassFragment extends DialogFragment implements View.OnClickListener {

    private UserClass userClass;
    private UserClassControl userClassControl;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        userClass = (UserClass) getArguments().getSerializable("userClass");
        getDialog().setTitle(userClass.getClassName());

        final View view = inflater.inflate(R.layout.fragment_join_class, container, false);

        Button button = (Button) view.findViewById(R.id.join_class_button);
        button.setOnClickListener(this);

        userClassControl = UserClassControl.getInstance(getActivity());

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case  R.id.join_class_button:
                validatePasswordAndJoinStudent();
                break;
        }
    }

    private void validatePasswordAndJoinStudent() {
        EditText password = (EditText) getView().findViewById(R.id.join_password_editText);

        try {
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            String email = preferences.getString("userEmail", null);

            String result = userClassControl.validateJoinClass(userClass, password.getText().toString(), email);
            Toast.makeText(getActivity(), result, Toast.LENGTH_SHORT).show();
            if (result.contains("true")) {
                Toast.makeText(getActivity(), getActivity().getString(R.string.join_class_success), Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getActivity(), getActivity().getString(R.string.join_class_failed), Toast.LENGTH_SHORT).show();
            }
            getDialog().dismiss();
        } catch (UserClassException e) {
            password.setError(e.getMessage());
            e.printStackTrace();
        } catch (InterruptedException e) {
            Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        } catch (ExecutionException e) {
            Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }
}

//    to properly open dialog
//
//    Bundle bundle = new Bundle();
//    bundle.putSerializable("userClass", userClassCalled);
//
//    FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
//    JoinClassFragment joinClassFragment = new JoinClassFragment();
//    joinClassFragment.setArguments(bundle);
//    joinClassFragment.show(fragmentTransaction, "joinClass");
