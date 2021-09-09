package com.pradum.asiacountries.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pradum.asiacountries.R;
import com.pradum.asiacountries.model.Country;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.ContactHolder>{

    private List<Country> contactsList;
    private Context mContext;


    public CountryAdapter(List<Country> contactsList, Context context) {
        this.contactsList = contactsList;
        this.mContext = context;
    }

    @NonNull
    @Override
    public ContactHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

        // Inflate the layout view you have created for the list rows here
        View view = layoutInflater.inflate(R.layout.country_item, parent, false);
        return new ContactHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CountryAdapter.ContactHolder holder, int position) {

        final Country contact = contactsList.get(position);

        holder.name.setText(contact.getName());
        holder.capital.setText(contact.getCapital());
        holder.region.setText(contact.getRegion());
        holder.subregion.setText(contact.getSubregion());
        holder.population.setText(contact.getPopulation().toString());
        holder.borders.setText(contact.getBorders().replaceAll("\\[|\\]", "").replaceAll("\"",""));

        holder.flag.setImageBitmap(contact.getFlag());


        try {
            JSONArray jsonArray = new JSONArray(contact.getLanguages());

            List<String> languages = new ArrayList<>();

            for (int i= 0;i<jsonArray.length();i++) {

                JSONObject object= jsonArray.getJSONObject(i);
                languages.add(object.getString("name"));

            }

            String string = languages.toString();
            holder.languages.setText(string.replaceAll("\\[|\\]", ""));



        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    @Override
    public int getItemCount() {
        return contactsList.size();
    }



    public class ContactHolder extends RecyclerView.ViewHolder {


        ImageView flag;
        TextView  name, capital,region,subregion, population, borders ,languages;


        public ContactHolder(@NonNull View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.name);
            capital= (TextView) itemView.findViewById(R.id.Capital);
            region= (TextView) itemView.findViewById(R.id.Region);
            subregion= (TextView) itemView.findViewById(R.id.SubRegion);
            population= (TextView) itemView.findViewById(R.id.population);
            borders= (TextView) itemView.findViewById(R.id.Borders);
            languages= (TextView) itemView.findViewById(R.id.Language);
            flag=  itemView.findViewById(R.id.flag);






        }



    }

}

