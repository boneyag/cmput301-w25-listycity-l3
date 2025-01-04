package com.example.listycity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.inputmethodservice.Keyboard;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity implements AddCityFragment.OnFragmentInteractionListener{

    ListView cityList;
    FloatingActionButton addCityButton;

    ArrayAdapter<City> cityAdapter;
    ArrayList<City> dataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cityList = (ListView) findViewById(R.id.city_list);

        String cities[] = {"Edmonton", "Calgary", "Vancouver", "Toronto", "Montreal", "Saskatoon", "Victoria", "Ottawa", "Quebec City", "London", "Kingston"};
        String provinces[] = {"AB", "AB", "BC", "ON", "QC", "SK", "BC", "ON", "QC", "ON", "ON"};

        dataList = new ArrayList<>();

        for (int i=0; i<cities.length; i++){
            dataList.add(new City(cities[i], provinces[i]));
        }

        cityAdapter = new CustomCityAdapter(this, R.layout.content, dataList);

        cityList.setAdapter(cityAdapter);

        addCityButton = findViewById(R.id.add_city_button);
        addCityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AddCityFragment().show(getSupportFragmentManager(),"ADD_CITY");
            }
        });

        cityList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                City clickedCity = cityAdapter.getItem(i);
                AddCityFragment.newInstance(clickedCity, i).show(getSupportFragmentManager(),
                        "EDIT_CITY");
                return false;
            }
        });
    }

    @Override
    public void onOkPressListener(City newCity) {
        dataList.add(newCity);
//        cityAdapter.notifyDataSetChanged();
    }

    @Override
    public void onEditPressListener(City editCity, int index) {
        City currentCity = dataList.get(index);
        currentCity.setCity(editCity.getCity());
        currentCity.setProvince(editCity.getProvince());
//        cityAdapter.notifyDataSetChanged();
    }
}