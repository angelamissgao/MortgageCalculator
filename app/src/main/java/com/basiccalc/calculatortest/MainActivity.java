package com.basiccalc.calculatortest;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import Model.Mortgate;
import Util.Calculation;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private Button btnAdd;
    private TextView result;
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
    }

    private void init() {
        btnAdd = (Button)findViewById(R.id.buttonAdd);
        Price = (EditText)findViewById(R.id.Price);
        Interest = (EditText)findViewById(R.id.InterestRate);
        Term = (EditText)findViewById(R.id.Term);
        DownPayment = (EditText)findViewById(R.id.DownPayment);
        PropertyTax = (EditText)findViewById(R.id.PropertyTax);
        Insurance = (EditText)findViewById(R.id.PropertyInsurance);
        result = (TextView)findViewById(R.id.result);

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
//                double downPayment = Double.parseDouble(DownPaymentString) / 100;
//                double loanPrice = Double.parseDouble(purchasePrice) * (1 - downPayment);
//                double propertyTaxInMonth = Double.parseDouble(PropertyTaxString) / 12;
//                double insuranceInMonth = Double.parseDouble(InsuranceString) / 12;
//
//                double monthlyRate = Double.parseDouble(InterestRate) /100 / 12;
//                int termInMonth = Integer.parseInt(termInYear) * 12;
//
//                double mortResult = (loanPrice * monthlyRate) /
//                        (1-Math.pow(1+monthlyRate, -termInMonth));
//                mortResult += propertyTaxInMonth;
//                mortResult += insuranceInMonth;

                double mortResult_cal = cal.calculation(mortgate.purchasePrice, mortgate.downPayment, mortgate.term, mortgate.interestRate, mortgate.tax, mortgate.insurance);

                result.setText(String.valueOf(mortResult_cal));
                break;
        }
    }


//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }
}