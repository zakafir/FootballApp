package com.st00.afir.footballapp;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class PlayersActivity extends AppCompatActivity {

    private static final String LOG_TAG = Utils.class.getSimpleName();

    /**
     * URL for players data
     */
    private static final String FOOTBALL_REQUEST_URL = "http://api.football-data.org/v1/teams/86/players";

    private ListView myListView;
    private PlayersAdapter<Player> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_players);

        myListView = (ListView) findViewById(R.id.list_view_players);
        adapter = new PlayersAdapter<>(PlayersActivity.this, new ArrayList<Player>());
        myListView.setAdapter(adapter);

        PlayersAsyncTask playersAsyncTask = new PlayersAsyncTask();
        playersAsyncTask.execute(FOOTBALL_REQUEST_URL);

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
            // Clear the adapter of previous data
            adapter.clear();

            // If there is a valid list of {@link Player}s, then add them to the adapter's
            // data set. This will trigger the ListView to update.
            if (list != null && !list.isEmpty()) {
                adapter.addAll(list);
            }
        }
    }
}
