package com.pradum.asiacountries.model;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import androidx.room.TypeConverter;

import java.io.ByteArrayOutputStream;

public class Converters {

    @TypeConverter
    public  byte[] fromBitmap(Bitmap bitmap) {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, bos);
        byte[] bArray = bos.toByteArray();

        return  bArray;
    }

    @TypeConverter
    public Bitmap tooBitmap(byte[] bArray) {


        Bitmap bitmap = BitmapFactory.decodeByteArray(bArray, 0, bArray.length);
        return bitmap;

    }


}
