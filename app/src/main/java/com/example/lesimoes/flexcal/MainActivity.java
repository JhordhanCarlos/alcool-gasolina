package com.example.lesimoes.flexcal;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import java.text.ParseException;

import faranjit.currency.edittext.CurrencyEditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private CurrencyEditText mEditAlcool;
    private CurrencyEditText mEditGasolina;
    private Button mBtnCalculate;
    private TextView mTextResult, mValueChange, mChangeGas, mChangeAlcool;
    private SharedPreferences mPreferences;
    private SharedPreferences.Editor mEditor;
    private CardView mCardResult;
    private SeekBar mSeekBar;
    private Toolbar mToolBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
        setSupportActionBar(mToolBar);

        mBtnCalculate.setOnClickListener(this);
        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                mValueChange.setText("R$"+ i*5 + ".00");

                try {
                    double valueGas =  (i * 5) / mEditGasolina.getCurrencyDouble();
                    double valueAlc =  (i * 5) / mEditAlcool.getCurrencyDouble();
                    mChangeGas.setText(String.format("%.2f Litros de Gasolina", valueGas));
                    mChangeAlcool.setText(String.format("%.2f Litros de Alcool", valueAlc));
                } catch (ParseException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onStart() {
        super.onStart();

        mPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mEditor = mPreferences.edit();

        mEditAlcool.setText(mPreferences.getString("priceAlcool", "0.00"));
        mEditGasolina.setText(mPreferences.getString("priceGas", "0.00"));

        mCardResult.setVisibility(View.GONE);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();

        if(id == R.id.btnCalc) {
            try {
                mTextResult.setText(Calculate.getBetterFuelCurrencyEditText(mEditAlcool, mEditGasolina));

                mEditor.putString("priceAlcool",mEditAlcool.getCurrencyText());
                mEditor.putString("priceGas", mEditGasolina.getCurrencyText());

                mEditor.commit();


                double valueGas =  (mSeekBar.getProgress() * 5) / mEditGasolina.getCurrencyDouble();
                double valueAlc =  (mSeekBar.getProgress() * 5) / mEditAlcool.getCurrencyDouble();
                mChangeGas.setText(String.format("%.2f Litros de Gasolina", valueGas));
                mChangeAlcool.setText(String.format("%.2f Litros de Alcool", valueAlc));


                mCardResult.setVisibility(View.VISIBLE);


                YoYo.with(Techniques.SlideInUp)
                    .duration(300)
                    .playOn(findViewById(R.id.card_view));

            } catch (NumberFormatException e) {
                Toast.makeText(getApplicationContext(), R.string.msg_empty_edit, Toast.LENGTH_SHORT).show();
            } catch (ParseException ex) {
                Log.e("Currency", "Currency convert error");
            }
        }

    }


    private void findViews() {
        mEditAlcool = this.findViewById(R.id.editAlcool);
        mEditGasolina = this.findViewById(R.id.editGasolina);
        mBtnCalculate = this.findViewById(R.id.btnCalc);
        mTextResult = this.findViewById(R.id.textResult);
        mCardResult = this.findViewById(R.id.card_view);
        mSeekBar = this.findViewById(R.id.valueSeekBar);
        mSeekBar.setMax(60);
        mValueChange = this.findViewById(R.id.valueChange);
        mValueChange.setText("R$0,00");
        mChangeGas = this.findViewById(R.id.changeGas);
        mChangeAlcool = this.findViewById(R.id.changeAlcool);
        mToolBar = this.findViewById(R.id.my_toolbar);

    }


}
