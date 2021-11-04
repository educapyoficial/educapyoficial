package com.educapyoficial.educapy.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.educapyoficial.educapy.models.Opcion;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ListOpcionesAdapter extends ArrayAdapter<Opcion> {

    private Context context;
    private List<Opcion> list;

    public static int position;
//    Locale locale = new Locale("en", "UK");
//    DecimalFormatSymbols symbols = new DecimalFormatSymbols(locale);
//
//    String pattern = "###,###.########";
//    DecimalFormat decimalFormat;

    public ListOpcionesAdapter(Context context, List<Opcion> list) {
        super(context, android.R.layout.simple_list_item_1, list);
        this.context = context;
        this.list = list;

    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
//        symbols.setDecimalSeparator(',');
//        symbols.setGroupingSeparator('.');
//        decimalFormat = new DecimalFormat(pattern, symbols);
        View vi = convertView;

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            vi = inflater.inflate(android.R.layout.simple_spinner_item, null);
        }

        Opcion item = list.get(position);

        if (item != null) {
            TextView textView = (TextView) vi.findViewById(android.R.id.text1);
            if (item.getValor() != null && !item.getValor().equals("")) {
                textView.setText(String.valueOf(item.getValor()));
            }
        }
        return vi;

    }

    public List<Opcion> getList() {
        return list;
    }

    public void setList(List<Opcion> list) {
        this.list = list;
    }

}
