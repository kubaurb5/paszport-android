package com.example.passportapp;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "PASSPORT_APP";

    private EditText etNumber, etFirstName, etLastName;
    private RadioButton rbBlue, rbGreen, rbHazel;
    private ImageView ivPhoto, ivFingerprint;
    private Button btnOk;
    private TextView tvMessage;

    private PassportValidator validator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        etNumber = findViewById(R.id.etNumber);
        etFirstName = findViewById(R.id.etFirstName);
        etLastName = findViewById(R.id.etLastName);

        rbBlue = findViewById(R.id.rbBlue);
        rbGreen = findViewById(R.id.rbGreen);
        rbHazel = findViewById(R.id.rbHazel);

        ivPhoto = findViewById(R.id.ivPhoto);
        ivFingerprint = findViewById(R.id.ivFingerprint);

        btnOk = findViewById(R.id.btnOk);
        tvMessage = findViewById(R.id.tvMessage);

        validator = new PassportValidator();

        etNumber.setOnFocusChangeListener((v, hasFocus) -> {
            if (!hasFocus) {
                updateImages();
            }
        });

        btnOk.setOnClickListener(v -> handleFormSubmission());
    }

    private void updateImages() {
        String number = etNumber.getText().toString().trim();

        int photoResId = getResources().getIdentifier("img_" + number + "_zdjecie", "drawable", getPackageName());
        int fingerprintResId = getResources().getIdentifier("img_" + number + "_odcisk", "drawable", getPackageName());

        if (photoResId != 0 && fingerprintResId != 0) {
            ivPhoto.setImageResource(photoResId);
            ivFingerprint.setImageResource(fingerprintResId);
        } else {
            ivPhoto.setImageDrawable(null);
            ivFingerprint.setImageDrawable(null);
        }
    }

    private void handleFormSubmission() {
        String firstName = etFirstName.getText().toString();
        String lastName = etLastName.getText().toString();

        String message;
        if (validator.isFormValid(firstName, lastName)) {
            String eyeColor = validator.getEyeColor(rbBlue.isChecked(), rbGreen.isChecked(), rbHazel.isChecked());
            message = validator.buildResultMessage(firstName, lastName, eyeColor);
        } else {
            message = "Wprowadź dane";
        }


        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();

        Log.d(TAG, message);

        tvMessage.setText(message);
    }
}