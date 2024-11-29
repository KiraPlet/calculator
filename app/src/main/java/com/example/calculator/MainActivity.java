package com.example.calculator;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button buttonAdd, buttonSubtract, buttonDivide, buttonMultiply, buttonClean, buttonDegree;
    private TextView operation, result;
    private EditText number1, number2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeViews();
        setButtonListeners();
    }

    private void initializeViews() {
        buttonAdd = findViewById(R.id.buttonAdd);
        buttonSubtract = findViewById(R.id.buttonSubtract);
        buttonDivide = findViewById(R.id.buttonDivide);
        buttonMultiply = findViewById(R.id.buttonMultiply);
        buttonClean = findViewById(R.id.buttonClean);
        buttonDegree = findViewById(R.id.buttonDegree);
        operation = findViewById(R.id.operation);
        number1 = findViewById(R.id.number1);
        number2 = findViewById(R.id.number2);
        result = findViewById(R.id.result);
    }

    private void setButtonListeners() {
        buttonAdd.setOnClickListener(v -> calculate("+"));
        buttonSubtract.setOnClickListener(v -> calculate("-"));
        buttonMultiply.setOnClickListener(v -> calculate("*"));
        buttonDivide.setOnClickListener(v -> calculate("/"));
        buttonDegree.setOnClickListener(v -> calculate("^"));
        buttonClean.setOnClickListener(v -> clearFields());
    }

    private void calculate(String op) {
        if (isInputValid()) {
            double num1 = Double.parseDouble(number1.getText().toString());
            double num2 = Double.parseDouble(number2.getText().toString());
            double res;

            switch (op) {
                case "+":
                    operation.setText("+");
                    res = num1 + num2;
                    break;
                case "-":
                    operation.setText("-");
                    res = num1 - num2;
                    break;
                case "*":
                    operation.setText("*");
                    res = num1 * num2;
                    break;
                case "/":
                    operation.setText("/");
                    if (num2 != 0) {
                        res = num1 / num2;
                    } else {
                        showError("Ошибка: деление на ноль");
                        return;
                    }
                    break;
                case "^":
                    operation.setText("^");
                    res = Math.pow(num1, num2);
                    break;
                default:
                    showError("Ошибка: неверная операция");
                    return;
            }
            result.setText(String.valueOf(res));
        } else {
            showError("Ошибка: введите числа");
        }
    }


    private boolean isInputValid() {
        return !TextUtils.isEmpty(number1.getText()) && !TextUtils.isEmpty(number2.getText());
    }

    private void clearFields() {
        number1.setText("");
        operation.setText("");
        number2.setText("");
        result.setText("");
    }


    private void showError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}