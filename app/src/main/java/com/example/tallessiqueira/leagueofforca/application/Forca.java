package com.example.tallessiqueira.leagueofforca.application;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

public class Forca {

    private char letrasUsadas[] = {};
    private int posicao = 0;

    public Forca (){
        posicao=0;
    }

    public String[] doChangeWordToUnderline(String word){
        String[] underlinedWord = word.split("(?!^)");

        for (int i=0 ; i<underlinedWord.length ; i++){
            underlinedWord[i] = "_ ";
        }
        return underlinedWord;
    }

    public String doBreakWord (String word, String[] brokenWord){
        brokenWord = word.split("(?!^)");
        StringBuilder builder = new StringBuilder();

        for(String s : brokenWord) {
            builder.append(s);
        }
        return builder.toString();
    }

    public boolean doCompareLetter (char[] strSplit, String[] underlinedWord, EditText editTextLetra){
        int count = 0;
        for (int i=0 ; i<strSplit.length ; i++){
            if (strSplit[i] == editTextLetra.getText().toString().toLowerCase().charAt(0)){
                underlinedWord[i] = editTextLetra.getText().toString().toLowerCase().charAt(0)+" ";
                count++;
            }
        }
        if (count != 0)
            return true;
        else
            return false;
    }

    /**public boolean doCompareLetterUsed (EditText editTextLetra){
        int count = 0;
        if(posicao > 0) {
            for (int i = 0; i <= (letrasUsadas.length + 1); i++) {
                if (letrasUsadas[i] != editTextLetra.getText().toString().toLowerCase().charAt(0)) {
                    letrasUsadas[i] = editTextLetra.getText().toString().toLowerCase().charAt(0);
                    count++;
                }
            }
        }else {
            letrasUsadas[posicao] = editTextLetra.getText().toString().toLowerCase().charAt(0);
            posicao++;
        }
        if (count != 0)
            return false;
        else
            return true;
    }**/

    public String doConvertStringArrayToString (String[] str){
        StringBuilder builder = new StringBuilder();

        for(String s : str) {
            builder.append(s);
        }
        return builder.toString();
    }

    public void draw (int tries, ImageView cabeca, ImageView corpo, ImageView braco1, ImageView braco2, ImageView perna1, ImageView perna2){
        switch (tries){
            case 6: drawImageView(cabeca);
                break;
            case 5: drawImageView(corpo);
                break;
            case 4: drawImageView(braco1);
                break;
            case 3: drawImageView(braco2);
                break;
            case 2: drawImageView(perna1);
                break;
            case 1: drawImageView(perna2);
                break;
        }
    }

    public void drawImageView (ImageView imageView){
        imageView.setVisibility(View.VISIBLE);
    }

    public boolean checkWin (String[] underlinedWord, String word){
        String[] strSplitWord = word.split("(?!^)");
        String underlinedWord2 = doConvertStringArrayToString(underlinedWord).replaceAll(" ", "");
        String[] underlinedWordSplit = underlinedWord2.split("(?!^)");
        int count = 0;

        for (int i=0 ; i<strSplitWord.length ; i++){
            if (strSplitWord[i] == underlinedWordSplit[i]){
                count++;
            }
        }
        if (count == strSplitWord.length)
            return true;
        else
            return false;
    }
}
