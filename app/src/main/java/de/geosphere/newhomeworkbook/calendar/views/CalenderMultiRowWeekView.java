package de.geosphere.newhomeworkbook.calendar.views;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class CalenderMultiRowWeekView extends RecyclerView {

    private CalenderAdapter calenderAdapter;


    public CalenderMultiRowWeekView(@NonNull Context context) {
        super(context);
        init(context);
    }

    public CalenderMultiRowWeekView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public CalenderMultiRowWeekView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        calenderAdapter = new CalenderAdapter(context);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(context, calenderAdapter.getColumCount(), LinearLayoutManager.VERTICAL, false);
        this.setLayoutManager(gridLayoutManager);

        this.setAdapter(calenderAdapter);
    }

    public void showTest() {
        calenderAdapter.filterModel();
    }
}
