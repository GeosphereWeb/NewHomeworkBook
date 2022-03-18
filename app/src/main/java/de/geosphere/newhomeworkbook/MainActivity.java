package de.geosphere.newhomeworkbook;

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
import androidx.room.Room;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.YearMonth;
import java.util.ArrayList;

import de.geosphere.newhomeworkbook.calendar.model.ModelSupportClass;
import de.geosphere.newhomeworkbook.calendar.model.Monthmodel;
import de.geosphere.newhomeworkbook.calendar.model.Weekmodel;
import de.geosphere.newhomeworkbook.databinding.ActivityMainBinding;
import de.geosphere.newhomeworkbook.datamanagement.AppDatabase;
import de.geosphere.newhomeworkbook.datamanagement.Teacher;
import de.geosphere.newhomeworkbook.datamanagement.TeacherDao;

public class MainActivity extends AppCompatActivity {
    @NonNull
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ModelSupportClass instance = ModelSupportClass.INSTANCE;
        ArrayList<DayOfWeek> wochentagsnamen = Weekmodel.getDayOfWeekAsSortedList();
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


        // TEST

//        AppDatabase db = AppDatabase.getInstance(getApplicationContext());
//        Teacher teacher = new Teacher();
//        teacher.firstName = "Werner";
//        teacher.lastName = "Schröter";
//
//        TeacherDao teacherDAO = db.teacherDao();
//        teacherDAO.insertTeacher(teacher);
//
//        Log.i("WER", "onCreate: ");

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