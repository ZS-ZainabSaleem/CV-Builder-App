package com.example.cvbuilderapp;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.content.Intent;
import android.widget.Button;
public class MainActivity extends AppCompatActivity {

    Button btnProfile, btnDetails, btnSummary, btnEducation, btnExperience, btnCertifications,
            btnReferences,btnSubmit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        init();
        btnProfile.setOnClickListener((v)->{
            Intent intent = new Intent(MainActivity.this, ProfilePictureActivity.class);
            startActivity(intent);
        });
        btnDetails.setOnClickListener((v)->{
            Intent intent = new Intent(MainActivity.this, PersonalDetailActivity.class);
            startActivity(intent);
        });
        btnSummary.setOnClickListener((v)->{
            Intent intent = new Intent(MainActivity.this, SummaryActivity.class);
            startActivity(intent);
        });
        btnEducation.setOnClickListener((v)->{
            Intent intent = new Intent(MainActivity.this, EducationActivity.class);
            startActivity(intent);
        });
        btnExperience.setOnClickListener((v)->{
            Intent intent = new Intent(MainActivity.this, ExperienceActivity.class);
            startActivity(intent);
        });
        btnCertifications.setOnClickListener((v)->{
            Intent intent = new Intent(MainActivity.this, CertificationsActivity.class);
            startActivity(intent);
        });
        btnReferences.setOnClickListener((v)->{
            Intent intent = new Intent(MainActivity.this, ReferencesActivity.class);
            startActivity(intent);
        });
        btnSubmit.setOnClickListener((v)->{
            Intent intent = new Intent(MainActivity.this, PreviewActivity.class);
            startActivity(intent);
        });
    }
    private void init()
    {
        btnProfile=findViewById(R.id.btnProfile);
        btnDetails=findViewById(R.id.btnDetails);
        btnSummary=findViewById(R.id.btnSummary);
        btnEducation=findViewById(R.id.btnEducation);
        btnExperience=findViewById(R.id.btnExperience);
        btnCertifications=findViewById(R.id.btnCertifications);
        btnReferences=findViewById(R.id.btnReferences);
        btnSubmit=findViewById(R.id.btnSubmit);

    }
}