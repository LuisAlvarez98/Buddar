package com.app.buddar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.app.buddar.adapters.HistoryVerticalAdapter;
import com.app.buddar.adapters.NotificationVerticalList;

import java.util.ArrayList;

/**
 * NotificationActivity Class
 * Displays the notifications of the user
 * <p>
 * RF03 - Conectar con usuario
 * Casos de uso que cumple esta pantalla:
 * . Aceptar conexion
 * . Rechazar conexion
 */
public class NotificationActivity extends AppCompatActivity {
    private ImageView back_button;
    private RecyclerView recylerNotifications;
    NotificationVerticalList adapt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);


        back_button = (ImageView) findViewById(R.id.back_button);
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        recylerNotifications = (RecyclerView) findViewById(R.id.recylcerNotifications);
        //info button
        recylerNotifications.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        ArrayList<String> notifications = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            notifications.add("You have a new connection!");
        }
        adapt = new NotificationVerticalList(notifications);
        recylerNotifications.setNestedScrollingEnabled(false);
        recylerNotifications.setAdapter(adapt);
    }
}
