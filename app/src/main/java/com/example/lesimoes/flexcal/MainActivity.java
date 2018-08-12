package com.example.lesimoes.flexcal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private EditText mEditAlcool;
    private EditText mEditGasolina;
    private Button mBtnCalculate;
    private TextView mTextResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        mEditAlcool = findViewById(R.id.editAlcool);
        mEditGasolina = findViewById(R.id.editGasolina);
        mBtnCalculate = findViewById(R.id.btnCalc);
        mTextResult = findViewById(R.id.textResult);

        mBtnCalculate.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        int id = view.getId();


        if(id == R.id.btnCalc)
            calculate(Double.valueOf(mEditAlcool.getText().toString()),
                Double.valueOf(mEditGasolina.getText().toString()));


    }


    private void calculate(double alcoolValue, double gasolinaValue) {
        if(alcoolValue * 0.7 >= gasolinaValue) {
            mTextResult.setText("Álcool");
        } else {
            mTextResult.setText("Gasolina");
        }

    }


}
