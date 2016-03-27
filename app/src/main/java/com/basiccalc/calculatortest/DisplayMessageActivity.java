package com.basiccalc.calculatortest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import java.util.ArrayList;

public class DisplayMessageActivity extends AppCompatActivity {
    private TextView totalResult, monthlyResult, message, payoffMonth, payoffYear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //new activity Display result on the next page
        super.onCreate(savedInstanceState);

        try {
            setContentView(R.layout.activity_display_message);

            Bundle bundle = getIntent().getExtras();

            ArrayList<String> stuff = bundle.getStringArrayList("result");
            monthlyResult = (TextView) findViewById(R.id.MonthlyResult);
            totalResult = (TextView) findViewById(R.id.totalMorgage);
            payoffMonth = (TextView) findViewById(R.id.PayOffMonth);
            payoffYear = (TextView) findViewById(R.id.PayOffYear);

            message = (TextView) findViewById(R.id.msg);

            monthlyResult.setText(stuff.get(0));
            totalResult.setText(stuff.get(1));
            message.setText(stuff.get(2));
            payoffMonth.setText(stuff.get(3));
            payoffYear.setText(stuff.get(4));
        } catch (Exception e) {
            Log.e("MyApp", "Exception", e);
        }
    }

}
