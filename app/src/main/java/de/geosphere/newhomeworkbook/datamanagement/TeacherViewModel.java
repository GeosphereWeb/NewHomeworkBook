package de.geosphere.newhomeworkbook.datamanagement;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class TeacherViewModel extends AndroidViewModel {

   private TeacherRepository mRepository;

   private final LiveData<List<Teacher>> mAllTeachers;

   public TeacherViewModel(Application application) {
       super(application);
       mRepository = new TeacherRepository(application);
       mAllTeachers = mRepository.getAllTeachers();
   }

   LiveData<List<Teacher>> getAllWords() { return mAllTeachers; }

   public void insert(Teacher teacher) { mRepository.insert(teacher); }
}