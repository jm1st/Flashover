package com.example.mrk201;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.timepicker.MaterialTimePicker;
import com.google.android.material.timepicker.TimeFormat;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class complete_report extends AppCompatActivity {

    String[] typeofincidentdrop = {"Residential","Non Residential","Non Structural","Transport"};

    String[] invlveddrop = {"Residential","Assembly","Educational","Day Care","Health Care","Residential Board and Care","Detention and Correcional",
            "Mercantile","Business","Indsutrial","Storage","Mixed Occupancies","Special Structure", "Grass","Agricultural","Rubbish","Electrical Post","Forest"};
    String [] alarmdrop = {"Unreponded","Fire Out Upon Arrival","1st Alarm","2nd Alarm","3rd Alarm","4th Alarm","5th Alarm", "Tassk Force Alpha", "Task Force Bravo", "Task Force Charlie","Task Force Delta","General Alarm"};

    String[] respondingteamdroplist = {"Central Fire Station - Bauer 10", "Central Fire Station - Hino", "SRF", "EMS", "Ayala Fire Sub-Station","Boalan Fire Sub-Station","Calarian Fire Sub-Station",
            "Culianan Fire Sub-Station","Guiwan Fire Sub-Station","Labuan Fire Sub-Station","Lunzuran Fire Sub-Station", "Mampang Fire Sub-Station","Manicahan Fire Sub-Station",
            "Mercedes Fire Sub-Station","Putik Fire Sub-Station","Quiniput Fire Sub-Station","Recodo Fire Sub-Station","San Jose Fire Sub-Station","San Jose Fire Sub-Station",
            "Sangali Fire Sub-Station","Sta Catalina Fire Sub-Station","Sta Maria Fire Sub-Station","Talisayan Fire Sub-Station", "Tetuan Fire Sub-Station","Tumaga Fire Sub-Station","Vitali Fire Sub-Station"};

    AutoCompleteTextView autoCompleteTextView;

    ArrayAdapter<String> adapterItems;

    Button clear_all_data;
    Button submit_complete;

    TextView[] TextViewarray = new TextView[20];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complete_report);

        //TYPE OF INCIDENT DROPDOWN
        autoCompleteTextView = findViewById(R.id.typeofincident_a2);
        autoCompleteTextView.setShowSoftInputOnFocus(false);

        adapterItems = new ArrayAdapter<String>(this, R.layout.list_item, typeofincidentdrop);
        autoCompleteTextView.setAdapter(adapterItems);

        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String item = adapterView.getItemAtPosition(i).toString();
            }
        });

        //RESPONDING TEAM DIALOG
        TextInputEditText respondingTeamEditText = findViewById(R.id.respondingteam_a3);
        respondingTeamEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showRespondingTeamDialog();
            }
        });

        //INVOLVED DROP
        autoCompleteTextView = findViewById(R.id.involved_a5);
        autoCompleteTextView.setShowSoftInputOnFocus(false);

        adapterItems = new ArrayAdapter<String>(this, R.layout.list_item, invlveddrop);
        autoCompleteTextView.setAdapter(adapterItems);

        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String item = adapterView.getItemAtPosition(i).toString();
            }
        });

        //ALARM STATUS DROP
        autoCompleteTextView = findViewById(R.id.alarm_a6);
        autoCompleteTextView.setShowSoftInputOnFocus(false);

        adapterItems = new ArrayAdapter<String>(this, R.layout.list_item, alarmdrop);
        autoCompleteTextView.setAdapter(adapterItems);

        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String item = adapterView.getItemAtPosition(i).toString();
            }
        });

        //TIME AND DATE REPORTED

        TextInputEditText reportedA4 = findViewById(R.id.reported_a4);

        reportedA4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the current date and time
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
                int hour = calendar.get(Calendar.HOUR_OF_DAY);
                int minute = calendar.get(Calendar.MINUTE);

                // Create the MaterialDatePicker for selecting the date
                MaterialDatePicker.Builder<Long> datePickerBuilder = MaterialDatePicker.Builder.datePicker();
                datePickerBuilder.setSelection(calendar.getTimeInMillis()); // Set the initial date selection
                MaterialDatePicker<Long> materialDatePicker = datePickerBuilder.build();

                // Set the listener for the selected date
                materialDatePicker.addOnPositiveButtonClickListener(selection -> {
                    calendar.setTimeInMillis(selection);

                    // Create the MaterialTimePicker for selecting the time
                    MaterialTimePicker.Builder timePickerBuilder = new MaterialTimePicker.Builder();
                    //timePickerBuilder.setTheme(R.style.Widget_Material3_MaterialTimePicker); // Set the time picker style
                    timePickerBuilder.setTimeFormat(TimeFormat.CLOCK_24H);
                    timePickerBuilder.setHour(hour); // Set the initial hour
                    timePickerBuilder.setMinute(minute); // Set the initial minute
                    MaterialTimePicker materialTimePicker = timePickerBuilder.build();

                    // Set the listener for the selected time
                    materialTimePicker.addOnPositiveButtonClickListener(view -> {
                        int selectedHour = materialTimePicker.getHour();
                        int selectedMinute = materialTimePicker.getMinute();

                        // Set the selected date and time to the EditText
                        calendar.set(Calendar.HOUR_OF_DAY, selectedHour);
                        calendar.set(Calendar.MINUTE, selectedMinute);

                        SimpleDateFormat dateTimeFormat = new SimpleDateFormat("HHmm'H' 'of' MMMM dd, yyyy", Locale.getDefault());
                        String selectedDateTime = dateTimeFormat.format(calendar.getTime());

                        reportedA4.setText(selectedDateTime);
                    });

                    materialTimePicker.show(getSupportFragmentManager(), "timePicker");
                });

                materialDatePicker.show(getSupportFragmentManager(), "datePicker");
            }
        });

        //TIME OF ARRIVAL

        TextInputEditText arrivalA7 = findViewById(R.id.arrival_a7);

        arrivalA7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the current date and time
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
                int hour = calendar.get(Calendar.HOUR_OF_DAY);
                int minute = calendar.get(Calendar.MINUTE);

                // Create the MaterialDatePicker for selecting the date
                MaterialDatePicker.Builder<Long> datePickerBuilder = MaterialDatePicker.Builder.datePicker();
                datePickerBuilder.setSelection(calendar.getTimeInMillis()); // Set the initial date selection
                MaterialDatePicker<Long> materialDatePicker = datePickerBuilder.build();

                // Set the listener for the selected date
                materialDatePicker.addOnPositiveButtonClickListener(selection -> {
                    calendar.setTimeInMillis(selection);

                    // Create the MaterialTimePicker for selecting the time
                    MaterialTimePicker.Builder timePickerBuilder = new MaterialTimePicker.Builder();
                    //timePickerBuilder.setTheme(R.style.Widget_Material3_MaterialTimePicker); // Set the time picker style
                    timePickerBuilder.setTimeFormat(TimeFormat.CLOCK_24H);
                    timePickerBuilder.setHour(hour); // Set the initial hour
                    timePickerBuilder.setMinute(minute); // Set the initial minute
                    MaterialTimePicker materialTimePicker = timePickerBuilder.build();

                    // Set the listener for the selected time
                    materialTimePicker.addOnPositiveButtonClickListener(view -> {
                        int selectedHour = materialTimePicker.getHour();
                        int selectedMinute = materialTimePicker.getMinute();

                        // Set the selected date and time to the EditText
                        calendar.set(Calendar.HOUR_OF_DAY, selectedHour);
                        calendar.set(Calendar.MINUTE, selectedMinute);

                        SimpleDateFormat dateTimeFormat = new SimpleDateFormat("HHmm'H' 'of' MMMM dd, yyyy", Locale.getDefault());
                        String selectedDateTime = dateTimeFormat.format(calendar.getTime());

                        arrivalA7.setText(selectedDateTime);
                    });

                    materialTimePicker.show(getSupportFragmentManager(), "timePicker");
                });

                materialDatePicker.show(getSupportFragmentManager(), "datePicker");
            }
        });

        //FIRE OUT

        TextInputEditText fireoutA8 = findViewById(R.id.fireout_a8);

        fireoutA8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the current date and time
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
                int hour = calendar.get(Calendar.HOUR_OF_DAY);
                int minute = calendar.get(Calendar.MINUTE);

                // Create the MaterialDatePicker for selecting the date
                MaterialDatePicker.Builder<Long> datePickerBuilder = MaterialDatePicker.Builder.datePicker();
                datePickerBuilder.setSelection(calendar.getTimeInMillis()); // Set the initial date selection
                MaterialDatePicker<Long> materialDatePicker = datePickerBuilder.build();

                // Set the listener for the selected date
                materialDatePicker.addOnPositiveButtonClickListener(selection -> {
                    calendar.setTimeInMillis(selection);

                    // Create the MaterialTimePicker for selecting the time
                    MaterialTimePicker.Builder timePickerBuilder = new MaterialTimePicker.Builder();
                    //timePickerBuilder.setTheme(R.style.Widget_Material3_MaterialTimePicker); // Set the time picker style
                    timePickerBuilder.setTimeFormat(TimeFormat.CLOCK_24H);
                    timePickerBuilder.setHour(hour); // Set the initial hour
                    timePickerBuilder.setMinute(minute); // Set the initial minute
                    MaterialTimePicker materialTimePicker = timePickerBuilder.build();

                    // Set the listener for the selected time
                    materialTimePicker.addOnPositiveButtonClickListener(view -> {
                        int selectedHour = materialTimePicker.getHour();
                        int selectedMinute = materialTimePicker.getMinute();

                        // Set the selected date and time to the EditText
                        calendar.set(Calendar.HOUR_OF_DAY, selectedHour);
                        calendar.set(Calendar.MINUTE, selectedMinute);

                        SimpleDateFormat dateTimeFormat = new SimpleDateFormat("HHmm'H' 'of' MMMM dd, yyyy", Locale.getDefault());
                        String selectedDateTime = dateTimeFormat.format(calendar.getTime());

                        fireoutA8.setText(selectedDateTime);
                    });

                    materialTimePicker.show(getSupportFragmentManager(), "timePicker");
                });

                materialDatePicker.show(getSupportFragmentManager(), "datePicker");
            }
        });

        //START OF DATA IMPORT

        clear_all_data = findViewById(R.id.clear_data);
        submit_complete = findViewById(R.id.submit_complete);

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

        TextViewarray[0] =findViewById(R.id.location_a1);
        TextViewarray[1] =findViewById(R.id.typeofincident_a2);
        TextViewarray[2] =findViewById(R.id.respondingteam_a3);
        TextViewarray[3] =findViewById(R.id.reported_a4);
        TextViewarray[4] =findViewById(R.id.involved_a5);
        TextViewarray[5] =findViewById(R.id.owner_a13);
        TextViewarray[6] =findViewById(R.id.alarm_a6);
        TextViewarray[7] =findViewById(R.id.arrival_a7);
        TextViewarray[8] =findViewById(R.id.fireout_a8);
        TextViewarray[9] =findViewById(R.id.damage_a14);
        TextViewarray[10] =findViewById(R.id.fatality_a15);
        TextViewarray[11] =findViewById(R.id.injured_a16);
        TextViewarray[12] =findViewById(R.id.noofestab_a17);
        TextViewarray[13] =findViewById(R.id.famaffect_a18);
        TextViewarray[14] =findViewById(R.id.nooftruck_a19);
        TextViewarray[15] =findViewById(R.id.groundcom_a9);
        TextViewarray[16] =findViewById(R.id.groundno_a10);
        TextViewarray[17] =findViewById(R.id.fireindia_a11);
        TextViewarray[18] =findViewById(R.id.fireindiano_a12);

        TextViewarray[0].setText(location);
        TextViewarray[1].setText(typeofincident);
        TextViewarray[2].setText(respondingteam);
        TextViewarray[3].setText(reported);
        TextViewarray[4].setText(involved);
        TextViewarray[5].setText(owner);
        TextViewarray[6].setText(alarm);
        TextViewarray[7].setText(arrival);
        TextViewarray[8].setText(fireout);
        TextViewarray[9].setText(damage);
        TextViewarray[10].setText(fatality);
        TextViewarray[11].setText(injured);
        TextViewarray[12].setText(noofbldg);
        TextViewarray[13].setText(nooffamily);
        TextViewarray[14].setText(nooftruck);
        TextViewarray[15].setText(grouncommander);
        TextViewarray[16].setText(grouncommandernumber);
        TextViewarray[17].setText(fireindia);
        TextViewarray[18].setText(fireindianumber);


        submit_complete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String location = TextViewarray[0].getText().toString();
                String typeofincident = TextViewarray[1].getText().toString();
                String respondingteam = TextViewarray[2].getText().toString();
                String reported = TextViewarray[3].getText().toString();
                String involved = TextViewarray[4].getText().toString();
                String owner = TextViewarray[5].getText().toString();
                String alarm = TextViewarray[6].getText().toString();
                String arrival = TextViewarray[7].getText().toString();
                String fireout = TextViewarray[8].getText().toString();
                String damage = TextViewarray[9].getText().toString();
                String fatality = TextViewarray[10].getText().toString();
                String injured = TextViewarray[11].getText().toString();
                String noofbldg = TextViewarray[12].getText().toString();
                String nooffamily = TextViewarray[13].getText().toString();
                String nooftruck = TextViewarray[14].getText().toString();
                String grouncommander = TextViewarray[15].getText().toString();
                String grouncommandernumber = TextViewarray[16].getText().toString();
                String fireindia = TextViewarray[17].getText().toString();
                String fireindianumber = TextViewarray[18].getText().toString();

                String smsreport =

                        "Complete Info\n" +

                                "\nZamboanga City Fire District Reporting a " + typeofincident + " fire at " + location + "\n" +

                                "\nResponding Team: \n" +
                                respondingteam + "\n" +

                                "\nTime and Date Reported: \n" +
                                reported + "\n" +

                                "\nInvolved \n" +
                                involved + "\n" +

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

                Toast.makeText(complete_report.this, "Copied", Toast.LENGTH_SHORT).show();

                //OPEN DEFAULT MESSAGING APP
                Intent sendIntent = new Intent(Intent.ACTION_SENDTO);
                sendIntent.setData(Uri.parse("sms:"));
                startActivity(sendIntent);

            }
        });

        clear_all_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(complete_report.this);
                View dialogView = getLayoutInflater().inflate(R.layout.dialog_clear_confirmation, null);
                builder.setView(dialogView);

                final AlertDialog dialog = builder.create();
                dialog.show();

                Button cancelBtn = dialogView.findViewById(R.id.dialog_cancel_button);
                cancelBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                Button confirmBtn = dialogView.findViewById(R.id.dialog_confirm_button);
                confirmBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

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

                        //PROGRESS STRINGS
                        //String owner = editTextArray[12].getText().toString();
                        //String damage = editTextArray[13].getText().toString();
                        //String fatality = editTextArray[14].getText().toString();
                        //String injured = editTextArray[15].getText().toString();
                        //String noofbldg = editTextArray[16].getText().toString();
                        //String nooffamily = editTextArray[17].getText().toString();
                        //String nooftruck = editTextArray[18].getText().toString();

                        // Clear all data and go to MainActivity
                        Intent intent = new Intent(complete_report.this, MainActivity.class);

                        //INTIAL STRINGS
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

                        //PROGRESS STRINGS
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
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent intent = new Intent(complete_report.this,progress_report.class);

        startActivity(intent);
        finish();
    }

    //RESPONDING TEAM DIALOG
    private StringBuilder selectedTeams_initial = new StringBuilder(); // Store the selected teams
    private void showRespondingTeamDialog() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.responding_team_dialog, null);
        dialogBuilder.setView(dialogView);

        final TextInputEditText respondingTeamField = findViewById(R.id.respondingteam_a3);
        respondingTeamField.setText(selectedTeams_initial.toString()); // Set the previously selected teams

        List<CheckBox> checkBoxes = new ArrayList<>();
        checkBoxes.add(dialogView.findViewById(R.id.checkbox_team1));
        checkBoxes.add(dialogView.findViewById(R.id.checkbox_team2));
        checkBoxes.add(dialogView.findViewById(R.id.checkbox_team3));
        checkBoxes.add(dialogView.findViewById(R.id.checkbox_team4));
        checkBoxes.add(dialogView.findViewById(R.id.checkbox_team5));
        checkBoxes.add(dialogView.findViewById(R.id.checkbox_team6));
        checkBoxes.add(dialogView.findViewById(R.id.checkbox_team7));
        checkBoxes.add(dialogView.findViewById(R.id.checkbox_team8));
        checkBoxes.add(dialogView.findViewById(R.id.checkbox_team9));
        checkBoxes.add(dialogView.findViewById(R.id.checkbox_team10));
        checkBoxes.add(dialogView.findViewById(R.id.checkbox_team11));
        checkBoxes.add(dialogView.findViewById(R.id.checkbox_team12));
        checkBoxes.add(dialogView.findViewById(R.id.checkbox_team13));
        checkBoxes.add(dialogView.findViewById(R.id.checkbox_team14));
        checkBoxes.add(dialogView.findViewById(R.id.checkbox_team15));
        checkBoxes.add(dialogView.findViewById(R.id.checkbox_team16));
        checkBoxes.add(dialogView.findViewById(R.id.checkbox_team17));
        checkBoxes.add(dialogView.findViewById(R.id.checkbox_team18));
        checkBoxes.add(dialogView.findViewById(R.id.checkbox_team19));
        checkBoxes.add(dialogView.findViewById(R.id.checkbox_team20));
        checkBoxes.add(dialogView.findViewById(R.id.checkbox_team21));
        checkBoxes.add(dialogView.findViewById(R.id.checkbox_team22));
        checkBoxes.add(dialogView.findViewById(R.id.checkbox_team23));
        checkBoxes.add(dialogView.findViewById(R.id.checkbox_team24));
        checkBoxes.add(dialogView.findViewById(R.id.checkbox_team25));
        checkBoxes.add(dialogView.findViewById(R.id.checkbox_team26));
        checkBoxes.add(dialogView.findViewById(R.id.checkbox_team27));
        checkBoxes.add(dialogView.findViewById(R.id.checkbox_team28));
        // ... Add the rest of the CheckBoxes

        TextView respoCountTextView = dialogView.findViewById(R.id.respo_count);
        respoCountTextView.setText("0"); // Initialize the count to 0

        Button cancelButton = dialogView.findViewById(R.id.dialog_cancel_button);
        Button doneButton = dialogView.findViewById(R.id.dialog_done_button);

        AlertDialog dialog = dialogBuilder.create(); // Create the dialog

        //CANCEL BUTTON
        cancelButton.setOnClickListener(v -> {
            resetDialogSelections(checkBoxes);
        });

        //DONE BUTTON
        doneButton.setOnClickListener(v -> {

            selectedTeams_initial.setLength(0); // Clear the previous selection
            int checkedCount = 0; // Count of checked checkboxes

            for (CheckBox checkBox : checkBoxes) {
                if (checkBox.isChecked()) {
                    selectedTeams_initial.append(checkBox.getText());
                    selectedTeams_initial.append("\n"); // Add a line break
                    checkedCount++;

                }
            }

            // Remove the extra line break if the last selected item exists
            if (selectedTeams_initial.length() > 0) {
                selectedTeams_initial.setLength(selectedTeams_initial.length() - 1);
            }

            respondingTeamField.setText(selectedTeams_initial.toString());
            respoCountTextView.setText(String.valueOf(checkedCount));

            dialog.dismiss();

            //count selected items

        });

        dialog.setOnShowListener(dialogInterface -> {
            // Set the CheckBoxes' states based on the selected teams
            for (CheckBox checkBox : checkBoxes) {
                checkBox.setChecked(selectedTeams_initial.toString().contains(checkBox.getText()));
            }
        });

        for (CheckBox checkBox : checkBoxes) {
            checkBox.setOnCheckedChangeListener((compoundButton, isChecked) -> {
                // Update the count when a checkbox is checked or unchecked
                int count = Integer.parseInt(respoCountTextView.getText().toString());
                count += isChecked ? 1 : -1;
                respoCountTextView.setText(String.valueOf(count));
            });
        }

        dialog.show(); // Show the dialog
    }

    private void resetDialogSelections(List<CheckBox> checkBoxes) {
        selectedTeams_initial.setLength(0); // Clear the previous selection

        for (CheckBox checkBox : checkBoxes) {
            checkBox.setChecked(false);
        }
    }

}
