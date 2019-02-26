package com.example.guga_.calculoimc;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import android.widget.Toast;

public class telaAdicionarDados extends AppCompatActivity {

    EditText txtPeso, txtData;
    Button btnAtualizar;
    static String current = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_adicionar_dados);



        txtPeso = findViewById(R.id.txtPeso);
        txtData = findViewById(R.id.txtData);
        btnAtualizar =  findViewById(R.id.btnAtualizar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Novo registro de peso");

        SimpleDateFormat formataData = new SimpleDateFormat("dd/MM/yyyy");


        Date data = new Date();
        final String dataFormatada = formataData.format(data);

        txtData.setText(dataFormatada);


        txtPeso.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable) {
                if (!editable.toString().equals(current)) {
                    double value = Double.parseDouble(editable.toString().replace(".", "")) / 100;
                    DecimalFormat format = new DecimalFormat("0.00");
                    String formatted = format.format(value);
                    current = formatted;
                    txtPeso.setText(current);
                    txtPeso.setSelection(current.length());
                }
            }
        });


//       txtPeso.addTextChangedListener(Mascara.mask(txtPeso, Mascara.FORMAT_PESO2));
//
//       txtPeso.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//           @Override
//           public void onFocusChange(View v, boolean hasFocus) {
//               if (!hasFocus) {
//
//                   if (txtPeso.getText().length() == 5) {
//
//                       txtPeso.setText("0" + txtPeso.getText().toString());
//
//                   } else if (txtPeso.getText().length() == 4) {
//
//                       txtPeso.setText(txtPeso.getText().toString() + "00");
//
//                   }
//
//           }
//
//
//            }
//
//       });


       btnAtualizar.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {


               if(txtPeso.getText().toString().equals("") || txtData.getText().toString().equals("")){
                   Toast.makeText(telaAdicionarDados.this, "Não deixe campos em branco", Toast.LENGTH_SHORT).show();
               }else if(Double.parseDouble(txtPeso.getText().toString()) > 550){
                   Toast.makeText(telaAdicionarDados.this, "Verifique se digitou corretamente o peso", Toast.LENGTH_SHORT).show();

               }else{



               BancoDeDados db = new BancoDeDados(telaAdicionarDados.this, 1);
               SharedPreferences sharedPreferencess = telaSplash.contexto.getSharedPreferences("dados", Context.MODE_PRIVATE);

               String pesoInicial = sharedPreferencess.getString("pesoInicial", "");
               String altura = sharedPreferencess.getString("alturaInicial", "");
               String pesoIdeal = sharedPreferencess.getString("pesoIdeal", "");




               double pesoIni = Double.parseDouble(pesoInicial);


               //Peso atual formatado
               double pesoAtu = Double.valueOf(String.format(Locale.US, "%.2f", Math.ceil(Double.parseDouble(txtPeso.getText().toString()))));

               //Peso Ideal formatado
               double pesoIdea = Double.valueOf(String.format(Locale.US, "%.2f", Math.ceil(Double.parseDouble(pesoIdeal))));

               double pesoRest = pesoAtu - pesoIdea;

               //Altura formatada
               double alturaUsada = Double.valueOf(String.format(Locale.US, "%.2f", Math.ceil(Double.parseDouble(altura))));

               //IMC FORMATADO
               double novoImc = pesoAtu/(Math.pow(alturaUsada, 2));
               double imcFormatado = Double.valueOf(String.format(Locale.US, "%.2f", Math.ceil(novoImc)));


               if(db.updateUsu(pesoIni, pesoAtu, imcFormatado, pesoRest, txtData.getText().toString())){
                   Toast.makeText(telaAdicionarDados.this, "Atualizado com sucesso", Toast.LENGTH_SHORT).show();
                    Intent it = new Intent(telaAdicionarDados.this, Principal.class);
                    startActivity(it);

               }else{
                   Toast.makeText(telaAdicionarDados.this, "Erro ao atualizar", Toast.LENGTH_SHORT).show();
               }



               }



           }
       });






    }

}

