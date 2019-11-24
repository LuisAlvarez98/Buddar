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
import com.squareup.picasso.Picasso;

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
 * History Fragment
 * Created by Luis F. Alvarez
 */
public class HistoryFragment extends Fragment{
    private RecyclerView recyclerHistory;
    private LinearLayout loaderContainer;
    //Retrofit init
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(Api.BASE_URL)
            .client(getUnsafeOkHttpClient().build())
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    Api apiInterface = retrofit.create(Api.class);
    HistoryVerticalAdapter adapt;

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


        Call<String> call2 = apiInterface.getHistory();
        call2.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                switch (response.code()) {
                    case 200:

                        try {
                            ArrayList<History> histories = new ArrayList<History>();
                            JSONObject jsonObject = new JSONObject(response.body());
                            JSONObject parsedJson = new JSONObject(jsonObject.get("history").toString());
                            JSONArray historyJson = new JSONArray(parsedJson.get("history").toString());

                            int len = historyJson.length();
                            for (int i = 0; i < len; i++) {
                                History history = new History();
                                JSONObject json = new JSONObject(historyJson.get(i).toString());
                                JSONObject user = new JSONObject(json.get("user").toString());

                                history.setName(user.getString("name"));
                                history.setBio(user.getString("bio"));
                                Log.d("BIUO", user.getString("bio"));
                                history.setPicture(user.getString("picture"));
                                histories.add(history);

                            }
                            adapt = new HistoryVerticalAdapter(histories);
                            recyclerHistory.setNestedScrollingEnabled(false);
                            recyclerHistory.setAdapter(adapt);

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