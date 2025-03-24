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

            // Save fields separately
            editor.putString("education_degree", degree);
            editor.putString("education_university", university);
            editor.putString("education_year", year);
            editor.apply();

            Log.d("EducationActivity", "Saved Education: " + degree + ", " + university + ", " + year);
            Toast.makeText(this, "Education saved successfully!", Toast.LENGTH_SHORT).show();

            // Redirect to PreviewActivity
            Intent intent = new Intent(EducationActivity.this, PreviewActivity.class);
            startActivity(intent);
            finish();
        }
    }

    private void loadEducationData() {
        if (etDegree != null && etUniversity != null && etYear != null) {
            etDegree.setText(sharedPreferences.getString("education_degree", ""));
            etUniversity.setText(sharedPreferences.getString("education_university", ""));
            etYear.setText(sharedPreferences.getString("education_year", ""));
        } else {
            Log.e("EducationActivity", "One or more EditText fields are null!");
        }
    }
    private void clearAllFields()
    {
        etDegree.setText("");
        etUniversity.setText("");
        etYear.setText("");

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove("education_degree");
        editor.remove("education_university");
        editor.remove("education_year");
        editor.apply();
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