package com.example.guga_.calculoimc;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class telaPrimeiroAcesso extends AppCompatActivity {

    EditText txtAltura, txtPeso2;
    Button botaoteste;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_primeiro_acesso);


        txtAltura = findViewById(R.id.txtAltura);
        txtPeso2 = findViewById(R.id.txtPeso2);
        botaoteste = findViewById(R.id.btnSalvar);

        txtAltura.addTextChangedListener(Mascara.mask(txtAltura, Mascara.FORMAT_ALTURA));
        txtPeso2.addTextChangedListener(Mascara.mask(txtPeso2, Mascara.FORMAT_PESO2));


        txtPeso2.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {

                    if (txtPeso2.getText().length() == 5) {

                        txtPeso2.setText("0" + txtPeso2.getText().toString());

                    } else if (txtPeso2.getText().length() == 4) {

                        txtPeso2.setText(txtPeso2.getText().toString() + "00");

                    }

                }


            }

        });




        botaoteste.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (txtAltura.getText().toString().equals("") || txtPeso2.getText().toString().equals("")) {

                        Toast.makeText(telaPrimeiroAcesso.this, "Algum campo está em branco, preencha-o", Toast.LENGTH_SHORT).show();

                    } else if(txtPeso2.getText().length() == 2)
                    {
                        Toast.makeText(telaPrimeiroAcesso.this, "Preencha corretamente o campo peso", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {

                        SharedPreferences.Editor editor = getSharedPreferences("dados", MODE_PRIVATE).edit();


                        editor.putString("valorAcesso", "acessosalvo");
                        editor.apply();

                    }

                }
            });











    }
}
