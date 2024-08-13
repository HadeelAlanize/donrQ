package com.example.MAproject;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.content.Intent;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Donate extends AppCompatActivity {

    Toolbar toolbar;
    DatabaseHelper databaseHelper;
    String email;
    int donationCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donate);
        email = getIntent().getStringExtra("email");
        // Toolbar
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    private Map<String, List<String>> createUserMap() {
        Map<String, List<String>> userMap = new HashMap<>();

        // Add users here
        userMap.put("Ammar Almajed", Arrays.asList("O+", "King Fahd Hospital", "0542463848", "Dammam"));
        userMap.put("Sara Almohsen", Arrays.asList("A-", "King Fahd Hospital", "0587654321", "Dammam"));
        userMap.put("Khalid Alnasser", Arrays.asList("B+", "Johns Hopkins Aramco Healthcare", "0567891234", "Dhahran"));
        userMap.put("Layla Alfaraj", Arrays.asList("A+", "Dr. Sulaiman Al Habib Hospital", "0532145876", "Khobar"));
        userMap.put("Yousef Alshammari", Arrays.asList("O-", "King Fahd Hospital", "0543216789", "Dammam"));
        userMap.put("Huda Alfarsi", Arrays.asList("AB+", "Johns Hopkins Aramco Healthcare", "0556781234", "Dhahran"));
        userMap.put("Ahmed Alzahrani", Arrays.asList("O+", "Dr. Sulaiman Al Habib Hospital", "0567894321", "Khobar"));
        userMap.put("Fatima Almajed", Arrays.asList("B-", "King Fahd Hospital", "0545678901", "Dammam"));
        userMap.put("Nasser Alqahtani", Arrays.asList("AB-", "Johns Hopkins Aramco Healthcare", "0551234567", "Dhahran"));
        userMap.put("Reem Alshehri", Arrays.asList("A+", "Dr. Sulaiman Al Habib Hospital", "0556677889", "Khobar"));
        userMap.put("Abdullah Alghamdi", Arrays.asList("B-", "King Fahd Hospital", "0543344556", "Dammam"));
        userMap.put("Fahad Alharbi", Arrays.asList("AB+", "Dr. Sulaiman Al Habib Hospital", "0561234567", "Khobar"));
        userMap.put("Lama Alfares", Arrays.asList("A-", "King Fahd Hospital", "0557890123", "Dammam"));
        userMap.put("Saleh Almubarak", Arrays.asList("O+", "Johns Hopkins Aramco Healthcare", "0543214321", "Dhahran"));
        userMap.put("Mariam Alhassan", Arrays.asList("B+", "Dr. Sulaiman Al Habib Hospital", "0532145678", "Khobar"));
        userMap.put("Tariq Alnaim", Arrays.asList("AB-", "King Fahd Hospital", "0567892345", "Dammam"));
        userMap.put("Amal Alyami", Arrays.asList("A+", "Johns Hopkins Aramco Healthcare", "0546789123", "Dhahran"));
        userMap.put("Khaled Alsubaie", Arrays.asList("O-", "Dr. Sulaiman Al Habib Hospital", "0551237890", "Khobar"));
        userMap.put("Hanan Alsayed", Arrays.asList("AB+", "Johns Hopkins Aramco Healthcare", "0589123456", "Dhahran"));
        userMap.put("Omar Alkadi", Arrays.asList("B-", "Dr. Sulaiman Al Habib Hospital", "0553344556", "Khobar"));
        userMap.put("Aisha Alfayez", Arrays.asList("A-", "King Fahd Hospital", "0539876543", "Dammam"));
        userMap.put("Turki Alrashed", Arrays.asList("O+", "Johns Hopkins Aramco Healthcare", "0567890987", "Dhahran"));
        userMap.put("Samar Alawad", Arrays.asList("B+", "Dr. Sulaiman Al Habib Hospital", "0543218765", "Khobar"));
        userMap.put("Yasir Almutairi", Arrays.asList("A+", "King Fahd Hospital", "0556677889", "Dammam"));
        userMap.put("Lina Alshahrani", Arrays.asList("AB-", "Johns Hopkins Aramco Healthcare", "0543456789", "Dhahran"));
        userMap.put("Ali Alhamdan", Arrays.asList("O-", "Dr. Sulaiman Al Habib Hospital", "0532143654", "Khobar"));
        userMap.put("Noura Alsuwailem", Arrays.asList("B+", "King Fahd Hospital", "0557894321", "Dammam"));
        userMap.put("Majed Alghamdi", Arrays.asList("A-", "Johns Hopkins Aramco Healthcare", "0568765432", "Dhahran"));
        userMap.put("Mona Alsaif", Arrays.asList("A+", "King Fahd Hospital", "0556789123", "Dammam"));
        userMap.put("Faisal Alabdulaziz", Arrays.asList("O-", "Dr. Sulaiman Al Habib Hospital", "0545678912", "Khobar"));
        userMap.put("Lujain Alhamoud", Arrays.asList("B+", "Johns Hopkins Aramco Healthcare", "0533456789", "Dhahran"));
        userMap.put("Sarah Alkhaled", Arrays.asList("A-", "Dr. Sulaiman Al Habib Hospital", "0567890123", "Khobar"));
        userMap.put("Hassan Alqarni", Arrays.asList("B-", "Johns Hopkins Aramco Healthcare", "0543214567", "Dhahran"));
        userMap.put("Dana Almojil", Arrays.asList("O+", "King Fahd Hospital", "0532123456", "Dammam"));
        userMap.put("Abdulaziz Alyousef", Arrays.asList("AB+", "Dr. Sulaiman Al Habib Hospital", "0557891011", "Khobar"));
        userMap.put("Lama Alsheikh", Arrays.asList("A+", "Johns Hopkins Aramco Healthcare", "0546543210", "Dhahran"));
        userMap.put("Ahmed Alfarsi", Arrays.asList("O-", "King Fahd Hospital", "0561234567", "Dammam"));
        userMap.put("Nouf Almutairi", Arrays.asList("B+", "Dr. Sulaiman Al Habib Hospital", "0557894561", "Khobar"));
        userMap.put("Mohammed Alsalem", Arrays.asList("AB-", "Johns Hopkins Aramco Healthcare", "0546789123", "Dhahran"));
        userMap.put("Fatima Alzahrani", Arrays.asList("A-", "King Fahd Hospital", "0534567890", "Dammam"));
        userMap.put("Bandar Alshammari", Arrays.asList("B-", "Dr. Sulaiman Al Habib Hospital", "0556678901", "Khobar"));
        userMap.put("Rana Alfadhel", Arrays.asList("O+", "Johns Hopkins Aramco Healthcare", "0543216789", "Dhahran"));
        userMap.put("Yousef Almubarak", Arrays.asList("AB+", "King Fahd Hospital", "0567891234", "Dammam"));
        userMap.put("Hala Alkhaldi", Arrays.asList("A+", "Dr. Sulaiman Al Habib Hospital", "0551234567", "Khobar"));
        userMap.put("Sultan Alharbi", Arrays.asList("O-", "Johns Hopkins Aramco Healthcare", "0547890123", "Dhahran"));
        userMap.put("Reema Alsuwaigh", Arrays.asList("B+", "King Fahd Hospital", "0534561234", "Dammam"));
        userMap.put("Fahad Alghamdi", Arrays.asList("AB-", "Dr. Sulaiman Al Habib Hospital", "0557893214", "Khobar"));
        return userMap;
    }

    public void searchBlood(View view){

        // Get the Spinner instances
        Spinner spinnerBloodGroup = findViewById(R.id.btngetBloodGroup);
        Spinner spinnerDivision = findViewById(R.id.btngetDivison);

        // Retrieve the selected items
        String selectedBloodGroup = spinnerBloodGroup.getSelectedItem().toString();
        String selectedDivision = spinnerDivision.getSelectedItem().toString();

        // Your existing logic to create the user map
        Map<String, List<String>> users = createUserMap();
        List<String> matchingUsers = new ArrayList<>();

        // Update the conditional check to use selectedBloodGroup and selectedDivision
        for (Map.Entry<String, List<String>> entry : users.entrySet()) {
            List<String> userDetails = entry.getValue();
            if (userDetails.get(0).equals(selectedBloodGroup) && userDetails.get(1).equals(selectedDivision)) {
                matchingUsers.add(entry.getKey());
            }
        }

        // Find the TextView by its ID
        // TextView textViewPeople = findViewById(R.id.people);

        if (matchingUsers.isEmpty()) {
            // If no matching users are found, display a message
            Toast.makeText(Donate.this, "No patients with the provided info", Toast.LENGTH_SHORT).show();
        } else {

            LinearLayout container = findViewById(R.id.container);
            container.removeAllViews(); // Clear all views if there are any from a previous search

            for (String user : matchingUsers) {
                List<String> userDetails = users.get(user);

                // Inflate the patient item layout
                View patientItemView = getLayoutInflater().inflate(R.layout.patient_item, container, false);

                // Set the patient information to the TextView
                TextView tvPatientInfo = patientItemView.findViewById(R.id.tvPatientInfo);
                String patientInfo = user + " | " + userDetails.get(0) + " | " + userDetails.get(1) + " | " + userDetails.get(2) + " | " + userDetails.get(3);
                tvPatientInfo.setText(patientInfo);

                Button btnShowOnMap = patientItemView.findViewById(R.id.btnShowOnMap);
                btnShowOnMap.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        // Get the hospital location for the current user
                        List<String> userDetails = users.get(user);
                        if (userDetails != null && userDetails.size() > 1) {
                            // Assume the hospital location is stored in userDetails at index 1
                            String hospitalLocation = userDetails.get(1);
                            Uri gmmIntentUri = Uri.parse("geo:0,0?q=" + Uri.encode(hospitalLocation));
                            Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                            mapIntent.setPackage("com.google.android.apps.maps");
                            if (mapIntent.resolveActivity(getPackageManager()) != null) {
                                startActivity(mapIntent);
                            } else {
                                // Handle the case where Google Maps is not installed
                                Toast.makeText(Donate.this, "Google Maps not installed", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            // Handle the case where userDetails does not contain the hospital location
                            Toast.makeText(Donate.this, "Hospital location not available", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                // Add the patient item view to the container
                container.addView(patientItemView);
            }
        }
    }

    public void goToMain(View view) {
        // This will navigate back to the MainActivity
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("email",email);
        startActivity(intent);
    }

    public void Donor(View view) {
        // Get the Spinner instances
        Spinner spinnerDivision = findViewById(R.id.btngetDivison);
        // Retrieve the selected items
        String selectedDivision = spinnerDivision.getSelectedItem().toString();
        Toast.makeText(Donate.this, "We are waiting for you at " + selectedDivision, Toast.LENGTH_SHORT).show();

        //update the donation count
        databaseHelper = new DatabaseHelper(this);
        donationCount = databaseHelper.updateDonationCount(email);

        // Get the Spinner instances
        Spinner spinnerBloodGroup = findViewById(R.id.btngetBloodGroup);
        // Retrieve the selected items
        String bloodTypePatient = spinnerBloodGroup.getSelectedItem().toString();

        //store in the database
        databaseHelper.insertUserDonation(email,bloodTypePatient,selectedDivision);

        // Show a popup message asking the user to check achievements
        showAchievementsPopup();
    }



    private void showAchievementsPopup() {
        // Use an AlertDialog to show a popup message
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Donation Successful");
        builder.setMessage("Do you want to check your achievements?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Open AchievementsActivity and pass the donation count
                Intent achievementsIntent = new Intent(Donate.this, AchievementsActivity.class);
                achievementsIntent.putExtra("email",email);
                startActivity(achievementsIntent);
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Go back to the main page
                Intent mainIntent = new Intent(Donate.this, MainActivity.class);
                mainIntent.putExtra("email",email);
                startActivity(mainIntent);
            }
        });
        builder.show();
    }
}