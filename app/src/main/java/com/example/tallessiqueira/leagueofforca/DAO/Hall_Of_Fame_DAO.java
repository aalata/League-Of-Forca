package com.example.tallessiqueira.leagueofforca.DAO;

import android.app.AlertDialog;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by talles.siqueira on 29/08/2014.
 */
public class Hall_Of_Fame_DAO extends SQLiteOpenHelper{

    /** O nome do arquivo de base de dados no sistema de arquivos */
    private static final String NOME_BD = "League_Of_Forca_db";
    /** A versão da base de dados que esta classe compreende. */
    private static final int VERSAO_BD = 2;

    Context context;

            //Comando SQL que criará a tabela de jogador
    private static final String sql_create = String.format("create table %s (%s integer primary key " +
            "autoincrement, %s text not null, %s integer not null)", Constantes.nomeTabela, Constantes.Colunas._ID,
            Constantes.Colunas.NOME, Constantes.Colunas.PONTUACAO);
    //Comando SQL que apagar a tabela jogador
    private static final String sql_drop = String.format("drop table if exists " + Constantes.nomeTabela);

    /** Mantém rastreamento do contexto que nós podemos carregar SQL */
    private static Hall_Of_Fame_DAO instance;

    public static Hall_Of_Fame_DAO getInstance(Context context) {
        if(instance == null){
            instance = new Hall_Of_Fame_DAO(context);
        }
        return instance;
    }

    private Hall_Of_Fame_DAO(Context context){
        super(context,NOME_BD,null,VERSAO_BD);
        }


    @Override
    public void onCreate(SQLiteDatabase db)
    {
        try {
            //deleta a tabela jogador se ela existir
            db.execSQL(sql_drop);
            //cria/recria a tabela jogador
            db.execSQL(sql_create);
        }catch (Exception ex){
            mensagemAlerta("Erro no banco","O erro foi: " + ex);
        }finally {
            db.close();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        // Isto é apenas didático. Na vida real, você terá de adicionar novas colunas e não apenas recriar o mesmo banco
        onCreate(db);
    }

    public void mensagemAlerta(String TituloAlerta, String MensagemAlerta){
        AlertDialog.Builder Mensagem = new AlertDialog.Builder(context);
        Mensagem.setTitle(TituloAlerta);
        Mensagem.setMessage(MensagemAlerta);
        Mensagem.setNeutralButton("OK", null);
        Mensagem.show();
    }
}
