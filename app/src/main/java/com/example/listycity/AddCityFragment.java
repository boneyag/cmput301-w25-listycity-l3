package com.example.listycity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class AddCityFragment extends DialogFragment {
    private static final String ARG_CITY = "city";
    private static final String ARG_INDEX = "index";

    private EditText cityName;
    private EditText provinceName;
    private OnFragmentInteractionListener listener;

    private City currentCity;
    private int index;

    public interface OnFragmentInteractionListener{
        void onOkPressListener(City newCity);
        void onEditPressListener(City editCity, int index);
    }

    static AddCityFragment newInstance(City city, int index){
        Bundle args = new Bundle();
        args.putSerializable(ARG_CITY, city);
        args.putSerializable(ARG_INDEX, index);

        AddCityFragment fragment = new AddCityFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            listener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString() +
                    " must implement the OnFragmentInteractionListener");
        }
    }

    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState){
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.add_city_fragment, null);
        cityName = view.findViewById(R.id.city_edit_text);
        provinceName = view.findViewById(R.id.province_edit_text);

        Bundle args = getArguments();
        if (args != null) {
            currentCity = (City) args.getSerializable(ARG_CITY);
            index = (int) args.getSerializable(ARG_INDEX);
            cityName.setText(currentCity.getCity());
            provinceName.setText(currentCity.getProvince());
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        if (currentCity != null){
            return builder
                    .setView(view)
                    .setTitle("Edit city")
                    .setNegativeButton("Cancel", null)
                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            String city = cityName.getText().toString();
                            String province = provinceName.getText().toString();
                            City cityObj = new City(city, province);
                            listener.onEditPressListener(cityObj, index);
                        }
                    })
                    .create();
        } else {
            return builder
                    .setView(view)
                    .setTitle("Add city")
                    .setNegativeButton("Cancel", null)
                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            String city = cityName.getText().toString();
                            String province = provinceName.getText().toString();
                            listener.onOkPressListener(new City(city, province));
                        }
                    })
                    .create();
        }

    }
}
