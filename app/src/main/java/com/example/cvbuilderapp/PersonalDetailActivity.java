    package com.example.cvbuilderapp;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Button;
import android.content.SharedPreferences;
import android.widget.Toast;
import android.view.View;

public class PersonalDetailActivity extends AppCompatActivity {
    EditText et_Name,et_Email,et_Phone;
    Button btnSave,btnCancel;
    private SharedPreferences sharedPreferences;
    private static final String PREFS_NAME = "CVData";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_personal_detail);
        init();
        sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        loadPersonalDetails();

        btnSave.setOnClickListener(v -> savePersonalDetails());
        btnCancel.setOnClickListener(v -> {
            clearAllFields();
            startActivity(new Intent(PersonalDetailActivity.this, MainActivity.class));
            finish();
        });
    }
    private void savePersonalDetails() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        String Name=et_Name.getText().toString().trim();
        String Email=et_Email.getText().toString().trim();
        String Phone=et_Phone.getText().toString().trim();
        editor.putString("name", Name);
        editor.putString("email", Email);
        editor.putString("phone", Phone);
        if(Name.isEmpty() || Email.isEmpty() || Phone.isEmpty())
        {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
        }
        editor.apply();
        Toast.makeText(this, "Personal details saved!", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(PersonalDetailActivity.this, MainActivity.class));
        finish();
    }
    private void loadPersonalDetails() {
        et_Name.setText(sharedPreferences.getString("name", ""));
        et_Email.setText(sharedPreferences.getString("email", ""));
        et_Phone.setText(sharedPreferences.getString("phone", ""));
    }
    private void clearAllFields()
    {
        et_Name.setText("");
        et_Email.setText("");
        et_Phone.setText("");
    }
    private void init()
    {
        et_Name=findViewById(R.id.et_Name);
        et_Email = findViewById(R.id.et_Email);
        et_Phone = findViewById(R.id.et_Phone);
        btnSave = findViewById(R.id.btnSave);
        btnCancel = findViewById(R.id.btnCancel);
    }
}