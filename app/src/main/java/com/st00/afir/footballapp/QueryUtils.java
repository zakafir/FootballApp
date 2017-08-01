package com.st00.afir.footballapp;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zakaria_afir on 31/07/2017.
 */

public class QueryUtils {

    private final static String URL_JSON = "{\"_links\":{\"self\":{\"href\":\"http://api.football-data.org/v1/teams/66/listOfPlayers\"},\"team\":{\"href\":\"http://api.football-data.org/v1/teams/66\"}},\"count\":24,\"listOfPlayers\":[{\"name\":\"David de Gea\",\"position\":\"Keeper\",\"jerseyNumber\":1,\"dateOfBirth\":\"1990-11-07\",\"nationality\":\"Spain\",\"contractUntil\":\"2019-06-30\",\"marketValue\":null},{\"name\":\"Sergio Romero\",\"position\":\"Keeper\",\"jerseyNumber\":20,\"dateOfBirth\":\"1987-02-22\",\"nationality\":\"Argentina\",\"contractUntil\":\"2018-06-30\",\"marketValue\":null},{\"name\":\"Eric Bailly\",\"position\":\"Centre-Back\",\"jerseyNumber\":3,\"dateOfBirth\":\"1994-04-12\",\"nationality\":\"Cote d'Ivoire\",\"contractUntil\":\"2020-06-30\",\"marketValue\":null},{\"name\":\"Chris Smalling\",\"position\":\"Centre-Back\",\"jerseyNumber\":12,\"dateOfBirth\":\"1989-11-22\",\"nationality\":\"England\",\"contractUntil\":\"2019-06-30\",\"marketValue\":null},{\"name\":\"Marcos Rojo\",\"position\":\"Centre-Back\",\"jerseyNumber\":5,\"dateOfBirth\":\"1990-03-20\",\"nationality\":\"Argentina\",\"contractUntil\":\"2019-06-30\",\"marketValue\":null},{\"name\":\"Phil Jones\",\"position\":\"Centre-Back\",\"jerseyNumber\":4,\"dateOfBirth\":\"1992-02-21\",\"nationality\":\"England\",\"contractUntil\":\"2019-06-30\",\"marketValue\":null},{\"name\":\"Daley Blind\",\"position\":\"Left-Back\",\"jerseyNumber\":17,\"dateOfBirth\":\"1990-03-09\",\"nationality\":\"Netherlands\",\"contractUntil\":\"2018-06-30\",\"marketValue\":null},{\"name\":\"Luke Shaw\",\"position\":\"Left-Back\",\"jerseyNumber\":23,\"dateOfBirth\":\"1995-07-12\",\"nationality\":\"England\",\"contractUntil\":\"2018-06-30\",\"marketValue\":null},{\"name\":\"Matteo Darmian\",\"position\":\"Right-Back\",\"jerseyNumber\":36,\"dateOfBirth\":\"1989-12-02\",\"nationality\":\"Italy\",\"contractUntil\":\"2019-06-30\",\"marketValue\":null},{\"name\":\"Antonio Valencia\",\"position\":\"Right-Back\",\"jerseyNumber\":25,\"dateOfBirth\":\"1985-08-04\",\"nationality\":\"Ecuador\",\"contractUntil\":\"2018-06-30\",\"marketValue\":null},{\"name\":\"Michael Carrick\",\"position\":\"Defensive Midfield\",\"jerseyNumber\":16,\"dateOfBirth\":\"1981-07-28\",\"nationality\":\"England\",\"contractUntil\":\"2017-06-30\",\"marketValue\":null},{\"name\":\"Timothy Fosu-Mensah\",\"position\":\"Defensive Midfield\",\"jerseyNumber\":24,\"dateOfBirth\":\"1998-01-02\",\"nationality\":\"Netherlands\",\"contractUntil\":\"2020-06-30\",\"marketValue\":null},{\"name\":\"Paul Pogba\",\"position\":\"Central Midfield\",\"jerseyNumber\":6,\"dateOfBirth\":\"1993-03-15\",\"nationality\":\"France\",\"contractUntil\":\"2021-06-30\",\"marketValue\":null},{\"name\":\"Ander Herrera\",\"position\":\"Central Midfield\",\"jerseyNumber\":21,\"dateOfBirth\":\"1989-08-14\",\"nationality\":\"Spain\",\"contractUntil\":\"2018-06-30\",\"marketValue\":null},{\"name\":\"Marouane Fellaini\",\"position\":\"Central Midfield\",\"jerseyNumber\":27,\"dateOfBirth\":\"1987-11-22\",\"nationality\":\"Belgium\",\"contractUntil\":\"2018-06-30\",\"marketValue\":null},{\"name\":\"Ashley Young\",\"position\":\"Left Midfield\",\"jerseyNumber\":18,\"dateOfBirth\":\"1985-07-09\",\"nationality\":\"England\",\"contractUntil\":\"2018-06-30\",\"marketValue\":null},{\"name\":\"Henrikh Mkhitaryan\",\"position\":\"Attacking Midfield\",\"jerseyNumber\":22,\"dateOfBirth\":\"1989-01-21\",\"nationality\":\"Armenia\",\"contractUntil\":\"2020-06-30\",\"marketValue\":null},{\"name\":\"Juan Mata\",\"position\":\"Attacking Midfield\",\"jerseyNumber\":8,\"dateOfBirth\":\"1988-04-28\",\"nationality\":\"Spain\",\"contractUntil\":\"2018-06-30\",\"marketValue\":null},{\"name\":\"Jesse Lingard\",\"position\":\"Left Wing\",\"jerseyNumber\":14,\"dateOfBirth\":\"1992-12-15\",\"nationality\":\"England\",\"contractUntil\":\"2021-06-30\",\"marketValue\":null},{\"name\":\"Wayne Rooney\",\"position\":\"Secondary Striker\",\"jerseyNumber\":10,\"dateOfBirth\":\"1985-10-24\",\"nationality\":\"England\",\"contractUntil\":\"2019-06-30\",\"marketValue\":null},{\"name\":\"Anthony Martial\",\"position\":\"Centre-Forward\",\"jerseyNumber\":11,\"dateOfBirth\":\"1995-12-05\",\"nationality\":\"France\",\"contractUntil\":\"2019-06-30\",\"marketValue\":null},{\"name\":\"Zlatan Ibrahimovic\",\"position\":\"Centre-Forward\",\"jerseyNumber\":9,\"dateOfBirth\":\"1981-10-03\",\"nationality\":\"Sweden\",\"contractUntil\":\"2017-06-30\",\"marketValue\":null},{\"name\":\"Marcus Rashford\",\"position\":\"Centre-Forward\",\"jerseyNumber\":19,\"dateOfBirth\":\"1997-10-31\",\"nationality\":\"England\",\"contractUntil\":\"2020-06-30\",\"marketValue\":null},{\"name\":\"James Wilson\",\"position\":\"Centre-Forward\",\"jerseyNumber\":49,\"dateOfBirth\":\"1995-12-01\",\"nationality\":\"England\",\"contractUntil\":\"2019-06-30\",\"marketValue\":null}]}";

    public QueryUtils() {
    }

    public List<Player> extractPlayers() {
        List<Player> listOfPlayers = new ArrayList<>();
        try {
            JSONArray players = new JSONObject(URL_JSON).optJSONArray("listOfPlayers");
            for (int i = 0; i < players.length(); ++i) {
                JSONObject entry = players.optJSONObject(i);
                listOfPlayers.add(new Player(entry.getInt("jerseyNumber"),
                        entry.getString("name"),
                        entry.getString("nationality"),
                        entry.getString("dateOfBirth")));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return listOfPlayers;
    }
}
