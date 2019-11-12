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
import android.widget.LinearLayout;
import android.widget.Toast;

import com.app.buddar.objects.Profile;
import com.squareup.picasso.Picasso;

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
 */
public class ProfileFragment extends Fragment implements View.OnClickListener {
    private CircleImageView profileImage;
    private Button submitButton, changePasswordButton;
    private LinearLayout loaderContainer;
    private Profile profile;
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

        //get profile
        Call<Profile> call2 = apiInterface.getProfile();
        call2.enqueue(new Callback<Profile>() {
            @Override
            public void onResponse(Call<Profile> call, Response<Profile> response) {
                profile = response.body();
                Toast.makeText(getContext(), response.code(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<Profile> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
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

        profileImage = (CircleImageView)view.findViewById(R.id.profileImage);
        submitButton = (Button)view.findViewById(R.id.submitButton);
        submitButton.setOnClickListener(this);
        changePasswordButton = (Button)view.findViewById(R.id.changePassword);
        changePasswordButton.setOnClickListener(this);
        loaderContainer = (LinearLayout)view.findViewById(R.id.loaderContainer);

        Picasso.get().load("https://scontent.fntr6-1.fna.fbcdn.net/v/t1.0-9/57738860_2189787707771861_2357075513719128064_n.jpg?_nc_cat=108&_nc_oc=AQkR0FD2ezHKLqilzxbqqTf3eXHYpCDCupqHbq-2uEivl5Fne-blS9-ngdYJKIhsFt8&_nc_ht=scontent.fntr6-1.fna&oh=ee0518b253fc53a1bd863c6079fee62f&oe=5E4F1BD5").into(profileImage);


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