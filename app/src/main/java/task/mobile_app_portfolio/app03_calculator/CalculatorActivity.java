package task.mobile_app_portfolio.app03_calculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import task.mobile_app_portfolio.R;
import task.mobile_app_portfolio.app03_calculator.logic.Calculator;
import task.mobile_app_portfolio.app03_calculator.logic.Splitter;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.Objects;

public class CalculatorActivity extends AppCompatActivity implements View.OnClickListener {
    TextView textExpression, textAnswer;
    MaterialButton btnAc, btnPercent, btnBack;
    MaterialButton btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btn0, btn00, btnDot;
    MaterialButton btnPlus, btnMinus, btnTimes, btnDivide, btnEquals;
    MaterialButton btnOpenPar, btnClosePar, btnExp;

    private final Calculator calculator = new Calculator();


    // ON CREATE: Add listener to all the buttons
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calculator);

        // remove the action bar
        Objects.requireNonNull(getSupportActionBar()).hide();

        addListenerToAllButtons();
        textExpression = findViewById(R.id.text_expression);
        textAnswer = findViewById(R.id.text_answer);

        // Change the navigation bar color
        getWindow().setNavigationBarColor(ContextCompat.getColor(this, R.color.background_gray));
    }

    private void addListenerToAllButtons() {
        addListenerToButton(btnAc, R.id.btn_ac);
        addListenerToButton(btnPercent, R.id.btn_percent);
        addListenerToButton(btnBack, R.id.btn_back);

        addListenerToButton(btn1, R.id.btn_1);
        addListenerToButton(btn2, R.id.btn_2);
        addListenerToButton(btn3, R.id.btn_3);
        addListenerToButton(btn4, R.id.btn_4);
        addListenerToButton(btn5, R.id.btn_5);
        addListenerToButton(btn6, R.id.btn_6);
        addListenerToButton(btn7, R.id.btn_7);
        addListenerToButton(btn8, R.id.btn_8);
        addListenerToButton(btn9, R.id.btn_9);

        addListenerToButton(btn00, R.id.btn_00);
        addListenerToButton(btn0, R.id.btn_0);
        addListenerToButton(btnDot, R.id.btn_dot);

        addListenerToButton(btnPlus, R.id.btn_plus);
        addListenerToButton(btnMinus, R.id.btn_minus);
        addListenerToButton(btnTimes, R.id.btn_times);
        addListenerToButton(btnDivide, R.id.btn_divide);
        addListenerToButton(btnEquals, R.id.btn_equals);
    }

    private void addListenerToButton(MaterialButton button, int id) {
        button = findViewById(id);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        // get button and its text
        MaterialButton button =(MaterialButton) view;
        String buttonText = button.getText().toString();

        // get input in the text box
        String rawExpression = textExpression.getText().toString();
        ArrayList<String> termsList = Splitter.getTermsList(rawExpression);
        StringBuilder newExpression = new StringBuilder(rawExpression);

        // get last term if there is any
        String lastTerm = (termsList.size() > 0) ? termsList.get(termsList.size() - 1) : "";
        String secLastTerm = (termsList.size() > 1) ? termsList.get(termsList.size() - 2) : "";
        String sequentialAnswer = "";

        boolean lastTermIsOperator = lastTerm.contains("+") || lastTerm.contains("-") || lastTerm.contains("×") || lastTerm.contains("÷");
        boolean lastTermEqualsZero = calculator.calculate(rawExpression).equals("0");

        // control input
        switch(buttonText) {
            case "%":
                break;
            case "=":
                String answer = calculator.calculate(rawExpression);
                newExpression = new StringBuilder(answer);

                // reset textAnswer and sequential answer
                textAnswer.setText(rawExpression);
                break;

            case "×":
            case "÷":
                if (!rawExpression.isEmpty()) {
                    if (lastTermIsOperator) {
                        newExpression.deleteCharAt(newExpression.length() - 1);
                    }
                    newExpression.append(buttonText);
                }
                break;

            case "+":
            case "-":
                if (lastTermIsOperator) {
                    newExpression.deleteCharAt(newExpression.length() - 1);
                }
                newExpression.append(buttonText);
                break;

            case ".":
                boolean thereIsInput = !termsList.isEmpty();
                boolean lastTermHasDot = lastTerm.contains(".");
                if (!thereIsInput || !lastTermHasDot)
                    newExpression.append(buttonText);
                break;

            case "AC":
                newExpression.delete(0, newExpression.length());
                textAnswer.setText("");
                break;
            case "⌫":
                if (newExpression.length() > 0)
                    newExpression.deleteCharAt(newExpression.length() - 1);

                // if not math error, set sequential answer
                sequentialAnswer = calculator.calculateSequentially(String.valueOf(newExpression));
                if (!sequentialAnswer.equals("Math Error"))
                    textAnswer.setText(sequentialAnswer);

                // if the new expression is empty
                if (newExpression.length() < 1)
                    textAnswer.setText("");

                break;

            case "0":
            case "00":
                String lastTermAns = calculator.calculate(rawExpression);
                if (!lastTermAns.equals("0")) {
                    newExpression.append(buttonText);
                    sequentialAnswer = calculator.calculateSequentially(String.valueOf(newExpression));
                    textAnswer.setText(sequentialAnswer);
                }

                break;

            default:
                if (lastTermEqualsZero) {
                    newExpression.deleteCharAt(newExpression.length() - 1);
                }

                newExpression.append(buttonText);
                sequentialAnswer = calculator.calculateSequentially(String.valueOf(newExpression));
                textAnswer.setText(sequentialAnswer);
                break;
        }

        textExpression.setText(newExpression);
    }

}