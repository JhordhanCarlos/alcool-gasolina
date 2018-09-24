package com.example.lesimoes.flexcal;



import java.text.ParseException;

import faranjit.currency.edittext.CurrencyEditText;

public abstract class Calculate  {


    public static String getBetterFuelCurrencyEditText(CurrencyEditText mEditAlcool, CurrencyEditText mEditGasolina) {

        try {
            return Calculate.getBetterFuel(mEditAlcool.getCurrencyDouble(), mEditAlcool.getCurrencyDouble());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return "err";
    }

    public static String getBetterFuel(double alcoolValue, double gasolinaValue) {

        if(alcoolValue * 0.7 >= gasolinaValue) return "Melhor opção: Álcool";

        return "Melhor opção: Gasolina";
    }

}
