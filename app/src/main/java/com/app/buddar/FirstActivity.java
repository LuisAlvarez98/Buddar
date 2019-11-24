package com.app.buddar;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
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
 * First Activity Class
 * Created by Luis F. Alvarez
 * <p>
 * RF01 - Autenticacion de Usuario
 * Casos de uso que cumple esta pantalla:
 * . Inicio de sesion
 * . Inicio de sesion / Registro con Facebook
 * . Inicio de sesion / Registro con Google
 */
public class FirstActivity extends AppCompatActivity implements View.OnClickListener{
    private Button loginButton, googleButton, facebookButton;
    private EditText emailInput, passwordInput;
    private String username, password;
    private TextView registerButton, forgotPassword;
    //Progress
    ProgressBar progressLogin;

    //Retrofit init
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(Api.BASE_URL)
            .client(getUnsafeOkHttpClient().build())
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    Api apiInterface = retrofit.create(Api.class);

    /**
     * onCreate method
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        //Input init
        emailInput = (EditText) findViewById(R.id.emailInput);
        passwordInput = (EditText) findViewById(R.id.passwordInput);
        //Login button init
        loginButton = (Button) findViewById(R.id.loginButton);
        loginButton.setOnClickListener(this);
        //Log in with google
        googleButton = (Button) findViewById(R.id.googleButton);
        googleButton.setOnClickListener(this);
        //Log in with facebook
        facebookButton = (Button) findViewById(R.id.facebookButton);
        facebookButton.setOnClickListener(this);

        registerButton = (TextView) findViewById(R.id.registerButton);
        registerButton.setOnClickListener(this);

        forgotPassword = (TextView) findViewById(R.id.forgotPassword);
        forgotPassword.setOnClickListener(this);

        progressLogin = (ProgressBar) findViewById(R.id.progressLogin);
        progressLogin.setVisibility(View.GONE);

    }

    /**
     * onClick method
     *
     * @param view
     */
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            //Forgot password click
            case R.id.forgotPassword:
                Intent forgot = new Intent(FirstActivity.this, ForgotPassword.class);
                startActivity(forgot);
                break;
            // Sign in with google button
            case R.id.googleButton:
                Toast.makeText(getApplicationContext(), "Signed in with Google.", Toast.LENGTH_SHORT).show();
                Intent inGoogle = new Intent(FirstActivity.this, DashboardActivity.class);
                startActivity(inGoogle);
                finish();
                break;
            // Sign in with facebook button
            case R.id.facebookButton:
                Toast.makeText(getApplicationContext(), "Signed in with Facebook.", Toast.LENGTH_SHORT).show();
                Intent inFacebook = new Intent(FirstActivity.this, DashboardActivity.class);
                startActivity(inFacebook);
                finish();
                break;
            // Register button
            case R.id.registerButton:
                Intent reg = new Intent(FirstActivity.this, RegisterActivity.class);
                startActivity(reg);
                break;
            //Login user button
            case R.id.loginButton:
                username = emailInput.getText().toString();
                password = passwordInput.getText().toString();
                if (!username.isEmpty() && !password.isEmpty()) {
                    Call<String> userCall = apiInterface.loginUser();
                    userCall.enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            switch (response.code()) {
                                case 200:
                                    try {
                                        JSONObject jsonObject = new JSONObject(response.body());
                                        JSONObject parsedJson = new JSONObject(jsonObject.get("login").toString());
                                        int status = parsedJson.getInt("status");
                                        String message = parsedJson.getString("message");
                                        String token = parsedJson.getString("token");

                                        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
                                        Intent in = new Intent(FirstActivity.this, DashboardActivity.class);
                                        startActivity(in);
                                        finish();
                                    } catch (JSONException err) {
                                        Log.d("Error", err.toString());
                                    }
                                    break;
                                case 400:
                                    passwordInput.setError("Wrong credentials");
                                    progressLogin.setVisibility(View.GONE);
                                    break;
                                case 404:
                                    passwordInput.setError("File not found.");
                                    progressLogin.setVisibility(View.GONE);
                                    break;
                                case 500:
                                    passwordInput.setError("Server error.");
                                    progressLogin.setVisibility(View.GONE);
                                    break;
                            }
                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {
                            Log.d("response", t.toString());
                        }
                    });
                } else {
                    Toast.makeText(getApplicationContext(), "Por favor ingrese sus credenciales de forma correcta.", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }


}
