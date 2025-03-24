    package com.example.cvbuilderapp;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ExperienceActivity extends AppCompatActivity {

    private EditText etCompany,etJoiningYear, etEndingYear,etRole;
    private Button btnSave, btnCancel;
    private SharedPreferences sharedPreferences;
    private static final String PREFS_NAME = "CVData";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_experience);
        init();
        sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        loadExperience();
        btnSave.setOnClickListener(v -> saveExperience());
        btnCancel.setOnClickListener(v -> {
            clearAllFields();
            startActivity(new Intent(ExperienceActivity.this, MainActivity.class));
            finish();
        });
    }
    private void saveExperience() {
        String company = etCompany.getText().toString().trim();
        String joiningYear = etJoiningYear.getText().toString().trim();
        String endingYear = etEndingYear.getText().toString().trim();
        String role = etRole.getText().toString().trim();
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("company", etCompany.getText().toString().trim());
        editor.putString("joining_year", etJoiningYear.getText().toString().trim());
        editor.putString("ending_year", etEndingYear.getText().toString().trim());
        editor.putString("role", etRole.getText().toString().trim());
        if(company.isEmpty()|| joiningYear.isEmpty()|| endingYear.isEmpty() || role.isEmpty())
        {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
        }
        else {
            editor.apply();
            Toast.makeText(this, "Experience saved!", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(ExperienceActivity.this, MainActivity.class));
            finish();
        }
    }

    private void loadExperience() {
        etCompany.setText(sharedPreferences.getString("company", ""));
        etJoiningYear.setText(sharedPreferences.getString("joining_year", ""));
        etEndingYear.setText(sharedPreferences.getString("ending_year", ""));
        etRole.setText(sharedPreferences.getString("role", ""));
    }
    private void clearAllFields()
    {
        etCompany.setText("");
        etJoiningYear.setText("");
        etEndingYear.setText("");
        etRole.setText("");

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove("company");
        editor.remove("role");
        editor.remove("joining_year");
        editor.remove("ending_year");
        editor.apply();
    }
    private void init()
    {
        etCompany = findViewById(R.id.etCompany);
        etJoiningYear = findViewById(R.id.etJoiningYear);
        etEndingYear = findViewById(R.id.etEndingYear);
        etRole = findViewById(R.id.etRole);

        btnSave = findViewById(R.id.btnSave);
        btnCancel = findViewById(R.id.btnCancel);
    }
}