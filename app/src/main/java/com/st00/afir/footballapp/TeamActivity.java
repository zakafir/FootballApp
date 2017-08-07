package com.st00.afir.footballapp;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;

public class TeamActivity extends AppCompatActivity {

    private static final String LOG_TAG = Utils.class.getSimpleName();
    /**
     * URL for players data, you have to add the number after teams/
     */
    private static String TEAMS_REQUEST_URL = "http://api.football-data.org/v1/teams/";
    private static List<String> listOfURLs = new ArrayList<>();

    private GridView myGridView;
    private TeamAdapter teamAdapter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grid_view_teams);

        myGridView = (GridView) findViewById(R.id.grid_view_teams);
        teamAdapter = new TeamAdapter(TeamActivity.this, new ArrayList<Player>());
        myGridView.setAdapter(teamAdapter);

        TeamsAsyncTask teamsAsyncTask = new TeamsAsyncTask();
        listOfURLs = preparingListOfURLs(TEAMS_REQUEST_URL);
        teamsAsyncTask.execute(listOfURLs);

    }

    private List<String> preparingListOfURLs(String urls) {
        List<String> list = new ArrayList<>();
        for (int i = 80; i <= 91; ++i) {
            String s = urls.concat(String.valueOf(i));
            list.add(s);
        }
        return list;
    }

    private class TeamsAsyncTask extends AsyncTask<List<String>, Void, List<Team>> {

        @Override
        protected List<Team> doInBackground(List<String>... strings) {

            if (strings.length < 1 || strings[0] == null) {
                return null;
            }

            List<Team> team = new ArrayList<>();
            for (int i = 0; i < strings.length; ++i) {
                team = Utils.fetchTeamsData(strings[i]);
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return team;
        }

        @Override
        protected void onPostExecute(List<Team> list) {
            teamAdapter.clear();
            if (list != null && !list.isEmpty()) {
                teamAdapter.addAll(list);
            }
        }
    }
}
