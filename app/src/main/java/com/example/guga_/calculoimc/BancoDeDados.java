package com.example.guga_.calculoimc;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;

public class BancoDeDados extends SQLiteOpenHelper {
    private final String criaUsuario = "CREATE TABLE usuario (" +
            "pesoInicial REAL NOT NULL, " +
            "pesoAtual REAL NOT NULL, " +
            "pesoIdeal REAL NOT NULL, " +
            "pesoRestante REAL NOT NULL, " +
            "altura REAL NOT NULL," +
            "imc REAL NOT NULL, " +
            "data TEXT NOT NULL);";

    private final String criaHistorico = "CREATE TABLE historico (" +
            "novoPeso REAL NOT NULL, " +
            "novoImc REAL NOT NULL, " +
            "novoRestante REAL  NOT NULL,  " +
            "novaData REAL NOT NULL);";



    public BancoDeDados(Context context, int version) {
        super(context, "BDimc", null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //Executando comandos SQL no banco de dados
        sqLiteDatabase.execSQL(criaUsuario);
        sqLiteDatabase.execSQL(criaHistorico);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        db.execSQL("PRAGMA foreign_keys=ON;");
    }

//    public boolean cadastrarCategoria(String nome){
//        boolean cadastrado = false;
//
//        //Abrir a conexão com o banco de dados
//        SQLiteDatabase banco = getWritableDatabase();
//
//        //Passagem de valores para a tabela "categoria"
//        ContentValues valores = new ContentValues();
//        valores.put("nome", nome); //Campo_da_tabela, valor
//
//        //Execução do comando
//        if(banco.insert("categoria", null, valores) != -1)
//            cadastrado = true;
//
//        return cadastrado;
//    }

    public ArrayList<Historico> buscaHistorico(){
        ArrayList<Historico> lista = new ArrayList<Historico>();

        //Conexão com o banco de dados
        SQLiteDatabase banco = getWritableDatabase();

        //O objeto Cursor é aonde ficará armazenado o retorno do SELECT
        //O último parâmetro como null indica que não teremos condição WHERE
        Cursor c = banco.rawQuery("SELECT * FROM historico", null);

        //Se o cursor conseguir mover-se até a primeira linha, indica que temos pelo menos
        //um valor retornado, ou mais de um
        if(c.moveToFirst()){
            do{
                //O cursor "c" possui os valores do SELECT
                //c.getInt recupera um valor inteiro da posição X
                Historico hist = new Historico(c.getFloat(0), c.getFloat(1), c.getFloat(2), c.getString(3));
                lista.add(hist);

            }while(c.moveToNext()); //Enquanto tiver um próximo valor retornado
        }

        return  lista;
    }





      public Boolean cadastrarUsu(double pesoInicial, double pesoAtual, double pesoIdeal, double pesoRestante, double altura, double imc, String data){
        SQLiteDatabase banco = getWritableDatabase(); //Conexão com o banco de dados

        //Passagem de valores para as colunas da tabela
        ContentValues valoresUsu = new ContentValues();
        valoresUsu.put("pesoInicial", pesoInicial);
        valoresUsu.put("pesoAtual", pesoAtual);
        valoresUsu.put("pesoIdeal", pesoIdeal);
        valoresUsu.put("pesoRestante", pesoRestante);
        valoresUsu.put("altura", altura);
        valoresUsu.put("imc", imc);
        valoresUsu.put("data", data);

        ContentValues valoresHist = new ContentValues();

        valoresHist.put("novoPeso", pesoInicial);
        valoresHist.put("novoImc", imc);
        valoresHist.put("novoRestante", pesoRestante);
        valoresHist.put("novaData", data);



                if(banco.insert("usuario", null, valoresUsu)!= -1 &&
                        banco.insert("historico", null, valoresHist) != -1){
                    return true;
                }else{
                    return false;
                }



    }

//    public boolean cadastrarHistorico(float novoPeso, float novoRestante, float novoImc, String novaData){
//        SQLiteDatabase banco = getWritableDatabase(); //Conexão com o banco de dados
//
//
//    }


//    public ArrayList<Filme> buscaFilmes(){
//        ArrayList<Filme> lista = new ArrayList<Filme>();
//
//        SQLiteDatabase banco = getWritableDatabase(); //Conexão com o banco
//
//        Cursor c = banco.rawQuery(
//         "SELECT F._id AS idFilme, titulo, ano, C._id AS idCategoria, C.nome " +
//            " FROM filme F, categoria C WHERE id_categoria = C._id", null);
//
//        if(c.moveToFirst()){
//            do{
//                Filme f = new Filme(c.getInt(0), c.getString(1),
//                                    c.getInt(2), c.getInt(3), c.getString(4));
//
//                lista.add(f);
//            }while (c.moveToNext());
//        }
//
//        return lista;
//    }

//    public boolean apagarFilme(int idFilme){
//        SQLiteDatabase banco = getWritableDatabase();
//
//        if(banco.delete("filme", "_id = ?", new String[]{idFilme + ""}) != -1)
//            return true;
//        else
//            return false;
//    }






    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}





















