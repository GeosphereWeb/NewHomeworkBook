package de.geosphere.newhomeworkbook.datamanagement;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

class TeacherRepository {

    private TeacherDao mTeacherDao;
    private LiveData<List<Teacher>> mAllTeachers;

    // Note that in order to unit test the WordRepository, you have to remove the Application
    // dependency. This adds complexity and much more code, and this sample is not about testing.
    // See the BasicSample in the android-architecture-components repository at
    // https://github.com/googlesamples
    TeacherRepository(Application application) {
        AppDatabase db = AppDatabase.getInstance(application);
        mTeacherDao = db.teacherDao();
        mAllTeachers = mTeacherDao.getAllTeachers();
    }

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    LiveData<List<Teacher>> getAllTeachers() {
        return mAllTeachers;
    }

    // You must call this on a non-UI thread or your app will throw an exception. Room ensures
    // that you're not doing any long running operations on the main thread, blocking the UI.
    void insert(Teacher teacher) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            mTeacherDao.insertTeacher(teacher);
        });
    }
}