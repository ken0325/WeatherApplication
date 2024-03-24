package com.example.weatherapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;

public class CurrentWeatherReportActivity extends OptionsMenuActivity {
    private Spinner selecterHKObs;
    private ImageView currentTempIcon;
    private TextView nowTemp, nowHumidity, uvIndex;
    private ImageButton refreshBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_weather_report);

        selecterHKObs = findViewById(R.id.selecterHKObs);
        currentTempIcon = findViewById(R.id.currentTempIcon);
        nowTemp = findViewById(R.id.nowTemp);
        nowHumidity = findViewById(R.id.nowHumidity);
        uvIndex = findViewById(R.id.uvIndex);
        refreshBtn = findViewById(R.id.refreshBtn);

        Toolbar toolbar = findViewById(R.id.mytoolbar);
        toolbar.setSubtitle(R.string.currentweatherReport);
        setSupportActionBar(toolbar);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        selecterHKObs.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String newPlace = selecterHKObs.getSelectedItem().toString();
                for (int e = 0; e < ModelCurrentWeatherReport.temperature.size(); e++) {
                    ArrayList<Datum> d = ModelCurrentWeatherReport.temperature.get(e).getData();
                    for (int k = 0; k < d.size(); k++) {
                        if (d.get(k).getPlace().equals(newPlace)) {
                            nowTemp.setText(String.valueOf(ModelCurrentWeatherReport.temperature.get(e).getData().get(k).getValue()) + " °" + ModelCurrentWeatherReport.temperature.get(e).getData().get(k).getUnit());
                        }
                    }
                }
            }

            public void onNothingSelected(AdapterView<?> adapterView) {
                return;
            }
        });

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
        ApiCurrentWeatherReport apiController = new ApiCurrentWeatherReport();
        apiController.start();
        try {
            apiController.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void setData() {
        ArrayList<String> obsPlacs = new ArrayList<>();
        for (int i = 0; i < ModelCurrentWeatherReport.temperature.size(); i++) {
            ArrayList<Datum> d = ModelCurrentWeatherReport.temperature.get(i).getData();
            for (int k = 0; k < d.size(); k++) {
                obsPlacs.add(d.get(k).getPlace());
            }
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, obsPlacs);
        selecterHKObs.setAdapter(adapter);

        String mDrawableName = "pic" + ModelCurrentWeatherReport.icon.get(0);
        int resID = getResources().getIdentifier(mDrawableName, "drawable", getPackageName());
        currentTempIcon.setImageResource(resID);

        nowTemp.setText(String.valueOf(ModelCurrentWeatherReport.temperature.get(0).getData().get(0).getValue()) + " °" + ModelCurrentWeatherReport.temperature.get(0).getData().get(0).getUnit());

        nowHumidity.setText(String.valueOf(ModelCurrentWeatherReport.humidity.getData().get(0).getValue()) + "%");

        uvIndex.setText("");
    }
}
