package com.example.cvbuilderapp;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.core.view.WindowInsetsCompat;

public class CertificationsActivity extends AppCompatActivity {

    private EditText etcertificate, etorganization;
    private Button btnSave, btnCancel;
    private SharedPreferences sharedPreferences;
    private static final String PREFS_NAME = "CVData";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_certifications);
        init();
        sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        loadCertificationData();

        // Save Button Click
        btnSave.setOnClickListener(v -> saveCertificationData());

        // Cancel Button Click
        btnCancel.setOnClickListener(v -> {
            clearAllFields();
            startActivity(new Intent(CertificationsActivity.this, MainActivity.class));
            finish();
        });
    }
    private void saveCertificationData() {
        String certification = etcertificate.getText().toString().trim();
        String issuedBy = etorganization.getText().toString().trim();

        if (certification.isEmpty() || issuedBy.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
        } else {
            SharedPreferences.Editor editor = sharedPreferences.edit();

            // Save as formatted string
            String certificationDetails = "Certification: " + certification + "\n" +
                    "Issued By: " + issuedBy;

            editor.putString("certification", certificationDetails);
            editor.apply();

            Log.d("CertificationsActivity", "Saved Certification: " + certificationDetails);
            Toast.makeText(this, "Certification saved successfully!", Toast.LENGTH_SHORT).show();

            // Redirect to MainActivity
            Intent intent = new Intent(CertificationsActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }
    private void loadCertificationData() {
        String certification = sharedPreferences.getString("certification", "");

        if (!certification.isEmpty()) {
            String[] parts = certification.split("\n");
            if (parts.length == 2) {
                etcertificate.setText(parts[0].replace("Certification: ", ""));
                etorganization.setText(parts[1].replace("Issued By: ", ""));
            }
        }
    }
    private void clearAllFields()
    {
        etcertificate.setText("");
        etorganization.setText("");
    }
    private void init()
    {
        etcertificate = findViewById(R.id.etcertificate);
        etorganization = findViewById(R.id.etorganization);
        btnSave = findViewById(R.id.btnSave);
        btnCancel = findViewById(R.id.btnCancel);
    }
}