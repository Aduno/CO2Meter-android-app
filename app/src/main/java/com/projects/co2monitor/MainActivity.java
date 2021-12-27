
package com.projects.co2monitor;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private MeterView meterView;
    private Button update_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        meterView = findViewById(R.id.meterView);
        update_btn = findViewById(R.id.button);

        update_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                APICaller.getInformation(new PPMCallback() {
                    @Override
                    public void onSuccess(int ppm) {
                        meterView.updateMeter(ppm);
                    }
                });
            }
        });
    }
}