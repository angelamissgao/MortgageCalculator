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

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    Button button;

    private Button btnAdd;
    private TextView monthlyResult, totalResult;
    private EditText Price, Interest, Term, DownPayment, PropertyTax, Insurance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        init();

        addListenerOnButton();
    }


    //test
    private void addListenerOnButton() {
        final Context context = this;

        button = (Button) findViewById(R.id.button1);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DisplayMessageActivity.class);

                ArrayList<String> results = new ArrayList<String>();
                results.add("Hello");
                results.add("worlds");

                Bundle bundle = new Bundle();
                bundle.putStringArrayList("result", results);
                intent.putExtras(bundle);

                startActivity(intent);

                String termInYear1 = Term.getText().toString();
                Mortgate mortgate1 = new Mortgate();
                Calculation cal1 = new Calculation();



            }
        });
    }

    private void init() {
        btnAdd = (Button)findViewById(R.id.buttonAdd);
        Price = (EditText)findViewById(R.id.Price);
        Interest = (EditText)findViewById(R.id.InterestRate);
        Term = (EditText)findViewById(R.id.Term);
        DownPayment = (EditText)findViewById(R.id.DownPayment);
        PropertyTax = (EditText)findViewById(R.id.PropertyTax);
        Insurance = (EditText)findViewById(R.id.PropertyInsurance);
        monthlyResult = (TextView)findViewById(R.id.MonthlyResult);
        totalResult = (TextView)findViewById(R.id.totalMorgage);

        btnAdd.setOnClickListener(this);
    };

    @Override
    public void onClick(View v) {

        String purchasePrice = Price.getText().toString();
        String InterestRate = Interest.getText().toString();
        String termInYear = Term.getText().toString();
        String DownPaymentString = DownPayment.getText().toString();
        String PropertyTaxString = PropertyTax.getText().toString();
        String InsuranceString = Insurance.getText().toString();

        Mortgate mortgate = new Mortgate();
        mortgate.purchasePrice = Double.parseDouble(Price.getText().toString());
        mortgate.downPayment = Double.parseDouble(DownPaymentString);
        mortgate.term = Integer.parseInt(termInYear);
        mortgate.interestRate = Double.parseDouble(InterestRate);
        mortgate.tax = Double.parseDouble(PropertyTaxString);
        mortgate.insurance =  Double.parseDouble(InsuranceString);

        Calculation cal = new Calculation();

        switch (v.getId()){
            case R.id.buttonAdd:

                //Monthly calculation
                double mortResultMonth_cal = cal.calculMonthly(mortgate.purchasePrice, mortgate.downPayment, mortgate.term, mortgate.interestRate, mortgate.tax, mortgate.insurance);
                String mortResultMonth = String.valueOf(mortResultMonth_cal);
                monthlyResult.setText(mortResultMonth);

                //totoal calculation
                double mortResultTotal_cal = cal.calculTotal(mortResultMonth_cal, mortgate.term);
                totalResult.setText(String.valueOf(mortResultTotal_cal));
                break;
        }
    }

}
