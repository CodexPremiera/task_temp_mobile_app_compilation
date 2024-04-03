package task.mobile_app_portfolio.app01_instagram;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

import task.mobile_app_portfolio.R;

public class InstagramActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.instagram);

        // remove the action bar
        Objects.requireNonNull(getSupportActionBar()).hide();
    }
}