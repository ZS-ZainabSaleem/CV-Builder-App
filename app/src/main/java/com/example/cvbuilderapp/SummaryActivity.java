package com.example.cvbuilderapp;

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
import android.content.SharedPreferences;

public class SummaryActivity extends AppCompatActivity {
    private EditText etSummary;
    private Button btnSave, btnCancel;
    private SharedPreferences sharedPreferences;
    private static final String PREFS_NAME = "CVData";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_summary);
        init();

        sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        loadSummary();

        btnSave.setOnClickListener(v -> saveSummary());
        btnCancel.setOnClickListener(v -> {
            clearAllFields();
            startActivity(new Intent(SummaryActivity.this, MainActivity.class));
            finish();
        });
    }
    private void saveSummary() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("summary", etSummary.getText().toString().trim());
        editor.apply();
        Toast.makeText(this, "Summary saved!", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(SummaryActivity.this, MainActivity.class));
        finish();
    }

    private void loadSummary() {
        etSummary.setText(sharedPreferences.getString("summary", ""));
    }
    private void clearAllFields()
    {
        etSummary.setText("");
    }
    private void init()
    {
        etSummary = findViewById(R.id.etSummary);
        btnSave = findViewById(R.id.btnSave);
        btnCancel = findViewById(R.id.btnCancel);

    }
}