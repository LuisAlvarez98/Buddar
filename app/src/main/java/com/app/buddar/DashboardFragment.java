package com.app.buddar;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.app.buddar.adapters.ChatsHorizontalAdapter;
import com.app.buddar.adapters.InfoHorizontalList;
import com.app.buddar.objects.ChatItem;
import com.app.buddar.objects.InfoItem;

import java.util.ArrayList;

/**
 * Dashboard Fragment - Displays categories
 * Luis Felipe Alvarez Sanchez
 */
public class DashboardFragment extends Fragment implements View.OnClickListener {
    ImageButton misProductos, eventosEnCurso, solicitudesDeEventos, historialDeEventos, miPerfil;
    ProfileFragment profileFragment;
    private LinearLayout loaderContainer;
    private RecyclerView recyclerChat, recyclerInfo;


    private Button createConnection;

    /**
     * Dashboard Fragment Constructor
     */
    public DashboardFragment() {
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
        return inflater.inflate(R.layout.fragment_dashboard, container, false);
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
        //open dialog new event
        //showPopup(view);
        profileFragment = new ProfileFragment();

        //Load chat items into adapter
        recyclerChat = (RecyclerView) view.findViewById(R.id.recyclerChat);
        //info button
        recyclerChat.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        ArrayList<ChatItem> chats = new ArrayList<ChatItem>();
        for (int i = 0; i < 20; i++) {
            chats.add(new ChatItem("", "", ""));
        }
        ChatsHorizontalAdapter adapt = new ChatsHorizontalAdapter(chats);
        recyclerChat.setNestedScrollingEnabled(false);
        recyclerChat.setAdapter(adapt);
        //Load info items into adapter
        ///////---------------------------------
        recyclerInfo = (RecyclerView) view.findViewById(R.id.recyclerInfo);
        //info button
        recyclerInfo.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        ArrayList<InfoItem> infos = new ArrayList<InfoItem>();
        for (int i = 0; i < 20; i++) {
            infos.add(new InfoItem("", "", ""));
        }
        InfoHorizontalList adapt2 = new InfoHorizontalList(infos);
        recyclerInfo.setNestedScrollingEnabled(false);
        recyclerInfo.setAdapter(adapt2);

        createConnection = (Button)view.findViewById(R.id.createConnection);
        createConnection.setOnClickListener(this);
        loaderContainer = (LinearLayout) view.findViewById(R.id.loaderContainer);
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
            case R.id.createConnection:
                //Goto create connection
                Toast.makeText(getContext(), "Create connection", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    /**
     * Sets the fragment
     *
     * @param fragment
     */
    private void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.commit();
    }
}