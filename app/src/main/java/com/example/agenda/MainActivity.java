    package com.example.agenda;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

    public class MainActivity extends AppCompatActivity {

    private Button bClear;
    private Button bGuardar;
    private Button bGet;
    private EditText etNom;
    private EditText etTlf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bGuardar = findViewById(R.id.bGuardar);
        bClear = findViewById(R.id.bClear);
        bGet = findViewById(R.id.bGet);
        etNom = findViewById(R.id.etNom);
        etTlf = findViewById(R.id.etTlf);
    }

    public void bClearOnClick (View view){
        //etNom.setHint(getString(R.string.Nom));
        //etTlf.setHint(getString(R.string.Telf));
        etTlf.setText("");
        etNom.setText("");
    }

    public void bGuardarOnClick (View view){
        String nom = etNom.getText().toString().trim();
        String tlf = etTlf.getText().toString().trim();
        SharedPreferences sharedPref = getSharedPreferences("cat.institutmarianao.PROJECT_5_PREFS", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        if(nom.isEmpty() && tlf.isEmpty()){
            Toast.makeText(this, this.getString(R.string.noNomTlf), Toast.LENGTH_SHORT).show();
        } else if (nom.isEmpty() && !tlf.isEmpty()){
            Toast.makeText(this, this.getString(R.string.noNom), Toast.LENGTH_SHORT).show();
        } else if (!nom.isEmpty() && tlf.isEmpty()){
            //popup
            //https://stackoverflow.com/questions/36747369/how-to-show-a-pop-up-in-android-studio-to-confirm-an-order
        } else {
            editor.putString(nom, tlf);
        }

    }
}