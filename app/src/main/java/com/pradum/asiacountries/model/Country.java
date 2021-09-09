package com.pradum.asiacountries.model;


import android.graphics.Bitmap;

import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "Country", indices = @Index(value = {"name"}, unique = true))
public class Country {


    @PrimaryKey(autoGenerate = true)
    private Integer id ;

    private String name;

    private String capital;

    private String region;

    private  String subregion;

    private  Integer population;

    private String borders;

    private  String languages;

    private Bitmap flag;


    public Bitmap getFlag() {
        return flag;
    }

    public void setFlag(Bitmap flag) {
        this.flag = flag;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getSubregion() {
        return subregion;
    }

    public void setSubregion(String subregion) {
        this.subregion = subregion;
    }

    public Integer getPopulation() {
        return population;
    }

    public void setPopulation(Integer population) {
        this.population = population;
    }


    public String getBorders() {
        return borders;
    }

    public void setBorders(String borders) {
        this.borders = borders;
    }

    public String getLanguages() {
        return languages;
    }

    public void setLanguages(String languages) {
        this.languages = languages;
    }
}
