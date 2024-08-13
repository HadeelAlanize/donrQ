package com.example.MAproject;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;


public class MainActivity extends AppCompatActivity {

     Toolbar toolbar;
    String email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Toolbar
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        email = getIntent().getStringExtra("email");

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start DonorDetails Activity
                Intent intent = new Intent(MainActivity.this,DonorDetails.class);
                intent.putExtra("email",email);
                startActivity(intent);
            }
        });

        Button button2 = findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start Donate Activity
                Intent intent = new Intent(MainActivity.this,Donate.class);
                intent.putExtra("email",email);
                startActivity(intent);
            }
        });

        Button button3 = findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start Achievements Activity
                Intent intent = new Intent(MainActivity.this,AchievementsActivity.class);
                intent.putExtra("email",email);
                startActivity(intent);
            }
        });
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
                Intent donorDetailsIntent = new Intent(this, DonorDetails.class);
                startActivity(donorDetailsIntent);
                return true;
            case R.id.nav_main:

                return true;
            case R.id.logout:
                Intent logout = new Intent(this, LoginActivity.class);
                startActivity(logout);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}






