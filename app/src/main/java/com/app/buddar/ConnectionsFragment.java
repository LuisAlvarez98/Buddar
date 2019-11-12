package com.app.buddar;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.app.buddar.adapters.ConnectionsVerticalAdapter;
import com.app.buddar.objects.Connection;

import java.util.ArrayList;

/**
 */
public class ConnectionsFragment extends Fragment implements View.OnClickListener {
    private LinearLayout loaderContainer;
    /**
     * Perfil Fragment Constructor
     */
    private RecyclerView recylerConnections;
    public ConnectionsFragment() {
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
        return inflater.inflate(R.layout.connections_fragment, container, false);
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
        recylerConnections = (RecyclerView) view.findViewById(R.id.recyclerConnections);
        //info button
        recylerConnections.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        ArrayList<Connection> connections = new ArrayList<Connection>();
        for(int i = 0 ;i < 20; i++){
            connections.add(new Connection("Connection " + ((int)i + 1)));
        }
        ConnectionsVerticalAdapter adapt = new ConnectionsVerticalAdapter(connections);
        recylerConnections.setNestedScrollingEnabled(false);
        recylerConnections.setAdapter(adapt);

        loaderContainer = (LinearLayout)view.findViewById(R.id.loaderContainer);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                loaderContainer.setVisibility(View.GONE);
            }
        }, 1000);
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