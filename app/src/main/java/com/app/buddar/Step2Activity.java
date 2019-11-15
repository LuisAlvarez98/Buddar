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
import android.widget.Toast;

import com.app.buddar.adapters.ChatsHorizontalAdapter;
import com.app.buddar.adapters.InterestVerticalList;

import java.util.ArrayList;

import static com.app.buddar.DashboardFragment.interests;

/**
 * Setp2Activity
 * Here the user inputs their interests
 * Created by Luis F. Alvarez
 */
public class Step2Activity extends AppCompatActivity {
    private ImageView back_button;
    private Button continueButton;

    //Recycler Interest
    private Button addInterest;
    private RecyclerView recyclerInterests;
    private EditText interestInput;

    private InterestVerticalList interestVerticalList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step2);

        //Continue button listener
        continueButton = (Button)findViewById(R.id.continueButton);
        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Step2Activity.this, ConfirmationActivity.class);
                startActivity(intent);
            }
        });

        //back button definition
        back_button = (ImageView)findViewById(R.id.back_button);
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        //definition of components
        addInterest = (Button)findViewById(R.id.addInterest);
        recyclerInterests = (RecyclerView)findViewById(R.id.recyclerInterests);
        interestInput = (EditText)findViewById(R.id.interest);
        recyclerInterests.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        recyclerInterests.setNestedScrollingEnabled(false);


        //Add interest listener
        addInterest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(interestInput.getText().toString() != ""){
                    interests.add(interestInput.getText().toString());
                    interestVerticalList.notifyDataSetChanged();
                    interestInput.setText("");
                    Toast.makeText(getApplicationContext(), "Interest added!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        //definition of adapter
        interestVerticalList = new InterestVerticalList(interests);
        recyclerInterests.setNestedScrollingEnabled(false);
        recyclerInterests.setAdapter(interestVerticalList);
    }
}
