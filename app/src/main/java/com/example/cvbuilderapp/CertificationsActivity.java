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

                // Save fields separately
                editor.putString("certification_name", certification);
                editor.putString("certification_issuer", issuedBy);
                editor.apply();

                Log.d("CertificationsActivity", "Saved Certification: " + certification + " | Issued By: " + issuedBy);
                Toast.makeText(this, "Certification saved successfully!", Toast.LENGTH_SHORT).show();

                // Redirect to MainActivity
                Intent intent = new Intent(CertificationsActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
                }
        }
        private void loadCertificationData() {
            if (etcertificate != null && etorganization != null) {
                String certification = sharedPreferences.getString("certification_name", "");
                String issuedBy = sharedPreferences.getString("certification_issuer", "");

                etcertificate.setText(certification);
                etorganization.setText(issuedBy);
            }
        }
        private void clearAllFields()
        {
            etcertificate.setText("");
            etorganization.setText("");

            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.remove("certification_name");
            editor.remove("certification_issuer");
            editor.apply();
        }
        private void init()
        {
            etcertificate = findViewById(R.id.etcertificate);
            etorganization = findViewById(R.id.etorganization);
            btnSave = findViewById(R.id.btnSave);
            btnCancel = findViewById(R.id.btnCancel);
        }
    }