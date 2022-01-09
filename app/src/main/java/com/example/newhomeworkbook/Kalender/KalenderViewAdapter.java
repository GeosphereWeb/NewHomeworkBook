package com.example.newhomeworkbook.Kalender;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newhomeworkbook.R;

public class KalenderViewAdapter extends RecyclerView.Adapter<KalenderViewAdapter.KalenderItemViewHolder> {


    @NonNull
    @Override
    public KalenderItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // diese Methode erzeugt die View
        // LinearLayout inflate = (LinearLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.kalendertag, parent, false);
        // return new KalenderTagViewHolder(inflate);
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull KalenderItemViewHolder holder, int position) {
        // Diese Methode bindet die Infos auf die ItemView

    }

    @Override
    public int getItemCount() {
        // gibt die anzahl der Items zurück
        return 0;
    }

    /**
     * Klasse für den ViewHolder
     * Über den View Holder können auf die Unterelemente in der View zugegriffen und dadurch verändert werden.
     */
    public static class KalenderItemViewHolder extends RecyclerView.ViewHolder {

        public KalenderItemViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
