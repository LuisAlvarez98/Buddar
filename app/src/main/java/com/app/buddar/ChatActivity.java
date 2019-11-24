package com.app.buddar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.app.buddar.adapters.ChatVerticalList;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Chat Activity - Displays the chat with an user
 * Luis Felipe Alvarez Sanchez
 * <p>
 * RF05 - Chat con conexion
 * Casos de uso que cumple esta pantalla:
 * . Mandar mensaje
 * . Borrar mensaje - NO FUNCIONA EN ESTA VERSION
 * . Reportar usuario - NO FUNCIONA EN ESTA VERSION
 */
public class ChatActivity extends AppCompatActivity {
    private ImageView back_button;
    private Button continueButton;

    //Recycler Interest
    private Button addInterest;
    private RecyclerView recyclerMessages;
    private EditText chatInput;

    private ArrayList<String> messages;

    private ChatVerticalList chatVerticalList;
    private CircleImageView profilePicture;
    private TextView name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        messages = new ArrayList<>();
        //back button definition
        back_button = (ImageView) findViewById(R.id.back_button);
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        profilePicture = (CircleImageView) findViewById(R.id.profilePicture);
        name = (TextView) findViewById(R.id.name);
        Intent intent = getIntent();
        if (intent != null) {
            name.setText(intent.getStringExtra("name"));
            Picasso.get().load(intent.getStringExtra("picture")).into(profilePicture);
        }

        //definition of components
        addInterest = (Button) findViewById(R.id.sendMessageButton);
        recyclerMessages = (RecyclerView) findViewById(R.id.recyclerMessages);
        chatInput = (EditText) findViewById(R.id.message);
        recyclerMessages.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        recyclerMessages.setNestedScrollingEnabled(false);


        //Add interest listener
        addInterest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!chatInput.getText().toString().isEmpty()) {
                    messages.add(chatInput.getText().toString());
                    chatVerticalList.notifyDataSetChanged();
                    chatInput.setText("");
                    Toast.makeText(getApplicationContext(), "Message sent!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Please input something!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        //definition of adapter
        chatVerticalList = new ChatVerticalList(messages);
        recyclerMessages.setNestedScrollingEnabled(false);
        recyclerMessages.setAdapter(chatVerticalList);
    }
}
