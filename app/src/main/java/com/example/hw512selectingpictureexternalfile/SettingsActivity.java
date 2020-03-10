package com.example.hw512selectingpictureexternalfile;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;

public class SettingsActivity extends AppCompatActivity {

    private Button btnOk;
    private EditText inputNameImage;
    private ImageView backgroundImageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        init();
    }

    private void init() {
        backgroundImageView = findViewById(R.id.backgroundImageView);
        inputNameImage = findViewById(R.id.inputNameImage);
        btnOk = findViewById(R.id.btnOk);
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameImage = inputNameImage.getText().toString() + ".jpg";
                //TODO замуты с TextUtils
                //TODO Проверку на ошибки и тд
                setBackgroundByNameImage(nameImage);
            }
        });
    }

    private void setBackgroundByNameImage(String nameImage) {
        File imageFile = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_DOWNLOADS), nameImage);
        Bitmap bitmap = BitmapFactory.decodeFile(imageFile.getPath());
        //backgroundImageView.setImageBitmap(bitmap);

       // MediaStore.Downloads

    }
}
