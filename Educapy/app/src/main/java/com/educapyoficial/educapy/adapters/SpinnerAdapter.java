package com.educapyoficial.educapy.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.educapyoficial.educapy.models.CursosModel;

import java.util.ArrayList;
import java.util.List;


public class SpinnerAdapter <T> extends BaseAdapter {
    protected Activity activity;
    protected List<CursosModel> items;
    private int mFieldId = 0;

    public SpinnerAdapter(Activity activity, int textViewResourceId,
                            ArrayList<CursosModel> items) {
        this.activity = activity;
        this.items = items;
        mFieldId = textViewResourceId;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);

    }

    public int getPosition(CursosModel cursosModel) {
        return items.indexOf(cursosModel);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View vi = convertView;

        if (convertView == null) {

            LayoutInflater inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            vi = inflater.inflate(
                    android.R.layout.simple_spinner_dropdown_item, null);

        }
        // if (position != 0) {
        // Rutas item = items.get(position);
        // TextView txt1 = (TextView) vi.findViewById(android.R.id.text1);
        // txt1.setText(item.getRutas().trim().toUpperCase());
        // }

        if (position == getCount()) {
            ((TextView) vi.findViewById(android.R.id.text1)).setText("");
            ((TextView) vi.findViewById(android.R.id.text1)).setHint("Seleccionar...."); // "Hint to be displayed"
        } else {
            CursosModel item = items.get(position);
            TextView txt1 = (TextView) vi.findViewById(android.R.id.text1);
            txt1.setText(item.getCursos().trim().toUpperCase());
        }

        return vi;
    }
}