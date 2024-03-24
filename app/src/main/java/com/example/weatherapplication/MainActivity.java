package com.example.weatherapplication;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Looper;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONException;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Locale;


public class MainActivity extends OptionsMenuActivity {
    private String TAG = "MainActivity";
    private TextView chineseDayTextView, ToDayTextView, defaultLocationTextView, currentTempTextView, currentTempUnitsTextView, currentMaxTempTextView, currentMaxTempUnitsTextView, currentMinTempTextView, currentMinTempUnitsTextView, humidityTextView, humidityUnitsTextView, windSpeedTextView, windSpeedUnitsTextView, windDirectionTextView, windDirectionUnitsTextView, sunsetTimeTextView, sunriseTimeTextView, updateTimeTextView;
    private ImageButton refreshBtn;
    FusedLocationProviderClient mFusedLocationClient;
    int PERMISSION_ID = 44;
    public static String lat, lon;

    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm");
    LocalTime time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


       time = LocalTime.now();

        Toolbar toolbar = findViewById(R.id.mytoolbar);
        toolbar.setSubtitle(R.string.currentweather);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        chineseDayTextView = findViewById(R.id.chineseDay);
        ToDayTextView = findViewById(R.id.toDayDate);
        defaultLocationTextView = findViewById(R.id.defaultLocation);
        currentTempTextView = findViewById(R.id.currentTemp);
        currentTempUnitsTextView = findViewById(R.id.currentTempUnits);
        currentMaxTempTextView = findViewById(R.id.currentMaxTemp);
        currentMaxTempUnitsTextView = findViewById(R.id.currentMaxTempUnits);
        currentMinTempTextView = findViewById(R.id.currentMinTemp);
        currentMinTempUnitsTextView = findViewById(R.id.currentMinTempUnits);
        humidityTextView = findViewById(R.id.humidity);
        humidityUnitsTextView = findViewById(R.id.humidityUnits);
        windSpeedTextView = findViewById(R.id.windSpeed);
        windSpeedUnitsTextView = findViewById(R.id.windSpeedUnits);
        windDirectionTextView = findViewById(R.id.windDirection);
        windDirectionUnitsTextView = findViewById(R.id.windDirectionUnits);
        sunsetTimeTextView = findViewById(R.id.sunsetTime);
        sunriseTimeTextView = findViewById(R.id.sunriseTime);
        refreshBtn = findViewById(R.id.refreshBtn);
        updateTimeTextView = findViewById(R.id.updateTime);


        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        getLastLocation();
        startAPI();
        setData();


        refreshBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                time = LocalTime.now();
                Toast.makeText(getApplicationContext(), "data is updated", Toast.LENGTH_LONG).show();
                startAPI();
                setData();
            }
        });
    }

    public void startAPI() {
        ApiController apiController = new ApiController();
        apiController.start();
        try {
            apiController.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void setData() {
        chineseDayTextView.setText(ApiController.chineseDayInfo.getLunarYear() + ApiController.chineseDayInfo.getLunarDate());

        SimpleDateFormat df = new SimpleDateFormat("yyyy年MM月dd日", Locale.CHINA);
        SimpleDateFormat enFormatter = new SimpleDateFormat("dd MMM yyyy", Locale.ENGLISH);
        if (Locale.getDefault().getCountry().equals("CN") || Locale.getDefault().getCountry().equals("HK")) {
            ToDayTextView.setText(df.format(Calendar.getInstance().getTime()));
        } else {
            ToDayTextView.setText(enFormatter.format(Calendar.getInstance().getTime()));
        }

        try {
            currentTempTextView.setText(String.valueOf(ApiController.currentWeather.getCurrent().getDouble("temperature_2m")));
            currentTempUnitsTextView.setText(ApiController.currentWeather.getCurrent_units().getString("temperature_2m"));
            currentMaxTempTextView.setText(String.valueOf(ApiController.currentWeather.getDaily().getJSONArray("temperature_2m_max").getInt(0)));
            currentMaxTempUnitsTextView.setText(ApiController.currentWeather.getDaily_units().getString("temperature_2m_max"));
            currentMinTempTextView.setText(String.valueOf(ApiController.currentWeather.getDaily().getJSONArray("temperature_2m_min").getInt(0)));
            currentMinTempUnitsTextView.setText(ApiController.currentWeather.getDaily_units().getString("temperature_2m_min"));
            humidityTextView.setText(String.valueOf(ApiController.currentWeather.getCurrent().getDouble("relative_humidity_2m")));
            humidityUnitsTextView.setText(ApiController.currentWeather.getCurrent_units().getString("relative_humidity_2m"));
            windSpeedTextView.setText(String.valueOf(ApiController.currentWeather.getCurrent().getDouble("wind_speed_10m")));
            windSpeedUnitsTextView.setText(ApiController.currentWeather.getCurrent_units().getString("wind_speed_10m"));
            windDirectionTextView.setText(String.valueOf(ApiController.currentWeather.getCurrent().getDouble("wind_direction_10m")));
            windDirectionUnitsTextView.setText(ApiController.currentWeather.getCurrent_units().getString("wind_direction_10m"));
            DateTimeFormatter parser1 = DateTimeFormatter.ofPattern("HH:mm");
            LocalDateTime sunsetTime = LocalDateTime.parse(ApiController.currentWeather.getDaily().getJSONArray("sunset").getString(0));
            sunsetTimeTextView.setText(parser1.format(sunsetTime));
            LocalDateTime sunriseTime = LocalDateTime.parse(ApiController.currentWeather.getDaily().getJSONArray("sunrise").getString(0));
            sunriseTimeTextView.setText(parser1.format(sunriseTime));
            updateTimeTextView.setText(dtf.format(time));

            Geocoder geocoder;
            if (lat != null) {
                if (ApiController.language.equals("en")) {
                    geocoder = new Geocoder(this, Locale.ENGLISH);
                } else {
                    geocoder = new Geocoder(this, Locale.CHINA);
                }
                defaultLocationTextView.setText(geocoder.getFromLocation(Double.valueOf(lat), Double.valueOf(lon), 1).get(0).getCountryName());
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @SuppressLint("MissingPermission")
    private void getLastLocation() {
        // check if permissions are given
        if (checkPermissions()) {

            // check if location is enabled
            if (isLocationEnabled()) {

                // getting last
                // location from
                // FusedLocationClient
                // object
                mFusedLocationClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
                    @Override
                    public void onComplete(@NonNull Task<Location> task) {
                        Location location = task.getResult();
                        if (location == null) {
                            requestNewLocationData();
                        } else {
                            lat = String.valueOf(location.getLatitude());
                            lon = String.valueOf(location.getLongitude());
                        }
                    }
                });
            } else {
                Toast.makeText(this, "Please turn on" + " your location...", Toast.LENGTH_LONG).show();
//                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
//                startActivity(intent);
            }
        } else {
            // if permissions aren't available,
            // request for permissions
            requestPermissions();
        }
    }

    @SuppressLint("MissingPermission")
    private void requestNewLocationData() {

        // Initializing LocationRequest
        // object with appropriate methods
        LocationRequest mLocationRequest = new LocationRequest();
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        mLocationRequest.setInterval(5);
        mLocationRequest.setFastestInterval(0);
        mLocationRequest.setNumUpdates(1);

        // setting LocationRequest
        // on FusedLocationClient
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        mFusedLocationClient.requestLocationUpdates(mLocationRequest, mLocationCallback, Looper.myLooper());
    }

    private LocationCallback mLocationCallback = new LocationCallback() {

        @Override
        public void onLocationResult(LocationResult locationResult) {
            Location mLastLocation = locationResult.getLastLocation();
            lat = String.valueOf(mLastLocation.getLatitude());
            lon = String.valueOf(mLastLocation.getLongitude());
        }
    };

    // method to check for permissions
    private boolean checkPermissions() {
        return ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED;

        // If we want background location
        // on Android 10.0 and higher,
        // use:
        // ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_BACKGROUND_LOCATION) == PackageManager.PERMISSION_GRANTED
    }

    // method to request for permissions
    private void requestPermissions() {
        ActivityCompat.requestPermissions(this, new String[]{
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSION_ID);
    }

    // method to check
    // if location is enabled
    private boolean isLocationEnabled() {
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
    }

    // If everything is alright then
    @Override
    public void
    onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == PERMISSION_ID) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getLastLocation();
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (checkPermissions()) {
            getLastLocation();
        }
    }
}