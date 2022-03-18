package de.geosphere.newhomeworkbook.datamanagement.test;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import de.geosphere.newhomeworkbook.datamanagement.Teacher;

public class TeacherListAdapter extends ListAdapter<Teacher, TeacherViewHolder> {


    public TeacherListAdapter(@NonNull DiffUtil.ItemCallback<Teacher> diffCallback) {
        super(diffCallback);
    }

    @Override
    public TeacherViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return TeacherViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(TeacherViewHolder holder, int position) {
        Teacher current = getItem(position);
        holder.bind(current.lastName);
    }

    public static class TeacherDiff extends DiffUtil.ItemCallback<Teacher> {

        @Override
        public boolean areItemsTheSame(@NonNull Teacher oldItem, @NonNull Teacher newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Teacher oldItem, @NonNull Teacher newItem) {
            return oldItem.lastName.equals(newItem.lastName);
        }
    }
}