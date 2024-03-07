package com.example.mrk201;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class progress_report extends AppCompatActivity {

    initial_report initialReport;
    EditText[] editTextArray = new EditText[20];
    Button proceed_complete_act;
    Button submit_progress_sms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress_report);

        initialReport = new initial_report();

        proceed_complete_act = findViewById(R.id.proceed_complete);

        editTextArray[12]=findViewById(R.id.owner_a13);
        editTextArray[13]=findViewById(R.id.damage_a14);
        editTextArray[14]=findViewById(R.id.fatality_a15);
        editTextArray[15]=findViewById(R.id.injured_a16);
        editTextArray[16]=findViewById(R.id.noofestab_a17);
        editTextArray[17]=findViewById(R.id.famaffect_a18);
        editTextArray[18]=findViewById(R.id.nooftruck_a19);

        submit_progress_sms = findViewById(R.id.submit_progress);

        //SUBMIT PROGRESS BUTTON
        submit_progress_sms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

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

                String countString = getIntent().getStringExtra("countString");

                String owner = editTextArray[12].getText().toString();
                String damage = editTextArray[13].getText().toString();
                String fatality = editTextArray[14].getText().toString();
                String injured = editTextArray[15].getText().toString();
                String noofbldg = editTextArray[16].getText().toString();
                String nooffamily = editTextArray[17].getText().toString();
                String nooftruck = editTextArray[18].getText().toString();


                String smsreport =

                        "Progress Info\n" +

                                "\nTime and Date Reported: \n" +
                                reported + "\n" +

                                "\nLocation: \n" +
                                location + "\n" +

                                "\nName of Owner: \n" +
                                owner + "\n" +

                                "\nAlarm Status: \n" +
                                alarm + "\n" +

                                "\nTime of Arrival: \n" +
                                arrival + "\n" +

                                "\nFire Out: \n" +
                                fireout + "\n" +

                                "\nEstimated Damage: \n" +
                                damage + "\n" +

                                "\nFatality: \n" +
                                fatality + "\n" +

                                "\nInjured: \n" +
                                injured + "\n" +

                                "\nNumber of House/Establishment: \n" +
                                noofbldg + "\n" +

                                "\nNumber of Families Affected: \n" +
                                nooffamily + "\n" +

                                "\nNumber of Firetrucks Responded: \n" +
                                nooftruck + "\n" +

                                "\nGround Commander: \n" +
                                grouncommander + "\n" + grouncommandernumber + "\n" +

                                "\nFire Investigator: \n" +
                                fireindia + "\n" + fireindianumber;

                ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("EditText", smsreport);
                clipboard.setPrimaryClip(clip);

                Toast.makeText(progress_report.this, "Copied", Toast.LENGTH_SHORT).show();

                //OPEN DEFAULT MESSAGING APP
                Intent sendIntent = new Intent(Intent.ACTION_SENDTO);
                sendIntent.setData(Uri.parse("sms:"));
                startActivity(sendIntent);

            }
        });

        proceed_complete_act.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

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

                String owner = editTextArray[12].getText().toString();
                String damage = editTextArray[13].getText().toString();
                String fatality = editTextArray[14].getText().toString();
                String injured = editTextArray[15].getText().toString();
                String noofbldg = editTextArray[16].getText().toString();
                String nooffamily = editTextArray[17].getText().toString();
                String nooftruck = editTextArray[18].getText().toString();

                Intent intent = new Intent(progress_report.this,complete_report.class);

                intent.putExtra("location", location);
                intent.putExtra("typeofincident", typeofincident);
                intent.putExtra("respondingteam", respondingteam);
                intent.putExtra("reported", reported);
                intent.putExtra("involved", involved);
                intent.putExtra("alarm", alarm);
                intent.putExtra("arrival", arrival);
                intent.putExtra("fireout", fireout);
                intent.putExtra("grouncommander", grouncommander);
                intent.putExtra("grouncommandernumber", grouncommandernumber);
                intent.putExtra("fireindia", fireindia);
                intent.putExtra("fireindianumber", fireindianumber);

                intent.putExtra("owner", owner);
                intent.putExtra("damage", damage);
                intent.putExtra("fatality", fatality);
                intent.putExtra("injured", injured);
                intent.putExtra("noofbldg", noofbldg);
                intent.putExtra("nooffamily", nooffamily);
                intent.putExtra("nooftruck", nooftruck);

                startActivity(intent);
                finish();
            }
        });
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();

        //INITIAL STRINGS
        //String location = editTextArray[0].getText().toString();
        //String typeofincident = editTextArray[1].getText().toString();
        //String respondingteam = editTextArray[2].getText().toString();
        //String reported = editTextArray[3].getText().toString();
        //String involved = editTextArray[4].getText().toString();
        //String alarm = editTextArray[5].getText().toString();
        //String arrival = editTextArray[6].getText().toString();
        //String fireout = editTextArray[7].getText().toString();
        //String grouncommander = editTextArray[8].getText().toString();
        //String grouncommandernumber = editTextArray[9].getText().toString();
        //String fireindia = editTextArray[10].getText().toString();
        //String fireindianumber = editTextArray[11].getText().toString();

        //PROGRESS STRINGS
        //String owner = editTextArray[12].getText().toString();
        //String damage = editTextArray[13].getText().toString();
        //String fatality = editTextArray[14].getText().toString();
        //String injured = editTextArray[15].getText().toString();
        //String noofbldg = editTextArray[16].getText().toString();
        //String nooffamily = editTextArray[17].getText().toString();
        //String nooftruck = editTextArray[18].getText().toString();

        Intent intent = new Intent(progress_report.this,initial_report.class);

        //intent.putExtra("location", location);
        //intent.putExtra("typeofincident", typeofincident);
        //intent.putExtra("respondingteam", respondingteam);
        //intent.putExtra("reported", reported);
        //intent.putExtra("involved", involved);
        //intent.putExtra("alarm", alarm);
        //intent.putExtra("arrival", arrival);
        //intent.putExtra("fireout", fireout);
        //intent.putExtra("grouncommander", grouncommander);
        //intent.putExtra("grouncommandernumber", grouncommandernumber);
        //intent.putExtra("fireindia", fireindia);
        //intent.putExtra("fireindianumber", fireindianumber);

        //intent.putExtra("owner", owner);
        //intent.putExtra("damage", damage);
        //intent.putExtra("fatality", fatality);
        //intent.putExtra("injured", injured);
        //intent.putExtra("noofbldg", noofbldg);
        //intent.putExtra("nooffamily", nooffamily);
        //intent.putExtra("nooftruck", nooftruck);

        startActivity(intent);
        finish();
    }

}