package com.example.tallessiqueira.leagueofforca.application;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tallessiqueira.leagueofforca.DAO.Jogador;
import com.example.tallessiqueira.leagueofforca.DAO.ScoreDao;
import com.example.tallessiqueira.leagueofforca.R;

import java.util.Random;

public class MyActivity extends Activity {
    private String[] dataBase = {"aatrox", "akali", "ahri", "cassiopeia",
            "fizz", "lissandra", "lux", "graves",
            "caitlyn", "vayne", "zac", "malphite",
            "mundo", "khazix", "rengar", "brand",
            "annie", "elise", "katarina", "orianna",
            "anivia", "rammus", "lucian", "nami",
            "janna", "soraka", "sona", "syndra",
            "ryze", "jarvan", "garen", "zed",
            "alistar", "shen", "vi", "udyr",
            "heimerdinger", "sivir", "amumu",
            "fiddlesticks", "nasus", "renekton",
            "volibear", "kennen", "teemo", "kayle",
            "morgana", "wukong", "jax", "tryndamere",
            "tristana", "nautilus", "kassadin", "singed",
            "maokai", "viktor", "swain", "leona",
            "shaco", "trundle", "pantheon", "ashe",
            "brand", "evelynn", "galio", "jax",
            "jayce", "karma", "karthus", "leblanc",
            "malzahar", "poppy", "shyvana", "sion",
            "thresh", "twitch", "warwick", "yorick",
            "ziggs", "zilean", "zyra", "gnar", "velkoz", "braum", "azir"};
    private Forca forca = new Forca();
    private Random random = new Random();
    private int randNumber;
    private String[] underlinedWord;
    private int tries;
    private int score = 0;
    private String pontuacao;
    private int pontuacaoFinal = 0;
    private String nome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        //torna invisível as referências para a forca
        ImageView forca_topo = (ImageView) findViewById(R.id.imgForcaTopo);
        ImageView forca_lateal = (ImageView) findViewById(R.id.imageViewForca1);
        ImageView forca_lateral_1 = (ImageView) findViewById(R.id.imageViewForca2);
        ImageView forca_lateral_2 = (ImageView) findViewById(R.id.imageViewForca3);

        forca_topo.setVisibility(View.INVISIBLE);
        forca_lateal.setVisibility(View.INVISIBLE);
        forca_lateral_1.setVisibility(View.INVISIBLE);
        forca_lateral_2.setVisibility(View.INVISIBLE);

        //reseta o jogo
        resetGame();

        pontuacao = String.valueOf(score);

        TextView newScore = (TextView) findViewById(R.id.txtNewScore);
        newScore.setText(pontuacao);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }

    public void btnTentar (View v){
        ImageView cabeca = (ImageView) findViewById(R.id.imgCabeca);
        ImageView braco1 = (ImageView) findViewById(R.id.imgBracoDireito);
        ImageView braco2 = (ImageView) findViewById(R.id.imgBracoEsquerdo);
        ImageView corpo = (ImageView) findViewById(R.id.imgCorpo);
        ImageView perna1 = (ImageView) findViewById(R.id.imgPernaDireita);
        ImageView perna2 = (ImageView) findViewById(R.id.imgPernaEsquerda);
        TextView textViewPalavra = (TextView) findViewById(R.id.txtPalavraEscondida);
        TextView textViewLetrasForam = (TextView) findViewById(R.id.txtLetrasUsadas);
        EditText editTextLetra = (EditText) findViewById(R.id.editTextLetra);
        AlertDialog.Builder msgBox = new AlertDialog.Builder(this);

        char[] strSplit = dataBase[randNumber].toCharArray();

        if (editTextLetra.getText().toString().equals("") || editTextLetra.getText().toString().equals(null) || editTextLetra.getText().toString().length() > 1){
            msgBox.setTitle("Erro!");
            msgBox.setMessage("Você precisa digitar uma letra!");
            msgBox.setNeutralButton("OK", new DialogInterface.OnClickListener(){
                public void onClick (DialogInterface dialog, int which){
                }
            });
            msgBox.show();
        }
        /**if(forca.doCompareLetterUsed(editTextLetra)){
            msgBox.setTitle("Erro!");
            msgBox.setMessage("Cada letra só pode ser utilizada apenas 1 vez!");
            msgBox.setNeutralButton("OK", new DialogInterface.OnClickListener(){
                public void onClick (DialogInterface dialog, int which){
                }
            });
            msgBox.show();
        }**/
        else{
            textViewLetrasForam.setText(textViewLetrasForam.getText() + " " + editTextLetra.getText().toString());
            if (forca.doCompareLetter(strSplit, underlinedWord, editTextLetra)){
                if (forca.doConvertStringArrayToString(underlinedWord).replaceAll(" ", "").equals(dataBase[randNumber])){
                    Toast.makeText(getApplicationContext(),"Parabéns! Você descobriu o campeão",
                            Toast.LENGTH_LONG).show();
                    score += 15;
                    pontuacao = String.valueOf(score);
                    TextView newScore = (TextView) findViewById(R.id.txtNewScore);
                    newScore.setText(pontuacao);
                    /**msgBox.setTitle("Parabéns!");
                    msgBox.setMessage("Você acertou a palavra!");
                    msgBox.setPositiveButton("Jogar Novamente", new DialogInterface.OnClickListener(){
                        public void onClick (DialogInterface dialog, int which){
                            resetGame();
                        }
                    });
                    msgBox.setNegativeButton("Finalizar", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    });
                    msgBox.show();**/
                    resetGame();
                }
                score += 5;
                pontuacao = String.valueOf(score);
                TextView newScore = (TextView) findViewById(R.id.txtNewScore);
                newScore.setText(pontuacao);
            }
            else{
                forca.draw(tries, cabeca, corpo, braco1, braco2, perna1, perna2);
                tries--;
                if(score > 0 && score > 2){
                    score -= 3;
                    pontuacao = String.valueOf(score);
                    TextView newScore = (TextView) findViewById(R.id.txtNewScore);
                    newScore.setText(pontuacao);
                }else {
                    score = 0;
                    pontuacao = String.valueOf(score);
                    TextView newScore = (TextView) findViewById(R.id.txtNewScore);
                    newScore.setText(pontuacao);
                }
                if (tries == 0){
                    //campo onde o jogador irá inserir o nome
                    final EditText nomeJogador = new EditText(this);

                    msgBox.setTitle("Aaah!");
                    msgBox.setMessage("Esse não é o nosso campeão! O campeão era: " + dataBase[randNumber]);
                    msgBox.setMessage("Insira seu nome para nosso ranking");
                    msgBox.setView(nomeJogador);
                    msgBox.setPositiveButton("Jogar Novamente", new DialogInterface.OnClickListener(){
                        public void onClick (DialogInterface dialog, int which){
                            nome = nomeJogador.getText().toString();
                            pontuacaoFinal = score;
                            score = 0;
                            pontuacao = String.valueOf(score);
                            TextView newScore = (TextView) findViewById(R.id.txtNewScore);
                            newScore.setText(pontuacao);
                            insereNoRanking(nome, pontuacaoFinal);
                            resetGame();
                        }
                    });
                    msgBox.setNegativeButton("Finalizar", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            nome = nomeJogador.getText().toString();
                            pontuacaoFinal = score;
                            insereNoRanking(nome, pontuacaoFinal);
                            finish();
                        }
                    });
                    msgBox.show();
                }
            }
        }
        textViewPalavra.setText(forca.doConvertStringArrayToString(underlinedWord));
        editTextLetra.setText("");
    }

    public void btnTentarPalavra (View v) {
        AlertDialog.Builder msgBox = new AlertDialog.Builder(this);
        EditText editTextTentarPalavra = (EditText) findViewById(R.id.editTextTentarPalavra);
        String tentativa = editTextTentarPalavra.getText().toString();

        if (tentativa.length() > 1){
            if (tentativa.equalsIgnoreCase(dataBase[randNumber])){
                score += 20;
                pontuacao = String.valueOf(score);
                TextView newScore = (TextView) findViewById(R.id.txtNewScore);
                newScore.setText(pontuacao);
                /**msgBox.setTitle("Parabéns!");
                msgBox.setMessage("Você acertou a palavra!");
                msgBox.setPositiveButton("Jogar Novamente", new DialogInterface.OnClickListener(){
                    public void onClick (DialogInterface dialog, int which){
                        resetGame();
                    }
                });
                msgBox.setNegativeButton("Finalizar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
                msgBox.show();**/
                Toast.makeText(getApplicationContext(),"Parabéns! Você descobriu o campeão",
                        Toast.LENGTH_LONG).show();
                resetGame();
            }
            else{
                //campo onde o jogador irá inserir o nome
                final EditText nomeJogador = new EditText(this);

                msgBox.setTitle("Aaah!");
                msgBox.setMessage("Esse não é o nosso campeão! O campeão era: " + dataBase[randNumber]
                        + "\nInsira seu nome para o nosso ranking");
                msgBox.setView(nomeJogador);
                msgBox.setPositiveButton("Jogar Novamente", new DialogInterface.OnClickListener(){
                    public void onClick (DialogInterface dialog, int which){
                        nome = nomeJogador.getText().toString();
                        pontuacaoFinal = score;
                        score = 0;
                        pontuacao = String.valueOf(score);
                        TextView newScore = (TextView) findViewById(R.id.txtNewScore);
                        newScore.setText(pontuacao);
                        insereNoRanking(nome, pontuacaoFinal);
                        resetGame();

                    }
                });
                msgBox.setNegativeButton("Finalizar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        nome = nomeJogador.getText().toString();
                        pontuacaoFinal = score;
                        insereNoRanking(nome,pontuacaoFinal);
                        finish();
                    }
                });
                msgBox.show();
            }
        }
        else{
            msgBox.setTitle("Erro!");
            msgBox.setMessage("Você precisa digitar uma palavra!");
            msgBox.setNeutralButton("Ok", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                }
            });
            msgBox.show();
        }
        editTextTentarPalavra.setText("");
    }

    public void resetGame(){
        ImageView cabeca = (ImageView) findViewById(R.id.imgCabeca);
        ImageView braco1 = (ImageView) findViewById(R.id.imgBracoDireito);
        ImageView braco2 = (ImageView) findViewById(R.id.imgBracoEsquerdo);
        ImageView corpo = (ImageView) findViewById(R.id.imgCorpo);
        ImageView perna1 = (ImageView) findViewById(R.id.imgPernaDireita);
        ImageView perna2 = (ImageView) findViewById(R.id.imgPernaEsquerda);
        TextView textViewPalavra = (TextView) findViewById(R.id.txtPalavraEscondida);
        TextView textViewLetrasForam = (TextView) findViewById(R.id.txtLetrasUsadas);

        cabeca.setVisibility(View.INVISIBLE);
        braco1.setVisibility(View.INVISIBLE);
        braco2.setVisibility(View.INVISIBLE);
        corpo.setVisibility(View.INVISIBLE);
        perna1.setVisibility(View.INVISIBLE);
        perna2.setVisibility(View.INVISIBLE);

        randNumber = random.nextInt(dataBase.length-1);
        tries = 6;
        underlinedWord = forca.doChangeWordToUnderline(dataBase[randNumber]);
        textViewPalavra.setText(forca.doConvertStringArrayToString(underlinedWord));
        textViewLetrasForam.setText("");
    }

    @Override
    public void onBackPressed() {
        // Do Here what ever you want do on back press;
    }

    private void insereNoRanking(String nome, int pontuacao){
        Jogador jogador = new Jogador(nome,pontuacao);

        ScoreDao.getInstance(getApplicationContext()).save(jogador);
    }
}
