package fga.mds.gpp.trezentos.View;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;

import fga.mds.gpp.trezentos.Model.UserClass;
import fga.mds.gpp.trezentos.R;

public class EvaluationFragment extends Fragment {

    private static EvaluationFragment fragment;

    public static EvaluationFragment getInstance() {

        if(fragment == null){
            fragment = new EvaluationFragment();
        }
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_evaluation, container, false);


        //Generates students static
        ArrayList<String> students = new ArrayList<>();
        students.add("Arthur Diniz");
        students.add("Cafe");

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerEvaluation);
        recyclerView.setAdapter(new EvaluationFragment.AdapterStudents(students, getActivity().getApplicationContext(), recyclerView));

        final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        return view;
    }

    private class AdapterStudents extends RecyclerView.Adapter implements View.OnClickListener {
        private final ArrayList<String> userAccounts;
        private Context context;
        private  RecyclerView recyclerView;
        private  EvaluationFragment.ViewHolder holder;


        public AdapterStudents(ArrayList<String> userAccounts, Context context,  RecyclerView recyclerView) {
            this.userAccounts = userAccounts;
            this.context = context;
            this.recyclerView = recyclerView;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(context).inflate(R.layout.evaluation_item, parent, false);
            EvaluationFragment.ViewHolder holder = new EvaluationFragment.ViewHolder(view);
            view.setOnClickListener(this);

            return holder;
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {

            holder = (EvaluationFragment.ViewHolder) viewHolder;

            String userAccount = userAccounts.get(position);
            holder.userAccountName.setText("Avalie a ajuda do aluno " + userAccount);//
            //holder.circleImageView.setImageResource(userAccount.getPhoto());
        }

        @Override
        public int getItemCount() {
            return userAccounts.size();
        }

        @Override
        public long getItemId(int position) {
            return super.getItemId(position);
        }


        @Override
        public void onClick(View v) {
            int itemPosition = recyclerView.getChildLayoutPosition(v);
        }
    }

    private class ViewHolder extends RecyclerView.ViewHolder implements RatingBar.OnRatingBarChangeListener {
        final TextView userAccountName;
        final RatingBar ratingBar;

        public  ViewHolder(View view) {
            super(view);
            userAccountName = (TextView) view.findViewById(R.id.student_name);
            ratingBar = (RatingBar) view.findViewById(R.id.ratingBar);

            ratingBar.setOnRatingBarChangeListener(this);
        }

        public void showConfirm(float rate){

            final Dialog d = new Dialog(getContext());
            d.setTitle("EvaluationConfirmation");
            d.setContentView(R.layout.dialog_confirm_evaluation);
            Button b1 = (Button) d.findViewById(R.id.button1);
            Button b2 = (Button) d.findViewById(R.id.button2);
            RatingBar ratingBarDialog = (RatingBar) d.findViewById(R.id.ratingBarDialog);
            ratingBarDialog.setRating(rate);

            d.show();


            b1.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v) {
                //Send API
                    // d.dismiss();
                }
            });
            b2.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v) {
                    d.dismiss(); // dismiss the dialog
                }
            });

        }

        @Override
        public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
            showConfirm(rating);
        }
    }
}
