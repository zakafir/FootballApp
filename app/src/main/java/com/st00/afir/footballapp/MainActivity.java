package com.st00.afir.footballapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String manU = "{\"_links\":{\"self\":{\"href\":\"http://api.football-data.org/v1/teams/66\"},\"fixtures\":{\"href\":\"http://api.football-data.org/v1/teams/66/fixtures\"},\"players\":{\"href\":\"http://api.football-data.org/v1/teams/66/players\"}},\"name\":\"Manchester United FC\",\"code\":\"MUFC\",\"shortName\":\"ManU\",\"squadMarketValue\":null,\"crestUrl\":\"http://upload.wikimedia.org/wikipedia/de/d/da/Manchester_United_FC.svg\"}";
        String tottenham = "{\"_links\":{\"self\":{\"href\":\"http://api.football-data.org/v1/teams/73\"},\"fixtures\":{\"href\":\"http://api.football-data.org/v1/teams/73/fixtures\"},\"players\":{\"href\":\"http://api.football-data.org/v1/teams/73/players\"}},\"name\":\"Tottenham Hotspur FC\",\"code\":\"THFC\",\"shortName\":\"Spurs\",\"squadMarketValue\":null,\"crestUrl\":\"http://upload.wikimedia.org/wikipedia/de/b/b4/Tottenham_Hotspur.svg\"}";
        List<String> teams = new ArrayList<String>();

        try {
            teams.add(new JSONObject(manU).getString("name"));
            teams.add(new JSONObject(tottenham).getString("name"));

        } catch (JSONException e) {
            e.printStackTrace();
        }

        // Spinner element
        Spinner spinner = (Spinner) findViewById(R.id.teams_spinner);

        // Spinner click listener
        spinner.setOnItemSelectedListener(this);

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, teams);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        String item = parent.getItemAtPosition(position).toString();

        // Showing selected spinner item
        Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
    }
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }
}
