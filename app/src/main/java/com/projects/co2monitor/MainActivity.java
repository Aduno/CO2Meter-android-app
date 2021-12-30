
package com.projects.co2monitor;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private MeterView meterView;
    private Button updateBtn;
    private TextView timeText;
    private Spinner sensor_spinner;

    private SensorOptions sensor = SensorOptions.sensor1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Setting up the references
        meterView = findViewById(R.id.meterView);
        updateBtn = findViewById(R.id.button);
        timeText = findViewById(R.id.timeTextView);
        sensor_spinner = findViewById(R.id.sensorSpinner);

        // Setting up listeners
        // Update button - Used to refresh the meter reading on screen
        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Calls the server to get the information
                APICaller.getPPMReading(new PPMCallback() {
                    @Override
                    public void onSuccess(String response) {
                        // Parses the PPM value from the response
                        String ppm = response;
                        String time = response;
                        meterView.updateMeter(ppm);
                        timeText.setText(time);
                    }

                    @Override
                    public void onFailure(){
                        Toast.makeText(MainActivity.this, "Failed to retrieve information", Toast.LENGTH_SHORT).show();
                    }
                },
        MainActivity.this,
                sensor);
            }
        });

        // Setting up sensor selection spinner
        ArrayList<String> list = new ArrayList<>();
        for(SensorOptions i: SensorOptions.values()){
            list.add(i.name());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item,list);
        sensor_spinner.setAdapter(adapter);

        // Setting listener for selection spinner
        sensor_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                // Updates the sensor variable to match the user's selection
                sensor = SensorOptions.getSensor(i+1);
                //If user selects a different sensor, updates the meter with that new sensor data
                APICaller.getPPMReading(new PPMCallback() {
                    @Override
                    public void onSuccess(String response) {
                        //Parse response for ppm and time
                        String ppm = response;
                        String time = response;
                        meterView.updateMeter(ppm);
                        timeText.setText(time);
                    }

                    @Override
                    public void onFailure() {
                        Toast.makeText(MainActivity.this,"Failed to retrieve information", Toast.LENGTH_SHORT).show();
                    }
                },
                MainActivity.this,
                sensor);
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        });
    }
}
