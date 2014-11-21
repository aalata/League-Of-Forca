package com.example.tallessiqueira.leagueofforca.application;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.tallessiqueira.leagueofforca.R;


public class menuForca extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Button jogar = (Button) findViewById(R.id.btnJogar);
        Button hallOfFame = (Button) findViewById(R.id.btnHallOfFame);

        jogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goNow = new Intent(getApplicationContext(),MyActivity.class);
                startActivity(goNow);
            }
        });

        hallOfFame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ranking = new Intent(getApplicationContext(),Hall_Of_Fame.class);
                startActivity(ranking);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
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

    @Override
    public void onBackPressed() {
        // Do Here what ever you want do on back press;
    }
}
