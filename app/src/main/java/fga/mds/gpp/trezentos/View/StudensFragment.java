package fga.mds.gpp.trezentos.View;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.TextView;


import java.util.ArrayList;


import de.hdodenhof.circleimageview.CircleImageView;
import fga.mds.gpp.trezentos.Model.UserAccount;
import fga.mds.gpp.trezentos.R;


public class StudensFragment extends Fragment implements NumberPicker.OnValueChangeListener {

    private ArrayList<UserAccount> userAccounts;

    public StudensFragment(){
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_studens, container, false);
        //ProgressBar progressBar = (ProgressBar) view.findViewById(R.id.progressBarExam);
        //progressBar.setVisibility(View.VISIBLE);

        userAccounts = new ArrayList<>();
        userAccounts.add(new UserAccount("Arthur Diniz", R.drawable.ic_person));
        userAccounts.add(new UserAccount("Grabriel Climaco", R.drawable.ic_person));
        userAccounts.add(new UserAccount("Ana Carolina", R.drawable.ic_person));
        userAccounts.add(new UserAccount("Elmar", R.drawable.ic_person));
        Log.i("SIZE", "Size" + String.valueOf(userAccounts.size()));



        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerStudents);
        recyclerView.setAdapter(new StudensFragment.AdapterStudents(userAccounts, getActivity().getApplicationContext(), recyclerView));
        Log.i("SIZE", "teste");

        if(recyclerView != null) {

            final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            recyclerView.setLayoutManager(layoutManager);
        }


        return view;


    }

    @Override
    public void onValueChange(NumberPicker picker, int oldVal, int newVal) {

    }

    private class AdapterStudents extends RecyclerView.Adapter implements View.OnClickListener {

        private final ArrayList<UserAccount> userAccounts;
        private Context context;
        private  RecyclerView recyclerView;
        private  StudensFragment.ViewHolder holder;


        public AdapterStudents(ArrayList<UserAccount> userAccounts, Context context,  RecyclerView recyclerView) {
            this.userAccounts = userAccounts;
            this.context = context;
            this.recyclerView = recyclerView;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View view = LayoutInflater.from(context).inflate(R.layout.student_item, parent, false);
            StudensFragment.ViewHolder holder = new StudensFragment.ViewHolder(view);
            view.setOnClickListener(this);

            return holder;
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {

            holder = (StudensFragment.ViewHolder) viewHolder;


            UserAccount userAccount = userAccounts.get(position) ;
            holder.userAccountName.setText(userAccount.getName());//
            holder.circleImageView.setImageResource(userAccount.getPhoto());


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



//            if(v.getId() == R.id.presence){
//                holder.noPresence.setVisibility(View.VISIBLE);
//                holder.presence.setVisibility(View.GONE);
//            }
//            if(v.getId() == R.id.no_presence){
//                holder.noPresence.setVisibility(View.GONE);
//                holder.presence.setVisibility(View.VISIBLE);
//            }
            //Exam exam = exams.get(itemPosition);



        }
    }


    private class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, NumberPicker.OnValueChangeListener {

        final TextView userAccountName;
        final ImageView presence;
        final ImageView noPresence;
        final CircleImageView circleImageView;
        final LinearLayout gradeLayout;
        final TextView gradeTextView;


        public ViewHolder(View view) {
            super(view);
            userAccountName = (TextView) view.findViewById(R.id.student_name);
            presence = (ImageView) view.findViewById(R.id.presence);
            noPresence= (ImageView) view.findViewById(R.id.no_presence);
            circleImageView = (CircleImageView) view.findViewById(R.id.profile_image);
            gradeLayout = (LinearLayout) view.findViewById(R.id.gradeLayout);
            gradeTextView = (TextView) view.findViewById(R.id.text_view_grade);

            presence.setOnClickListener(this);
            noPresence.setOnClickListener(this);
            gradeLayout.setOnClickListener(this);


//            presence.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    noPresence.setVisibility(View.VISIBLE);
//                    presence.setVisibility(View.GONE);
//                }
//            });
//
//            noPresence.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    noPresence.setVisibility(View.GONE);
//                    presence.setVisibility(View.VISIBLE);
//                }
//            });


        }


        @Override
        public void onClick(View v) {
            switch (v.getId()){

                case R.id.presence:
                    noPresence.setVisibility(View.VISIBLE);
                    presence.setVisibility(View.GONE);

                    break;

                case R.id.no_presence:
                    noPresence.setVisibility(View.GONE);
                    presence.setVisibility(View.VISIBLE);

                    break;


                case R.id.gradeLayout:
                    final Dialog d = new Dialog(getContext());
                    d.setTitle("NumberPicker");
                    d.setContentView(R.layout.dialog);
                    Button b1 = (Button) d.findViewById(R.id.button1);
                    Button b2 = (Button) d.findViewById(R.id.button2);

                    final NumberPicker np1 = (NumberPicker) d.findViewById(R.id.numberPicker1);
                    np1.setMinValue(0);   // min value 0
                    np1.setMaxValue(10); // max value 100
                    np1.setWrapSelectorWheel(false);
                    np1.setOnValueChangedListener(this);


                    final NumberPicker np2 = (NumberPicker) d.findViewById(R.id.numberPicker2);
                    np2.setMinValue(0);   // min value 0
                    np2.setMaxValue(99); // max value 100
                    np2.setWrapSelectorWheel(false);
                    np2.setOnValueChangedListener(this);



                    b1.setOnClickListener(new View.OnClickListener()
                    {
                        @Override
                        public void onClick(View v) {
                           gradeTextView.setText(String.valueOf(np1.getValue()) + "." + String.valueOf(np2.getValue())); //set the value to textview

                            d.dismiss();
                        }
                    });
                    b2.setOnClickListener(new View.OnClickListener()
                    {
                        @Override
                        public void onClick(View v) {
                            d.dismiss(); // dismiss the dialog
                        }
                    });
                    d.show();
                    break;



            }
        }

        @Override
        public void onValueChange(NumberPicker picker, int oldVal, int newVal) {

        }
    }


}
