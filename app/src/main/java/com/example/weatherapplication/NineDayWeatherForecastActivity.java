package com.example.weatherapplication;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NineDayWeatherForecastActivity extends OptionsMenuActivity {
    private ListView nineDayListView;
    private TextView nineDayGeneralSituationTextView, weatherIconsHyperLink;
    private ImageButton refreshBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nine_day_weather_forecast);

        nineDayListView = findViewById(R.id.nineDayList);
        nineDayGeneralSituationTextView = findViewById(R.id.nineDayGS);
        weatherIconsHyperLink = (TextView) findViewById(R.id.weatherIconsHyperLink);
        refreshBtn = findViewById(R.id.refreshBtn);

        Toolbar toolbar = findViewById(R.id.mytoolbar);
        toolbar.setSubtitle(R.string.nineDayWeatherForecast);
        setSupportActionBar(toolbar);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
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
        ApiNineDayWeatherForecast jsonHandlerThread = new ApiNineDayWeatherForecast();
        jsonHandlerThread.start();
        try {
            jsonHandlerThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void setData() {
//        https://www.hko.gov.hk/textonly/v2/explain/wxicon_c.htm
//        https://www.hko.gov.hk/en/wxinfo/currwx/fnd.htm?tablenote=true
        nineDayGeneralSituationTextView.setText(ApiNineDayWeatherForecast.hkoNineDayWeatherForecast.getGeneralSituation());
        List<Map<String, Object>> items = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < ModelNineDayWeatherForecast.weatherForecastList.size(); i++) {
            Map<String, Object> item = new HashMap<String, Object>();
            String mDrawableName = "pic" + ModelNineDayWeatherForecast.weatherForecastList.get(i).get("forecastIcon");
            int resID = getResources().getIdentifier(mDrawableName, "drawable", getPackageName());
            item.put(ModelNineDayWeatherForecast.FORECASTDATE, ModelNineDayWeatherForecast.weatherForecastList.get(i).get("forecastDate"));
            item.put(ModelNineDayWeatherForecast.WEEK, ModelNineDayWeatherForecast.weatherForecastList.get(i).get("week"));
            item.put(ModelNineDayWeatherForecast.FORECASTWIND, ModelNineDayWeatherForecast.weatherForecastList.get(i).get("forecastWind"));
            item.put(ModelNineDayWeatherForecast.FORECASTWEATHER, ModelNineDayWeatherForecast.weatherForecastList.get(i).get("forecastWeather"));
            item.put(ModelNineDayWeatherForecast.FORECASTMAXMINTEMP, ModelNineDayWeatherForecast.weatherForecastList.get(i).get("forecastMaxMintemp"));
            item.put(ModelNineDayWeatherForecast.FORECASTMAXMINRH, ModelNineDayWeatherForecast.weatherForecastList.get(i).get("forecastMaxMinrh"));
            item.put(ModelNineDayWeatherForecast.FORECASTICON, resID);
            item.put(ModelNineDayWeatherForecast.PSR, ModelNineDayWeatherForecast.weatherForecastList.get(i).get("psr"));
            String mDrawableName2 = ModelNineDayWeatherForecast.weatherForecastList.get(i).get("enPsr");
            String finalmDrawableName2 = null;
            switch (mDrawableName2) {
                case "Low":
                    finalmDrawableName2 = "psr_low_50_light";
                    break;
                case "Medium Low":
                    finalmDrawableName2 = "psr_medium_low_50_light";
                    break;
                case "Medium":
                    finalmDrawableName2 = "psr_medium_50_light";
                    break;
                case "Medium High":
                    finalmDrawableName2 = "psr_medium_high_50_light";
                    break;
                case "High":
                    finalmDrawableName2 = "psr_high_50_light";
                    break;
                default:
                    break;
            }
            int resID2 = getResources().getIdentifier(finalmDrawableName2, "drawable", getPackageName());
            item.put(ModelNineDayWeatherForecast.ENPSR, resID2);
            items.add(item);
        }
        SimpleAdapter adapter = new SimpleAdapter(this,
                items,
                R.layout.nine_day_list_view_layout,
                new String[]{ModelNineDayWeatherForecast.FORECASTDATE, ModelNineDayWeatherForecast.WEEK, ModelNineDayWeatherForecast.FORECASTWIND, ModelNineDayWeatherForecast.FORECASTWEATHER, ModelNineDayWeatherForecast.FORECASTMAXMINTEMP, ModelNineDayWeatherForecast.FORECASTMAXMINRH, ModelNineDayWeatherForecast.FORECASTICON, ModelNineDayWeatherForecast.PSR, ModelNineDayWeatherForecast.ENPSR},
                new int[]{R.id.forecastDate, R.id.week, R.id.forecastWind, R.id.forecastWeather, R.id.forecastMaxMintemp, R.id.forecastMaxMinrh, R.id.forecastIcon, R.id.psr, R.id.enPsr}
        );
        nineDayListView.setAdapter(adapter);
        weatherIconsHyperLink.setClickable(true);
        weatherIconsHyperLink.setMovementMethod(LinkMovementMethod.getInstance());
        if (ApiController.language.equals("sc")) {
            weatherIconsHyperLink.setText(Html.fromHtml("<a href='https://www.hko.gov.hk/textonly/v2/explain/wxicon_sc.htm'> 天气标记 </a>"));
        } else if (ApiController.language.equals("tc")) {
            weatherIconsHyperLink.setText(Html.fromHtml("<a href='https://www.hko.gov.hk/textonly/v2/explain/wxicon_c.htm'> 天氣標記 </a>"));
        } else {
            weatherIconsHyperLink.setText(Html.fromHtml("<a href='https://www.hko.gov.hk/textonly/v2/explain/wxicon_e.htm'> weather Icons </a>"));
        }
    }
}
