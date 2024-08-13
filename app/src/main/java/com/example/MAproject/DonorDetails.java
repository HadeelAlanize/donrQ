package com.example.MAproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.PopupMenu;
import android.widget.Toast;
import com.example.MAproject.databinding.ActivityDonorDetailsBinding;
import androidx.appcompat.widget.Toolbar;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class DonorDetails extends AppCompatActivity {
    public Toolbar toolbar;
    ActivityDonorDetailsBinding binding;
    DatabaseHelper databaseHelper;
    Calendar myCalendar= Calendar.getInstance();
    String email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDonorDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        email = getIntent().getStringExtra("email");

        // Toolbar
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        databaseHelper = new DatabaseHelper(this);
        showData();
        binding.gender.setFocusable(false);
        binding.gender.setClickable(true);
        binding.gender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popupMenu = new PopupMenu(DonorDetails.this, binding.gender);
                popupMenu.getMenuInflater().inflate(R.menu.gender, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {

                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        binding.gender.setText(menuItem.getTitle());
                        return true;
                    }
                });
                popupMenu.show();
            }
        });
        binding.bloodType.setFocusable(false);
        binding.bloodType.setClickable(true);
        binding.bloodType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                PopupMenu popupMenu = new PopupMenu(DonorDetails.this, binding.bloodType);


                popupMenu.getMenuInflater().inflate(R.menu.blood, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {


                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {

                        binding.bloodType.setText(menuItem.getTitle());
                        return true;
                    }
                });

                popupMenu.show();
            }
        });
        binding.city.setFocusable(false);
        binding.city.setClickable(true);
        binding.city.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                PopupMenu popupMenu = new PopupMenu(DonorDetails.this, binding.city);


                popupMenu.getMenuInflater().inflate(R.menu.city, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {


                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {

                        binding.city.setText(menuItem.getTitle());
                        return true;
                    }
                });

                popupMenu.show();
            }
        });

        DatePickerDialog.OnDateSetListener date =new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH,month);
                myCalendar.set(Calendar.DAY_OF_MONTH,day);
                String myFormat="MM/dd/yyyy";
                SimpleDateFormat dateFormat=new SimpleDateFormat(myFormat, Locale.US);
                binding.birthdate.setText(dateFormat.format(myCalendar.getTime()));
            }
        };
        binding.birthdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(DonorDetails.this,date,myCalendar.get(Calendar.YEAR),myCalendar.get(Calendar.MONTH),myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        binding.updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = binding.name.getText() != null ? binding.name.getText().toString() : "";
                String mobile = binding.mobile.getText() != null ? binding.mobile.getText().toString() : "";
                String city = binding.city.getText() != null ? binding.city.getText().toString() : "";
                String address = binding.address.getText() != null ? binding.address.getText().toString() : "";
                String gender = binding.gender.getText() != null ? binding.gender.getText().toString() : "";
                String bloodType = binding.bloodType.getText() != null ? binding.bloodType.getText().toString() : "";
                String birthDate = binding.birthdate.getText() != null ? binding.birthdate.getText().toString() : "";



                if (TextUtils.isEmpty(name) || TextUtils.isEmpty(mobile) ||
                        TextUtils.isEmpty(city) || TextUtils.isEmpty(address) || TextUtils.isEmpty(gender)
                        || TextUtils.isEmpty(bloodType) || TextUtils.isEmpty(birthDate)) {
                    Toast.makeText(DonorDetails.this, "All fields are mandatory", Toast.LENGTH_SHORT).show();
                }
                else if ((mobile.length()!=10) || (!mobile.startsWith("05"))) {
                    Toast.makeText(DonorDetails.this, "Invalid Mobile number should start with 05", Toast.LENGTH_SHORT).show();
                }

                else {

                            Boolean update = databaseHelper.updateDonor(LoginActivity.username, name,mobile,city,address,gender,bloodType,birthDate);

                            if(update == true) {
                                Toast.makeText(DonorDetails.this, "Updated Successfully", Toast.LENGTH_SHORT).show();
                                showData();

                            } else {
                                Toast.makeText(DonorDetails.this, "Updated Failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
        });
        Button back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DonorDetails.this, MainActivity.class);
                intent.putExtra("email",email);
                startActivity(intent);
                finish();
            }
        });
    }

    public void showData(){
        Cursor cursor=databaseHelper.GetDonor(LoginActivity.username);
        if(cursor.moveToFirst()){
            binding.name.setText(cursor.getString(2));
            binding.mobile.setText(cursor.getString(3));
            binding.city.setText(cursor.getString(4));
            binding.address.setText(cursor.getString(5));
            binding.gender.setText(cursor.getString(6));
            binding.bloodType.setText(cursor.getString(7));
            binding.birthdate.setText(cursor.getString(8));
        }
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

