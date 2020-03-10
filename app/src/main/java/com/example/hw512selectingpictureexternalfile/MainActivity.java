package com.example.hw512selectingpictureexternalfile;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class MainActivity extends AppCompatActivity {
    private ConstraintLayout engineeringView;
    private ConstraintLayout mainView;
    private CalcView curCalcView = CalcView.MAIN_VIEW;
    private String textBuffer;
    private TextView txtOutput;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        engineeringView = findViewById(R.id.engineeringView);
        engineeringView.setVisibility(View.GONE);
        txtOutput = findViewById(R.id.txtOutput);
        mainView = findViewById(R.id.mainView);


    }

    public void onClick(View v) { //TODO больше не работает
        Button btn = (Button) v;
        String curBtn = btn.getText().toString();

        if (TextUtils.isEmpty(txtOutput.getText())) {
            textBuffer = "";
        } else {
            textBuffer = txtOutput.getText().toString();
        }

        if (curBtn.equals(getString(R.string.btn_point)) &&
                textBuffer.contains(getString(R.string.btn_point))) {
            Toast.makeText(MainActivity.this,
                    getString(R.string.error_point_already_exist), Toast.LENGTH_SHORT).show();
        } else {
            textBuffer += curBtn;
        }

        txtOutput.setText(textBuffer);
    }

    public void changeMode() {
        CharSequence curTxtInOutput;

        switch (curCalcView) {
            case MAIN_VIEW:
                curCalcView = CalcView.ENGINEER_VIEW;
                mainView.setVisibility(View.GONE);
                engineeringView.setVisibility(View.VISIBLE);

                curTxtInOutput = txtOutput.getText();
                txtOutput = findViewById(R.id.txtOutputEnView);
                txtOutput.setText(curTxtInOutput);
                break;

            case ENGINEER_VIEW:
                curCalcView = CalcView.MAIN_VIEW;
                engineeringView.setVisibility(View.GONE);
                mainView.setVisibility(View.VISIBLE);
                curTxtInOutput = txtOutput.getText();
                txtOutput = findViewById(R.id.txtOutput);
                txtOutput.setText(curTxtInOutput);
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_change_mode:
                changeMode();
                return true;
            case R.id.settings:
                Intent intent = new Intent(this, SettingsActivity.class);
                startActivity(intent);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
