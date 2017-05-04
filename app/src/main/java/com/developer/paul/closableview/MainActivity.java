package com.developer.paul.closableview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        ClosableFragment fragment = new ClosableFragment();
        ClosableBtnFragment fragment = new ClosableBtnFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.container, fragment).commit();

//        closableLinearLayout = (ClosableLinearLayout) findViewById(R.id.closable_linearlayout);
//
//        closableLinearLayout.setOrderHashMap(orderHash);

//        closableLinearLayout.addRow("Location",getResources().getDrawable(R.drawable.contact_female_icon), "Location", new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(getApplicationContext(), "Location", Toast.LENGTH_SHORT).show();
//            }
//        });
//
//        closableLinearLayout.addRow("Repeat", getResources().getDrawable(R.drawable.contact_male_icon), "Repeat", new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(getApplicationContext(), "Repeat", Toast.LENGTH_SHORT).show();
//            }
//        });
//
//        closableLinearLayout.addRow("Note",getResources().getDrawable(R.drawable.contact_female_icon), "Note", new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(getApplicationContext(), "Note", Toast.LENGTH_SHORT).show();
//            }
//        });



    }
}
