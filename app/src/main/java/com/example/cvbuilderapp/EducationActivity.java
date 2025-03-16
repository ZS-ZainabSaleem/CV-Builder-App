package com.example.cvbuilderapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class EducationActivity extends AppCompatActivity {

    private EditText etDegree, etUniversity, etYear;
    private Button btnSave, btnCancel;
    private SharedPreferences sharedPreferences;
    private static final String PREFS_NAME = "CVData"; // Ensure consistency across activities

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_education);

        init();
        // Initialize SharedPreferences
        sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);

        // Load existing data
        loadEducationData();

        // Save Button Click
        btnSave.setOnClickListener(v -> saveEducationData());

        // Cancel Button Click
        btnCancel.setOnClickListener(v -> {
            clearAllFields();
            startActivity(new Intent(EducationActivity.this, MainActivity.class));
            finish();
        });
    }

    private void saveEducationData() {
        String degree = etDegree.getText().toString().trim();
        String university = etUniversity.getText().toString().trim();
        String year = etYear.getText().toString().trim();

        if (degree.isEmpty() || university.isEmpty() || year.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
        } else {
            SharedPreferences.Editor editor = sharedPreferences.edit();

            // Save education as a single formatted string for FinalActivity
            String educationDetails = "Degree: " + degree + "\n" +
                    "University: " + university + "\n" +
                    "Year of Graduation: " + year;

            editor.putString("education", educationDetails);
            editor.apply();

            Log.d("EducationActivity", "Saved Education: " + educationDetails);
            Toast.makeText(this, "Education saved successfully!", Toast.LENGTH_SHORT).show();

            // Redirect to FinalActivity
            Intent intent = new Intent(EducationActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    private void loadEducationData() {
        String education = sharedPreferences.getString("education", "");

        if (!education.isEmpty()) {
            String[] parts = education.split("\n");
            if (parts.length == 3) {
                etDegree.setText(parts[0].replace("Degree: ", ""));
                etUniversity.setText(parts[1].replace("University: ", ""));
                etYear.setText(parts[2].replace("Year of Graduation: ", ""));
            }
        }
    }
    private void clearAllFields()
    {
        etDegree.setText("");
        etUniversity.setText("");
        etYear.setText("");
    }
    private void init()
    {
        etDegree = findViewById(R.id.etDegree);
        etUniversity = findViewById(R.id.etUniversity);
        etYear=findViewById(R.id.etYear);
        btnSave = findViewById(R.id.btnSave);
        btnCancel = findViewById(R.id.btnCancel);
    }
}