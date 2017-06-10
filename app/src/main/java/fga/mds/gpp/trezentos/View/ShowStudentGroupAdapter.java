package fga.mds.gpp.trezentos.View;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import fga.mds.gpp.trezentos.Model.Student;
import fga.mds.gpp.trezentos.R;

public class ShowStudentGroupAdapter  extends RecyclerView.Adapter 
        implements View.OnClickListener {
    
    private final ArrayList<Student> studentGroupAndGrades;
    private Context context;
    private RecyclerView recyclerView;
    private ClassViewHolder.OnItemClickListener listener;

    public void setOnItemClickListener(ClassViewHolder.OnItemClickListener listener) {
        this.listener = listener;
    }

   ShowStudentGroupAdapter(ArrayList<Student> studentGroupAndGrades,
                           Context context, RecyclerView recyclerView) {
        this.studentGroupAndGrades = studentGroupAndGrades;
        this.context = context;
        this.recyclerView = recyclerView;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.class_item, parent, false);
        ClassViewHolder holder = new ClassViewHolder(view);
//        view.setOnClickListener(this);
        holder.setOnItemClickListener(listener);

        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        Student student  = studentGroupAndGrades.get(position) ;
        ShowStudentGroupViewHolder studentGroupViewHolder
                = (ShowStudentGroupViewHolder) holder;

        Log.d("CLASSNAME", student.getStudentEmail() + " " + position);
        studentGroupViewHolder.studentEmail.setText(student.getStudentEmail());
        studentGroupViewHolder.firstGrade
                .setText(student.getFirstGrade().toString());

        if(student.getSecondGrade() != null) {
            studentGroupViewHolder.secondGrade
                    .setText(student.getSecondGrade().toString());
        }else{
            studentGroupViewHolder.secondGrade
                    .setText(" - ");
        }
    }

    @Override
    public int getItemCount() {
        return studentGroupAndGrades.size();
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public void onClick(View v) {

    }
}
