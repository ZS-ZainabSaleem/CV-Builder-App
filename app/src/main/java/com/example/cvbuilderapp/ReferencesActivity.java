package com.example.cvbuilderapp;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.content.Intent;
public class ReferencesActivity extends AppCompatActivity {

    private EditText et_refName, etref_job, etref_org;
    private Button btnSave, btnCancel;
    private SharedPreferences sharedPreferences;
    private static final String PREFS_NAME = "CVData"; // Shared Preferences File Name

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_references);
        init();

        sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);

        // Load existing data
        loadReferenceData();

        // Save Button Click
        btnSave.setOnClickListener(v -> saveReferenceData());
        btnCancel.setOnClickListener(v -> {
            clearAllFields();
            startActivity(new Intent(ReferencesActivity.this, MainActivity.class));
            finish();
        });
    }
    private void saveReferenceData() {
        String refName = et_refName.getText().toString().trim();
        String organization = etref_org.getText().toString().trim();
        String jobTitle = etref_job.getText().toString().trim();

        if (refName.isEmpty() || organization.isEmpty() || jobTitle.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
        } else {
            SharedPreferences.Editor editor = sharedPreferences.edit();

            // Save each field separately
            editor.putString("reference_name", refName);
            editor.putString("reference_org", organization);
            editor.putString("reference_job", jobTitle);
            editor.apply();

            Log.d("ReferencesActivity", "Saved Reference: " + refName + ", " + organization + ", " + jobTitle);
            Toast.makeText(this, "Reference saved successfully!", Toast.LENGTH_SHORT).show();

            // Redirect to MainActivity
            startActivity(new Intent(ReferencesActivity.this, MainActivity.class));
            finish();
        }
    }
    private void loadReferenceData() {
        // Retrieve saved values separately
        String refName = sharedPreferences.getString("reference_name", "");
        String organization = sharedPreferences.getString("reference_org", "");
        String jobTitle = sharedPreferences.getString("reference_job", "");

        if (!refName.isEmpty() || !organization.isEmpty() || !jobTitle.isEmpty()) {
            et_refName.setText(refName);
            etref_org.setText(organization);
            etref_job.setText(jobTitle);
        }
    }
    private void clearAllFields()
    {
        et_refName.setText("");
        etref_job.setText("");
        etref_org.setText("");

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove("reference");
        editor.apply();
    }
    private void init()
    {
        et_refName = findViewById(R.id.et_refName);
        etref_job = findViewById(R.id.etref_job);
        etref_org = findViewById(R.id.etref_org);
        btnSave = findViewById(R.id.btnSave);
        btnCancel = findViewById(R.id.btnCancel);

    }
}