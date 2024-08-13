package com.example.MAproject;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class AchievementsActivity extends AppCompatActivity {
    private TextView donationCountTextView;
    private TextView achievementTextView;
    private String email;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_achievements);
        email = getIntent().getStringExtra("email");
        DatabaseHelper databaseHelper = new DatabaseHelper(this);

        // Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Initialize TextViews
        donationCountTextView = findViewById(R.id.donationCountTextView);
        achievementTextView = findViewById(R.id.achievementTextView);

        // Receive donation count from database
        int donationCount = databaseHelper.getAchievementsCount(email);

        // Update TextViews based on the donation count
        updateAchievements(donationCount);

        // Link to MainActivity
        findViewById(R.id.buttonGoToMain).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AchievementsActivity.this, MainActivity.class);
                intent.putExtra("email",email);
                startActivity(intent);
            }
        });
    }

    // Update TextViews based on donor details
    @SuppressLint("SetTextI18n")
    private void updateAchievements(int donationCount) {
        // Determine achievement level based on the donation count
        if (donationCount == 1) {
            achievementTextView.setText("Congratulations! You made your first donation!");
        } else if (donationCount >= 1 && donationCount < 5) {
            achievementTextView.setText("YOU ARE A BRONZE DONOR!");
        } else if (donationCount >=5 && donationCount < 10 ){
            achievementTextView.setText("YOU ARE A SILVER DONOR!");
        } else if (donationCount > 10 ){
            achievementTextView.setText("YOU ARE A GOLD DONOR!");
        }
        donationCountTextView.setText("Total Number of Donations = "+donationCount);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.tool, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_donor_details:
                startActivity(new Intent(this, DonorDetails.class));
                return true;
            case R.id.nav_main:
                startActivity(new Intent(this, MainActivity.class));
                return true;
            case R.id.logout:
                startActivity(new Intent(this, LoginActivity.class));
                finish(); // Close the achievements activity when logging out
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
