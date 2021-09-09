package com.pradum.asiacountries.apiServices;

import retrofit2.Retrofit;
import retrofit2.Retrofit.Builder;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class api_caller {

    public static String baseUrl="https://restcountries.eu/rest/v2/region/";

        public static api_Services api_services;
        public  static  api_Services getApi_services(){

            if(api_services==null){

                Retrofit retrofit = new Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(ScalarsConverterFactory.create()).build();

                api_services=retrofit.create(api_Services.class);

            }
return  api_services;

        }

}
