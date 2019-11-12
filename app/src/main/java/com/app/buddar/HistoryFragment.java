package com.app.buddar;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.buddar.adapters.ConnectionsVerticalAdapter;
import com.app.buddar.adapters.HistoryVerticalAdapter;
import com.app.buddar.objects.Connection;
import com.app.buddar.objects.History;

import java.util.ArrayList;

/**
 * Perfil Fragment
 * Created by Luis F. Alvarez
 */
public class HistoryFragment extends Fragment implements View.OnClickListener {
    private RecyclerView recyclerHistory;
    /**
     * Perfil Fragment Constructor
     */
    public HistoryFragment() {
    }

    /**
     * onCreateView method
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.history_fragment, container, false);
    }

    /**
     * onViewCreated method
     *
     * @param view
     * @param savedInstanceState
     */
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerHistory = (RecyclerView) view.findViewById(R.id.recyclerHistory);
        //info button
        recyclerHistory.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        ArrayList<History> histories = new ArrayList<History>();
        for(int i = 0 ;i < 20; i++){
            histories.add(new History("History " + ((int)i + 1)));
        }
        HistoryVerticalAdapter adapt = new HistoryVerticalAdapter(histories);
        recyclerHistory.setNestedScrollingEnabled(false);
        recyclerHistory.setAdapter(adapt);
    }

    /**
     * onClick method
     *
     * @param view
     */
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
        }
    }
}