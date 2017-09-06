package com.st00.afir.footballapp;

import android.app.LoaderManager;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class PlayersActivity extends AppCompatActivity implements LoaderCallbacks<List<Player>>{

    private static final String LOG_TAG = PlayersActivity.class.getSimpleName();

    /**
     * Constant value for the player loader ID. We can choose any integer.
     * This really only comes into play if you're using multiple loaders.
     */
    private static final int PLAYER_LOADER_ID = 1;

    /**
     * URL for players data
     */
    private static final String FOOTBALL_REQUEST_URL = "http://api.football-data.org/v1/teams/86/players";

    private ListView myListView;
    private PlayersAdapter<Player> adapter;
    private TextView noDataTextView;
    private ProgressBar myProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_view_players);

        myListView = (ListView) findViewById(R.id.list_view_players);
        adapter = new PlayersAdapter<>(PlayersActivity.this, new ArrayList<Player>());
        myListView.setAdapter(adapter);

        noDataTextView = (TextView)findViewById(R.id.message_no_data);
        myListView.setEmptyView(noDataTextView);

        myProgressBar = (ProgressBar)findViewById(R.id.loading_spinner);

        ConnectivityManager cm =
                (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();

        if(isConnected) {
            // Get a reference to the LoaderManager, in order to interact with loaders.
            LoaderManager loaderManager = getLoaderManager();
            loaderManager.initLoader(PLAYER_LOADER_ID, null, PlayersActivity.this);
            Log.d(LOG_TAG, "Loader initialized");
        }
        else {
            noDataTextView.setText("No INTERNET connexion");
            myProgressBar.setVisibility(View.GONE);
        }

    }

    @Override
    public Loader<List<Player>> onCreateLoader(int id, Bundle args) {
        Log.d(LOG_TAG,"Loader created");

        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);

        String minNumber = sharedPrefs.getString(
                getString(R.string.settings_min_number_team_key),
                getString(R.string.settings_min_number_team_default));

        Uri baseUri = Uri.parse(FOOTBALL_REQUEST_URL);
        Uri.Builder uriBuilder = baseUri.buildUpon();
        uriBuilder.appendQueryParameter("", minNumber);
        uriBuilder.appendQueryParameter("", "players");

        return new PlayerLoader(this,FOOTBALL_REQUEST_URL);
    }

    @Override
    public void onLoadFinished(Loader<List<Player>> loader, List<Player> players) {
        // Clear the adapter of previous data
        adapter.clear();

        // If there is a valid list of {@link Player}s, then add them to the adapter's
        // data set. This will trigger the ListView to update.
        if (players != null && !players.isEmpty()) {
            adapter.addAll(players);
        }
        Log.d(LOG_TAG,"Loader Finished Loading");
        myProgressBar.setVisibility(View.GONE);
        noDataTextView.setText("No players");
    }

    @Override
    public void onLoaderReset(Loader<List<Player>> loader) {
        Log.d(LOG_TAG,"Loader reset");
        adapter.clear();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            Intent settingsIntent = new Intent(this, SettingsActivity.class);
            startActivity(settingsIntent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
