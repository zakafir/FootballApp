package com.st00.afir.footballapp;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zakaria_afir on 31/07/2017.
 */

public class QueryUtils {

    private final static String TAG_LOG = QueryUtils.class.getSimpleName();
    private final static String URL_JSON = "{\"_links\":{\"self\":{\"href\":\"http://api.football-data.org/v1/teams/86/players\"},\"team\":{\"href\":\"http://api.football-data.org/v1/teams/86\"}},\"count\":22,\"players\":[{\"name\":\"Keylor Navas\",\"position\":\"Keeper\",\"jerseyNumber\":1,\"dateOfBirth\":\"1986-12-15\",\"nationality\":\"Costa Rica\",\"contractUntil\":\"2020-06-30\",\"marketValue\":null},{\"name\":\"Kiko Casilla\",\"position\":\"Keeper\",\"jerseyNumber\":13,\"dateOfBirth\":\"1986-10-02\",\"nationality\":\"Spain\",\"contractUntil\":\"2020-06-30\",\"marketValue\":null},{\"name\":\"Rubén Yáñez\",\"position\":\"Keeper\",\"jerseyNumber\":25,\"dateOfBirth\":\"1993-10-12\",\"nationality\":\"Spain\",\"contractUntil\":\"2018-06-30\",\"marketValue\":null},{\"name\":\"Sergio Ramos\",\"position\":\"Centre-Back\",\"jerseyNumber\":4,\"dateOfBirth\":\"1986-03-30\",\"nationality\":\"Spain\",\"contractUntil\":\"2020-06-30\",\"marketValue\":null},{\"name\":\"Raphaël Varane\",\"position\":\"Centre-Back\",\"jerseyNumber\":5,\"dateOfBirth\":\"1993-04-25\",\"nationality\":\"France\",\"contractUntil\":\"2020-06-30\",\"marketValue\":null},{\"name\":\"Nacho Fernández\",\"position\":\"Centre-Back\",\"jerseyNumber\":6,\"dateOfBirth\":\"1990-01-18\",\"nationality\":\"Spain\",\"contractUntil\":\"2020-06-30\",\"marketValue\":null},{\"name\":\"Pepe\",\"position\":\"Centre-Back\",\"jerseyNumber\":3,\"dateOfBirth\":\"1983-02-26\",\"nationality\":\"Portugal\",\"contractUntil\":\"2017-06-30\",\"marketValue\":null},{\"name\":\"Fábio Coentrão\",\"position\":\"Left-Back\",\"jerseyNumber\":15,\"dateOfBirth\":\"1988-03-11\",\"nationality\":\"Portugal\",\"contractUntil\":\"2019-06-30\",\"marketValue\":null},{\"name\":\"Daniel Carvajal\",\"position\":\"Right-Back\",\"jerseyNumber\":2,\"dateOfBirth\":\"1992-01-11\",\"nationality\":\"Spain\",\"contractUntil\":\"2020-06-30\",\"marketValue\":null},{\"name\":\"Casemiro\",\"position\":\"Defensive Midfield\",\"jerseyNumber\":14,\"dateOfBirth\":\"1992-02-23\",\"nationality\":\"Brazil\",\"contractUntil\":\"2021-06-30\",\"marketValue\":null},{\"name\":\"Toni Kroos\",\"position\":\"Central Midfield\",\"jerseyNumber\":8,\"dateOfBirth\":\"1990-01-04\",\"nationality\":\"Germany\",\"contractUntil\":\"2022-06-30\",\"marketValue\":null},{\"name\":\"Luka Modric\",\"position\":\"Central Midfield\",\"jerseyNumber\":19,\"dateOfBirth\":\"1985-09-09\",\"nationality\":\"Croatia\",\"contractUntil\":\"2020-06-30\",\"marketValue\":null},{\"name\":\"Mateo Kovacic\",\"position\":\"Central Midfield\",\"jerseyNumber\":16,\"dateOfBirth\":\"1994-05-06\",\"nationality\":\"Croatia\",\"contractUntil\":\"2021-06-30\",\"marketValue\":null},{\"name\":\"James Rodríguez\",\"position\":\"Attacking Midfield\",\"jerseyNumber\":10,\"dateOfBirth\":\"1991-07-12\",\"nationality\":\"Colombia\",\"contractUntil\":\"2020-06-30\",\"marketValue\":null},{\"name\":\"Isco\",\"position\":\"Attacking Midfield\",\"jerseyNumber\":22,\"dateOfBirth\":\"1992-04-21\",\"nationality\":\"Spain\",\"contractUntil\":\"2018-06-30\",\"marketValue\":null},{\"name\":\"Cristiano Ronaldo\",\"position\":\"Left Wing\",\"jerseyNumber\":7,\"dateOfBirth\":\"1985-02-05\",\"nationality\":\"Portugal\",\"contractUntil\":\"2021-06-30\",\"marketValue\":null},{\"name\":\"Marco Asensio\",\"position\":\"Left Wing\",\"jerseyNumber\":20,\"dateOfBirth\":\"1996-01-21\",\"nationality\":\"Spain\",\"contractUntil\":\"2022-06-30\",\"marketValue\":null},{\"name\":\"Gareth Bale\",\"position\":\"Right Wing\",\"jerseyNumber\":11,\"dateOfBirth\":\"1989-07-16\",\"nationality\":\"Wales\",\"contractUntil\":\"2022-06-30\",\"marketValue\":null},{\"name\":\"Lucas Vázquez\",\"position\":\"Right Wing\",\"jerseyNumber\":17,\"dateOfBirth\":\"1991-07-01\",\"nationality\":\"Spain\",\"contractUntil\":\"2021-06-30\",\"marketValue\":null},{\"name\":\"Karim Benzema\",\"position\":\"Centre-Forward\",\"jerseyNumber\":9,\"dateOfBirth\":\"1987-12-19\",\"nationality\":\"France\",\"contractUntil\":\"2019-06-30\",\"marketValue\":null},{\"name\":\"Álvaro Morata\",\"position\":\"Centre-Forward\",\"jerseyNumber\":21,\"dateOfBirth\":\"1992-10-23\",\"nationality\":\"Spain\",\"contractUntil\":\"2021-06-30\",\"marketValue\":null},{\"name\":\"Mariano Díaz\",\"position\":\"Centre-Forward\",\"jerseyNumber\":18,\"dateOfBirth\":\"1993-08-01\",\"nationality\":\"Dominican Republic\",\"contractUntil\":\"2021-06-30\",\"marketValue\":null}]}";

    public QueryUtils() {
    }

    public List<Player> extractPlayers() {
        List<Player> listOfPlayers = new ArrayList<>();
        try {
            JSONArray players = new JSONObject(URL_JSON).optJSONArray("players");
            for (int i = 0; i < players.length(); ++i) {
                JSONObject entry = players.optJSONObject(i);
                listOfPlayers.add(new Player(entry.getInt("jerseyNumber"),
                        entry.getString("name"),
                        entry.getString("nationality"),
                        entry.getString("dateOfBirth"),
                        entry.getString("position")));
            }

        } catch (JSONException e) {
            Log.e(TAG_LOG,"Error on JSON: "+e);
        }
        return listOfPlayers;
    }
}
