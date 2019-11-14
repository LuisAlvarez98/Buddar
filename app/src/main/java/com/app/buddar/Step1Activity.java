package com.app.buddar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class Step1Activity extends AppCompatActivity {
    private ImageView back_button;

    private LinearLayout itemDinner, itemTalk, itemOther;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step1);

        itemDinner = (LinearLayout)findViewById(R.id.itemDinner);
        itemDinner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Step1Activity.this, Step2Activity.class);
                intent.putExtra("itemType",0);
                startActivity(intent);
            }
        });
        itemOther = (LinearLayout)findViewById(R.id.itemOther);
        itemOther.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Step1Activity.this, Step2Activity.class);
                intent.putExtra("itemType",2);
                startActivity(intent);
            }
        });
        itemTalk = (LinearLayout)findViewById(R.id.itemTalk);
        itemTalk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Step1Activity.this, Step2Activity.class);
                intent.putExtra("itemType",1);
                startActivity(intent);
            }
        });
        back_button = (ImageView)findViewById(R.id.back_button);
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
