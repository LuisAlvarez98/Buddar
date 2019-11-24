package com.app.buddar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

/**
 * DetailUserActivity Class
 * Displays the info of a desired user
 * <p>
 * RF04 - Lista de conexiones mas recientes
 * Casos de uso que cumple esta pantalla:
 * . Visualizar perfil de conexion reciente
 */
public class DetailUserActivity extends AppCompatActivity {

    private ImageView back_button;
    private TextView email, name, bio;
    private ImageView profileImage;
    private Button sendMessageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_user);

        //Definition of objects


        back_button = (ImageView) findViewById(R.id.back_button);
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        email = (TextView) findViewById(R.id.email);
        name = (TextView) findViewById(R.id.name);
        bio = (TextView) findViewById(R.id.bio);
        profileImage = (ImageView) findViewById(R.id.profileImage);

        //Get items from intent;

        final Intent intent = getIntent();
        if (intent != null) {
            name.setText(intent.getStringExtra("name"));
            bio.setText(intent.getStringExtra("bio"));
            email.setText(intent.getStringExtra("email"));
            Picasso.get().load(intent.getStringExtra("picture")).resize(950, 800)
                    .centerCrop().into(profileImage);
        }

        sendMessageButton = (Button)findViewById(R.id.sendMessageButton);
        sendMessageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent infoIntent = new Intent(view.getContext(), ChatActivity.class);
                infoIntent.putExtra("name",intent.getStringExtra("name"));
                infoIntent.putExtra("picture",intent.getStringExtra("picture"));
                view.getContext().startActivity(infoIntent);
            }
        });
    }
}
