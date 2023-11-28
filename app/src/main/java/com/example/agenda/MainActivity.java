    package com.example.agenda;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;
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
            eliminarContacte();
        } else if (!nom.isEmpty() && !tlf.isEmpty()) {
            editor.putString(nom, tlf);
            Toast.makeText(this, this.getString(R.string.nomAfegir), Toast.LENGTH_SHORT).show();
            editor.apply();
        }
    }

    public void bGetPhoneOnClick (View view){
        String nom = etNom.getText().toString().trim();
        String tlf = "";
        SharedPreferences prefs = getSharedPreferences("cat.institutmarianao.PROJECT_5_PREFS", Context.MODE_PRIVATE);
        if (!nom.isEmpty() && tlf.isEmpty()){
            String tlfComprovar = prefs.getString(nom, null);
            if (tlfComprovar == null){
                etTlf.setError(getString(R.string.nomInvalid));
            } else {
                etTlf.setText(tlfComprovar);
            }
        } else if(nom.isEmpty() && tlf.isEmpty()) {
            Toast.makeText(this, this.getString(R.string.noNom), Toast.LENGTH_SHORT).show();
        }
    }

    public void eliminarContacte (){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setMessage("Estas segur que vols borrar el contacte ? ");
        builder.setPositiveButton("Confirm",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

}
