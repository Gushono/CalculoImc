package com.example.guga_.calculoimc;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class telaPrimeiroAcesso extends AppCompatActivity {

    EditText txtAltura, txtPeso2;
    Button btnSalvar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_primeiro_acesso);


        txtAltura = findViewById(R.id.txtAltura);
        txtPeso2 = findViewById(R.id.txtPeso2);
        btnSalvar = findViewById(R.id.btnSalvar);

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




        btnSalvar.setOnClickListener(new View.OnClickListener() {
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


                        double pesoInicial;
                        double pesoAtual = Double.parseDouble(txtPeso2.getText().toString());
                        double altura = Double.parseDouble(txtAltura.getText().toString());
                        double pesoIdeal;
                        double pesoRestante;
                        double imc;
                        String dataFormatada;
                        double valorSuporte = (altura *100 - 152.4)/2.54;

                        //AQUI SAI O VALOR DO PESO IDEAL
                       pesoIdeal = 50 + (valorSuporte * 2.3);
                       //VARIAVEL USADA PARA PEGAR O VALOR DO PESOIDEAL JÁ FORMATADO COM UMA CASA APÓS A VIRGULA
                       double pesoIdealformatado = Double.valueOf(String.format(Locale.US, "%.2f", Math.ceil(pesoIdeal))); //ok

                        //VALOR DO PESO RESTANTE QUE É A SUBTRAÇÃO DO PESO ATUAL MENOS O PESO FORMATADO
                       pesoRestante = pesoAtual - pesoIdealformatado; //ok
                       pesoInicial = pesoAtual; //ok

                        imc = pesoAtual/(Math.pow(altura, 2));  //ok
                        double imcFormatado = Double.valueOf(String.format(Locale.US, "%.2f", Math.ceil(imc)));

                        SimpleDateFormat formataData = new SimpleDateFormat("dd/MM/yyyy");
                        Date datinha= new Date();
                        dataFormatada = formataData.format(datinha);  //ok

                        BancoDeDados db = new BancoDeDados(telaPrimeiroAcesso.this, 1);


                        if(db.cadastrarUsu(pesoInicial, pesoAtual, pesoIdealformatado, pesoRestante, altura, imcFormatado, dataFormatada))
                        {
                            Toast.makeText(telaPrimeiroAcesso.this, "Usuario cadastrado", Toast.LENGTH_SHORT ).show();
                            SharedPreferences.Editor editor = getSharedPreferences("dados", MODE_PRIVATE).edit();
                            editor.putString("valorAcesso", "acessosalvo");
                            editor.apply();

                            Intent it = new Intent(telaPrimeiroAcesso.this, Principal.class);
                            startActivity(it);


                        }else
                        {
                            Toast.makeText(telaPrimeiroAcesso.this, "Erro ao cadastrar!", Toast.LENGTH_SHORT).show();
                        }








//


                    }

                }
            });














    }
}
