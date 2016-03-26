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
import android.widget.TextView;

import java.util.ArrayList;

import Model.Mortgate;
import Util.Calculation;
import Util.DatabaseHelper;

public class MainActivity extends AppCompatActivity{
    Button button;
    DatabaseHelper myDb;
    private Button btnAdd;
    private TextView monthlyResult, totalResult;
    private EditText Price, Interest, Term, DownPayment, PropertyTax, Insurance;

    ArrayList<String> results = new ArrayList<String>();
    Mortgate mortgate = new Mortgate();

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


        addListenerOnButton();
    }


    private void addListenerOnButton() {
        final Context context = this;

        button = (Button) findViewById(R.id.button1);
        Price = (EditText)findViewById(R.id.Price);
        Interest = (EditText)findViewById(R.id.InterestRate);
        Term = (EditText)findViewById(R.id.Term);
        DownPayment = (EditText)findViewById(R.id.DownPayment);
        PropertyTax = (EditText)findViewById(R.id.PropertyTax);
        Insurance = (EditText)findViewById(R.id.PropertyInsurance);
        monthlyResult = (TextView)findViewById(R.id.MonthlyResult);
        totalResult = (TextView)findViewById(R.id.totalMorgage);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DisplayMessageActivity.class);

                String purchasePrice = Price.getText().toString();
                String InterestRate = Interest.getText().toString();
                String termInYear = Term.getText().toString();
                String DownPaymentString = DownPayment.getText().toString();
                String PropertyTaxString = PropertyTax.getText().toString();
                String InsuranceString = Insurance.getText().toString();

                mortgate.purchasePrice = Double.parseDouble(purchasePrice);
                mortgate.downPayment = Double.parseDouble(DownPaymentString);
                mortgate.term = Integer.parseInt(termInYear);
                mortgate.interestRate = Double.parseDouble(InterestRate);
                mortgate.tax = Double.parseDouble(PropertyTaxString);
                mortgate.insurance =  Double.parseDouble(InsuranceString);


                Calculation cal = new Calculation();
                double mortResultMonth_cal = cal.calculMonthly(mortgate.purchasePrice, mortgate.downPayment, mortgate.term, mortgate.interestRate, mortgate.tax, mortgate.insurance);
                String mortResultMonth = String.valueOf(mortResultMonth_cal);

                double mortResultTotal_cal = cal.calculTotal(mortResultMonth_cal, mortgate.term);
                String mortResultTotal = String.valueOf(mortResultTotal_cal);

                //DB insert
                String isInserted = myDb.insertRecord(mortResultMonth, mortResultTotal);

                results.add(mortResultMonth);
                results.add(mortResultTotal);
                results.add(isInserted);

                Bundle bundle = new Bundle();
                bundle.putStringArrayList("result", results);
                intent.putExtras(bundle);


                startActivity(intent);

            }
        });
    }

}
