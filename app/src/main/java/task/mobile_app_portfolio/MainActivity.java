package task.mobile_app_portfolio;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.button.MaterialButton;

import java.util.Objects;

import task.mobile_app_portfolio.app01_instagram.InstagramActivity;
import task.mobile_app_portfolio.app02_settings.SettingsActivity;
import task.mobile_app_portfolio.app03_calculator.CalculatorActivity;
import task.mobile_app_portfolio.app05_connect_three.ConnectThreeActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_home);

        // remove the action bar
        Objects.requireNonNull(getSupportActionBar()).hide();

        MaterialButton btn_instagram = (MaterialButton) findViewById(R.id.btn_instagram);
        btn_instagram.setOnClickListener(view -> openInstagram());

        MaterialButton btn_settings = (MaterialButton) findViewById(R.id.btn_settings);
        btn_settings.setOnClickListener(view -> openSettings());

        MaterialButton btn_calculator = (MaterialButton) findViewById(R.id.btn_calculator);
        btn_calculator.setOnClickListener(view -> openCalculator());

        MaterialButton btn_connect_three = (MaterialButton) findViewById(R.id.btn_connect_three);
        btn_connect_three.setOnClickListener(view -> openConnectThree());
    }

    public void openInstagram() {
        Intent intent = new Intent(this, InstagramActivity.class);
        startActivity(intent);
    }

    public void openSettings() {
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }

    public void openCalculator() {
        Intent intent = new Intent(this, CalculatorActivity.class);
        startActivity(intent);
    }

    public void openConnectThree() {
        Intent intent = new Intent(this, ConnectThreeActivity.class);
        startActivity(intent);
    }
}