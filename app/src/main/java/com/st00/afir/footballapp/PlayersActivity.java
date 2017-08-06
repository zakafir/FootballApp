package com.st00.afir.footballapp;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import java.util.List;

public class PlayersActivity extends AppCompatActivity {

    private static final String LOG_TAG = Utils.class.getSimpleName();

    /**
     * URL for players data
     */
    private static final String FOOTBALL_REQUEST_URL = "http://api.football-data.org/v1/teams/87/players";

    private ListView myListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_players);

        PlayersAsyncTask playersAsyncTask = new PlayersAsyncTask();
        playersAsyncTask.execute(FOOTBALL_REQUEST_URL);

        myListView = (ListView) findViewById(R.id.list_view_players);

    }

    private class PlayersAsyncTask extends AsyncTask<String, Void, List<Player>> {

        @Override
        protected List<Player> doInBackground(String... strings) {
            if (strings.length < 1 || strings[0] == null) {
                return null;
            }
            List<Player> list = Utils.fetchPlayersData(strings[0]);
            return list;
        }

        @Override
        protected void onPostExecute(List<Player> list) {
            if (list == null) {
                return;
            }
            PlayersAdapter<Player> adapter = new PlayersAdapter<>(PlayersActivity.this, list);
            myListView.setAdapter(adapter);
            Log.e(LOG_TAG, "setting the adapter");
        }
    }
}
