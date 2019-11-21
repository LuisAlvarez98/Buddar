package com.app.buddar;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
 * Forgot Password Activity
 * Created by Luis F. Alvarez
 * <p>
 * RF02 - Administracion de perfil
 * Casos de uso que cumple esta pantalla:
 * . Restablecer contrasena
 */
public class ForgotPassword extends AppCompatActivity {

    private ImageView back_button;
    private Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
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
                Toast.makeText(getApplicationContext(), "You will recieve an email with instructions to reset your password.", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}
