package com.example.cvbuilderapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import android.content.SharedPreferences;
import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
public class ProfilePictureActivity extends AppCompatActivity {

    ImageView iv_ProfilePicture;
    Button btnSave,btnCancel;
    FloatingActionButton fabSetProfilePic;
    ActivityResultLauncher<Intent> getImageLauncher;
    Uri selectedImageUri;
    SharedPreferences sharedPreferences;
    private static final String PREFS_NAME = "CVData";
    private static final String PROFILE_PIC_KEY = "profilePic";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_profile_picture);
        init();
        //loadSavedImage(); // Load previously saved image if available
        fabSetProfilePic.setOnClickListener((v)->{
            Intent i = new Intent(Intent.ACTION_PICK);
            i.setType("image/*");
            getImageLauncher.launch(i);
        });

        btnSave.setOnClickListener((v) -> {
            if (selectedImageUri != null) {
                saveImageToPreferences(selectedImageUri.toString());
                Toast.makeText(this, "Profile picture saved!", Toast.LENGTH_SHORT).show();
                finish(); // Close activity after saving
            } else {
                Toast.makeText(this, "Please select an image", Toast.LENGTH_SHORT).show();
            }
        });
        btnCancel.setOnClickListener((v)->{
            startActivity(new Intent(ProfilePictureActivity.this, MainActivity.class));
            finish();
        });
    }
    private void init()
    {
        iv_ProfilePicture=findViewById(R.id.iv_ProfilePicture);
        fabSetProfilePic = findViewById(R.id.fab_setProfilePic);
        btnSave = findViewById(R.id.btnSave);
        btnCancel = findViewById(R.id.btnCancel);

        sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);

        getImageLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                (result) -> {
                    if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                        selectedImageUri = result.getData().getData(); // Update selectedImageUri
                        iv_ProfilePicture.setImageURI(selectedImageUri);
                    } else {
                        Toast.makeText(this, "Please select an image", Toast.LENGTH_SHORT).show();
                    }
                });
    }
    private void saveImageToPreferences(String imageUri) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(PROFILE_PIC_KEY, imageUri);
        editor.apply();
    }
    private void loadSavedImage() {
        String savedImageUri = sharedPreferences.getString(PROFILE_PIC_KEY, "");
        if (!savedImageUri.isEmpty()) {
            try {
                Uri uri = Uri.parse(savedImageUri);
                iv_ProfilePicture.setImageURI(uri);
            } catch (Exception e) {
                Toast.makeText(this, "Failed to load saved image", Toast.LENGTH_SHORT).show();
            }
        }
    }
}