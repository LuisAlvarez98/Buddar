package com.app.buddar;

import android.content.Intent;
import android.icu.text.IDNA;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.app.buddar.adapters.ChatsHorizontalAdapter;
import com.app.buddar.adapters.ConnectionsVerticalAdapter;
import com.app.buddar.adapters.HistoryVerticalAdapter;
import com.app.buddar.adapters.InfoHorizontalList;
import com.app.buddar.objects.ChatItem;
import com.app.buddar.objects.Connection;
import com.app.buddar.objects.InfoItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

import static com.app.buddar.util.RestAdapter.getUnsafeOkHttpClient;

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
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(Api.BASE_URL)
            .client(getUnsafeOkHttpClient().build())
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    Api apiInterface = retrofit.create(Api.class);
    InfoHorizontalList adaptInfo;
    ChatsHorizontalAdapter adaptChat;
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
        createConnection = (Button)view.findViewById(R.id.createConnection);
        createConnection.setOnClickListener(this);
        loaderContainer = (LinearLayout) view.findViewById(R.id.loaderContainer);
        //Load chat items into adapter
        recyclerChat = (RecyclerView) view.findViewById(R.id.recyclerChat);
        //info button
        recyclerChat.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        Call<String> call1 = apiInterface.getChatList();
        call1.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                switch (response.code()) {
                    case 200:

                        try {
                            ArrayList<ChatItem>chatItem = new ArrayList<ChatItem>();
                            JSONObject jsonObject = new JSONObject(response.body());
                            JSONObject parsedJson = new JSONObject( jsonObject.get("chatList").toString());
                            JSONArray historyJson = new JSONArray( parsedJson.get("list").toString());

                            int len = historyJson.length();
                            for (int i=0;i<len;i++){
                                ChatItem info = new ChatItem();
                                JSONObject json = new JSONObject(historyJson.get(i).toString());
                                JSONObject user = new JSONObject(json.get("user").toString());
                                info.setUrl(user.getString("picture"));
                                chatItem.add(info);
                            }
                            adaptChat = new ChatsHorizontalAdapter(chatItem);
                            recyclerChat.setNestedScrollingEnabled(false);
                            recyclerChat.setAdapter(adaptChat);
                            loaderContainer.setVisibility(View.GONE);
                        }catch (JSONException err){
                            Log.d("Error", err.toString());
                        }
                        break;
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        //Load info items into adapter
        ///////---------------------------------
        recyclerInfo = (RecyclerView) view.findViewById(R.id.recyclerInfo);
        //info button
        recyclerInfo.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        Call<String> call2 = apiInterface.getRecentConnections();
        call2.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                switch (response.code()) {
                    case 200:

                        try {
                            ArrayList<InfoItem>infos = new ArrayList<InfoItem>();
                            JSONObject jsonObject = new JSONObject(response.body());
                            JSONObject parsedJson = new JSONObject( jsonObject.get("connections").toString());
                            JSONArray historyJson = new JSONArray( parsedJson.get("connections").toString());

                            int len = historyJson.length();
                            for (int i=0;i<len;i++){
                                InfoItem info = new InfoItem();
                                JSONObject json = new JSONObject(historyJson.get(i).toString());
                                JSONObject user = new JSONObject(json.get("user").toString());
                                info.setEmail(user.getString("email"));
                                info.setName(user.getString(("name")));
                                info.setBio(user.getString(("bio")));
                                info.setPicture(user.getString("picture"));
                                infos.add(info);

                            }
                            adaptInfo = new InfoHorizontalList(infos);
                            recyclerInfo.setNestedScrollingEnabled(false);
                            recyclerInfo.setAdapter(adaptInfo);
                            loaderContainer.setVisibility(View.GONE);
                        }catch (JSONException err){
                            Log.d("Error", err.toString());
                        }
                        break;
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });


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
                Intent loginIntent = new Intent(view.getContext(), Step1Activity.class);
                startActivity(loginIntent);
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