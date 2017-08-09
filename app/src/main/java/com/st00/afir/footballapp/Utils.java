package com.st00.afir.footballapp;

import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zakaria_afir on 06/08/2017.
 */

public final class Utils {

    private static final String LOG_TAG = Utils.class.getSimpleName();

    private static final String AUTH_TOKEN = "7dbc0bb8c49d40bcb290f09ef3908cdb";

    public static List<Player> fetchPlayersData(String requestUrl) {
        // Create URL object
        URL url = createUrl(requestUrl);

        // Perform HTTP request to the URL and receive a JSON response back
        String jsonResponse = null;
        try {
            jsonResponse = makeHttpRequest(url);
        } catch (IOException e) {
            Log.e(LOG_TAG, "Error closing input stream", e);
        }

        // Extract relevant fields from the JSON response and create an {@link Players} object
        List<Player> players = extractPlayerInformationFromJson(jsonResponse);

        // Return the {@link Players}
        return players;
    }

    /**
     * fetching team data
     */
    private static List<Team> teams = new ArrayList<>();

    public static List<Team> fetchTeamsData(List<String> requestUrl) {

        for (String u : requestUrl) {

            URL url = createUrl(u);

            // Perform HTTP request to the URL and receive a JSON response back
            String jsonResponse = null;
            try {
                jsonResponse = makeHttpRequest(url);
            } catch (IOException e) {
                Log.e(LOG_TAG, "Error closing input stream", e);
            }

            // Extract relevant fields from the JSON response and create an {@link Players} object
            teams.add(extractTeamInformationFromJson(jsonResponse));
        }
        // Return the {@link Players}
        return teams;

    }

    /**
     * Returns new URL object from the given string URL.
     */
    private static URL createUrl(String stringUrl) {
        URL url = null;
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException e) {
            Log.e(LOG_TAG, "Error with creating URL ", e);
        }
        return url;
    }

    /**
     * Make an HTTP request to the given URL and return a String as the response.
     */
    private static String makeHttpRequest(URL url) throws IOException {
        String jsonResponse = "";

        // If the URL is null, then return early.
        if (url == null) {
            return jsonResponse;
        }

        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(10000 /* milliseconds */);
            urlConnection.setConnectTimeout(20000 /* milliseconds */);
            urlConnection.setRequestMethod("GET");
            urlConnection.setRequestProperty("X-Auth-Token", AUTH_TOKEN);
            urlConnection.connect();

            // If the request was successful (response code 200),
            // then read the input stream and parse the response.
            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } else {
                Log.e(LOG_TAG, "Error response code: " + urlConnection.getResponseCode());
            }
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem retrieving the player JSON results.", e);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return jsonResponse;
    }

    /**
     * Convert the {@link InputStream} into a String which contains the
     * whole JSON response from the server.
     */
    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }

    /**
     * Return an {@link Player} object by parsing out information
     */
    private static List<Player> extractPlayerInformationFromJson(String playerJSON) {
        // If the JSON string is empty or null, then return early.
        if (TextUtils.isEmpty(playerJSON)) {
            return null;
        }
        List<Player> listOfPlayers = new ArrayList<>();
        try {
            JSONArray players = new JSONObject(playerJSON).optJSONArray("players");
            for (int i = 0; i < players.length(); ++i) {
                JSONObject entry = players.optJSONObject(i);
                listOfPlayers.add(new Player(entry.getInt("jerseyNumber"),
                        entry.getString("name"),
                        entry.getString("nationality"),
                        entry.getString("dateOfBirth"),
                        entry.getString("position")));
            }

        } catch (JSONException e) {
            Log.e(LOG_TAG, "Error on JSON: " + e);
        }
        return listOfPlayers;
    }

    private static Team extractTeamInformationFromJson(String playerJSON) {
        // If the JSON string is empty or null, then return early.
        if (TextUtils.isEmpty(playerJSON)) {
            return null;
        }
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(playerJSON);
            //String teamLogo = jsonObject.optString("crestUrl");

        } catch (JSONException e) {
            Log.e(LOG_TAG, "Error on JSON: " + e);
        }
        return new Team(jsonObject.optString("name"), jsonObject.optString("crestUrl"));
    }
}