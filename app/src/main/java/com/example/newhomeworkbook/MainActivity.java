package com.example.newhomeworkbook;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.newhomeworkbook.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {
    @NonNull
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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