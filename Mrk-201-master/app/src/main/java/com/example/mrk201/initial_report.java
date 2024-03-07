package com.example.mrk201;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
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
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.android.material.timepicker.MaterialTimePicker;
import com.google.android.material.timepicker.TimeFormat;

import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class initial_report extends AppCompatActivity {

    //START OF TEST CODE

    //END OF TEST CODE

    String[] typeofincidentdrop = {"Residential","Non Residential","Non Structural","Transport"};

    String[] invlveddrop = {"Residential","Assembly","Educational","Day Care","Health Care","Residential Board and Care","Detention and Correctional",
            "Mercantile","Business","Indsutrial","Storage","Mixed Occupancies","Special Structure", "Grass","Agricultural","Rubbish","Electrical Post","Forest"};
    String [] alarmdrop = {"Unresponded","Fire Out Upon Arrival","1st Alarm","2nd Alarm","3rd Alarm","4th Alarm","5th Alarm", "Task Force Alpha", "Task Force Bravo", "Task Force Charlie","Task Force Delta","General Alarm"};

    String[] respondingteamdroplist = {"Central Fire Station - Bauer 10", "Central Fire Station - Bauer 23", "Central Fire Station - Hino", "SRF", "EMS", "Ayala Fire Sub-Station","Boalan Fire Sub-Station","Calarian Fire Sub-Station",
            "Culianan Fire Sub-Station","Guiwan Fire Sub-Station","Labuan Fire Sub-Station","Lunzuran Fire Sub-Station", "Mampang Fire Sub-Station","Manicahan Fire Sub-Station",
            "Mercedes Fire Sub-Station","Putik Fire Sub-Station","Quiniput Fire Sub-Station","Recodo Fire Sub-Station","San Jose Gusu Fire Sub-Station", "Sangali Fire Sub-Station","Sta Catalina Fire Sub-Station",
            "Sta Maria Fire Sub-Station","Talisayan Fire Sub-Station", "Tetuan Fire Sub-Station","Tumaga Fire Sub-Station","Vitali Fire Sub-Station"};

    AutoCompleteTextView autoCompleteTextView;
    ArrayAdapter<String> adapterItems;
    EditText[] editTextArray = new EditText[14];
    Button proceed_progress_act;
    Button submit_initial_sms;

    TextView respoCountTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intial_report);

        //LOCATION TEST

        //TYPE OF INCIDENT
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

        //RESPONDING TEAM
        TextInputEditText respondingTeamEditText = findViewById(R.id.respondingteam_a3);
        respondingTeamEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showRespondingTeamDialog();
            }
        });
        //END OF TEST CODE

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
                datePickerBuilder.setTitleText("Date Reported"); // Set the title of the date picker
                MaterialDatePicker<Long> materialDatePicker = datePickerBuilder.build();

                // Set the listener for the selected date
                materialDatePicker.addOnPositiveButtonClickListener(selection -> {
                    calendar.setTimeInMillis(selection);

                    // Create the MaterialTimePicker for selecting the time
                    MaterialTimePicker.Builder timePickerBuilder = new MaterialTimePicker.Builder();
                    timePickerBuilder.setTimeFormat(TimeFormat.CLOCK_24H);
                    timePickerBuilder.setHour(hour); // Set the initial hour
                    timePickerBuilder.setMinute(minute); // Set the initial minute
                    timePickerBuilder.setTitleText("Time Reported"); // Set the title of the time picker
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
                datePickerBuilder.setTitleText("Date of Arrival"); // Set the title of the date picker
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
                    timePickerBuilder.setTitleText("Time of Arrival"); // Set the title of the time picker
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
                datePickerBuilder.setTitleText("Date of Fire Out"); // Set the title of the date picker
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
                    timePickerBuilder.setTitleText("Time Fire Out"); // Set the title of the time picker
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

        //TYPE OF OCCUPANCY
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

        //ALARM STATUS
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


        proceed_progress_act = findViewById(R.id.proceed_progress);

        //SUBMIT INITIAL REPORT
        editTextArray[0] = findViewById(R.id.location_a1);
        editTextArray[1] = findViewById(R.id.typeofincident_a2);
        editTextArray[2] = findViewById(R.id.respondingteam_a3);
        editTextArray[3] = findViewById(R.id.reported_a4);
        editTextArray[4] = findViewById(R.id.involved_a5);
        editTextArray[5] = findViewById(R.id.alarm_a6);
        editTextArray[6] = findViewById(R.id.arrival_a7);
        editTextArray[7] = findViewById(R.id.fireout_a8);
        editTextArray[8] = findViewById(R.id.groundcom_a9);
        editTextArray[9] = findViewById(R.id.groundno_a10);
        editTextArray[10] = findViewById(R.id.fireindia_a11);
        editTextArray[11] = findViewById(R.id.fireindiano_a12);

        submit_initial_sms = findViewById(R.id.submit_initial);

        submit_initial_sms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String location = editTextArray[0].getText().toString();
                String typeofincident = editTextArray[1].getText().toString();
                String respondingteam = editTextArray[2].getText().toString();
                String reported = editTextArray[3].getText().toString();
                String involved = editTextArray[4].getText().toString();
                String alarm = editTextArray[5].getText().toString();
                String arrival = editTextArray[6].getText().toString();
                String fireout = editTextArray[7].getText().toString();
                String grouncommander = editTextArray[8].getText().toString();
                String grouncommandernumber = editTextArray[9].getText().toString();
                String fireindia = editTextArray[10].getText().toString();
                String fireindianumber = editTextArray[11].getText().toString();


                String smsreport =

                        "Initial Info\n" +
                                "\nZamboanga City Fire District Reporting a " + typeofincident + " Fire at " + location + "\n" +
                                "\nResponding Team: \n" +
                                respondingteam + "\n" +

                                "\nTime and Date Reported: \n" +
                                reported + "\n" +

                                "\nInvolved \n" +
                                involved + "\n" +

                                "\nAlarm Status: \n" +
                                alarm + "\n" +

                                "\nTime of Arrival: \n" +
                                arrival + "\n" +

                                "\nFire Out: \n" +
                                fireout + "\n" +

                                "\nGround Commander: \n" +
                                grouncommander + "\n" + grouncommandernumber + "\n" +

                                "\nFire Investigator: \n" +
                                fireindia + "\n" + fireindianumber;


                ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("EditText", smsreport);
                clipboard.setPrimaryClip(clip);

                Toast.makeText(initial_report.this, "Copied", Toast.LENGTH_SHORT).show();

                //OPEN DEFAULT MESSAGING APP
                Intent sendIntent = new Intent(Intent.ACTION_SENDTO);
                sendIntent.setData(Uri.parse("sms:"));
                startActivity(sendIntent);
            }
        });

        editTextArray[0] = findViewById(R.id.location_a1);
        editTextArray[1] = findViewById(R.id.typeofincident_a2);
        editTextArray[2] = findViewById(R.id.respondingteam_a3);
        editTextArray[3] = findViewById(R.id.reported_a4);
        editTextArray[4] = findViewById(R.id.involved_a5);
        editTextArray[5] = findViewById(R.id.alarm_a6);
        editTextArray[6] = findViewById(R.id.arrival_a7);
        editTextArray[7] = findViewById(R.id.fireout_a8);
        editTextArray[8] = findViewById(R.id.groundcom_a9);
        editTextArray[9] = findViewById(R.id.groundno_a10);
        editTextArray[10] = findViewById(R.id.fireindia_a11);
        editTextArray[11] = findViewById(R.id.fireindiano_a12);

        proceed_progress_act.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //INITIAL STRINGS
                String location = editTextArray[0].getText().toString();
                String typeofincident = editTextArray[1].getText().toString();
                String respondingteam = editTextArray[2].getText().toString();
                String reported = editTextArray[3].getText().toString();
                String involved = editTextArray[4].getText().toString();
                String alarm = editTextArray[5].getText().toString();
                String arrival = editTextArray[6].getText().toString();
                String fireout = editTextArray[7].getText().toString();
                String grouncommander = editTextArray[8].getText().toString();
                String grouncommandernumber = editTextArray[9].getText().toString();
                String fireindia = editTextArray[10].getText().toString();
                String fireindianumber = editTextArray[11].getText().toString();

                //PROGRESS STRINGS
                //String owner = editTextArray[12].getText().toString();
                //String damage = editTextArray[13].getText().toString();
                //String fatality = editTextArray[14].getText().toString();
                //String injured = editTextArray[15].getText().toString();
                //String noofbldg = editTextArray[16].getText().toString();
                //String nooffamily = editTextArray[17].getText().toString();
                //String nooftruck = editTextArray[18].getText().toString();

                //String countString = respoCountTextView.getText().toString();

                Intent intent = new Intent(initial_report.this,progress_report.class);

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
                //intent.putExtra("owner", owner);
                //intent.putExtra("damage", damage);
                //intent.putExtra("fatality", fatality);
                //intent.putExtra("injured", injured);
                //intent.putExtra("noofbldg", noofbldg);
                //intent.putExtra("nooffamily", nooffamily);
                //intent.putExtra("nooftruck", nooftruck);

                //intent.putExtra("countString",countString);

                startActivity(intent);
                finish();
            }
        });

    }

    @Override
    public void onBackPressed() {

        AlertDialog.Builder builder = new AlertDialog.Builder(initial_report.this);
        View dialogView = getLayoutInflater().inflate(R.layout.dialog_areyousure_confirmation, null);
        builder.setView(dialogView);

        Button confirmButton = dialogView.findViewById(R.id.dialog_yes_button);
        Button cancelButton = dialogView.findViewById(R.id.dialog_no_button);

        AlertDialog dialog = builder.create();
        dialog.show();

        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(initial_report.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialog.dismiss();
            }
        });



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



        //INTIAL STRINGS
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

        //PROGRESS STRINGS
        //intent.putExtra("owner", owner);
        //intent.putExtra("damage", damage);
        //intent.putExtra("fatality", fatality);
        //intent.putExtra("injured", injured);
        //intent.putExtra("noofbldg", noofbldg);
        //intent.putExtra("nooffamily", nooffamily);
        //intent.putExtra("nooftruck", nooftruck);

    }

    //ON BACK PRESSED SHARED PREFERENCE
    //@Override
    //public void onBackPressed() {
        //super.onBackPressed();

    //TEST CODE

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
