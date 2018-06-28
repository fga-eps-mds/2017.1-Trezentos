package fga.mds.gpp.trezentos.View.Fragment;

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

import fga.mds.gpp.trezentos.Controller.UserAccountControl;
import fga.mds.gpp.trezentos.R;
import fga.mds.gpp.trezentos.View.Activity.ForgotPasswordActivity;
import fga.mds.gpp.trezentos.View.Activity.SignInActivity;

import static com.facebook.FacebookSdk.getApplicationContext;

public class UserFragment extends Fragment implements View.OnClickListener{

    private ImageView exitButton;
    private Button changePasswordButton;

    private String userEmail;
    private String userId;
    private String userName;
    private String userTelephoneDDI;
    private String userTelephoneDDD;
    private String userTelephoneNumber;

    private TextView profileName;
    private TextView profileEmail;
    private TextView profileTelephoneNumber;
    private TextView profileTelephoneDDD;
    private TextView profileTelephoneDDI;

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

        exitButton = view.findViewById(R.id.exit_button);

        changePasswordButton = view.findViewById(R.id.button_change_password);

        profileName = view.findViewById(R.id.profile_name);
        profileEmail = view.findViewById(R.id.profile_email);
        profileTelephoneDDI = view.findViewById(R.id.profile_telephone_ddi);
        profileTelephoneDDD = view.findViewById(R.id.profile_telephone_ddd);
        profileTelephoneNumber = view.findViewById(R.id.profile_telephone_number);

        exitButton.setOnClickListener(this);
        changePasswordButton.setOnClickListener(this);

        initSharedPreference();

        profileName.setText(userName);
        profileEmail.setText(userEmail);
        profileTelephoneDDD.setText(userTelephoneDDD);
        profileTelephoneDDI.setText(userTelephoneDDI);
        profileTelephoneNumber.setText(userTelephoneNumber);

        return view;
    }

    @Override
    public void onClick(View view){
        switch (view.getId()){
            case R.id.exit_button: {
                //LoginManager.getInstance().logOut();

                UserAccountControl userAccountControl = UserAccountControl.getInstance(getApplicationContext());
                userAccountControl.logOutUser();

                goLoginScreen();

                break;
            }
            case R.id.button_change_password: {
                goChangePasswordScreen();
            }
        }
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }

    private void goLoginScreen() {
        Intent intent = new Intent(getApplicationContext(), SignInActivity.class);

        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK |
                Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    private void goChangePasswordScreen() {
        Intent intent = new Intent(getApplicationContext(), ForgotPasswordActivity.class);
        startActivity(intent);
    }


    private void initSharedPreference(){
        SharedPreferences session = PreferenceManager.getDefaultSharedPreferences(getActivity());

        userEmail = session.getString("userEmail", "");
        userName = session.getString("userFirstName", "") + " " + session.getString("userLastName", "");
        userId = session.getString("userId", "");
        userTelephoneDDI = session.getString("userTelephoneDDI", "");
        userTelephoneDDD = session.getString("userTelephoneDDD", "");
        userTelephoneNumber = session.getString("userTelephoneNumber", "");

    }
}