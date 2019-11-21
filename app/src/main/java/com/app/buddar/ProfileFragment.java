package com.app.buddar;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.app.buddar.objects.Profile;
import com.app.buddar.objects.User;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

import static com.app.buddar.util.RestAdapter.getUnsafeOkHttpClient;

/**
 * Perfil Fragment
 * Created by Luis F. Alvarez
 * <p>
 * RF02 - Administracion de Perfil
 * Casos de uso que cumple esta pantalla:
 * . Editar perfil
 */
public class ProfileFragment extends Fragment implements View.OnClickListener {
    private CircleImageView profileImage;
    private EditText inputName, inputEmail, textBio;
    private Button submitButton, changePasswordButton;
    private LinearLayout loaderContainer;
    private TextView title;
    public static String userName, userPicture, userEmail, userBio;
    //Retrofit init
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(Api.BASE_URL)
            .client(getUnsafeOkHttpClient().build())
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    Api apiInterface = retrofit.create(Api.class);

    /**
     * Perfil Fragment Constructor
     */
    public ProfileFragment() {
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
        return inflater.inflate(R.layout.profile_fragment, container, false);
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

        profileImage = (CircleImageView) view.findViewById(R.id.profileImage);
        submitButton = (Button) view.findViewById(R.id.submitButton);
        submitButton.setOnClickListener(this);
        changePasswordButton = (Button) view.findViewById(R.id.changePassword);
        changePasswordButton.setOnClickListener(this);
        loaderContainer = (LinearLayout) view.findViewById(R.id.loaderContainer);

        inputEmail = (EditText) view.findViewById(R.id.emailInput);
        inputName = (EditText) view.findViewById(R.id.nameInput);
        textBio = (EditText) view.findViewById(R.id.bioText);
        title = (TextView) view.findViewById(R.id.title);

        Call<String> call2 = apiInterface.getProfile();
        call2.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                switch (response.code()) {
                    case 200:
                        try {
                            JSONObject jsonObject = new JSONObject(response.body());
                            JSONObject parsedJson = new JSONObject(jsonObject.get("profile").toString());
                            JSONObject userJson = new JSONObject(parsedJson.get("user").toString());

                            String name = userJson.getString("name");
                            String email = userJson.getString("email");
                            String picture = userJson.getString("picture");
                            String bio = userJson.getString("bio");
                            title.setText("Hello, " + name);
                            inputName.setText(name);
                            inputEmail.setText(email);
                            textBio.setText(bio);
                            Picasso.get().load(picture).into(profileImage);

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
            case R.id.submitButton:
                Toast.makeText(getContext(), "Profile information changed succesfully.", Toast.LENGTH_SHORT).show();
                break;
            case R.id.changePassword:
                Toast.makeText(getContext(), "Go to change password view.", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}