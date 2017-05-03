package com.developer.paul.closableview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements Button.OnClickListener {

    private ClosableLinearLayout closableLinearLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        closableLinearLayout = (ClosableLinearLayout) findViewById(R.id.closable_linearlayout);
        HashMap<String, Integer> orderHash = new HashMap<>();
        orderHash.put("Location", 0);
        orderHash.put("Repeat", 1);
        orderHash.put("Note",2);
        closableLinearLayout.setOrderHashMap(orderHash);

        closableLinearLayout.addRow("Location",getResources().getDrawable(R.drawable.contact_female_icon), "Location", new OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Location", Toast.LENGTH_SHORT).show();
            }
        });

        closableLinearLayout.addRow("Repeat", getResources().getDrawable(R.drawable.contact_male_icon), "Repeat", new OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Repeat", Toast.LENGTH_SHORT).show();
            }
        });

        closableLinearLayout.addRow("Note",getResources().getDrawable(R.drawable.contact_female_icon), "Note", new OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Note", Toast.LENGTH_SHORT).show();
            }
        });


        Button locationBtn = (Button)findViewById(R.id.location_btn);
        Button repeatBtn = (Button) findViewById(R.id.repeat_btn);
        Button noteBtn = (Button)findViewById(R.id.note_btn);
        locationBtn.setOnClickListener(this);
        repeatBtn.setOnClickListener(this);
        noteBtn.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        closableLinearLayout.showClosedView(((Button)v).getText().toString());
    }
}
