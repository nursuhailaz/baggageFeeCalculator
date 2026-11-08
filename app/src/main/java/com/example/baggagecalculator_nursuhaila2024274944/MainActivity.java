package com.example.baggagecalculator_nursuhaila2024274944;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnCalculate, btnReset;
    RadioButton btnEconomy, btnBusiness;
    RadioGroup radioGroupClass;
    EditText number1, number2;
    TextView tvResult;
    double num1, num2, result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        toolbar.setTitle("Baggage Fee Estimator");
        toolbar.setTitleTextColor(android.graphics.Color.WHITE);

        btnCalculate = findViewById(R.id.btnCalculate);
        btnReset = findViewById(R.id.btnReset);

        btnEconomy = findViewById(R.id.btnEconomy);
        btnBusiness = findViewById(R.id.btnBusiness);
        radioGroupClass = findViewById(R.id.radioGroupClass);

        number1 = findViewById(R.id.number1);
        number2 = findViewById(R.id.number2);

        tvResult = findViewById(R.id.tvResult);

        // Button listeners
        btnCalculate.setOnClickListener(this);
        btnReset.setOnClickListener(this);
    }

    // Toolbar menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    // Toolbar menu actions
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.action_share) {

            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("text/plain");

            shareIntent.putExtra(
                    Intent.EXTRA_TEXT,
                    "Check out my Baggage Fee Estimator!\n"
                            + "https://github.com/nursuhailaz/baggageFeeCalculator"
            );

            startActivity(Intent.createChooser(shareIntent, "Share using"));

            return true;

        } else if (item.getItemId() == R.id.action_info) {

            Intent intent = new Intent(MainActivity.this, AboutActivity.class);
            startActivity(intent);

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    // Convert EditText input into double
    public double getDoubleFromEditText(EditText editText) {
        return Double.parseDouble(editText.getText().toString().trim());
    }

    @Override
    public void onClick(View view) {

        // Calculate button
        if (view.getId() == R.id.btnCalculate) {

            try {

                // Check empty fields
                if (number1.getText().toString().trim().isEmpty() ||
                        number2.getText().toString().trim().isEmpty()) {

                    Toast.makeText(this, "Please fill in all fields",
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                // Get input values
                num1 = getDoubleFromEditText(number1);
                num2 = getDoubleFromEditText(number2);

                // Check ticket class
                double baggageLimit;

                if (btnEconomy.isChecked()) {
                    baggageLimit = 20;

                } else if (btnBusiness.isChecked()) {
                    baggageLimit = 40;

                } else {
                    Toast.makeText(this, "Please select ticket class",
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                // Validate input
                if (num1 < 0 || num2 < 0 ||
                        Double.isInfinite(num1) || Double.isInfinite(num2) ||
                        Double.isNaN(num1) || Double.isNaN(num2)) {

                    Toast.makeText(this, "Values cannot be negative or invalid",
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                // Calculate excess weight
                double excessWeight = Math.max(0, num1 - baggageLimit);

                // Calculate excess charge
                double excessCharge = excessWeight * num2;

                // Calculate service tax
                double tax = excessCharge * 0.08;

                // Calculate total payable
                result = excessCharge + tax;

                // Display result
                tvResult.setText(
                        "Excess Weight: " +
                                String.format("%.2f", excessWeight) + " kg\n\n" +

                                "Excess Charge: RM " +
                                String.format("%.2f", excessCharge) + "\n" +

                                "Service Tax (8%): RM " +
                                String.format("%.2f", tax) + "\n\n" +

                                "Total Amount: RM " +
                                String.format("%.2f", result)
                );

            } catch (NumberFormatException e) {

                Toast.makeText(this, "Please enter valid values",
                        Toast.LENGTH_SHORT).show();
            }
        }

        // Reset button
        else if (view.getId() == R.id.btnReset) {

            number1.setText("");
            number2.setText("");

            radioGroupClass.clearCheck();

            tvResult.setText("Your calculation results will appear here.");
        }
    }
}
