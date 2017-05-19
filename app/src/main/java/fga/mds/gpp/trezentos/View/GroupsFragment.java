package fga.mds.gpp.trezentos.View;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import fga.mds.gpp.trezentos.Model.Groups;
import fga.mds.gpp.trezentos.R;

public class GroupsFragment  extends Fragment implements RecyclerViewOnClickListener{
    private RecyclerView mRecyclerView;
    private List<Groups> groupses;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container
            , Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_groups
                , container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_groups);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setOnScrollListener(new RecyclerView.OnScrollListener(){
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState){
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy){
                super.onScrolled(recyclerView, dx, dy);

                LinearLayoutManager layoutManager = (LinearLayoutManager)
                        mRecyclerView.getLayoutManager();
                GroupsAdapter adapter = (GroupsAdapter) mRecyclerView.getAdapter();

            }
        });

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);

        groupses = getSetGroupList(4);
        GroupsAdapter adapter = new GroupsAdapter(getActivity(), groupses);
        adapter.setRecyclerViewOnClickListener(this);
        mRecyclerView.setAdapter(adapter);

        return view;
    }

    public List<Groups> getSetGroupList(int quantity){
        String[] groupNumber = new String[]{"Grupo 1", "Grupo 2"
                , "Grupo 3", "Grupo 4"};
        String[] leaderName = new String[]{"Bruna Pinos", "Guilherme Lacerda"
                , "Arthur Diniz", "Guilherme Augusto"};

        List<Groups> listAux = new ArrayList<>();

        for (int i = 0; i < quantity; i++){
            Groups groupses = new Groups(groupNumber[i % groupNumber.length]
                    , leaderName[i % leaderName.length]);
            listAux.add(groupses);
        }

        return (listAux);
    }

    @Override
    public void onClickListener(View view, int position) {
        Toast.makeText(getActivity(), "Position: " + position, Toast.LENGTH_SHORT).show();
    }
}