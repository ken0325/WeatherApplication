package com.example.weatherapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class LocalWeatherForecastActivity extends OptionsMenuActivity {
    private TextView generalSituationTextView, forecastPeriodTextView, forecastDescTextView, outlookTextView;
    private ImageButton refreshBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_weather_forecast);

        Toolbar toolbar = findViewById(R.id.mytoolbar);
        toolbar.setSubtitle(R.string.localWeatherForecast);
        setSupportActionBar(toolbar);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        generalSituationTextView = findViewById(R.id.generalSituationTextView);
        forecastPeriodTextView = findViewById(R.id.forecastPeriodTextView);
        forecastDescTextView = findViewById(R.id.forecastDescTextView);
        outlookTextView = findViewById(R.id.outlookTextView);
        refreshBtn = findViewById(R.id.refreshBtn);

        startAPI();
        setData();

        refreshBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "data is refresh", Toast.LENGTH_LONG).show();
                startAPI();
                setData();
            }
        });
    }

    public void startAPI() {
        ApiLocalWeatherForecast apiController = new ApiLocalWeatherForecast();
        apiController.start();
        try {
            apiController.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void setData() {
        generalSituationTextView.setText(ApiLocalWeatherForecast.localWeatherForecast.getGeneralSituation());
        forecastPeriodTextView.setText(ApiLocalWeatherForecast.localWeatherForecast.getForecastPeriod());
        forecastDescTextView.setText(ApiLocalWeatherForecast.localWeatherForecast.getForecastDesc());
        outlookTextView.setText(ApiLocalWeatherForecast.localWeatherForecast.getOutlook());
    }
}
