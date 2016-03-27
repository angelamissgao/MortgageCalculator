package com.basiccalc.calculatortest;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
//import android.widget.TextView;

import java.util.ArrayList;

import Model.Mortgage;
import Util.DatabaseHelper;

public class MainActivity extends AppCompatActivity {
    /*
        Main activity for gathering user input data;
     */
    Button button;
    DatabaseHelper myDb;
    private EditText Price, Interest, Term, DownPayment, PropertyTax, Insurance, FirstMonth, FirstYear;
    ArrayList<String> results = new ArrayList<String>();
    Mortgage mortgage = new Mortgage();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        myDb = new DatabaseHelper(this);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        // execute when click the calculate button;
        addListenerOnButton();
    }


    private void addListenerOnButton() {
        final Context context = this;

        button = (Button) findViewById(R.id.button1);
        Price = (EditText) findViewById(R.id.Price);
        Interest = (EditText) findViewById(R.id.InterestRate);
        Term = (EditText) findViewById(R.id.Term);
        DownPayment = (EditText) findViewById(R.id.DownPayment);
        PropertyTax = (EditText) findViewById(R.id.PropertyTax);
        Insurance = (EditText) findViewById(R.id.PropertyInsurance);
        FirstMonth = (EditText) findViewById(R.id.Month);
        FirstYear = (EditText) findViewById(R.id.Year);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DisplayMessageActivity.class);

                try {
                    mortgage.purchasePrice = Double.parseDouble(Price.getText().toString());
                    mortgage.downPayment = Double.parseDouble(DownPayment.getText().toString());
                    mortgage.term = Integer.parseInt(Term.getText().toString());
                    mortgage.interestRate = Double.parseDouble(Interest.getText().toString());
                    mortgage.tax = Double.parseDouble(PropertyTax.getText().toString());
                    mortgage.insurance = Double.parseDouble(Insurance.getText().toString());
                    mortgage.firstPaymentMonth = Integer.parseInt(FirstMonth.getText().toString());
                    mortgage.firstPaymentYear = Integer.parseInt(FirstYear.getText().toString());

                    //Calculation model
                    double mortResultMonth_cal = mortgage.getMonthlyMortgate();
                    String mortResultMonth = String.valueOf(mortResultMonth_cal);

                    double mortResultTotal_cal = mortgage.getTotalMortgate();
                    String mortResultTotal = String.valueOf(mortResultTotal_cal);

                    int[] payoffDate = mortgage.getPayoffDate();
                    String payoffMonth = String.valueOf(payoffDate[0]);
                    String payoffYear = String.valueOf(payoffDate[1]);

                    //DB insert
                    String isInserted = myDb.insertRecord(mortgage);
                    results.add(mortResultMonth);
                    results.add(mortResultTotal);
                    results.add(isInserted);
                    results.add(payoffMonth);
                    results.add(payoffYear);

                    // bundle data to the next activity
                    Bundle bundle = new Bundle();
                    bundle.putStringArrayList("result", results);
                    intent.putExtras(bundle);

                    startActivity(intent);
                } catch (Exception e){
                    Log.e("MyApp", "Exception", e);
                }

            }
        });
    }

}
