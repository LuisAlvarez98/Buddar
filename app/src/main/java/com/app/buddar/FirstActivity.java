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


import com.app.buddar.objects.User;

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
 * Luis Felipe Alvarez Sanchez
 */
public class FirstActivity extends AppCompatActivity implements View.OnClickListener, Callback<User> {
    private Button loginButton;
    private Button regBtn, proveedorNuevo;
    private EditText emailInput, passwordInput;
    private String username, password;
    private String token;
    public static SharedPreferences pref;

    private TextView registerButton;
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

        registerButton = (TextView) findViewById(R.id.registerButton);
        registerButton.setOnClickListener(this);

        progressLogin = (ProgressBar) findViewById(R.id.progressLogin);
        progressLogin.setVisibility(View.GONE);

        pref = getSharedPreferences("user_details", MODE_PRIVATE);
        if (pref.contains("token")) {
            Intent loggedIn = new Intent(FirstActivity.this, DashboardActivity.class);
            startActivity(loggedIn);
        }
    }

    /**
     * onClick method
     *
     * @param view
     */
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.registerButton:
                Intent reg = new Intent(FirstActivity.this, RegisterActivity.class);
                startActivity(reg);
                break;
            //Login user
            case R.id.loginButton:
                Intent in = new Intent(FirstActivity.this, DashboardActivity.class);
                startActivity(in);
                finish();
                progressLogin.setVisibility(View.VISIBLE);
                username = emailInput.getText().toString();
                password = passwordInput.getText().toString();
                if (!username.isEmpty() && !password.isEmpty()) {
                    try {
                        JSONObject paramObject = new JSONObject();
                        paramObject.put("username", username);
                        paramObject.put("password", password);
                        Log.d("response", paramObject.toString());
                        Call<User> userCall = apiInterface.loginUser(paramObject.toString());
                        userCall.enqueue(new Callback<User>() {
                            @Override
                            public void onResponse(Call<User> call, Response<User> response) {
                                switch (response.code()) {
                                    case 200:
                                        SharedPreferences.Editor editor = pref.edit();
                                        //Sets token in shared preferences
                                        editor.putString("token", response.body().getToken());
                                        //adds token to the shared prefrences
                                        editor.commit();

                                        progressLogin.setVisibility(View.GONE);
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
                            public void onFailure(Call<User> call, Throwable t) {
                                Log.d("response", t.toString());
                            }
                        });
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                } else if (username.isEmpty()) {
                    emailInput.setError("Input username");
                    progressLogin.setVisibility(View.GONE);
                } else if (password.isEmpty()) {
                    passwordInput.setError("Input password");
                    progressLogin.setVisibility(View.GONE);
                } else {
                    emailInput.setError("Invalid input");
                    passwordInput.setError("Invalid input");
                    progressLogin.setVisibility(View.GONE);
                }
                break;
        }
    }

    /**
     *onBackPressed
     */
    @Override
    public void onBackPressed() {
    }

    /**
     * @param call
     * @param response
     */
    @Override
    public void onResponse(Call<User> call, Response<User> response) {
        if (response.isSuccessful()) {
            Intent loginIntent = new Intent(FirstActivity.this, DashboardActivity.class);
            startActivity(loginIntent);
        }
        Toast.makeText(getApplicationContext(), "Login", Toast.LENGTH_SHORT).show();
    }

    /**
     * onFailure method
     * @param call
     * @param t
     */
    @Override
    public void onFailure(Call<User> call, Throwable t) {
        Toast.makeText(getApplicationContext(), "Didn't work", Toast.LENGTH_SHORT).show();
    }
}
