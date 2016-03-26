package com.basiccalc.calculatortest;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

import Util.Calculation;

public class DisplayMessageActivity extends AppCompatActivity {
    private TextView totalResult, monthlyResult, message;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);

        Bundle bundle = getIntent().getExtras();
        ArrayList<String> stuff = bundle.getStringArrayList("result");
        monthlyResult = (TextView)findViewById(R.id.MonthlyResult);
        totalResult = (TextView)findViewById(R.id.totalMorgage);
        message = (TextView)findViewById(R.id.msg);


        monthlyResult.setText(stuff.get(0));
        totalResult.setText(stuff.get(1));
        message.setText(stuff.get(2));
    }

}
