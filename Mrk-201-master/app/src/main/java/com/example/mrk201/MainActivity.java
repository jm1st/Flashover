package com.example.mrk201;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {

    ImageButton add_incident;
    ImageButton profile_image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String location = getIntent().getStringExtra("location");
        String typeofincident = getIntent().getStringExtra("typeofincident");
        String respondingteam = getIntent().getStringExtra("respondingteam");
        String reported = getIntent().getStringExtra("reported");
        String involved = getIntent().getStringExtra("involved");
        String alarm = getIntent().getStringExtra("alarm");
        String arrival = getIntent().getStringExtra("arrival");
        String fireout = getIntent().getStringExtra("fireout");
        String grouncommander = getIntent().getStringExtra("grouncommander");
        String grouncommandernumber = getIntent().getStringExtra("grouncommandernumber");
        String fireindia = getIntent().getStringExtra("fireindia");
        String fireindianumber = getIntent().getStringExtra("fireindianumber");

        String owner = getIntent().getStringExtra("owner");
        String damage = getIntent().getStringExtra("damage");
        String fatality = getIntent().getStringExtra("fatality");
        String injured = getIntent().getStringExtra("injured");
        String noofbldg = getIntent().getStringExtra("noofbldg");
        String nooffamily = getIntent().getStringExtra("nooffamily");
        String nooftruck = getIntent().getStringExtra("nooftruck");

        add_incident = findViewById(R.id.add_incident_image);
        profile_image = findViewById(R.id.account_image);


        profile_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent intent = (new Intent(MainActivity.this, Profile.class));
                //startActivity(intent);
                //finish();

                Toast.makeText(MainActivity.this, "Under Development", Toast.LENGTH_SHORT).show();


            }
        });

        add_incident.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = (new Intent(MainActivity.this, initial_report.class));
                startActivity(intent);
                finish();
            }
        });


    }
}