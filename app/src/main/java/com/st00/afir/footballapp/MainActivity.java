package com.st00.afir.footballapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<Player> players = new ArrayList<>();
        players.add(new Player(7, "Cristiano Ronaldo", "Portugal", "feb 1998"));
        players.add(new Player(9, "Karim Benzema", "France", "feb 1998"));
        players.add(new Player(11, "Gareth Bale", "Gallois", "feb 1998"));
        players.add(new Player(10, "Lukas Modric", "Gallois", "feb 1998"));
        players.add(new Player(4, "Sergio Ramos", "Espagne", "feb 1998"));
        players.add(new Player(3, "Raphael Varane", "France", "feb 1998"));

        ListView myListView = (ListView) findViewById(R.id.list_view_players);
        PlayersAdapter<Player> adapter = new PlayersAdapter<Player>(this, players);
        myListView.setAdapter(adapter);
    }
}
