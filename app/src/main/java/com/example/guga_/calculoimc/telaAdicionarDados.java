package com.example.guga_.calculoimc;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.widget.Toast;

public class telaAdicionarDados extends AppCompatActivity {

    EditText txtPeso, txtData;
    Button Botao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_adicionar_dados);


        txtPeso = findViewById(R.id.txtPeso);
        txtData = findViewById(R.id.txtData);
        Botao =  findViewById(R.id.btnAtualizar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Novo registro de peso");

        SimpleDateFormat formataData = new SimpleDateFormat("dd/MM/yyyy");


        Date data = new Date();
        String dataFormatada = formataData.format(data);

        txtData.setText(dataFormatada);





       txtPeso.addTextChangedListener(Mascara.mask(txtPeso, Mascara.FORMAT_PESO2));

       txtPeso.setOnFocusChangeListener(new View.OnFocusChangeListener() {
           @Override
           public void onFocusChange(View v, boolean hasFocus) {
               if (!hasFocus) {

                   if (txtPeso.getText().length() == 5) {

                       txtPeso.setText("0" + txtPeso.getText().toString());

                   } else if (txtPeso.getText().length() == 4) {

                       txtPeso.setText(txtPeso.getText().toString() + "00");

                   }

           }


            }

       });


       Botao.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

               BancoDeDados db = new BancoDeDados(telaAdicionarDados.this, 1);
//
//               if(db.atualizaUsu(pesoInicial, pesoAtual, pesoIdealformatado, pesoRestante, altura, imcFormatado, dataFormatada)) {
//
//
//               }






//               Toast.makeText(telaAdicionarDados.this, "" + acesso, Toast.LENGTH_SHORT).show();

           }
       });






    }
}
