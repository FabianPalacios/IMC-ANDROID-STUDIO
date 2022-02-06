package com.fabianpalacios.imc;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText lblWeightValue = null;
    private EditText lblHeightValue = null;


    private TextView lblIMCValue = null;
    private TextView lblDescription = null;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
    }

    public  void initViews(){
        lblWeightValue = findViewById(R.id.lblWeightValue);
        lblHeightValue = findViewById(R.id.lblHeightValue);


        lblIMCValue = findViewById(R.id.lblIMCValue);
        lblDescription = findViewById(R.id.lblDescription);
    }

    //Method to make the BMI, with its respective exception
    public void calculate(View view){

        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        StringBuilder text = new StringBuilder();

        if(lblWeightValue.getText().toString().isEmpty() && lblHeightValue.getText().toString().isEmpty()){

            text.append(getString(R.string.Empty_fields));
            alert.setMessage(text);
            alert.setPositiveButton("close", null);

            alert.show();

        }else{

            double weight = Double.parseDouble(lblWeightValue.getText().toString());
            double height= Double.parseDouble(lblHeightValue.getText().toString());

            if(weight <= 0  || height <= 0){
                text.append(getString(R.string.zero_values));
                alert.setMessage(text);
                alert.setPositiveButton("cerrar", null);

                alert.show();
            }else{
                double resultBMI = weight/(height*height);
                lblIMCValue.setText(getString(R.string.bmi)+" "+String.format("%.2f",resultBMI));
                description(resultBMI);
            }



        }

    }

    //Method that describes the result obtained from the bmi calculation
    public  void description(double resultBMI){
        if(resultBMI > 0 && resultBMI <18.5)
            lblDescription.setText(getString(R.string.description)+" "+getString(R.string.under_weight));
        else
        if(resultBMI>= 18.5 && resultBMI <= 24.9)
            lblDescription.setText(getString(R.string.description)+" "+getString(R.string.normal_weight));
        else
        if(resultBMI>= 25 && resultBMI <= 29.9)
            lblDescription.setText(getString(R.string.description)+" "+getString(R.string.overweight));
        else
        if(resultBMI>= 30 && resultBMI <= 34.9)
            lblDescription.setText(getString(R.string.description)+" "+getString(R.string.type_1_obesity));
        else
        if(resultBMI>= 35 && resultBMI <= 39.9)
            lblDescription.setText(getString(R.string.description)+" "+getString(R.string.type_2_obesity));
        else
        if(resultBMI>= 40)
            lblDescription.setText(getString(R.string.description)+" "+getString(R.string.type_3_obesity));
    }

    //Method to clean the text fields
    public  void clear(View v){
        lblWeightValue.setText("");
        lblHeightValue.setText("");
        lblIMCValue.setText(getString(R.string.bmi));
        lblDescription.setText(getString(R.string.description));
    }
}