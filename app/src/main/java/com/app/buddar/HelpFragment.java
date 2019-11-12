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

import com.app.buddar.adapters.FAQVerticalAdapter;
import com.app.buddar.objects.FAQ;

import java.util.ArrayList;

/**
 * Perfil Fragment
 * Created by Luis F. Alvarez
 */
public class HelpFragment extends Fragment implements View.OnClickListener {
    private LinearLayout loaderContainer;
    private RecyclerView recyclerFAQ;
    /**
     * Perfil Fragment Constructor
     */
    public HelpFragment() {
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
        return inflater.inflate(R.layout.help_fragment, container, false);
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
        recyclerFAQ = (RecyclerView) view.findViewById(R.id.recyclerFAQ);
        //info button
        recyclerFAQ.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        ArrayList<FAQ> histories = new ArrayList<FAQ>();
        for(int i = 0 ;i < 5; i++){
            histories.add(new FAQ("What is Buddar?","Buddar is an app where you can connect with people around your community that has similar interests."));
        }
        FAQVerticalAdapter adapt = new FAQVerticalAdapter(histories);
        recyclerFAQ.setNestedScrollingEnabled(false);
        recyclerFAQ.setAdapter(adapt);
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