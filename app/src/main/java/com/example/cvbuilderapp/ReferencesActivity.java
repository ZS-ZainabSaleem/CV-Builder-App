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

            // Save as formatted string
            String referenceDetails = "Name: " + refName + "\n" +
                    "Organization: " + organization + "\n" +
                    "Job Title: " + jobTitle;

            editor.putString("reference", referenceDetails);
            editor.apply();

            Log.d("ReferencesActivity", "Saved Reference: " + referenceDetails);
            Toast.makeText(this, "Reference saved successfully!", Toast.LENGTH_SHORT).show();

            // Redirect to MainActivity
            Intent intent = new Intent(ReferencesActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }
    private void loadReferenceData() {
        String reference = sharedPreferences.getString("reference", "");

        if (!reference.isEmpty()) {
            String[] parts = reference.split("\n");
            if (parts.length == 3) {
                et_refName.setText(parts[0].replace("Name: ", ""));
                etref_org.setText(parts[1].replace("Organization: ", ""));
                etref_job.setText(parts[2].replace("Job Title: ", ""));
            }
        }
    }
    private void clearAllFields()
    {
        et_refName.setText("");
        etref_job.setText("");
        etref_org.setText("");
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