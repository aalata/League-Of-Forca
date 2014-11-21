package com.example.tallessiqueira.leagueofforca.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.tallessiqueira.leagueofforca.DAO.Jogador;
import com.example.tallessiqueira.leagueofforca.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by talles.siqueira on 01/09/2014.
 */
public class HallOfFame_Adapter extends BaseAdapter {

    private Context context;
    private List<Jogador> ranking = new ArrayList<Jogador>();

    public HallOfFame_Adapter(Context context){
        this.context = context;
    }
    @Override
    public int getCount() {
        return ranking.size();
    }

    @Override
    public Object getItem(int position) {
        return ranking.get(position);
    }

    @Override
    public long getItemId(int position) {
        return ranking.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        ViewHolder holder = null;

        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.adapter_hall_of_fame, parent, false);
            holder = new ViewHolder();
            holder.txtNome = (TextView) view.findViewById(R.id.txtNome);
            holder.txtPontuacao = (TextView) view.findViewById(R.id.txtPontuacao);
            view.setTag(holder);

        } else {
            holder = (ViewHolder) view.getTag();
        }

        Jogador jogador = ranking.get(position);

        holder.txtNome.setText(jogador.getNome());
        holder.txtPontuacao.setText(String.valueOf(jogador.getPontuacao()));

        return view;
    }

    public void setItems(List<Jogador> ranking) {
        this.ranking = ranking;
        notifyDataSetChanged();
    }

    public static class ViewHolder{
        TextView txtNome;
        TextView txtPontuacao;
    }
}
