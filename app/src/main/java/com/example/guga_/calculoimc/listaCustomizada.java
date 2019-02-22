package com.example.guga_.calculoimc;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class listaCustomizada extends ArrayAdapter<String> {

    private String [] tvPeso;
    private String [] tvImc;
    private String [] tvPesoGanho;
    private String [] tvData;

    private Context context;


    public listaCustomizada(Context context, String [] tvPeso, String[] tvImc, String[] tvPesoGanho, String[] tvData) {
        super(context, R.layout.layoutlista, tvPeso);

        this.context = context;
        this.tvPeso = tvPeso;
        this.tvImc = tvImc;
        this.tvPesoGanho = tvPesoGanho;
        this.tvData = tvData;

    }

    public View getView (int position, @Nullable View convertView, @NonNull ViewGroup parent){
        View r = convertView;
        ViewHolder viewHolder = null;
        if(r==null)
        {

            LayoutInflater inflater = LayoutInflater.from(getContext());

            r = inflater.inflate(R.layout.layoutlista, null, true);
            viewHolder = new ViewHolder(r);
            r.setTag(viewHolder);

        }else {

            viewHolder = (ViewHolder) r.getTag();
        }


        viewHolder.tvw1.setText("" + tvPeso[position]);
        viewHolder.tvw2.setText("" + tvImc[position]);
        viewHolder.tvw3.setText("" + tvPesoGanho[position]);
        viewHolder.tvw4.setText("" + tvData[position]);


        return r;





    }
    class ViewHolder
    {
        TextView tvw1, tvw2, tvw3, tvw4;

        ViewHolder(View v)
        {
            tvw1 = v.findViewById(R.id.tvPesoLista);
            tvw2 = v.findViewById(R.id.tvImcLista);
            tvw3 = v.findViewById(R.id.tvPesoDiferenca);
            tvw4 = v.findViewById(R.id.tvDataLista);
        }
    }

}