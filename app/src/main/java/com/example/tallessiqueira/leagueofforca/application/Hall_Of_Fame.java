package com.example.tallessiqueira.leagueofforca.application;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.tallessiqueira.leagueofforca.Adapter.HallOfFame_Adapter;
import com.example.tallessiqueira.leagueofforca.DAO.Jogador;
import com.example.tallessiqueira.leagueofforca.DAO.ScoreDao;
import com.example.tallessiqueira.leagueofforca.R;

import java.util.List;

public class Hall_Of_Fame extends ListActivity {

    private ScoreDao scoreDao;
    private HallOfFame_Adapter adapter;
    private Button voltar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hall__of__fame);

        adapter = new HallOfFame_Adapter(this);
        setListAdapter(adapter);

        scoreDao = ScoreDao.getInstance(this);

        voltar = (Button) findViewById(R.id.btnVoltarMenu);

        updateList();
    }

    private void updateList(){
        List<Jogador> ranking = scoreDao.list();
        adapter.setItems(ranking);
    }

    public void voltarMenu(View view){
        Intent voltaMenu = new Intent(this,menuForca.class);
        startActivity(voltaMenu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.hall__of__fame, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
