package de.geosphere.newhomeworkbook.datamanagement.test;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import de.geosphere.newhomeworkbook.R;

class TeacherViewHolder extends RecyclerView.ViewHolder {
    private final TextView wordItemView;

    private TeacherViewHolder(View itemView) {
        super(itemView);
        wordItemView = itemView.findViewById(R.id.textView);
    }

    public void bind(String text) {
        wordItemView.setText(text);
    }

    static TeacherViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_item, parent, false);
        return new TeacherViewHolder(view);
    }
}