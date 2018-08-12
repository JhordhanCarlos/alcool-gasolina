package com.example.lesimoes.flexcal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.i("Extra", "onCreate");

    }


    @Override
    protected void onResume() {
        super.onResume();

        Log.i("Extra", "onResume");

    }

    @Override
    protected void onPause() {
        super.onPause();

        Log.i("Extra", "onPause");

    }

    @Override
    protected void onStop() {
        super.onStop();

        Log.i("Extra", "onStop");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        Log.i("Extra", "onDestroy");

    }

}
