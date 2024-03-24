package com.example.weatherapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import java.time.LocalTime;

public class TemperatureConverterActivity extends OptionsMenuActivity {
    private TextView resultTextView;
    private EditText inputText;
    private Button button;
    private RadioButton celsius;
    private ImageButton refreshBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temperature_converter);

        Toolbar toolbar = findViewById(R.id.mytoolbar);
        toolbar.setSubtitle(R.string.temperatureConverter);
        setSupportActionBar(toolbar);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        resultTextView = findViewById(R.id.result);
        inputText = findViewById(R.id.input);
        button = findViewById(R.id.convertbutton);
        celsius = findViewById(R.id.celsius);
        refreshBtn = findViewById(R.id.refreshBtn);

        refreshBtn.setEnabled(false);
        refreshBtn.setBackgroundResource(0);


        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String input = inputText.getText().toString();
                if (input.length() == 0) {
                    Toast.makeText(TemperatureConverterActivity.this, "Please enter a valid number", Toast.LENGTH_LONG).show();
                    return;
                }
                double temperature = Double.parseDouble(input);
                boolean toCelsius = celsius.isChecked();
                double convertedTemperature;
                if (toCelsius) {
                    convertedTemperature = ConverterUtil.convertFahrenheitToCelsius(temperature);
                } else {
                    convertedTemperature = ConverterUtil.convertCelsiusToFahrenheit(temperature);
                }
                resultTextView.setText(String.valueOf(convertedTemperature));
            }
        });
    }
}
