package com.app.buddar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

import static com.app.buddar.util.RestAdapter.getUnsafeOkHttpClient;

/**
 * Register Activity
 * Created by Luis F. Alvarez
 * RF01 - Autenticacion de Usuario
 * Casos de uso que cumple esta pantalla:
 * . Registro de usuario
 */
public class RegisterActivity extends AppCompatActivity {

    private ImageView back_button;
    private Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        //Retrofit init
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .client(getUnsafeOkHttpClient().build())
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        final Api apiInterface = retrofit.create(Api.class);

        back_button = (ImageView) findViewById(R.id.back_button);
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        submitButton = (Button) findViewById(R.id.submitButton);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //validate input and create a POST request
                Call<String> userCall = apiInterface.createUser();
                userCall.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        switch (response.code()) {
                            case 200:
                                try {
                                    JSONObject jsonObject = new JSONObject(response.body());
                                    JSONObject parsedJson = new JSONObject(jsonObject.get("register").toString());
                                    int status = parsedJson.getInt("status");
                                    String message = parsedJson.getString("message");
                                    Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
                                    Intent in = new Intent(RegisterActivity.this, DashboardActivity.class);
                                    startActivity(in);
                                    finish();
                                } catch (JSONException err) {
                                    Log.d("Error", err.toString());
                                }
                                break;
                        }
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        Log.d("response", t.toString());
                    }
                });
            }
        });

    }
}
