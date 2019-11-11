package com.app.buddar;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

/**
 * Dashboard Fragment - Displays categories
 * Luis Felipe Alvarez Sanchez
 */
public class DashboardFragment extends Fragment implements View.OnClickListener {
    ImageButton misProductos, eventosEnCurso, solicitudesDeEventos, historialDeEventos, miPerfil;
    ProfileFragment profileFragment;

    private Button addProducts;
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
    }


    /**
     * onClick method
     *
     * @param view
     */
    @Override
    public void onClick(View view) {
        switch (view.getId()) {

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