package task.mobile_app_portfolio.app02_settings;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;
import java.util.Random;

import task.mobile_app_portfolio.R;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);

        // remove the action bar
        Objects.requireNonNull(getSupportActionBar()).hide();

        // TODO Close Button
        Button btn_close = (Button) findViewById(R.id.btn_close);
        btn_close.setOnClickListener(view -> close());

        // TODO Toast Button
        Button btn_toast = (Button) findViewById(R.id.btn_toast);
        btn_toast.setOnClickListener(view -> makeToast(getApplicationContext()));

        // TODO Background Button
        Button btn_background = (Button) findViewById(R.id.btn_background);
        RelativeLayout settings_container = (RelativeLayout) findViewById(R.id.settings_container);
        btn_background.setOnClickListener(view -> changeColor(settings_container));

        // TODO Button Color Button
        Button btn_buttonColor = (Button) findViewById(R.id.btn_button);
        btn_buttonColor.setOnClickListener(view -> changeTint(btn_buttonColor));

        // TODO Hide Button Button
        Button btn_hideButton = (Button) findViewById(R.id.btn_hide);
        btn_hideButton.setOnClickListener(view -> hideButton());
    }

    public void close() {
        /*
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        */
        finish();
    }

    public void makeToast(Context context) {
        Toast toast = Toast.makeText(context, "Hello World", Toast.LENGTH_SHORT);
        toast.show();
    }

    private int getRandomColor() {
        Random random = new Random();

        // Generate random RGB values
        int red = random.nextInt(256);
        int green = random.nextInt(256);
        int blue = random.nextInt(256);

        // Create a random color using RGB values
        return Color.rgb(red, green, blue);
    }

    public void changeColor(View view) {
        int randomColor = getRandomColor();
        view.setBackgroundColor(randomColor);
    }

    public void changeTint(View view) {
        int randomColor = getRandomColor();
        view.getBackground().setColorFilter(randomColor, PorterDuff.Mode.SRC_IN);
    }

    public void colorButton() {
        Button btn_buttonColor = findViewById(R.id.btn_button);
        Random random = new Random();

        // Generate random RGB values
        int red = random.nextInt(256);
        int green = random.nextInt(256);
        int blue = random.nextInt(256);

        // Create a random color using RGB values
        int randomColor = Color.rgb(red, green, blue);

        // Set the background color of the button to the random color
        btn_buttonColor.setBackgroundColor(randomColor);
    }


    public void hideButton() {
        Button btn_hideButton = findViewById(R.id.btn_hide);
        btn_hideButton.setVisibility(View.GONE);
    }

}