package com.example.hw512selectingpictureexternalfile;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;

public class SettingsActivity extends AppCompatActivity {
    private final static String SHARD_PREF_NAME = "sharedPreferences";
    private final static String FILE_PATH_KEY = "filePath";

    private Button btnOk;
    private EditText inputNameImage;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        init();
    }

    private void init() {
        sharedPreferences = getSharedPreferences(SHARD_PREF_NAME, MODE_PRIVATE);

        inputNameImage = findViewById(R.id.inputNameImage);

        btnOk = findViewById(R.id.btnOk);
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(inputNameImage.getText())) {
                    showToast(getString(R.string.error_field_empty));
                    return;
                }
                String nameImage = inputNameImage.getText().toString();

                String filePath = findFilePathByName(nameImage);

                if (filePath == null) {
                    showToast(getString(R.string.error_file_not_found));
                } else {
                    sharedPreferences.edit().putString(FILE_PATH_KEY, filePath).commit();
                    finish();
                }
            }
        });
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private String findFilePathByName(String nameImage) {
        File imageFile = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_DOWNLOADS), nameImage);

        if (imageFile.exists()) {
            return imageFile.getAbsolutePath();
        } else {
            return null;
        }
    }

}
