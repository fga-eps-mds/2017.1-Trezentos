package fga.mds.gpp.trezentos.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import fga.mds.gpp.trezentos.Model.UserClass;
import fga.mds.gpp.trezentos.R;

public class ClassActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class);

        Intent intent = getIntent();
        UserClass userClass = (UserClass) intent.getSerializableExtra("Class");
        if(userClass != null){
            TextView textView = (TextView) findViewById(R.id.testClass);
            textView.setText(userClass.getClassName());
        }
    }
}
