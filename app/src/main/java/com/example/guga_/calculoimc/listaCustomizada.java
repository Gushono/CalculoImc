package com.example.guga_.calculoimc;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class listaCustomizada extends ArrayAdapter<Historico> {
    private ArrayList<Historico> lista;
    Context context;

    //Construtor da classe   //Necessário por causa do Extends
    public listaCustomizada(ArrayList<Historico> dados, Context context) {
        super(context, R.layout.layoutlista, dados);
        this.lista = dados;
        this.context = context;
    }

    //Classe que define quais componentes serão usados para cada item da lista
    //Os componentes seguem o mesmo conteúdo definido no layout XML de cada item
    private static class ViewHolder{
        TextView peso;
        TextView imc;
        TextView pesoganho;
        TextView data;
    }

    //Sobrescrita de método utilizado para definir o que acontece para cada item quando for montar
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //Recupera os itens que serão adicionados na lista
        Historico hist = getItem(position);

        //Criação de uma View temporária para poder encontrar os componentes da tela e vincular um valor
        ViewHolder viewHolder;

        //View aonde será adicionado todos os componentes já prontos
        View resultado;

        //Teste para verificar se a View que veio pelo método "getView" está vazia
        if(convertView == null){
            //Criação de uma ViewHolder nova para poder configurar cada componente da tela
            viewHolder = new ViewHolder();

            //Classe responsável por vincular um layout XML
            LayoutInflater inflater = LayoutInflater.from(getContext());

            //Indicando qual layout XML será utilizado para preenchimento
            convertView = inflater.inflate(R.layout.layoutlista, parent, false);

            //Vinculando cada componente do XML com o componente do Adapter
            viewHolder.peso = (TextView) convertView.findViewById(R.id.tvPesoLista);
            viewHolder.imc = (TextView) convertView.findViewById(R.id.tvImcLista);
            viewHolder.pesoganho = (TextView) convertView.findViewById(R.id.tvPesoDiferenca);
            viewHolder.data = (TextView) convertView.findViewById(R.id.tvDataLista);

            //Atribuindo todos os componentes para a View
            resultado = convertView;
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
            resultado = convertView;
        }

        //Atribuindo valores para cada componente da tela

        viewHolder.peso.setText("" +hist.getNovoPeso());
        viewHolder.imc.setText("" +hist.getNovoImc());

        if(hist.getNovoRestante() < 0){
            viewHolder.pesoganho.setTextColor(Color.GREEN);
            viewHolder.pesoganho.setText("+ " + hist.getNovoRestante()*-1);
        }else if (hist.getNovoRestante() > 0){
            viewHolder.pesoganho.setTextColor(Color.RED);
            viewHolder.pesoganho.setText("" + hist.getNovoRestante()*-1);
        }else if(hist.getNovoRestante() == 0){
            viewHolder.pesoganho.setText("" + hist.getNovoRestante());
        }


        viewHolder.data.setText("" + hist.getNovaData());

        return convertView;

    }
}
