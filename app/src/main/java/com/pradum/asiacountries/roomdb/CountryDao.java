package com.pradum.asiacountries.roomdb;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.pradum.asiacountries.model.Country;

import java.util.List;

@Dao
public interface CountryDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
   void insertall(Country country);



    @Query("SELECT * FROM Country")
    List<Country> getAll();



    @Query("DELETE FROM Country")
    void delete();

}
