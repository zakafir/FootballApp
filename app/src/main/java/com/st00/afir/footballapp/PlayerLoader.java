package com.st00.afir.footballapp;

import android.content.Context;
import android.content.AsyncTaskLoader;
import java.util.List;

/**
 * Created by zakaria_afir on 07/08/2017.
 */

public class PlayerLoader extends AsyncTaskLoader<List<Player>> {

    private static final String LOG_TAG = PlayerLoader.class.getName();
    private String mUrl;


    public PlayerLoader(Context context,String url) {
        super(context);
        this.mUrl = url;
    }

    @Override
    public List<Player> loadInBackground() {
        if (mUrl == null) {
            return null;
        }
        List<Player> list = Utils.fetchPlayersData(mUrl);
        return list;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }
}
