/*
package com.st00.afir.footballapp;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

*/
/**
 * Created by zakaria_afir on 09/08/2017.
 *//*


public class TeamLoader extends AsyncTaskLoader<List<Team>> {

    private static final String LOG_TAG = TeamLoader.class.getName();
    private List<String> mUrls;


    public TeamLoader(Context context, List<String> url) {
        super(context);
        this.mUrls = new ArrayList<>();
        this.mUrls.addAll(url);
        Log.d(LOG_TAG, "the urls are "+ this.mUrls);
    }

    @Override
    public List<Team> loadInBackground() {

        if (mUrls.size() < 1 || mUrls.get(0) == null) {
            return null;
        }

        List<Team> team = new ArrayList<>();
        for (int i = 0; i < mUrls.size(); ++i) {
            team = Utils.fetchTeamsData(mUrls);
        }
        Log.d(LOG_TAG, "Load in background : "+ team.toString());
        return team;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }
}
*/
