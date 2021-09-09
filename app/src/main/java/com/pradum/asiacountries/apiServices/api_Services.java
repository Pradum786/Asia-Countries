package com.pradum.asiacountries.apiServices;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface api_Services {


    @GET("{asia}")
    Call<String> getCountries(@Path("asia") String Asia);

}
