package com.example.weatherapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;


public class OptionsMenuActivity extends AppCompatActivity {
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbal_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.currentWeather) {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        } else if (item.getItemId() == R.id.localWeather) {
            Intent intent = new Intent(getApplicationContext(), LocalWeatherForecastActivity.class);
            startActivity(intent);
        } else if (item.getItemId() == R.id.nineDayWeatherForecast) {
            Intent intent = new Intent(getApplicationContext(), NineDayWeatherForecastActivity.class);
            startActivity(intent);
        } else if (item.getItemId() == R.id.currentweatherReport) {
            Intent intent = new Intent(getApplicationContext(), CurrentWeatherReportActivity.class);
            startActivity(intent);
        } else if (item.getItemId() == R.id.temperatureConverter) {
            Intent intent = new Intent(getApplicationContext(), TemperatureConverterActivity.class);
            startActivity(intent);
        } else if (item.getItemId() == R.id.weatherDisasterWarning) {
            Intent intent = new Intent(getApplicationContext(), weatherDisasterWarningActivity.class);
            startActivity(intent);
        }
        return true;
    }
}
