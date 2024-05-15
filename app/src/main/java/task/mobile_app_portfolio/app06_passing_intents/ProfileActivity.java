package task.mobile_app_portfolio.app06_passing_intents;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.content.ContextCompat;

import java.util.Objects;

import task.mobile_app_portfolio.R;

public class ProfileActivity extends AppCompatActivity {
    /* UI COMPONENTS */
    private TextView profileFirstname;
    private TextView profileLastname;
    private TextView profileGender;
    private TextView profileBirthdate;
    private TextView profileEmail;
    private TextView profileMobile;
    private TextView profileId;
    private TextView profileCourse;
    private TextView profileYear;
    private TextView profileUnits;
    private TextView profileGpa;

    /* ON CREATE METHODS */
    private void initializeWindow() {
        int dark_blue = ContextCompat.getColor(this, R.color.intents_dark_blue);
        getWindow().setNavigationBarColor(dark_blue);
        getWindow().setStatusBarColor(dark_blue);

        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
    }

    private void initializeTextViews(Intent intent) {
        profileFirstname = findViewById(R.id.profile_firstname);
        profileLastname = findViewById(R.id.profile_lastname);
        profileGender = findViewById(R.id.profile_gender);
        profileBirthdate = findViewById(R.id.profile_birthdate);
        profileEmail = findViewById(R.id.profile_email);
        profileMobile = findViewById(R.id.profile_mobile);
        profileId = findViewById(R.id.profile_id);
        profileCourse = findViewById(R.id.profile_course);
        profileYear = findViewById(R.id.profile_year);
        profileUnits = findViewById(R.id.profile_units);
        profileGpa = findViewById(R.id.profile_gpa);

        // get the intent data
        String firstname = intent.getStringExtra("firstname");
        String lastname = intent.getStringExtra("lastname");
        String gender = intent.getStringExtra("gender");
        String birthdate = intent.getStringExtra("birthdate");
        String email = intent.getStringExtra("email");
        String mobile = intent.getStringExtra("mobile");
        String id = intent.getStringExtra("id");
        String course = intent.getStringExtra("course");
        String yearLevel = intent.getStringExtra("yearLevel");
        String units = intent.getStringExtra("units");
        String gpa = intent.getStringExtra("gpa");

        // set the text of each TextView with the corresponding data
        profileFirstname.setText(firstname);
        profileLastname.setText(lastname);
        profileGender.setText(gender);
        profileBirthdate.setText(birthdate);
        profileEmail.setText(email);
        profileMobile.setText(mobile);
        profileId.setText(id);
        profileCourse.setText(course);
        profileYear.setText(yearLevel);
        profileUnits.setText(units);
        profileGpa.setText(gpa);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // setup the navigation bar and status bar
        this.initializeWindow();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.intents_profile);

        // setup return
        AppCompatButton buttonReturn = (AppCompatButton) findViewById(R.id.button_return);
        buttonReturn.setOnClickListener(view -> openMain());

        // retrieve data from the intent
        Intent intent = getIntent();
        initializeTextViews(intent);
    }

    /* METHODS */
    public void openMain() {
        finish();
    }

}