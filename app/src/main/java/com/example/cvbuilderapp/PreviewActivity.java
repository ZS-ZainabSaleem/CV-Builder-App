package com.example.cvbuilderapp;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;

public class PreviewActivity extends AppCompatActivity {

    private TextView tvName, tvEmail, tvPhone, tvSummary, tvEducation, tvExperience, tvReferences, tvCertificaion;
    private ImageView ivProfilePic;
    private SharedPreferences sharedPreferences;
    private static final String PREFS_NAME = "CVData";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_preview);
        init();
        loadData();
    }
    private void loadData() {
        sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);

        // Retrieve saved data
        String name = sharedPreferences.getString("name", "Your Name");
        String email = sharedPreferences.getString("email", "your.email@example.com");
        String phone = sharedPreferences.getString("phone", "+000-000-0000");
        String summary = sharedPreferences.getString("summary", "No summary provided.");
        //String education = sharedPreferences.getString("education", "No education details provided.");
        //String experience = sharedPreferences.getString("experience", "No work experience provided.");
        //String references = sharedPreferences.getString("reference", "No references provided.");
        String profilePicPath = sharedPreferences.getString("profilePic", "");
        String experience = "Company: " + sharedPreferences.getString("company", "Not provided") + "\n" +
                "Role: " + sharedPreferences.getString("role", "Not provided") + "\n" +
                "Joining Year: " + sharedPreferences.getString("joining_year", "Not provided") + "\n" +
                "Ending Year: " + sharedPreferences.getString("ending_year", "Not provided");
        String referenceDetails = "Name: " + sharedPreferences.getString("reference_name", "Not provided") + "\n" +
                "Organization: " + sharedPreferences.getString("reference_org", "Not provided") + "\n" +
                "Job Title: " + sharedPreferences.getString("reference_job", "Not provided");
        String certificationDetails = "Certification: " + sharedPreferences.getString("certification_name", "Not provided") + "\n" +
                "Issued By: " + sharedPreferences.getString("certification_issuer", "Not provided");
        String educationDetails = "Degree: " + sharedPreferences.getString("education_degree", "Not provided") + "\n" +
                "University: " + sharedPreferences.getString("education_university", "Not provided") + "\n" +
                "Year: " + sharedPreferences.getString("education_year", "Not provided");
        // Set data to views
        tvName.setText(name);
        tvEmail.setText(email);
        tvPhone.setText(phone);
        tvSummary.setText(summary);
        tvEducation.setText(educationDetails);
        tvExperience.setText(experience);
        tvReferences.setText(referenceDetails);
        tvCertificaion.setText(certificationDetails);

        // Load profile picture if available
        if (ivProfilePic != null && profilePicPath != null && !profilePicPath.isEmpty()) {
            ivProfilePic.setImageURI(android.net.Uri.parse(profilePicPath));
        }
    }
    private void init()
    {
        ivProfilePic = findViewById(R.id.ivProfilePic);
        tvName = findViewById(R.id.tvName);
        tvEmail = findViewById(R.id.tvEmail);
        tvPhone = findViewById(R.id.tvPhone);
        tvSummary = findViewById(R.id.tvSummary);
        tvEducation = findViewById(R.id.tvEducation);
        tvExperience = findViewById(R.id.tvExperience);
        tvReferences = findViewById(R.id.tvReferences);
        tvCertificaion = findViewById(R.id.tvCertificaion);
    }
}