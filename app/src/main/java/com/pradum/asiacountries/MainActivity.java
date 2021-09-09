package com.pradum.asiacountries;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.caverock.androidsvg.SVG;
import com.caverock.androidsvg.SVGParseException;
import com.pradum.asiacountries.Adapter.CountryAdapter;
import com.pradum.asiacountries.apiServices.api_caller;
import com.pradum.asiacountries.model.Country;
import com.pradum.asiacountries.roomdb.roomDB;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

   private roomDB mroomDB;
    private CountryAdapter listAdapter;
    private RecyclerView recycler;
    ProgressDialog pd;
    Button delete;
    List<Country> country1 = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         pd = new ProgressDialog(this);
        pd.setMessage("Please Wait..");
        mroomDB= roomDB.getInstance(MainActivity.this);
         delete=findViewById(R.id.delete);

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mroomDB.countryDao().delete();

            }
        });


        recycler = findViewById(R.id.detail);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recycler.setLayoutManager(layoutManager);



        ConnectivityManager cm = (ConnectivityManager)getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo nInfo = cm.getActiveNetworkInfo();
        boolean connected = nInfo != null && nInfo.isAvailable() && nInfo.isConnected();

        if (connected) {

            getData();
        }else {
            pd.show();
            Toast.makeText(this, "No Internet Conection", Toast.LENGTH_LONG).show();
           country1 = mroomDB.countryDao().getAll();
           showdata(country1);


        }



    }

    private void getData() {

        pd.show();

        Call<String> call =api_caller.getApi_services().getCountries("Asia");

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

                new data().execute(response.body());

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });


    }


    public class data extends AsyncTask<String, Void, String> {


        @Override
        protected String doInBackground(String... strings) {


            try {
                JSONArray jsonArray=new JSONArray(strings[0]);

                Country country=new Country();

                for (int i =0; i<jsonArray.length(); i++) {

                    JSONObject json=jsonArray.getJSONObject(i);


                    country.setName(json.getString("name"));
                    country.setRegion(json.getString("region"));
                    country.setCapital(json.getString("capital"));
                    country.setSubregion(json.getString("subregion"));
                    country.setPopulation(json.getInt("population"));
                    country.setBorders(json.getJSONArray("borders").toString());
                    country.setLanguages(json.getJSONArray("languages").toString());
                    URL    url = new URL(json.getString("flag"));
                    SVG svg= SVG.getFromInputStream( url.openConnection().getInputStream());

                    Bitmap  newBM = Bitmap.createBitmap((int) Math.ceil(svg.getDocumentWidth()),
                            (int) Math.ceil(svg.getDocumentHeight()),
                            Bitmap.Config.ARGB_8888);

                    Canvas  bmcanvas = new Canvas(newBM);
                    bmcanvas.drawRGB(255, 255, 0);
                    svg.renderToCanvas(bmcanvas);

                    country.setFlag(newBM);

                    mroomDB.countryDao().insertall(country);

                }



            } catch (JSONException | MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (SVGParseException e) {
                e.printStackTrace();
            }




            return null;
        }


        @Override
        protected void onPostExecute(String svg) {
            super.onPostExecute(svg);
            country1=mroomDB.countryDao().getAll();
            showdata(country1);



        }
    }

    private void showdata(List<Country> country1) {

        pd.dismiss();

        if (country1.size()==0) {
            delete.setVisibility(View.GONE);
            Toast.makeText(this, "No Data Download", Toast.LENGTH_LONG).show();
        }else {

        listAdapter = new CountryAdapter(country1, this);
        recycler.setAdapter(listAdapter);}


    }






}