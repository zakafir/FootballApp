package com.st00.afir.footballapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by zakaria_afir on 30/07/2017.
 */

class PlayersAdapter<T> extends ArrayAdapter {

    public PlayersAdapter(@NonNull Context context, @NonNull List objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View listItemView = convertView;

        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.player_items, parent, false);
        }

        Player currentPlayer = (Player) getItem(position);

        TextView playerNumberTextView = (TextView) listItemView.findViewById(R.id.jersey_number);
        playerNumberTextView.setText("" + currentPlayer.getJerseyNumber());

        TextView playerNameTextView = (TextView) listItemView.findViewById(R.id.player_name);
        playerNameTextView.setText(currentPlayer.getName());

        TextView playerNationalityTextView = (TextView) listItemView.findViewById(R.id.player_natioanlity);
        playerNationalityTextView.setText(currentPlayer.getNationality());

        TextView playerBirthTextView = (TextView) listItemView.findViewById(R.id.player_date_of_birth);
        playerBirthTextView.setText(currentPlayer.getDateOfBirth());

        return listItemView;
    }
}
