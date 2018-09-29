package com.example.lesimoes.flexcal;



import android.util.Log;

import java.text.ParseException;

import faranjit.currency.edittext.CurrencyEditText;

public abstract class Calculate  {


    public static String getBetterFuelCurrencyEditText(CurrencyEditText mEditAlcool, CurrencyEditText mEditGasolina) {

        try {
            return Calculate.getBetterFuel(mEditAlcool.getCurrencyDouble(), mEditGasolina.getCurrencyDouble());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return "err";
    }

    public static String getBetterFuel(double alcoolValue, double gasolinaValue) {

        if(alcoolValue < gasolinaValue * 0.7) return "Melhor opção: Álcool";

        return "Melhor opção: Gasolina";
    }

}
