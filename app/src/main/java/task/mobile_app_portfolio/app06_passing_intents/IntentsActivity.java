package task.mobile_app_portfolio.app06_passing_intents;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import task.mobile_app_portfolio.R;

public class IntentsActivity extends AppCompatActivity {
    /* UI COMPONENTS */
    private EditText fieldFirstname;
    private EditText fieldLastname;
    private RadioGroup fieldGender;
    private RadioButton fieldGenderMale, fieldGenderFemale, fieldGenderOthers;
    private EditText fieldBirthdate;
    private EditText fieldEmail;
    private EditText fieldMobile;
    private EditText fieldId;
    private EditText fieldCourse;
    private EditText fieldYearLevel;
    private EditText fieldUnits;
    private EditText fieldGpa;


    /* ON CREATE */
    private void initializeWindow() {
        int dark_blue = ContextCompat.getColor(this, R.color.intents_dark_blue);
        getWindow().setNavigationBarColor(dark_blue);
        getWindow().setStatusBarColor(dark_blue);

        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
    }

    private void initializeFields() {
        this.fieldFirstname = findViewById(R.id.field_firstname);
        this.fieldLastname = findViewById(R.id.field_lastname);
        this.fieldBirthdate = findViewById(R.id.field_birthdate);

        this.fieldGender = findViewById(R.id.field_gender);
        this.fieldGenderMale = findViewById(R.id.field_gender_male);
        this.fieldGenderFemale = findViewById(R.id.field_gender_female);
        this.fieldGenderOthers = findViewById(R.id.field_gender_others);

        this.fieldEmail = findViewById(R.id.field_email);
        this.fieldMobile = findViewById(R.id.field_mobile);

        this.fieldId= findViewById(R.id.field_id);
        this.fieldCourse = findViewById(R.id.field_course);
        this.fieldYearLevel = findViewById(R.id.field_year_level);
        this.fieldUnits = findViewById(R.id.field_units);
        this.fieldGpa = findViewById(R.id.field_gpa);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // setup the navigation bar and status bar
        this.initializeWindow();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.intents_main);

        // initialize fields
        initializeFields();

        // setup return
        AppCompatButton buttonReturn = findViewById(R.id.button_submit);
        buttonReturn.setOnClickListener(view -> openProfile());

        // setup clear
        AppCompatButton buttonClear = findViewById(R.id.button_clear);
        buttonClear.setOnClickListener(view -> clearForm());
    }


    /* METHODS */
    public void openProfile() {
        // get strings of UI COMPONENTS
        String firstname = fieldFirstname.getText().toString();
        String lastname = fieldLastname.getText().toString();
        String birthdate = fieldBirthdate.getText().toString();
        String email = fieldEmail.getText().toString();
        String mobile = fieldMobile.getText().toString();
        String id = fieldId.getText().toString();
        String course = fieldCourse.getText().toString();
        String yearLevel = fieldYearLevel.getText().toString();
        String units = fieldUnits.getText().toString();
        String gpa = fieldGpa.getText().toString();

        // Get the selected gender
        int selectedGenderId = fieldGender.getCheckedRadioButtonId();
        String gender = "";
        if (selectedGenderId != -1) {
            RadioButton selectedGenderRadioButton = findViewById(selectedGenderId);
            gender = selectedGenderRadioButton.getText().toString();
        }

        // Create an Intent to start ProfileActivity
        Intent intent = new Intent(this, ProfileActivity.class);

        // Put extras into the Intent
        intent.putExtra("firstname", firstname);
        intent.putExtra("lastname", lastname);
        intent.putExtra("gender", gender);
        intent.putExtra("birthdate", birthdate);
        intent.putExtra("email", email);
        intent.putExtra("mobile", mobile);
        intent.putExtra("id", id);
        intent.putExtra("course", course);
        intent.putExtra("yearLevel", yearLevel);
        intent.putExtra("units", units);
        intent.putExtra("gpa", gpa);

        // Start the ProfileActivity
        startActivity(intent);
    }

    public void clearForm() {
        // Clear the inputs of UI COMPONENTS
        fieldFirstname.setText("");
        fieldLastname.setText("");
        fieldBirthdate.setText("");
        fieldEmail.setText("");
        fieldMobile.setText("");
        fieldId.setText("");
        fieldCourse.setText("");
        fieldYearLevel.setText("");
        fieldUnits.setText("");
        fieldGpa.setText("");

        // Clear the RadioGroup selection
        fieldGender.clearCheck();
    }

}