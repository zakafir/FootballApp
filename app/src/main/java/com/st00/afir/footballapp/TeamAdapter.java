package com.st00.afir.footballapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by zakaria_afir on 06/08/2017.
*/


class TeamAdapter<T> extends ArrayAdapter{

    public TeamAdapter(@NonNull Context context, @NonNull List objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View gridItemView = convertView;

        if (gridItemView == null) {
            gridItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.team_items, parent, false);
        }

        Team currentTeam = (Team) getItem(position);
        TextView nameTeamTextView = gridItemView.findViewById(R.id.team_name);
        if(!currentTeam.getName().isEmpty() || currentTeam.getName() != null){
            nameTeamTextView.setText(""+currentTeam.getName());
        }

        ImageView imageTeamView = (ImageView) gridItemView.findViewById(R.id.team_image);
        //imageTeamView.setImageResource(currentTeam.getLogoUrl());
        imageTeamView.setVisibility(View.VISIBLE);

        return gridItemView;
    }
}
