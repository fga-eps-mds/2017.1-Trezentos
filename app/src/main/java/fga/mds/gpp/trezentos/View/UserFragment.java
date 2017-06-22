package fga.mds.gpp.trezentos.View;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.login.LoginManager;

import org.w3c.dom.Text;

import fga.mds.gpp.trezentos.Controller.UserAccountControl;
import fga.mds.gpp.trezentos.R;

import static com.facebook.FacebookSdk.getApplicationContext;

public class UserFragment extends Fragment implements View.OnClickListener{

    private ImageView exitButton;
    private TextView profileName;
    private String userEmail;
    private String userName;
    private TextView profileEmail;
    private UserAccountControl userAccountControl;
    private OnFragmentInteractionListener mListener;
    private static UserFragment fragment;

    public static UserFragment getInstance() {
        if(fragment == null){
            fragment = new UserFragment();
        }
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_user, container, false);

        exitButton = (ImageView) view.findViewById(R.id.exit_button);
        profileName = (TextView) view.findViewById(R.id.profile_name);
        profileEmail = (TextView) view.findViewById(R.id.profile_email);

        exitButton.setOnClickListener(this);

        initSharedPreference();

        profileName.setText(userName);
        profileEmail.setText(userEmail);

        return view;
    }

    @Override
    public void onClick(View view){
        switch (view.getId()){
            case R.id.exit_button: {
                LoginManager.getInstance().logOut();

                UserAccountControl userAccountControl = UserAccountControl.getInstance(getApplicationContext());
                userAccountControl.logOutUser();

                goLoginScreen();

                break;
            }
        }
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }

    private void goLoginScreen() {
        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);

        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK |
                Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    private void initSharedPreference(){
        SharedPreferences session = PreferenceManager.getDefaultSharedPreferences(getActivity());

        userEmail = session.getString("userEmail", "");
        userName = session.getString("userName", "");
    }
}