package com.example.tallessiqueira.leagueofforca.DAO;

/**
 * Created by talles.siqueira on 01/09/2014.
 */
public class Jogador {

    private int id;
    private String nome;
    private int pontuacao;

    public Jogador(int id, String nome, int pontuacao){
        this.id = id;
        this.nome = nome;
        this.pontuacao = pontuacao;
    }

    public Jogador(String nome, int pontuacao){
        this.nome = nome;
        this.pontuacao = pontuacao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPontuacao() {

        return pontuacao;
    }

    public void setValor(int pontuacao) {
        this.pontuacao = pontuacao;
    }

}
