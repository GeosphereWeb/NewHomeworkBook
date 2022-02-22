package com.example.newhomeworkbook;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.newhomeworkbook.calendar.model.ModelSupportClass;
import com.example.newhomeworkbook.calendar.model.Monthmodel;
import com.example.newhomeworkbook.calendar.model.Weekmodel;
import com.example.newhomeworkbook.databinding.ActivityMainBinding;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.YearMonth;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    @NonNull
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ModelSupportClass instance = ModelSupportClass.INSTANCE;
        ArrayList<DayOfWeek> wochentagsnamen = instance.getDayOfWeekAsSortedList();
        Weekmodel weekmodel = instance.instantiateWeekmodel(2022, LocalDate.now());
        Monthmodel monthmodel = instance.instantiateMonthmodel(YearMonth.of(2022, Month.FEBRUARY));

        Log.i("WERNER", "Test");

        // Inflating XML
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        // Navigation einrichten
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        NavHostFragment navHostFragment = (NavHostFragment) supportFragmentManager.findFragmentById(R.id.fragmentContainerView);
        NavController navController = navHostFragment.getNavController();
        NavigationUI.setupWithNavController(binding.bottomNavId, navController);

        // Action bar
        setSupportActionBar(binding.toolbarId);
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();



//        binding.bottomNav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                switch (item.getItemId()) {
//                    case R.id.home_menue_item:
////                        Toast.makeText(MainActivity.this, "home_menue_item", Toast.LENGTH_SHORT).show();
//                        navController.navigate(R.id.action_mainFragment_to_schedulePlanFragment);
//                    case R.id.schedule_menue_item:
////                        Toast.makeText(MainActivity.this, "schedule_menue_item", Toast.LENGTH_SHORT).show();
//                        navController.navigate(R.id.action_mainFragment_to_schedulePlanFragment);
//                    case R.id.schoolsubject_menue_item:
////                        Toast.makeText(MainActivity.this, "schoolsubject_menue_item", Toast.LENGTH_SHORT).show();
//                        navController.navigate(R.id.action_mainFragment_to_schoolsubjectFragment);
//                    default:
//                        //
//                }
//
//                return false;
//            }
//        });



    }
}