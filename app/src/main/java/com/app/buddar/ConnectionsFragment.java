package com.app.buddar;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.app.buddar.adapters.ConnectionsVerticalAdapter;
import com.app.buddar.adapters.HistoryVerticalAdapter;
import com.app.buddar.objects.Connection;
import com.app.buddar.objects.History;

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
 * ConnectionsFragment
 * Displays all the connections of the user
 */
public class ConnectionsFragment extends Fragment {
    private LinearLayout loaderContainer;

    private RecyclerView recylerConnections;
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(Api.BASE_URL)
            .client(getUnsafeOkHttpClient().build())
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    Api apiInterface = retrofit.create(Api.class);
    ConnectionsVerticalAdapter adapt;

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
        Call<String> call2 = apiInterface.getConnections();
        call2.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                switch (response.code()) {
                    case 200:

                        try {
                            ArrayList<Connection> connections = new ArrayList<Connection>();
                            JSONObject jsonObject = new JSONObject(response.body());
                            JSONObject parsedJson = new JSONObject(jsonObject.get("connections").toString());
                            JSONArray historyJson = new JSONArray(parsedJson.get("connections").toString());

                            int len = historyJson.length();
                            for (int i = 0; i < len; i++) {
                                Connection connection = new Connection();
                                JSONObject json = new JSONObject(historyJson.get(i).toString());
                                JSONObject user = new JSONObject(json.get("user").toString());

                                connection.setName(user.getString("name"));
                                connection.setEmail(user.getString("email"));
                                connection.setBio(user.getString("bio"));
                                Log.d("BIUO", user.getString("bio"));
                                connection.setPicture(user.getString("picture"));
                                connections.add(connection);

                            }
                            adapt = new ConnectionsVerticalAdapter(connections);
                            recylerConnections.setNestedScrollingEnabled(false);
                            recylerConnections.setAdapter(adapt);

                        } catch (JSONException err) {
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


        loaderContainer = (LinearLayout) view.findViewById(R.id.loaderContainer);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                loaderContainer.setVisibility(View.GONE);
            }
        }, 1000);
    }
}