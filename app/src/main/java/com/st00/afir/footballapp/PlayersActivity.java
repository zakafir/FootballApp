package com.st00.afir.footballapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.List;

public class PlayersActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_players);

        QueryUtils queryUtils = new QueryUtils();
        List<Player> list = queryUtils.extractPlayers();

        ListView myListView = (ListView) findViewById(R.id.list_view_players);
        PlayersAdapter<Player> adapter = new PlayersAdapter<>(this, list);
        myListView.setAdapter(adapter);
    }
}
