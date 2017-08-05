package com.st00.afir.footballapp;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
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

    @RequiresApi(api = Build.VERSION_CODES.M)
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

        TextView playerPositionTextView = (TextView) listItemView.findViewById(R.id.player_position);
        playerPositionTextView.setText(currentPlayer.getPosition());

        GradientDrawable numberCircle = (GradientDrawable) playerNumberTextView.getBackground();
        int numberColor = getNumberColor(currentPlayer.getPosition());
        numberCircle.setColor(numberColor);

        return listItemView;
    }

    public int getNumberColor(String position) {
        if (position.equals("Keeper")) {
            return ContextCompat.getColor(getContext(), R.color.keeper);
        } else if (position.contains("Back")) {
            return ContextCompat.getColor(getContext(), R.color.back);
        } else if (position.contains("Defensive Midfield")) {
            return ContextCompat.getColor(getContext(), R.color.defensive_Midfield);
        } else if (position.contains("Central Midfield")) {
            return ContextCompat.getColor(getContext(), R.color.central_Midfield);
        } else if (position.equals("Attacking Midfield")) {
            return ContextCompat.getColor(getContext(), R.color.attacking_Midfield);
        } else if (position.contains("Midfield")) {
            return ContextCompat.getColor(getContext(), R.color.defensive_Midfield);
        } else if (position.contains("Wing")) {
            return ContextCompat.getColor(getContext(), R.color.wing);
        } else if (position.contains("Striker")) {
            return ContextCompat.getColor(getContext(), R.color.secondary_Striker);
        } else {
            return ContextCompat.getColor(getContext(), R.color.centre_Forward);
        }
    }
}
