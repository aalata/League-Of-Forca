package com.example.tallessiqueira.leagueofforca.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by talles.siqueira on 01/09/2014.
 */
public class ScoreDao {

    private static ScoreDao instance;
    private Hall_Of_Fame_DAO dbHelper;
    private SQLiteDatabase db;

    public static ScoreDao getInstance(Context context){
        if(instance == null){
            instance = new ScoreDao(context.getApplicationContext());
        }
        return instance;
    }

    private ScoreDao(Context context){
        dbHelper = Hall_Of_Fame_DAO.getInstance(context);
        db = dbHelper.getWritableDatabase();
    }

    public List<Jogador> list(){

        db = dbHelper.getWritableDatabase();

        String[] colunas = {
                Constantes.Colunas._ID,
                Constantes.Colunas.NOME,
                Constantes.Colunas.PONTUACAO
        };

        Cursor cursor = db.query(Constantes.nomeTabela,colunas,null,null,null,null,Constantes.Colunas.PONTUACAO + " DESC");

        List<Jogador> jogador = new ArrayList<Jogador>();

        try{
            if(cursor.moveToFirst()){
                do{
                    Jogador j = ScoreDao.fromCursor(cursor);
                    jogador.add(j);
                }while (cursor.moveToNext());
            }
        }finally {
            db.close();
        }

        return jogador;
    }

    public void save(Jogador jogador){

        db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Constantes.Colunas.NOME,jogador.getNome());
        values.put(Constantes.Colunas.PONTUACAO, jogador.getPontuacao());

        long id = db.insert(Constantes.nomeTabela,null,values);
        jogador.setId((int)id);
    }

    public void update(Jogador jogador){

        ContentValues values = new ContentValues();
        values.put(Constantes.Colunas.NOME,jogador.getNome());
        values.put(Constantes.Colunas.PONTUACAO, jogador.getPontuacao());

        db.update(
                Constantes.nomeTabela,
                values,
                Constantes.Colunas._ID +"=?",
                new String[]{String.valueOf(jogador.getId())});
    }

    public static Jogador fromCursor(Cursor cursor){
        int id = cursor.getInt(cursor.getColumnIndex(Constantes.Colunas._ID));
        String nome = cursor.getString(cursor.getColumnIndex(Constantes.Colunas.NOME));
        int pontuacao = cursor.getInt(cursor.getColumnIndex(Constantes.Colunas.PONTUACAO));
        return new Jogador(id,nome,pontuacao);
    }
}
