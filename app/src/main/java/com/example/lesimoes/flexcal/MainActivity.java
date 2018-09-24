package com.example.lesimoes.flexcal;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import java.text.ParseException;

import faranjit.currency.edittext.CurrencyEditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private CurrencyEditText mEditAlcool;
    private CurrencyEditText mEditGasolina;
    private Button mBtnCalculate;
    private TextView mTextResult;
    private SharedPreferences mPreferences;
    private SharedPreferences.Editor mEditor;
    private CardView mCardResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();

        mBtnCalculate.setOnClickListener(this);


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
    }


}
