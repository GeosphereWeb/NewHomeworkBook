package de.geosphere.newhomeworkbook.datamanagement;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;

@Dao
public interface TeacherDao {

    @Query("SELECT * FROM teacher")
    LiveData<List<Teacher>> getAllTeachers();

    @Query("SELECT * FROM teacher WHERE uid IN (:teacherIds)")
    LiveData<List<Teacher>> loadAllByIds(int[] teacherIds);

    @Query("SELECT * FROM teacher WHERE first_name LIKE :first AND last_name LIKE :last LIMIT 1")
    Teacher findByNam(String first, String last);

//    @Insert
//    void insertAll(Teacher... teachers);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertTeacher(Teacher teacher);

    @Delete
    void delete(Teacher teacher);

}
