package com.example.listycity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class CustomCityAdapter extends ArrayAdapter<City> {
    private ArrayList<City> cities;
    private Context context;


    public CustomCityAdapter(@NonNull Context context, int resource, @NonNull ArrayList<City> cities) {
        super(context, resource, cities);
        this.cities = cities;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        View view = convertView;

        if (view == null){
            view = LayoutInflater.from(context).inflate(R.layout.content, parent, false);
        }

        City city = cities.get(position);

        TextView cityName = view.findViewById(R.id.city_name);
        TextView provinceName = view.findViewById(R.id.province_name);

        cityName.setText(city.getCity());
        provinceName.setText(city.getProvince());

        return view;
    }
}
