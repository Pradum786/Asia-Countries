package com.pradum.asiacountries.roomdb;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.pradum.asiacountries.model.Converters;
import com.pradum.asiacountries.model.Country;


@Database(entities = {Country.class}, version = 1,exportSchema = false)
@TypeConverters(Converters.class)
public abstract class roomDB extends RoomDatabase {




    public  synchronized static   roomDB getInstance(Context context){


        return   Room.databaseBuilder(context.getApplicationContext(),
                    roomDB.class, "database").allowMainThreadQueries().build();



    }
    public  abstract CountryDao countryDao();

}
